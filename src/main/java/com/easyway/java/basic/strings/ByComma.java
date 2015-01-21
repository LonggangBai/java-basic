/**
 * Project Name:java-basic
 * File Name:ByComma.java
 * Package Name:com.easyway.java.basic.strings
 * Date:2015-1-21下午2:18:54
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.strings;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1．现在输入n个数字，以逗号，分开；然后可选择升或者降序排序；按提交键就在另一页面显示按什么排序，结果为，提供reset
 * 
 * ClassName:ByComma <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:18:54 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ByComma {
    public static String[] splitStringByComma(String source) {
        if (source == null || source.trim().equals(""))
            return null;
        StringTokenizer commaToker = new StringTokenizer(source, ",");
        String[] result = new String[commaToker.countTokens()];
        int i = 0;
        while (commaToker.hasMoreTokens()) {
            result[i] = commaToker.nextToken();
            i++;
        }
        return result;
    }


    public static void main(String args[]) {
        String[] s = splitStringByComma("5,8,7,4,3,9,1");
        int[] ii = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ii[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(ii);
        // asc
        for (int i = 0; i < s.length; i++) {
            System.out.println(ii[i]);
        }
        // desc
        for (int i = (s.length - 1); i >= 0; i--) {
            System.out.println(ii[i]);
        }
    }
}
