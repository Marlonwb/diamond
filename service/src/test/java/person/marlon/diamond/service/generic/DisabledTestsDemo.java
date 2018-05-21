package person.marlon.diamond.service.generic;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 包含被禁用测试方法的测试用例。
 */
class DisabledTestsDemo {
    @Disabled
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }

}
