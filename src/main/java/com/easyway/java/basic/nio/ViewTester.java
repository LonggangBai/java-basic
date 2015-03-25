/**
 * Project Name:java-basic
 * File Name:ViewTester.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午10:28:36
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * ClassName:ViewTester <br/>
 * Function: ByteBuffer 提供asCharBuffer，asIntBuffer等用来生成缓冲区视图的方法，通过视图，程序可以从底层的
 * ByteBuffer中读取各种基本类型的数据，或者向底层的ByteBuffer中写入各种基本类型的数据。 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午10:28:36 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ViewTester {
    public static void main(String[] args) {
        ByteBuffer bb=ByteBuffer.allocate(4);
        while(bb.hasRemaining()){
            System.out.println(bb.get());
        }
        bb.rewind();
        
        CharBuffer cb=bb.asCharBuffer();
        cb.put("你好");
        while(bb.hasRemaining()){
            System.out.println(bb.get());
        }
        
    }

}

