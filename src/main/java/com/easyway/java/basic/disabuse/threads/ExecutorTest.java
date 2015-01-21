/**
 * Project Name:java-basic
 * File Name:ExecutorTest.java
 * Package Name:com.easyway.java.basic.disabuse.threads
 * Date:2015-1-21下午2:07:38
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.threads;

/**
 * 
 * <pre>
 * 当然，我们实现的是最简单的，这里只是为了演示线程池的实现原理。在实际应用中，根据情况的不同，可以做很多优化。比如： 
 调整任务队列的规则，给任务设置优先级，级别高的任务优先执行。
 动态维护线程池，当待执行任务数量较多时，增加线程的数量，加快任务的执行速度；当任务较少时，回收一部分长期闲置的线程，减少对系统资源的消耗。

 事实上Java5.0及以上版本已经为我们提供了线程池功能，无需再重新实现。这些类位于java.util.concurrent包中。 

 Executors类提供了一组创建线程池对象的方法，常用的有一下几个： 
 public static ExecutorService newCachedThreadPool() {
 // other code
 }
 public static ExecutorService newFixedThreadPool(int nThreads) {
 // other code
 }
 public static ExecutorService newSingleThreadExecutor() {
 // other code
 }

 newCachedThreadPool()方法创建一个动态的线程池，其中线程的数量会根据实际需要来创建和回收，适合于执行大量短期任务的情况；newFixedThreadPool(int nThreads)方法创建一个包含固定数量线程对象的线程池，nThreads代表要创建的线程数，如果某个线程在运行的过程中因为异常而终止了，那么一个新的线程会被创建和启动来代替它；而newSingleThreadExecutor()方法则只在线程池中创建一个线程，来执行所有的任务。 

 这三个方法都返回了一个ExecutorService类型的对象。实际上，ExecutorService是一个接口，它的submit()方法负责接收任务并交与线程池中的线程去运行。submit()方法能够接受Callable和Runnable两种类型的对象。它们的用法和区别如下： 
 Runnable接口：继承Runnable接口的类要实现它的run()方法，并将执行任务的代码放入其中，run()方法没有返回值。适合于只做某种操作，不关心运行结果的情况。
 Callable接口：继承Callable接口的类要实现它的call()方法，并将执行任务的代码放入其中，call()将任务的执行结果作为返回值。适合于执行某种操作后，需要知道执行结果的情况。

 无论是接收Runnable型参数，还是接收Callable型参数的submit()方法，都会返回一个Future（也是一个接口）类型的对象。该对象中包含了任务的执行情况以及结果。调用Future的boolean isDone()方法可以获知任务是否执行完毕；调用Object get()方法可以获得任务执行后的返回结果，如果此时任务还没有执行完，get()方法会保持等待，直到相应的任务执行完毕后，才会将结果返回。 

 我们用下面的一个例子来演示Java5.0中线程池的使用： 


 运行结果： 
 已经执行-RunnableTest.run()
 已经执行-CallableTest.call()
 返回值-CallableTest.call()
 执行完毕-RunnableTest.run()
 执行完毕-CallableTest.run()

 使用完线程池之后，需要调用它的shutdown()方法停止服务，否则其中的所有线程都会保持运行，程序不会退出。
 * </pre>
 * ClassName:ExecutorTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 下午2:07:38 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.*;


public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future fr = es.submit(new RunnableTest());// 提交任务
        Future fc = es.submit(new CallableTest());// 提交任务
        // 取得返回值并输出
        System.out.println((String) fc.get());
        // 检查任务是否执行完毕
        if (fr.isDone()) {
            System.out.println("执行完毕-RunnableTest.run()");
        }
        else {
            System.out.println("未执行完-RunnableTest.run()");
        }
        // 检查任务是否执行完毕
        if (fc.isDone()) {
            System.out.println("执行完毕-CallableTest.run()");
        }
        else {
            System.out.println("未执行完-CallableTest.run()");
        }
        // 停止线程池服务
        es.shutdown();
    }
}


class RunnableTest implements Runnable {
    public void run() {
        System.out.println("已经执行-RunnableTest.run()");
    }
}


class CallableTest implements Callable {
    public Object call() {
        System.out.println("已经执行-CallableTest.call()");
        return "返回值-CallableTest.call()";
    }
}
