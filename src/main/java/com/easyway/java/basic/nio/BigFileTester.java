/**
 * Project Name:java-basic
 * File Name:BigFileTester.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午10:43:13
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * ClassName:BigFileTester <br/>
 * Function: MappedByteBuffer 用于创建和修改那些因为太大而不能放入内存的文件。MappedByteBuffer可用来映射文件中的一块区域，
 * 所有对MappedByteBuffer的读写操作都会被映射到对文件的物理读写操作。
 * 
 *  <br/>
 * Reason:	 <br/>
 * Date:     2015-3-25 上午10:43:13 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BigFileTester {

     public static void main(String[] args) throws IOException {
        int capacity=0x800000;
        MappedByteBuffer mb=new RandomAccessFile("D:\test.txt","rw")
        .getChannel().map(FileChannel.MapMode.READ_WRITE, 0, capacity);
        mb.put("你好吗".getBytes("GBK"));
        mb.flip();
        System.out.println(" "+Charset.forName("GBK").decode(mb));
    }
}

