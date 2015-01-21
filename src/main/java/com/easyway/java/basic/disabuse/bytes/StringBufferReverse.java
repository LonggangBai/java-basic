/**
 * Project Name:java-basic
 * File Name:StringBufferReverse.java
 * Package Name:com.easyway.java.basic.disabuse.bytes
 * Date:2015-1-21下午1:26:46
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.bytes;

/**
 * ClassName:StringBufferReverse <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 下午1:26:46 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StringBufferReverse {   
    public static void main(String[] args) {   
        // 原始字符串   
        String s = "A quick brown fox jumps over the lazy dog.";   
        System.out.println("原始的字符串：" + s);   
  
        System.out.print("反转后字符串：");   
        StringBuffer buff = new StringBuffer(s);   
        // java.lang.StringBuffer类的reverse()方法可以将字符串反转   
        System.out.println(buff.reverse().toString());   
    }   
}  