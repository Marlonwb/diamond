package person.marlon.diamond.web.interceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import person.marlon.diamond.common.dto.User;
import person.marlon.diamond.common.generic.ApiResponse;
import person.marlon.diamond.common.util.EncryptUtil;
import person.marlon.diamond.common.util.WebUtil;
import person.marlon.diamond.service.user.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * sessionId的生成，维护登陆状态等验证操作
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    private static final Map<String, JsonObject> sessionLoginMap = new HashMap<>();

    private static final Object sessionLock = new Object(); //多端登录，登陆计数可能会有并发问题，用锁或者CAS？后面尝试

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //generate session ID
        String sessionId = request.getRequestedSessionId();
        if(StringUtils.isEmpty(sessionId)){
           //sessionId =UUID.randomUUID().toString().replace("-", "").toUpperCase();
            sessionId = request.getSession(true).getId();
            //System.out.println("sessionId = " + sessionId);
        }
        //set http request domain with custom port
        WebUtil.setAbsoluteRequestDomain(request,request.getServerName());

        String pathInfo = request.getPathInfo();
        logger.info("received request:{}",pathInfo);
        // 登录：检测非正常登陆模式，防止被暴力破解
        if(pathInfo.contains("/login")){
            JsonObject loginJson  = null;
            if(!sessionLoginMap.containsKey(sessionId)){
                loginJson = new JsonObject();

                loginJson.addProperty("continuousLoginCount",0); //快速连续登录次数 < 4
                loginJson.addProperty("totalAttemptLoginCount",0);// 2分钟内总登录次数  > 8
                sessionLoginMap.put(sessionId,loginJson);
            }
            loginJson = sessionLoginMap.get(sessionId);
            if(loginJson!= null){
                int continuousLoginCount = loginJson.get("continuousLoginCount").getAsInt();
                int totalAttemptLoginCount = loginJson.get("totalAttemptLoginCount").getAsInt();
                long currentTime = System.currentTimeMillis();
                if(loginJson.get("lastLoginTime") != null){
                    long lastLoginTime = loginJson.get("lastLoginTime").getAsLong();
                    if((currentTime - lastLoginTime) / 1000L  < 2){
                        continuousLoginCount++;
                    }
                }else{ //第一次访问，没有lastLoginTime,直接+1
                    continuousLoginCount++;
                }
                totalAttemptLoginCount++;

                loginJson.addProperty("lastLoginTime", currentTime);
                loginJson.addProperty("continuousLoginCount",continuousLoginCount);
                loginJson.addProperty("totalAttemptLoginCount",totalAttemptLoginCount);

                // authentication
                if(authenticate(request,loginJson,sessionId)){
                    sessionLoginMap.put(sessionId,loginJson);
                    return true;
                }else{
                    // Check if it is a normal request, if not, let it log in with the verification code next time.
                    // 快速连续请求登录到达阈值，进行验证码验证
                    if(continuousLoginCount >= 4){
                        loginJson.addProperty("continuousLoginCount",0);
                        loginJson.addProperty("totalAttemptLoginCount",0);
                        // 验证码
                        String verificationCode = generateValidateCode(6);
                        loginJson.addProperty("verificationCode",verificationCode);
                        logger.info("will return verificationCode:[{}]",verificationCode);
                        sessionLoginMap.put(sessionId,loginJson);
                        String result = new ApiResponse<String>(verificationCode,-1,"need check validate code!").toString();
                        sendHttpMessage(response,null,result);
                        return false;
                    }

                    if(totalAttemptLoginCount > 8){
                        loginJson.addProperty("continuousLoginCount",0);
                        loginJson.addProperty("totalAttemptLoginCount",0);
                        // 验证码
                        String verificationCode = generateValidateCode(6);
                        loginJson.addProperty("verificationCode",verificationCode);
                        logger.info("will return verificationCode:[{}]",verificationCode);
                        sessionLoginMap.put(sessionId,loginJson);
                        String result = new ApiResponse<String>(verificationCode,-1,"need check validate code!").toString();
                        sendHttpMessage(response,null,result);
                        return false;
                    }
                    sessionLoginMap.put(sessionId,loginJson);
                }
            }
        }else{
//            if(!sessionLoginMap.containsKey(sessionId) || sessionLoginMap.get(sessionId) == null){
//                response.sendRedirect(WebUtil.getAbsoluteRequestDomain(request)+ "/static/login.html");
//                return false;
//            }else{
                if(WebUtil.getLoginUser(request) != null){
                    return true;
                }
//            }
        }
        response.sendRedirect(WebUtil.getAbsoluteRequestDomain(request)+ "/static/login.html#fail");
        return false;
    }

    @Resource
    private UserService userService;

    private boolean authenticate(HttpServletRequest request,JsonObject loginJson,String sessionId){
        String bodyString = readRequestBody(request);
        if(StringUtils.isNotEmpty(bodyString)){
            JsonObject jsonObject = new Gson().fromJson(bodyString,JsonObject.class);
            //针对/login接口带来的数据,放到Request对象中去，login接口不要再用@RequestBody，因为已经读取过一遍流
            WebUtil.setLoginRequestBody(request,jsonObject);
            if(jsonObject.get("userName") != null && jsonObject.get("passwd")!=null){
                String userName = jsonObject.get("userName").getAsString();
                String passwd = jsonObject.get("passwd").getAsString();
                User loginUser = userService.getUserByName(userName);
                if(loginUser != null){
                    if(Objects.equals(EncryptUtil.encrypt(passwd, EncryptUtil.SHA256), loginUser.getPassword())){
                        // if request with verificationCode
                        if( jsonObject.get("vCode")!=null){
                            String vCode = jsonObject.get("vCode").getAsString();
                            String cachedVerificationCode = loginJson.get("verificationCode").getAsString();
                            if(!vCode.toLowerCase().equals(cachedVerificationCode.toLowerCase())){
                                // 验证码错误，重新生成一次验证码
                                String verificationCode = generateValidateCode(6);
                                loginJson.addProperty("verificationCode",verificationCode);
                                logger.info("authenticate failed! will regenerateValidateCode verificationCode:[{}]",verificationCode);
                                sessionLoginMap.put(sessionId,loginJson);
                                return false;
                            }
                        }
                        // 储存登陆用户
                        WebUtil.setLoginUser(request,loginUser);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String readRequestBody(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader();) {
            char[]buff = new char[1024];
            int len;
            while((len = reader.read(buff)) > 0) {
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Ascii码
     *
     * 48-57    0-9
     * 65-90    A-Z
     * 97-122   a-z
     *
     * 按照连续 10 + 52 = 62 个数字对上面的字母做映射
     */
    private char[] asciiChars = null;
    private String generateValidateCode(int length){
        // init when called first time.
        if(asciiChars == null){
            asciiChars = new char[62];
            int charCode = 48;
            for(int i = 0; i < 62; i++){
                //0-9 48-57
                if(i < 10){
                    asciiChars[i] = (char)charCode;
                    charCode++;
                }
                //A-Z 65-90
                if(i >= 10 && i < 36){
                    if(charCode < 65){
                        charCode = 65;
                    }
                    asciiChars[i] = (char)charCode;
                    charCode ++;
                }
                //a-z 97-122
                if(i >= 36){
                    if(charCode < 97){
                        charCode = 97;
                    }
                    asciiChars[i] = (char)charCode;
                    charCode ++;
                }
            }

        }
       char[] validateCode = new char[length];
       for(int i = 0; i < length; i++){
           validateCode[i] = asciiChars[new Random().nextInt(62)];
       }
        return new String(validateCode);
    }

    private void sendHttpMessage(HttpServletResponse response,Integer errorCode,String msg){
        if(errorCode != null){
            response.setStatus(errorCode);
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.write(msg);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
