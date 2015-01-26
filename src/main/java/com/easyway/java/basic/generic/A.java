package com.easyway.java.basic.generic;

/**
 * <pre>
 * Java泛型解析(04)：约束和局限性
 *      
 *     前两节，认识和学习了泛型的限定以及通配符，初学者可能需要一些时间去体会到泛型程序设计的好处和力量，特别是想成为库程序员的同学就需要下去体会通配符的运用了，应用程序员则需要掌握怎么使用泛型，这里针对泛型的使用中的约束和局限性做一个介绍性的讲解。
 * 
 * 不能用基本类型实例化类型参数
 *      这个很好理解，实例化泛型类时泛型参数不能使用基本类型，如List<int>这是不合法的，要存储基本类型则可以利用基本类型的包装类如List<Integer> 、List<Double>等，下面补充一下八种基本类型对应的包装类，和默认初始值
 * [code01]：
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * byte                 Byte            0  
 * short                Short           0  
 * int                  Integer         0  
 * long                 Long            0L  
 * float                FLoat           0.0F  
 * double               Double          0.0D  
 * char                 Character       '\u0000'  
 * boolean              Boolean         false  
 * 不能实例化类型参数
 *      Java中创建对象使用new关键字，但是泛型的类型参数不能用于实例化对象，如：
 * [code02]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public Couple() {wife = new T(); husband = new T();} // 错误  
 *      如果是在想要创建T对象，就必须使用Java反射来创建对象了，遗憾的是T.class在Java中也是不被支持使用的，所以一种弥补的方式，传入一个类型阐述为T的Class对象，如Class<T>
 * [code03]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 *  public static <T> Couple<T> createInstance(Class<T> clazz) {  
 *           try {  
 *                     return new Couple<T>(clazz.newInstance(), clazz.newInstance());  
 *          } catch (Exception e) {  
 *                     return null ;  
 *          }  
 * }  
 *      初学者对Java反射不熟悉不用着急，这里只要知道不能实例化类型参数即可，同理，不能实例化一个泛型数组，如
 * [code04]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T> T[] maxTwo(T[] values) {T[] array = new T[2];} // 错误  
 *      泛型构建数组是不合法的，因为这么构建在擦除之后构造的永远是new Object[2]，这不是我们所希望的结果。而且这样会导致一些运行时错误。为了更进一步说明隐患问题，来看看下面代码：
 * [code05]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T extends Comparable<T>> T[] maxTwo(T[] array) {  
 *      Object[] result = new Object[2];  
 *      return (T[]) result; // Type safety: Unchecked cast from Object[] to T[]  
 * }  
 *      这种方式会产生变异警告：Object[]转换为T[]是没有被检查的。我们来试一试这个调用： maxTwo(new String[] { "5", "7" , "9" });，运行后，发生了类型转换异常，因为方法在调用的时候将Object[]转换为String[]，失败的类型转化。怎么解决呢？同样这里可以使用Java发射来解决：
 * [code06]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T extends Comparable<T>> T[] maxTwo(T[] array) {  
 *      // Type safety: Unchecked cast from Object[] to T[]  
 *      return (T[]) Array.newInstance(array.getClass().getComponentType(), 2) ;  
 * }  
 * 不能声明参数化的数组
 *      Couple<Employee>[] couple = new Couple<Employee>[5] ;这种声明式不合法的，这里面有一个问题还是通过类型擦除机制来解释，类型擦除后couple的类型是Couple[]，考虑一下两种赋值方式：
 *      1.couple[0] = "wife"时，编译器会报错，这个很明显的错误，couple每个元素都是Couple类型。
 *      2.couple[0] = new Couple<String>()，类型擦除后这个可以通过数组检测，但仍然是错误的类型，因为couple在声明的时候定义的是Couple<Employee>，所以会出现问题。
 *      如果要存放参数化类型对象的集合，可以考虑使用ArrayList<Couple<Employee>>进行声明，而且Java中建议优先使用集合，这样既安全又有效。
 * 类型参数不能进行类型查询
 *      通常我们会使用if (arg1 instanceof Couple)来判断arg1是否属于Couple类型，有些爱思考的程序员可能会想更具体地去判断arg1类型是属于Couple<T>，指定类型参数，如：
 * [code07]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * if (arg1 instanceof Couple<Employee>){...} // 错误  
 *      Java虚拟机中没有泛型概念，所有instanceof 类型查询只查询原始类型，所以code07中的语法在Java中的不支持的。同理，在强制类型转换中也是不允许指定类型参数的，如：Couple<Employee> couple = (Couple<Employee>)arg1; // 错误
 * 不能抛出、不能捕获泛型类实例
 *      在Java中，public class GenericException <T> extends Exception {...}这种泛型类扩展子Throwable是不合法的，不能通过编译器。
 *      不能再catch子句中使用类型参数，如：
 * [code08]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T extends Throwable> void doSomething(Class<T> t) {  
 *      try {  
 *            // do something...  
 *      } catch (T e) {  
 *            e.printStackTrace();  
 *      }  
 *   }  // 错误  
 *      code08中的代码是不合法的，下面在异常声明中使用类型参数是合法的，例如：
 * [code09]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T extends Throwable> void doSomething(T t) throws T {  
 *           try {  
 *                     // do something...  
 *          } catch (Throwable e) {  
 *                    e.printStackTrace();  
 *                     throw t;  
 *          }  
 * }// 正确  
 * 泛型类中的类型参数不能用于静态上下文中
 *      怎么理解这个呢？看个例子就知道了，如一个泛型单例：
 * [code10]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public class Singleton <T>{  
 *   
 *            private static T instance;  
 *             
 *            public static T getInstance(){ ..}  
 * }  
 *      上述代码，是无法通过编译器的，怎么解释这个呢？试想假设这个程序可以运行，声明一个Singleton<String>和一个Singleton<Employee>，类型擦除之后都是Singleton，但只包含了一个instance域，这个域不可能同时是String类型的又是Employee类型的，所以假设是不成立的，顺便回顾了一下学校里学的反证法~ ~
 * 
 * 类型擦除后引起的冲突
 *      看一个泛型类：
 * [code11]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public class NameClash<T> {  
 *       public boolean equals(T value) {  
 *                return false ;  
 *      }  
 * }  
 *      从这个类的定义中来看，存在两个equals方法，一个是自身定义的public boolean equals(T value) {...}，一个是从Object继承的public boolean equals(Object obj) {...}，但类型擦除以后，前者方法成为了public boolean equals(Object value) {...}，而在一个类中同时存在两个方法名和参数一样的方法是不可能的，所以这里引发的冲突是没法通过编译器的。可以通过重新命名方法进行修正。
 *      擦除引起的冲突还体现在另一点上，再看一段错误的代码：
 * [code12]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * class Calendar implements Comparable<Calendar> {...}  
 * class GregorianCalendar extends Calendar implements Comparable<GregorianCalendar> {...}  
 *      上述代码是非法的，为什么？回顾一下类型擦除后，虚拟机会为Calendar 类合成桥方法，实现了Comparable<Calendar>获得一个桥方法：
 *      public int compareTo (Object o) {return compareTo((Calendar)o);}
 *      而实现了Comparable<GregorianCalendar >在类型擦除后，虚拟机为GregorianCalendar合成一个桥方法：     
 *      public int compareTo (Object o) {return compareTo((GregorianCalendar )o);}
 *      这样一来在GregorianCalendar类中存在两个一样的方法，这是不允许的。
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
public class A {

}
