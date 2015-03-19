/**
 * Project Name:java-basic
 * File Name:PartClazz.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-19下午3:59:10
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.innerclazz;
/**
 * ClassName:PartClazz <br/>
 * Function: 局部内部类 <br/>
 * Reason:	 特点：
 * 1.局部内部类只能在当前方法中使用。
 * 2.局部内部类和实例内部类一样，不能包含静态成员。
 * 3.局部内部类定义不能被public，protected，private等修饰。
 * 4.局部内部类和实例内部类一样，可以访问外部类的所有成员。局部内部类可以访问所在方法中final标识的参数和变量。 <br/>
 * Date:     2015-3-19 下午3:59:10 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class PartA{
    final int localV2=100;
    public void method(){
        class B {
            int v1;
            int v2;
            class C{
                int v3;
                {
                    System.out.println("localV2="+localV2);
                }
            }
        }
        B b=new B();
        B.C c=b.new C();
        
    }
}
public class PartClazz {
    public static void main(String[] args) {
        PartA a=new PartA();
        a.method();
    }

}

