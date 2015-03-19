/**
 * Project Name:java-basic
 * File Name:InnerMethodClazz.java
 * Package Name:com.easyway.java.basic.clazz.innerclazz
 * Date:2015-3-19下午5:02:27
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.innerclazz;
/**
 * ClassName:InnerMethodClazz <br/>
 * Function: 子类和父类中内部类同名，内部类并不存在覆盖的概念。子类和父类中存在同名的内部类，那么这两个
 * 内部类也会分别在不同的命名空间中。因此不会发生冲突。 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午5:02:27 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
class MethodOuter{
    Inner in;
    MethodOuter(){
        in=new Inner();
    }
    public class Inner{
        public Inner(){
            System.out.println("inner of MethodOuter");
        }
    }
}
class SubMethodOuter extends  MethodOuter{
     class Inner{
        public Inner(){
            System.out.println("inner of SubMethodOuter");
        }
    }
}
 
public class InnerMethodClazz {
    public static void main(String[] args) {
        SubMethodOuter.Inner  in1=new SubMethodOuter().new Inner();
        MethodOuter.Inner in2=new MethodOuter().new Inner();
        
    }

}

