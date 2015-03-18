/**
 * Project Name:java-basic
 * File Name:ClassInit.java
 * Package Name:com.easyway.java.basic.clazz.lifecycle
 * Date:2015-3-17下午5:05:38
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.clazz.lifecycle;

/**
 * ClassName:类的初始化时机 <br/>
 * Function: java虚拟机只有在程序首次主动使用一个类或者接口时才会初始化它 1.创建类的实例。创建类的途径：new，反射，克隆，反序列化
 * 2.调用类的静态方法 3.访问某一个类或者接口的静态变量，或者对该静态变量赋值。 4.调用java api中某一些反射方法。Class.forName
 * 5.初始化一个类的子类。 6.java虚拟机启动时候被表明为启动类的类。<br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-17 下午5:05:38 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class InitConstant {
    /**
     * 1.对于final类型的静态变量，如果在编译时就能计算出变量的取值，那么这种变量被看作编译时候的常量。
     * java程序中对类编译时候常量的使用，被看作是对类的被动使用，不会导致类的初始化。
     * 
     * 当java虚拟机加载并连接InitConstant类时候，不会在方法区内为它的编译时常量a分配内存。
     */
    public static final int a = 2 * 3;//
    static {
        System.out.println("static init InitConstant...");
    }
}


class InitMethod {
    /**
     * 2.对于final类型的静态变量，如果在编译时不能计算出变量的取值，那么程序对类的这种变量使用， 被看做是对类的主动使用，会导致类的初始化。
     */
    public static final int a = (int) (Math.random() * 10) / 10 + 1;//
    static {
        System.out.println("static init InitMethod...");
    }
}


/**
 * 
 * ClassName:3. 当java虚拟机初始化一个类时候，要求他的所有父类都已经被初始化，但是这条规则并不适用于接口。
 * 在初始化一个类时，并不会先初始化它所实现的接口。 在初始化一个接口时，并不会先初始化它的父接口。
 * 一个父接口并不会因为它的子接口或者实现类的初始化而初始化，只有当程序首次使用特定接口的静态变量时候，才会导致该接口的初始化。 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.6
 */
interface BaseInterface {
    public static final int a = 3 * 5;
    public static final int c = (int) (Math.random() * 10) / 10 + 1;//
}


interface SubInterface extends BaseInterface {
    public static final int b = 3 * 5;
}


class ClassInterface implements SubInterface {
    static {
        System.out.println("ClassInterface.init.....");
    }
}


/**
 * 4.只有程序访问的静态变量或者静态方法的确在当前或接口中定义时候，才可看作是对类或者接口的主动使用。
 * 
 * @author Administrator
 * @version
 * @since JDK 1.6
 */
class IBase {
    static int a = 1;
    static {
        System.out.println("init base...");
    };


    static void method() {
        System.out.println("init method of base...");
    }
}
class ISub extends IBase {
    static {
        System.out.println("init sub...");
    }
}
/**
 * 1.调用CLassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 *
 * @author Administrator
 * @version 
 * @since JDK 1.6
 */
class ClassA{
    static{
        System.out.println("init ClassA ....");
    }
}

public class ClassInit {
    public static void main(String[] args) {
        System.out.println("InitConstant.a=" + InitConstant.a);
        System.out.println("InitMethod.a=" + InitMethod.a);
        System.out.println("ClassInterface.c=" + ClassInterface.c);

        // 静态变量a和静态方法method在base父类中定义，因此java虚拟机仅仅初始化base类，而没有初始化Sub类。
        System.out.println("a=" + ISub.a);
        ISub.method();
        String className="com.easyway.java.basic.clazz.lifecycle.ClassA";
        ClassLoader loader=ClassLoader.getSystemClassLoader();//获得系统类加载器
        try {
            Class<?> objClass = loader.loadClass(className);//加载ClassA
            System.out.println("after load ClassA");
            System.out.println("before load ClassA");
            objClass=Class.forName(className);//初始化ClassA
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

}
