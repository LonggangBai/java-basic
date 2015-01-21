/**
 * Project Name:java-basic
 * File Name:StringReverse.java
 * Package Name:com.easyway.java.basic.disabuse.bytes
 * Date:2015-1-21下午1:25:34
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.bytes;

/**
 * <pre>
 * 
 * 字符串的反转输出 
 * 
 * 这也是面试题中常考的一道。我们就以一个包含了全部26个英文字母，同时又具有完整含义的最短句子作为例子来完成解答。先来看一下这个句子： 
 * 
 * 引用
 * A quick brown fox jumps over the lazy dog.（一只轻巧的棕色狐狸从那条懒狗身上跳了过去。）
 * 
 * 
 * 最常用的方式就是反向取出每个位置的字符，然后依次将它们输出到控制台：
 * 运行结果： 
 * 原始的字符串：A quick brown fox jumps over the lazy dog.
 * 反转后字符串：.god yzal eht revo spmuj xof nworb kciuq A
 * 反转后字符串：.god yzal eht revo spmuj xof nworb kciuq A
 * 
 * 以上两种方式虽然常用，但却不是最简单的方式，更简单的是使用现有的方法：
 * </pre>
 * 
 * ClassName:StringReverse <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:25:34 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class StringReverse {
    public static void main(String[] args) {
        // 原始字符串
        String s = "A quick brown fox jumps over the lazy dog.";
        System.out.println("原始的字符串：" + s);

        System.out.print("反转后字符串：");
        for (int i = s.length(); i > 0; i--) {
            System.out.print(s.charAt(i - 1));
        }

        // 也可以转换成数组后再反转，不过有点多此一举
        char[] data = s.toCharArray();
        System.out.println();
        System.out.print("反转后字符串：");
        for (int i = data.length; i > 0; i--) {
            System.out.print(data[i - 1]);
        }
    }
}
