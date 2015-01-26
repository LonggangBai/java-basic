package com.easyway.java.basic.generic;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 
 *  Java泛型解析(03)：虚拟机执行泛型代码
 * 
 *      Java虚拟机是不存在泛型类型对象的，所有的对象都属于普通类，甚至在泛型实现的早起版本中，可以将使用泛型的程序编译为在1.0虚拟机上
 *      能够运行的class文件，这个向后兼容性后期被抛弃了，所以后来如果用Sun公司的编译器编译的泛型代码，是不能运行在Java5.0之前的虚拟机
 *      的，这样就导致了一些实际生产的问题，如一些遗留代码如何跟新的系统进行衔接，要弄明白这个问题，需要先了解一下虚拟机是怎么执行泛型代码的。
 *        虚拟机的一种机制：擦除类型参数，并将其替换成特定类型，没有指定特定类型用Object代替，如前文中的Couple<T>类，虚拟机擦除后：   
 * [code01]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public class Couple {  
 *       private Object wife ;  
 *       private Object husband ;  
 *   
 *       public Couple(Object  wife, Object  husband) {  
 *                this.wife = wife;  
 *                this.husband = husband;  
 *      }  
 *       public void setWife(Object  wife) {this. wife = wife;}  
 *       public void setHusband(Object  husband) {this. husband = husband;}  
 *        
 *       public Object  getWife() {return wife;}  
 *       public Object  getHusband() {return husband;}  
 * }  
 *      类型参数T是一个任意类型的，所以擦除后用Object代替了。不管是Couple<Employee>或者Couple<String>擦除后都成为了原始类
 *      Couple类，这就好比回到了泛型引入Java之前的普通类。所以这里重点围绕着擦除类型参数这个机制展开讲解。
 *      如有对类型参数有类型限定会怎么替换呢？擦除类型参数机制告诉我们，使用限定的类型代替，如果有多个，使用第一个代替，看一段代码：
 * [code02]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public class Period<T extends Comparable<T> & Serializable> {  
 *       private T begin;  
 *       private T end;  
 *   
 *       public Period(T one, T two) {  
 *                if (one.compareTo(two) > 0) {begin = two;end = one;  
 *               } else {begin = one;end = two;}  
 *      }  
 * }  
 *      code02擦除后，Period的原始类型如下：
 * [code03]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public class Period {  
 *       private Comparable begin;  
 *       private Comparable end;  
 *   
 *       public Period(Comparable one, Comparable two) {  
 *                if (one.compareTo(two) > 0) {begin = two; end = one;  
 *               } else {begin = one; end = two;}  
 *      }  
 * }  
 *      思考一下，如果将Period<T extends Comparable<T> & Serializable>写成Period<T extends Serializable  & Comparable<T>>
 *      会是怎么样呢?同理，擦除后原始类型用第一个Serializable代替，这样进行compareTo方法调用的时候，编译器会进行必要的强制类型转换，所以为了
 *      提高效率，将标签接口(没有任何方法的接口，也叫tagging接口)放在后面。
 *      先来看看虚拟机执行表达式的时候发生了什么，如：
 * [code04]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * Couple<Employee> couple = ...;  
 * Employee wife = couple.getWife();  
 *      擦除后，getWife()返回的是Object类型，然后虚拟机会插入强制类型转换，将Object转换为Employee，所以虚拟机实际上执行了两天指令：
 *      1.调用Couple.getWife()方法。
 *      2.将Object转换成Employee类型。
 *      再来看看虚拟机执行泛型方法的时候发生了什么，泛型方法如：
 * [code05]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public static <T extends Comparable<T>> max(T[] arrays) {... }  
 * 擦除后成了:  
 * public staticComoparable max(Comparable[] arrays) {... }  
 *      但是泛型方法的擦除会带来两个复杂的问题，且看第一个实例，一个实例：
 * [code06]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * 
 *      DateInterval类型擦除后，Period中的方法变成：
 *      public void setBegin(Object begin) {...}
 *      而DateInterval中的方法还是：
 *      public void setBegin(Date begin) {...}
 *      所以DateInterval从Period中继承了 public void setBegin(Object begin) {...}而自身又存在
 *      public void setBegin(Date begin) {...}方法，用户使用时问题发生了：
 * [code07]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * Period<Date> period  = new DateInterval(...);  
 * period.setBegin(new Date());  
 *      这里因为period引用指向了DateInterval实例，根据多态性，setBegin应该调用DateInterval对象的setBegin方法，
 *      可是这个擦除让Period中的 public void setBegin(Object begin) {...}被调用，导致了擦除与多态发生了冲突，
 *      怎么办呢？虚拟机此时会在DateInterval类中生成一个桥方法(bridge method)，调用过程发生了细微的变化：
 * [code08]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * public void setBegin(Object begin) {  
 *      setBegin((Date)begin);  
 *  }  
 *      有了这个合成的桥方法以后，code07中对setBegin的调用步骤如下：
 *       1.调用DateInterval.setBegin(Object)方法。
 *       2.DateInterval.setBegin(Object)方法调用DateInterval.setBegin(Date)方法。
 *      发现了吗，当我们在DateInterval中增加了getBegin方法之后会是什么样子的呢？是不是Peroid中有一个Object getBegin()的方法，
 *      而DateInterval中有一个Date getBegin()方法呢，这两个方法在Java中是不能同时存在的？可是Java5以后增加了一个协变类型，使得
 *      这里是被允许的，看看DateInterval中getBegin方法就知道了：
 * [code09]
 * [java] view plaincopy在CODE上查看代码片派生到我的代码片
 * @Override  
 * public Date getBegin(){ return super.getBegin(); }  
 *      这里用了@Override，说明是覆盖了父类的Object getBegin()方法，而返回值可以指定为父类中的返回值类型的子类，这就是协变类型，
 *      这是Java5以后才可以允许的，允许子类覆盖了方法后指定一个更严格的类型(子类型)。
 * 总结：
 *      1.记住一点，虚拟机中没有泛型，只有普通的类。
 *      2.所有泛型的类型参数都用它们限定的类型代替，没有限定则用Object。
 *      3.为了保持类型安全性，虚拟机在有必要时插入强制类型转换。
 *      4.桥方法的合成用来保持多态性。
 *      5.协变类型允许子类覆盖方法后返回一个更严格的类型。
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
class Period<T extends Comparable<T> & Serializable> {
	private T begin;
	private T end;

	public Period(T one, T two) {
		if (one.compareTo(two) > 0) {
			begin = two;
			end = one;
		} else {
			begin = one;
			end = two;
		}
	}

	public void setBegin(T begin) {
		this.begin = begin;
	}

	public void setEnd(T end) {
		this.end = end;
	}

	public T getBegin() {
		return begin;
	}

	public T getEnd() {
		return end;
	}
}

public class DateInterval extends Period<Date> {

	public DateInterval(Date one, Date two) {
		super(one, two);
	}

	public void setBegin(Date begin) {
		super.setBegin(begin);
	}
}
