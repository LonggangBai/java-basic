/**
 * Project Name:java-basic
 * File Name:TaskQueue.java
 * Package Name:com.easyway.java.basic.disabuse.threads
 * Date:2015-1-21下午2:05:47
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.threads;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * <pre>
 * TaskQueue（任务队列）：在同一时刻，可能有很多任务需要执行，而程序在同一时刻只能执行一定数量的任务，
 * 当需要执行的任务数超过了程序所能承受的任务数时怎么办呢？这就有了先执行哪些任务，后执行哪些任务的规则。
 * TaskQueue类就定义了这些规则中的一种，它采用的是FIFO（先进先出，英文名是First In First Out）的
 * 方式，也就是按照任务到达的先后顺序执行。
 * 
 * 
 * addTask(Task task)方法用于当一个新的任务到达时，将它添加到任务队列中。这里使用了LinkedList类来保存
 * 任务到达的先后顺序。finishTask(Task task)方法用于任务被执行完毕时，将它从任务队列中清除出去。getTask()方法用于取得当前要执行的任务。
 * </pre>
 * 
 * ClassName:TaskQueue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:05:47 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class TaskQueue {
    private List<Task> queue = new LinkedList<Task>();


    // 添加一项任务
    public synchronized void addTask(Task task) {
        if (task != null) {
            queue.add(task);
        }
    }


    // 完成任务后将它从任务队列中删除
    public synchronized void finishTask(Task task) {
        if (task != null) {
            task.setState(Task.State.FINISHED);
            queue.remove(task);
        }
    }


    // 取得一项待执行任务
    public synchronized Task getTask() {
        Iterator<Task> it = queue.iterator();
        Task task;
        while (it.hasNext()) {
            task = it.next();
            // 寻找一个新建的任务
            if (Task.State.NEW.equals(task.getState())) {
                // 把任务状态置为运行中
                task.setState(Task.State.RUNNING);
                return task;
            }
        }
        return null;
    }
}
