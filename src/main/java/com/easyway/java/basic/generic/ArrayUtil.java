package com.easyway.java.basic.generic;

/**
 * <pre>
 * Java泛型解析(02)：通配符限定
 * 
 *      考虑一个这样的场景，计算数组中的最大元素。
 * [code01]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public class ArrayUtil {  
 *        public static <T> T max(T[] array) {  
 *                 if (array == null || 0 == array.length) { return null ;}  
 *                T max = array[0];  
 *                 for (int i = 1; i < array.length; i++) {  
 *                           if (max.compareTo(array[i]) < 0) {max = array[i];}  
 *                }  
 *                 return max;  
 *       }  
 *  }  
 *      仔细看看code01里面的代码(代码不完整)，使用类型参数T定义一个max局部变量，这几意味着这个max可以是任意的类型，那么max.compareTo(array[i]) 
 *      方法的调用的前提是T所属的类中有compareTo方法，怎么能做到这一点呢？别着急，让我们来看看如何给类型参数进行限定，现在来对code01中的代码进行完善。
 * [code02]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public class ArrayUtil {  
 *        public static <T extends Comparable<T>> T max(T[] array) {  
 *                 if (array == null || 0 == array.length) { return null ;}  
 *                T max = array[0];  
 *                 for (int i = 1; i < array.length; i++) {  
 *                           if (max.compareTo(array[i]) < 0) {max = array[i];}  
 *                }  
 *                 return max;  
 *       }  
 *  }  
 *      注意看，我们定义类型参数的变化：<T extends Comparable<T>>，这里将T类型限定在Comparable及其所有的子类。是不是很好奇Comparable明明是
 *      一个interfacce，根据所学知识判断，实现interface用的关键字是implements，为什么呢？
 *      <T extends Bounding Type>，表示T类型应该是绑定类型及其子类型(subType)，T和绑定类型可以是类或者接口，使用extends关键字因为它更接近于
 *      子类的概念，另外Java设计者并不打算为Java添加新的关键字如：sub
 *      如果给T限定多个类型，则需要使用符号"&"，如下面格式
 * [code03]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * <T extends Runnable & Serializable>  
 *      细心的读者可能会发现，这里限定的都是interface，如果限定为class是不是也这么自由的呢？先不急着回答这个问题，我们知道Java中可以实现多个接口，
 *      而继承只能是单继承，可想而知，当我们给T限定类型的时候，限定为某个class的时候是有限制的，看看下面几组泛型限定的代码
 * [code04]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * <T extends Runnable & Serializable & ArrayList> // 错误  
 * <T extends Runnable & ArrayList & Serializable> // 错误  
 * <T extends ArrayList & LinkedList & Serializable> // 错误  
 * <T extends ArrayList & Runnable& Serializable> // 正确  
 *      不难看出，如果要限定T为class的时候，就有一个非常严格的规则，这个class只能放在第一个，最多只能有一个class。其实很容易理解，这样一来，就能够
 *      严格控制T类型是单继承的，遵循Java的规范。
 * 小结：
 *      1.类型限定只能限定某个类型及其子类，使用关键字extends。
 *      2.多个类型参数用","隔开，如：<K, V>，多个限定类型用"&"隔开，如： <T extends Runnable & Serializable>
 *      3.限定interface的时候，对interface的个数和顺序无严格要求，限定class时，则需要将class类型置于第一个，且最多只能存在一个class类型。
 * 钻牛角尖：
 *      问：类型限定中可以通过 extends 来限定子类型，是否可以通过类似super关键字来限定超类型呢？ 
 *     答：哈哈，问的好，接下来一一揭晓。
 *     比较遗憾的是，类似<T extends Runnable & Serializable>这样的泛型限定子类的语法，来限定超类是没有成为Java中的一个语法规范的，例如：
 * [code05]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * <T super File & Runnable> // 错误  
 *      code03中的类型参数的定义是错误的，至少目前Java中没有这样的规范来支撑这种语法，如何解释这个问题，笔者得花一番心思了...
 * 不得不请教面向对象先生了：
 *      1.面向接口(抽象)编程，而非面向实现编程。这个设计原则告诉我们，方法调用通过高层的抽象类或者接口来进行，具体调用的方法体就是我们实际运行
 *      时期传递的具体实现类的实例，这也是多态的一种体现。我们实现自己的泛型是提供后期应用程序员使用的，限定一个子类，这就需要我们通过子类来调用方法，
 *      而调用的方法体则是这个类的超类的实例，继承结构越往上就可能是abstract的，或者是interface，抽象类和接口是无法实例化对象，这种反设计
 *      让调用面临失败，一旦限定的这个类就是抽象的或者是接口，必定会造成这个泛型类或泛型方法无法使用，导致设计失败。举个例子：
 * [code06]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T super Runnable> void test(T runner) {  
 *      runner.run();  
 * }  
 *      这个T类型限定为Runnable的超类，这个Runnable是一个接口，无法实例化对象，方法参数runner就是一个不存在的实例，所以这是一个失败的设计，
 *      而且这种语法也无法通过编译器。
 *      面向对象先生的解释对初学者可能有点晦涩难懂，没关系，这里只要知道Java是不能支持这种泛型限定的。无论从设计角度，还是从后期扩展的角度，都是说不过去的。
 *      但是不能这样定义泛型，并不代表Java泛型中就没有 super 关键字了，接下来说说泛型中的通配符类型，有了前面的基础，这里恐怕不是问题了。
 * 通配符类型
 *      通配符类型，相比于固定的泛型类型，它是一个巧妙的解决方案。如：
 * [code07]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * Couple<? extends Employee>  
 *      表示Couple的泛型类型是Employee或者其子类，Couple<Manager>满足，而Couple<File>不满足了。通配符用 "?" 表示。
 *      我们来打印一下夫妇类中的wife：
 * [code08]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static void printWife(Couple<Employee> couple) {  
 *      Employee wife = couple.getWife();  
 *           System. out.println(wife);  
 * }  
 *      code08中的方法参数只能将Employee组成的夫妇传入，貌似经理的如Couple<Manager>就不能了，这有点不合适了，搞得好像Manager还不能结婚了。
 *      所以要想让Manager也能结婚并打印其wife，需要更改我们的设计了：
 * [code09]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static void printWife(Couple<? extends Employee> couple) {  
 *      Employee wife = couple.getWife();  
 *           System. out .println(wife);  
 * }  
 *      通配符的子类型限定的语法与文章一开始介绍的类型限定有点相似，但是这里有些细节的秘密。
 * [code10]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T extends Comparable<T>> T max(T[] array) {...}  
 * public static void printWife(Couple<? extends Employee> couple) {...}  
 *      前者T是预定义的类型参数，T可以作为一个具体的类型来定义方法的参数类型，局部变量等，T的作用域是整个方法(方法返回值，参数，方法体中局部变量)，
 *      这种设计是为了给使用者带来方便，将参数类型的指定权有限制地交给了使用者。而后者中不存在类型参数的定义，max方法参数的类型是预先定义好的Couple类型，
 *      使用者无法在使用的时候指定其他类型，但可以有限制地指定Couple中的泛型参数的类型，
 *      ? extends Employee 自身不能单独使用，可以理解为只能寄生在其他泛型类中，作为泛型类一个具体的类型参数，通常用于定义阶段，如下面：
 * [code11]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static ? extends Employee printWife() {...} // 错误  
 * public static void printWife(? extends Empoyee employee) {...} // 错误  
 *      使用通配符来定义方法返回值和方法的参数类型在Java中是不允许的！
 *      弄清楚了前面类型限定和通配符的区别以后，再引入通配符的超类型限定就不是那么难以理解了。
 * 通配符的超类型限定：
 *      和前面子类型的限定一样，用"?"表示通配符，它的存在必须存在泛型类的类型参数中，如：
 * [code12]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * Couple<? super Manager>  
 *      格式跟通配符限定子类型一样，用了关键字super，但是这两种方式的通配符存在一个隐蔽的区别，让我们来揭晓吧，先看看下面代码：
 * [code13]、
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * Couple<Manager> couple = new Couple<Manager>(new Manager(),new Manager());  
 * Couple<? extends Employee> couple2 = couple;  
 * Employee wife = couple2.getWife();  
 * // couple2.setWife(new Manager()); // 此处编译错误  
 *      Couple<? extends Employee>定义了couple2后可以将getWife和setWife想象成如下：
 * [code14]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * ? extends Employee getWife() {...}  
 * void setWife(? extends Employee) {...}  
 *      getWife是可以通过的，因为将一个返回值的引用赋给超类Employee是完全可以的，而setWife方法接受的是一个Employee的子类，具体是什么子类，编译器并不知道，
 *      拒绝传递任何特定的类型，所以couple2.setWife(new Manager())是不能被调用的。所以通配符的子类限定适用于读取。
 *      在来看看通配符的超类型限定，即Couple<? super Manager>，getWife和setWife可以想象成：
 * [code15]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * ? super Manager getWife() {...}  
 * void setWife(? super Manager) {...}  
 *      getWife方法的返回值是Manager的超类型，而Manger的超类型是得不到保证的，虚拟器会将它会给Object，而setWife方法是需要的是Manager的超类型，
 *      所以传入任意Manager都是允许的，所以通配符的超类型限定适用于写入。
 * 无限定通配符
 *      无限定通配符去除了超类型和子类型的规则，仅仅用一个"?"表示，并且也只能用于指定泛型类的类型参数中。如Couple<?>的形式，此时getWife和setWife方法如：
 * [code16]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * ? getWife() {...}  
 * void setWife(?) {...}  
 *      getWife返回值直接付给了Object，而setWife方法是不允许调用的。那么既然这么脆弱，牛逼的Java设计者为什么还要引入这种通配符呢？
 *      在一些简单的操作中，五限定通配符还是有用武之地的，比如：
 * [code17]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static boolean isCoupleComplete(Couple<?> couple) {  
 *     return couple.getWife() != null && couple.getHusband() != null;  
 * }  
 *      这个方法体中，getWife和getHusband返回值都是Object类型的，此时我们只需要判断是否为null即可，而不需要知道具体的类型是什么，
 *      这就发挥了无限定通配符的作用了。发动脑经想一想，这个方法用文章开始所提到类型限定是否可以代替呢？自我思考中...
 * [code18]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T> boolean isCoupleComplete(Couple<T> couple) {  
 *      return couple.getWife() != null && couple.getHusband() != null ;  
 * }  
 * public static <T extends Employee> boolean isCoupleComplete(Couple<T> couple) {  
 *      return couple.getWife() != null && couple.getHusband() != null ;  
 * }  
 *      到这里，爱思考的读者可能在思考一个问题，通配符代表了泛型类中的参数类型，在方法体中，怎么去捕获这个参数类型呢?这里考虑三种通配符的捕获
 *      1.Couple<? extends Employee> couple：getWife返回Employee
 *      2.Couple<? super Manager> couple：无法捕获，getWife返回Object
 *      3.Couple<?> couple：无法捕获，getWife返回Object
 *      悲催了，只有第一种能捕获，怎么办呢？别着急，看看下面的小魔术：
 * [code19]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static void print(Couple<?> couple) {  
 *        printHelp(couple);  
 *  }  
 *  public static <T> void printHelp(Couple<T> couple) {  
 *       T husband = couple.getHusband();  
 *       T wife = couple.getWife();  
 *       couple.setHusband(wife);  
 *       couple.setWife(husband);  
 *       System. out.println(husband);  
 *       System. out.println(wife);  
 *   }  
 *      当需要捕获通配符的时候，可以借助前面所学的类型参数进行辅助，其实这是一个多余的动作，基本上用不到这么麻烦，这么做是为了把通配符和泛型限定联系起来，巩固一下之前的学习。
 * 总结：
 *      1.泛型参数的限定，使用extends关键字，限定多个类型时用"&"隔开。如：<T extends Runnable& Serializable> 
 *      2.泛型参数限定中，如果限定的类型是class而不是interface，则class必须放在限定类表中的第一个，且最多只能存在一个class。如：
 *      <T extends ArrayList & Runnable& Serializable> 
 *      3.通配符只能用在泛型类的泛型参数中，不能单独使用。如Couple<?>、Couple<? extends Employee> 、Couple<? super Manager>
 *      4.通配符的超类型限定适用于写入，通配符的子类型限定适用于读取，无限定通配符适用于一些非null判断等简单操作。
 *      5.通配符的捕获可以借助泛型类型限定来辅助。
 *      这一节内容比较多，需要花点时间好好消化，体会总结中的5点，下一节，说一个深刻点的话题，虚拟机中的针对泛型代码的擦除。
 * 
 * Java泛型解析(01)：认识泛型
 * Java泛型解析(02)：通配符限定
 * Java泛型解析(03)：虚拟机执行泛型代码
 * Java泛型解析(04)：约束和局限性
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class ArrayUtil {
	public static <T extends Comparable<T>> T max(T[] array) {
		if (array == null || 0 == array.length) {
			return null;
		}
		T max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (max.compareTo(array[i]) < 0) {
				max = array[i];
			}
		}
		return max;
	}
}