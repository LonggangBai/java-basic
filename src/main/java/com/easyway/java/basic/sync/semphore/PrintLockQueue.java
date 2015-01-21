/**
 * Project Name:java-basic
 * File Name:PrintQueue.java
 * Package Name:com.easyway.java.basic.sync
 * Date:2015-1-21下午4:25:50
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync.semphore;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * <pre>
 * 修改Lock的公平性
 * 
 * 在ReentrantLock类和 ReentrantReadWriteLock类的构造器中，允许一个名为fair的boolean类型参数，它允许你来控制这些类的行为。默认值为 false，这将启用非公平模式。在这个模式中，当有多个线程正在等待一把锁（ReentrantLock或者 ReentrantReadWriteLock），这个锁必须选择它们中间的一个来获得进入临界区，选择任意一个是没有任何标准的。true值将开启公平 模式。在这个模式中，当有多个线程正在等待一把锁（ReentrantLock或者ReentrantReadWriteLock），这个锁必须选择它们 中间的一个来获得进入临界区，它将选择等待时间最长的线程。考虑到之前解释的行为只是使用lock()和unlock()方法。由于tryLock()方 法并不会使线程进入睡眠，即使Lock接口正在被使用，这个公平属性并不会影响它的功能。
 * 
 * 在这个指南中，我们将修改使用Lock同步代码块食谱示例来使用这个属性，并且观察公平与非公平模式之间的差别。
 * 
 * 准备工作…
 * 
 * 我们将要修改使用Lock同步代码块食谱的示例，所以阅读那个食谱来实现这个示例。
 * 
 * 如何做…
 * 
 * 按以下步骤来实现的这个例子:
 * 
 * 1.实现有使用Lock同步代码块食谱中解释的示例。
 * 
 * 2.在PrintQueue类，修改Lock对象的构造，如下：
 * 
 * 1
 * private Lock queueLock=new ReentrantLock(true);
 * 3.修改printJob()方法，使用两个代码块分离打印的模拟，在它们之间释放锁。
 * 
 * 
 * 
 * 所有线程都创建一个0.1秒的差异，第一需要获取锁的控制权的线程是Thread0，然后是Thread1，以此类推。当Thread0正在运行第一个由锁 保护的代码块时，有9个线程正在那个代码块上等待执行。当Thread0释放锁，它需要马上再次获取锁，所以我们有10个线程试图获取这个锁。当启用代码 模式，Lock接口将会选择Thread1，它是在这个锁上等待最长时间的线程。然后，选择Thread2，然后是Thread3，以此类推。直到所有线 程都通过了这个锁保护的第一个代码块，否则，没有一个线程能执行该锁保护的第二个代码块。
 * 
 * 一旦所有线程已经执行完由这个锁保护的第一个代码块，再次轮到Thread0。然后，轮到Thread1，以此类推。
 * 
 * 为了看与非公平模式的差异，改变传入锁构造器的参数，传入false值。在以下截图中，你可以看到修改示例后的执行结果：
 * 
 * 
 * 在这种情况下，线程按被创建的顺序执行，但每个线程各自执行两个受保护的代码块。然而，这种行为的原因是没有保证的，正如之前解释的，这个锁将选择任意一个线程获得访问保护代码块。在这种情况下，JVM不能保证线程的执行顺序。
 * 
 * 不止这些…
 * 
 * 读/写锁在它们的构造器中也有公平参数。这个参数在这种锁中的行为与本指南的解释是一样的。
 * 
 * 参见
 * 
 * 在第2章，基本线程同步中使用Lock同步代码块的指南
 * 在第2章，基本线程同步中使用读/写锁同步数据访问的指南
 * 在第7章，制订并发类中实现一个自定义的Lock类的指南
 * 
 * </pre>
 * 
 * ClassName:PrintQueue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午4:25:50 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
// 1. 创建一个会实现print queue的类名为 PrintQueue。
public class PrintLockQueue {

    // 2. 声明一个对象为Semaphore，称它为semaphore。
    private Lock queueLock = new ReentrantLock(true);


    // 3. 实现类的构造函数并初始能保护print quere的访问的semaphore对象的值。
    public PrintLockQueue() {
    }


    // 4. 实现Implement the printJob()方法，此方法可以模拟打印文档，并接收document对象作为参数。
    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during "
                    + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            queueLock.unlock();
        }
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during "
                    + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            queueLock.unlock();
        }

    }
}
