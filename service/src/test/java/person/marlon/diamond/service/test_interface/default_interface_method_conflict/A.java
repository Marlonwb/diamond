package person.marlon.diamond.service.test_interface.default_interface_method_conflict;

import org.junit.jupiter.api.Test;

public interface A {
    @Test
    void hello();

    default void name(){
        hello();
        System.out.println("name = A");
    }
}
