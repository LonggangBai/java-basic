/**
 * Project Name:java-basic
 * File Name:Job.java
 * Package Name:com.easyway.java.basic.sync.semphore
 * Date:2015-1-21下午4:27:11
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync.semphore;

/**
 * ClassName:Job <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午4:27:11 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
// 8. 创建一个名为Job的类并一定实现Runnable 接口。这个类实现把文档传送到打印机的任务。
public class ManyJob implements Runnable {

    // 9. 声明一个对象为PrintQueue，名为printQueue。
    private ManyPrintQueue printQueue;


    // 10. 实现类的构造函数，初始化这个类里的PrintQueue对象。
    public ManyJob(ManyPrintQueue printQueue) {
        this.printQueue = printQueue;
    }


    // 11. 实现方法run()。
    @Override
    public void run() {

        // 12. 首先， 此方法写信息到操控台表明任务已经开始执行了。
        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

        // 13. 然后，调用PrintQueue 对象的printJob()方法。
        printQueue.printJob(new Object());

        // 14. 最后， 此方法写信息到操控台表明它已经结束运行了。
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
