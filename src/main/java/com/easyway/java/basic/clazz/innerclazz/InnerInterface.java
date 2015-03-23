/**
 * Project Name:java-basic
 * File Name:InnerInterface.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-19下午5:20:01
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.innerclazz;
/**
 * ClassName:InnerInterface <br/>
 * Function:  <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午5:20:01 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
 class StaticInterfaceOuter{
    public static interface Tool
    {
        public int add(int a ,int b);
    }
    
    private Tool tool=new Tool(){

        @Override
        public int add(int a, int b) {
            
            return a+b;
        }};
        public int add(int a ,int b,int c){
            return tool.add(tool.add(a, b), c);
        }
}
 class MyTool implements StaticInterfaceOuter.Tool{

    @Override
    public int add(int a, int b) {
        
        return a+b;
    }
     
 }
 
 interface StaticA{
    static class StaticB{
    }   
    public void method(StaticB b);
 }
 class StaticC implements StaticA{
     StaticB  b=new StaticB();

    @Override
    public void method(StaticB b) {
               
    }
     
 }
public class InnerInterface {
    StaticA.StaticB b=new StaticA.StaticB();
    
}

