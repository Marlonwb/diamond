package person.marlon.diamond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource(value = {"classpath:system.properties"},ignoreResourceNotFound=false)
public class MyConfig {

    @Autowired
    private Environment env;

    @PostConstruct
    private void ver(){
        String greeting = env.getProperty("greeting");
        System.out.println(greeting);
        System.out.println("================");

    }
}