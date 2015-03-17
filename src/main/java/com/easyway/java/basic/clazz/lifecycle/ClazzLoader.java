/**
 * Project Name:java-basic
 * File Name:ClassLoader.java
 * Package Name:com.easyway.java.basic.clazz.lifecycle
 * Date:2015-3-17下午3:17:51
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.lifecycle;
/**
 * ClassName:类加载的机制 <br/>
 * Function:  类的加载是指把类的.class文件中的二进制数据读入到内存中，把它存放在运行时数据区的方法区内，
 * 然后在堆区创建一个java.lang.CLass对象，用来封装类的方法区内的数据结果。
 * 
 * 类的加载的最终的产品是位于运行时数据区的堆区的class对象。Class对象封装了类的方法区内的数据结构，
 * 并且向Java程序提供了访问类在方法区内的数据结构的接口。
 * 
 * 堆区：存储描述Worker类的Class对象。
 * 方法区：Worker类的数据结构。
 * 类的加载是由类加载器完成的，类加载器可分为两种：
 * 1.java虚拟机自带的加载器包括启动类加载器，扩展类加载器和系统类加载器。
 * 2.用户自定义的类加载器是java.lang.ClassLoader类的子类的实例，用户可以通过它来定制类的加载方式。
 * 
 * 
 * <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-17 下午3:17:51 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ClazzLoader {

}

