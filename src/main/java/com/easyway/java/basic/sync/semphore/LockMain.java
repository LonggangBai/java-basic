/**
 * Project Name:java-basic
 * File Name:Main.java
 * Package Name:com.easyway.java.basic.sync.semphore
 * Date:2015-1-21下午4:27:42
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync.semphore;

/**
 * ClassName:Main <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午4:27:42 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
// 15. 实现例子的main类，创建名为 Main的类并实现main()方法。
public class LockMain {
    public static void main(String args[]) {

        // 16. 创建PrintQueue对象名为printQueue。
        PrintQueue printQueue = new PrintQueue();

        // 17. 创建10个threads。每个线程会执行一个发送文档到print queue的Job对象。
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

        // 18. 最后，开始这10个线程们。
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
