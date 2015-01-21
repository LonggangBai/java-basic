/**
 * Project Name:java-basic
 * File Name:FinalSubClass.java
 * Package Name:com.easyway.java.basic.disabuse.finalize
 * Date:2015-1-21下午1:10:17
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.finalize;

/**
 * <pre>
 * 这里需要特殊说明的是，具有private访问权限的方法也可以增加final修饰，但是由于子类无法继承private方法，因此也无法重写它。编译器在处理private方法时，是按照final方法来对待的，这样可以提高该方法被调用时的效率。不过子类仍然可以定义同父类中的private方法具有同样结构的方法，但是这并不会产生重写的效果，而且它们之间也不存在必然联系。 
 * 
 * 最后我们再来回顾一下final用于类的情况。这个大家应该也很熟悉了，因为我们最常用的String类就是final的。由于final类不允许被继承，编译器在处理时把它的所有方法都当作final的，因此final类比普通类拥有更高的效率。而由关键字abstract定义的抽象类含有必须由继承自它的子类重载实现的抽象方法，因此无法同时用final和abstract来修饰同一个类。同样的道理，final也不能用来修饰接口。final的类的所有方法都不能被重写，但这并不表示final的类的属性（变量）值也是不可改变的，要想做到final类的属性值不可改变，必须给它增加final修饰，请看下面的例子：
 * 
 * 运行上面的代码试试看，结果是99，而不是初始化时的10。 
 * </pre>
 * 
 * ClassName:FinalSubClass <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:10:17 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class ParentClass {
    public final void TestFinal() {
        System.out.println("父类--这是一个final方法");
    }
}


public class FinalSubClass extends ParentClass {
    /**
     * 子类无法重写（override）父类的final方法，否则编译时会报错
     */
    // public void TestFinal() {
    // System.out.println("子类--重写final方法");
    // }

    public static void main(String[] args) {
        FinalSubClass sc = new FinalSubClass();
        sc.TestFinal();
    }
}
