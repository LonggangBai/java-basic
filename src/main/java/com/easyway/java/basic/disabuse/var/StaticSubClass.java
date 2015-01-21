/**
 * Project Name:java-basic
 * File Name:StaticSubClass.java
 * Package Name:com.easyway.java.basic.disabuse.var
 * Date:2015-1-21下午12:50:40
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.var;

/**
 * JAVA面试题解惑系列（三）——变量（属性）的覆盖
 * <pre>
 * 运行结果如下： 
 * 子类静态变量
 * 子类常量
 * 子类静态常量
 * 
 * 虽然上面的结果中包含“子类静态变量”和“子类静态常量”，但这并不表示父类的“静态变量”和“静态常量”可以被子类覆盖，因为它们都是属于类，而不属于对象。 
 * 
 * 上面的例子中，我们一直用对象来对变量（属性）的覆盖做测试，如果是基本类型的变量，结果是否会相同呢？答案是肯定的，这里我们就不再一一举例说明了。 
 * 
 * 最后，我们来做个总结。通过以上测试，可以得出一下结论： 
 * 由于private变量受访问权限的限制，它不能被覆盖。
 * 属性的值取父类还是子类并不取决于我们创建对象的类型，而是取决于我们定义的变量的类型。
 * friendly、protected和public修饰符并不影响属性的覆盖。
 * 静态变量和静态常量属于类，不属于对象，因此它们不能被覆盖。
 * 常量可以被覆盖。
 * 对于基本类型和对象，它们适用同样的覆盖规律。
 * 
 * 我们再回到篇首的那道题，我想大家都已经知道答案了，输出结果应该是40。
 * </pre>
 * 
 * ClassName:StaticSubClass <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:50:40 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class StaticParentClass {
    public static String staticField = "父类静态变量";

    public final String finalField = "父类常量";

    public static final String staticFinalField = "父类静态常量";
}


public class StaticSubClass extends StaticParentClass {
    public static String staticField = "子类静态变量";

    public final String finalField = "子类常量";

    public static final String staticFinalField = "子类静态常量";


    public static void main(String[] args) {
        StaticSubClass subClass = new StaticSubClass();
        System.out.println(StaticSubClass.staticField);
        // 注意，这里的subClass变量，不是SubClass类
        System.out.println(subClass.finalField);
        System.out.println(StaticSubClass.staticFinalField);
    }
}
