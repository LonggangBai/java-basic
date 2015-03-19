/**
 * Project Name:java-basic
 * File Name:Ghost.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-19下午2:00:27
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:Ghost <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午2:00:27 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Ghost {
    private static final Map<String,Ghost> ghosts=new HashMap<String,Ghost>();
    private final String name;
    public Ghost(String name){
        this.name=name;
    }
  
    public String getName() {
        return name;
    }
    public static Ghost getInstance(String name){
        Ghost ghost=ghosts.get(name);
        if(ghost==null){
            ghost=new Ghost(name);
            ghosts.put(name, ghost);
        }
        return ghost;
    }
    
    public static  void removeInstance(String name){
        ghosts.remove(name);
    }
    public static void main(String[] args) throws InterruptedException {
        Ghost ghost=Ghost.getInstance("ghosts");
        System.out.println(ghost);
        String name=ghost.getName();
        ghost=null;
        Ghost.removeInstance(name);
        System.gc();
        Thread.sleep(3000);
        ghost=Ghost.getInstance("ghosts");
        System.out.println(ghost);
    }

}

