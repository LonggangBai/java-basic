/**
 * Project Name:java-basic
 * File Name:Outer.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-19下午3:23:56
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.innerclazz;


/**
 * ClassName:Outer <br/>
 * Function:实例内部类特点：
 * 1.在创建实例内部类的实例时候，外部类的实例必须已经存在。
 * 2.实例内部类的实力自动持有外部类的实例的引用。在内部类中可以直接访问外部类的所有的成员，包括成员变量和成员方法。
 *  在多重嵌套中，内部类可以访问所有外部类的成员。
 * 3.外部类实例与内部类实例之间是一对多的关系，一个内部类实例只会引用一个外部类的实例，而一个外部类实例对应多个
 * 内部类实例。外部类中不能直接访问内部类的成员，必须通过内部类的实例去访问。
 * 4.在实例内部类中不能定义静态成员，而只能定义实例成员。
 *  <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午3:23:56 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class OuterClazz {
    private InnerTools tool=new InnerTools();
    int  v=1;
    private void methodA(){
        System.out.println("OuterClazz.methodA");
    }
    /**
     * 
     * ClassName: InnerTools <br/>
     * Function: 成员内部类可以分为两种：实例内部类和静态内部类。 <br/>
     */
    public class InnerTools{
        int v=2;
        private void methodB(){
            System.out.println("InnerTools.methodB");
        }
        public int add(int a ,int b)
        {
            return a+b;
        }
        class ThirdC{
            private String v="V";

            public void methodC(){
                methodA();
                methodB();
                System.out.println("ThirdC.methodC");
                System.out.println("OuterClazz.this.v="+OuterClazz.this.v);
                System.out.println("v="+v);
                System.out.println("this.v="+this.v);
                System.out.println("InnerTools.this.v="+InnerTools.this.v);
                System.out.println("InnerTools.this.v="+InnerTools.this.v);
                System.out.println("OuterClazz.InnerTools.this.v="+OuterClazz.InnerTools.ThirdC.this.v);
            } 
        }
    }
    public int add(int a ,int b,int c)
    {
        return tool.add(tool.add(a, b),c);
    }
}
public class InstanceClazz{
    public static void main(String[] args) {
        OuterClazz o=new OuterClazz();
        o.add(1,2,3);
        OuterClazz.InnerTools tools=new com.easyway.java.basic.clazz.innerclazz.OuterClazz().new InnerTools();
        OuterClazz.InnerTools.ThirdC third=tools.new ThirdC();
        third.methodC();
    }
}

