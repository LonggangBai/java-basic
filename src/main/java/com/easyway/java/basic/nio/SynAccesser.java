/**
 * Project Name:java-basic
 * File Name:SynAccesser.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午11:03:08
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


/**
 * ClassName:SynAccesser <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 上午11:03:08 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

public class SynAccesser {
    static FileChannel fc;
   static class Modifier extends Thread{
        private ByteBuffer buff;
        private int start,end;
        Modifier(ByteBuffer mbb,int start,int end){
            this.start=start;
            this.end=end;
            mbb.limit(end);
            mbb.position(start);
            buff=mbb.slice();
            start();
        }
        public void run(){
            try {
                FileLock fl=fc.lock(start, end, false);
                System.out.println("lock :"+ start +" to "+end);
                while(buff.position()<buff.limit()-1){
                    //buff.get和buff.put方法都会改变buff的position
                    buff.put((byte)(buff.get()+1));
                    fl.release();
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        final int  capacity=0x800;
        fc=new RandomAccessFile("D:\\test.txt","rw").getChannel();
        MappedByteBuffer mbb=fc.map(FileChannel.MapMode.READ_WRITE, 0, capacity);
        for(int i=0;i<capacity/2;i++){
            mbb.put((byte)'a');
        }
        for(int i=0;i<capacity/2;i++){
            mbb.put((byte)'c');
        }
        new Modifier(mbb,0,capacity/2);
        new Modifier(mbb,capacity/2,capacity);
    }

}

