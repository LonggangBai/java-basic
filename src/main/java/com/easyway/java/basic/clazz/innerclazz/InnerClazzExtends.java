/**
 * Project Name:java-basic
 * File Name:InnerClazzExtends.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-19下午4:08:02
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.innerclazz;
/**
 * ClassName:InnerClazzExtends <br/>
 * Function: 在直接构造实例内部类的实例的时候，java虚拟机会自动使用内部类实例引用它的外部类实例。 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午4:08:02 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class OuterClazzExtends {
     private int a;
     public OuterClazzExtends(int a){
         this.a=a;
     }
     class Inner{
         public Inner(){}
         public void print(){
             System.out.println("a="+a);//访问外部类的实例变量a
         }
     }
 }

public class InnerClazzExtends  extends OuterClazzExtends.Inner{
    
    public InnerClazzExtends(OuterClazzExtends o){
        o.super();
    }
    public static void main(String[] args) {
        OuterClazzExtends outer1=new OuterClazzExtends(1);
        OuterClazzExtends outer2=new OuterClazzExtends(2);
        
        OuterClazzExtends.Inner  in=outer1.new Inner();
        in.print();
        
        InnerClazzExtends s1=new InnerClazzExtends(outer1);
        InnerClazzExtends s2=new InnerClazzExtends(outer2);
        s1.print();
        s2.print();
        
    }

}

