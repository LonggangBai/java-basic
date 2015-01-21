/**
 * Project Name:java-basic
 * File Name:VarSubClass.java
 * Package Name:com.easyway.java.basic.disabuse.var
 * Date:2015-1-21下午12:48:08
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.var;

/**
 * JAVA面试题解惑系列（三）——变量（属性）的覆盖
 * 
 * 
 * 运行结果： 子类变量 ClassName:VarSubClass 
 * 
 * <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:48:08 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class VarParentClass {
    /* friendly */String field = "父类变量";
}


public class VarSubClass extends VarParentClass {
    protected String field = "子类变量";


    public static void main(String[] args) {
        VarSubClass subClass = new VarSubClass();
        System.out.println(subClass.field);
    }
}
