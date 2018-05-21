package person.marlon.diamond.service.di;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Dependency Injection for Constructors and Methods
 *
 * ParameterResolver defines the API for test extensions that wish to dynamically resolve parameters at runtime.
 * If a test constructor or a @Test, @TestFactory, @BeforeEach, @AfterEach, @BeforeAll, or @AfterAll method accepts
 * a parameter, the parameter must be resolved at runtime by a registered ParameterResolver.
 *
 * TestInfoParameterResolver:
 * if a method parameter is of type TestInfo, the TestInfoParameterResolver will supply
 * an instance of TestInfo corresponding to the current test as the value for the parameter. The TestInfo can then
 * be used to retrieve information about the current test such as the test’s display name, the test class, the
 * test method, or associated tags. The display name is either a technical name, such as the name of the test class
 * or test method, or a custom name configured via @DisplayName.
 *
 * TestInfo
 * acts as a drop-in replacement for the TestName rule from JUnit 4. The following demonstrates how to have
 * TestInfo injected into a test constructor, @BeforeEach method, and @Test method.
 *
 * RepetitionInfoParameterResolver:
 * if a method parameter in a @RepeatedTest, @BeforeEach, or @AfterEach method is of
 * type RepetitionInfo, the RepetitionInfoParameterResolver will supply an instance of RepetitionInfo. RepetitionInfo
 * can then be used to retrieve information about the current repetition and the total number of repetitions for the
 * corresponding @RepeatedTest. Note, however, that RepetitionInfoParameterResolver is not registered outside the
 * context of a @RepeatedTest. See Repeated Test Examples.
 */
class TestInfoDemo {
    TestInfoDemo(TestInfo testInfo) {
        assertEquals("TestInfo Demo", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    void test2() {
    }
}
