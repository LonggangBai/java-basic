/**
 * Project Name:java-basic
 * File Name:MThreadLocal.java
 * Package Name:com.easyway.java.basic.threadlocal
 * Date:2015-3-24上午10:58:16
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:MThreadLocal <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-24 上午10:58:16 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MThreadLocal<T> {
    
    private Map<Runnable ,T> values=Collections.synchronizedMap(new HashMap<Runnable ,T>());
    protected T initialValue(){
        return null;
    }
    public T get(){
        Thread curThread=Thread.currentThread();
        T o=values.get(curThread);
        if(o==null && !values.containsKey(curThread)){
            o=initialValue();
            values.put(curThread, o);
        }
        return o;
    }
    
    public void set(T newValue){
        values.put(Thread.currentThread(), newValue);
    }

}

