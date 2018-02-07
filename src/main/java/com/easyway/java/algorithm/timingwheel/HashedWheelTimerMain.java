package com.easyway.java.algorithm.timingwheel;

import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * // deadline = 当前时间 + 任务延迟 - timer启动时间 = timer启动到任务结束的时间
 long deadline = System.currentTime() + timeout - timerStartTime;

 // calculated = tick 次数
 long calculated = deadline / tickDuration;
 // tick 目前已经 tick 过的次数
 final long ticks = Math.max(calculated, tick); // Ensure we don't schedule for past.
 // 算出任务应该插入的 wheel 的 slot, slotIndex = tick 次数 & mask, mask = wheel.length - 1, 默认即为 511
 stopIndex = (int) (ticks & mask);
 // 计算剩余的轮数, 只有 timer 走够轮数, 并且到达了 task 所在的 slot, task 才会过期
 remainingRounds = (calculated - tick) / wheel.length;
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