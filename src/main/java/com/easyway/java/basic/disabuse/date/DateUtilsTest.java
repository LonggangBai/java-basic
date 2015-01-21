/**
 * Project Name:java-basic
 * File Name:DateUtilsTest.java
 * Package Name:com.easyway.java.basic.disabuse.date
 * Date:2015-1-21下午1:35:18
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.date;

/**
 * 
 * 
 * <pre>
 * 
 * 我们将日期对象转换成一定格式的字符串输出，以得到符合我们习惯的较为友好的表现形式。我们还可以反过来，使用DateFormat类的parse(String source)方法将具有一定格式的字符串转换为一个Date对象，前提是我们利用前面讲到日期格式表达式语法为其找到一个合适的Pattern。例如： 
 * 
 * 运行结果： 
 *原始字符串：2008-08-08
 *对应表达式：yyyy-MM-dd
 *转换后的值：Fri Aug 08 00:00:00 CST 2008
 *原始字符串：05年2月12日 18:04:33
 *对应表达式：yy年M月d日 HH:mm:ss
 *转换后的值：Sat Feb 12 18:04:33 CST 2005
 *原始字符串：16/5/2004 20:7:2.050
 * 对应表达式：d/M/yyyy HH:m:s.SSS
 *转换后的值：Sun May 16 20:07:02 CST 2004
 * </pre>
 * ClassName:DateUtilsTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 下午1:35:18 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtilsTest {
    public static void main(String[] args) throws ParseException {
        String s = "2008-08-08";
        System.out.println("原始字符串：" + s);
        String pattern = "yyyy-MM-dd";
        System.out.println("对应表达式：" + pattern);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date = df.parse(s);
        System.out.println("转换后的值：" + date);
        System.out.println();

        s = "05年2月12日 18:04:33";
        System.out.println("原始字符串：" + s);
        pattern = "yy年M月d日 HH:mm:ss";
        System.out.println("对应表达式：" + pattern);
        df.applyPattern(pattern);
        date = df.parse(s);
        System.out.println("转换后的值：" + date);
        System.out.println();

        s = "16/5/2004 20:7:2.050";
        System.out.println("原始字符串：" + s);
        pattern = "d/M/yyyy HH:m:s.SSS";
        System.out.println("对应表达式：" + pattern);
        df.applyPattern(pattern);
        date = df.parse(s);
        System.out.println("转换后的值：" + date);
    }
}
