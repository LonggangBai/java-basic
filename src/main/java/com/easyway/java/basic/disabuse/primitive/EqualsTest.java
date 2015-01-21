/**
 * Project Name:java-basic
 * File Name:EqualsTest.java
 * Package Name:com.easyway.java.basic.disabuse.primitive
 * Date:2015-1-21下午1:47:10
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.primitive;

/**
 * 
 * <pre>
 * 运行结果： 
 * int_int == int_Integer结果是：true
 * Integer_Integer == Integer_int结果是：false
 * int_int == Integer_Integer结果是：true
 * Integer_Integer == int_int结果是：true
 * boolean_boolean == boolean_Boolean结果是：true
 * Boolean_Boolean == Boolean_boolean结果是：false
 * boolean_boolean == Boolean_Boolean结果是：true
 * Boolean_Boolean == boolean_boolean结果是：true
 * 
 * 为了便于查看，上例中变量命名没有采用规范的方式，而是采用了“变量类型”+“_”+“初始化值类型”的方式
 * 
 * Math.round()方法 
 * 
 * java.lang.Math类里有两个round()方法，它们的定义如下： 
 * Java代码 复制代码
 * public static int round(float a) {   
 *     //other code   
 * }   
 *   
 * public static long round(double a) {   
 *     //other code   
 * }  
 * 
 * 它们的返回值都是整数，且都采用四舍五入法。运算规则如下： 
 * 如果参数为正数，且小数点后第一位>=5，运算结果为参数的整数部分+1。
 * 如果参数为负数，且小数点后第一位>5，运算结果为参数的整数部分-1。
 * 如果参数为正数，且小数点后第一位<5；或者参数为负数，且小数点后第一位<=5，运算结果为参数的整数部分。
 * 
 * </pre>
 * 
 * ClassName:EqualsTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:47:10 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class EqualsTest {
    public static void main(String[] args) {
        // int类型用int类型初始化
        int int_int = 0;
        // int类型用Integer类型初始化
        int int_Integer = new Integer(0);
        // Integer类型用Integer类型初始化
        Integer Integer_Integer = new Integer(0);
        // Integer类型用int类型初始化
        Integer Integer_int = 0;

        System.out.println("int_int == int_Integer结果是：" + (int_int == int_Integer));
        System.out.println("Integer_Integer == Integer_int结果是：" + (Integer_Integer == Integer_int));
        System.out.println();
        System.out.println("int_int == Integer_Integer结果是：" + (int_int == Integer_Integer));
        System.out.println("Integer_Integer == int_int结果是：" + (Integer_Integer == int_int));
        System.out.println();

        // boolean类型用boolean类型初始化
        boolean boolean_boolean = true;
        // boolean类型用Boolean类型初始化
        boolean boolean_Boolean = new Boolean(true);
        // Boolean类型用Boolean类型初始化
        Boolean Boolean_Boolean = new Boolean(true);
        // Boolean类型用boolean类型初始化
        Boolean Boolean_boolean = true;

        System.out.println("boolean_boolean == boolean_Boolean结果是：" + (boolean_boolean == boolean_Boolean));
        System.out.println("Boolean_Boolean == Boolean_boolean结果是：" + (Boolean_Boolean == Boolean_boolean));
        System.out.println();
        System.out.println("boolean_boolean == Boolean_Boolean结果是：" + (boolean_boolean == Boolean_Boolean));
        System.out.println("Boolean_Boolean == boolean_boolean结果是：" + (Boolean_Boolean == boolean_boolean));
    }
}
