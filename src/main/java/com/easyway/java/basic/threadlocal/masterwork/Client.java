package com.easyway.java.basic.threadlocal.masterwork;

import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *     在整个计算中，master和worker 的执行完全是异步的，master不必等到每所有
 *     worker完成，就可以进行求和操作。在获得部分子任务结果时，就已经可以对结果进行计算，
 *     从而提高并发度和吞吐量。
 * </pre>
 * 
 * @author longgangbai
 *
 */
public class Client {
	public static void main(String[] args) {
		Master master = new Master(new SubWorker(), 5);// 指定5个
		for (int i = 0; i < 100; i++)
			master.submit(i);
		master.execute();
		int re = 0;
		Map<String, Object> resultMap = master.getResultMap();
		while (resultMap.size() > 0 || !master.isComplete()) {
			// 不需要等待所有的worker执行完就可以计算结果
			Set<String> keys = resultMap.keySet();
			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}
			Integer i = null;
			if (key != null)
				i = (Integer) resultMap.get(key);
			if (i != null)
				re += i;// 最终计算结果

			if (key != null)
				resultMap.remove(key);
		}
		System.out.println(re); // 打印最后计算结果
	}
}