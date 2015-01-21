/**
 * Project Name:java-basic
 * File Name:Test.java
 * Package Name:com.easyway.java.basic.disabuse.operate
 * Date:2015-1-21下午2:14:32
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.operate;

/**
 * 
 * <pre>
 * 
 * 作者：臧圩人（zangweiren） 
 * 网址：[url]http://zangweiren.java eye.com[/url]
 * >>>转载请注明出处！<<<
 * 有些运算符在JAVA语言中存在着，但是在实际开发中我们或许很少用到它们，在面试题中却时常出现它们的身影，对于这些运算符的含义和用法，你是否还记得呢？
 * [b]自增（++）和自减（--）运算符 [/b]
 * 我们先来回答几个问题吧：
 * Java代码 
 * int i = 0;   
 * int j = i++;   
 * int k = --i; 
 * 这段代码运行后，i等于多少？j等于多少？k等于多少？太简单了？好，继续：
 * Java代码 
 * int i = 0;   
 * int j = i++ + ++i;   
 * int k = --i + i--; 
 * 代码执行后i、j、k分别等于多少呢？还是很简单？好，再继续：
 * Java代码 
 * int i=0;   
 * System.out.println(i++); 
 * 这段代码运行后输出结果是什么？0？1？
 * Java代码 
 * float f=0.1F;   
 * f++;   
 * double d=0.1D;   
 * d++;   
 * char c='a';   
 * c++; 
 * 上面这段代码可以编译通过吗？为什么？如果你能顺利回答到这里，说明你对自增和自减运算符的掌握已经很好了。
 * 为了分析出上面提出的几个问题，我们首先来回顾一下相关知识： 
 * 1、自增（++）：将变量的值加1，分前缀式（如++i）和后缀式（如i++）。前缀式是先加1再使用；后缀式是先使用再加1。 
 * 2、自减（--）：将变量的值减1，分前缀式（如--i）和后缀式（如i--）。前缀式是先减1再使用；后缀式是先使用再减1。
 * 在第一个例子中，int j=i++;是后缀式，因此i的值先被赋予j，然后再自增1，所以这行代码运行后，i=1、j=0；而int k=--i;是前缀式，因此i先自减1，然后再将它的值赋予k，因此这行代码运行后，i=0、k=0。
 * 在第二个例子中，对于int j=i++ + ++i;，首先运行i++，i的值0被用于加运算（+），之后i自增值变为1，然后运行++i，i先自增变为2，之后被用于加运算，最后将i两次的值相加的结果1+2=3赋给j，因此这行代码运行完毕后i=2、j=3；对于int k=--i + i--;用一样的思路分析，具体过程在此不再赘述，结果应该是i=0、k=2。
 * 自增与自减运算符还遵循以下规律： 
 * 1、可以用于整数类型byte、short、int、long，浮点类型float、double，以及字符串类型char。 
 * 2、在Java5.0及以上版本中，它们可以用于基本类型对应的包装器类Byte、Short、Integer、Long、Float、Double、Character。 
 * 3、它们的运算结果的类型与被运算的变量的类型相同。
 * 下面的这个例子验证以上列出的规律，它可以编译通过并执行。
 * </pre>
 * 
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:14:32 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class Test {
    public static void main(String[] args) {
        // 整型
        byte b = 0;
        b++;
        // 整型
        long l = 0;
        l++;
        // 浮点型
        double d = 0.0;
        d++;
        // 字符串
        char c = 'a';
        c++;
        // 基本类型包装器类
        Integer i = new Integer(0);
        i++;
    }
}
