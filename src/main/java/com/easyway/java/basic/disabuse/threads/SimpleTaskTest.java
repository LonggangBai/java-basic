/**
 * Project Name:java-basic
 * File Name:SimpleTaskTest.java
 * Package Name:com.easyway.java.basic.disabuse.threads
 * Date:2015-1-21下午2:07:13
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.threads;

/**
 * 
 * <pre>
 * 完成了上面四个类，我们就实现了一个简单的线程池。现在我们就可以使用它了，下面的代码做了一个简单的示例：
 * </pre>
 * ClassName:SimpleTaskTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-1-21 下午2:07:13 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SimpleTaskTest extends Task {
    @Override
    public void deal() {
        // do something
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolService service = new ThreadPoolService();
        service.start();
        // 执行十次任务
        for (int i = 0; i < 10; i++) {
            service.runTask(new SimpleTaskTest());
        }
        // 睡眠1秒钟，等待所有任务执行完毕
        Thread.sleep(1000);
        service.stop();
    }
}