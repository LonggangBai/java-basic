package com.easyway.java.basic.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 我们知道Spring通过各种DAO模板类降低了开发者使用各种数据持久技术的难度。这些模板类都是线程安全的，也就是说，多个DAO可以复用同一个模板实例而不会发生冲突。
 *  我们使用模板类访问底层数据，根据持久化技术的不同，模板类需要绑定数据连接或会话的资源。但这些资源本身是非线程安全的，也就是说它们不能在同一时刻被多个线程共享。
 *  虽然模板类通过资源池获取数据连接或会话，但资源池本身解决的是数据连接或会话的缓存问题，并非数据连接或会话的线程安全问题。
 *  按照传统经验，如果某个对象是非线程安全的，在多线程环境下，对对象的访问必须采用synchronized进行线程同步。但Spring的DAO模板类并未采用线程同步机制，因为线程同步限制了并发访问，会带来很大的性能损失。
 *  此外，通过代码同步解决性能安全问题挑战性很大，可能会增强好几倍的实现难度。那模板类究竟仰丈何种魔法神功，可以在无需同步的情况下就化解线程安全的难题呢？答案就是ThreadLocal！
 *  ThreadLocal在Spring中发挥着重要的作用，在管理request作用域的Bean、事务管理、任务调度、AOP等模块都出现了它们的身影，起着举足轻重的作用。要想了解Spring事务管理的底层技术，ThreadLocal是必须攻克的山头堡垒。
 *  ThreadLocal是什么
 *  早在JDK1.2的版本中就提供java.lang.ThreadLocal，ThreadLocal为解决多线程程序的并发问题提供了一种新的思路。使用这个工具类可以很简洁地编写出优美的多线程程序。
 *  ThreadLocal很容易让人望文生义，想当然地认为是一个“本地线程”。其实，ThreadLocal并不是一个Thread，而是Thread的局部变量，也许把它命名为ThreadLocalVariable更容易让人理解一些。
 *  当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 *  从线程的角度看，目标变量就象是线程的本地变量，这也是类名中“Local”所要表达的意思。
 *  线程局部变量并不是Java的新发明，很多语言（如IBM IBM XLFORTRAN）在语法层面就提供线程局部变量。在Java中没有提供在语言级支持，而是变相地通过ThreadLocal的类提供支持。
 *  所以，在Java中编写线程局部变量的代码相对来说要笨拙一些，因此造成线程局部变量没有在Java开发者中得到很好的普及。
 *  ThreadLocal的接口方法
 *  ThreadLocal类接口很简单，只有4个方法，我们先来了解一下：
 *  void set(Object value)
 *  设置当前线程的线程局部变量的值。
 *  public Object get()
 *  该方法返回当前线程所对应的线程局部变量。
 *  public void remove()
 *  将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
 *  protected Object initialValue()
 *  返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。
 *  
 * 
 * 值得一提的是，在JDK5.0中，ThreadLocal已经支持泛型，该类的类名已经变为ThreadLocal<T>。API方法也相应进行了调整，新版本的API方法分别是voidset(T value)、T get()以及T initialValue()。
 *  ThreadLocal是如何做到为每一个线程维护变量的副本的呢？其实实现的思路很简单：在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本。我们自己就可以提供一个简单的实现版本：
 * 
 * 


 * </pre>
 * 
 * Spring如何处理线程并发
 * 
 * @author Administrator
 * 
 */
public class SimpleThreadLocal {
	private Map<Object, Object> valueMap = Collections
			.synchronizedMap(new HashMap<Object, Object>());

	public void set(Object newValue) {
		valueMap.put(Thread.currentThread(), newValue);// ①键为线程对象，值为本线程的变量副本
	}

	public Object get() {
		Thread currentThread = Thread.currentThread();
		Object o = valueMap.get(currentThread);// ②返回本线程对应的变量
		if (o == null && !valueMap.containsKey(currentThread)) {// ③如果在Map中不存在，放到Map
			// 中保存起来。
			o = initialValue();
			valueMap.put(currentThread, o);
		}
		return o;
	}

	public void remove() {
		valueMap.remove(Thread.currentThread());
	}

	public Object initialValue() {
		return null;
	}
}