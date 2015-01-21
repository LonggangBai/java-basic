/**
 * Project Name:java-basic
 * File Name:StringStaticTest2.java
 * Package Name:com.easyway.java.basic.disabuse.basic
 * Date:2015-1-21下午12:25:41
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.string;

/**
 * JAVA面试题解惑系列（二）——到底创建了几个String对象？
 * <pre>
 * 它的运行结果是这样： 
 * s不等于t，它们不是同一个对象
 * 
 * 只是做了一点改动，结果就和刚刚的例子恰好相反。我们再来分析一下。A和B虽然被定义为常量（只能被赋值一次），但是它们都没有马上被赋值。在运算出s的值之前，他们何时被赋值，以及被赋予什么样的值，都是个变数。因此A和B在被赋值之前，性质类似于一个变量。那么s就不能在编译期被确定，而只能在运行时被创建了。 
 * 
 * 由于字符串池中对象的共享能够带来效率的提高，因此我们提倡大家用引号包含文本的方式来创建String对象，实际上这也是我们在编程中常采用的。
 * 
 * 
 * 它的运行结果是这样： 
 * s不等于t，它们不是同一个对象
 * 
 * 只是做了一点改动，结果就和刚刚的例子恰好相反。我们再来分析一下。A和B虽然被定义为常量（只能被赋值一次），但是它们都没有马上被赋值。在运算出s的值之前，他们何时被赋值，以及被赋予什么样的值，都是个变数。因此A和B在被赋值之前，性质类似于一个变量。那么s就不能在编译期被确定，而只能在运行时被创建了。 
 * 
 * 由于字符串池中对象的共享能够带来效率的提高，因此我们提倡大家用引号包含文本的方式来创建String对象，实际上这也是我们在编程中常采用的。
 * 
 * 
 * 
 * 接下来我们再来看看intern()方法，它的定义如下： 
 * Java代码 复制代码
 * public native String intern();  
 * 
 * 这是一个本地方法。在调用这个方法时，JAVA虚拟机首先检查字符串池中是否已经存在与该对象值相等对象存在，如果有则返回字符串池中对象的引用；如果没有，则先在字符串池中创建一个相同值的String对象，然后再将它的引用返回。
 * 
 * </pre>
 * 
 * ClassName:StringStaticTest2 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:25:41 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class StringStaticTest2 {
    // 常量A
    public static final String A;

    // 常量B
    public static final String B;

    static {
        A = "ab";
        B = "cd";
    }


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
