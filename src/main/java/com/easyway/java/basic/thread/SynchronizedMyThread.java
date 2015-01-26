package com.easyway.java.basic.thread;

/**
 * <pre>
 * 
 * 要想使上面的单件模式变成线程安全的，只要为getInstance加上synchronized关键字即可。代码如下：
 * 
 * public static synchronized Singleton getInstance() {   } 
 * 当然，还有更简单的方法，就是在定义Singleton变量时就建立Singleton对象，代码如下：
 * 
 * private static final Singleton sample = new Singleton(); 
 * 然后在getInstance方法中直接将sample返回即可。这种方式虽然简单，但不知在getInstance方法中创建Singleton对象灵活。
 * 读者可以根据具体的需求选择使用不同的方法来实现单件模式。
 * 
 * 在使用synchronized关键字时有以下四点需要注意：
 * 
 * 1.  synchronized关键字不能继承。
 * 
 * 虽然可以使用synchronized来定义方法，但synchronized并不属于方法定义的一部分，因此，synchronized关键字不能被继承。
 * 如果在父类中的某个方法使用了synchronized关键字，而在子类中覆盖了这个方法，在子类中的这个方法默认情况下并不是同步的，而必须
 * 显式地在子类的这个方法中加上synchronized关键字才可以。当然，还可以在子类方法中调用父类中相应的方法，这样虽然子类中的方法不是
 * 同步的，但子类调用了父类的同步方法，因此，子类的方法也就相当于同步了。这两种方式的例子代码如下：
 * 
 * 在子类方法中加上synchronized关键字
 * 
 * class Parent  
 * {  
 *     public synchronized void method() {   }  
 * }  
 * class Child extends Parent  
 * {  
 *     public synchronized void method() {   }  
 * } 
 * 在子类方法中调用父类的同步方法
 * 
 * class Parent  
 * {  
 *     public synchronized void method() {   }  
 * }  
 * class Child extends Parent  
 * {  
 *     public void method() { super.method();   }  
 * } 
 * 2.  在定义接口方法时不能使用synchronized关键字。
 * 
 * 3.  构造方法不能使用synchronized关键字，但可以使用下节要讨论的synchronized块来进行同步。
 * 
 * 4.  synchronized可以自由放置。
 * 
 * 在前面的例子中使用都是将synchronized关键字放在方法的返回类型前面。但这并不是synchronized可放置唯一位置。在非静态方法中，
 * synchronized还可以放在方法定义的最前面，在静态方法中，synchronized可以放在static的前面，代码如下：
 * 
 * public synchronized void method();  
 * synchronized public void method();  
 * public static synchronized void method();  
 * public synchronized static void method();  
 * synchronized public static void method(); 
 * 但要注意，synchronized不能放在方法返回类型的后面，如下面的代码是错误的：
 * 
 * public void synchronized method();  
 * public static void synchronized method(); 
 * synchronized关键字只能用来同步方法，不能用来同步类变量，如下面的代码也是错误的。
 * 
 * public synchronized int n = 0;  
 * public static synchronized int n = 0; 
 * 虽然使用synchronized关键字同步方法是最安全的同步方式，但大量使用synchronized关键字会造成不必要的资源消耗以及性能损失。虽然从表面上看
 * synchronized锁定的是一个方法，但实际上synchronized锁定的是一个类。也就是说，如果在非静态方法method1和method2定义时都使用了synchronized，
 * 在method1未执行完之前，method2是不能执行的。静态方法和非静态方法的情况类似。但静态和非静态方法不会互相影响。看看如下的代码：
 * 
 * 运行结果如下：
 * 
 * 非静态的method1方法
 * 静态的method3方法
 * 
 * 从上面的运行结果可以看出，method2和method4在method1和method3未结束之前不能运行。因此，我们可以得出一个结论，如果在类中使用synchronized
 * 关键字来定义非静态方法，那将影响这个中的所有使用synchronized关键字定义的非静态方法。如果定义的是静态方法，那么将影响类中所有使用synchronized
 * 关键字定义的静态方法。这有点象数据表中的表锁，当修改一条记录时，系统就将整个表都锁住了，因此，大量使用这种同步方式会使程序的性能大幅度下降。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class SynchronizedMyThread extends Thread {
	public String methodName;

	public static void method(String s) {
		System.out.println(s);
		while (true)
			;
	}

	public synchronized void method1() {
		method("非静态的method1方法");
	}

	public synchronized void method2() {
		method("非静态的method2方法");
	}

	public static synchronized void method3() {
		method("静态的method3方法");
	}

	public static synchronized void method4() {
		method("静态的method4方法");
	}

	public void run() {
		try {
			getClass().getMethod(methodName).invoke(this);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) throws Exception {
		SynchronizedMyThread myThread1 = new SynchronizedMyThread();
		for (int i = 1; i <= 4; i++) {
			myThread1.methodName = "method" + String.valueOf(i);
			new Thread(myThread1).start();
			sleep(100);
		}
	}
}
