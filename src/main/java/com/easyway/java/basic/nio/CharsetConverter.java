/**
 * Project Name:java-basic
 * File Name:CharsetConverter.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25上午10:16:49
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;


/**
 * ClassName:CharsetConverter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-25 上午10:16:49 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class CharsetConverter {
    public static void main(String[] args) throws IOException {
        final int BSIZE = 1024;
        // 代码1
        ByteBuffer bb = ByteBuffer.wrap("你好,".getBytes("UTF-8"));
        CharBuffer cb = bb.asCharBuffer();
        System.out.println(cb);

        // 代码2
        bb = ByteBuffer.wrap("你好".getBytes("UTF-16BE"));
        cb = bb.asCharBuffer();
        System.out.println(cb);

        // 代码3
        bb = ByteBuffer.wrap("你好".getBytes("UTF-8"));
        cb = Charset.forName("UTF-8").decode(bb);
        System.out.println(cb);

        // 代码4
        Charset cs = Charset.forName("GBK");
        bb = cs.encode("你好");
        cb = cs.decode(bb);
        for (int i = 0; i < cb.limit(); i++) {
            System.out.println(cb.get());
        }

    }
}
