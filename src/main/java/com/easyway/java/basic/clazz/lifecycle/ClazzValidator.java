/**
 * Project Name:java-basic
 * File Name:ClazzValidator.java
 * Package Name:com.easyway.java.basic.clazz.lifecycle
 * Date:2015-3-17下午4:23:13
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.lifecycle;
/**
 * ClassName:类的验证 <br/>
 * Function: 类的验证内容：
 * 1.类文件的结构检查：确保类文件遵从Java类文件的固定格式。
 * 2.语义检查：确保类本身符合Java语言的语法规定。
 * 3.字节码验证：确保自截留可以被java虚拟机安全地执行。字节码流代表java方法（包括静态方法和实例方法），
 * 它是被称作操作码的单字节指令组成的序列。每一个操作码后都跟着一个或者多个操作数。
 * 4.二进制兼容的验证：确保相互引用的类之间协调一致。
 * 
 * 类的准备：
 * 在准备阶段java熏鸡为类的静态变量分配内容，并设置默认的初始值。
 * 类的解析：java虚拟机会把类的二进制数据中的符号引用替换为直接引用。
 * 
 * 类的初始化
 *    在初始化阶段，java虚拟机执行类的初始化语句，为类的静态变量赋予初始值。
 *    在程序中，静态变量的初始化有两种路径：
 *    1.在静态变量的声明处进行初始化。
 *    2.在静态代码块中进行出耍。
 *    
 * 静态变量的声明语句，以及静态代码块都被看作累的初始化语句。java虚拟机会按照
 * 初始化语句在类文件中的先后顺序来一次执行他们。
 * 
 *    <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-17 下午4:23:13 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class Base {
    static int aa=1;
    {
        System.out.println("init   Base ....");
    }
    static {
        System.out.println("init  static Base ....");
    }
}
class Sub extends Base{
    static int bb=1;
    {
        System.out.println("init   Sub....");
    }
    static{
        System.out.println("init static  Sub....");
    }
}
public class ClazzValidator {
    
    private static int a=1;//在静态变量的声明处进行初始化
    public static long b;
    public static long c;//静态变量c没有被显式初始化，它将保持默认数值0
    {
      System.out.println("init ClazzValidator");  
    };
    static{
        b=2;//在静态代码块中进行初始化
    };
    static{
        b=7;
    };
    public static void main(String[] args) {
        System.out.println("a ="+a +" ,b ="+b+" ,c="+c);
        System.out.println("bb="+Sub.bb);//执行代码时候，先依次初始化Base类和Sub类。
        Base base;//不会初始化Base类
        base=new Sub();//执行初始化
        System.out.println("aa="+base.aa);
        System.out.println("bb="+Sub.bb);//执行代码时候，
        
    }
}

