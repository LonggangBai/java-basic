/**
 * Project Name:java-basic
 * File Name:InterruptTest.java
 * Package Name:com.easyway.java.basic.disabuse.threads
 * Date:2015-1-21下午2:03:43
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.threads;

/**
 * 
 * <pre>
 * 
 * 线程或者说多线程，是我们处理多任务的强大工具。线程和进程是不同的，每个进程都是一个独立运行的程序，拥有自己的变量，且不同进程间的变量不能共享；而线程是运行在进程内部的，每个正在运行的进程至少有一个线程，而且不同的线程之间可以在进程范围内共享数据。也就是说进程有自己独立的存储空间，而线程是和它所属的进程内的其他线程共享一个存储空间。线程的使用可以使我们能够并行地处理一些事情。线程通过并行的处理给用户带来更好的使用体验，比如你使用的邮件系统（outlook、Thunderbird、foxmail等），你当然不希望它们在收取新邮件的时候，导致你连已经收下来的邮件都无法阅读，而只能等待收取邮件操作执行完毕。这正是线程的意义所在。 
 * 
 * 实现线程的方式 
 * 
 * 实现线程的方式有两种： 
 * 继承java.lang.Thread，并重写它的run()方法，将线程的执行主体放入其中。
 * 实现java.lang.Runnable接口，实现它的run()方法，并将线程的执行主体放入其中。
 * 
 * 这是继承Thread类实现线程的示例： 
 * public class ThreadTest extends Thread {
 *     public void run() {
 *         // 在这里编写线程执行的主体
 *         // do something
 *     }
 * }
 * 
 * 这是实现Runnable接口实现多线程的示例： 
 * public class RunnableTest implements Runnable {
 *     public void run() {
 *         // 在这里编写线程执行的主体
 *         // do something
 *     }
 * }
 * 
 * 这两种实现方式的区别并不大。继承Thread类的方式实现起来较为简单，但是继承它的类就不能再继承别的类了，因此也就不能继承别的类的有用的方法了。而使用是想Runnable接口的方式就不存在这个问题了，而且这种实现方式将线程主体和线程对象本身分离开来，逻辑上也较为清晰，所以推荐大家更多地采用这种方式。 
 * 
 * 如何启动线程 
 * 
 * 我们通过以上两种方式实现了一个线程之后，线程的实例并没有被创建，因此它们也并没有被运行。我们要启动一个线程，必须调用方法来启动它，这个方法就是Thread类的start()方法，而不是run()方法（既不是我们继承Thread类重写的run()方法，也不是实现Runnable接口的run()方法）。run()方法中包含的是线程的主体，也就是这个线程被启动后将要运行的代码，它跟线程的启动没有任何关系。上面两种实现线程的方式在启动时会有所不同。 
 * 
 * 继承Thread类的启动方式： 
 * public class ThreadStartTest {
 *     public static void main(String[] args) {
 *         // 创建一个线程实例
 *         ThreadTest tt = new ThreadTest();
 *         // 启动线程
 *         tt.start();
 *     }
 * }
 * 
 * 实现Runnable接口的启动方式： 
 * public class RunnableStartTest {
 *     public static void main(String[] args) {
 *         // 创建一个线程实例
 *         Thread t = new Thread(new RunnableTest());
 *         // 启动线程
 *         t.start();
 *     }
 * }
 * 
 * 实际上这两种启动线程的方式原理是一样的。首先都是调用本地方法启动一个线程，其次是在这个线程里执行目标对象的run()方法。那么这个目标对象是什么呢？为了弄明白这个问题，我们来看看Thread类的run()方法的实现： 
 * public void run() {
 *     if (target != null) {
 *         target.run();
 *     }
 * }
 * 
 * 当我们采用实现Runnable接口的方式来实现线程的情况下，在调用new Thread(Runnable target)构造器时，将实现Runnable接口的类的实例设置成了线程要执行的主体所属的目标对象target，当线程启动时，这个实例的run()方法就被执行了。当我们采用继承Thread的方式实现线程时，线程的这个run()方法被重写了，所以当线程启动时，执行的是这个对象自身的run()方法。总结起来就一句话，线程类有一个Runnable类型的target属性，它是线程启动后要执行的run()方法所属的主体，如果我们采用的是继承Thread类的方式，那么这个target就是线程对象自身，如果我们采用的是实现Runnable接口的方式，那么这个target就是实现了Runnable接口的类的实例。 
 * 
 * 线程的状态 
 * 
 * 在Java 1.4及以下的版本中，每个线程都具有新建、可运行、阻塞、死亡四种状态，但是在Java 5.0及以上版本中，线程的状态被扩充为新建、可运行、阻塞、等待、定时等待、死亡六种。线程的状态完全包含了一个线程从新建到运行，最后到结束的整个生命周期。线程状态的具体信息如下： 
 * NEW（新建状态、初始化状态）：线程对象已经被创建，但是还没有被启动时的状态。这段时间就是在我们调用new命令之后，调用start()方法之前。
 * RUNNABLE（可运行状态、就绪状态）：在我们调用了线程的start()方法之后线程所处的状态。处于RUNNABLE状态的线程在JAVA虚拟机（JVM）上是运行着的，但是它可能还正在等待操作系统分配给它相应的运行资源以得以运行。
 * BLOCKED（阻塞状态、被中断运行）：线程正在等待其它的线程释放同步锁，以进入一个同步块或者同步方法继续运行；或者它已经进入了某个同步块或同步方法，在运行的过程中它调用了某个对象继承自java.lang.Object的wait()方法，正在等待重新返回这个同步块或同步方法。
 * WAITING（等待状态）：当前线程调用了java.lang.Object.wait()、java.lang.Thread.join()或者java.util.concurrent.locks.LockSupport.park()三个中的任意一个方法，正在等待另外一个线程执行某个操作。比如一个线程调用了某个对象的wait()方法，正在等待其它线程调用这个对象的notify()或者notifyAll()（这两个方法同样是继承自Object类）方法来唤醒它；或者一个线程调用了另一个线程的join()（这个方法属于Thread类）方法，正在等待这个方法运行结束。
 * TIMED_WAITING（定时等待状态）：当前线程调用了java.lang.Object.wait(long timeout)、java.lang.Thread.join(long millis)、java.util.concurrent.locks.LockSupport.packNanos(long nanos)、java.util.concurrent.locks.LockSupport.packUntil(long deadline)四个方法中的任意一个，进入等待状态，但是与WAITING状态不同的是，它有一个最大等待时间，即使等待的条件仍然没有满足，只要到了这个时间它就会自动醒来。
 * TERMINATED（死亡状态、终止状态）：线程完成执行后的状态。线程执行完run()方法中的全部代码，从该方法中退出，进入TERMINATED状态。还有一种情况是run()在运行过程中抛出了一个异常，而这个异常没有被程序捕获，导致这个线程异常终止进入TERMINATED状态。
 * 
 * 在Java5.0及以上版本中，线程的全部六种状态都以枚举类型的形式定义在java.lang.Thread类中了，代码如下： 
 * public enum State {
 *     NEW,
 *     RUNNABLE,
 *     BLOCKED,
 *     WAITING,
 *     TIMED_WAITING,
 *     TERMINATED;
 * }
 * 
 * sleep()和wait()的区别 
 * 
 * sleep()方法和wait()方法都成产生让当前运行的线程停止运行的效果，这是它们的共同点。下面我们来详细说说它们的不同之处。 
 * 
 * sleep()方法是本地方法，属于Thread类，它有两种定义： 
 * public static native void sleep(long millis) throws InterruptedException;
 * public static void sleep(long millis, int nanos) throws InterruptedException {
 *     //other code
 * }
 * 
 * 其中的参数millis代表毫秒数（千分之一秒），nanos代表纳秒数（十亿分之一秒）。这两个方法都可以让调用它的线程沉睡（停止运行）指定的时间，到了这个时间，线程就会自动醒来，变为可运行状态（RUNNABLE），但这并不表示它马上就会被运行，因为线程调度机制恢复线程的运行也需要时间。调用sleep()方法并不会让线程释放它所持有的同步锁；而且在这期间它也不会阻碍其它线程的运行。上面的连个方法都声明抛出一个InterruptedException类型的异常，这是因为线程在sleep()期间，有可能被持有它的引用的其它线程调用它的interrupt()方法而中断。中断一个线程会导致一个InterruptedException异常的产生，如果你的程序不捕获这个异常，线程就会异常终止，进入TERMINATED状态，如果你的程序捕获了这个异常，那么程序就会继续执行catch语句块（可能还有finally语句块）以及以后的代码。 
 * 
 * 为了更好地理解interrupt()效果，我们来看一下下面这个例子：
 * 
 * 
 * 运行结果： 
 * 我被执行了-在sleep()方法前
 * 我被执行了-在catch语句块中
 * 我被执行了-在try{}语句块后
 * 
 * wait()方法也是本地方法，属于Object类，有三个定义： 
 * public final void wait() throws InterruptedException {
 *     //do something
 * }
 * public final native void wait(long timeout) throws InterruptedException;
 * public final void wait(long timeout, int nanos) throws InterruptedException {
 *     //do something
 * }
 * 
 * wari()和wait(long timeout,int nanos)方法都是基于wait(long timeout)方法实现的。同样地，timeout代表毫秒数，nanos代表纳秒数。当调用了某个对象的wait()方法时，当前运行的线程就会转入等待状态（WAITING），等待别的线程再次调用这个对象的notify()或者notifyAll()方法（这两个方法也是本地方法）唤醒它，或者到了指定的最大等待时间，线程自动醒来。如果线程拥有某个或某些对象的同步锁，那么在调用了wait()后，这个线程就会释放它持有的所有同步资源，而不限于这个被调用了wait()方法的对象。wait()方法同样会被Thread类的interrupt()方法中断，并产生一个InterruptedException异常，效果同sleep()方法被中断一样。 
 * 
 * 实现同步的方式 
 * 
 * 同步是多线程中的重要概念。同步的使用可以保证在多线程运行的环境中，程序不会产生设计之外的错误结果。同步的实现方式有两种，同步方法和同步块，这两种方式都要用到synchronized关键字。 
 * 
 * 给一个方法增加synchronized修饰符之后就可以使它成为同步方法，这个方法可以是静态方法和非静态方法，但是不能是抽象类的抽象方法，也不能是接口中的接口方法。下面代码是一个同步方法的示例： 
 * public synchronized void aMethod() {
 *     // do something
 * }
 * public static synchronized void anotherMethod() {
 *     // do something
 * }
 * 
 * 线程在执行同步方法时是具有排它性的。当任意一个线程进入到一个对象的任意一个同步方法时，这个对象的所有同步方法都被锁定了，在此期间，其他任何线程都不能访问这个对象的任意一个同步方法，直到这个线程执行完它所调用的同步方法并从中退出，从而导致它释放了该对象的同步锁之后。在一个对象被某个线程锁定之后，其他线程是可以访问这个对象的所有非同步方法的。 
 * 
 * 同步块的形式虽然与同步方法不同，但是原理和效果是一致的。同步块是通过锁定一个指定的对象，来对同步块中包含的代码进行同步；而同步方法是对这个方法块里的代码进行同步，而这种情况下锁定的对象就是同步方法所属的主体对象自身。如果这个方法是静态同步方法呢？那么线程锁定的就不是这个类的对象了，也不是这个类自身，而是这个类对应的java.lang.Class类型的对象。同步方法和同步块之间的相互制约只限于同一个对象之间，所以静态同步方法只受它所属类的其它静态同步方法的制约，而跟这个类的实例（对象）没有关系。 
 * 
 * 下面这段代码演示了同步块的实现方式： 
 * public void test() {
 *     // 同步锁
 *     String lock = "LOCK";
 *     // 同步块
 *     synchronized (lock) {
 *         // do something
 *     }
 *     int i = 0;
 *     // ...
 * }
 * 
 * 对于作为同步锁的对象并没有什么特别要求，任意一个对象都可以。如果一个对象既有同步方法，又有同步块，那么当其中任意一个同步方法或者同步块被某个线程执行时，这个对象就被锁定了，其他线程无法在此时访问这个对象的同步方法，也不能执行同步块。 
 * 
 * synchronized和Lock 
 * 
 * Lock是一个接口，它位于Java 5.0新增的java.utils.concurrent包的子包locks中。concurrent包及其子包中的类都是用来处理多线程编程的。实现Lock接口的类具有与synchronized关键字同样的功能，但是它更加强大一些。java.utils.concurrent.locks.ReentrantLock是较常用的实现了Lock接口的类。下面是ReentrantLock类的一个应用实例： 
 * private Lock lock = new ReentrantLock();
 * public void testLock() {
 *     // 锁定对象
 *     lock.lock();
 *     try {
 *         // do something
 *     } finally {
 *         // 释放对对象的锁定
 *         lock.unlock();
 *     }
 * }
 * 
 * lock()方法用于锁定对象，unlock()方法用于释放对对象的锁定，他们都是在Lock接口中定义的方法。位于这两个方法之间的代码在被执行时，效果等同于被放在synchronized同步块中。一般用法是将需要在lock()和unlock()方法之间执行的代码放在try{}块中，并且在finally{}块中调用unlock()方法，这样就可以保证即使在执行代码抛出异常的情况下，对象的锁也总是会被释放，否则的话就会为死锁的产生增加可能。 
 * 
 * 使用synchronized关键字实现的同步，会把一个对象的所有同步方法和同步块看做一个整体，只要有一个被某个线程调用了，其他的就无法被别的线程执行，即使这些方法或同步块与被调用的代码之间没有任何逻辑关系，这显然降低了程序的运行效率。而使用Lock就能够很好地解决这个问题。我们可以把一个对象中按照逻辑关系把需要同步的方法或代码进行分组，为每个组创建一个Lock类型的对象，对实现同步。那么，当一个同步块被执行时，这个线程只会锁定与当前运行代码相关的其他代码最小集合，而并不影响其他线程对其余同步代码的调用执行。 
 * 
 * 关于死锁 
 * 
 * 死锁就是一个进程中的每个线程都在等待这个进程中的其他线程释放所占用的资源，从而导致所有线程都无法继续执行的情况。死锁是多线程编程中一个隐藏的陷阱，它经常发生在多个线程共用资源的时候。在实际开发中，死锁一般隐藏的较深，不容易被发现，一旦死锁现象发生，就必然会导致程序的瘫痪。因此必须避免它的发生。 
 * 
 * 程序中必须同时满足以下四个条件才会引发死锁： 
 * 互斥（Mutual exclusion）：线程所使用的资源中至少有一个是不能共享的，它在同一时刻只能由一个线程使用。
 * 持有与等待（Hold and wait）：至少有一个线程已经持有了资源，并且正在等待获取其他的线程所持有的资源。
 * 非抢占式（No pre-emption）：如果一个线程已经持有了某个资源，那么在这个线程释放这个资源之前，别的线程不能把它抢夺过去使用。
 * 循环等待（Circular wait）：假设有N个线程在运行，第一个线程持有了一个资源，并且正在等待获取第二个线程持有的资源，而第二个线程正在等待获取第三个线程持有的资源，依此类推……第N个线程正在等待获取第一个线程持有的资源，由此形成一个循环等待。
 * 
 * 
 * 
 * 
 * </pre>
 * 
 * ClassName:InterruptTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:03:43 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class InterruptTest {
    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                try {
                    System.out.println("我被执行了-在sleep()方法前");
                    // 停止运行10分钟
                    Thread.sleep(1000 * 60 * 60 * 10);
                    System.out.println("我被执行了-在sleep()方法后");
                }
                catch (InterruptedException e) {
                    System.out.println("我被执行了-在catch语句块中");
                }
                System.out.println("我被执行了-在try{}语句块后");
            }
        };
        // 启动线程
        t.start();
        // 在sleep()结束前中断它
        t.interrupt();
    }
}
