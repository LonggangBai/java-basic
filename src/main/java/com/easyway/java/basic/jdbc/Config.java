/**
 * Project Name:java-basic
 * File Name:Config.java
 * Package Name:com.easyway.java.basic.jdbc
 * Date:2015-1-21下午2:47:56
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ClassName:Config <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午2:47:56 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class Config {
    private final static Logger Logger = LoggerFactory.getLogger(Config.class);
    public static String URL=null;
    private static Properties properties=new Properties();
    public static String PASSWD=null;
    public static String DRIVERCLASS=null;
    public static String USERNAME=null;

    static {
        InputStream inputStream = Config.class.getResourceAsStream("./jdbc.properties");
        try {
            properties.load(inputStream);
            URL=properties.getProperty("jdbc.url");
            DRIVERCLASS=properties.getProperty("jdbc.driverClass");
            USERNAME=properties.getProperty("jdbc.username");
            PASSWD=properties.getProperty("jdbc.password");
        }
        catch (IOException e) {
            Logger.debug("加载数据库连接配置失败..."+e.getMessage());
            e.printStackTrace();
        }
    }

}
