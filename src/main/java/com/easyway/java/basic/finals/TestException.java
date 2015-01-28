package com.easyway.java.basic.finals;

/**
 * <pre>
 * 1. 引子
 * 
 * try…catch…finally恐怕是大家再熟悉不过的语句了，而且感觉用起来也是很简单，逻辑上似乎也是很容易理解。不过，我亲自体验的“教训”告诉我，
 * 这个东西可不是想象中的那么简单、听话。不信？那你看看下面的代码，“猜猜”它执行后的结果会是什么？不要往后看答案、也不许执行代码看真正答案哦。
 * 如果你的答案是正确，那么这篇文章你就不用浪费时间看啦。
 * 
 * 现在公布正确答案：
 * 
 * i=2
 * i=1
 * testEx2, catch exception
 * testEx2, finally; return value=false
 * testEx1, finally; return value=false
 * testEx, finally; return value=false
 * 
 * 注意说明：
 * 
 * finally语句块不应该出现 应该出现return。上面的return ret最好是其他语句来处理相关逻辑。
 * 
 * 小结：
 * 
 * try 块：用于捕获异常。其后可接零个或多个catch块，如果没有catch块，则必须跟一个finally块。
 * catch 块：用于处理try捕获到的异常。
 * finally 块：无论是否捕获或处理异常，finally块里的语句都会被执行。当在try块或catch块中遇到return语句时，finally语句块将在方法返回之前被执行。在以下4种特殊情况下，finally块不会被执行：
 * 1）在finally语句块中发生了异常。
 * 2）在前面的代码中用了System.exit()退出程序。
 * 3）程序所在的线程死亡。
 * 4）关闭CPU。
 * 
 * 3. try-catch-finally 规则(异常处理语句的语法规则）：
 * 
 * 1)  必须在 try 之后添加 catch 或 finally 块。try 块后可同时接 catch 和 finally 块，但至少有一个块。
 * 2) 必须遵循块顺序：若代码同时使用 catch 和 finally 块，则必须将 catch 块放在 try 块之后。
 * 3) catch 块与相应的异常类的类型相关。
 * 4) 一个 try 块可能有多个 catch 块。若如此，则执行第一个匹配块。即Java虚拟机会把实际抛出的异常对象依次和各个catch代码块声明的异常类型匹配，如果异常对象为某个异常类型或其子类的实例，就执行这个catch代码块，不会再执行其他的 catch代码块
 * 5) 可嵌套 try-catch-finally 结构。
 * 6) 在 try-catch-finally 结构中，可重新抛出异常。
 * 7) 除了下列情况，总将执行 finally 做为结束：JVM 过早终止（调用 System.exit(int)）；在 finally 块中抛出一个未处理的异常；计算机断电、失火、或遭遇病毒攻击。
 * 
 * 4. try、catch、finally语句块的执行顺序:
 * 
 * 1)当try没有捕获到异常时：try语句块中的语句逐一被执行，程序将跳过catch语句块，执行finally语句块和其后的语句；
 * 
 * 2)当try捕获到异常，catch语句块里没有处理此异常的情况：当try语句块里的某条语句出现异常时，而没有处理此异常的catch语句块时，此异常将会抛给JVM处理，finally语句块里的语句还是会被执行，但finally语句块后的语句不会被执行；
 * 
 * 3)当try捕获到异常，catch语句块里有处理此异常的情况：在try语句块中是按照顺序来执行的，当执行到某一条语句出现异常时，程序将跳到catch语句块，并与catch语句块逐一匹配，找到与之对应的处理程序，其他的catch语句块将不会被执行，而try语句块中，出现异常之后的语句也不会被执行，catch语句块执行完后，执行finally语句块里的语句，最后执行finally语句块后的语句；
 * 
 * 图示try、catch、finally语句块的执行：
 * 
 * 
 * 
 *  图2  图示try、catch、finally语句块的执行
 * 
 * 4.2 抛出异常
 * 
 * 任何Java代码都可以抛出异常，如：自己编写的代码、来自Java开发环境包中代码，或者Java运行时系统。无论是谁，都可以通过Java的throw语句抛出异常。从方法中抛出的任何异常都必须使用throws子句。
 * 
 * 1. throws抛出异常
 * 
 * 如果一个方法可能会出现异常，但没有能力处理这种异常，可以在方法声明处用throws子句来声明抛出异常。例如汽车在运行时可能会出现故障，汽车本身没办法处理这个故障，那就让开车的人来处理。
 * 
 * throws语句用在方法定义时声明该方法要抛出的异常类型，如果抛出的是Exception异常类型，则该方法被声明为抛出所有的异常。多个异常可使用逗号分割。throws语句的语法格式为：
 * methodname throws Exception1,Exception2,..,ExceptionN  
 * {  
 * }
 * 方法名后的throws Exception1,Exception2,…,ExceptionN 为声明要抛出的异常列表。当方法抛出异常列表的异常时，方法将不对这些类型及其子类类型的异常作处理，而抛向调用该方法的方法，由他去处理。例如：
 * import java.lang.Exception;  
 * public class TestException {  
 *     static void pop() throws NegativeArraySizeException {  
 *         // 定义方法并抛出NegativeArraySizeException异常  
 *         int[] arr = new int[-3]; // 创建数组  
 *     }  
 *    
 *     public static void main(String[] args) { // 主方法  
 *         try { // try语句处理异常信息  
 *             pop(); // 调用pop()方法  
 *         } catch (NegativeArraySizeException e) {  
 *             System.out.println("pop()方法抛出的异常");// 输出异常信息  
 *         }  
 *     }  
 *    
 * }
 * 使用throws关键字将异常抛给调用者后，如果调用者不想处理该异常，可以继续向上抛出，但最终要有能够处理该异常的调用者。
 * 
 * pop方法没有处理异常NegativeArraySizeException，而是由main函数来处理。
 * 
 * Throws抛出异常的规则：
 * 
 * 1) 如果是不可查异常（unchecked exception），即Error、RuntimeException或它们的子类，那么可以不使用throws关键字来声明要抛出的异常，编译仍能顺利通过，但在运行时会被系统抛出。
 * 
 * 2）必须声明方法可抛出的任何可查异常（checked exception）。即如果一个方法可能出现受可查异常，要么用try-catch语句捕获，要么用throws子句声明将它抛出，否则会导致编译错误
 * 
 * 3)仅当抛出了异常，该方法的调用者才必须处理或者重新抛出该异常。当方法的调用者无力处理该异常的时候，应该继续抛出，而不是囫囵吞枣。
 * 
 * 4）调用方法必须遵循任何可查异常的处理和声明规则。若覆盖一个方法，则不能声明与覆盖方法不同的异常。声明的任何异常必须是被覆盖方法所声明异常的同类或子类。
 * 
 * 例如：
 * 
 * 判断一个方法可能会出现异常的依据如下：
 * 
 * 1）方法中有throw语句。例如，以上method7()方法的catch代码块有throw语句。
 * 
 * 2）调用了其他方法，其他方法用throws子句声明抛出某种异常。例如，method3()方法调用了method1()方法，method1()方法声明抛出IOException，因此，在method3()方法中可能会出现IOException。
 * 
 * 2. 使用throw抛出异常
 * 
 * throw总是出现在函数体中，用来抛出一个Throwable类型的异常。程序会在throw语句后立即终止，它后面的语句执行不到，然后在包含它的所有try块中（可能在上层调用函数中）从里向外寻找含有与其匹配的catch子句的try块。
 * 
 * 我们知道，异常是异常类的实例对象，我们可以创建异常类的实例对象通过throw语句抛出。该语句的语法格式为：
 * 
 * throw new exceptionname;
 * 
 * 例如抛出一个IOException类的异常对象：
 * 
 * throw new IOException;
 * 
 * 要注意的是，throw 抛出的只能够是可抛出类Throwable 或者其子类的实例对象。下面的操作是错误的：
 * 
 * throw new String(“exception”);
 * 
 * 这是因为String 不是Throwable 类的子类。
 * 
 * 如果抛出了检查异常，则还应该在方法头部声明方法可能抛出的异常类型。该方法的调用者也必须检查处理抛出的异常。
 * 
 * 如果所有方法都层层上抛获取的异常，最终JVM会进行处理，处理也很简单，就是打印异常消息和堆栈信息。如果抛出的是Error或RuntimeException，则该方法的调用者可选择处理该异常。
 * 
 * package Test;  
 * import java.lang.Exception;  
 * public class TestException {  
 *     static int quotient(int x, int y) throws MyException { // 定义方法抛出异常  
 *         if (y < 0) { // 判断参数是否小于0  
 *             throw new MyException("除数不能是负数"); // 异常信息  
 *         }  
 *         return x/y; // 返回值  
 *     }  
 *     public static void main(String args[]) { // 主方法  
 *         int  a =3;  
 *         int  b =0;   
 *         try { // try语句包含可能发生异常的语句  
 *             int result = quotient(a, b); // 调用方法quotient()  
 *         } catch (MyException e) { // 处理自定义异常  
 *             System.out.println(e.getMessage()); // 输出异常信息  
 *         } catch (ArithmeticException e) { // 处理ArithmeticException异常  
 *             System.out.println("除数不能为0"); // 输出提示信息  
 *         } catch (Exception e) { // 处理其他异常  
 *             System.out.println("程序发生了其他的异常"); // 输出提示信息  
 *         }  
 *     }  
 *    
 * }  
 * class MyException extends Exception { // 创建自定义异常类  
 *     String message; // 定义String类型变量  
 *     public MyException(String ErrorMessagr) { // 父类方法  
 *         message = ErrorMessagr;  
 *     }  
 *    
 *     public String getMessage() { // 覆盖getMessage()方法  
 *         return message;  
 *     }  
 * }
 * 4.3 异常链
 * 
 * 1) 如果调用quotient(3,-1)，将发生MyException异常，程序调转到catch (MyException e)代码块中执行；
 * 
 * 2) 如果调用quotient(5,0)，将会因“除数为0”错误引发ArithmeticException异常，属于运行时异常类，由Java运行时系统自动抛出。quotient（）方法没有捕捉ArithmeticException异常，Java运行时系统将沿方法调用栈查到main方法，将抛出的异常上传至quotient（）方法的调用者：
 * 
 * int result = quotient(a, b); // 调用方法quotient()
 * 
 * 由于该语句在try监控区域内，因此传回的“除数为0”的ArithmeticException异常由Java运行时系统抛出，并匹配catch子句：
 * 
 * catch (ArithmeticException e) { // 处理ArithmeticException异常
 * System.out.println(“除数不能为0″); // 输出提示信息
 * } 
 * 
 * 处理结果是输出“除数不能为0”。Java这种向上传递异常信息的处理机制，形成异常链。
 * 
 * Java方法抛出的可查异常将依据调用栈、沿着方法调用的层次结构一直传递到具备处理能力的调用方法，最高层次到main方法为止。如果异常传递到main方法，而main不具备处理能力，也没有通过throws声明抛出该异常，将可能出现编译错误。
 * 
 * 3)如还有其他异常发生，将使用catch (Exception e)捕捉异常。由于Exception是所有异常类的父类，如果将catch (Exception e)代码块放在其他两个代码块的前面，后面的代码块将永远得不到执行，就没有什么意义了，所以catch语句的顺序不可掉换。
 * 
 * 4.4 Throwable类中的常用方法
 * 
 * 注意：catch关键字后面括号中的Exception类型的参数e。Exception就是try代码块传递给catch代码块的变量类型，e就是变量名。catch代码块中语句”e.getMessage();”用于输出错误性质。通常异常处理常用3个函数来获取异常的有关信息:
 * 
 * getCause()：返回抛出异常的原因。如果 cause 不存在或未知，则返回 null。
 * 
 * getMeage()：返回异常的消息信息。
 * 
 * printStackTrace()：对象的堆栈跟踪输出至错误输出流，作为字段 System.err 的值。
 * 
 * 有时为了简单会忽略掉catch语句后的代码，这样try-catch语句就成了一种摆设，一旦程序在运行过程中出现了异常，就会忽略处理异常，而错误发生的原因很难查找。
 * 
 * 5.Java常见异常
 * 
 * 在Java中提供了一些异常用来描述经常发生的错误，对于这些异常，有的需要程序员进行捕获处理或声明抛出，有的是由Java虚拟机自动进行捕获处理。Java中常见的异常类:
 * 
 * 1. runtimeException子类:
 * 
 *     1、 java.lang.ArrayIndexOutOfBoundsException
 * 
 *     数组索引越界异常。当对数组的索引值为负数或大于等于数组大小时抛出。
 * 
 *     2、java.lang.ArithmeticException
 * 
 *     算术条件异常。譬如：整数除零等。
 * 
 *     3、java.lang.NullPointerException
 * 
 *     空指针异常。当应用试图在要求使用对象的地方使用了null时，抛出该异常。譬如：调用null对象的实例方法、访问null对象的属性、计算null对象的长度、使用throw语句抛出null等等
 * 
 *     4、java.lang.ClassNotFoundException
 * 
 *     找不到类异常。当应用试图根据字符串形式的类名构造类，而在遍历CLASSPAH之后找不到对应名称的class文件时，抛出该异常。
 * 
 *    5、java.lang.NegativeArraySizeException  数组长度为负异常
 * 
 *    6、java.lang.ArrayStoreException 数组中包含不兼容的值抛出的异常
 * 
 *    7、java.lang.SecurityException 安全性异常
 * 
 *    8、java.lang.IllegalArgumentException 非法参数异常
 * 
 * 2.IOException
 * 
 * IOException：操作输入流和输出流时可能出现的异常。
 * 
 * EOFException   文件已结束异常
 * 
 * FileNotFoundException   文件未找到异常
 * 
 * 3. 其他
 * 
 * ClassCastException    类型转换异常类
 * 
 * ArrayStoreException  数组中包含不兼容的值抛出的异常
 * 
 * SQLException   操作数据库异常类
 * 
 * NoSuchFieldException   字段未找到异常
 * 
 * NoSuchMethodException   方法未找到抛出的异常
 * 
 * NumberFormatException    字符串转换为数字抛出的异常
 * 
 * StringIndexOutOfBoundsException 字符串索引超出范围抛出的异常
 * 
 * IllegalAccessException  不允许访问某类异常
 * 
 * InstantiationException  当应用程序试图使用Class类中的newInstance()方法创建一个类的实例，而指定的类对象无法被实例化时，抛出该异常
 * 
 *   6.自定义异常
 * 
 * 使用Java内置的异常类可以描述在编程时出现的大部分异常情况。除此之外，用户还可以自定义异常。用户自定义异常类，只需继承Exception类即可。
 * 
 * 在程序中使用自定义异常类，大体可分为以下几个步骤。
 * 
 * （1）创建自定义异常类。
 * （2）在方法中通过throw关键字抛出异常对象。
 * （3）如果在当前抛出异常的方法中处理异常，可以使用try-catch语句捕获并处理；否则在方法的声明处通过throws关键字指明要抛出给方法调用者的异常，继续进行下一步操作。
 * （4）在出现异常方法的调用者中捕获并处理异常。
 * 
 * 在上面的“使用throw抛出异常”例子已经提到了。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class TestException {
	public TestException() {
	}

	boolean testEx() throws Exception {
		boolean ret = true;
		try {
			ret = testEx1();
		} catch (Exception e) {
			System.out.println("testEx, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx, finally; return value=" + ret);
			return ret;
		}
	}

	boolean testEx1() throws Exception {
		boolean ret = true;
		try {
			ret = testEx2();
			if (!ret) {
				return false;
			}
			System.out.println("testEx1, at the end of try");
			return ret;
		} catch (Exception e) {
			System.out.println("testEx1, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx1, finally; return value=" + ret);
			return ret;
		}
	}

	boolean testEx2() throws Exception {
		boolean ret = true;
		try {
			int b = 12;
			int c;
			for (int i = 2; i >= -2; i--) {
				c = b / i;
				System.out.println("i=" + i);
			}
			return true;
		} catch (Exception e) {
			System.out.println("testEx2, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx2, finally; return value=" + ret);
			return ret;
		}
	}

	public static void main(String[] args) {
		TestException testException1 = new TestException();
		try {
			testException1.testEx();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}