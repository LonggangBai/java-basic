package com.easyway.java.basic.sync;

/**
 * <pre>
 * 
 * public class Test{
 *     private String a = "test";
 *     public void print(){
 *         synchronized(a){//锁住a对象
 *             ...;
 *         }
 *     }
 *     public synchronized void t(){
 *         ...; //这个同步代码块不会因为print()而锁定.
 *     }
 * }
 * 执行print()，会给对象a加锁，注意不是给Test的对象加锁，也就是说 Test对象的其它synchronized方法不会因为print()而被锁。同步代码块执行完，则释放对a的锁。
 * 
 * 为了锁住一个对象的代码块而不影响该对象其它 synchronized块的高性能写法：
 * 
 * public class Test{
 *     private byte[] lock = new byte[0];
 *     public void print(){
 *         synchronized(lock){
 *             ...;
 *         }
 *     }
 *     public synchronized void t(){
 *         ...; 
 *     }
 * }
 * 执行print()，会给对象a加锁，注意不是给SyncVar的对象加锁，也就是说 SyncVar对象的其它synchronized方法不会因为print()而被锁。同步代码块执行完，则释放对a的锁。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class SyncVar {
	private String a = "test";

	public void print() {
		synchronized (a) {// 锁住a对象
		}
	}

	public synchronized void t() {
		// 这个同步代码块不会因为print()而锁定.
	}
}