/**
 * Project Name:java-basic
 * File Name:Stack.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-19下午2:43:04
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;

import java.util.EmptyStackException;

/**
 * ClassName:Stack <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午2:43:04 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Stack {
    private Object[] elements;//存放的对象
    private int size=0;
    private int capacityIncrement=10;//堆栈的容量增加的步长
    public Stack(int initcapacity,int capacityIncrement){
        this(initcapacity);
        this.capacityIncrement=capacityIncrement;
    }
    public Stack(int initcapacity){
       this.elements=new Object[initcapacity];
    }
    
    public void push(Object object){
        ensureCapacity();
        elements[size++]=object;
    }
    /**
     * 
     * pop:在某些情况下，当程序通过数组来使用内存时，必须十分小心的清除过期的对象引用，否则会导致潜在的内存泄漏的错误。
     *  <br/>
     *
     * @author Administrator
     * @return
     * @since JDK 1.6
     */
    public Object pop(){
        if(size==0){
            throw new EmptyStackException();
        }
        Object object=elements[--size];
        elements[size]=null;//清理过期的对象的引用
        return object;
    }
    
    private void ensureCapacity(){//增加堆栈的容量
        if(elements.length==size){
            Object[] oldElements=elements;
            elements=new Object[elements.length+capacityIncrement];
            System.arraycopy(oldElements, 0, elements, 0, size);
        }
    }
    
}

