/**
 * Project Name:java-basic
 * File Name:ClassLoaderBasic.java
 * Package Name:com.easyway.java.basic.classloader
 * Date:2015-3-14下午4:15:40
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.classloader;
import java.util.List;
/**
 * <pre>
 * Java虚拟机中类加载器：

 Java虚拟机中可以安装多个类加载器，系统默认三个主要的类加载器，每个类负责加载特定位置的类：

 BootStrap,ExtClassLoader,AppClassLoader

 类加载器也是Java类，因为Java类的类加载器本身也是要被类加载器加载的，显然必须有第一个类加载器不是Java类，
 这个正是BootStrap,使用C/C++代码写的，已经封装到JVM内核中了，而ExtClassLoader和AppClassLoader是Java类。

 * </pre>
 * ClassName:ClassLoaderBasic <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-14 下午4:15:40 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ClassLoaderBasic {

    public static void main(String[] args) {
        // 输出ClassLoaderText的类加载器名称
        System.out.println("ClassLoaderText类的加载器的名称:" + ClassLoaderBasic.class.getClassLoader().getClass().getName());
        System.out.println("System类的加载器的名称:" + System.class.getClassLoader());
        System.out.println("List类的加载器的名称:" + List.class.getClassLoader());

        ClassLoader cl = ClassLoaderBasic.class.getClassLoader();
        while (cl != null) {
            System.out.print(cl.getClass().getName() + "->");
            cl = cl.getParent();
        }
        System.out.println(cl);
    }

}
