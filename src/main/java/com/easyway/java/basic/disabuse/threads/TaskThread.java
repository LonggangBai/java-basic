/**
 * Project Name:java-basic
 * File Name:TaskThread.java
 * Package Name:com.easyway.java.basic.disabuse.threads
 * Date:2015-1-21下午2:06:16
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.threads;

/**
 * 
 * 
 * TaskThread（执行任务的线程）：它继承自Thread类，专门用于执行任务队列中的待执行任务
 * 
 * 
 * ClassName:TaskThread <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 下午2:06:16 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TaskThread extends Thread {
    // 该线程所属的线程池
    private ThreadPoolService service;
    public TaskThread(ThreadPoolService tps) {
        service = tps;
    }
    public void run() {
        // 在线程池运行的状态下执行任务队列中的任务
        while (service.isRunning()) {
            TaskQueue queue = service.getTaskQueue();
            Task task = queue.getTask();
            if (task != null) {
                task.deal();
            }
            queue.finishTask(task);
        }
    }
}

