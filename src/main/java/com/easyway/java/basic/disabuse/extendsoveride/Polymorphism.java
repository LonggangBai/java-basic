/**
 * Project Name:java-basic
 * File Name:Polymorphism.java
 * Package Name:com.easyway.java.basic.disabuse.extendsoveride
 * Date:2015-1-21下午1:57:44
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.extendsoveride;

/**
 * <pre>
 * 
 * 多态(Polymorphism) 
 * 
 * 多态是又一个重要的基本概念，上面说到了，它是面向对象的三个基本特征之一。究竟什么是多态呢？我们先看看下面的例子，来帮助理解： 
 * 运行结果： 
 * 车型：BMW 单价：300000
 * 车型：CheryQQ 单价：20000
 * 总收入：320000
 * 
 * 继承是多态得以实现的基础。从字面上理解，多态就是一种类型（都是Car类型）表现出多种状态（宝马汽车的名称是BMW，
 * 售价是300000；
 * 奇瑞汽车的名称是CheryQQ，售价是2000）。将一个方法调用同这个方法所属的主体（也就是对象或类）关联起来叫做绑定，分前期绑定和后期绑定两种。
 * 下面解释一下它们的定义： 
 * 前期绑定：在程序运行之前进行绑定，由编译器和连接程序实现，又叫做静态绑定。比如static方法和final方法，注意，这里也包括private方法，
 * 因为它是隐式final的。
 * 后期绑定：在运行时根据对象的类型进行绑定，由方法调用机制实现，因此又叫做动态绑定，或者运行时绑定。除了前期绑定外的所有方法都属于后期绑定。
 * 
 * 多态就是在后期绑定这种机制上实现的。多态给我们带来的好处是消除了类之间的耦合关系，使程序更容易扩展。比如在上例中，新增加一种类型汽车的销售，
 * 只需要让新定义的类继承Car类并实现它的所有方法，而无需对原有代码做任何修改，CarShop类的sellCar(Car car)方法就可以处理新的车型了。
 * 新增代码如下：
 * </pre>
 * 
 * ClassName:Polymorphism <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:57:44 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class Polymorphism {
    public static void main(String[] args) {
        CarShop aShop = new CarShop();
        // 卖出一辆宝马
        aShop.sellCar(new BMW());
        // 卖出一辆奇瑞QQ
        aShop.sellCar(new CheryQQ());
        System.out.println("总收入：" + aShop.getMoney());
    }
}


interface Car {
    // 汽车名称
    String getName();


    // 获得汽车售价
    int getPrice();
}


// 宝马
class BMW implements Car {
    public String getName() {
        return "BMW";
    }


    public int getPrice() {
        return 300000;
    }
}


// 奇瑞QQ
class CheryQQ implements Car {
    public String getName() {
        return "CheryQQ";
    }


    public int getPrice() {
        return 20000;
    }
}


// 桑塔纳汽车
class Santana implements Car {
    public String getName() {
        return "Santana";
    }


    public int getPrice() {
        return 80000;
    }
}


// 汽车出售店
class CarShop {
    // 售车收入
    private int money = 0;


    // 卖出一部车
    public void sellCar(Car car) {
        System.out.println("车型：" + car.getName() + "  单价：" + car.getPrice());
        // 增加卖出车售价的收入
        money += car.getPrice();
    }


    // 售车总收入
    public int getMoney() {
        return money;
    }

}