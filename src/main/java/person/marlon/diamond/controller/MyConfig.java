package person.marlon.diamond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import person.marlon.diamond.util.CharGraph;
import person.marlon.diamond.util.I18nUtil;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

@Configuration
@PropertySource(value = {"classpath:system.properties"},ignoreResourceNotFound=false)
public class MyConfig {

    @Autowired
    private Environment env;

    @PostConstruct
    private void ver(){
        System.out.println(env.getProperty("greeting"));
        System.out.println(CharGraph.generateFoZu());
        new Timer("dynamic").scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try{

                    System.out.println(/*new SimpleDateFormat().format()*/I18nUtil.getMessage("author"));
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            }
        },3000,5000);
    }
}