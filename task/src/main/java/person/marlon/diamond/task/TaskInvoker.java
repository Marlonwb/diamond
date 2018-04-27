package person.marlon.diamond.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaskInvoker {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("task-context.xml");
        SampleTask sampleTask = (SampleTask) ctx.getBean("sampleTask");
        sampleTask.greet();
    }
}
