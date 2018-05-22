package person.marlon.diamond.service.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * An external, static factory method can be referenced by providing its fully qualified method name as demonstrated in
 * the following example.
 */
class ExternalMethodSourceDemo {
    @ParameterizedTest
    @MethodSource("person.marlon.diamond.service.parameterized.StringsProviders#blankStrings")
    void testWithExternalMethodSource(String blankString) {
        // test with blank string
        assertEquals("", blankString);
    }
}
