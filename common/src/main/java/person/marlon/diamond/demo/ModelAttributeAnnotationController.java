package person.marlon.diamond.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import person.marlon.diamond.demo.model.User;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by Lenovo on 2017/2/16.
 *
 * 使用 @ModelAttribute --Model属性值的赋值和获取
 * 当 @ModelAttribute 标记在方法上的时候，该方法将在处理器方法执行之前执行，
 * 然后把返回的对象存放在 session 或 Model属性中，属性名称可以使用 @ModelAttribute(“attributeName”) 在标记方法的时候指定，
 * 若未指定，则使用返回类型的类名称（首字母小写）作为属性名称。
 *
 * 使用@SessionAttributes--session值赋值和获取
 *
 */
@Controller
@RequestMapping( "/ma" )
//@SessionAttributes(value={ "user" }, types={User. class})//注意value属性对应的是User对象的属性名
public class ModelAttributeAnnotationController {

        @ModelAttribute( "hello" )
        public String getModel() {
            System. out .println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) +"-------------Hello---------" );
            return "hello world!" ;
        }

        @ModelAttribute ( "intAge" )
        public int getInteger() {
            System. out .println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) +"-------------intAge---------------" );
            return 10;
        }


        @ModelAttribute ( "user1" )
        public User getUser() {
            System. out .println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) +"---------getUser-------------" );
            return new User( "baby" ,"22");
        }

        @RequestMapping ( "/sayHello" )
        public void sayHello(@ModelAttribute ( "hello" ) String hello, @ModelAttribute ( "intAge" ) int intAge, @ModelAttribute ( "user1" ) User user,
                             Writer writer, HttpSession session) throws IOException {
            writer.write( "Hello: " + hello + " , userName: " + user.getName() +"intAge: "+ intAge);
            writer.write( "\r" );

            //以下用来验证session中是否有数据写入
            Enumeration enume = session.getAttributeNames();
            while (enume.hasMoreElements()){
                writer.write(enume.nextElement() + "\r" );
            }

        }
}
