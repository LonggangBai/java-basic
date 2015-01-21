/**
 * Project Name:java-basic
 * File Name:EncodeTest.java
 * Package Name:com.easyway.java.basic.disabuse.bytes
 * Date:2015-1-21下午1:27:16
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.bytes;

import java.io.UnsupportedEncodingException;


/**
 * <pre>
 * 按字节截取含有中文汉字的字符串 
 * 
 * 要求实现一个按字节截取字符串的方法，比如对于字符串"我ZWR爱JAVA"，截取它的前四位字节应该是"我ZW"，而不是"我ZWR"，同时要保证不会出现截取了半个汉字的情况。 
 * 
 * 英文字母和中文汉字在不同的编码格式下，所占用的字节数也是不同的，我们可以通过下面的例子来看看在一些常见的编码格式下，一个英文字母和一个中文汉字分别占用多少字节。 
 * 运行结果如下： 
 * 英文字母：A
 * 字节数：1;编码：GB2312
 * 字节数：1;编码：GBK
 * 字节数：1;编码：GB18030
 * 字节数：1;编码：ISO-8859-1
 * 字节数：1;编码：UTF-8
 * 字节数：4;编码：UTF-16
 * 字节数：2;编码：UTF-16BE
 * 字节数：2;编码：UTF-16LE
 * 中文汉字：人
 * 字节数：2;编码：GB2312
 * 字节数：2;编码：GBK
 * 字节数：2;编码：GB18030
 * 字节数：1;编码：ISO-8859-1
 * 字节数：3;编码：UTF-8
 * 字节数：4;编码：UTF-16
 * 字节数：2;编码：UTF-16BE
 * 字节数：2;编码：UTF-16LE
 * 
 * 
 * UTF-16BE和UTF-16LE是UNICODE编码家族的两个成员。UNICODE标准定义了UTF-8、UTF-16、UTF-32三种编码格式，共有UTF-8、
 * UTF-16、UTF-16BE、UTF-16LE、UTF-32、UTF-32BE、UTF-32LE七种编码方案。JAVA所采用的编码方案是UTF-16BE。从上例
 * 的运行结果中我们可以看出，GB2312、GBK、GB18030三种编码格式都可以满足题目的要求。下面我们就以GBK编码为例来进行解答。 
 * 
 * 如果我们直接按照字节截取会出现什么情况呢？我们来测试一下：
 * </pre>
 * 
 * ClassName:EncodeTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:27:16 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class EncodeTest {
    /**
     * 打印字符串在指定编码下的字节数和编码名称到控制台
     * 
     * @param s
     *            字符串
     * @param encodingName
     *            编码格式
     */
    public static void printByteLength(String s, String encodingName) {
        System.out.print("字节数：");
        try {
            System.out.print(s.getBytes(encodingName).length);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(";编码：" + encodingName);
    }


    public static void main(String[] args) {
        String en = "A";
        String ch = "人";

        // 计算一个英文字母在各种编码下的字节数
        System.out.println("英文字母：" + en);
        EncodeTest.printByteLength(en, "GB2312");
        EncodeTest.printByteLength(en, "GBK");
        EncodeTest.printByteLength(en, "GB18030");
        EncodeTest.printByteLength(en, "ISO-8859-1");
        EncodeTest.printByteLength(en, "UTF-8");
        EncodeTest.printByteLength(en, "UTF-16");
        EncodeTest.printByteLength(en, "UTF-16BE");
        EncodeTest.printByteLength(en, "UTF-16LE");

        System.out.println();

        // 计算一个中文汉字在各种编码下的字节数
        System.out.println("中文汉字：" + ch);
        EncodeTest.printByteLength(ch, "GB2312");
        EncodeTest.printByteLength(ch, "GBK");
        EncodeTest.printByteLength(ch, "GB18030");
        EncodeTest.printByteLength(ch, "ISO-8859-1");
        EncodeTest.printByteLength(ch, "UTF-8");
        EncodeTest.printByteLength(ch, "UTF-16");
        EncodeTest.printByteLength(ch, "UTF-16BE");
        EncodeTest.printByteLength(ch, "UTF-16LE");
    }
}
