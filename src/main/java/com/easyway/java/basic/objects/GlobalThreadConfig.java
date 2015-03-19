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
 * ClassName:GlobalThreadConfig <br/>
 * Function: 在不改变GlobalThreadConfig类的接口的前提下，可以修改静态工厂方法实现但李雷改为为每一个线程分配一个实例。 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-19 上午10:20:13 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class GlobalThreadConfig {
    private static final ThreadLocal<GlobalThreadConfig> threadConfig = new ThreadLocal<GlobalThreadConfig>();
    private Properties properties = null;


    private GlobalThreadConfig() {
        InputStream in = null;
        try {
            in = GlobalThreadConfig.class.getClass().getResourceAsStream("mq.properties");
            properties.load(in);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace();

            }
        }
    }


    public static GlobalThreadConfig getInstance() {// 静态工厂方法
        GlobalThreadConfig config = threadConfig.get();
        if (config == null) {
            config = new GlobalThreadConfig();
            threadConfig.set(config);
        }
        return config;
    }


    public Properties getProperties() {
        return properties;
    }
}
