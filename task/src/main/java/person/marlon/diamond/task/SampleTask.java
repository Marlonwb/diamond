package person.marlon.diamond.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleTask{

    public void greet(){
        System.out.println(Thread.currentThread().getName() + ":" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) +":hello world!");
    }

    public void feedback(){
        System.out.println(Thread.currentThread().getName() + ":" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) +":hi,man!");
    }
}
