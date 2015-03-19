/**
 * Project Name:java-basic
 * File Name:ValueOf.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-19上午11:15:27
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:ValueOf <br/>
 * Function: 不可变类的实例的状态不会变化，这样的实例可以安全地被其他与之关联的对象共享，
 * 还都可以安全地被多个线程共享。为了节省控件优化程序的性能，应该尽可能的重用不可变类的实例，
 * 避免重复创建有相同属性的不可变类的实例 。
 * 在一些不可变类，如Integer类做了优化，它具有一个实例缓存，用来存放程序中使用Integer实例。
 * 
 * <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 上午11:15:27 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ValueOf {
    public static void main(String[] args) {
        Integer a=new Integer(10);
        Integer b=new Integer(10);
        
        Integer c=Integer.valueOf(10);
        Integer d=Integer.valueOf(10);
        
        
        Integer e=Integer.valueOf(130);
        Integer f=Integer.valueOf(130);
        System.out.println("a==b ?"+(a==b));
        System.out.println("b==c ?"+(b==c));
        System.out.println("c==d ?"+(c==d));
        
        System.out.println("e==f ?"+(e==f));
        
        Map map=new HashMap();
    }

}

