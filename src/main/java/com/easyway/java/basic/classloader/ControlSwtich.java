/**
 * Project Name:java-basic
 * File Name:ControlSwtich.java
 * Package Name:com.easyway.java.basic.classloader
 * Date:2015-3-16上午9:38:10
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.classloader;

/**
 * ClassName:ControlSwtich <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-16 上午9:38:10 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ControlSwtich {
    public static void main(String[] args) {
        int x = 5;
        switch (x) {

        default:
            System.out.println("default");
        case 1:
            System.out.println("case1");
        case 2:
            System.out.println("case2");
        case 3:
            System.out.println("case3");
            break;
        case 4:
            System.out.println("case4");
        }
        
        do {
            System.out.println("1>2");
        } while (1>2);
        
        for (int i = 3; i >0;System.out.print("选择部分打印：i="+(--i))) {
            System.out.println("   循环体打印：i="+i);
        }
    }

}
