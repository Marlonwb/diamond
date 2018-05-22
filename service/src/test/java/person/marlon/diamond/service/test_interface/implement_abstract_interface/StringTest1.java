package person.marlon.diamond.service.test_interface.implement_abstract_interface;

/**
 * 当存在两个实现类，接口的默认方法调用了接口方法，实际上跑的也是实现类，会继承过来方法
 */
public class StringTest1 implements ComparableContract<String>, EqualsContract<String>{

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
