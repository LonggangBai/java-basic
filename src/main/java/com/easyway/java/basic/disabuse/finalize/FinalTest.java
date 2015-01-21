/**
 * Project Name:java-basic
 * File Name:FinalTest.java
 * Package Name:com.easyway.java.basic.disabuse.finalize
 * Date:2015-1-21下午1:08:17
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.finalize;

/**
 * 
 * <pre>
 * final、finally和finalize的区别是什么？ 
 * 
 * 这是一道再经典不过的面试题了，我们在各个公司的面试题中几乎都能看到它的身影。final、finally和finalize虽然长得像孪生三兄弟一样，但是它们的含义和用法却是大相径庭。这一次我们就一起来回顾一下这方面的知识。 
 * 
 * final关键字 
 * 
 * 我们首先来说说final。它可以用于以下四个地方： 
 * 定义变量，包括静态的和非静态的。
 * 定义方法的参数。
 * 定义方法。
 * 定义类。
 * 
 * 我们依次来回顾一下每种情况下final的作用。首先来看第一种情况，如果final修饰的是一个基本类型，就表示这个变量被赋予的值是不可变的，即它是个常量；如果final修饰的是一个对象，就表示这个变量被赋予的引用是不可变的，这里需要提醒大家注意的是，不可改变的只是这个变量所保存的引用，并不是这个引用所指向的对象。在第二种情况下，final的含义与第一种情况相同。实际上对于前两种情况，有一种更贴切的表述final的含义的描述，那就是，如果一个变量或方法参数被final修饰，就表示它只能被赋值一次，但是JAVA虚拟机为变量设定的默认值不记作一次赋值。 
 * 
 * 被final修饰的变量必须被初始化。初始化的方式有以下几种： 
 * 在定义的时候初始化。
 * final变量可以在初始化块中初始化，不可以在静态初始化块中初始化。
 * 静态final变量可以在静态初始化块中初始化，不可以在初始化块中初始化。
 * final变量还可以在类的构造器中初始化，但是静态final变量不可以。
 * 
 * 通过下面的代码可以验证以上的观点：
 * 
 * 
 * 
 * 我们运行上面的代码之后出了可以发现final变量（常量）和静态final变量（静态常量）未被初始化时，编译会报错。 
 * 
 * 用final修饰的变量（常量）比非final的变量（普通变量）拥有更高的效率，因此我们在实际编程中应该尽可能多的用常量来代替普通变量，这也是一个很好的编程习惯。 
 * 
 * 当final用来定义一个方法时，会有什么效果呢？正如大家所知，它表示这个方法不可以被子类重写，但是它这不影响它被子类继承。我们写段代码来验证一下：
 * </pre>
 * 
 * ClassName:FinalTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:08:17 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class FinalTest {
    // 在定义时初始化
    public final int A = 10;

    public final int B;
    // 在初始化块中初始化
    {
        B = 20;
    }

    // 非静态final变量不能在静态初始化块中初始化
    // public final int C;
    // static {
    // C = 30;
    // }

    // 静态常量，在定义时初始化
    public static final int STATIC_D = 40;

    public static final int STATIC_E;
    // 静态常量，在静态初始化块中初始化
    static {
        STATIC_E = 50;
    }

    // 静态变量不能在初始化块中初始化
    // public static final int STATIC_F;
    // {
    // STATIC_F = 60;
    // }

    public final int G;


    // 静态final变量不可以在构造器中初始化
    // public static final int STATIC_H;

    // 在构造器中初始化
    public FinalTest() {
        G = 70;
        // 静态final变量不可以在构造器中初始化
        // STATIC_H = 80;

        // 给final的变量第二次赋值时，编译会报错
        // A = 99;
        // STATIC_D = 99;
    }

    // final变量未被初始化，编译时就会报错
    // public final int I;

    // 静态final变量未被初始化，编译时就会报错
    // public static final int STATIC_J;
}
