package person.marlon.diamond.service.test_interface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * A test class that uses the example TimingExtension
 *
 * Since the TimingExtensionTests class registers the TimingExtension via @ExtendWith, its tests will
 * have this timing applied when they execute.
 */
@ExtendWith(TimingExtension.class)
class TimingExtensionTests {

    @Test
    void sleep20ms() throws Exception {
        Thread.sleep(20);
    }

    @Test
    void sleep50ms() throws Exception {
        Thread.sleep(50);
    }
}
