/**
 * Project Name:java-basic
 * File Name:CustomClassLoader.java
 * Package Name:com.easyway.java.basic.clazz.lifecycle
 * Date:2015-3-18上午9:55:02
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.lifecycle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 
 * 不同类加载器的命名控件存在以下关系
 * 1.同一个命名空间的类是互相可见的。
 * 2.子加载器的命名控件包含所有父类加载器的命名空间，因此由子加载器加载的类能砍价父类加载器加载的类。
 * 例如系统类加载器的类能看见根类加载器加载的类。
 * 3.由父加载器加载的类不能看见子加载器加载类。
 * 4.如果两个加载器之间没有直接或者间接的父子关系，那么他们各自加载的类互不可见。
 * 
 * 
 * 当两个不同命名空间内的类互相不可见时候，可采用java反射机制来访问对方实例的属性和方法。
 * 
 * 
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CustomClassLoader  extends ClassLoader{
    private String name;
    private String path="D:\\";
    private final String fileType=".class";
    
    public CustomClassLoader(String name){
        super();
        this.name=name;
    }
    public CustomClassLoader(ClassLoader parent ,String name){
        super(parent);
        this.name=name;
    }
    
    protected Class<?> findClass(String name)throws ClassNotFoundException{
        byte[] data=loadClassData(name);
        return defineClass(name, data,0,data.length);
    }
    
    private byte[] loadClassData(String name) throws ClassNotFoundException{
        FileInputStream fis=null;
        byte[] data=null;
        ByteArrayOutputStream baos=null;
        try {
            System.out.println("name="+name);
            //将.替换为\
            name=name.replace("\\.", "\\\\");
            fis=new FileInputStream(new File(path+name+fileType));
            baos=new ByteArrayOutputStream();
            int ch=0;
            while((ch=fis.read())!=-1){
                baos.write(ch);
            }
            data=baos.toByteArray();
        }
        catch (Exception e) {
            //异常转义
            throw new ClassNotFoundException("Class is not found :"+name,e);
        }finally{
            try {
                fis.close();
                baos.close();
            }
            catch (Exception e2) {
               e2.printStackTrace();
            }
        }
        return data;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException {
        CustomClassLoader loader0=new CustomClassLoader("loader0");
        loader0.setPath("D:\\git\\repo\\java-basic\\target\\classes\\com\\easyway\\java\\basic\\collection\\");
        String clazzName="com.easyway.java.basic.collection.RandomTest";
        Class<?> objClass=loader0.loadClass(clazzName);
        System.out.println("objClass's hashCode is "+objClass.hashCode());
        Object obj=objClass.newInstance();
        loader0=null;
        objClass=null;
        obj=null;
        
        
        loader0=new CustomClassLoader("loader0");
        loader0.setPath("D:\\git\\repo\\java-basic\\target\\classes\\com\\easyway\\java\\basic\\collection\\");
        objClass=loader0.loadClass(clazzName);
        System.out.println("objClass's hashCode is "+objClass.hashCode());
        
        
        String className="com.easyway.java.basic.clazz.lifecycle.ClassA";
        CustomClassLoader loader1=new CustomClassLoader("loader1");
        loader1.setPath("D:\\git\\repo\\java-basic\\target\\classes\\com\\easyway\\java\\basic\\clazz\\lifecycle\\");
      
        
        //load2继承父加载器loader1即自定义类加载器 ，代表自定义类加载器
        CustomClassLoader loader2=new CustomClassLoader(loader1,"loader2");
        loader2.setPath("D:\\git\\repo\\java-basic\\target\\classes\\com\\easyway\\java\\basic\\execeptions\\"); 
        
        
        //load3继承父加载器null即根加载器 ，代表扩展加载器
        CustomClassLoader loader3=new CustomClassLoader(null,"loader3");
        loader3.setPath("D:\\git\\repo\\java-basic\\target\\classes\\com\\easyway\\java\\basic\\execeptions\\"); 
        System.out.println("==============================================");       
        System.out.println("=================loader1=====================");
        test(loader1);
        System.out.println("=================loader2=====================");
        test(loader2);
        System.out.println("=================loader3=====================");
        test(loader3);
        System.out.println("==============================================");
        
        
    }
    
    public static void test(ClassLoader loader) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        String className="com.easyway.java.basic.clazz.lifecycle.ClassA";
        System.out.println("loader"+loader.toString());
        Class<?> objClass=loader.loadClass(className);   
        Object obj=objClass.newInstance();//创建一个类的实例
    }
    
}

