/**
 * Project Name:java-basic
 * File Name:DateTest.java
 * Package Name:com.easyway.java.basic.disabuse.date
 * Date:2015-1-21下午1:31:32
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.date;

import java.util.Date;


/**
 * <pre>
 * 日期和时间的处理不仅在面试题中会考到，在实际项目开发中也是我们经常需要处理的问题，似乎没有哪个项目可以避开它们，我们常常在处理用户的出生年月日、注册日期，订单的创建时间等属性时用到，由此可见其重要性。 
 * 
 * java.util.Date类 
 * 
 * 提到日期和时间，我想大家最先想到应该是java.util.Date类吧。Date类可以精确到毫秒数，这个毫秒数是相对于格林威治标准时间“1970-01-01 00:00:00.000 GMT”的差值。那么，什么是格林威治标准时间呢？要回答这个问题，我们需要先来了解一下世界时间标准方面的知识。 
 * 
 * 世界时间标准主要有UTC，即Coordinated Universal Time（中文名译作世界协调时间、世界统一时间或世界标准时间），以及GMT，即Greenwich Mean Time（中文名译作格林威治标准时间或格林威治平均时间）两种。严格来讲，UTC比GMT更加精确一些，不过它们的差值不会超过0.9秒，如果超过了，将会为UTC增加闰秒以与GMT，也就是地球自转周期保持一致。所以在日常使用中，我们可以把UTC和GMT一样看待。 
 * 
 * 日期和时间的表示是与我们所处的时区相关联的，如果我们不指定时区，那么它们将以系统默认的时区来显示。我们先来看看如何创建日期对象。Date类有很多个构造器方法，大部分已经不被赞成使用了（Deprecated），不过还剩下两个可以使用的： 
 * Java代码 复制代码
 * public Date() {   
 *     this(System.currentTimeMillis());   
 * }   
 *   
 * public Date(long date) {   
 *     //other code   
 * }  
 * 
 * 第一个是无参构造器，使用系统当前时间的毫秒数来创建Date对象，它调用了java.lang.System类的currentTimeMillis()来取得系统的当前时间的毫秒值。这是个本地方法，它的定义如下： 
 * Java代码 复制代码
 * public static native long currentTimeMillis();  
 * 
 * 第二个构造器是根据给定的毫秒数来创建一个与之对应的Date对象，这个毫秒数决定了被创建对象的年、月、日、时、分、秒属性的值。 
 * 
 * 我们来看看日期和时间在默认时区下的显示效果： 
 * Java代码 复制代码
 * import java.util.Date;   
 *   
 * public class DateTest {   
 *     public static void main(String[] args) {   
 *         Date d = new Date();   
 *         // 在默认时区下输出日期和时间值   
 *         System.out.println(d);   
 *     }   
 * }  
 * 
 * 运行结果： 
 * Tue Jul 22 10:44:47 CST 2008
 * 
 * 大家应该注意到了年份前的“CST”标识，它是China Standard Time的缩写，指的是中国标准时间，也就是我们常说的北京时间。它与UTC的时差是UTC+8:00，就是说北京时间比世界标准时间早8个小时，如果世界标准时间是早上1点，北京时间就是早上9点。一般情况下我们不需要关心时区问题。 
 * 
 * 在创建完Date对象之后，我们可以通过调用getTime()方法来获得该对象的毫秒数值，调用setTime(long time)方法来设置它的毫秒数值，从而影响年、月、日、时、分、秒这些属性。这两个方法的定义如下： 
 * Java代码 复制代码
 * public long getTime() {   
 *     //other code   
 * }   
 *   
 * public void setTime(long time) {   
 *     //other code   
 * }  
 * 
 * 既然Date对象可以表示盛相对于“1970-01-01 00:00:00.000 GMT”的毫秒数，我们自然可以通过这个值来比较两个日期的大小了，不过对于日期来讲，前后的说法应该更为恰当。而Date类已经为我们提供了这样的方法： 
 * Java代码 复制代码
 * public boolean before(Date when) {   
 *     //other code   
 * }   
 *   
 * public boolean after(Date when) {   
 *     //other code   
 * }   
 *   
 * public int compareTo(Date anotherDate) {   
 *     //other code   
 * }  
 * 
 * before()是判断当前日期是否在参数日期之前，即当前日期毫秒数小于参数日期毫秒数；after()是判断当前日期是否在参数日期之后，即当前日期毫秒数大于参数日期毫秒数。而compareTo()是将当前日期与参数日期比较后，返回一个int型值，它的返回值有三种可能：-1、0和1。如果返回-1则表示当前日期在参数日期之前；如果返回0则表示两个日期是同一时刻；返回1则表示当前日期在参数日期之后。虽然我们可以用compareTo()方法来比较两个Date对象，但是它的设计实际是另有用途的，我们在后面的章节将会讲到。 
 * 
 * 下面我们就用一个示例来检验一下以上方法的用法：
 * 
 * 
 * 运行结果： 
 * 调用方法：d1900.before(d2008)
 * 比较结果："1900-01-01 20:00:00"在"2008-08-08 20:00:00"之前
 * 调用方法：d2008.after(d1900)
 * 比较结果："2008-08-08 20:00:00"在"1900-01-01 20:00:00"之后
 * 调用方法：d1900.compareTo(d2008)
 * 比较结果："1900-01-01 20:00:00"在"2008-08-08 20:00:00"之前
 * 
 * 那么如果我们想直接获取或者改变年、月、日、时、分、秒等等这些属性的值时怎么办呢？Date类当然有完成这些操作的方法，不过遗憾的是它们也都已经不被赞成使用了。我们必须换一个能够提供这些操作的类，这个类就是java.util.Calendar。
 * </pre>
 * 
 * ClassName:DateTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:31:32 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class DateTest {
    public static void main(String[] args) {
        // 2008-08-08 20:00:00对应的毫秒数
        long t2008 = 1218196800000L;
        // 1900-01-01 20:00:00对应的毫秒数
        long t1900 = -2208945952000L;

        // 指定毫秒数创建Date对象
        Date d2008 = new Date(t2008);
        // 使用系统默认时间创建Date对象
        Date d1900 = new Date();
        // 通过设置毫秒数改变日期和时间
        d1900.setTime(t1900);

        System.out.println("调用方法：d1900.before(d2008)");
        System.out.print("比较结果：\"1900-01-01 20:00:00\"在\"2008-08-08 20:00:00\"");
        // 使用before()方法比较
        if (d1900.before(d2008)) {
            System.out.println("之前");
        }
        else {
            System.out.println("之后");
        }

        System.out.println();

        System.out.println("调用方法：d2008.after(d1900)");
        System.out.print("比较结果：\"2008-08-08 20:00:00\"在\"1900-01-01 20:00:00\"");
        // 使用after()方法比较
        if (d2008.after(d1900)) {
            System.out.println("之后");
        }
        else {
            System.out.println("之前");
        }

        System.out.println();

        System.out.println("调用方法：d1900.compareTo(d2008)");
        System.out.print("比较结果：\"1900-01-01 20:00:00\"在\"2008-08-08 20:00:00\"");
        // 使用compareTo()方法比较
        int i = d1900.compareTo(d2008);
        if (i == -1) {
            System.out.println("之前");
        }
        else if (i == 1) {
            System.out.println("之后");
        }
        else if (i == 0) {
            System.out.println("是同一时刻");
        }
    }
}
