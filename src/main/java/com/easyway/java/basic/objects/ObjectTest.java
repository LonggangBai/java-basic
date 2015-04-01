/**
 * Project Name:java-basic
 * File Name:ObjectTest.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-25下午3:56:13
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;
/**
 * ClassName:ObjectTest <br/>
 * Function: Object类 <br/>
 * Reason:	 Object类的主要成员方法：
 * 1.equals:比较两个对象是否相等，仅当比较两个引用变量指向同一个对象时候，equals方法返回true。 
 * 2.notify:从等待池中唤醒一个线程，把它转移到锁池。
 * 3.notifyAll：从等待池中唤醒所有的线程，把他们转移到锁池。
 * 4.wait：使当前线程进入等待状态，直到别的线程调用notify或者notifyAll方法唤醒他。
 * 5.hashCode：返回对象的哈希码。hashTable和HashMap会根据对象的哈希码决定它的存放位置。
 * 6.toString:返回当前对象的字符串表示，格式为：“类名@对象的十六禁止哈希码”
 * 7.finalize:对于一个已经不被任何引用变量引用的对象，当垃圾回收准备回收该对象所占用的内存，将自动调用该对象的finalize方法。<br/>
 * 
 * String类和StringBuffer类
 * 
 * Date:     2015-3-25 下午3:56:13 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ObjectTest {
    public static void main(String[] args) {
        String s1=new String("abc");
        String s2=new String("abc");
        System.out.println(s1.equals(s2));
        StringBuffer sb1=new StringBuffer("abc");
        StringBuffer sb2=new StringBuffer("abc");
        System.out.println(sb1.equals(sb2));
    }

}

