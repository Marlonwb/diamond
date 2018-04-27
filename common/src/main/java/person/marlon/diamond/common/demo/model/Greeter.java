package person.marlon.diamond.common.demo.model;

/**
 * Created by Marlon Wang on 2016/11/26.
 */
public class Greeter {
    private String toWhom;

    public Greeter(String toWhom) {
        this.toWhom = toWhom;
    }

    public String sayHello(){

        return "person.marlon.diamond.controller.Hello "+toWhom;
    }
}
