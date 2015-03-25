/**
 * Project Name:java-basic
 * File Name:BatchWriter.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午10:34:44
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * ClassName:BatchWriter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午10:34:44 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BatchWriter {

    public static void main(String[] args) {
        ByteBuffer bb=ByteBuffer.allocate(1024);
        IntBuffer ib=bb.asIntBuffer();
        ib.put(new int[]{10,20,30,45,78});//向缓冲区写入一批int类型的数据
        System.out.println(ib.get(3));
        ib.put(3,400);
        System.out.println(ib.get(3));
        ib.rewind();
        while(ib.hasRemaining()){
            int i=ib.get();
            if(i==0){
                break;
            }
            System.out.println(i);
        }
        
    }
}

