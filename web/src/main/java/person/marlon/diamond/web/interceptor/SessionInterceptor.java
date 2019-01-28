package person.marlon.diamond.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sessionId的生成，维护登陆状态等验证操作
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //generate session ID
        String sessionId = request.getRequestedSessionId();
        if(StringUtils.isEmpty(sessionId)){
           //sessionId =UUID.randomUUID().toString().replace("-", "").toUpperCase();
            request.getSession(true);
            //System.out.println("sessionId = " + sessionId);
        }

        return true;
    }
}
