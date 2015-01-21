/**
 * Project Name:java-basic
 * File Name:CalendarTest.java
 * Package Name:com.easyway.java.basic.disabuse.date
 * Date:2015-1-21下午1:32:51
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.date;
import java.text.DateFormat;   
import java.text.SimpleDateFormat;   
import java.util.Calendar;   
import java.util.GregorianCalendar;   
/**
 * <pre>
 * 公历历法java.util.GregorianCalendar 

Calendar是一个抽象类，我们无法直接实例化它，它有一个具体子类实体类java.util.GregorianCalendar，这个类实现的就是我们日常所用的公历历法，或者叫做阳历。我们可以直接使用new命令创建它的实例，或者使用Calendar类的这个方法来获得它实例： 
Java代码 复制代码
public static Calendar getInstance(){   
    //other code   
}  

采用上面这个方法时，我们创建的Calendar对象的日期和时间值是对象被创建时系统日期和时间值。当使用new命令时，我们有两种选择，一种是使用系统当前的日期和时间值初始化GregorianCalendar对象；另一种是通过给定年、月、日、时、分、秒等属性值来对其进行初始化。请看下面的例子： 
运行结果如下： 
创建方式：Calendar.getInstance()
日期时间：2008年07月22日 11:54:48
创建方式：new GregorianCalendar()
日期时间：2008年07月22日 11:54:48
创建方式：new GregorianCalendar(2008, 8, 8)
日期时间：2008年09月08日 00:00:00
创建方式：new GregorianCalendar(2008, 8, 8, 6, 10)
日期时间：2008年09月08日 06:10:00
创建方式：new GregorianCalendar(2008, 8, 8, 18, 10, 5)
日期时间：2008年09月08日 18:10:05

为了便于阅读，我们增加一个toFriendlyString(Calendar c)方法，它将日期时间值格式化为一种更加友好易懂的形式，我们将在接下来的内容中讲解它的实现原理。分析运行结果后，我们发现有两个地方需要注意： 
在创建GregorianCalendar对象时，月份值都设定为8，但打印结果都是9月份。这并不是我们的代码有问题，而是因为JAVA表示的月份是从0开始的，也就是说它用来表示月份的数值总是比实际月份值小1。因此我们要表示8月份，就是应该设置8-1=7这个值。
GregorianCalendar的小时数是24小时制的。

为了避免出现因为忘记处理1的差值而设置了错误的月份，也让代码看起来更加直观，推荐大家使用定义在Calendar类的的这些常量来代替直接用数字表示月份： 
一月：Calendar.JANUARY = 0
二月：Calendar.FEBRUARY = 1
三月：Calendar.MARCH = 2
四月：Calendar.APRIL = 3
五月：Calendar.MAY = 4
六月：Calendar.JUNE = 5
七月：Calendar.JULY = 6
八月：Calendar.AUGUST = 7
九月：Calendar.SEPTEMBER = 8
十月：Calendar.OCTOBER = 9
十一月：Calendar.NOVEMBER = 10
十二月：Calendar.DECEMBER = 11

如果我们想要从Calendar对象获得各种属性的值，就需要调用它的get(int field)方法，这个方法接收一个int型的参数，并且根据这个给定参数的值来返回相应的属性的值。该方法的定义如下： 
Java代码 复制代码
public int get(int field){   
    //other code   
}  

我们以一个示例来说明get(int field)方法所能接受的一些常用参数的含义及用法： 
 * 
 * </pre>
 * ClassName:CalendarTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 下午1:32:51 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CalendarTest {   
    /**  
     * 以一种较为友好的方式格式化日期时间值  
     *   
     * @param c  
     *            日期时间对象  
     * @return 格式化后的日期时间字符串  
     */  
    public static String toFriendlyString(Calendar c) {   
        if (c != null) {   
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");   
            return df.format(c.getTime());   
        }   
        return null;   
    }   
  
    public static void main(String[] args) {   
        Calendar c1 = Calendar.getInstance();   
        System.out.println("创建方式：Calendar.getInstance()");   
        System.out.println("日期时间：" + CalendarTest.toFriendlyString(c1));   
        System.out.println();   
  
        Calendar c2 = new GregorianCalendar();   
        System.out.println("创建方式：new GregorianCalendar()");   
        System.out.println("日期时间：" + CalendarTest.toFriendlyString(c2));   
        System.out.println();   
  
        // 参数含义依次为：年、月、日   
        Calendar c3 = new GregorianCalendar(2008, 8, 8);   
        System.out.println("创建方式：new GregorianCalendar(2008, 8, 8)");   
        System.out.println("日期时间：" + CalendarTest.toFriendlyString(c3));   
        System.out.println();   
  
        // 参数含义依次为：年、月、日、时、分   
        Calendar c4 = new GregorianCalendar(2008, 8, 8, 6, 10);   
        System.out.println("创建方式：new GregorianCalendar(2008, 8, 8, 6, 10)");   
        System.out.println("日期时间：" + CalendarTest.toFriendlyString(c4));   
        System.out.println();   
  
        // 参数含义依次为：年、月、日、时、分、秒   
        Calendar c5 = new GregorianCalendar(2008, 8, 8, 18, 10, 5);   
        System.out.println("创建方式：new GregorianCalendar(2008, 8, 8, 18, 10, 5)");   
        System.out.println("日期时间：" + CalendarTest.toFriendlyString(c5));   
    }   
}  
