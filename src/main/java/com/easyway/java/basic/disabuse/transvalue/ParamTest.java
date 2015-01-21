/**
 * Project Name:java-basic
 * File Name:ParamTest.java
 * Package Name:com.easyway.java.basic.disabuse.transvalue
 * Date:2015-1-21下午1:16:58
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.transvalue;

/**
 * 
 * JAVA中的传递都是值传递吗？有没有引用传递呢？
 * 
 * <pre>
 * 在回答这两个问题前，让我们首先来看一段代码： 
 * 这段代码的运行结果如下： 
 * 参数--基本类型
 * 原有的值：0
 * 赋值之后：0
 * 运算之后：0
 * 参数--引用类型
 * 原有的值：0
 * 赋引用后：0
 * 改属性后：20
 * 
 * 从上面这个直观的结果中我们很容易得出如下结论： 
 * 对于基本类型，在方法体内对方法参数进行重新赋值，并不会改变原有变量的值。
 * 对于引用类型，在方法体内对方法参数进行重新赋予引用，并不会改变原有变量所持有的引用。
 * 方法体内对参数进行运算，不影响原有变量的值。
 * 方法体内对参数所指向对象的属性进行运算，将改变原有变量所指向对象的属性值。
 * 
 * 上面总结出来的不过是我们所看到的表面现象。那么，为什么会出现这样的现象呢？这就要说到值传递和引用传递的概念了。这个问题向来是颇有争议的。 
 * 
 * 大家都知道，在JAVA中变量有以下两种： 
 * 基本类型变量，包括char、byte、short、int、long、float、double、boolean。
 * 引用类型变量，包括类、接口、数组（基本类型数组和对象数组）。
 * 
 * 当基本类型的变量被当作参数传递给方法时，JAVA虚拟机所做的工作是把这个值拷贝了一份，然后把拷贝后的值传递到了方法的内部。因此在上面的例子中，我们回头来看看这个方法： 
 * Java代码 复制代码
 * // 为方法参数重新赋值   
 * public void change(int i) {   
 *     i = 5;   
 * }  
 * 
 * 在这个方法被调用时，变量i和ParamTest型对象t的属性num具有相同的值，却是两个不同变量。变量i是由JAVA虚拟机创建的作用域在change(int i)方法内的局部变量，在这个方法执行完毕后，它的生命周期就结束了。在JAVA虚拟机中，它们是以类似如下的方式存储的：
 * </pre>
 * 
 * ClassName:ParamTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:16:58 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ParamTest {
    // 初始值为0
    protected int num = 0;


    // 为方法参数重新赋值
    public void change(int i) {
        i = 5;
    }


    // 为方法参数重新赋值
    public void change(ParamTest t) {
        ParamTest tmp = new ParamTest();
        tmp.num = 9;
        t = tmp;
    }


    // 改变方法参数的值
    public void add(int i) {
        i += 10;
    }


    // 改变方法参数属性的值
    public void add(ParamTest pt) {
        pt.num += 20;
    }


    public static void main(String[] args) {
        ParamTest t = new ParamTest();

        System.out.println("参数--基本类型");
        System.out.println("原有的值：" + t.num);
        // 为基本类型参数重新赋值
        t.change(t.num);
        System.out.println("赋值之后：" + t.num);
        // 为引用型参数重新赋值
        t.change(t);
        System.out.println("运算之后：" + t.num);

        System.out.println();

        t = new ParamTest();
        System.out.println("参数--引用类型");
        System.out.println("原有的值：" + t.num);
        // 改变基本类型参数的值
        t.add(t.num);
        System.out.println("赋引用后：" + t.num);
        // 改变引用类型参数所指向对象的属性值
        t.add(t);
        System.out.println("改属性后：" + t.num);
    }
}
