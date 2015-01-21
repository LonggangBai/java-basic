/**
 * Project Name:java-basic
 * File Name:MathTest.java
 * Package Name:com.easyway.java.basic.disabuse.primitive
 * Date:2015-1-21下午1:47:40
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.primitive;

/**
 * 
 * <pre>
 * 运行结果： 
 * 小数点后第一位=5
 * 正数：Math.round(11.5)=12
 * 负数：Math.round(-11.5)=-11
 * 小数点后第一位<5
 * 正数：Math.round(11.46)=11
 * 负数：Math.round(-11.46)=-11
 * 小数点后第一位>5
 * 正数：Math.round(11.68)=12
 * 负数：Math.round(-11.68)=-12
 * 
 * 根据上面例子的运行结果，我们还可以按照如下方式总结，或许更加容易记忆： 
 * 参数的小数点后第一位<5，运算结果为参数整数部分。
 * 参数的小数点后第一位>5，运算结果为参数整数部分绝对值+1，符号（即正负）不变。
 * 参数的小数点后第一位=5，正数运算结果为整数部分+1，负数运算结果为整数部分。
 * 
 * 但是上面的结论仍然不是很好记忆。我们来看看round()方法的内部实现会给我们带来什么启发？我们来看这两个方法内部的代码： 
 * Java代码 复制代码
 * public static int round(float a) {   
 *     return (int)floor(a + 0.5f);   
 * }   
 *   
 * public static long round(double a) {   
 *     return (long)floor(a + 0.5d);   
 * }  
 * 
 * 看来它们都是将参数值+0.5后交与floor()进行运算，然后取返回值。那么floor()方法的作用又是什么呢？它是取一个小于等于参数值的最大整数。比如经过floor()方法运算后，如果参数是10.2则返回10，13返回13，-20.82返回-21，-16返回-16等等。既然是这样，我们就可以用一句话来概括round()方法的运算效果了： 
 * Math类的round()方法的运算结果是一个<=(参数值+0.5)的最大整数。
 * 
 * switch语句 
 * 
 * 哪些类型可以用于switch语句的判断呢？我们做个测试就知道了： 
 * Java代码 复制代码
 * </pre>
 * 
 * ClassName:MathTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:47:40 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class MathTest {
    public static void main(String[] args) {
        System.out.println("小数点后第一位=5");
        System.out.println("正数：Math.round(11.5)=" + Math.round(11.5));
        System.out.println("负数：Math.round(-11.5)=" + Math.round(-11.5));
        System.out.println();

        System.out.println("小数点后第一位<5");
        System.out.println("正数：Math.round(11.46)=" + Math.round(11.46));
        System.out.println("负数：Math.round(-11.46)=" + Math.round(-11.46));
        System.out.println();

        System.out.println("小数点后第一位>5");
        System.out.println("正数：Math.round(11.68)=" + Math.round(11.68));
        System.out.println("负数：Math.round(-11.68)=" + Math.round(-11.68));
    }
}
