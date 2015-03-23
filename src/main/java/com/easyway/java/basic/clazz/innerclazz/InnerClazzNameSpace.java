/**
 * Project Name:java-basic
 * File Name:InnerClazzNameSpace.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-23上午9:57:32
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.innerclazz;
/**
 * ClassName:InnerClazzNameSpace <br/>
 * Function: 内部类的类文件 <br/>
 * Reason:	 对于每一个内部类来说，java编译器会生成独立的.class文件，这些类文件的命名规则如下：
 * 1.成员内部类：外部类的名字$内部类的名字
 * 2.局部内部类：外部类的名字$数字$内部类的名字
 * 3.匿名类：外部类的名字$数字<br/>
 * Date:     2015-3-23 上午9:57:32 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class A{
    static class B{}//成员内部类 对应A$B.class
    class C{        //成员内部类对应A$C.class
         class D{}  //成员内部类对应的A$C$D.class
    }
    public void method1(){
        class E{}//局部内部类1，对应A$1$E.class
        B b=new B(){};//匿名类1，对应A$1.class
        C c=new C(){};//匿名类2，对应A$2.class
    }
    public void method2(){
        class E{}//局部内部类2 对应A$2$E.class
    }
}
public class InnerClazzNameSpace {

}

