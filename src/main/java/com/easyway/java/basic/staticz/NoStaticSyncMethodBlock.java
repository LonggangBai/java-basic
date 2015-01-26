package com.easyway.java.basic.staticz;

/**
 * <pre>
 * synchronized关键字有两种用法。第一种就是在《使用Synchronized关键字同步类方法》一文中所介绍的直接用在方法的定义中。另外一种就是synchronized块。我们不仅可以通过synchronized块来同步一个对象变量。也可以使用synchronized块来同步类中的静态方法和非静态方法。
 * 
 * synchronized块的语法如下：
 * 
 * public void method()  
 * {  
 *     … …  
 *     synchronized(表达式)  
 *     {  
 *         … …  
 *     }  
 * } 
 * 一、非静态类方法的同步   
 * 
 * 从《使用Synchronized关键字同步类方法》一文中我们知道使用synchronized关键字来定义方法就会锁定类中所有使用synchronzied关键字定义的静态方法或非静态方法，但这并不好理解。而如果使用synchronized块来达到同样的效果，就不难理解为什么会产生这种效果了。如果想使用synchronized块来锁定类中所有的同步非静态方法，需要使用this做为synchronized块的参数传入synchronized块国，代码如下：
 * 
 * 通过synchronized块同步非静态方法
 * 在上面的代码中的method1和method2方法中使用了synchronized块。而第017行的method3方法仍然使用synchronized关键字来定义方法。在使用同一个SyncBlock类实例时，这三个方法只要有一个正在执行，其他两个方法就会因未获得同步锁而被阻塞。在使用synchronized块时要想达到和synchronized关键字同样的效果，必须将所有的代码都写在synchronized块中，否则，将无法使当前方法中的所有代码和其他的方法同步。
 * 
 * 除了使用this做为synchronized块的参数外，还可以使用SyncBlock.this作为synchronized块的参数来达到同样的效果。
 * 
 * 在内类（InnerClass）的方法中使用synchronized块来时，this只表示内类，和外类(OuterClass)没有关系。但内类的非静态方法可以和外类的非静态方法同步。如在内类InnerClass中加一个method4方法，并使method4方法和SyncBlock的三个方法同步，代码如下：
 * 
 * 使内类的非静态方法和外类的非静态方法同步
 * 
 * public class SyncBlock  
 * {  
 *     … …  
 *     class InnerClass  
 *     {  
 *         public void method4()  
 *         {  
 *             synchronized(SyncBlock.this)  
 *             {  
 *                 … …   
 *             }  
 *         }  
 *     }  
 *     … …  
 * } 
 * 在上面SyncBlock类的新版本中，InnerClass类的method4方法和SyncBlock类的其他三个方法同步，因此，method1、method2、method3和method4
 * 四个方法在同一时间只能有一个方法执行。
 * 
 * Synchronized块不管是正常执行完，还是因为程序出错而异常退出synchronized块，当前的synchronized块所持有的同步锁都会自动释放。因此，在使用
 * synchronized块时不必担心同步锁的释放问题。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class NoStaticSyncMethodBlock {
	public void method1() {
		synchronized (this) // 相当于对method1方法使用synchronized关键字
		{
		}
	}

	public void method2() {
		synchronized (this) // 相当于对method2方法使用synchronized关键字
		{
		}
	}

	public synchronized void method3() {
	}
}