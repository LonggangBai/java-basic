/**
 * Project Name:java-basic
 * File Name:Redirecter.java
 * Package Name:com.easyway.java.basic.io.jdk
 * Date:2015-3-24下午5:23:46
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.io.jdk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;


/**
 * ClassName:Redirecter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-24 下午5:23:46 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class Redirecter {

    public static void redirect(InputStream in, PrintStream out, PrintStream err) {
        System.setIn(in);
        System.setOut(out);
        System.setErr(err);
    }


    public static void copy() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String data;
        while ((data = br.readLine()) != null && data.length() != 0) {
            System.out.println(data);
            System.err.println(data);
        }
    }


    public static void main(String[] args) throws IOException {
        InputStream sin = System.in;
        PrintStream so = System.out;
        PrintStream se = System.err;
        InputStream in = new BufferedInputStream(new FileInputStream("D:/t.txt"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("D:/t.txt")));
        PrintStream err = new PrintStream(new BufferedOutputStream(new FileOutputStream("D:/t.txt")));
        redirect(in, out, err);
        copy();
        in.close();
        out.close();
        err.close();
        redirect(sin, so, se);
        copy();

    }

}
