/**
 * 
 */
package com.easyway.java.basic.syncdb;

import java.util.HashMap;

import javax.sql.DataSource;

/**
 * @author longgangbai
 * 2015-1-20  下午4:08:08
 */
public class ConnectionFactory{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private DataSource masterDb;
    private DataSource salveDb;
    public DataSource getMasterDb() {
        return masterDb;
    }
    public void setMasterDb(DataSource masterDb) {
        this.masterDb = masterDb;
    }
    public DataSource getSalveDb() {
        return salveDb;
    }
    public void setSalveDb(DataSource salveDb) {
        this.salveDb = salveDb;
    }
    
}
