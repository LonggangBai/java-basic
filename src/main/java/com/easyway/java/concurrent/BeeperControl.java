/**
 * Project Name:java-basic
 * File Name:BeeperControl.java
 * Package Name:com.easyway.java.concurrent
 * Date:2015-3-6下午1:29:15
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;


/**
 * ClassName:BeeperControl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-6 下午1:29:15 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */

public class BeeperControl {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        BigInteger   bi;
        BeeperControl bc=new BeeperControl();
        bc.beepForAnHour();
    }

    public  void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };
        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 60 * 60, SECONDS);
    }
}
