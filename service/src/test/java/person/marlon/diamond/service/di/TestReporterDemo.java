package person.marlon.diamond.service.di;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;

/**
 * TestReporterParameterResolver:
 *  if a method parameter is of type TestReporter, the TestReporterParameterResolver will
 *  supply an instance of TestReporter. The TestReporter can be used to publish additional data about the current test
 *  run. The data can be consumed through TestExecutionListener.reportingEntryPublished() and thus be viewed by IDEs or
 *  included in reports.
 *
 *  In JUnit Jupiter you should use TestReporter where you used to print information to stdout or stderr in JUnit 4.
 *  Using @RunWith(JUnitPlatform.class) will even output all reported entries to stdout.
 */
class TestReporterDemo {

    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
    }

    @Test
    void reportSeveralValues(TestReporter testReporter) {
        HashMap<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");

        testReporter.publishEntry(values);
    }
}
