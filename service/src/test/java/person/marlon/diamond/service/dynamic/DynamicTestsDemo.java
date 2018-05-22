package person.marlon.diamond.service.dynamic;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * The standard @Test annotation in JUnit Jupiter described in Annotations is very similar to the @Test annotation
 * in JUnit 4. Both describe methods that implement test cases. These test cases are static in the sense that they are
 * fully specified at compile time, and their behavior cannot be changed by anything happening at runtime. Assumptions
 * provide a basic form of dynamic behavior but are intentionally rather limited in their expressiveness.
 *
 * In addition to these standard tests a completely new kind of test programming model has been introduced in
 * JUnit Jupiter. This new kind of test is a dynamic test which is generated at runtime by a factory method that is
 * annotated with @TestFactory.
 *
 * In contrast to @Test methods, a @TestFactory method is not itself a test case but rather a factory for test cases.
 * Thus, a dynamic test is the product of a factory. Technically speaking, a @TestFactory method must return a Stream,
 * Collection, Iterable, or Iterator of DynamicNode instances. Instantiable subclasses of DynamicNode are
 * DynamicContainer and DynamicTest. DynamicContainer instances are composed of a display name and a list of dynamic
 * child nodes, enabling the creation of arbitrarily nested hierarchies of dynamic nodes. DynamicTest instances will
 * then be executed lazily, enabling dynamic and even non-deterministic generation of test cases.
 *
 * Any Stream returned by a @TestFactory will be properly closed by calling stream.close(), making it safe to use a
 * resource such as Files.lines().
 *
 * As with @Test methods, @TestFactory methods must not be private or static and may optionally declare parameters to
 * be resolved by ParameterResolvers.
 *
 * A DynamicTest is a test case generated at runtime. It is composed of a display name and an Executable. Executable is
 * a @FunctionalInterface which means that the implementations of dynamic tests can be provided as lambda expressions
 * or method references.
 */
class DynamicTestsDemo {

    /**
     * returns an invalid return type. Since an invalid return type cannot be detected at compile time, a JUnitException
     * is thrown when it is detected at runtime.
     */
    // This will result in a JUnitException!
    @TestFactory
    List<String> dynamicTestsWithInvalidReturnType() {
        return Collections.singletonList("Hello");
    }

    /**
     * The next five methods are very simple examples that demonstrate the generation of a Collection, Iterable,
     * Iterator, or Stream of DynamicTest instances. Most of these examples do not really exhibit dynamic behavior but
     * merely demonstrate the supported return types in principle. However, dynamicTestsFromStream() and
     * dynamicTestsFromIntStream() demonstrate how easy it is to generate dynamic tests for a given set of strings or a
     * range of input numbers.
     */
    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> assertTrue(true)),
                dynamicTest("2nd dynamic test", () -> assertEquals(4, 2 * 2))
        );
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestsFromIterable() {
        return Arrays.asList(
                dynamicTest("3rd dynamic test", () -> assertTrue(true)),
                dynamicTest("4th dynamic test", () -> assertEquals(4, 2 * 2))
        );
    }

    @TestFactory
    Iterator<DynamicTest> dynamicTestsFromIterator() {
        return Arrays.asList(
                dynamicTest("5th dynamic test", () -> assertTrue(true)),
                dynamicTest("6th dynamic test", () -> assertEquals(4, 2 * 2))
        ).iterator();
    }

    /**
     * demonstrate how easy it is to generate dynamic tests for a given set of strings or a range of input numbers.
     */
    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        return Stream.of("A", "B", "C")
                .map(str -> dynamicTest("test" + str, () -> { /* ... */ }));
    }

    /**
     * demonstrate how easy it is to generate dynamic tests for a given set of strings or a range of input numbers.
     */
    @TestFactory
    Stream<DynamicTest> dynamicTestsFromIntStream() {
        // Generates tests for the first 10 even integers.
        return IntStream.iterate(0, n -> n + 2).limit(10)
                .mapToObj(n -> dynamicTest("test" + n, () -> assertTrue(n % 2 == 0)));
    }

    /**
     * The next method is truly dynamic in nature. generateRandomNumberOfTests() implements an Iterator that generates
     * random numbers, a display name generator, and a test executor and then provides all three to DynamicTest.stream().
     * Although the non-deterministic behavior of generateRandomNumberOfTests() is of course in conflict with test
     * repeatability and should thus be used with care, it serves to demonstrate the expressiveness and power of dynamic
     * tests.
     */
    @TestFactory
    Stream<DynamicTest> generateRandomNumberOfTests() {

        // Generates random positive integers between 0 and 100 until
        // a number evenly divisible by 7 is encountered.
        Iterator<Integer> inputGenerator = new Iterator<Integer>() {

            Random random = new Random();
            int current;

            @Override
            public boolean hasNext() {
                current = random.nextInt(100);
                return current % 7 != 0;
            }

            @Override
            public Integer next() {
                return current;
            }
        };

        // Generates display names like: input:5, input:37, input:85, etc.
        Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

        // Executes tests based on the current input value.
        ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);

        // Returns a stream of dynamic tests.
        return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
    }

    /**
     * The last method generates a nested hierarchy of dynamic tests utilizing DynamicContainer.
     */
    @TestFactory
    Stream<DynamicNode> dynamicTestsWithContainers() {
        return Stream.of("A", "B", "C")
                .map(input -> dynamicContainer("Container " + input, Stream.of(
                        dynamicTest("not null", () -> assertNotNull(input)),
                        dynamicContainer("properties", Stream.of(
                                dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
                                dynamicTest("not empty", () -> assertFalse(input.isEmpty()))
                        ))
                )));
    }

}
