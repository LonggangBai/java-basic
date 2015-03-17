/**
 * Project Name:java-basic
 * File Name:OverLoad.java
 * Package Name:com.easyway.java.basic.jdk.jdk15
 * Date:2015-3-16上午10:13:06
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.jdk.jdk15;

/**
 * ClassName:OverLoad <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-16 上午10:13:06 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class Base {
    String var="BaseVar";
    static String staticVar="StaticBaseVar";
    public int max(int a, int b) {
        return a > b ? a : b;
    }


    protected static int staticMax(int a, int b) {
        return a > b ? a : b;
    }


    void method() {
        System.out.println("method of Base ");
    }


    static void staticMethod() {
        System.out.println(" static method of Base ");
    }
}


class Sub extends Base {
    String var="BaseVar";
    static String staticVar="StaticBaseVar";
    /**
     * 覆盖方法必须满足的多种条件约束 1).子类方法的名称，参数签名和返回类型必须与父类方法的名称，参数签名和返回类型一致。
     * 2).子类方法不能缩小父类方法的访问权限。如果子类缩小了父类方法的访问权限，这是无效的方法覆盖，将导致编译错误。
     * 3).子类方法不能抛出比父类方法更多的异常。子类方法抛出的异常必须和父类方法抛出的异常相同，或者子类方法抛出异常类是父类方法抛出异常的子类。
     * 4).方法覆盖只存在于子类和父类之间，在同一个类中方法只能被重载，不能被覆盖。 5).父类的静态方法不能被子类覆盖为非静态方法。
     * 6).子类可以定义与父类的静态方法同名的静态方法。以便在子类中隐藏父类的静态方法。在编译时，子类定义的静态方法也必须满足与方法覆盖类似
     * 的约束；方法的参数签名一致，返回类型一致，不能缩小父类方法的访问权限，不能抛出更多的异常。
     * 子类隐藏父类的静态方法和子类覆盖父类的实例方法，这两者的区别在于： 运行时，Java虚拟机把静态方法和所属的类绑定，而把实例方法和所属的实例绑定。
     * 7).父类的非静态方法不能被子类覆盖为静态方法。
     * 8).父类的私有方法不能被子类覆盖。
     * 9).父类的抽象方法可以被子类通过两种途径覆盖：
     *    一是子类实现父类的抽象方法。
     *    二是子类重新生命父类的抽象方法。
     * 10).父类的非抽象方法可以被覆盖为抽象方法。
     * @see com.easyway.java.basic.jdk.jdk15.Base#max(int, int)
     */
    public int max(int a, int b) {
        return Math.abs(a) > Math.abs(b) ? a : b;
    }


    void method() {
        System.out.println("method of Sub ");
    }


    static void staticMethod() {
        System.out.println(" static method of Sub ");
    }


    public static int staticMax(int a, int b) {
        return Math.abs(a) > Math.abs(b) ? a : b;
    }
}

/**
 *          引用变量sub1和sub2都引用Sub类的实例，Java虚拟机在执行sub1.method()和sub2.method()时，
 * 都调用Sub实例的method方法，此时父类Base实例方法method被子类覆盖。
 *          引用变量sub1被声明为Base类型，Java虚拟机在执行sub1.staticMethod()时，调用Base类的staticMethod方法，
 * 可见父类Base的静态方法staticMethod不能被子类覆盖。
 *         引用变量sub2被声明为Sub类型，Java虚拟机在执行sub2.staticMethod()时候，调用Sub类的.staticMethod()方法，
 * Base 类的.staticMethod()方法被Sub类的.staticMethod()方法隐藏。
 *
 * @author Administrator
 * @version 
 * @since JDK 1.6
 */
public class Override {
    public static void main(String[] args) {
        Base sub1 = new Sub();
        System.out.println("sub1.var="+sub1.var);
        System.out.println("sub1.staticVar="+sub1.staticVar);
        System.out.println(sub1.max(-2, 1));
        System.out.println(sub1.staticMax(-2, 1));
        sub1.method();
        sub1.staticMethod();

        Sub sub2 = new Sub();
        System.out.println(sub2.max(-2, 1));
        System.out.println(sub2.staticMax(-2, 1));
        sub2.method();
        sub2.staticMethod();
    }

}
