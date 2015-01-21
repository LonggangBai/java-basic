/**
 * Project Name:java-basic
 * File Name:TestOrder.java
 * Package Name:com.easyway.java.basic.disabuse.clazz
 * Date:2015-1-21下午12:02:45
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.clazz;

/**
 * JAVA面试题解惑系列（一）——类的初始化顺序
 * <pre>
 * 那么对于静态变量和静态初始化块之间、变量和初始化块之间的先后顺序又是怎样呢？是否静态变量总是先于静态初始化块，
 * 变量总是先于初始化块就被初始化了呢？实际上这取决于它们在类中出现的先后顺序。
 * 我们以静态变量和静态初始化块为例来进行说明。
 * 
 * 运行上面的代码，会得到如下的结果： 
 * Test--A
 * 静态初始化块
 * Test--B
 * 
 * 大家可以随意改变变量a、变量b以及静态初始化块的前后位置，就会发现输出结果随着它们在类中出现的前后顺序而改变，这就说明静态变量和
 * 静态初始化块是依照他们在类中的定义顺序进行初始化的。同样，变量和初始化块也遵循这个规律。 
 * 了解了继承情况下类的初始化顺序之后，如何判断最终输出结果就迎刃而解了。

 * </pre>
 * 
 * ClassName:TestOrder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:02:45 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class TestOrder {

    // 静态变量
    public static TestA a = new TestA();

    // 静态初始化块
    static {
        System.out.println("静态初始化块");
    }

    // 静态变量
    public static TestB b = new TestB();


    public static void main(String[] args) {
        new TestOrder();
    }
}


class TestA {
    public TestA() {
        System.out.println("Test--A");
    }
}

class TestB {
    public TestB() {
        System.out.println("Test--B");
    }
}