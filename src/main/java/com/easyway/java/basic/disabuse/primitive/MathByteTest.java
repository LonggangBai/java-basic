/**
 * Project Name:java-basic
 * File Name:MathByteTest.java
 * Package Name:com.easyway.java.basic.disabuse.primitive
 * Date:2015-1-21下午1:48:10
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.primitive;

/**
 * 
 * <pre>
 * 运行结果如下： 
 * byte可以用于switch语句
 * Byte类可以用于switch语句
 * char可以用于switch语句
 * Character类可以用于switch语句
 * short可以用于switch语句
 * Short类可以用于switch语句
 * int可以用于switch语句
 * Integer类可以用于switch语句
 * enum可以用于switch语句-A
 * 
 * 结果已经出来了，我们来总结一下： 
 * byte、char、short、int四种基本类型以及它们的包装类（需要Java5.0/1.5以上版本支持）都可以用于switch语句。
 * long、float、double、boolean四种基本类型以及它们的包装类（在Java所有版本中）都不能用于switch语句。
 * enum类型，即枚举类型可以用于switch语句，但是要在Java5.0（1.5）版本以上才支持。
 * 所有类型的对象（包括String类，但在Java5.0/1.5以上版本中，该项要排除byte、char、short、int四种基本类型对应的包装类）都不能用于switch语句。
 * </pre>
 * 
 * ClassName:MathByteTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:48:10 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class MathByteTest {
    // 枚举类型，Java5.0以上版本可用
    static enum enum_e {
        A,
        B
    }


    public static void main(String[] args) {
        // byte
        byte byte_n = 0;
        switch (byte_n) {
        case 0:
            System.out.println("byte可以用于switch语句");
            break;
        }

        // Byte类
        Byte byte_m = 0;
        // 需要Java5.0（1.5）以上版本支持
        switch (byte_m) {
        case 0:
            System.out.println("Byte类可以用于switch语句");
            System.out.println();
            break;
        }

        // char
        char char_n = 0;
        switch (char_n) {
        case 0:
            System.out.println("char可以用于switch语句");
            break;
        }

        // Character类
        Character char_m = 0;
        // 需要Java5.0（1.5）以上版本支持
        switch (char_m) {
        case 0:
            System.out.println("Character类可以用于switch语句");
            System.out.println();
            break;
        }

        // short
        short short_n = 0;
        switch (short_n) {
        case 0:
            System.out.println("short可以用于switch语句");
            break;
        }

        // Short
        Short short_m = 0;
        // 需要Java5.0（1.5）以上版本支持
        switch (short_m) {
        case 0:
            System.out.println("Short类可以用于switch语句");
            System.out.println();
            break;
        }

        // int
        int int_n = 0;
        switch (int_n) {
        case 0:
            System.out.println("int可以用于switch语句");
            break;
        }

        // Integer类
        Integer int_m = 0;
        // 需要Java5.0（1.5）以上版本支持
        switch (int_m) {
        case 0:
            System.out.println("Integer类可以用于switch语句");
            System.out.println();
            break;
        }

        // long
        long long_n = 0;
        // 编译错误，long型不能用于switch语句
        // switch (long_n) {
        // case 0:
        // System.out.println("long可以用于switch语句");
        // break;
        // }

        // Long类
        Long long_m = 0L;
        // 编译错误，Long类型不能用于switch语句
        // switch (long_m) {
        // case 0:
        // System.out.println("Long类可以用于switch语句");
        // System.out.println();
        // break;
        // }

        // float
        float float_n = 0.0F;
        // 编译错误，float型不能用于switch语句
        // switch (float_n) {
        // case 0.0F:
        // System.out.println("float可以用于switch语句");
        // break;
        // }

        // Float类
        Float float_m = 0.0F;
        // 编译错误，Float类型不能用于switch语句
        // switch (float_m) {
        // case 0.0F:
        // System.out.println("Float类可以用于switch语句");
        // System.out.println();
        // break;
        // }

        // double
        double double_n = 0.0;
        // 编译错误，double型不能用于switch语句
        // switch (double_n) {
        // case 0.0:
        // System.out.println("double可以用于switch语句");
        // break;
        // }

        // Double类
        Double double_m = 0.0;
        // 编译错误，Double类型不能用于switch语句
        // switch (double_m) {
        // case 0.0:
        // System.out.println("Double类可以用于switch语句");
        // System.out.println();
        // break;
        // }

        // boolean
        boolean bool_b = true;
        // 编译错误，boolean型不能用于switch语句
        // switch (bool_b) {
        // case true:
        // System.out.println("boolean可以用于switch语句");
        // break;
        // }

        // Boolean类
        Boolean bool_l = true;
        // 编译错误，Boolean类型不能用于switch语句
        // switch (bool_l) {
        // case true:
        // System.out.println("Boolean类可以用于switch语句");
        // System.out.println();
        // break;
        // }

        // String对象
        String string_s = "Z";
        // 编译错误，long型不能用于switch语句
        // switch (string_s) {
        // case "Z":
        // System.out.println("String可以用于switch语句");
        // System.out.println();
        // break;
        // }

        // enum（枚举类型，Java5.0以上版本可用）
        switch (MathByteTest.enum_e.A) {
        case A:
            System.out.println("enum可以用于switch语句-A");
            break;
        case B:
            System.out.println("enum可以用于switch语句-B");
            break;
        }
    }
}
