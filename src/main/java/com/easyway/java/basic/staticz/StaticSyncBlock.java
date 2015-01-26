package com.easyway.java.basic.staticz;

/**
 * <pre>
 * 由于在调用静态方法时，对象实例不一定被创建。因此，就不能使用this来同步静态方法，而必须使用Class对象来同步静态方法。代码如下：
 * 
 * 通过synchronized块同步静态方法
 * 
 * public class StaticSyncBlock  
 *   {  
 *       public static void method1()  
 *       {  
 *           synchronized(StaticSyncBlock.class)    
 *           {  
 *               … …  
 *           }  
 *       }  
 *       public static synchronized void method2()    
 *       {  
 *           … …  
 *       }  
 *   } 
 * 在同步静态方法时可以使用类的静态字段class来得到Class对象。在上例中method1和method2方法同时只能有一个方法执行。除了使用
 * class字段得到Class对象外，还可以使用实例的getClass方法来得到Class对象。上例中的代码可以修改如下：
 * 
 * 使用getClass方法得到Class对象
 * 
 * public class StaticSyncBlock  
 * {  
 *     public static StaticSyncBlock instance;   
 *     public StaticSyncBlock()  
 *     {  
 *         instance = this;  
 *     }  
 *     public static void method1()  
 *     {  
 *        synchronized(instance.getClass())  
 *        {  
 *               
 *        }  
 *     }  
 *        
 * }  
 * 在上面代码中通过一个public的静态instance得到一个StaticSyncBlock类的实例，并通过这个实例的getClass方法得到了Class对象
 * （一个类的所有实例通过getClass方法得到的都是同一个Class对象，因此，调用任何一个实例的getClass方法都可以）。我们还可以通过
 * Class对象使不同类的静态方法同步，如Test类的静态方法method和StaticSyncBlock类的两个静态方法同步，代码如下：
 * 
 * Test类的method方法和StaticSyncBlock类的method1、method2方法同步
 * 
 * public class Test  
 * {  
 *     public static void method()  
 *     {  
 *         synchronized(StaticSyncBlock.class)  
 *         {  
 *                
 *         }  
 *     }  
 * } 
 * 注意：在使用synchronized块同步类方法时，非静态方法可以使用this来同步，而静态方法必须使用Class对象来同步。它们互不影响。当然，也可以在非静态方法中使用Class对象来同步静态方法。但在静态方法中不能使用this来同步非静态方法。这一点在使用synchronized块同步类方法时应注意。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class StaticSyncBlock {
	public static StaticSyncBlock instance;

	public StaticSyncBlock() {
		instance = this;
	}

	public static void method3() {
		synchronized (instance.getClass()) {

		}
	}

	public static void method1() {
		synchronized (StaticSyncBlock.class) {
		}
	}

	public static synchronized void method2() {
	}

}
