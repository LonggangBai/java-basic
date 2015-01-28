package com.easyway.java.basic.clazz;

/**
 * 
 * <pre>
 * 介绍一下java的引用类型以及对象创建过程
 * 1. 引用类型（reference type）：引用类型是用在对象上的。一个对象可以被多个引用所指向，但同一时刻，每个引用只能指向唯一的一个对象。如果一
 * 个对象被多个引用所指向，那么无论哪个引用对对象的属性进行了修改，都会反映到其他的引用当中。
 * 
 * 2.在java里，永远无法直接操作对象，对对象的所有操作都是通过对象的引用来间接完成的。
 * People people=new People(); //这句话在内存的堆中生成了一个 People类型的对象，并且在内存的栈中生成了一个 People类型的引用，这个引
 * 用指向了堆中生成的People类型的对象
 * people.change(people); //传到方法的时候创建了一个新的引用，也指向刚才在堆中生成的People类型的对象
 * public void change(People people)
 * {
 * people = new People(); //在内存的堆中又生成了一个 People类型的对象，并将方法中的那个引用从指向旧的People对象改成指向这个新的对象
 * 
 * people.age = 30; //这里通过新的引用修改的是方法中新产生的对象的age属性，而不是外面那个对象的age属性
 * }
 * 
 * 3. 如果一个类包含了属性与方法，那么该类的每一个对象都具有自己的属性，但无论一个类有多少个对象，这些对象共享同一个方法（引用同一个方法）。
 * 
 * 4. 关于方法参数传递的总结：对于Java中的方法参数传递，无论传递的是原生数据类型还是引用类型，统一是传值（pass by value），而不是传递内存地址。
 * C#里面有ref可以传递内存地址
 * 
 * 5. 什么类型的引用就能指向什么类型的对象，不能指向其他类型的对象，比如People类型的引用就能指向People类型的对象，但不能指向Student类型的对象。比如：
 * People people = new People(); // 正确
 * People people = new Student(); //错误
 * 
 * 6. 构造方法（Constructor）：构造方法用于完成对象属性的初始化工作，构造方法的特点：
 * a) 构造方法的名字必须与类名完全一致（包含大小写）
 * b) 构造方法没有返回值，连void也不能出现。
 * c) 如果在定义一个类的时候，没有为类声明构造方法，那么Java编译器会自动为类添加一个没有参数且方法体为空的构造方法（默认的构造方法）
 * d) 如果在定义一个类的时候，为类声明了构造方法，那么Java编译器就不会再为类添加构造方法了。
 * e) 不能显式调用类的构造方法，构造方法通常是通过new关键字隐式调用。
 * 
 * 7. 默认的构造方法：构造方法没有参数且方法体为空。
 * 
 * 8. new关键字在生成对象时完成了三件事情：
 * a) 为对象开辟内存空间。
 * b) 调用类的构造方法进行对象的一系列的初始化。
 * c) 将生成的对象的地址返回。
 * 
 * 9. 使用new来生成对象的时候，后面的小括号()表示构造方法的参数列表，如果构造方法不接收参数，那么小括号中的内容为空；如果构造方法接收参数，
 * 那么小括号中的实际参数就需要与构造方法定义中的形式参数保持一致（参数数量一致、参数类型一致、按照顺序逐一赋值）。
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class People {
	int age = 20;

	public void change(People people) {
		people = new People();
		people.age = 30;
	}

	public static void main(String[] args) {
		People people = new People();
		int age = people.age;
		System.out.println(age); // 输出 20
		people.change(people);
		int age2 = people.age;
		System.out.println(age2); // 还是输出20
	}
}