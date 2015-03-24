/**
 * Project Name:java-basic
 * File Name:StaticInnerClazz.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-19下午3:48:14
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.clazz.innerclazz;

/**
 * ClassName:StaticInnerClazz <br/>
 * Function: 静态内部类<br/>
 * Reason: 特点： 1.静态内部类的实例不会自动持有外部类的特定实例的引用。在创建内部类实例时候，不必创建外部类的实例。
 * 2.静态内部类可以直接访问外部类的静态成员，如果访问外部类的实例成员，就必须通过外部类的实例去访问。 3.在静态内部类中可以定义静态成员和实例成员。
 * 4.客户类可以通过完整的类名直接访问静态内部类的静态成员。 <br/>
 * Date: 2015-3-19 下午3:48:14 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class StaticInnerA {
    private int a1;

    private static int a2;

    public static class B {
        int b2 = a2;
        int b3 = new StaticInnerA().a1;
        int v1;
        static int v2;

        public static class C {
            static int v3;
            int v4;
        }
    }
}


public class StaticInnerClazz {

    public static void main(String[] args) {
        StaticInnerA.B b = new StaticInnerA.B();
        b.b2 = 1;
        b.v1 = 1;
        b.v2 = 1;
        StaticInnerA.B.v2 = 1;
        StaticInnerA.B.C.v3 = 1;

        StaticInnerA.B.C c = new StaticInnerA.B.C();
    }
}
