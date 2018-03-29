package person.marlon.diamond.interceptor;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import person.marlon.diamond.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * sessionId的生成，维护登陆状态等验证操作
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //generate session ID
        String sessionId = request.getRequestedSessionId();
        if(StringUtil.isEmpty(sessionId)){
           //sessionId =UUID.randomUUID().toString().replace("-", "").toUpperCase();
            sessionId = request.getSession(true).getId();
            //System.out.println("sessionId = " + sessionId);
        }

        return true;
    }
}
