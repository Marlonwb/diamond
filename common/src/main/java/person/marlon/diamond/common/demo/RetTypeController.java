package person.marlon.diamond.common.demo;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import person.marlon.diamond.common.demo.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 路径匹配规则：URLPathHelper:getPathWithinServletMapping()
 * {@link org.springframework.web.util.UrlPathHelper}
 *
 * DispatcherServlet路径匹配
 * {@link org.springframework.web.servlet.DispatcherServlet}
 */
@Controller
@RequestMapping("rt")
public class RetTypeController {
    //===================================方法间跳转======================================================
    /**
     * 请求转发
     */
    @RequestMapping("/forward")
    public String forward(){
        return"forward:/generic/rt/home";//路径不要加"/",经测试302返回路径会丢失前置路径,造成404
    }

    /**
     * 页面重定向
     */
    @RequestMapping("/redirect")
    public String redirect(){
        return"redirect:home";//重定向路径最好不要加"/",经测试302返回路径会丢失前置路径,造成404,会认为是根目录,直接在根目录下
    }

    /**
     * 请求转发 404
     */
    @RequestMapping("/forward404")
    public String forward404(){
        return"forward:/home";
    }

    /**
     * 页面重定向 404
     */
    @RequestMapping("/redirect404")
    public String redirect404(){
        return"redirect:/home";
    }

    /**
     * WEB-INF下不能直接访问
     * 返回类型：Map集合(以及其他类型，返回的视图默认是RequestMapping路径)
     */
    @RequestMapping("/test")
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("user", "bwang");//map.put()相当于request.setAttribute方法
        return map;
    }
   //===================================方法间跳转======================================================
    /**
     * 返回类型：String
     */
    @RequestMapping("/home")
    public String gethome(Model model) {
        model.addAttribute("user","bwang");
        model.addAttribute("user1","marlon");
        return "test";
    }

    /**
     * 返回类型：ModelAndView
     * {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/mav")
    public ModelAndView getMav() {
        ModelAndView mav = new ModelAndView("test");//ModelAndView构造方法可以指定返回的页面名称

        User user = new User("bwang", "22");

        mav.addObject("user", user);
        System.out.println("" + user);
        mav.addObject("user1", new Gson().toJson(new User("bwang", "23")));
        return mav;
    }

    /**
     * 返回类型：String(用Model/ModelMap绑定数据:如果key相同，谁最后绑定，从谁那里获取)
     *  {@link org.springframework.ui.Model}
     *  {@link org.springframework.ui.ModelMap}
     *  {@link org.springframework.ui.ExtendedModelMap}
     */
    @RequestMapping("/string")
    public String getString(Model model, ModelMap modelMap) {
        model.addAttribute("user","model:bwang");
        modelMap.addAttribute("user1","modelMap:marlon");
        modelMap.addAttribute("user","modelMap:bwang");
        model.addAttribute("user1","model:marlon");
        return "test";
    }

    /**
     * 返回类型：String(有@ResponseBody注解)
     */
    @RequestMapping("/json")
    @ResponseBody
    public String getStringReturnWithResponseBody(Model model) {
        return new Gson().toJson(new User("bwang", "56"));
    }

}
