package com.easyway.java.basic.thread.productconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <pre>
 *  三、生产者-消费者模式

    在经典的多线程模式中，生产者-消费者为多线程间协作提供了良好的解决方案。基本原理是两类线程，即若干个生产者和若干个消费者，生产者负责提交用户请求任务（到内存缓冲区），消费者线程负责处理任务（从内存缓冲区中取任务进行处理），两类线程之间通过共享内存缓冲区进行通信。

     共享内存缓冲区的存在避免生产者和消费者直接通信，且允许消费者和生产者执行速度上存在时间差，无论谁快谁慢，都可以通过缓冲区缓解，确保系统正常运行。

    生产者消费者模式中主要角色

生产者：提交用户请求，提取用户任务，并装入内存缓冲区；

消费者：在内存缓冲区中提取并处理任务；

内存缓冲区：缓存生产者提交的任务或数据，供消费者使用；

任务：生产者向内存缓冲区提交的数据结构；

Main：即Client客户端，使用生产者和消费者的客户端。

     下面用代码实现一个基于本模式的求整数平方和的并行计算。

     具体实现采用BlockingQueue充当缓冲区，创建一个任务类PCData，
     生产者负责创建PCData对象放入缓冲区，消费者负责处理从缓冲区中取出
     PCData对象进行处理。
 * 总结：

     从执行结果可以看出，当客户端程序启动三个生产者、消费者线程时，生产者开始创建数据对象，缓冲区中数据个数为1，自增加一打印出2，传入1消费者开始进行平方处理，打印出平方结果，消费者线程处理完毕。依次循环操作，生产者线程关闭，直到消费者将缓冲区中的数据全部处理完毕时，程序运行结束。

     生产者-消费者模式能够很好的对生产者线程和消费者线程进行解耦，优化了系统结构。同时由于共享缓冲区的作用，允许两类线程存在执行速度上的差异，一定程度上缓解了性能瓶颈对系统运行的影响。
﻿﻿
 * </pre>
 * 
 * @author longgangbai
 *
 */
public class Client {
	public static void main(String[] args) throws InterruptedException {
		// 建立缓冲区
		BlockingQueue<PCData> queue = new LinkedBlockingDeque<PCData>(10);
		// 建立3个生产者
		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		// 建立3个消费者
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);

		// 创建线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(p1);// 启动生产者线程
		threadPool.execute(p2);
		threadPool.execute(p3);

		threadPool.execute(c1);// 启动消费者线程
		threadPool.execute(c2);
		threadPool.execute(c3);

		Thread.sleep(10 * 1000);
		// 停止生产
		p1.stop();
		p2.stop();
		p3.stop();// 当消费者处理完缓冲区中所有数据，程序执行完毕

		Thread.sleep(3000);
		threadPool.shutdown();
	}
}