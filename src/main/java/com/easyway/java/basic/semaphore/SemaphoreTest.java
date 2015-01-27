package com.easyway.java.basic.semaphore;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 用于控制同一时间内   一个对象的某个方法 最多被n条线程同时访问
 * 在执行要限制的方法前先用 semaphore.acquire();方法判断是否已经到达边界，
 * 如果到达，就阻塞，否则执行方法，并信号量+1
 * 当要释放一个信号量时，用	semaphore.release() ; 方法
 * @author Administrator
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		final SemaphoreTest st = new SemaphoreTest();
		final BoundedHashSet<String> set = st.getSet();
		final ExecutorService poll = Executors.newCachedThreadPool();

		for (int i = 0; i < 5 ; i++) {
			poll.execute(new Runnable() {
				@Override
				public void run() {
					try {
						set.add(Thread.currentThread().getName());
					} catch (Exception e) {  
						e.printStackTrace();
					}
				}
			});
		}
		
		for (int i = 0; i < 5 ; i++) {
			poll.execute(new Runnable() {
				@Override
				public void run() {
					try {
						set.remove(Thread.currentThread().getName());
					} catch (Exception e) {  
						e.printStackTrace();
					}
				}
			});
		}

	}

	//返回一个同时只能有两个线程访问的set
	public BoundedHashSet<String> getSet(){
		return new BoundedHashSet<String>(2);
	}

	class BoundedHashSet<T>{

		private final Set<T> set ;

		private final Semaphore semaphore ;

		public BoundedHashSet(int bound){
			this.set = Collections.synchronizedSet(new HashSet<T>());  
			semaphore = new Semaphore(bound, true);
		}

		public void add(T t) throws Exception{
			System.out.println("即将添加 " + t);
			semaphore.acquire();	//判断是否已经到达边界，如果到了，就阻塞
			set.add(t);
			System.out.println("成功添加  " + t);
		}

		public void remove(T t)throws Exception{
			System.out.println("即将移除  " + t);
			if(set.remove(t)){
				System.out.println("成功移除 " + t);
				semaphore.release() ;	//释放掉一个信号量
			}
		}

	}


}
