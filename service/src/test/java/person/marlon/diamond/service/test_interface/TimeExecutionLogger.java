package person.marlon.diamond.service.test_interface;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @ExtendWith and @Tag can be declared on a test interface so that classes that implement the interface automatically
 * inherit its tags and extensions. See Before and After Test Execution Callbacks for the source code of
 * the TimingExtension.
 */
@Tag("timed")
@ExtendWith(TimingExtension.class)
interface TimeExecutionLogger {
}
