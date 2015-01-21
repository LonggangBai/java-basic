/**
 * Project Name:java-basic
 * File Name:CalendarDateTest.java
 * Package Name:com.easyway.java.basic.disabuse.date
 * Date:2015-1-21下午1:33:33
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * 
 * <pre>
 * 运行结果如下： 
 * 当前时刻：2008年07月22日 13:16:07.421
 * 属性名称：Calendar.AM_PM
 * 代表含义：上下午标识，上午返回Calendar.AM=0，下午返回Calendar.PM=1
 * 测试结果：1
 * 属性名称：Calendar.DATE
 * 代表含义：一个月中的第几天，同Calendar.DAY_OF_MONTH
 * 测试结果：22
 * 属性名称：Calendar.DAY_OF_MONTH
 * 代表含义：一个月中的第几天，同Calendar.DATE
 * 测试结果：22
 * 属性名称：Calendar.DAY_OF_WEEK
 * 代表含义：一周中的第几天，对应星期几，第一天为星期日，于此类推。
 * 星期日:Calendar.SUNDAY=1
 * 星期一:Calendar.MONDAY=2
 * 星期二:Calendar.TUESDAY=3
 * 星期三:Calendar.WEDNESDAY=4
 * 星期四:Calendar.THURSDAY=5
 * 星期五:Calendar.FRIDAY=6
 * 星期六:Calendar.SATURDAY=7
 * 测试结果：3
 * 属性名称：Calendar.DAY_OF_WEEK_IN_MONTH
 * 代表含义：这一天所对应的星期几在该月中是第几次出现
 * 测试结果：4
 * 属性名称：Calendar.DAY_OF_YEAR
 * 代表含义：一年中的第几天
 * 测试结果：204
 * 属性名称：Calendar.HOUR
 * 代表含义：12小时制下的小时数，中午和午夜表示为0
 * 测试结果：1
 * 属性名称：Calendar.HOUR_OF_DAY
 * 代表含义：24小时制下的小时数，午夜表示为0
 * 测试结果：13
 * 属性名称：Calendar.MILLISECOND
 * 代表含义：毫秒数
 * 测试结果：421
 * 属性名称：Calendar.MINUTE
 * 代表含义：分钟
 * 测试结果：16
 * 属性名称：Calendar.MONTH
 * 代表含义：月份，从0到11表示12个月份，比实际月份值小1
 * 测试结果：6
 * 属性名称：Calendar.SECOND
 * 代表含义：秒
 * 测试结果：7
 * 属性名称：Calendar.WEEK_OF_MONTH
 * 代表含义：一个月中的第几个星期
 * 测试结果：4
 * 属性名称：Calendar.WEEK_OF_YEAR
 * 代表含义：一年中的第几个星期
 * 测试结果：30
 * 属性名称：Calendar.YEAR
 * 代表含义：年份
 * 测试结果：2008
 * 
 * 其中Calendar.DAY_OF_WEEK_IN_MONTH代表的含义比较难理解一些，它表示“这一天所对应的星期几在该月中是第几次出现”。比如2008年8月8日是星期五，在它之前的8月1日也是星期五，因此它是8月份的第二个星期五。所以这时调用get(Calendar.DAY_OF_WEEK_IN_MONTH)就会返回2。这里存在一个简单易记的规律：对于每月的1-7号，它们一定占全了星期一到星期日，所以不管是它们中的哪一天，也不管这一天是星期几，它总是第一个，因此返回1；8-14号也同样占全了星期一到星期日，但由于1-7号的关系，对于它们总是返回2；以此类推，15-21号返回3，22-28号返回4，29-31号返回5。 
 * 
 * Calendar对象和Date对象可以通过Calendar类的如下两个方法进行相互转换： 
 * Java代码 复制代码
 * public final Date getTime() {   
 *     //other code   
 * }   
 *   
 * public final void setTime(Date date) {   
 *     //other code   
 * }
 * </pre>
 * 
 * ClassName:CalendarDateTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:33:33 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class CalendarDateTest {
    /**
     * 以一种较为友好的方式格式化日期时间值
     * 
     * @param c
     *            日期时间对象
     * @return 格式化后的日期时间字符串
     */
    public static String toFriendlyString(Calendar c) {
        if (c != null) {
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss.SSS");
            return df.format(c.getTime());
        }
        return null;
    }


    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        System.out.println("当前时刻：" + CalendarDateTest.toFriendlyString(c));
        System.out.println();

        System.out.println("属性名称：Calendar.AM_PM");
        System.out.println("代表含义：上下午标识，上午返回Calendar.AM=0，下午返回Calendar.PM=1");
        System.out.println("测试结果：" + c.get(Calendar.AM_PM));
        System.out.println();

        System.out.println("属性名称：Calendar.DATE");
        System.out.println("代表含义：一个月中的第几天，同Calendar.DAY_OF_MONTH");
        System.out.println("测试结果：" + c.get(Calendar.DATE));
        System.out.println();

        System.out.println("属性名称：Calendar.DAY_OF_MONTH");
        System.out.println("代表含义：一个月中的第几天，同Calendar.DATE");
        System.out.println("测试结果：" + c.get(Calendar.DAY_OF_MONTH));
        System.out.println();

        System.out.println("属性名称：Calendar.DAY_OF_WEEK");
        System.out.println("代表含义：一周中的第几天，对应星期几，第一天为星期日，于此类推。");
        System.out.println("星期日:Calendar.SUNDAY=1");
        System.out.println("星期一:Calendar.MONDAY=2");
        System.out.println("星期二:Calendar.TUESDAY=3");
        System.out.println("星期三:Calendar.WEDNESDAY=4");
        System.out.println("星期四:Calendar.THURSDAY=5");
        System.out.println("星期五:Calendar.FRIDAY=6");
        System.out.println("星期六:Calendar.SATURDAY=7");
        System.out.println("测试结果：" + c.get(Calendar.DAY_OF_WEEK));
        System.out.println();

        System.out.println("属性名称：Calendar.DAY_OF_WEEK_IN_MONTH");
        System.out.println("代表含义：这一天所对应的星期几在该月中是第几次出现");
        System.out.println("测试结果：" + c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println();

        System.out.println("属性名称：Calendar.DAY_OF_YEAR");
        System.out.println("代表含义：一年中的第几天");
        System.out.println("测试结果：" + c.get(Calendar.DAY_OF_YEAR));
        System.out.println();

        System.out.println("属性名称：Calendar.HOUR");
        System.out.println("代表含义：12小时制下的小时数，中午和午夜表示为0");
        System.out.println("测试结果：" + c.get(Calendar.HOUR));
        System.out.println();

        System.out.println("属性名称：Calendar.HOUR_OF_DAY");
        System.out.println("代表含义：24小时制下的小时数，午夜表示为0");
        System.out.println("测试结果：" + c.get(Calendar.HOUR_OF_DAY));
        System.out.println();

        System.out.println("属性名称：Calendar.MILLISECOND");
        System.out.println("代表含义：毫秒数");
        System.out.println("测试结果：" + c.get(Calendar.MILLISECOND));
        System.out.println();

        System.out.println("属性名称：Calendar.MINUTE");
        System.out.println("代表含义：分钟");
        System.out.println("测试结果：" + c.get(Calendar.MINUTE));
        System.out.println();

        System.out.println("属性名称：Calendar.MONTH");
        System.out.println("代表含义：月份，从0到11表示12个月份，比实际月份值小1");
        System.out.println("测试结果：" + c.get(Calendar.MONTH));
        System.out.println();

        System.out.println("属性名称：Calendar.SECOND");
        System.out.println("代表含义：秒");
        System.out.println("测试结果：" + c.get(Calendar.SECOND));
        System.out.println();

        System.out.println("属性名称：Calendar.WEEK_OF_MONTH");
        System.out.println("代表含义：一个月中的第几个星期");
        System.out.println("测试结果：" + c.get(Calendar.WEEK_OF_MONTH));
        System.out.println();

        System.out.println("属性名称：Calendar.WEEK_OF_YEAR");
        System.out.println("代表含义：一年中的第几个星期");
        System.out.println("测试结果：" + c.get(Calendar.WEEK_OF_YEAR));
        System.out.println();

        System.out.println("属性名称：Calendar.YEAR");
        System.out.println("代表含义：年份");
        System.out.println("测试结果：" + c.get(Calendar.YEAR));
    }
}
