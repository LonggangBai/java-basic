/**
 * Project Name:java-basic
 * File Name:ChineseCutString.java
 * Package Name:com.easyway.java.basic.disabuse.bytes
 * Date:2015-1-21下午1:29:24
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.bytes;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 * 在截取前六个字节时，第二个汉字“爱”被截取了一半，导致它无法正常显示了，这样显然是有问题的。 
 * 
 * 我们不能直接使用String类的substring(int beginIndex, int endIndex)方法，因为它是
 * 按字符截取的。'我'和'Z'都被作为一个字符来看待，length都是1。实际上我们只要能区分开中文汉字
 * 和英文字母，这个问题就迎刃而解了，而它们的区别就是，中文汉字是两个字节，英文字母是一个字节。 
 * 
 * 
 * 运行结果： 
 * 原始字符串：我ZWR爱JAVA
 * 截取前1位：我
 * 截取前2位：我
 * 截取前4位：我ZW
 * 截取前6位：我ZWR爱
 * </pre>
 * 
 * ClassName:ChineseCutString <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:29:24 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ChineseCutString {

    /**
     * 判断是否是一个中文汉字
     * 
     * @param c
     *            字符
     * @return true表示是中文汉字，false表示是英文字母
     * @throws UnsupportedEncodingException
     *             使用了JAVA不支持的编码格式
     */
    public static boolean isChineseChar(char c) throws UnsupportedEncodingException {
        // 如果字节数大于1，是汉字
        // 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
        return String.valueOf(c).getBytes("GBK").length > 1;
    }


    /**
     * 按字节截取字符串
     * 
     * @param orignal
     *            原始字符串
     * @param count
     *            截取位数
     * @return 截取后的字符串
     * @throws UnsupportedEncodingException
     *             使用了JAVA不支持的编码格式
     */
    public static String substring(String orignal, int count) throws UnsupportedEncodingException {
        // 原始字符不为null，也不是空字符串
        if (orignal != null && !"".equals(orignal)) {
            // 将原始字符串转换为GBK编码格式
            orignal = new String(orignal.getBytes(), "GBK");
            // 要截取的字节数大于0，且小于原始字符串的字节数
            if (count > 0 && count < orignal.getBytes("GBK").length) {
                StringBuffer buff = new StringBuffer();
                char c;
                for (int i = 0; i < count; i++) {
                    // charAt(int index)也是按照字符来分解字符串的
                    c = orignal.charAt(i);
                    buff.append(c);
                    if (ChineseCutString.isChineseChar(c)) {
                        // 遇到中文汉字，截取字节总数减1
                        --count;
                    }
                }
                return buff.toString();
            }
        }
        return orignal;
    }


    public static void main(String[] args) {
        // 原始字符串
        String s = "我ZWR爱JAVA";
        System.out.println("原始字符串：" + s);
        try {
            System.out.println("截取前1位：" + ChineseCutString.substring(s, 1));
            System.out.println("截取前2位：" + ChineseCutString.substring(s, 2));
            System.out.println("截取前4位：" + ChineseCutString.substring(s, 4));
            System.out.println("截取前6位：" + ChineseCutString.substring(s, 6));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
