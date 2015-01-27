package com.easyway.java.basic.sync;

/**
 * <pre>
 * 用法1
 * public class Test{
 *     public synchronized void print(){
 *         ....;
 *     } 
 * }
 * 某线程执行print()方法，则该对象将加锁。其它线程将无法执行该对象的所有synchronized块。
 * 
 * 用法2
 * public class Test{
 *     public void print(){
 *         synchronized(this){//锁住本对象
 *             ...;
 *         }
 *     }
 * }
 * 同用法1, 但更能体现synchronized用法的本质。
 * 某线程执行print()方法，则该对象将加锁。其它线程将无法执行该对象的所有synchronized块。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class SyncMethod {
	public synchronized void print() {
	}
}