/**
 * Project Name:java-basic
 * File Name:InitialOrderTest.java
 * Package Name:com.easyway.java.basic.disabuse.clazz
 * Date:2015-1-21上午11:02:50
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.clazz;

/**
 * <pre>
 * 大家在去参加面试的时候，经常会遇到这样的考题：给你两个类的代码，它们之间是继承的关系，每个类里只有构造器方法和一些变量，
 * 构造器里可能还有一段代码对变量值进行了某种运算
 * ，另外还有一些将变量值输出到控制台的代码，然后让我们判断输出的结果。这实际上是在考查我们对于继承情况下类的初始化顺序的了解。
 * 我们大家都知道，对于静态变量、静态初始化块、变量、初始化块、构造器，它们的初始化顺序依次是（静态变量、静态初始化块）>（变量、初始化块）>构造器。
 * 
 * 我们也可以通过下面的测试代码来验证这一点：
 * 运行以上代码，我们会得到如下的输出结果： 
 *      1.静态变量 
 *      2.静态初始化块 
 *      3.变量 
 *      4.初始化块 
 *      5.构造器 
 *  
 *  </pre>
 *  ClassName:InitialOrderTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 上午11:02:50 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class InitialOrderTest {

    // 静态变量
    public static String staticField = "静态变量";
    // 变量
    public String field = "变量";

    // 静态初始化块
    static {
        System.out.println(staticField);
        System.out.println("静态初始化块");
    }

    // 初始化块
    {
        System.out.println(field);
        System.out.println("初始化块");
    }


    // 构造器
    public InitialOrderTest() {
        System.out.println("构造器");
    }


    public static void main(String[] args) {
        new InitialOrderTest();
    }
}
