/**
 * Project Name:java-basic
 * File Name:NetworkService.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-2-4下午2:54:59
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * <pre>
 * shutdown和shutdownNow--多线程任务的关闭
 * 博客分类： Java
 * 多线程threadJava .
 * 采用5.0的线程池关闭线程，不管怎样，最后都是调用Interrupt.而interrupt这个方法，并不是什么情况下都能结束线程，释放资源。
 * Interrupt只是在线程阻塞的时候，抛个异常出来，从而结束这个阻塞。
 * 比如像下面的这种代码，就不管怎么shutdown，或者是shutdownNow，都不会关闭： 
 * 
 * Java代码  
 * 1.while(true){  
 * 2.                    try {     
 * 3.System.out.println("beat");             TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));  
 * 4.                    } catch (InterruptedException e) {  
 * 5.                        // TODO Auto-generated catch block  
 * 6.                        e.printStackTrace();  
 * 7.                    }  
 * 8.                }  
 *  
 * 
 * 因此，我们使用线程池的时候，不仅需要知道怎么开启，更需要知道怎么关闭，下面的比较好的写法： 
 * 
 * Java代码  
 * 1.while(!Thread.interrupted()){  
 * 2.                    try {  
 * 3.System.out.println("beat");  
 * 4.    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));  
 * 5.                    } catch (InterruptedException e) {  
 * 6.                        // TODO Auto-generated catch block  
 * 7.                        e.printStackTrace();  
 * 8.//终结循环  
 * 9.Thread.currentThread().interrupt();  
 * 10.                    }  
 * 11.                }  
 *  
 * 最后需要调用  executorService.shutdown();  或者showdownNow()来终止这个任务。 
 * 这里有几点需要注意： 
 * 1. Thread.interrupted() 是一个测试线程是否已经被调用了interrupt的方法，因为如果已
 * Java代码  
 * 1.  
 * 经被调用的话，线程下次wait的时候，就会很粗暴的抛出一个异常来，Thread.interrupted()返回一个boolean，表示是否存在这种
 * 情形，不仅如此，它还会把已经调用的一个interrupt给清掉。（只能清掉一个）
 * 2. shutdown方法： 这个方法，只能立刻interrupt那些目前没有任务，处于等待状态从blockingQueue获取任务的异常。而不能
 * interrupt那些在任务执行过程中的thread,或者是任务执行过程中挂起的thread. 看一下实现代码就知道原因：
 * 
 * Java代码  
 * 1.void interruptIfIdle() {  
 * 2.    final ReentrantLock runLock = this.runLock;  
 * 3.    if ([b]runLock.tryLock()[/b]) {  
 * 4.        try {  
 * 5.            thread.interrupt();  
 * 6.        } finally {  
 * 7.            runLock.unlock();  
 * 8.        }  
 * 9.    }  
 * 10.}  
 * 执行过程中的worker有锁，没有执行完任务，这个锁是不会释放的。 
 * 3. shutdownNow方法： 不管任务是否在执行中，一律interrupt，不去判断什么锁不锁。 
 * Java代码  
 * 1.void interruptNow() {  
 * 2.    thread.interrupt();  
 * 3.}
 * 
 * </pre>
 * 
 * ClassName:NetworkService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-4 下午2:54:59 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class NetworkService implements Runnable {
    private final ServerSocket serverSocket;
    private final ExecutorService pool;


    public NetworkService(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);
    }


    public void run() { // run the service
        try {
            for (;;) {
                pool.execute(new Handler(serverSocket.accept()));
            }
        }
        catch (IOException ex) {
            pool.shutdown();
        }
    }
}


class Handler implements Runnable {
    private final Socket socket;


    Handler(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        // read and service request on socket
    }
}
