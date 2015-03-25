/**
 * Project Name:java-basic
 * File Name:FileChannelTester.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午9:21:36
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * ClassName:FileChannelTester <br/>
 * Function: 用FileChannel读取文件 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午9:21:36 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileChannelTester {
    public static void main(String[] args) throws IOException {
        final int BSIZE=1024;
        FileChannel fc=new FileOutputStream("D:\\test.txt").getChannel();
        fc.write(ByteBuffer.wrap("你好,".getBytes()));
        fc.close();
        
        fc=new RandomAccessFile("D:\\test.txt","rw").getChannel();
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("朋友".getBytes()));
        fc.close();
        
        fc=new FileInputStream("D:\\test.txt").getChannel();
        //创建ByteBuffer对象
        ByteBuffer buff=ByteBuffer.allocate(BSIZE);
        fc.read(buff);//将数据写到bytebuffer中
        //把缓冲区的极限limit设为当前位置值，再把位置positioin设置0，这使得接下来的cs.decode(buff)方法仅仅操作刚刚写入缓冲区的数据
        //cs.decode(buff)方法把缓冲区的数据转换为Unicode字符编码，然后打印该编码锁代表的字符串。
        buff.flip();
        
        Charset cs=Charset.defaultCharset();//获得本地平台的字符编码
        System.out.println(cs.decode(buff));//转换为Unicode字符编码
        fc.close();
        
        
    }

}

