/**
 * 
 */
package com.easyway.java.basic.pattern.singleton;

/**
 * <pre>
 * 　　作为对象的创建模式，单例模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。这个类称为单例类。
 *  
 * --------------------------------------------------------------------------------
 * 
 * 单例模式的结构
 *  
 * 　　单例模式的特点：
 *      •单例类只能有一个实例。
 *      •单例类必须自己创建自己的唯一实例。
 *      •单例类必须给所有其他对象提供这一实例。
 *  
 * 　  饿汉式单例类
 * 上面的例子中，在这个类被加载时，静态变量instance会被初始化，此时类的私有构造子会被调用。这时候，单例类的唯一实例就被创建出来了。
 * 
 * 饿汉式其实是一种比较形象的称谓。既然饿，那么在创建对象实例的时候就比较着急，饿了嘛，于是在装载类的时候就创建对象实例。
 * 
 * private static EagerSingleton instance = new EagerSingleton();
 * 饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例，不管你用不用，先创建出来，然后每次调用的时候，就不需要再判断，节省了运行时间。
 * </pre>
 * 
 * ClassName: EagerSingleton <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-1-20 下午4:59:39 <br/>
 *
 * @author longgangbai
 * @version 
 * @since JDK 1.6
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    /**
     * 私有默认构造子
     */
    private EagerSingleton() {
    }

    /**
     * 静态工厂方法
     */
    public static EagerSingleton getInstance() {
	return instance;
    }
}