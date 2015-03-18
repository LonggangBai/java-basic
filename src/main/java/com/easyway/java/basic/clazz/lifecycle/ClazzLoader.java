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
 * 类加载器用来把类加载到java虚拟机中。从JDK1.2版本中开始，类的加载过程中采用父亲委托机制，这种机制能更好的保证java平台的安全。
 * 
 * java虚拟机自带的类加载器：
 * 1.根类加载器：该加载器没有父类加载器。他负责加载虚拟机的核心类库。如java.lang.*等。
 * java.lang.Object就是由根类加载器加载的。根类加载器从系统属性sun.boot.class.path
 * 所制定的目录中加载类库。根类加载器的实现依赖底层操作系统。属于虚拟机的实现的一部分，他并没有
 * 继承java.lang.ClassLoader类。
 * 2.扩展类加载器：他的父类加载器为根类加载器，它从java.ext.dirs系统属性所指定的目录中加载类库，
 * 或者从jdk的安装目录中jre/lib/ext子目录下加载类库。如果把用户创建的jar文件放在这个目录下，
 * 也会自动由扩展类加载器加载。扩展类加载器是纯Java类， 是java.lang.ClassLoader类的子类。
 * 3.系统类加载器，也称为应用类加载器，他的父类加载器为扩展类加载器。他从环境变量classpath
 * 或者系统属性java.class.path所制定的目录中加载类。它是用户自动亿的类加载器的默认父加载器，
 * 系统类加载器是纯java类，是java.lang.ClassLoader类的子类。
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
    public static void main(String[] args) {
        Class c;
        ClassLoader c1,c11;
        c1=ClassLoader.getSystemClassLoader();//获取系统加载器
        System.out.println(c1);//系统加载器
        while(c1!=null){
            c11=c1;
            c1=c1.getParent();
            System.out.println(c11+"'s parent is "+c1);
        }
        try {
            c=Class.forName("java.lang.Object");
            c1=c.getClassLoader();
            System.out.println("java.lang.object's loader is "+c1);
            
            c=Class.forName("com.easyway.java.basic.clazz.lifecycle.ClazzLoader");
            c1=c.getClassLoader();
            System.out.println("com.easyway.java.basic.clazz.lifecycle.ClazzLoader's loader is "+c1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

