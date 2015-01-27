package com.easyway.java.basic.executors;

import java.util.concurrent.CyclicBarrier;

/**
 * CycliBarrier. 等所有线程都达到一个起跑线后才能开始继续运行。
 * 
 * 这简化了传统的用计数器+wait/notifyAll来实现该功能的方式。
 * @author Administrator
 *
 */
public class CycliBarrierTest implements Runnable {
    private CyclicBarrier barrier;

    public CycliBarrierTest(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        //do xxxx;
        try {
            this.barrier.await();//线程运行至此会检查是否其它线程都到齐了，没到齐就继续等待。到齐了就执行barrier的run函数体里的内容
        } catch (Exception e) {

        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //参数2代表两个线程都达到起跑线才开始一起继续往下执行
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
                public void run() {
                    //do xxxx;
                }
            });
        Thread t1 = new Thread(new CycliBarrierTest(barrier));         
        Thread t2 = new Thread(new CycliBarrierTest(barrier));
        t1.start();
        t2.start();
    }

}