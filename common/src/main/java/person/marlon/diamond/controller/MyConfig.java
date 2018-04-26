package person.marlon.diamond.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import person.marlon.diamond.util.CharGraph;
import person.marlon.diamond.util.I18nUtil;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource(value = {"classpath:system.properties"},ignoreResourceNotFound=false)
public class MyConfig {

    private Logger logger = LoggerFactory.getLogger(MyConfig.class);

    @Autowired
    private Environment env;//env.getProperty("start_greeting")

    @PostConstruct
    private void ver(){
        System.out.println(Thread.currentThread().getName() + ":" + I18nUtil.getMessage("start_greeting") + I18nUtil.getMessage("author"));
        System.out.println(CharGraph.generateFoZu());

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