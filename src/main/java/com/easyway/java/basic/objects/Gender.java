/**
 * Project Name:java-basic
 * File Name:Gender.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-19上午10:54:46
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * ClassName:Gender <br/>
 * Function: 枚举类是指实例为数目有限的类。
 * 创建枚举类，采用以下设计模式
 * 1.构建方法定义为private类型
 * 2.提供一些public static final 类型的静态变量，每个静态变量引用类的一个实例
 * 3.提供静态工厂方法，允许用户根据特定参数获得与之匹配的实例。
 * 
 * 不可变类的实例在实例的整个生命周期中永远保持初始化的状态，
 * 不可变类的有点：
 * 1.不可变类使程序更加安全，不容易出错。
 * 2不可变类是线程安全的，当多个线程访问不可变类的同一个实例时候，无须进行线程的同步。
 * 
 * 
 *  <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 上午10:54:46 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Gender implements Serializable{

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;
    private  Character sex;
    private  transient String description;
    private static final Map<Character,Gender>  instancesBySex=new HashMap<Character,Gender>();
    
    public static final Gender FEMALE=new Gender(new Character('F'),"Female");
    public static final Gender MALE=new Gender(new Character('M'),"Male");
    private Gender(Character sex,String desc){
        this.sex=sex;
        this.description=desc;
        instancesBySex.put(sex, this);
    }
    
    public static Collection<Gender> getAllValues(){
        return  Collections.unmodifiableCollection(instancesBySex.values());
    }
    
    public static Gender getInstance(Character sex){
        Gender result=(Gender)instancesBySex.get(sex);
        if(result==null){
            throw new NoSuchElementException(sex.toString());
        }
        return result;
    }
    public Character getSex() {
        return sex;
    }
    public String getDescription() {
        return description;
    }
    
    private Object readResolve(){
        return getInstance(sex);
    }
    

}

