/**
 * Project Name:java-basic
 * File Name:SubClass.java
 * Package Name:com.easyway.java.basic.disabuse.var
 * Date:2015-1-21下午12:43:53
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.var;

/**
 * JAVA面试题解惑系列（三）——变量（属性）的覆盖
 * <pre>
 * 控制台的输出结果是多少呢？20？40？还是60？ 
 * 
 * 变量，或者叫做类的属性，在继承的情况下，如果父类和子类存在同名的变量会出现什么情况呢？这就是这道题要考查的知识点——变量（属性）的覆盖。 
 * 
 * 这个问题虽然简单，但是情况却比较复杂。因为我们不仅要考虑变量、静态变量和常量三种情况，还要考虑private、friendly（即不加访问修饰符）、protected和public四种访问权限下对属性的不同影响。 
 * 
 * 我们先从普通变量说起。依照我们的惯例，先来看一段代码：
 * 
 * </pre>
 * 
 * ClassName:SubClass <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:43:53 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class ParentClass {
    public int i = 10;
}


public class SubClass extends ParentClass {
    public int i = 30;


    public static void main(String[] args) {
        ParentClass parentClass = new SubClass();
        SubClass subClass = new SubClass();
        System.out.println(parentClass.i + subClass.i);
    }
}
