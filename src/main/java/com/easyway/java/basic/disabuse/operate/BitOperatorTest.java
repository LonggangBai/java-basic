/**
 * Project Name:java-basic
 * File Name:BitOperatorTest.java
 * Package Name:com.easyway.java.basic.disabuse.operate
 * Date:2015-1-21下午2:13:55
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.operate;

/**
 * 
 * <pre>
 * [b]按位运算符 [/b]
 * 你还能说出来按位运算符一共有哪几种吗？对比下面的列表看看，有没有从你的记忆中消失了的： 
 * 1、按位与运算（&）：二元运算符。当被运算的两个值都为1时，运算结果为1；否则为0。 
 * 2、按位或运算（|）：二元运算符。当被运算的两个值都为0时，运算结果为0；否则为1。 
 * 3、按位异或运算（^）：二元运算符。当被运算的两个值中任意一个为1，另一个为0时，运算结果为1；否则为0。 
 * 4、按位非运算（~）：一元运算符。当被运算的值为1时，运算结果为0；当被运算的值为0时，运算结果为1。
 * 这里不像我们看到的逻辑运算符（与运算&&、或运算||、非运算！）操作的是布尔值true或false，或者是一个能产生布尔值的表达式；“按位运算符”所指的“位”就是二进制位，因此它操作的是二进制的0和1。在解释按位运算符的执行原理时，我们顺便说说它们和逻辑运算符的区别。
 * 1、逻辑运算符只能操作布尔值或者一个能产生布尔值的表达式；按位运算符能操作整型值，包括byte、short、int、long，但是不能操作浮点型值（即float和double），它还可以操作字符型（char）值。按位运算符不能够操作对象，但是在Java5.0及以上版本中，byte、short、int、long、char所对应的包装器类是个例外，因为JAVA虚拟机会自动将它们转换为对应的基本类型的数据。
 * 下面的例子验证了这条规律：
 * 运行结果： 
*(byte)10 & (byte)20 = 0 
*(char)a | (char)A = 97 
*(Long)555 ^ (Long)666 = 177
 * </pre>
 * 
 * ClassName:BitOperatorTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:13:55 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class BitOperatorTest {
    public static void main(String[] args) {
        // 整型
        byte b1 = 10, b2 = 20;
        System.out.println("(byte)10 & (byte)20 = " + (b1 & b2));
        // 字符串型
        char c1 = 'a', c2 = 'A';
        System.out.println("(char)a | (char)A = " + (c1 | c2));
        // 基本类型的包装器类
        Long l1 = new Long(555), l2 = new Long(666);
        System.out.println("(Long)555 ^ (Long)666 = " + (l1 ^ l2));
        // 浮点型
        float f1 = 0.8F, f2 = 0.5F;
        // 编译报错，按位运算符不能用于浮点数类型
        // System.out.println("(float)0.8 & (float)0.5 = " + (f1 & f2));
    }
}
