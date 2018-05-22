package person.marlon.diamond.service.test_interface.default_interface_method_conflict;

/**
 * 默认接口冲突，如果两个接口没有相关性，则子类必须声明，
 * 如果接口有继承性,则以最近的实现类为基准：可以将B继承A，注释本类的实现方法进行测试
 */
class DefaultInterfaceMethodConflict implements A{


    @Override
    public void hello() {
        System.out.println("name = DefaultInterfaceMethodConflict");
    }


    @Override
    public void name() {
        A.super.name();//继承多接口冲突，必须用这种形式指定某个父接口的方法去实现，否则报错
//        B.super.name();
    }
}
