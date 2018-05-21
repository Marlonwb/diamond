package person.marlon.diamond.service.di;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static person.marlon.diamond.service.di.RandomParametersExtension.Random;

/**
 *  Other parameter resolvers must be explicitly enabled by registering appropriate extensions via @ExtendWith.
 *
 *  While not intended to be production-ready, it demonstrates the simplicity and expressiveness of both the
 *  extension model and the parameter resolution process. MyRandomParametersTest demonstrates how to inject
 *  random values into @Test methods.
 */
@ExtendWith(RandomParametersExtension.class)
class MyRandomParametersTest {

    @Test
    void injectsInteger(@Random int i, @Random int j) {
        assertNotEquals(i, j);
    }

    @Test
    void injectsDouble(@Random double d) {
        assertEquals(0.0, d, 1.0);
    }
}
