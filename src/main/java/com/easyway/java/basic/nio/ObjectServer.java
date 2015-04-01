/**
 * Project Name:java-basic
 * File Name:ObjectServer.java
 * Package Name:com.easyway.java.basic.nio
 * Date:2015-3-25下午3:28:00
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ClassName:ObjectServer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-25 下午3:28:00 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ObjectServer {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("D://objectFile.obj"));
        String obj1="hello";
        out.writeObject(obj1);
        out.close();
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("D://objectFile.obj"));
        String obj2=(String) in.readObject();
        System.out.println("obj2"+obj2);
        
    }
}
