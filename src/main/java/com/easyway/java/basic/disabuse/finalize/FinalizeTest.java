/**
 * Project Name:java-basic
 * File Name:FinalizeTest.java
 * Package Name:com.easyway.java.basic.disabuse.finalize
 * Date:2015-1-21下午1:13:51
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.finalize;

/**
 * 
 * <pre>
 * 运行结果如下： 
 * 执行了finalize()方法
 * 
 * 程序调用了java.lang.System类的gc()方法，引起GC的执行，GC在清理ft对象时调用了它的finalize()方法，因此才有了上面的输出结果。调用System.gc()等同于调用下面这行代码： 
 * Java代码 复制代码
 * Runtime.getRuntime().gc();  
 * 
 * 调用它们的作用只是建议垃圾收集器（GC）启动，清理无用的对象释放内存空间，但是GC的启动并不是一定的，这由JAVA虚拟机来决定。直到JAVA虚拟机停止运行，有些对象的finalize()可能都没有被运行过，那么怎样保证所有对象的这个方法在JAVA虚拟机停止运行之前一定被调用呢？答案是我们可以调用System类的另一个方法： 
 * Java代码 复制代码
 * public static void runFinalizersOnExit(boolean value) {   
 *     //other code   
 * }  
 * 
 * 给这个方法传入true就可以保证对象的finalize()方法在JAVA虚拟机停止运行前一定被运行了，不过遗憾的是这个方法是不安全的，它会导致有用的对象finalize()被误调用，因此已经不被赞成使用了。 
 * 
 * 由于finalize()属于Object类，因此所有类都有这个方法，Object的任意子类都可以重写（override）该方法，在其中释放系统资源或者做其它的清理工作，如关闭输入输出流。 
 * 
 * 通过以上知识的回顾，我想大家对于final、finally、finalize的用法区别已经很清楚了。
 * </pre>
 * 
 * ClassName:FinalizeTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:13:51 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public final class FinalizeTest {
    // 重写finalize()方法
    protected void finalize() throws Throwable {
        System.out.println("执行了finalize()方法");
    }


    public static void main(String[] args) {
        FinalizeTest ft = new FinalizeTest();
        ft = null;
        System.gc();
    }
}
