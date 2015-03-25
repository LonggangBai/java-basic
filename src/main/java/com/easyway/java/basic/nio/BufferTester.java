/**
 * Project Name:java-basic
 * File Name:BufferTester.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午9:34:01
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ClassName:BufferTester <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午9:34:01 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BufferTester {
    public static void main(String[] args) throws IOException {
        final int BSIZE=1024;
        FileChannel in=new FileInputStream("D:\\in.txt").getChannel();
        FileChannel out=new FileOutputStream("D:\\out.txt").getChannel();
        //方式一
        //创建一个控制缓冲区，缓冲区是一块可重用的内存空间
        ByteBuffer buff=ByteBuffer.allocate(BSIZE);
        while(in.read(buff)!=-1){
            buff.flip();//方法确保out.write()方法仅仅操作buff缓存区域中的当前数据。
            out.write(buff);
            buff.clear();//把缓存区的极限设为容量值，为下一次执行in.read方法向 清理buffer相关的缓存信息
        }
        //方式二
        in.transferTo(0, in.size(), out);
        //方式三
        out.transferFrom(in, 0, in.size());
        
        in.close();
        out.close();
    }

}

