/**
 * Project Name:java-basic
 * File Name:StringStaticTest.java
 * Package Name:com.easyway.java.basic.disabuse.basic
 * Date:2015-1-21下午12:25:06
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.basic;

/**
 * <pre>
 * 这段代码的运行结果如下： 
 * s等于t，它们是同一个对象
 * 
 * 这又是为什么呢？原因是这样的，对于常量来讲，它的值是固定的，因此在编译期就能被确定了，而变量的值只有到运行时才能被确定，因为这个变量可以被不同的方法调用，从而可能引起值的改变。在上面的例子中，A和B都是常量，值是固定的，因此s的值也是固定的，它在类被编译时就已经确定了。也就是说： 
 * Java代码 复制代码
 * String s=A+B;  
 * 
 * 等同于： 
 * Java代码 复制代码
 * String s="ab"+"cd";
 * </pre>
 * 
 * ClassName:StringStaticTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:25:06 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class StringStaticTest {
    // 常量A
    public static final String A = "ab";

    // 常量B
    public static final String B = "cd";


    public static void main(String[] args) {
        // 将两个常量用+连接对s进行初始化
        String s = A + B;
        String t = "abcd";
        if (s == t) {
            System.out.println("s等于t，它们是同一个对象");
        }
        else {
            System.out.println("s不等于t，它们不是同一个对象");
        }
    }
}
