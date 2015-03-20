/**
 * Project Name:java-basic
 * File Name:ConditionLock.java
 * Package Name:com.easyway.java.basic.collection.blockingqueue
 * Date:2015-3-20下午2:32:09
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.collection.blockingqueue;
/**
 * ClassName:ConditionLock <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 下午2:32:09 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//产品仓库
public class ConditionLockProductStore {
    private boolean flag =  false;  
    private Lock lock = new ReentrantLock();  
    private Condition producerCond = lock.newCondition(); //控制生产者的condition  
    private Condition consumerCond = lock.newCondition(); //控制消费者的condition

    public boolean hasProduct(){//是否有产品
        return flag;
    }
    /**
     * 生产产品
     * @throws Exception
     */
    public void makeProduct() throws Exception{
        lock.lock();  
        try {
            while(hasProduct()){//还有产品，阻塞生产者
                producerCond.await();
            }
            Thread.sleep(300);
            flag = true;
            System.out.println(Thread.currentThread().getName()+":生产了一个产品");
            consumerCond.signal();//唤醒一个消费者
        } finally{
            lock.unlock();  
        }
    }
    /**
     * 取走产品
     * @throws Exception
     */
    public void getProduct() throws Exception{
        lock.lock();  
        try {
            while(!hasProduct()){//没有产品，阻塞消费者
                consumerCond.await();
            }
            Thread.sleep(100);
            flag = false;
            System.out.println(Thread.currentThread().getName()+":取走一个产品");
            producerCond.signal();//唤醒一个生产者
        } finally {
            lock.unlock();
        }
    }
}
