/**
 * Project Name:java-basic
 * File Name:Proxy.java
 * Package Name:com.easyway.java.pattern.proxy
 * Date:2015-3-20下午2:53:06
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * ClassName:Proxy <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-20 下午2:53:06 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ProxyClazz {
    public static void main(String[] args) {
     // 创建被代理类
        RealSubject rs = new RealSubject();
        // 通过被代理类初始化一个代理处理器，在newProxyInstance()中作为参数生成代理
        DynamicSubject ds = new DynamicSubject(rs);
        Class<?> cls = rs.getClass();
        //生成代理对象
        Subject subject = (Subject) Proxy.newProxyInstance(
                cls.getClassLoader(), cls.getInterfaces(), ds);
        //通过代理对象调用真实方法
        subject.request();
    }


}

