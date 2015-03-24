/**
 * Project Name:java-basic
 * File Name:DeadLockThread.java
 * Package Name:com.easyway.java.basic.multithread
 * Date:2015-3-23下午4:33:17
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.multithread;
/**
 * ClassName:DeadLockThread <br/>
 * Function:死锁的实例 <br/>
 * Reason:	 避免死锁的一个通用的经验法则：当几个线程都要访问共享资源时候，保证使每一个线程都按照同样的顺序去访问他们。 <br/>
 * Date:     2015-3-23 下午4:33:17 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class Counter{
    private int a;
    private Counter friend;
    public void setFriend(Counter friend){
        this.friend=friend;
    }
    public synchronized void add(){
        a++;
        Thread.yield();//线程放弃CPU，但是不会释放对象的锁
        friend.delete();
        System.out.println(Thread.currentThread().getName()+": add ");
    }
    public synchronized void delete(){
        a--;
     System.out.println(Thread.currentThread().getName()+": delete ");
    }
}
public class DeadLockThread extends Thread {

    private Counter counter;//共享数据
    public DeadLockThread(Counter counter){
        this.counter=counter;
        start();
    }
    public void run(){
        for (int i = 0; i < 1000; i++) {
            counter.add();
        }
    }
    public static void main(String[] args) {
        Counter counter1=new Counter();
        Counter counter2=new Counter();
        counter1.setFriend(counter2);
        counter2.setFriend(counter1);
        DeadLockThread machine1=new DeadLockThread(counter1);
        DeadLockThread machine2=new DeadLockThread(counter2);
    }
}

