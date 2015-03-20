/**
 * Project Name:java-basic
 * File Name:RealSubject.java
 * Package Name:com.easyway.java.pattern.proxy
 * Date:2015-3-20下午2:52:08
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.pattern.proxy;
/**
 * ClassName:RealSubject <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 下午2:52:08 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RealSubject implements Subject {

    public RealSubject() {}

    public void request() {
        System.out.println("真实的请求.");
    }
}

