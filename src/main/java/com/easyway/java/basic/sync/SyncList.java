/**
 * Project Name:java-basic
 * File Name:SyncList.java
 * Package Name:com.easyway.java.basic.sync
 * Date:2015-2-12上午9:59:06
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.sync;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentSkipListMap;


/**
 * ClassName:SyncList <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-2-12 上午9:59:06 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SyncList {
    public static void main(String[] args) {
        int hash=3;
        int segmentShift=6;
        int segmentMask=7;
        System.out.println((hash >> segmentShift));
        System.out.println((hash >>> segmentShift) & segmentMask);
        ConcurrentHashMap map;
        
        ConcurrentHashMap mapttt;
    }

}

