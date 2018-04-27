package person.marlon.diamond.common.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import person.marlon.diamond.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //set http request domain with custom port
        WebUtil.setAbsoluteRequestDomain(request,request.getServerName());

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){
            ModelMap modelMap = modelAndView.getModelMap();

            //in case of bringing extra redirect param when sever return redirect uri to the front point.
            if(StringUtils.contains(modelAndView.getViewName(),"redirect:")){
                return;
            }
            //deliver the absoluteDomain to teh front point.
            modelMap.addAttribute("appDomain",WebUtil.getAbsoluteRequestDomain(request));
        }
    }
}
