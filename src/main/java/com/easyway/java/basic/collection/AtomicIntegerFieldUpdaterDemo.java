/**
 * Project Name:java-basic
 * File Name:AtomicIntegerFieldUpdaterDemo.java
 * Package Name:com.easyway.java.basic.collection
 * Date:2015-3-20上午10:48:32
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 * <pre>
 * 然后关注字段的原子更新。
 *  
 * AtomicIntegerFieldUpdater<T>/AtomicLongFieldUpdater<T>/AtomicReferenceFieldUpdater<T,V>是基于反射的原子更新字段的值。
 *  
 * 相应的API也是非常简单的，但是也是有一些约束的。
 *  
 * （1）字段必须是volatile类型的！volatile到底是个什么东西。请查看  
 *  （2）字段的描述类型（修饰符public/protected/default/private）是与调用者与操作对象字段的关系一致。也就是说调用者能够直接操作对象字段，
 *  那么就可以反射进行原子操作。但是对于父类的字段，子类是不能直接操作的，尽管子类可以访问父类的字段。
 *  
 * （3）只能是实例变量，不能是类变量，也就是说不能加static关键字。
 *  
 * （4）只能是可修改变量，不能使final变量，因为final的语义就是不可修改。实际上final的语义和volatile是有冲突的，这两个关键字不能同时存在。
 *  
 * （5）对于AtomicIntegerFieldUpdater和AtomicLongFieldUpdater只能修改int/long类型的字段，不能修改其包装类型（Integer/Long）。
 * 如果要修改包装类型就需要使用AtomicReferenceFieldUpdater。
 * 
 * 
 * 在上面的例子中DemoData的字段value3/value4对于AtomicIntegerFieldUpdaterDemo类是不可见的，因此通过反射是不能直接修改其值的。
 *  
 *  
 *  
 * AtomicMarkableReference类描述的一个<Object,Boolean>的对，可以原子的修改Object或者Boolean的值，这种数据结构在一些缓存或者状
 * 态描述中比较有用。这种结构在单个或者同时修改Object/Boolean的时候能够有效的提高吞吐量。
 *  
 *  
 *  
 * AtomicStampedReference类维护带有整数“标志”的对象引用，可以用原子方式对其进行更新。对比AtomicMarkableReference类的<Object,Boolean>，
 * AtomicStampedReference维护的是一种类似<Object,int>的数据结构，其实就是对对象（引用）的一个并发计数。但是与AtomicInteger不同的是，
 * 此数据结构可以携带一个对象引用（Object），并且能够对此对象和计数同时进行原子操作。
 *  
 * 在本文结尾会提到“ABA问题”，而AtomicMarkableReference/AtomicStampedReference在解决“ABA问题”上很有用。
 * 
 * 三、Atomic类的作用
 *  
 * 使得让对单一数据的操作，实现了原子化
 *  使用Atomic类构建复杂的，无需阻塞的代码
 *  访问对2个或2个以上的atomic变量（或者对单个atomic变量进行2次或2次以上的操作）通常认为是需要同步的，以达到让这些操作能被作为一个原子单元。
 *  无锁定且无等待算法
 *  
 * 基于 CAS （compare and swap）的并发算法称为 无锁定算法，因为线程不必再等待锁定（有时称为互斥或关键部分，这取决于线程平台的术语）。无论 CAS 
 * 操作成功还是失败，在任何一种情况中，它都在可预知的时间内完成。如果 CAS 失败，调用者可以重试 CAS 操作或采取其他适合的操作。
 *  如果每个线程在其他线程任意延迟（或甚至失败）时都将持续进行操作，就可以说该算法是 无等待的。与此形成对比的是， 无锁定算法要求仅 某个线程总是执行操作。
 *  （无等待的另一种定义是保证每个线程在其有限的步骤中正确计算自己的操作，而不管其他线程的操作、计时、交叉或速度。这一限制可以是系统中线程数的函数；例如，
 *  如果有 10 个线程，每个线程都执行一次CasCounter.increment() 操作，最坏的情况下，每个线程将必须重试最多九次，才能完成增加。）
 *  再过去的 15 年里，人们已经对无等待且无锁定算法（也称为 无阻塞算法）进行了大量研究，许多人通用数据结构已经发现了无阻塞算法。无阻塞算法被广泛用于操作系
 *  统和 JVM 级别，进行诸如线程和进程调度等任务。虽然它们的实现比较复杂，但相对于基于锁定的备选算法，它们有许多优点：可以避免优先级倒置和死锁等危险，竞争
 *  比较便宜，协调发生在更细的粒度级别，允许更高程度的并行机制等等。
 *  常见的：
 *  非阻塞的计数器Counter
 *  非阻塞堆栈ConcurrentStack
 *  非阻塞的链表ConcurrentLinkedQueue
 * 
 * </pre>
 * 
 * ClassName:AtomicIntegerFieldUpdaterDemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-20 上午10:48:32 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class AtomicIntegerFieldUpdaterDemo {

    class DemoData {
        public volatile int value1 = 1;
        volatile int value2 = 2;
        protected volatile int value3 = 3;
        private volatile int value4 = 4;
    }


    AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName) {
        return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
    }


    void doit() {
        DemoData data = new DemoData();
        System.out.println("1 ==> " + getUpdater("value1").getAndSet(data, 10));
        System.out.println("3 ==> " + getUpdater("value2").incrementAndGet(data));
        System.out.println("2 ==> " + getUpdater("value3").decrementAndGet(data));
        System.out.println("true ==> " + getUpdater("value4").compareAndSet(data, 4, 5));
    }


    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        demo.doit();
    }
}
