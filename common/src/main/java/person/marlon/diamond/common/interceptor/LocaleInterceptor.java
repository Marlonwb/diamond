package person.marlon.diamond.common.interceptor;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import person.marlon.diamond.common.util.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 参考LocaleChangeInterceptor
 * @see org.springframework.web.servlet.i18n.LocaleChangeInterceptor
 */
public class LocaleInterceptor extends HandlerInterceptorAdapter {

    private static final String COOKIE_LOCALE_NAME = "locale";//和localeResolver配置的cookieName保持一致

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie cookie = CookieUtil.getCookie(request, COOKIE_LOCALE_NAME);
        if(cookie == null){
            //过滤设置语言的方法请求
            if(!request.getRequestURI().endsWith("/language")){
                //DispatcherServlet中的doService相关方法将localResolver对象保存在了request对象中，request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException(
                            "No LocaleResolver found: not in a DispatcherServlet request?");
                }
                localeResolver.setLocale(request,response,localeResolver.resolveLocale(request));
            }
        }

        return true;
    }
}
