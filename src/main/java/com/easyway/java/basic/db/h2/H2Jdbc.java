/**
 * Project Name:java-basic
 * File Name:H2Jdbc.java
 * Package Name:com.easyway.java.basic.db.h2
 * Date:2015-2-12上午9:49:14
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.db.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * ClassName:H2Jdbc <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-12 上午9:49:14 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class H2Jdbc {
    public void server() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        // add application code here
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + "," + rs.getString("NAME"));
        }
        conn.close();

    }


    public void memery() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:test2", "sa", "");
        // add application code here
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("CREATE TABLE TEST_MEM(ID INT PRIMARY KEY,NAME VARCHAR(255));");
        stmt.executeUpdate("INSERT INTO TEST_MEM VALUES(1, 'Hello_Mem');");
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST_MEM");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + "," + rs.getString("NAME"));
        }
        conn.close();
    }
}
