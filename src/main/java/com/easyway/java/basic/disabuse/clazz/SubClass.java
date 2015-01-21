/**
 * Project Name:java-basic
 * File Name:SubClass.java
 * Package Name:com.easyway.java.basic.disabuse.clazz
 * Date:2015-1-21上午11:58:33
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.clazz;

/**
 * <pre>
 * 运行一下上面的代码，结果马上呈现在我们的眼前： 
 *      父类--静态变量
 *      父类--静态初始化块
 *      子类--静态变量
 *      子类--静态初始化块
 *      父类--变量
 *      父类--初始化块
 *      父类--构造器
 *      子类--变量
 *      子类--初始化块
 *      子类--构造器
 * 现在，结果已经不言自明了。大家可能会注意到一点，那就是，并不是父类完全初始化完毕后才进行子类的初始化，实际上子类的静态变量和静态初始化块的初始化是在父类的变量、
 * 初始化块和构造器初始化之前就完成了。
 * </pre>
 * ClassName:SubClass <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 上午11:58:33 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class Parent {   
    // 静态变量   
    public static String p_StaticField = "父类--静态变量";   
    // 变量   
    public String p_Field = "父类--变量";   
  
    // 静态初始化块   
    static {   
        System.out.println(p_StaticField);   
        System.out.println("父类--静态初始化块");   
    }   
  
    // 初始化块   
    {   
        System.out.println(p_Field);   
        System.out.println("父类--初始化块");   
    }   
  
    // 构造器   
    public Parent() {   
        System.out.println("父类--构造器");   
    }   
}   
public class SubClass extends Parent {
    // 静态变量
    public static String s_StaticField = "子类--静态变量";
    // 变量
    public String s_Field = "子类--变量";
    // 静态初始化块
    static {
        System.out.println(s_StaticField);
        System.out.println("子类--静态初始化块");
    }
    // 初始化块
    {
        System.out.println(s_Field);
        System.out.println("子类--初始化块");
    }


    // 构造器
    public SubClass() {
        System.out.println("子类--构造器");
    }


    // 程序入口
    public static void main(String[] args) {
        new SubClass();
    }
}
