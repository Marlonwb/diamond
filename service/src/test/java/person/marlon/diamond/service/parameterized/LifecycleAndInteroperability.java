package person.marlon.diamond.service.parameterized;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Each invocation of a parameterized test has the same lifecycle as a regular @Test method. For example,@BeforeEach
 * methods will be executed before each invocation. Similar to Dynamic Tests, invocations will appear one
 * by one in the test tree of an IDE. You may at will mix regular @Test methods and @ParameterizedTest methods within
 * the same test class.
 *
 * You may use ParameterResolver extensions with @ParameterizedTest methods. However, method parameters that are
 * resolved by argument sources need to come first in the argument list. Since a test class may contain regular tests
 * as well as parameterized tests with different parameter lists, values from argument sources are not resolved for
 * lifecycle methods (e.g. @BeforeEach) and test class constructors.
 */
class LifecycleAndInteroperability {
    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        // ...
    }

    @ParameterizedTest
    @ValueSource(strings = "foo")
    void testWithRegularParameterResolver(String argument, TestReporter testReporter) {
        testReporter.publishEntry("argument", argument);
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        // ...
    }
}
