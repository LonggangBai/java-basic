/**
 * Project Name:java-basic
 * File Name:OverLoad.java
 * Package Name:com.easyway.java.basic.jdk.jdk15
 * Date:2015-3-16上午10:13:06
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.jdk.jdk15;
/**
 * ClassName:OverLoad <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-16 上午10:13:06 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class OverLoad {
    /**
     * 两个方法的方法名相同，但是参数不一致，
     * 重载方法必须满足一下条件
     * 1.方法名相同
     * 2.方法的参数类型，个数，顺序至少有一项不相同。
     * 3.方法的返回类型可以不相同。
     * 4.方法的修饰符可以不相同。
     *
     * @author Administrator
     * @param a
     * @param b
     * @return
     * @since JDK 1.6
     */
    public int max(int a ,int b){
        return a>b?a:b;
    }
    public double max(double a ,double b){
        return a>b?a:b;
    }
    

}

