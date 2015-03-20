/**
 * Project Name:java-basic
 * File Name:ConditionProductStore.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午2:23:35
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection.blockingqueue;

/**
 * ClassName:ConditionProductStore <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 第二种方法就是利用java.util.concurrent包下的Lock得到Conditon，利用Condition的await()、signal()、signalAll()实现线程的通信：
 * 
 *  makeProduct和getProduct方法不再使用synchronized修饰，所以使用Lock来控制同步，conditon的await()、singal()、singalAll()分别替换了Object的wait()、notify()和notifyAll()。
 *  <br/>
 * Date:     2015-3-20 下午2:23:35 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ConditionProducer implements Runnable {

    ConditionProductStore store;


    public ConditionProducer(ConditionProductStore store) {
        this.store = store;
    }


    @Override
    public void run() {
        try {
            store.makeProduct();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class ConditionConsumer implements Runnable {

    ConditionProductStore store;


    public ConditionConsumer(ConditionProductStore store) {
        this.store = store;
    }


    @Override
    public void run() {
        try {
            store.getProduct();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


// 产品仓库
public class ConditionProductStore {
    private boolean flag = false;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition(); // 得到condition


    public boolean hasProduct() {// 是否有产品
        return flag;
    }


    /**
     * 生产产品
     * 
     * @throws Exception
     */
    public void makeProduct() throws Exception {
        lock.lock();
        try {
            while (hasProduct()) {// 如果生产线程唤醒的还是生产线程，这个被唤醒的生产线程将继续wait
                condition.await();
            }
            Thread.sleep(300);
            flag = true;
            System.out.println(Thread.currentThread().getName() + ":生产了一个产品");
            condition.signalAll();// 唤醒所有线程
        }
        finally {
            lock.unlock();
        }
    }


    /**
     * 取走产品
     * 
     * @throws Exception
     */
    public void getProduct() throws Exception {
        lock.lock();
        try {
            while (!hasProduct()) {
                condition.await();
            }
            Thread.sleep(100);
            flag = false;
            System.out.println(Thread.currentThread().getName() + ":取走一个产品");
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ConditionProductStore store = new ConditionProductStore();
        for (int i = 1; i <= 5; i++) {
            new Thread(new ConditionConsumer(store), "消费者" + i).start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(new ConditionProducer(store), "生产者" + i).start();
        }

    }
}
