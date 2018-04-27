package person.marlon.diamond.common.demo;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import person.marlon.diamond.common.demo.model.User;

import java.text.SimpleDateFormat;

/**
 * Created by Marlon Wang on 2016/11/26.
 */
@Controller
@RequestMapping("/greeting")
public class Greeting {

    @RequestMapping(value = "")
    public String toHome() {
        return "index";
    }

    @RequestMapping("tohome")
    public String sayHello() {
        return "forward:index";
    }

    @RequestMapping(value = "/hello/withname",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String echo(@RequestParam String name){
        return  new Gson().toJson(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis())+" hello: "+ name);
    }
//    public static void main(String[] args){
//        Greeter greeter = new Greeter("Marlon Wang :)");
//        System.out.println(greeter.sayHello());
//    }
//}

    @RequestMapping("/hi")
    public String toHome( User user){
//        attr.addAttribute("name",name);

//        return "test";
        return "redirect:https://www.baidu.com";
//        model.addAttribute("name",name);
//        return "forward:hello";
    }

    @RequestMapping("/greet/{name}")
    @ResponseBody
    public String toHome(@PathVariable String name){
        return name;
//        model.addAttribute("name",name);
//        return "forward:hello";
    }
}