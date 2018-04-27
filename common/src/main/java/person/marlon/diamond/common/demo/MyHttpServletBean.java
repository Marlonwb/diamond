package person.marlon.diamond.common.demo;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Marlon Wang on 2017/1/8.
 */
public class MyHttpServletBean extends HttpServletBean {
    public MyHttpServletBean() {
        super();
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        super.doGet(req, resp);
//        resp.setContentType("text/javascript;charset=utf-8");
        String protocol = req.getProtocol();
        String Schema = req.getScheme();

        System.out.println(protocol);
        System.out.println(Schema);
        System.out.println(this.name);

        PrintWriter writer = resp.getWriter();
        writer.write(protocol+"<br/>" + Schema+"<br/>" + this.name);
        writer.flush();
    }


    @Override
    protected void initBeanWrapper(BeanWrapper bw) throws BeansException {
        super.initBeanWrapper(bw);
    }

    @Override
    protected void initServletBean() throws ServletException {
        super.initServletBean();
    }

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
    }

    @Override
    public ConfigurableEnvironment getEnvironment() {
        return super.getEnvironment();
    }

    @Override
    protected ConfigurableEnvironment createEnvironment() {
        return super.createEnvironment();
    }
}
