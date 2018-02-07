package com.easyway.java.algorithm.timingwheel;

import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * Created by darren on 2016/11/17.
 */
public class HashedWheelTimerMain implements TimerTask {
    final static Timer timer = new HashedWheelTimer();

    public static void main(String[] args) {
        TimerTask timerTask = new HashedWheelTimerMain();
        for (int i = 0; i < 10; i++) {
            timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);
        }
    }

    public void run(Timeout timeout) throws Exception {
        System.out.println("timeout, argv = " + timeout.toString());
    }
}