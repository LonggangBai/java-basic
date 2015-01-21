/**
 * Project Name:java-basic
 * File Name:Results.java
 * Package Name:com.easyway.java.basic.sync.cyclicBarrier
 * Date:2015-1-21下午5:05:56
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync.cyclicBarrier;

/**
 * ClassName:Results <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午5:05:56 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */

// 8. 现在，实现一个类名为 Results。此类会在array内保存被查找的数字在矩阵的每行里出现的次数。
public class Results {

    // 9. 声明私有 int array 名为 data。
    private int data[];


    // 10. 实现类的构造函数。此构造函数接收一个表明array元素量的整数作为参数。
    public Results(int size) {
        data = new int[size];
    }


    // 11. 实现 setData() 方法。此方法接收array的某个位置和一个值作为参数，然后把array的那个位置设定为那个值。
    public void setData(int position, int value) {
        data[position] = value;
    }


    // 12. 实现 getData() 方法。此方法返回结果 array。
    public int[] getData() {
        return data;
    }

}
