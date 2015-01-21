/**
 * Project Name:java-basic
 * File Name:BooleanTest.java
 * Package Name:com.easyway.java.basic.disabuse.transvalue
 * Date:2015-1-21下午1:18:26
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.transvalue;

/**
 * 
 * <pre>
 * 很明显，在基本类型被作为参数传递给方式时，是值传递，在整个过程中根本没有牵扯到引用这个概念。这也是大家所公认的。对于布尔型变量当然也是如此，请看下面的例子： 
 * 输出结果如下： 
 * 参数--布尔型
 * 原有的值：true
 * 赋值之后：true
 * b运算后的值：false
 * 运算之后：true
 * 
 * 那么当引用型变量被当作参数传递给方法时JAVA虚拟机又是怎样处理的呢？同样，它会拷贝一份这个变量所持有的引用，然后把它传递给JAVA虚拟机为方法创建的局部变量，从而这两个变量指向了同一个对象。在篇首所举的示例中，ParamTest类型变量t和局部变量pt在JAVA虚拟机中是以如下的方式存储的： 
 * 
 * 
 * 有一种说法是当一个对象或引用类型变量被当作参数传递时，也是值传递，这个值就是对象的引用，因此JAVA中只有值传递，没有引用传递。还有一种说法是引用可以看作是对象的别名，当对象被当作参数传递给方法时，传递的是对象的引用，因此是引用传递。这两种观点各有支持者，但是前一种观点被绝大多数人所接受，其中有《Core Java》一书的作者，以及JAVA的创造者James Gosling，而《Thinking in Java》一书的作者Bruce Eckel则站在了中立的立场上。 
 * 
 * 我个人认为值传递中的值指的是基本类型的数值，即使对于布尔型，虽然它的表现形式为true和false，但是在栈中，它仍然是以数值形式保存的，即0表示false，其它数值表示true。而引用是我们用来操作对象的工具，它包含了对象在堆中保存地址的信息。即使在被作为参数传递给方法时，实际上传递的是它的拷贝，但那仍是引用。因此，用引用传递来区别与值传递，概念上更加清晰。 
 * 
 * 最后我们得出如下的结论： 
 * 基本类型和基本类型变量被当作参数传递给方法时，是值传递。在方法实体中，无法给原变量重新赋值，也无法改变它的值。
 * 对象和引用型变量被当作参数传递给方法时，在方法实体中，无法给原变量重新赋值，但是可以改变它所指向对象的属性。至于到底它是值传递还是引用传递，这并不重要，重要的是我们要清楚当一个引用被作为参数传递给一个方法时，在这个方法体内会发生什么。
 * </pre>
 * 
 * ClassName:BooleanTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:18:26 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class BooleanTest {
    // 布尔型值
    boolean bool = true;


    // 为布尔型参数重新赋值
    public void change(boolean b) {
        b = false;
    }


    // 对布尔型参数进行运算
    public void calculate(boolean b) {
        b = b && false;
        // 为了方便对比，将运算结果输出
        System.out.println("b运算后的值：" + b);
    }


    public static void main(String[] args) {
        BooleanTest t = new BooleanTest();

        System.out.println("参数--布尔型");
        System.out.println("原有的值：" + t.bool);
        // 为布尔型参数重新赋值
        t.change(t.bool);
        System.out.println("赋值之后：" + t.bool);

        // 改变布尔型参数的值
        t.calculate(t.bool);
        System.out.println("运算之后：" + t.bool);
    }
}