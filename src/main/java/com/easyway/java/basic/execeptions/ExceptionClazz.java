/**
 * Project Name:java-basic
 * File Name:ExceptionClazz.java
 * Package Name:com.easyway.java.basic.execeptions
 * Date:2015-3-17上午10:03:08
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.execeptions;

import java.io.IOException;

/**
 * 异常处理：
 * 1.考虑避免异常，彻底杜绝异常的发生，若不能完全避免，则尽可能地减小异常发生的几率。
 * 2.如果有些异常不可避免，那么应该预先准备好处理异常的措施，从而降低或者弥补异常造成的损失，或者恢复正常的流程。
 * 3.对于某个系统遇到的异常，有些异常仅靠系统本身就能处理；有些异常则需要系统本身以及其他系统共同来处理。
 * 4.对于某一个系统遇到的异常，系统本身应该仅可能地处理异常，实在没有办法处理，才求助于其他系统的处理。异常处
 * 理越早损失就越小。否则，如果异常传播到多个系统，引起连锁反映，就会造成更大的损失。
 * 
 * Java 虚拟机的方法调用栈
 *    java虚拟机用方法调用栈来跟踪每一个线程中一系列的方法调用过程。该堆栈保存了每个调用方法的本地信息。每一个线程都有一个独立
 * 的方法法调用栈。对于java应用程序的主线程，堆栈底部是程序的入口方法main。当一个新方法被调用时候，java虚拟机把描述该方法的
 * 栈结构置入栈顶部，位于栈顶的方法为正在执行的方法。
 * 
 * 当一个方法正常执行完毕，java虚拟机会从调用栈中弹出该方法的栈结构，然后继续处理前一个方法。如果在执行方法的过程中抛出异常，则
 * Java虚拟机必须找到能捕获该异常的catch代码块。它首先查看当前方法是否存在这样的catch代码块，如果存在，那么就执行该catch代码块，
 * 否则，java虚拟机会从调用栈中弹出该方法的栈结构，继续到前一个方法查找何时的catch代码块。
 * 
 * 
 * 如果java虚拟机在某一个方法中找到处理该异常的代码，则方法的栈结构将成为栈顶元素，
 * 程序流程将转到该方法的异常处理代码部分继续执行。
 * 当java虚拟机追溯到调用栈的底部的方法时，如果仍然没找到处理该异常代码将以下步骤处理。
 * 1.调用异常对象的printStackTrace()，打印来自方法调用栈的异常信息。
 * 2.如果该线程不是主线程，那么终止这个线程，
 * 其他线程继续正常运行。如果该线程是主线程（即方法调用栈的底部为main方法），那么整个应用程序被终止。
 * 
 * 异常处理对性能的影响
 * try-catch对性能影响不大，当异常发生时候，java虚拟机需要执行额外的操作，来定位处理异常的代码块，
 * 对性能产生负面影响。特别异常处理代码块位于调用栈的底部时候，java虚拟机定位异常处理代码块就需要大量的工作。
 * 
 * 
 * ClassName:ExceptionClazz <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-17 上午10:03:08 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ExceptionClazz {
    /**
     * 
     * methodA: 在当前方法中通过try-catch语句捕获并处理异常 <br/>
     *
     * @author Administrator
     * @param money
     * @since JDK 1.6
     */
    public void methodA(int money){
        try {
            if(money<0){
                throw new IllegalAccessException("金额无效");
            }
        }
        catch (IllegalAccessException e) {
            // TODO: handle exception
        }
    }
    
    /**
     * 
     * methodB:在方法声明处通过throws语句声明抛出异常 <br/>
     *
     * @author Administrator
     * @param money
     * @since JDK 1.6
     */
    public void methodB(int money)throws IllegalAccessException{
            if(money<0){
                throw new IllegalAccessException("金额无效");
            }
    }
    /**
     * 
     * finallyexit:
     * 1.finally 语句 不被执行的唯一情况是先执行了用于终止程序的System.exit()方法，
     * Java.lang.System类的静态方法exit()用于终止当前的Java虚拟机进程，java虚拟机所执行的Java程序也随之终止。
     * 2.如果在执行try代码块时，突然关掉电脑的电源，所有进程都终止运行，也不会执行finally语句。
     * <br/>
     * @author Administrator
     * @return
     * @since JDK 1.6
     */
    public static int finallyexit(){
        int a=0;
        try{
            a=5;
            System.exit(0);
            return 2;
        }finally{
            a=1;
        }
    }
    /**
     * 
     * test:
     *
     * @author Administrator
     * @return
     * @since JDK 1.6
     */
    public int returnMethodA(int money)throws IllegalAccessException{
        if(money<0){
            throw new IllegalAccessException("金额无效");
        }
        return money;
   }
    /**
     * 
     * returnMethodB:2.return 语句用于退出本方法，在执行try或者catch代码块中的return语句时，
     * 假如有finally 代码块，会先执行finally代码块 <br/>
     *
     * @author Administrator
     * @param money
     * @return
     * @since JDK 1.6
     */
    public int returnMethodB(int money){
        try {
            System.out.println("Begin");
            int result=returnMethodA(money);
            return result;
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return -100;
        }finally{
            System.out.println("Finally");
        }
    }
    /**
     * 
     * test:3.finally 代码块虽然在return语句之前被执行，但finally 
     * 代码块不能通过重新给变量复制的方式改变return语句返回值<br/>
     *
     * @author Administrator
     * @return
     * @since JDK 1.6
     */
    public static int test(){
        int a=0;
        try{
            return a;
        }finally{
            a=1;
        }
    }
    /**
     * 
     * returnB:4建议不要在finally代码块中使用return语句，因为它会导致以下两种潜在的错误，第一种错误的覆盖try或者catch代码块的return语句。<br/>
     *
     * @author Administrator
     * @param money
     * @return
     * @since JDK 1.6
     */
    public int returnB(int money){
        try {
            System.out.println("Begin");
            int result=returnMethodA(money);
            return result;
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return -100;
        }finally{
            return 100;
        }
    }
    
    
    public static void main(String[] args) {
        ExceptionClazz  ec=new ExceptionClazz();
        System.out.println(ec.returnB(3));
    }
    

}

