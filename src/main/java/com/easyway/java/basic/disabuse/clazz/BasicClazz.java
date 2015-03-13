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
 *   
 *   每当用java命令启动一个java虚拟机进程时，java虚拟机就会创建
 *   一个主线程，该线程从程序入口main方法开始执行，主线程在java栈区内有一个方法调用栈，没执行一个方法，就会想方法调用栈中压入一个包含该方法的局部变量以及参数的栈帧。
 *   
 *   
 *   即：静态常量和类信息位于方法区域。
 *      实例变量位于堆区。
 *      局部变量位于java栈区域，
 *      
 *      实例变量系统初始化会为它进行一些初始化动作。
 *      局部变量生命之后，java虚拟机就不会自动给它初始化默认值。因此对于局部变量，必须先经过显示初始化，才能使用它。
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
        int var4=var1-var2;//局部变量
        return var4;
    }
    
    public void bool(){
        int output1=10;
        int output2=10;
        int output3=10;
        int output4=10;
        boolean b1=false;
        boolean b2=true;
        if((b1==true)&((output1+=10)==20)){
            System.out.println("&   We are equal "+output1);
        }else{
            System.out.println("&  Not equal "+output1);
        }
        if((b2==true)|((output2+=10)==20)){
            System.out.println(" | We are equal "+output2);
        }else{
            System.out.println("| Not equal "+output2);
        }
        if((b1==true)&&((output3+=10)==20)){
            System.out.println("&&  We are equal "+output3);
        }else{
            System.out.println("&&  Not equal "+output3);
        }
        if((b2==true)||((output4+=10)==20)){
            System.out.println(" || We are equal "+output4);
        }else{
            System.out.println("|| Not equal "+output4);
        }
    }
    
    
    
    public static int swap(byte b){
        int lowBites=b & 0xF;//获取低4位
        int highBites=b & 0xF0;//获取高4位
        //把低4左移4位，变成高4位
        //把高4位无符号右移4位，变成低4位
        //再把两者进行位或
        return   lowBites<<4| highBites>>>4;
    }
    /**
     * 
     * op:
     * 在表达式i=x++中，先把x赋给i，再将x加1，在表达式j=++x中，先将x加1,再把x赋给j。
     * 
     * a+=b等价于 a=a+b
     * a*=b等价于 a=a*b
     * a/=b等价于 a=a/b
     * a%=b等价于 a=a%b
     *  <br/>
     * @author Administrator
     * @since JDK 1.6
     */
    public void op(){
        byte a=1,b=2;
        //byte c=a+b;//错误
        int i=1, j=10,k=10,l=10 , m=10 ,n=10;
        int x=0;
        System.out.println("i="+i +"j ="+j +" k="+k +" l ="+l +" m="+m +" n="+n);
         i=x++;//"++"作为为后置操作符，i的数值变为0，x的值变为1
         j=++x;//"++"作为前置操作符号，x的数值变为2，j的值变为2;
         
         int c=a>b?++a:++b;
         System.out.println("a="+a+" b="+b+" c="+c);
         
         Integer aa=new Integer(1);
         Integer ab=new Integer(1);
         System.out.println("aa=bb ? "+(aa==ab));
         System.out.println("aa.equals(ab) ？ "+aa.equals(ab));
         
         Integer ac=Integer.valueOf(1);
         Integer ad=Integer.valueOf(1);
         System.out.println("ac=ad ? "+(ac==ad));
         System.out.println("ac.equals(ad) ？ "+ac.equals(ad));
    }
    
    public static void main(String[] args) {
        //BasicClazz信息存储在方法栈中
        //basicClazz存储在堆内
        BasicClazz basicClazz=new BasicClazz();
        basicClazz.add();
        basicClazz.bool();
        basicClazz.op();
    }

}

