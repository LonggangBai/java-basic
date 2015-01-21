/**
 * Project Name:java-basic
 * File Name:OperatorTest.java
 * Package Name:com.easyway.java.basic.disabuse.operate
 * Date:2015-1-21下午2:13:42
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.operate;

/**
 * <pre>
 * 
 *  逻辑运算符的运算遵循短路形式，而按位运算符则不是。所谓短路就是一旦能够确定运算的结果，就不再进行余下的运算。下面的例子更加直观地展现了短路与非短路的区别：
 *  运行结果： 
 * 执行-返回值:false;方法:leftCondition() 
 * 
 * 执行-返回值:0;方法:leftNumber() 
 * 执行-返回值:1;方法:rightNumber()
 * 运行结果已经很明显地显示了短路和非短路的区别，我们一起来分析一下产生这个运行结果的原因。当运行“ot.leftCondition() && ot.rightCondition()”时，由于方法leftCondition()返回了false，而对于“&&”运算来说，必须要运算符两边的值都为true时，运算结果才为true，因此这时候就可以确定，不论rightCondition()的返回值是什么，“ot.leftCondition() && ot.rightCondition()”的运算值已经可以确定是false，由于逻辑运算符是短路的形式，因此在这种情况下，rightCondition()方法就不再被运行了。
 * 而对于“ot.leftNumber() & ot.rightNumber()”，由于“leftNumber()”的返回值是0，对于按位运算符“&”来说，必须要运算符两边的值都是1时，运算结果才是1，因此这时不管“rightNumber()”方法的返回值是多少，“ot.leftNumber() & ot.rightNumber()”的运算结果已经可以确定是0，但是由于按位运算符是非短路的，所以rightNumber()方法还是被执行了。这就是短路与非短路的区别。
 * [b]移位运算符 [/b]
 * 移位运算符和按位运算符一样，同属于位运算符，因此移位运算符的位指的也是二进制位。它包括以下几种： 
 * 1、左移位（>）：将操作符左侧的操作数向右移动操作符右侧指定的位数。移动的规则是，如果被操作数的符号为正，则在二进制的高位补0；如果被操作数的符号为负，则在二进制的高位补1。 
 * 3、无符号右移位（>>>）：将操作符左侧的操作数向右移动操作符右侧指定的位数。移动的规则是，无论被操作数的符号是正是负，都在二进制位的高位补0。
 * 注意，移位运算符不存在“无符号左移位（<<<）”运算符一说。
 * 与按位运算符不同的是，移位运算符不存在短路不短路的问题。
 * 写到这里就不得不提及一个在面试题中经常被考到的题目： 
 * 引用
 * [quote]请用最有效率的方法计算出2乘以8等于几？ [/quote]
 * 这里所谓的最有效率，实际上就是通过最少、最简单的运算得出想要的结果，而移位是计算机中相当基础的运算了，用它来实现准没错了。左移位“
 * </pre>
 * 
 * ClassName:OperatorTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:13:42 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class OperatorTest {
    public boolean leftCondition() {
        System.out.println("执行-返回值:false;方法:leftCondition()");
        return false;
    }


    public boolean rightCondition() {
        System.out.println("执行-返回值:true;方法:rightCondition()");
        return true;
    }


    public int leftNumber() {
        System.out.println("执行-返回值:0;方法:leftNumber()");
        return 0;
    }


    public int rightNumber() {
        System.out.println("执行-返回值:1;方法:rightNumber()");
        return 1;
    }


    public static void main(String[] args) {
        OperatorTest ot = new OperatorTest();

        if (ot.leftCondition() && ot.rightCondition()) {
            // do something
        }
        System.out.println();

        int i = ot.leftNumber() & ot.rightNumber();
    }
}
