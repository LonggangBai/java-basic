/**
 * Project Name:java-basic
 * File Name:FileTest.java
 * Package Name:com.easyway.java.basic.files
 * Date:2015-3-25下午3:41:20
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.files;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Date;

import javax.management.remote.rmi.RMIServer;
import javax.swing.JTable;

/**
 * ClassName:FileTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-25 下午3:41:20 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileTest {
    public static void main(String[] args) {
        RMIServer  dd;
        JTable jt;
    }
    public static void filter(String dirpath){
        File file=new File(dirpath);
        FilenameFilter filter=new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                System.out.println("根目录"+dir.getPath()+" 子路径:"+name);
                File currFile=new File(dir,name);
                if(currFile.isFile() && name.indexOf(".txt")!=-1){
                    return true;
                }
                return false;
            }
        };
        String[] lists=file.list(filter);
        for (String string : lists) {
            System.out.println(string);
        }
    }
    
    public static void listDir(File dir){
        File[] lists=dir.listFiles();
        //打印当前目录下包含的所有子目录和文件的名称
        System.out.println("目录："+dir.getName());
        for (File file : lists) {
            if(file.isDirectory()){
                listDir(file);
            }else{
                System.out.println(file.getName()+" canRead:"+file.canRead()+" lastModified :"+new Date(file.lastModified()));
            }
        }
    }

}

