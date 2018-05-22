package person.marlon.diamond.service.test_interface.default_interface_method_conflict;

import org.junit.jupiter.api.Test;

public interface B{
    @Test
    default void name(){
        System.out.println("name = B");
    }
}
