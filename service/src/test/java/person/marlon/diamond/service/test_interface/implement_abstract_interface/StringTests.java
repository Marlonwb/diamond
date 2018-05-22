package person.marlon.diamond.service.test_interface.implement_abstract_interface;

/**
 * 实现接口，接口上的默认实现方法调用接口方法时，实现类都会默认继承，
 * 接口上的@Test标签声明后，子类就可以不必声明，自动都会继承过来，@Test标签有继承性
 */
class StringTests implements ComparableContract<String>, EqualsContract<String>{
    @Override
    public String createValue() {
        return "foo";
    }

    @Override
    public String createSmallerValue() {
        return "bar"; // 'b' < 'f' in "foo"
    }

    @Override
    public String createNotEqualValue() {
        return "baz";
    }
}
