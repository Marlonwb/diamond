package person.marlon.diamond.service.repeat;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit Jupiter provides the ability to repeat a test a specified number of times simply by annotating a method
 * with @RepeatedTest and specifying the total number of repetitions desired. Each invocation of a repeated test
 * behaves like the execution of a regular @Test method with full support for the same lifecycle callbacks and
 * extensions.
 *
 * The following example demonstrates how to declare a test named repeatedTest() that will be automatically
 * repeated 10 times.
 *
 * #@RepeatedTest(10)
 * void repeatedTest() {
 *     // ...
 * }
 * In addition to specifying the number of repetitions, a custom display name can be configured for each repetition
 * via the name attribute of the @RepeatedTest annotation. Furthermore, the display name can be a pattern composed of a
 * combination of static text and dynamic placeholders. The following placeholders are currently supported.
 *
 * --{displayName}: display name of the @RepeatedTest method
 *
 * --{currentRepetition}: the current repetition count
 *
 * --{totalRepetitions}: the total number of repetitions
 *
 * The default display name for a given repetition is generated based on the following pattern: "repetition
 * {currentRepetition} of {totalRepetitions}". Thus, the display names for individual repetitions of the previous
 * repeatedTest() example would be: repetition 1 of 10, repetition 2 of 10, etc. If you would like the display name
 * of the @RepeatedTest method included in the name of each repetition, you can define your own custom pattern or use
 * the predefined RepeatedTest.LONG_DISPLAY_NAME pattern.
 * The latter is equal to "{displayName} :: repetition {currentRepetition} of {totalRepetitions}" which results in
 * display names for individual repetitions like repeatedTest() :: repetition 1 of 10, repeatedTest() :: repetition 2 of 10, etc.
 *
 * In order to retrieve information about the current repetition and the total number of repetitions programmatically,
 * a developer can choose to have an instance of RepetitionInfo injected into a @RepeatedTest, @BeforeEach,
 * or @AfterEach method.
 *-----------------------------------------------------------------------------------------------------------
 * The RepeatedTestsDemo class at the end of this section demonstrates several examples of repeated tests.
 *
 * The repeatedTest() method is identical to example from the previous section; whereas,
 * repeatedTestWithRepetitionInfo() demonstrates how to have an instance of RepetitionInfo injected
 * into a test to access the total number of repetitions for the current repeated test.
 *
 * The next two methods demonstrate how to include a custom @DisplayName for the @RepeatedTest method
 * in the display name of each repetition. customDisplayName() combines a custom display name with a
 * custom pattern and then uses TestInfo to verify the format of the generated display name.
 * repeat! is the {displayName} which comes from the @DisplayName declaration, and 1/1 comes from
 * {currentRepetition}/{totalRepetitions}. In contrast, customDisplayNameWithLongPattern() uses the
 * aforementioned predefined RepeatedTest.LONG_DISPLAY_NAME pattern.
 *
 * repeatedTestInGerman() demonstrates the ability to translate display names of repeated tests into
 * foreign languages — in this case German, resulting in names for individual repetitions
 * such as: Wiederholung 1 von 5, Wiederholung 2 von 5, etc.
 */
class RepeatedTestsDemo {
    private Logger logger = LoggerFactory.getLogger(RepeatedTestsDemo.class);// ...

    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        String methodName = testInfo.getTestMethod().get().getName();
        logger.info(String.format("About to execute repetition %d of %d for %s", //
                currentRepetition, totalRepetitions, methodName));
//        logger.info("About to execute repetition {} of {} for {}", //
//                currentRepetition, totalRepetitions, methodName);
    }

    @RepeatedTest(10)
    void repeatedTest() {
        // ...
    }

    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "repeat! 1/1");
    }

    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Details... :: repetition 1 of 1");
    }

    @RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
    void repeatedTestInGerman() {
        // ...
    }
}
