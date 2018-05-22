package person.marlon.diamond.service.template;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TestTemplateInvocationContextProvider extension API is primarily intended for implementing different kinds of
 * tests that rely on repetitive invocation of a test-like method albeit in different contexts — for example, with
 * different parameters, by preparing the test class instance differently, or multiple times without modifying the
 * context.Please refer to the implementations of Repeated Tests or Parameterized Tests which use this extension point
 * to provide their functionality.
 */
class TemplateTest {
    @TestTemplate
    @ExtendWith(MyTestTemplateInvocationContextProvider.class)
    void testTemplate(String parameter) {
        assertEquals(3, parameter.length());
    }
}
