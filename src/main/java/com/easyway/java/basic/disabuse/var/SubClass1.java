/**
 * Project Name:java-basic
 * File Name:SubClass1.java
 * Package Name:com.easyway.java.basic.disabuse.var
 * Date:2015-1-21下午12:49:27
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.var;

/**
 * JAVA面试题解惑系列（三）——变量（属性）的覆盖
 * <pre>
 * 运行结果： 
 * 子类变量
 * 
 * 上面两段不同的代码，输出结果确是相同的。事实上，我们可以将父类和子类属性前的访问修饰符在friendly、protected和public之间任意切换，
 * 得到的结果都是相同的。也就是说访问修饰符并不影响属性的覆盖，关于这一点大家可以自行编写测试代码验证。
 * </pre>
 * 
 * ClassName:SubClass1 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:49:27 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class ParentClass1 {
    public String field = "父类变量";
}


public class SubClass1 extends ParentClass1 {
    protected String field = "子类变量";


    public static void main(String[] args) {
        SubClass1 subClass = new SubClass1();
        System.out.println(subClass.field);
    }
}