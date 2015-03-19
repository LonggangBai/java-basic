/**
 * Project Name:java-basic
 * File Name:AnonymousClazz.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-19下午5:08:04
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.innerclazz;
/**
 * ClassName:AnonymousClazz <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午5:08:04 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
interface Task{
    public void doTask();
}
//基于抽象类
abstract class AnonymousClass{
    abstract void method();
    AnonymousClass clazz=new AnonymousClass(){

        @Override
        void method() {
            System.out.println("inner ");
        }
        
    };
}
//基于类
public class AnonymousClazz {
    AnonymousClazz(int v){
        System.out.println("another constructor");
    }
    AnonymousClazz(){
        System.out.println("default constructor");
    }
    void method(){
        System.out.println("from AnonymousClazz");
    }
    
    public static void main(String[] args) {
        new AnonymousClazz().method();
        AnonymousClazz a=new AnonymousClazz(){
            {
                System.out.println("init install AnonymousClass ");
            }
            @Override
            void method(){
                System.out.println("override from AnonymousClazz");
            }
        };
        a.method();
        //基于接口
        Task task=new Task(){

            @Override
            public void doTask() {
                
                System.out.println("dotask");
                
            }};
    }
}

