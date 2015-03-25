/**
 * Project Name:java-basic
 * File Name:PipedInputReceiver.java
 * Package Name:com.easyway.java.basic.io.jdk
 * Date:2015-3-24下午4:15:51
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.io.jdk;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


/**
 * ClassName:PipedInputReceiver <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-24 下午4:15:51 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class Sender extends Thread {
    private PipedOutputStream out = new PipedOutputStream();


    public PipedOutputStream getPipedOutputStream() {
        return out;
    }


    public void run() {
        try {
            for (int i = -127; i <= 128; i++) {
                out.write(i);
                yield();
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}


public class PipedInputReceiver extends Thread {
    private PipedInputStream in;


    public PipedInputReceiver(Sender sender) throws IOException {
        in = new PipedInputStream(sender.getPipedOutputStream());
    }


    public void run() {
        try {
            int data;
            while ((data = in.read()) != -1) {
                System.out.println(data + " ");
            }
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        Sender sender = new Sender();
        PipedInputReceiver receiver = new PipedInputReceiver(sender);
        sender.start();
        receiver.start();
    }
}
