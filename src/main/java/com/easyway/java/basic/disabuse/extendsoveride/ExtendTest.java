/**
 * Project Name:java-basic
 * File Name:ExtendTest.java
 * Package Name:com.easyway.java.basic.disabuse.extendsoveride
 * Date:2015-1-21下午1:56:04
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.extendsoveride;

/**
 * <pre>
 * 什么是多态？它的实现机制是什么呢？重载和重写的区别在那里？这就是这一次我们要回顾的四个十分重要的概念：继承、多态、重载和重写。 
 * 
 * 继承(inheritance) 
 * 
 * 简单的说，继承就是在一个现有类型的基础上，通过增加新的方法或者重定义已有方法（下面会讲到，这种方式叫重写）的方式，产生一个新的类型。继承是面向对象的三个基本特征--封装、继承、多态的其中之一，我们在使用JAVA时编写的每一个类都是在继承，因为在JAVA语言中，java.lang.Object类是所有类最根本的基类（或者叫父类、超类），如果我们新定义的一个类没有明确地指定继承自哪个基类，那么JAVA就会默认为它是继承自Object类的。 
 * 
 * 我们可以把JAVA中的类分为以下三种： 
 * 类：使用class定义且不含有抽象方法的类。
 * 抽象类：使用abstract class定义的类，它可以含有，也可以不含有抽象方法。
 * 接口：使用interface定义的类。
 * 
 * 在这三种类型之间存在下面的继承规律： 
 * 类可以继承（extends）类，可以继承（extends）抽象类，可以继承（implements）接口。
 * 抽象类可以继承（extends）类，可以继承（extends）抽象类，可以继承（implements）接口。
 * 接口只能继承（extends）接口。
 * 
 * 请注意上面三条规律中每种继承情况下使用的不同的关键字extends和implements，它们是不可以随意替换的。大家知道，一个普通类继承一个接口后，必须实现这个接口中定义的所有方法，否则就只能被定义为抽象类。我在这里之所以没有对implements关键字使用“实现”这种说法是因为从概念上来说它也是表示一种继承关系，而且对于抽象类implements接口的情况下，它并不是一定要实现这个接口定义的任何方法，因此使用继承的说法更为合理一些。 
 * 
 * 以上三条规律同时遵守下面这些约束： 
 * 类和抽象类都只能最多继承一个类，或者最多继承一个抽象类，并且这两种情况是互斥的，也就是说它们要么继承一个类，要么继承一个抽象类。
 * 类、抽象类和接口在继承接口时，不受数量的约束，理论上可以继承无限多个接口。当然，对于类来说，它必须实现它所继承的所有接口中定义的全部方法。
 * 抽象类继承抽象类，或者实现接口时，可以部分、全部或者完全不实现父类抽象类的抽象（abstract）方法，或者父类接口中定义的接口。
 * 类继承抽象类，或者实现接口时，必须全部实现父类抽象类的全部抽象（abstract）方法，或者父类接口中定义的全部接口。
 * 
 * 继承给我们的编程带来的好处就是对原有类的复用（重用）。就像模块的复用一样，类的复用可以提高我们的开发效率，实际上，模块的复用是大量类的复用叠加后的效果。除了继承之外，我们还可以使用组合的方式来复用类。所谓组合就是把原有类定义为新类的一个属性，通过在新类中调用原有类的方法来实现复用。如果新定义的类型与原有类型之间不存在被包含的关系，也就是说，从抽象概念上来讲，新定义类型所代表的事物并不是原有类型所代表事物的一种，比如黄种人是人类的一种，它们之间存在包含与被包含的关系，那么这时组合就是实现复用更好的选择。下面这个例子就是组合方式的一个简单示例： 
 * 
 * 
 * 当然，为了使代码更加有效，我们也可以在需要使用到原有类型（比如Parent p）时，才对它进行初始化。 
 * 
 * 使用继承和组合复用原有的类，都是一种增量式的开发模式，这种方式带来的好处是不需要修改原有的代码，因此不会给原有代码带来新的BUG，也不用因为对原有代码的修改而重新进行测试，这对我们的开发显然是有益的。因此，如果我们是在维护或者改造一个原有的系统或模块，尤其是对它们的了解不是很透彻的时候，就可以选择增量开发的模式，这不仅可以大大提高我们的开发效率，也可以规避由于对原有代码的修改而带来的风险。
 * </pre>
 * 
 * ClassName:ExtendTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:56:04 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ExtendTest {
    public static void main(String[] args) {
        Sub b = new Sub();
        b.doSomething();
    }

}


class Sub {
    private Parent p = new Parent();


    public void doSomething() {
        // 复用Parent类的方法
        p.method();
        // other code
    }
}


class Parent {
    public void method() {
        // do something here
    }
}
