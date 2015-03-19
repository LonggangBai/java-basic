/**
 * Project Name:java-basic
 * File Name:GlobalConfig.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-19上午10:20:13
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 静态工厂方法最主要的特点是:每次被调用的时候，不一定要创建一个新的对象。利用这一特点。静态工厂方法可以用来创建以下实例。
 * 1.单例类：只有唯一的实例的类。
 * 2.枚举类：实例的数量有限的类。
 * 3.具有实例缓存的类：能把已经创建的实例暂且存放在缓存中的类。
 * 4.具有实例缓存的不可变类，不可变类的实例一旦创建，其属性值就不会被改变。
 * ClassName:GlobalConfig <br/>
 * Function: 单利类是指仅有一个实例的类，在系统中具有唯一性的组件可作为单例类。 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 上午10:20:13 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class GlobalConfig {
    private static final GlobalConfig INSTANCE=new GlobalConfig();
    private Properties properties=new Properties();
    private GlobalConfig(){
        InputStream in=null;
        try {
            in=GlobalConfig.class.getClass().getResourceAsStream("mq.properties");
            properties.load(in);
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace();
                
            }
        }
    }
    
    public static GlobalConfig getInstance(){//静态工厂方法
        return INSTANCE;
    }
    public Properties getProperties(){
        return properties;
    }
}

