/**
 * Project Name:java-basic
 * File Name:Task.java
 * Package Name:com.easyway.java.basic.disabuse.threads
 * Date:2015-1-21下午2:05:26
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.threads;

/**
 * 
 * <pre>
 * 线程池 
 * 
 * 线程池就像数据库连接池一样，是一个对象池。所有的对象池都有一个共同的目的，那就是为了提高对象的使用率，
 * 从而达到提高程序效率的目的。比如对于Servlet，它被设计为多线程的（如果它是单线程的，你就可以想象，
 * 当1000个人同时请求一个网页时，在第一个人获得请求结果之前，其它999个人都在郁闷地等待），如果为每个
 * 用户的每一次请求都创建一个新的线程对象来运行的话，系统就会在创建线程和销毁线程上耗费很大的开销，大大
 * 降低系统的效率。因此，Servlet多线程机制背后有一个线程池在支持，线程池在初始化初期就创建了一定数量
 * 的线程对象，通过提高对这些对象的利用率，避免高频率地创建对象，从而达到提高程序的效率的目的。 
 * 
 * 下面实现一个最简单的线程池，从中理解它的实现原理。为此我们定义了四个类，它们的用途及具体实现如下： 
 * Task（任务）：这是个代表任务的抽象类，其中定义了一个deal()方法，继承Task抽象类的子类需要实现这
 * 个方法，并把这个任务需要完成的具体工作在deal()方法编码实现。线程池中的线程之所以被创建，就是为了
 * 执行各种各样数量繁多的任务的，为了方便线程对任务的处理，我们需要用Task抽象类来保证任务的具体工作
 * 统一放在deal()方法里来完成，这样也使代码更加规范。
 * </pre>
 * 
 * ClassName:Task <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:05:26 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public abstract class Task {
    public enum State {
        /* 新建 */NEW, /* 执行中 */
        RUNNING, /* 已完成 */
        FINISHED
    }

    // 任务状态
    private State state = State.NEW;


    public void setState(State state) {
        this.state = state;
    }


    public State getState() {
        return state;
    }


    public abstract void deal();
}