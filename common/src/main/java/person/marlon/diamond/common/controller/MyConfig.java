package person.marlon.diamond.common.controller;

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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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

    @PostConstruct
    private void ver(){
        System.out.println(Thread.currentThread().getName() + ":" + I18nUtil.getMessage("start_greeting") + I18nUtil.getMessage("author"));
        System.out.println(CharGraph.generateFoZu());
        taskExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + ":" +"logger = " + logger));

        CustomHashMap<String,String> map = new CustomHashMap<>();
        map.put("yhjasdsahdasd","1");
        logger.info(Thread.currentThread().getName() +":haahaahahahahaha");

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
    }
}