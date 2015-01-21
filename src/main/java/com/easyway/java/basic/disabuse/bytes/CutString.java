/**
 * Project Name:java-basic
 * File Name:CutString.java
 * Package Name:com.easyway.java.basic.disabuse.bytes
 * Date:2015-1-21下午1:28:27
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.bytes;

import java.io.UnsupportedEncodingException;


/**
 * <pre>
 * 输出结果： 
 * 我ZWR?
 * 
 * 在截取前六个字节时，第二个汉字“爱”被截取了一半，导致它无法正常显示了，这样显然是有问题的。
 * </pre>
 * 
 * ClassName:CutString <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:28:27 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class CutString {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "我ZWR爱JAVA";
        // 获取GBK编码下的字节数据
        byte[] data = s.getBytes("GBK");
        byte[] tmp = new byte[6];
        // 将data数组的前六个字节拷贝到tmp数组中
        System.arraycopy(data, 0, tmp, 0, 6);
        // 将截取到的前六个字节以字符串形式输出到控制台
        s = new String(tmp);
        System.out.println(s);
    }
}
