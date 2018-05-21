package person.marlon.diamond.service.test_interface;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test Interfaces and Default Methods
 *
 * JUnit Jupiter allows @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, @TestTemplate, @BeforeEach,
 * and @AfterEach to be declared on test_interface default methods. @BeforeAll and @AfterAll can either be declared
 * on static methods in a test test_interface or on test_interface default methods if the test test_interface or test class is
 * annotated with @TestInstance(Lifecycle.PER_CLASS) (see Test Instance Lifecycle). Here are some examples.
 */

@TestInstance(Lifecycle.PER_CLASS)//keep with this object with singleton.
interface TestLifecycleLogger {

    Logger LOG = LoggerFactory.getLogger(TestLifecycleLogger.class);

    @BeforeAll
    default void beforeAllTests() {
        LOG.info("Before all tests");
    }

    @AfterAll
    default void afterAllTests() {
        LOG.info("After all tests");
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        LOG.info("About to execute [{}]", testInfo.getDisplayName());
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        LOG.info("Finished executing [{}]", testInfo.getDisplayName());
    }

    @Test
    default void doTest(){
        System.out.println("true = " + true);
    }

    @Test
    default void doTest1(){
        System.out.println("false = " + false);
    }

}
