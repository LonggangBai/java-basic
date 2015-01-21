/**
 * Project Name:java-basic
 * File Name:Overloading.java
 * Package Name:com.easyway.java.basic.disabuse.extendsoveride
 * Date:2015-1-21下午2:00:13
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.extendsoveride;

/**
 * 
 * <pre>
 * 重载(overloading)和重写(overriding) 
 * 
 * 重载和重写都是针对方法的概念，在弄清楚这两个概念之前，我们先来了解一下什么叫方法的型构（英文名是signature，有的译作“签名”，虽然它被使用的较为广泛，但是这个翻译不准确的）。型构就是指方法的组成结构，具体包括方法的名称和参数，涵盖参数的数量、类型以及出现的顺序，但是不包括方法的返回值类型，访问权限修饰符，以及abstract、static、final等修饰符。比如下面两个就是具有相同型构的方法： 
 * Java代码 复制代码
 * public void method(int i, String s) {   
 *     // do something   
 * }   
 *   
 * public String method(int i, String s) {   
 *     // do something   
 * }  
 * 
 * 而这两个就是具有不同型构的方法： 
 * Java代码 复制代码
 * public void method(int i, String s) {   
 *     // do something   
 * }   
 *   
 * public void method(String s, int i) {   
 *     // do something   
 * }  
 * 
 * 了解完型构的概念后我们再来看看重载和重写，请看它们的定义： 
 * 重写，英文名是overriding，是指在继承情况下，子类中定义了与其基类中方法具有相同型构的新方法，就叫做子类把基类的方法重写了。这是实现多态必须的步骤。
 * 重载，英文名是overloading，是指在同一个类中定义了一个以上具有相同名称，但是型构不同的方法。在同一个类中，是不允许定义多于一个的具有相同型构的方法的。
 * 
 * 我们来考虑一个有趣的问题：构造器可以被重载吗？答案当然是可以的，我们在实际的编程中也经常这么做。实际上构造器也是一个方法，构造器名就是方法名，构造器参数就是方法参数，而它的返回值就是新创建的类的实例。但是构造器却不可以被子类重写，因为子类无法定义与基类具有相同型构的构造器。
 * </pre>
 * 
 * ClassName:Overloading <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:00:13 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class Overloading {
    public void method(int i, String s) {
        // do something
    }


    public String method(String s, int i) {
        // do something
        return s;
    }
}
