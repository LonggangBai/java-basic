package com.easyway.java.basic.finals;

/**
 * <PRE>
 * 3、final变量（常量）
 *         用final修饰的成员变量表示常量，值一旦给定就无法改变！
 *         final修饰的变量有三种：静态变量、实例变量和局部变量，分别表示三种类型的常量。
 *         从下面的例子中可以看出，一旦给final变量初值后，值就不能再改变了。
 *         另外，final变量定义的时候，可以先声明，而不给初值，这中变量也称为final空白，无论什么情况，
 *         编译器都确保空白final在使用之前必须被初始化。但是，final空白在final关键字final的使用上提供了更大的灵活性，
 *         为此，一个类中的final数据成员就可以实现依对象而有所不同，却有保持其恒定不变的特征。
 * </PRE>
 * 
 * @author Administrator
 * 
 */
public class FinalVar {
	private final String S = "final实例变量S";
	private final int A = 100;
	public final int B = 90;

	public static final int C = 80;
	private static final int D = 70;

	public final int E; // final空白,必须在初始化对象的时候赋初值

	public FinalVar(int x) {
		E = x;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FinalVar t = new FinalVar(2);
		// t.A=101; //出错,final变量的值一旦给定就无法改变
		// t.B=91; //出错,final变量的值一旦给定就无法改变
		// t.C=81; //出错,final变量的值一旦给定就无法改变
		// t.D=71; //出错,final变量的值一旦给定就无法改变

		System.out.println(t.A);
		System.out.println(t.B);
		System.out.println(t.C); // 不推荐用对象方式访问静态字段
		System.out.println(t.D); // 不推荐用对象方式访问静态字段
		System.out.println(FinalVar.C);
		System.out.println(FinalVar.D);
		// System.out.println(Test3.E); //出错,因为E为final空白,依据不同对象值有所不同.
		System.out.println(t.E);

		FinalVar t1 = new FinalVar(3);
		System.out.println(t1.E); // final空白变量E依据对象的不同而不同
	}

	private void test() {
		System.out.println(new FinalVar(1).A);
		System.out.println(FinalVar.C);
		System.out.println(FinalVar.D);
	}

	public void test2() {
		final int a; // final空白,在需要的时候才赋值
		final int b = 4; // 局部常量--final用于局部变量的情形
		final int c; // final空白,一直没有给赋值.
		a = 3;
		// a=4; 出错,已经给赋过值了.
		// b=2; 出错,已经给赋过值了.
	}
}