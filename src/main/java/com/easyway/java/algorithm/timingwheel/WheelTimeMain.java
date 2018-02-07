package com.easyway.java.algorithm.timingwheel;

import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.TimerTask;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WheelTimeMain {
    /**
     * ﻿tickDuration: 每 tick 一次的时间间隔, 每 tick 一次就会到达下一个槽位
     * ticksPerWheel: 轮中的 slot 数
     * @throws InterruptedException
     */
    public static void main(String[] args)  throws InterruptedException {
        final HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000/**tickDuration**/, TimeUnit.MILLISECONDS, 16 /**ticksPerWheel**/);
        System.out.println(new Date()+" submitted");
        Timeout timeout = hashedWheelTimer.newTimeout(new TimerTask() {
           public  void run(Timeout timeout) throws Exception {
               new Thread() {
                   @Override
                   public void run() {
                       System.out.println(new Date() + " executed");
                       System.out.println(hashedWheelTimer);
                       try {
                           TimeUnit.SECONDS.sleep(100);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       System.out.println(new Date() + " FINISH");
                   }
               }.start();
           }
        }, 5, TimeUnit.SECONDS);
    }
}
