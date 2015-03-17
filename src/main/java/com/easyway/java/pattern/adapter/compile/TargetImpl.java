/**
 * Project Name:java-basic
 * File Name:SourceImpl.java
 * Package Name:com.easyway.java.pattern.adapter.inters
 * Date:2015-3-16下午4:56:26
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.pattern.adapter.compile;
/**
 * ClassName:SourceImpl <br/>
 * Function:适配器模式的之组合实现方式 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-16 下午4:56:26 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
interface SourceIFC{
     public int add(int a, int b);
}
interface TargetIFC{
    public int addOne(int a);
    public int add(int a, int b);
}
 class  SourceImpl  implements  SourceIFC{
    public int add(int a, int b){
        return a+b;
    }
}
public  class TargetImpl  implements  TargetIFC{
    private SourceIFC source;
    public TargetImpl(SourceIFC source){
        this.source=source;
    }
    public int addOne(int a){
        return add(a,1);
    }
    public int add(int a,int b){
        return this.source.add(a, 1);
    }
}

