/**
 * Project Name:java-basic
 * File Name:RandomAccessFileTest.java
 * Package Name:com.easyway.java.basic.io.jdk
 * Date:2015-3-25上午9:12:10
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.io.jdk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * ClassName:RandomAccessFileTest <br/>
 * Function: InputStream和OutputStream代表字节流，而Reader和Writer代表字符流，
 * 它们的共同特点是：只能按照数据的先后顺序读取数据源的数据以及按照数据的先后顺序向数据汇写数据。 <br/>
 * Date: 2015-3-25 上午9:12:10 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile rf = new RandomAccessFile("D:\\txt,dat", "rw");
        for (int i = 0; i < 10; i++) {
            rf.writeLong(i * 1000);
        }
        rf.seek(5 * 8);
        rf.writeLong(1234);
        rf.seek(0);
        for (int i = 0; i < 10; i++) {
            System.out.println("Value " + i + " :" + rf.readLong());
        }
        rf.close();
    }
}
