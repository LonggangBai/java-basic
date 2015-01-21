/**
 * Project Name:java-basic
 * File Name:DateFormatTest.java
 * Package Name:com.easyway.java.basic.disabuse.date
 * Date:2015-1-21下午1:34:29
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.date;

/**
 * <pre>
 * 日期格式化与解析 

 我们回头再来看看在上面的例子中定义的toFriendlyString(Calendar c)方法，它将一个Calendar对象的日期时间值以一种很友好的方式来展现，使人们很容易看懂，也符合我们中国人的习惯。这完全得益于抽象类DateFormat以及它的子类实体类SimpleDateFormat的帮助。这两个类都位于java.text包中，是专门用于日期格式化和解析的类。而这两项工作的核心就是我们为此设定的Pattern，我们可以称之为“日期格式表达式”。 

 理论上讲日期格式表达式包含全部26个英文字母的大小写，不过它们中的一些字母只是被预留了，并没有确切的含义。目前有效的字母及它们所代表的含义如下： 
 G：年代标识，表示是公元前还是公元后
 y：年份
 M：月份
 d：日
 h：小时，从1到12，分上下午
 H：小时，从0到23
 m：分钟
 s：秒
 S：毫秒
 E：一周中的第几天，对应星期几，第一天为星期日，于此类推
 z：时区
 D：一年中的第几天
 F：这一天所对应的星期几在该月中是第几次出现
 w：一年中的第几个星期
 W：一个月中的第几个星期
 a：上午/下午标识
 k：小时，从1到24
 K：小时，从0到11，区分上下午

 在日期格式表达式中出现的所有字母，在进行日期格式化操作后，都将被其所代表的含义对应的属性值所替换，并且对某些字母来说，重复次数的不同，格式化后的结果也会有所不同。请看下面的例子： 
 * 输出结果如下： 
 1位：年代:公元;年份:08;月份:7;日:22;时(1~12):3;时(0~23):15;分:17;秒:49;毫秒:187;星期:星期二;上/下午:下午;时区:CST
 2位：年代:公元;年份:08;月份:07;日:22;时(1~12):03;时(0~23):15;分:17;秒:49;毫秒:187;星期:星期二;上/下午:下午;时区:CST
 3位：年代:公元;年份:08;月份:七月;日:022;时(1~12):003;时(0~23):015;分:017;秒:049;毫秒:187;星期:星期二;上/下午:下午;时区:CST
 4位：年代:公元;年份:2008;月份:七月;日:0022;时(1~12):0003;时(0~23):0015;分:0017;秒:0049;毫秒:0187;星期:星期二;上/下午:下午;时区:中国标准时间
 5位：年代:公元;年份:02008;月份:七月;日:00022;时(1~12):00003;时(0~23):00015;分:00017;秒:00049;毫秒:00187;星期:星期二;上/下午:下午;时区:中国标准时间
 6位：年代:公元;年份:002008;月份:七月;日:000022;时(1~12):000003;时(0~23):000015;分:000017;秒:000049;毫秒:000187;星期:星期二;上/下午:下午;时区:中国标准时间

 如果我们想输出原始的字母，而不是它们所代表含义的替换值，就需要用单引号将它们包含在内，对于预留字母也是如此，虽然它们没有确切的含义。一对单引号可以一次包含多个字母，而两个连续的单引号将输出一个单引号结果，双引号则需要转义后输出。对于26个字母之外的字符，可以放在一对单引号中，也可以直接书写。请看下面的例子：
 Java代码 复制代码
 import java.text.SimpleDateFormat;   
 import java.util.Date;   

 public class Test {   
 public static void main(String[] args) {   
     Date now = new Date();   
     SimpleDateFormat df = new SimpleDateFormat(   
     "'YEAR': yyyy 'MONTH:' ''MM'' 'DAY:' \"dd\" ");   
     System.out.println(df.format(now));   
 }   
 }  

 运行结果： 
 YEAR: 2008 MONTH: '07' DAY: "22"

 * </pre>
 * ClassName:DateFormatTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 下午1:34:29 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatTest {
    public static void main(String[] args) {
        // 使用系统当前日期时间值创建一个Date对象
        Date now = new Date();

        // 创建一个日期格式表达式
        String pattern = "年代:G;年份:y;月份:M;日:d;时(1~12):h;时(0~23):H;分:m;秒:s;毫秒:S;星期:E;上/下午:a;时区:z";
        // 使用日期格式表达式创建一个SimpleDateFormat对象
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        // 调用SimpleDateFormat类的format(Date date)方法对Date对象进行格式化，并返回格式化后的字符串。
        // 该方法继承自java.text.DateFormat类
        System.out.println("1位：" + df.format(now));

        // 创建一个新的日期格式表达式
        pattern = "年代:GG;年份:yy;月份:MM;日:dd;时(1~12):hh;时(0~23):HH;分:mm;秒:ss;毫秒:SS;星期:EE;上/下午:aa;时区:zz";
        // 调用SimpleDateFormat的applyPattern(String pattern)方法用新创建的日期格式表达式替换其原有的
        df.applyPattern(pattern);
        System.out.println("2位：" + df.format(now));

        pattern = "年代:GGG;年份:yyy;月份:MMM;日:ddd;时(1~12):hhh;时(0~23):HHH;分:mmm;秒:sss;毫秒:SSS;星期:EEE;上/下午:aaa;时区:zzz";
        df.applyPattern(pattern);
        System.out.println("3位：" + df.format(now));

        pattern =
                "年代:GGGG;年份:yyyy;月份:MMMM;日:dddd;时(1~12):hhhh;时(0~23):HHHH;分:mmmm;秒:ssss;毫秒:SSSS;星期:EEEE;上/下午:aaaa;时区:zzzz";
        df.applyPattern(pattern);
        System.out.println("4位：" + df.format(now));

        pattern =
                "年代:GGGGG;年份:yyyyy;月份:MMMMM;日:ddddd;时(1~12):hhhhh;时(0~23):HHHHH;分:mmmmm;秒:sssss;毫秒:SSSSS;星期:EEEEE;上/下午:aaaaa;时区:zzzzz";
        df.applyPattern(pattern);
        System.out.println("5位：" + df.format(now));

        pattern =
                "年代:GGGGGG;年份:yyyyyy;月份:MMMMMM;日:dddddd;时(1~12):hhhhhh;时(0~23):HHHHHH;分:mmmmmm;秒:ssssss;毫秒:SSSSSS;星期:EEEEEE;上/下午:aaaaaa;时区:zzzzzz";
        df.applyPattern(pattern);
        System.out.println("6位：" + df.format(now));
    }
}
