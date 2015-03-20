/**
 * Project Name:java-basic
 * File Name:LinkedStack.java
 * Package Name:com.easyway.java.basic.collection
 * Date:2015-3-20上午10:47:01
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection;

import java.util.concurrent.atomic.AtomicReference;


/**
 * 
 * <pre>
 * 一、何谓Atomic？
 *  
 *  Atomic一词跟原子有点关系，后者曾被人认为是最小物质的单位。计算机中的Atomic是指不能分割成若干部分的意思。如果一段代码被认为是Atomic，
 *  则表示这段代码在执行过程中，是不能被中断的。通常来说，原子指令由硬件提供，供软件来实现原子方法（某个线程进入该方法后，就不会被中断，直到其执行完成）
 *   
 *   在x86 平台上，CPU提供了在指令执行期间对总线加锁的手段。CPU芯片上有一条引线#HLOCK pin，如果汇编语言的程序中在一条指令前面加上前缀
 *   "LOCK"，经过汇编以后的机器代码就使CPU在执行这条指令的时候把#HLOCK pin的电位拉低，持续到这条指令结束时放开，从而把总线锁住，这样同
 *   一总线上别的CPU就暂时不能通过总线访问内存了，保证了这条指令在多处理器环境中的原子性。
 *   
 *  二、java.util.concurrent中的原子变量
 *  
 * 无论是直接的还是间接的，几乎 java.util.concurrent 包中的所有类都使用原子变量，而不使用同步。类似 ConcurrentLinkedQueue 的类也
 * 使用原子变量直接实现无等待算法，而类似 ConcurrentHashMap 的类使用 ReentrantLock 在需要时进行锁定。然后， ReentrantLock 使用
 * 原子变量来维护等待锁定的线程队列。
 *  如果没有 JDK 5.0 中的 JVM 改进，将无法构造这些类，这些改进暴露了（向类库，而不是用户类）接口来访问硬件级的同步原语。然后，
 *  java.util.concurrent 中的原子变量类和其他类向用户类公开这些功能
 *  java.util.concurrent.atomic的原子类
 *  
 * 这个包里面提供了一组原子类。其基本的特性就是在多线程环境下，当有多个线程同时执行这些类的实例包含的方法时，具有排他性，即当某个线程进入方法，
 * 执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，一直等到该方法执行完成，才由JVM从等待队列中选择一个另一个线程进入，这只是
 * 一种逻辑上的理解。实际上是借助硬件的相关指令来实现的，不会阻塞线程(或者说只是在硬件级别上阻塞了)。其中的类可以分成4组
 *  AtomicBoolean，AtomicInteger，AtomicLong，AtomicReference
 *  AtomicIntegerArray，AtomicLongArray
 *  AtomicLongFieldUpdater，AtomicIntegerFieldUpdater，AtomicReferenceFieldUpdater
 *  AtomicMarkableReference，AtomicStampedReference，AtomicReferenceArray
 *  其中AtomicBoolean，AtomicInteger，AtomicLong，AtomicReference是类似的。
 *  
 * 首先AtomicBoolean，AtomicInteger，AtomicLong，AtomicReference内部api是类似的：举个AtomicReference的例子
 *  
 * 使用AtomicReference创建线程安全的堆栈
 * 
 * </pre>
 * 
 * ClassName:LinkedStack <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-20 上午10:47:01 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class LinkedStack<T> {

    private AtomicReference<Node<T>> stacks = new AtomicReference<Node<T>>();


    public T push(T e) {
        Node<T> oldNode, newNode;
        while (true) { // 这里的处理非常的特别，也是必须如此的。
            oldNode = stacks.get();
            newNode = new Node<T>(e, oldNode);
            if (stacks.compareAndSet(oldNode, newNode)) {
                return e;
            }
        }
    }


    public T pop() {
        Node<T> oldNode, newNode;
        while (true) {
            oldNode = stacks.get();
            newNode = oldNode.next;
            if (stacks.compareAndSet(oldNode, newNode)) {
                return oldNode.object;
            }
        }
    }

    private static final class Node<T> {
        private T object;

        private Node<T> next;


        private Node(T object, Node<T> next) {
            this.object = object;
            this.next = next;
        }
    }
}
