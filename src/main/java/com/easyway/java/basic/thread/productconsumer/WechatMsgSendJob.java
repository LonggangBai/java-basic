package com.easyway.java.basic.thread.productconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WechatMsgSendJob {

	class WechatMsg {

	}

	private static Log log = LogFactory.getLog(WechatMsgSendJob.class);
	private static BlockingQueue<WechatMsg> msgs = new LinkedBlockingQueue<WechatMsg>(10000);

	public static void addMsg(WechatMsg msg) {
		try {
			msgs.put(msg);
		} catch (InterruptedException e) {
			log.error("addMsg error", e);
		}
	}

	// init方法
	public void init() {
		try {
			// 启动线程池，8个线程 不停的处理队列中的数据
			ExecutorService consumerService = Executors.newFixedThreadPool(8);
			for (int i = 0; i < 8; i++) {
				consumerService.submit(new Runnable() {
					public void run() {
						while (true) {
							try {
								WechatMsg msg = msgs.take();
								if (msg != null) {
									// 这里处理数据

								}
							} catch (Exception e) {
								log.error("callWechatSendApi error", e);
							}

						}
					}
				});
			}
		} catch (Exception e) {
			log.error("sendMsg error", e);
		}
	}
}