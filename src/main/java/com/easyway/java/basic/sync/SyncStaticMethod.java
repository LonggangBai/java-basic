package com.easyway.java.basic.sync;

/**
 * 
 * <pre>
 * 静态方法的锁
 * public class Test{
 *     public synchronized static void execute(){
 *         ...;
 *     }
 * }
 * 效果同
 * 
 * public class Test{
 *     public static void execute(){
 *         synchronized(TestThread.class){
 *             ...;
 *         }
 *     }
 * }
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class SyncStaticMethod {
	public synchronized static void execute() {
		// ...;
	}
}