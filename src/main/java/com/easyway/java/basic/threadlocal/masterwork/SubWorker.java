package com.easyway.java.basic.threadlocal.masterwork;
/**
 * Worker子类实现：单个数字立方计算，重写worker的handle方法
 * @author longgangbai
 *
 */
public class SubWorker extends Worker{  
    public Object handle(Object input)  
    {  
        Integer i=(Integer)input;  
        return i*i*i;  
    }  
}  