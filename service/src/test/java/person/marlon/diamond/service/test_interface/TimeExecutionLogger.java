package person.marlon.diamond.service.test_interface;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("timed")
@ExtendWith(TimingExtension.class)
interface TimeExecutionLogger {
}
