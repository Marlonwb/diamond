package person.marlon.diamond.web.common.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import person.marlon.diamond.common.util.CharGraph;
import person.marlon.diamond.common.util.CustomHashMap;
import person.marlon.diamond.common.util.I18nUtil;
import person.marlon.diamond.common.dto.Major;
import person.marlon.diamond.service.major.MajorService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Configuration
@PropertySource(value = {"classpath:system.properties"},ignoreResourceNotFound=true)
public class MyConfig {

    private Logger logger = LoggerFactory.getLogger(MyConfig.class);

    private final Environment env;//env.getProperty("start_greeting")\

    @Autowired
    public MyConfig(Environment env) {
        this.env = env;
    }

    @Resource
    private TaskExecutor taskExecutor;
    
    @Resource
    private MajorService majorService;

    @PostConstruct
    private void ver(){
        System.out.println(Thread.currentThread().getName() + ": " + I18nUtil.getMessage("start_greeting") + " "+ I18nUtil.getMessage("author"));
        System.out.println(CharGraph.generateFoZu());
        taskExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + ":" +"logger = " + logger));
    
        List<Major> majorList = majorService.getMajorList();
        System.out.println("new Gson().toJson(majorList) = " + new Gson().toJson(majorList));
        
        CustomHashMap<String,String> map = new CustomHashMap<>();
        map.put("yhjasdsahdasd","1");
        logger.info(Thread.currentThread().getName() +":haahaahahahahaha");

        logger.info(UUID.randomUUID().toString().replace("-","").toUpperCase());

//        new Timer("dynamic").scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                try{
//
//                    System.out.println(/*new SimpleDateFormat().format()*/I18nUtil.getMessage("author"));
//                }catch(Exception e){
//                    System.out.println(e.getMessage());
//                }
//
//            }
//        },3000,5000);

//        WebUtil.checkAllowedBrowser(WebUtil.getBrowserType())
    }
}