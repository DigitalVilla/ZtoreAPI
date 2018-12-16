/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ztore.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author john
 */
public class DBCP {
    
    private static DBCP pool = null;
    
    private static DataSource dataSource = null;
    
    private DBCP() {
        try {
            
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/ztore");
            
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized DBCP getInstance() {
        
        if (pool == null) {
            pool = new DBCP();
        }
        
        return pool;
    }
    
    public Connection getConnection() {
        
        try {
            return dataSource.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
 
    }
    
    public void freeConnection(Connection conn) {
        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
