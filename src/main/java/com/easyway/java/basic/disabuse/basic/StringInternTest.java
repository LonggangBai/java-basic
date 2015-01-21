/**
 * Project Name:java-basic
 * File Name:StringInternTest.java
 * Package Name:com.easyway.java.basic.disabuse.basic
 * Date:2015-1-21下午12:26:18
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.basic;

/**
 * JAVA面试题解惑系列（二）——到底创建了几个String对象？
 * <pre>
 * 运行结果： 
 * b没被加入字符串池中，新建了对象
 * 
 * 如果String类的intern()方法在没有找到相同值的对象时，是把当前对象加入字符串池中，然后返回它的引用的话，那么b和a指向的就是同一个对象；否则b指向的对象就是JAVA虚拟机在字符串池中新建的，只是它的值与a相同罢了。上面这段代码的运行结果恰恰印证了这一点。 
 * 
 * 最后我们再来说说String对象在JAVA虚拟机（JVM）中的存储，以及字符串池与堆（heap）和栈（stack）的关系。我们首先回顾一下堆和栈的区别： 
 * 栈（stack）：主要保存基本类型（或者叫内置类型）（char、byte、short、int、long、float、double、boolean）和对象的引用，数据可以共享，速度仅次于寄存器（register），快于堆。
 * 堆（heap）：用于存储对象。
 * 
 * 我们查看String类的源码就会发现，它有一个value属性，保存着String对象的值，类型是char[]，这也正说明了字符串就是字符的序列。 
 * 
 * 当执行String a="abc";时，JAVA虚拟机会在栈中创建三个char型的值'a'、'b'和'c'，然后在堆中创建一个String对象，它的值（value）是刚才在栈中创建的三个char型值组成的数组{'a','b','c'}，最后这个新创建的String对象会被添加到字符串池中。如果我们接着执行String b=new String("abc");代码，由于"abc"已经被创建并保存于字符串池中，因此JAVA虚拟机只会在堆中新创建一个String对象，但是它的值（value）是共享前一行代码执行时在栈中创建的三个char型值值'a'、'b'和'c'。 
 * 
 * 说到这里，我们对于篇首提出的String str=new String("abc")为什么是创建了两个对象这个问题就已经相当明了了。
 * </pre>
 * 
 * ClassName:StringInternTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:26:18 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class StringInternTest {
    public static void main(String[] args) {
        // 使用char数组来初始化a，避免在a被创建之前字符串池中已经存在了值为"abcd"的对象
        String a = new String(new char[] { 'a', 'b', 'c', 'd' });
        String b = a.intern();
        if (b == a) {
            System.out.println("b被加入了字符串池中，没有新建对象");
        }
        else {
            System.out.println("b没被加入字符串池中，新建了对象");
        }
    }
}
