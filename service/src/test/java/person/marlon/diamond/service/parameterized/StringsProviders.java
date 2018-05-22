package person.marlon.diamond.service.parameterized;

import java.util.stream.Stream;

public class StringsProviders {
    static Stream<String> blankStrings() {
        return Stream.of("", " ", " \n ");
    }
}
