package person.marlon.diamond.service;

import org.junit.jupiter.api.Test;
import person.marlon.diamond.service.test.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTests {

    @Test
    void add() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.add(1, 2), "1 + 1 should equal 2");
    }
}
