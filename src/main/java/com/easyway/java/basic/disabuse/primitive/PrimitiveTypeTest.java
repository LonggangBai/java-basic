/**
 * Project Name:java-basic
 * File Name:PrimitiveTypeTest.java
 * Package Name:com.easyway.java.basic.disabuse.primitive
 * Date:2015-1-21下午1:43:19
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.primitive;

/**
 * 
 * <pre>
 * 运行结果： 
 * -7616
 * 
 * 运算符对基本类型的影响 
 * 
 * 当使用+、-、*、/、%运算符对基本类型进行运算时，遵循如下规则： 
 * 只要两个操作数中有一个是double类型的，另一个将会被转换成double类型，并且结果也是double类型；
 * 否则，只要两个操作数中有一个是float类型的，另一个将会被转换成float类型，并且结果也是float类型；
 * 否则，只要两个操作数中有一个是long类型的，另一个将会被转换成long类型，并且结果也是long类型；
 * 否则，两个操作数（包括byte、short、int、char）都将会被转换成int类型，并且结果也是int类型。
 * 
 * 当使用+=、-=、*=、/=、%=、运算符对基本类型进行运算时，遵循如下规则： 
 * 运算符右边的数值将首先被强制转换成与运算符左边数值相同的类型，然后再执行运算，且运算结果与运算符左边数值类型相同。
 * 
 * 了解了这些，我们就能解答下面这个常考的面试题了。请看： 
 * 引用
 * short s1=1;s1=s1+1;有什么错？short s1=1;s1+=1;有什么错？
 * 
 * 乍一看，觉得它们都应该没有错误，可以正常运行。我们来写个例子试试：
 * 
 * 
 * 从例子中我们可以看出结果了。利用上面列举的规律，也很容易解释。在s1=s1+1;中，s1+1运算的结果是int型，把它赋值给一个short型变量s1，所以会报错；而在s1+=1;中，由于是s1是short类型的，所以1首先被强制转换为short型，然后再参与运算，并且结果也是short类型的，因此不会报错。那么，s1=1+1;为什么不报错呢？这是因为1+1是个编译时可以确定的常量，“+”运算在编译时就被执行了，而不是在程序执行的时候，这个语句的效果等同于s1=2，所以不会报错。前面讲过了，对基本类型执行强制类型转换可能得出错误的结果，因此在使用+=、-=、*=、/=、%=等运算符时，要多加注意。 
 * 
 * 当使用“==”运算符在基本类型和其包装类对象之间比较时，遵循如下规则： 
 * 只要两个操作数中有一个是基本类型，就是比较它们的数值是否相等。
 * 否则，就是判断这两个对象的内存地址是否相等，即是否是同一个对象。
 * </pre>
 * 
 * ClassName:PrimitiveTypeTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:43:19 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class PrimitiveTypeTest {
    public static void main(String[] args) {
        short s1 = 1;
        // 这一行代码会报编译错误
        // s1 = s1 + 1;
        // 这一行代码没有报错
        s1 = 1 + 1;
        // 这一行代码也没有报错
        s1 += 1;
    }
}
