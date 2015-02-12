/**
 * Project Name:java-basic
 * File Name:DerbyJdbc.java
 * Package Name:com.easyway.java.basic.db.derby
 * Date:2015-2-12上午9:51:41
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.db.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName:DerbyJdbc <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-12 上午9:51:41 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class DerbyJdbc {

    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    String dbName = "./mydb";


    static void loadDriver() {
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doIt() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        System.out.println("starting");
        try {
            conn = DriverManager.getConnection(protocol + dbName + ";create=true");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Connected to and created database " + dbName);

        try {

            s = conn.createStatement();
            rs = s.executeQuery("select * from mytable");

            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            conn.close();
            conn = null;
            s.close();
            s = null;
            rs.close();
            rs = null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        DerbyJdbc t = new DerbyJdbc();
        t.loadDriver();
        t.doIt();
    }
}
