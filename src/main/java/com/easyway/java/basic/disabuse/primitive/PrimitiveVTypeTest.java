/**
 * Project Name:java-basic
 * File Name:PrimitiveVTypeTest.java
 * Package Name:com.easyway.java.basic.disabuse.primitive
 * Date:2015-1-21下午1:45:08
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.primitive;

/**
 * <pre>
 * 基本类型，或者叫做内置类型，是JAVA中不同于类的特殊类型。它们是我们编程中使用最频繁的类型，因此面试题中也总少不了它们的身影，在这篇文章中我们将从面试中常考的几个方面来回顾一下与基本类型相关的知识。 
 * 
 * 基本类型共有九种，它们分别都有相对应的包装类。关于它们的详细信息请看下表： 
 * 
 *  
 * 
 * 对于基本类型void以及它的包装类java.lang.Void，我们都无法直接进行操作。基本类型可以分为三类，字符类型char，布尔类型boolean以及数值类型byte、short、int、long、float、double。数值类型又可以分为整数类型byte、short、int、long和浮点数类型float、double。JAVA中的数值类型不存在无符号的，它们的取值范围是固定的，不会随着机器硬件环境或者操作系统的改变而改变。对于数值类型的基本类型的取值范围，我们无需强制去记忆，因为它们的值都已经以常量的形式定义在对应的包装类中了。请看下面的例子：
 * 运行结果： 
 * 基本类型：byte 二进制位数：8
 * 包装类：java.lang.Byte
 * 最小值：Byte.MIN_VALUE=-128
 * 最大值：Byte.MAX_VALUE=127
 * 基本类型：short 二进制位数：16
 * 包装类：java.lang.Short
 * 最小值：Short.MIN_VALUE=-32768
 * 最大值：Short.MAX_VALUE=32767
 * 基本类型：int 二进制位数：32
 * 包装类：java.lang.Integer
 * 最小值：Integer.MIN_VALUE=-2147483648
 * 最大值：Integer.MAX_VALUE=2147483647
 * 基本类型：long 二进制位数：64
 * 包装类：java.lang.Long
 * 最小值：Long.MIN_VALUE=-9223372036854775808
 * 最大值：Long.MAX_VALUE=9223372036854775807
 * 基本类型：float 二进制位数：32
 * 包装类：java.lang.Float
 * 最小值：Float.MIN_VALUE=1.4E-45
 * 最大值：Float.MAX_VALUE=3.4028235E38
 * 基本类型：double 二进制位数：64
 * 包装类：java.lang.Double
 * 最小值：Double.MIN_VALUE=4.9E-324
 * 最大值：Double.MAX_VALUE=1.7976931348623157E308
 * 基本类型：char 二进制位数：16
 * 包装类：java.lang.Character
 * 最小值：Character.MIN_VALUE=0
 * 最大值：Character.MAX_VALUE=65535
 * 
 * Float和Double的最小值和最大值都是以科学记数法的形式输出的，结尾的“E+数字”表示E之前的数字要乘以10的多少倍。比如3.14E3就是3.14×1000=3140，3.14E-3就是3.14/1000=0.00314。 
 * 
 * 大家将运行结果与上表信息仔细比较就会发现float、double两种类型的最小值与Float.MIN_VALUE、Double.MIN_VALUE的值并不相同，这是为什么呢？实际上Float.MIN_VALUE和Double.MIN_VALUE分别指的是float和double类型所能表示的最小正数。也就是说存在这样一种情况，0到±Float.MIN_VALUE之间的值float类型无法表示，0到±Double.MIN_VALUE之间的值double类型无法表示。这并没有什么好奇怪的，因为这些范围内的数值超出了它们的精度范围。 
 * 
 * 基本类型存储在栈中，因此它们的存取速度要快于存储在堆中的对应包装类的实例对象。从Java5.0（1.5）开始，JAVA虚拟机（Java Virtual Machine）可以完成基本类型和它们对应包装类之间的自动转换。因此我们在赋值、参数传递以及数学运算的时候像使用基本类型一样使用它们的包装类，但这并不意味着你可以通过基本类型调用它们的包装类才具有的方法。另外，所有基本类型（包括void）的包装类都使用了final修饰，因此我们无法继承它们扩展新的类，也无法重写它们的任何方法。 
 * 
 * 各种数值类型之间的赋值与转换遵循什么规律呢？我们来看下面这个例子：
 * </pre>
 * 
 * ClassName:PrimitiveVTypeTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:45:08 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class PrimitiveVTypeTest {
    public static void main(String[] args) {
        // byte
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println();

        // short
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println();

        // int
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println();

        // long
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println();

        // float
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println();

        // double
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println();

        // char
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
        System.out.println("最小值：Character.MIN_VALUE=" + (int) Character.MIN_VALUE);
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
        System.out.println("最大值：Character.MAX_VALUE=" + (int) Character.MAX_VALUE);
    }
}
