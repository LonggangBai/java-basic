/**
 * Project Name:java-basic
 * File Name:FinallyTest.java
 * Package Name:com.easyway.java.basic.disabuse.finalize
 * Date:2015-1-21下午1:11:17
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.finalize;

/**
 * <pre>
 * finally语句 
 * 
 * 接下来我们一起回顾一下finally的用法。这个就比较简单了，它只能用在try/catch语句中，并且附带着一个语句块，表示这段语句最终总是被执行。请看下面的代码： 
 * 
 * 运行结果说明了finally的作用： 
 * 程序抛出了异常
 * 执行了finally语句块
 * 
 * 请大家注意，捕获程序抛出的异常之后，既不加处理，也不继续向上抛出异常，并不是良好的编程习惯，它掩盖了程序执行中发生的错误，这里只是方便演示，请不要学习。 
 * 
 * 那么，有没有一种情况使finally语句块得不到执行呢？大家可能想到了return、continue、break这三个可以打乱代码顺序执行语句的规律。那我们就来试试看，这三个语句是否能影响finally语句块的执行：
 * </pre>
 * 
 * ClassName:FinallyTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:11:17 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public final class FinallyTest {
    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        }
        catch (NullPointerException e) {
            System.out.println("程序抛出了异常");
        }
        finally {
            System.out.println("执行了finally语句块");
        }
    }
}
