package person.marlon.diamond.service.parameterized;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToStringArgumentConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(String.class, targetType, "Can only convert to String");
        return String.valueOf(source);
    }
}
