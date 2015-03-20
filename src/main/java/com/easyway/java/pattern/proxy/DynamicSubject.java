/**
 * Project Name:java-basic
 * File Name:DynamicSubject.java
 * Package Name:com.easyway.java.pattern.proxy
 * Date:2015-3-20下午2:52:30
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ClassName:DynamicSubject <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 下午2:52:30 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DynamicSubject implements InvocationHandler {
    private Object sub;

    public DynamicSubject() {}

    public DynamicSubject(Object obj) {
        sub = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        method.invoke(sub, args);
        return null;
    }
}
