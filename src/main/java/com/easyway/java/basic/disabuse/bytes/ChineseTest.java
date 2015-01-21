/**
 * Project Name:java-basic
 * File Name:ChineseTest.java
 * Package Name:com.easyway.java.basic.disabuse.bytes
 * Date:2015-1-21下午1:20:14
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.bytes;

/**
 * <pre>
 * 上一次我们已经一起回顾了面试题中常考的到底创建了几个String对象的相关知识，这一次我们以几个常见面试题为引子，来回顾一下String对象相关的其它一些方面。 
 * 
 * String的length()方法和数组的length属性 
 * 
 * String类有length()方法吗？数组有length()方法吗？ 
 * 
 * String类当然有length()方法了，看看String类的源码就知道了，这是这个方法的定义： 
 * Java代码 复制代码
 * public int length() {   
 *     return count;   
 * }  
 * 
 * String的长度实际上就是它的属性--char型数组value的长度。数组是没有length()方法的，大家知道，在JAVA中，数组也被作为对象来处理，它的方法都继承自Object类。数组有一个属性length，这也是它唯一的属性，对于所有类型的数组都是这样。 
 * 
 * 中文汉字在char中的保存 
 * 
 * 一个中文汉字能保存在一个char类型里吗？ 
 * 编译没有报错，运行结果： 
 * 中文测试成功
 * 
 * 答案就不用说了。为什么一个中文汉字可以保存在一个char变量里呢？因为在JAVA中，一个char是2个字节（byte），而一个中文汉字是一个字符，也是2个字节。而英文字母都是一个字节的，因此它也能保存到一个byte里，一个中文汉字却不能。请看：
 * 
 * </pre>
 * 
 * ClassName:ChineseTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:20:14 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ChineseTest {
    public static void main(String[] args) {
    }


    /**
     * 
     *  运行结果： 156035
     * 
     * 这显然不是我们想要的结果。只所以会这样是因为我们误用了“+”运算符，当它被用于字符串和字符串之间，或者字符串和其他类型变量之间时，
     * 它产生的效果是字符串的拼接
     * ；但当它被用于字符和字符之间时，效果等同于用于数字和数字之间，是一种算术运算。因此我们得到的“156035”是'中'、'文'
     * 、'测'、'试'、'成'、'功'这六个汉字分别对应的数值算术相加后的结果。
     * 
     * chineseCon:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author Administrator
     * @since JDK 1.6
     */
    private static void chineseCon() {
        // 将一个中文汉字赋值给一个char变量
        char a = '中';
        char b = '文';
        char c = '测';
        char d = '试';
        char e = '成';
        char f = '功';
        System.out.print(a + b + c + d + e + f);
    }


    /**
     * 运行结果： byte a = 97
     * 
     * 正如大家所看到的那样，我们实际上是把字符'a'对应的ASCII码值赋值给了byte型变量a。
     * 正如大家所看到的那样，我们实际上是把字符'a'对应的ASCII码值赋值给了byte型变量a。
     * byteValue:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author Administrator
     * @since JDK 1.6
     */
    private static void byteValue() {
        // 将一个英文字母赋值给一个byte变量
        byte a = 'a';
        // 将一个中文汉字赋值给一个byte变量时，编译会报错
        // byte b = '中';

        System.out.println("byte a = " + a);
        // System.out.println("byte b = "+b);
    }


    /**
     * 编译没有报错，运行结果： 中文测试成功 charbytesLength:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author Administrator
     * @since JDK 1.6
     */
    private static void charbytesLength() {
        // 将一个中文汉字赋值给一个char变量
        char a = '中';
        char b = '文';
        char c = '测';
        char d = '试';
        char e = '成';
        char f = '功';
        System.out.print(a);
        System.out.print(b);
        System.out.print(c);
        System.out.print(d);
        System.out.print(e);
        System.out.print(f);
    }
}
