package person.marlon.diamond.service.parameterized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

class ParameterizedTests {

    /**
     * * @ValueSource is one of the simplest possible sources. It lets you specify a single array of literal values
     * * and can only be used for providing a single parameter per parameterized test invocation.
     *
     * * The following types of literal values are supported by @ValueSource.
     *
     * * short
     *
     * * byte
     *
     * * int
     *
     * * long
     *
     * * float
     *
     * * double
     *
     * * char
     *
     * * java.lang.String
     *
     * * java.lang.Class
     */
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        assertTrue(isPalindrome(candidate));
    }

    private boolean isPalindrome(String candidate){
        //TODO
        return true;
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    /**
     * * @EnumSource provides a convenient way to use Enum constants. The annotation provides an optional names parameter
     * * that lets you specify which constants shall be used. If omitted, all constants will be used like in the following
     * * example.
     */
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    /**
     * The @EnumSource annotation also provides an optional mode parameter that enables fine-grained control over which
     * constants are passed to the test method. For example, you can exclude names from the enum constant pool or specify
     * regular expressions as in the following examples.
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = { "DAYS", "HOURS" })
    void testWithEnumSourceExclude(TimeUnit timeUnit) {
        assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
        assertTrue(timeUnit.name().length() > 5);
    }
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithEnumSourceRegex(TimeUnit timeUnit) {
        String name = timeUnit.name();
        assertTrue(name.startsWith("M") || name.startsWith("N"));
        assertTrue(name.endsWith("SECONDS"));
    }

    /**
     * * @MethodSource allows you to refer to one or more factory methods of the test class or external classes.
     * * Such factory methods must return a Stream, Iterable, Iterator, or array of arguments. In addition, such factory
     * * methods must not accept any arguments. Factory methods within the test class must be static unless the test class
     * * is annotated with @TestInstance(Lifecycle.PER_CLASS); whereas, factory methods in external classes must always be
     * * static.
     *
     * * If you only need a single parameter, you can return a Stream of instances of the parameter type as demonstrated
     * * in the following example.
     */
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithSimpleMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }

    /**
     * If you do not explicitly provide a factory method name via @MethodSource, JUnit Jupiter will search for a factory
     * method that has the same name as the current @ParameterizedTest method by convention. This is demonstrated in the
     * following example.
     */
    @ParameterizedTest
    @MethodSource
    void testWithSimpleMethodSourceHavingNoValue(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> testWithSimpleMethodSourceHavingNoValue() {
        return Stream.of("foo", "bar");
    }

    /**
     * Streams for primitive types (DoubleStream, IntStream, and LongStream) are also supported as demonstrated by the
     * following example.
     */
    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    /**
     * If a test method declares multiple parameters, you need to return a collection or stream of Arguments instances
     * as shown below. Note that Arguments.of(Object…​) is a static factory method defined in the Arguments interface.
     */
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(3, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("foo", 1, Arrays.asList("a", "b")),
                Arguments.of("bar", 2, Arrays.asList("x", "y"))
        );
    }

    /**
     * * @CsvSource allows you to express argument lists as comma-separated values (i.e., String literals).
     * * @CsvSource uses a single quote ' as its quote character. See the 'baz, qux' value in the example above and in the
     * * table below. An empty, quoted value '' results in an empty String; whereas, an entirely empty value is interpreted
     * * as a null reference. An ArgumentConversionException is raised if the target type of a null reference is a
     * * primitive type.
     *
     * * Example Input  --> Resulting Argument List
     * * @CsvSource({ "foo, bar" })  -->  "foo", "bar"
     * * @CsvSource({ "foo, 'baz, qux'" }) -->  "foo", "baz, qux"
     * * @CsvSource({ "foo, ''" })  -->  "foo", ""
     * * @CsvSource({ "foo, " })  -->  "foo", null
     */
    @ParameterizedTest
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    void testWithCsvSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    /**
     * * @CsvFileSource lets you use CSV files from the classpath. Each line from a CSV file results in one invocation of
     * * the parameterized test.
     *
     * * In contrast to the syntax used in @CsvSource, @CsvFileSource uses a double quote " as the quote character.
     * * See the "United States of America" value in the example above. An empty, quoted value "" results in an empty
     * * String; whereas, an entirely empty value is interpreted as a null reference. An ArgumentConversionException is
     * * raised if the target type of a null reference is a primitive type.
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    /**
     * Argument Conversion
     *
     * Widening Conversion
     * JUnit Jupiter supports Widening Primitive Conversion for arguments supplied to a @ParameterizedTest. For example,
     * a parameterized test annotated with @ValueSource(ints = { 1, 2, 3 }) can be declared to accept not only an
     * argument of type int but also an argument of type long, float, or double.
     *
     * Implicit Conversion
     * To support use cases like @CsvSource, JUnit Jupiter provides a number of built-in implicit type converters.
     * The conversion process depends on the declared type of each method parameter.
     *
     * For example, if a @ParameterizedTest declares a parameter of type TimeUnit and the actual type supplied by the
     * declared source is a String, the string will be automatically converted into the corresponding TimeUnit enum
     * constant.
     *
     * String instances are currently implicitly converted to the following target types.
     *
     * Target Type : Example
     * boolean/Boolean : "true" → true
     * byte/Byte : "1" → (byte) 1
     * char/Character  : "o" → 'o'
     * short/Short : "1" → (short) 1
     * int/Integer : "1" → 1
     * long/Long : "1" → 1L
     * float/Float : "1.0" → 1.0f
     * double/Double : "1.0" → 1.0d
     * Enum subclass : "SECONDS" → TimeUnit.SECOND
     * java.io.File : "/path/to/file" → new File("/path/to/file")
     * java.math.BigDecimal : "123.456e789" → new BigDecimal("123.456e789")
     * java.math.BigInteger : "1234567890123456789" → new BigInteger("1234567890123456789")
     * java.net.URI : "http://junit.org/" → URI.create("http://junit.org/")
     * java.net.URL : "http://junit.org/" → new URL("http://junit.org/")
     * java.nio.charset.Charset : "UTF-8" → Charset.forName("UTF-8")
     * java.nio.file.Path : "/path/to/file" → Paths.get("/path/to/file")
     * java.time.Instant : "1970-01-01T00:00:00Z" → Instant.ofEpochMilli(0)
     * java.time.LocalDateTime : "2017-03-14T12:34:56.789" → LocalDateTime.of(2017, 3, 14, 12, 34, 56, 789_000_000)
     * java.time.LocalDate : "2017-03-14" → LocalDate.of(2017, 3, 14)
     * java.time.LocalTime : "12:34:56.789" → LocalTime.of(12, 34, 56, 789_000_000)
     * java.time.OffsetDateTime : "2017-03-14T12:34:56.789Z" → OffsetDateTime.of(2017, 3, 14, 12, 34, 56, 789_000_000, ZoneOffset.UTC)
     * java.time.OffsetTime : "12:34:56.789Z" → OffsetTime.of(12, 34, 56, 789_000_000, ZoneOffset.UTC)
     * java.time.YearMonth : "2017-03" → YearMonth.of(2017, 3)
     * java.time.Year : "2017" → Year.of(2017)
     * java.time.ZonedDateTime : "2017-03-14T12:34:56.789Z" → ZonedDateTime.of(2017, 3, 14, 12, 34, 56, 789_000_000, ZoneOffset.UTC)
     * java.util.Currency : "JPY" → Currency.getInstance("JPY")
     * java.util.Locale : "en" → new Locale("en")
     * java.util.UUID : "d043e930-7b3b-48e3-bdbe-5a3ccfb833db" → UUID.fromString("d043e930-7b3b-48e3-bdbe-5a3ccfb833db")
     */
    @ParameterizedTest
    @ValueSource(strings = "SECONDS")
    void testWithImplicitArgumentConversion(TimeUnit argument) {
        assertNotNull(argument.name());
    }

    /**
     * Fallback String-to-Object Conversion
     *
     * In addition to implicit conversion from strings to the target types listed in the above table, JUnit Jupiter also
     * provides a fallback mechanism for automatic conversion from a String to a given target type if the target type
     * declares exactly one suitable factory method or a factory constructor as defined below.
     *
     * * factory method: a non-private, static method declared in the target type that accepts a single String argument
     *   and returns an instance of the target type. The name of the method can be arbitrary and need not follow any
     *   particular convention.
     *
     * * factory constructor: a non-private constructor in the target type that accepts a single String argument.
     *
     * If multiple factory methods are discovered, they will be ignored. If a factory method and a factory constructor
     * are discovered, the factory method will be used instead of the constructor.
     *
     * For example, in the following @ParameterizedTest method, the Book argument will be created by invoking the
     * Book.fromTitle(String) factory method and passing "42 Cats" as the title of the book.
     */
    @ParameterizedTest
    @ValueSource(strings = "42 Cats")
    void testWithImplicitFallbackArgumentConversion(Book book) {
        assertEquals("42 Cats", book.getTitle());
    }

    /**
     * Explicit Conversion
     * Instead of relying on implicit argument conversion you may explicitly specify an ArgumentConverter to use for a
     * certain parameter using the @ConvertWith annotation like in the following example.
     */
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithExplicitArgumentConversion(
            @ConvertWith(ToStringArgumentConverter.class) String argument) {

        assertNotNull(TimeUnit.valueOf(argument));
    }

    /**
     * Explicit argument converters are meant to be implemented by test and extension authors. Thus, junit-jupiter-params
     * only provides a single explicit argument converter that may also serve as a reference implementation:
     * JavaTimeArgumentConverter. It is used via the composed annotation JavaTimeConversionPattern.
     */
    @ParameterizedTest
    @ValueSource(strings = { "01.01.2017", "31.12.2017" })
    void testWithExplicitJavaTimeConverter(
            @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {

        assertEquals(2017, argument.getYear());
    }


    /**
     * Argument Aggregation
     * By default, each argument provided to a @ParameterizedTest method corresponds to a single method parameter.
     * Consequently, argument sources which are expected to supply a large number of arguments can lead to large method
     * signatures.
     *
     * In such cases, an ArgumentsAccessor can be used instead of multiple parameters. Using this API, you can access
     * the provided arguments through a single argument passed to your test method. In addition, type conversion is
     * supported as discussed in Implicit Conversion.
     *
     * An instance of ArgumentsAccessor is automatically injected into any parameter of type ArgumentsAccessor.
     */
    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
//        Person person = new Person(arguments.getString(0),
//                arguments.getString(1),
//                arguments.get(2, Gender.class),
//                arguments.get(3, LocalDate.class));
//
//        if (person.getFirstName().equals("Jane")) {
//            assertEquals(Gender.F, person.getGender());
//        }
//        else {
//            assertEquals(Gender.M, person.getGender());
//        }
//        assertEquals("Doe", person.getLastName());
//        assertEquals(1990, person.getDateOfBirth().getYear());
    }

    /**
     * Custom Aggregators
     * Apart from direct access to a @ParameterizedTest method’s arguments using an ArgumentsAccessor, JUnit Jupiter
     * also supports the usage of custom, reusable aggregators.
     *
     * To use a custom aggregator simply implement the ArgumentsAggregator interface and register it via
     * the @AggregateWith annotation on a compatible parameter in the @ParameterizedTest method. The result of the
     * aggregation will then be provided as an argument for the corresponding parameter when the parameterized test is
     * invoked.
     */
//    @ParameterizedTest
//    @CsvSource({
//            "Jane, Doe, F, 1990-05-20",
//            "John, Doe, M, 1990-10-22"
//    })
//    void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
        // perform assertions against person
//    }

//    public class PersonAggregator implements ArgumentsAggregator {
//        @Override
//        public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
//            return new Person(arguments.getString(0),
//                    arguments.getString(1),
//                    arguments.get(2, Gender.class),
//                    arguments.get(3, LocalDate.class));
//        }
//    }

    /**
     * If you find yourself repeatedly declaring @AggregateWith(MyTypeAggregator.class) for multiple parameterized test
     * methods across your codebase, you may wish to create a custom composed annotation such as @CsvToMyType that is
     * meta-annotated with @AggregateWith(MyTypeAggregator.class). The following example demonstrates this in action
     * with a custom @CsvToPerson annotation.
     */
//    @ParameterizedTest
//    @CsvSource({
//            "Jane, Doe, F, 1990-05-20",
//            "John, Doe, M, 1990-10-22"
//    })
//    void testWithCustomAggregatorAnnotation(@CsvToPerson Person person) {
//        // perform assertions against person
//    }

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.PARAMETER)
//    @AggregateWith(PersonAggregator.class)
//    public @interface CsvToPerson {
//    }

    /**
     * Customizing Display Names
     * By default, the display name of a parameterized test invocation contains the invocation index and the String
     * representation of all arguments for that specific invocation. However, you can customize invocation display names
     * via the name attribute of the @ParameterizedTest annotation like in the following example.
     *
     * The following placeholders are supported within custom display names.
     * Placeholder  -->  Description
     *
     * {index} --> the current invocation index (1-based)
     * {arguments} -->  the complete, comma-separated arguments list
     * {0}, {1}, …​ -->  an individual argument
     */
    @DisplayName("Display name of container")
    @ParameterizedTest(name = "{index} ==> first=''{0}'', second={1}")
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    void testWithCustomDisplayNames(String first, int second) {
    }
}
