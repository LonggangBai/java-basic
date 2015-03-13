/**
 * Project Name:java-basic
 * File Name:BasicClazz.java
 * Package Name:com.easyway.java.basic.disabuse.clazz
 * Date:2015-3-12下午3:47:30
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.disabuse.clazz;
/**
 * <pre>
 * jvm底层执行的情况 。
 * java类编译成Class文件，启动jvm，jvm通过类加载器将class文件以二进制的形式加载到内存中，然后对类的二进制数据进行验证，
 * 然后解析并执行指令。
 * jvm运行时候需要一个运行时数据区，他分为多个子区域，堆区，方法区，java栈区。
 *   堆区域：存放对象实例信息，某一类的实例成员变量。
 *   方法区域：存放类的信息，包括静态成员变量和方法信息（类的所有方法的字节码）。
 *   java栈区域：局部变量
 *   
 *   java命令执行类的加载的过程：
 *    启动一个java虚拟机进程，该进程首先从classpath中找到对应的class文件，读取这个文件的二进制数据，
 *    把类的类型信息存放到运行时数据区的方法区中。
 *    
 *    Doll beibei=new Doll("");
 *    new创建实例的步骤：
 *    1.搜索方法区，查找Doll类的类型信息，由于此时不存在该信息，因此java虚拟机先加载Doll类，把Doll类的类型信息存放在方法区。
 *    2.在堆区中为一个新的Doll实例分配内存，这个Doll实例持有指向方法区域的Doll类的类型信息引用。这里的引用实际上指的是Doll
 *    类型信息在方法区域中的内存地址，在Doll实例的数据区存放这一个地址。
 *    3.beibei变量在main方法中定义，他是局部变量，被添加到执行main方法的主线程的java方法调用栈中。这个beibei变量引用
 *    堆区域中Doll实例，也就是说它持有指向Doll实例的引用。
 *    
 *   静态变量和实例变量的生命周期
 *   类的成员变量有两种：
 *      一种被static关键字修饰的变量，叫类变量或者静态变量。
 *      一种没有被static关键字修改的变量，叫实例变量。
 *   
 *   
 *   静态变量和实例变量的区别在于
 *   1.类的静态变量在内存中只有一个，java虚拟机的加载类的过程中为静态变量分配内存，静态变量为方法区域，被类的所有实例共享。
 *   静态变量可以直接通过类名被访问，静态变量的生命周期取决于类的生命周期，当加载类的时候，静态变量被创建并分配内存，当卸载类
 *   的时候，静态变量被销毁并撤销所占内存。
 *   2.类的每一个实例都有响应的实例变量。每创建一个类的实例，java虚拟机教会为实例变量分配一次内存，实例变量位于堆区中。实例变量
 *   的生命周期取决于实例的生命周期，当创建实例的时候，实例变量被创建并分配内存，当销毁实例的时候，实例变量被销毁并撤销所占内存。
 *   
 *   
 *   静态变量可可以作为所有实例的共享数据，它不依赖特定实例。
 *   实例变量属于特定实例。
 *   
 *   
 *   局部变量的生命周期
 *      静态变量的生命周期取决于类何时被加载以及卸载。
 *      实例变量的生命周期取决于实例何时被创建以及销毁。
 *      
 *   局部变量的生命周期，取决于所属的方法何时被调用以及结束调用。
 *   1.当java虚拟机调用一个方法时候，会为这个方法中的局部变量分为内存。
 *   2.当java虚拟机结束调用一个方法时，会结束这个方法中的局部变量的生命周期。
 *   
 *   
 *   java虚拟机执行流程
 *   1.加载BasicClazz类，开始静态变量var2的生命周期，var2位于方法区域。
 *   2.创建BasicClazz实例，开始实例变量var1的生命周期，var1位于堆区。
 *   3.调用BasicClazz的实例的add方法，开始局部变量var3的生命周期，var3位于java栈区。
 *   4.执行完毕BasicClazz实例add（）方法，结束局部变量var3的生命周期，退回到main方法。
 *   5.执行完毕BasicClazz类的main方法，结束BasicClazz实例以及它的实例变量var1的生命周期，
 *   卸载BasicClazz类，结束静态变量var2的生命周期，java虚拟机运行结束。
 * </pre>
 * @author Administrator
 * @version 
 * @since JDK 1.6
 */
public class BasicClazz {
    
    
    public static int number=0;//方法堆
    private int id=0;//堆区
    private String name="BasicClazz";//堆区 
    
    int var1=1;//实例变量  //堆区
    static int var2=2;//静态变量
    
    
    public int add(){
        int var3=var1+var2;//var3是局部变量  java栈区
        return var3;
    }
    
    public int delete(){
        int var4=var1-var2;
        return var4;
    }
    
    public static void main(String[] args) {
        //BasicClazz信息存储在方法栈中
        //basicClazz存储在堆内
        BasicClazz basicClazz=new BasicClazz();
        basicClazz.add();
    }

}

