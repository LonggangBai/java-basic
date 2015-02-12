/**
 * Project Name:java-basic
 * File Name:SqliteJdbc.java
 * Package Name:com.easyway.java.basic.db.sqlite
 * Date:2015-2-12上午9:40:45
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.db.sqlite;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.JDBC;


/**
 * ClassName:SqliteJdbc <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-2-12 上午9:40:45 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class SqliteJdbc {
    private final static Logger logger = LoggerFactory.getLogger(SqliteJdbc.class);

    private Connection conn = null;
    private Statement statement = null;


    private void initSqlite(String dataPath) {
        try {
            logger.info("创建数据库连接。。。");
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dataPath);
            statement = conn.createStatement();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void initSqliteTable() {
        try {
            logger.info("创建数据库表。。。");
            this.statement.executeUpdate("DROP TABLE if exists DB_certificate;");
            this.statement.executeUpdate("CREATE TABLE DB_certificate(_id TEXT PRIMARY KEY, type TEXT, name TEXT);");

            this.statement.executeUpdate("DROP TABLE if exists DB_city;");
            this.statement
                .executeUpdate("CREATE TABLE DB_city(code TEXT PRIMARY KEY, name TEXT,district_code TEXT,PROVINCE_CODE TEXT);");

            this.statement.executeUpdate("DROP TABLE if exists DB_province;");
            this.statement
                .executeUpdate("CREATE TABLE DB_province(code TEXT PRIMARY KEY, name TEXT, shortname TEXT,district_code TEXT,start_date TEXT,stop_date TEXT);");

            this.statement.executeUpdate("DROP TABLE if exists DB_seatType;");
            this.statement.executeUpdate("CREATE TABLE DB_seatType(_id TEXT PRIMARY KEY, type TEXT, name TEXT);");

            this.statement.executeUpdate("DROP TABLE if exists DB_ticketType;");
            this.statement.executeUpdate("CREATE TABLE DB_ticketType(_id TEXT PRIMARY KEY, type TEXT, name TEXT);");

            this.statement.executeUpdate("DROP TABLE if exists DB_carGrade;");
            this.statement.executeUpdate("CREATE TABLE DB_carGrade(_id TEXT PRIMARY KEY, name TEXT, grade TEXT);");

            this.statement.executeUpdate("DROP TABLE if exists DB_passenger;");
            this.statement
                .executeUpdate("CREATE TABLE DB_passenger(PASSENGER_ID NUMBER(16) not null, USERID NUMBER(16) not null,NAME VARCHAR2(50) not null,SEX VARCHAR2(1) default 'M',COUNTRY NUMBER,CERT_TYPE NUMBER,CERT_NO VARCHAR2(18),PASSENGER_TYPE VARCHAR2(1),POSTCODE CHAR(6),ADRESS VARCHAR2(50),ADDTIME DATE,VERIFYSTATE CHAR(1),TEL VARCHAR2(18),EMAIL VARCHAR2(50));");
            this.statement.executeUpdate("DROP TABLE if exists DB_staion;");
            this.statement
                .executeUpdate("CREATE TABLE DB_staion(IDCODE_STATION VARCHAR2(30) not null,NAME_STATION VARCHAR2(50),DISTRICT_CODE VARCHAR2(100),STATION_ABBREVIATION VARCHAR2(50),STATION_TYPE VARCHAR2(8),STATION_LEVEL VARCHAR2(8),STATION_ADDRESS VARCHAR2(100),STATION_TEL VARCHAR2(18),SUPERINTENDENT VARCHAR2(50));");
            this.statement.executeUpdate("DROP TABLE if exists DB_insuranceProducts;");
            this.statement
                .executeUpdate("CREATE TABLE DB_insuranceProducts(INSURANCE_PRODUCTS_NUM  NUMBER(5) not null,INSURANCE_PRODUCTS_CODE VARCHAR2(9),INSURANCE_PRODUCTS_NAME VARCHAR2(40),INSURANCE_COMPANY_CODE  VARCHAR2(10),INSURANCE_AMOUNT NUMBER(8,2),INSURANCE_COST NUMBER(6,2),INCOME_TYPE NUMBER(1),INCOME_VALUE NUMBER(4,2),USER_STATE NUMBER(1),COMPANY_DESC VARCHAR2(500),START_DATE NUMBER(8),STOP_DATE NUMBER(8),VALID_FLAG CHAR(1),OPERATOR_ID NUMBER(16),OPERATE_TIME DATE);");
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertInsuranceProducts(Map m) {
        try {
            logger.info("insert DB_insuranceProducts:" + m.toString());
            PreparedStatement prep =
                    this.conn
                        .prepareStatement("insert into DB_insuranceProducts values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            prep.setInt(1, ((BigDecimal) m.get("INSURANCE_PRODUCTS_NUM")).intValue());
            prep.setString(2, null);// 未找到保险产品编码
            prep.setString(3, (String) m.get("INSURANCE_TYPE_NAME"));
            prep.setString(4, (String) m.get("INSURANCE_COMPANY_CODE"));
            if (m.get("INSURANCE_AMOUNT") != null)
                prep.setString(5, ((BigDecimal) m.get("INSURANCE_AMOUNT")).toString());
            if (m.get("INSURANCE_COST") != null)
                prep.setString(6, ((BigDecimal) m.get("INSURANCE_COST")).toString());
            // prep.setBigDecimal(6, (BigDecimal) m.get("INSURANCE_COST"));
            if (m.get("INCOME_TYPE") != null)
                prep.setInt(7, ((BigDecimal) m.get("INCOME_TYPE")).intValue());
            if (m.get("INCOME_VALUE") != null)
                prep.setString(8, ((BigDecimal) m.get("INCOME_VALUE")).toString());
            if (m.get("USER_STATE") != null)
                prep.setInt(9, ((BigDecimal) m.get("USER_STATE")).intValue());// USER_STATE
            prep.setString(10, (String) m.get("COMPANY_DESC"));
            if (m.get("START_DATE") != null)
                prep.setString(11, ((BigDecimal) m.get("START_DATE")).toString());
            if (m.get("STOP_DATE") != null)
                prep.setString(12, ((BigDecimal) m.get("STOP_DATE")).toString());
            prep.setString(13, null);// VALID_FLAG
            if (m.get("OPERATOR_ID") != null)
                prep.setString(14, ((BigDecimal) m.get("OPERATOR_ID")).toString());

            if (m.get("OPERATE_TIME") != null)
                prep.setDate(15, new java.sql.Date(((java.sql.Timestamp) m.get("OPERATE_TIME")).getTime()));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertStaion(Map m) {
        try {
            logger.info("insert DB_staion:" + m.toString());
            PreparedStatement prep = this.conn.prepareStatement("insert into DB_staion values (?,?,?,?,?,?,?,?,?);");
            prep.setString(1, (String) m.get("IDCODE_STATION"));
            prep.setString(2, (String) m.get("NAME_STATION"));
            prep.setString(3, (String) m.get("DISTRICT_CODE"));
            prep.setString(4, (String) m.get("STATION_ABBREVIATION"));
            prep.setString(5, (String) m.get("STATION_TYPE"));
            prep.setString(6, (String) m.get("STATION_LEVEL"));
            prep.setString(7, (String) m.get("STATION_ADDRESS"));
            prep.setString(8, (String) m.get("STATION_TEL"));
            prep.setString(9, (String) m.get("SUPERINTENDENT"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertPassenger(Map m) {
        try {
            logger.info("insert DB_passenger:" + m.toString());
            PreparedStatement prep =
                    this.conn.prepareStatement("insert into DB_passenger values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            prep.setInt(1, ((BigDecimal) m.get("PASSENGER_ID")).intValue());
            prep.setString(2, (String) m.get("USERID"));
            prep.setString(3, (String) m.get("NAME"));
            prep.setString(4, (String) m.get("SEX"));
            if (m.get("COUNTRY") != null)
                prep.setInt(5, ((BigDecimal) m.get("COUNTRY")).intValue());
            if (m.get("CERT_TYPE") != null)
                prep.setInt(6, ((BigDecimal) m.get("CERT_TYPE")).intValue());
            prep.setString(7, (String) m.get("CERT_NO"));
            prep.setString(8, (String) m.get("PASSENGER_TYPE"));
            prep.setString(9, (String) m.get("POSTCODE"));
            prep.setString(10, (String) m.get("ADRESS"));
            if (m.get("ADDTIME") != null){
                prep.setDate(11,  new java.sql.Date(Long.valueOf(m.get("ADDTIME").toString())));
            }
            prep.setString(12, (String) m.get("VERIFYSTATE"));
            prep.setString(13, (String) m.get("TEL"));
            prep.setString(14, (String) m.get("EMAIL"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertCarGradee(Map m) {
        try {
            logger.info("insert DB_carGrade:" + m.toString());
            PreparedStatement prep = this.conn.prepareStatement("insert into DB_carGrade values (?,?,?);");
            prep.setString(1, (String) m.get("VEHICLE_TYPE_LEVEL_CODE"));
            prep.setString(2, (String) m.get("VEHICLE_TYPE_LEVEL_NEME"));
            prep.setString(3, (String) m.get("VEHICLE_TYPE_LEVEL_CODE"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertTicketType(Map m) {
        try {
            logger.info("insert DB_ticketType:" + m.toString());
            PreparedStatement prep = this.conn.prepareStatement("insert into DB_ticketType values (?,?,?);");
            prep.setString(1, m.get("TICKET_TYPE").toString());
            prep.setString(2, m.get("TICKET_TYPE").toString());
            prep.setString(3, (String) m.get("TICKET_TYPE_NAME"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertSeatType(Map m) {
        try {
            logger.info("insert DB_seatType:" + m.toString());
            PreparedStatement prep = this.conn.prepareStatement("insert into DB_seatType values (?,?,?);");
            prep.setString(1, (String) m.get("SEAT_TYPE"));
            prep.setString(2, (String) m.get("SEAT_TYPE"));
            prep.setString(3, (String) m.get("SEAT_TYPE_NAME"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertProvince(Map m) {
        try {
            logger.info("insert DB_province:" + m.toString());
            PreparedStatement prep = this.conn.prepareStatement("insert into DB_province values (?,?,?,?,?,?);");
            prep.setString(1, (String) m.get("PROVINCE_CODE"));
            prep.setString(2, (String) m.get("PROVINCE_NAME"));
            prep.setString(3, (String) m.get("PROVINCE_SHORTNAME"));
            prep.setString(4, (String) m.get("DISTRICT_CODE"));
            prep.setString(5, (String) m.get("PROVINCE_START_DATE"));
            prep.setString(6, (String) m.get("PROVINCE_STOP_DATE"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertCertificate(Map m) {
        try {
            logger.info("insert DB_certificate:" + m.toString());
            PreparedStatement prep = this.conn.prepareStatement("insert into DB_certificate values (?,?,?);");
            prep.setString(1, (String) m.get("CERTIFICATE_TYPE_CODE"));
            prep.setString(2, (String) m.get("CERTIFICATE_TYPE_SHORTCODE"));
            prep.setString(3, (String) m.get("CERTIFICATE_TYPE_NAME"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void insertCity(Map city) {
        try {
            logger.info("insert DB_city:" + city.toString());
            PreparedStatement prep = this.conn.prepareStatement("insert into DB_city values (?,?,?,?);");
            prep.setString(1, city.get("CITY_CODE").toString());
            prep.setString(2, (String) city.get("CITY_NAME"));
            prep.setString(3, (String) city.get("DISTRICT_CODE"));
            prep.setString(4, (String) city.get("PROVINCE_CODE"));
            prep.addBatch();
            prep.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.close();
                this.statement.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void close() {
        try {
            if (conn != null)
                this.conn.close();
            if (statement != null)
                this.statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updatePhoneDB(String filePath) {
        this.initSqlite(filePath);
        this.initSqliteTable();
        List<Map> list = null;
        // 城市表
      
    }
}
