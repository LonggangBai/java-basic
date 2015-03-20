/**
 * Project Name:java-basic
 * File Name:ProductStore.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午2:18:31
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection.blockingqueue;

/**
 * ClassName:WaitNotifyProductStore <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 生产者消费者是经典的线程之间同步通信问题，生产者线程只有在产品仓库中没有产品的时候才生产产品，当它生成完一个产品以后唤醒消费者线程，
 * 消费者线程只有在产品仓库中有产品的时候才能取走产品，然后唤醒生产者线程。
 * 
 * Java可以有好几种方法解决这个问题。首先基础的当然是用Object的wait()、notify()和notifyAll()。
 * 
 * 产品仓库类：
 * 
 * <br/>
 * Date: 2015-3-20 下午2:18:31 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class WaitNotifyProducer implements Runnable {

    WaitNotifyProductStore store;


    public WaitNotifyProducer(WaitNotifyProductStore store) {
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


class WaitNotifyConsumer implements Runnable {

    WaitNotifyProductStore store;


    public WaitNotifyConsumer(WaitNotifyProductStore store) {
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
public class WaitNotifyProductStore {
    private boolean flag = false;


    public boolean hasProduct() {// 是否有产品
        return flag;
    }


    /**
     * 生产产品
     * 
     * @throws Exception
     */
    public synchronized void makeProduct() throws Exception {
        while (hasProduct()) {// 如果生产线程唤醒的还是生产线程，这个被唤醒的生产线程将继续wait
            this.wait();
        }
        Thread.sleep(300);
        flag = true;
        System.out.println(Thread.currentThread().getName() + ":生产了一个产品");
        this.notifyAll();// 唤醒所有线程
    }


    /**
     * 取走产品
     * 
     * @throws Exception
     */
    public synchronized void getProduct() throws Exception {
        while (!hasProduct()) {
            this.wait();
        }
        Thread.sleep(100);
        flag = false;
        System.out.println(Thread.currentThread().getName() + ":取走一个产品");
        this.notifyAll();
    }


    public static void main(String[] args) {
        WaitNotifyProductStore store = new WaitNotifyProductStore();
        for (int i = 1; i <= 5; i++) {
            new Thread(new WaitNotifyConsumer(store), "消费者" + i).start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(new WaitNotifyProducer(store), "生产者" + i).start();
        }
    }
}
