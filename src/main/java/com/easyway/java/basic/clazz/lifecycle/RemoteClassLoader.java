/**
 * Project Name:java-basic
 * File Name:RemoteClassLoader.java
 * Package Name:com.easyway.java.basic.clazz.lifecycle
 * Date:2015-3-18下午4:04:50
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.lifecycle;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *一个类的实例总是引用代表这个类的Class对象，在object类中定义了getClass()的方法，
 *这个方法返回代表对象所属类的CLass对象的引用。此外所有的java类都有一个静态属性class，他引用代表这个类的class对象。 
 *
 *加载过程负责把类的二进制数据读入到java虚拟机的方法区，并且在堆区内创建一个描述这个类的class对象，连接过程负责验证类
 *的二进制数据，为静态变量分配内存并初始化为默认数值，把字节码的符号引用替换直接引用。初始化过程负责执行类的初始化语句，
 *为静态变量赋予初始值。
 *<br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-18 下午4:04:50 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RemoteClassLoader {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URL url=new URL("http://www.javathinker.org/java/book/classes/");
        URLClassLoader loader=new URLClassLoader(new URL[]{url});
        Class objClazz=loader.loadClass("Sample");
        Object obj=objClazz.newInstance();
    }
}

