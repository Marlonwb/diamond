package person.marlon.diamond.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import person.marlon.diamond.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sessionId的生成，维护登陆状态等验证操作
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getRequestedSessionId();
        if(StringUtil.isEmpty(sessionId)){
           //sessionId =UUID.randomUUID().toString().replace("-", "").toUpperCase();
            sessionId = request.getSession(true).getId();
            //System.out.println("sessionId = " + sessionId);
        }
        return super.preHandle(request, response, handler);
    }
}
