package com.easyway.java.basic.clazz;

/**
 * 什么是内部类?内部类的种类、优点。
 * 
 * <pre>
 * 在一个类的内部定义了另一个类，处于内层的类称为内部类，包含内部类的类称为外部类。
 * 很显然，内部类依赖于外部类而存在，程序编译之后得到的内部类文件形式为：外部类$内部类.class，如果要表示内部类，则可采用这种形式：外部类.内部类。
 * 内部类的种类：
 * 成员内部类、
 * 局部内部类、
 * 静态内部类、
 * 匿名内部类（图形是要用到，必须掌握）
 * 
 * 使用内部类的优点：内部类成员可以直接访问外部类的成员(包括私有成员)。
 * 
 * 声明方法的存在而不去实现它的类被叫做抽象类（abstract class），它用于要创建一个体现某些基本行为的类，并为该类声明方法，
 * 但不能在该类中实现该类的情况。不能创建abstract 类的实例。然而可以创建一个变量，其类型是一个抽象类，并让它指向具体子类的
 * 一个实例。不能有抽象构造函数或抽象静态方法。Abstract 类的子类为它们父类中的所有抽象方法提供实现，否则它们也是抽象类为。
 * 取而代之，在子类中实现该方法。知道其行为的其它类可以在类中实现这些方法。
 * 接 口（interface）是抽象类的变体。在接口中，所有方法都是抽象的。多继承性可通过实现这样的接口而获得。接口中的所有方法都是
 * 抽象的，没有一个有 程序体。接口只可以定义static final成员变量。接口的实现与子类相似，除了该实现类不能从接口定义中继承行
 * 为。当类实现特殊接口时，它定义（即将程序体给予）所有这种接口的方法。 然后，它可以在实现了该接口的类的任何对象上调用接口的方
 * 法。由于有抽象类，它允许使用接口名作为引用变量的类型。通常的动态联编将生效。引用可以转换到 接口类型或从接口类型转换，
 * instanceof 运算符可以用来决定某对象的类是否实现了接口。
 * </pre>
 * 
 * @author Administrator
 * 
 */
class A {
    public A() {
        System.out.println("2");
    }
    static int get() {
        try {
            return 1;
        }
        finally {
            return 2;
        }
    }

    static int test() {
        int x = 1;
        try {
            return x;
        }
        finally {
            ++x;
        }
    }

}


class B extends A {
    public B() {
        System.out.println(super.getClass().getName());
        System.out.println(getClass().getSuperclass().getName());
        System.out.println("b");
    }
}


public class Clazz {
    public static void main(String[] args) {
        A a = new B();
        a = new B();
        System.out.println();
        String s = "a" + "b" + "c" + "d";
        System.out.println(s == "abcd");
        System.out.println(A.test());
        System.out.println(A.get());

    }
}