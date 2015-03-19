/**
 * Project Name:java-basic
 * File Name:ObjectReference.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-19下午3:06:50
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;
/**
 * ClassName:ObjectReference <br/>
 * Function: 对象的强，软，弱和虚的引用 <br/>
 * Reason:	 对象应用作用
 * 1.强引用：必可可少的生活的用品，垃圾回收器绝对不会回收他。当内存控件不足，
 * java虚拟机宁愿抛出outofMemoryError错误，是程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足的问题。
 * 2.软引用（SoftReference）
 * 如果对象只具有软引用，可用可无的，垃圾回收站在空间足够，就不回收他，如果空间不足，就回收它。
 * 软引用可用来实现内存敏感的高速缓存。
 * 软引用可可以和一个引用队列联合使用，如果软引用的对象被垃圾回收器回收，java虚拟机就会把这个软引用加入到与之关联的引用队列中。
 * 3.弱引用
 * 弱引用和软引用的区别在于：只具有若引用的对象拥有更短暂的生命周期，垃圾回收站的线程扫描它所管辖的内存区域文档过程发现弱引用的对象，不管空间是否够用，都会回收他的内存。
 * 4.虚引用：一个对象仅持有虚引用，那么和没有任何引用一样，在任何时候都可能被垃圾回收机制回收。<br/>
 * Date:     2015-3-19 下午3:06:50 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ObjectReference {

}

