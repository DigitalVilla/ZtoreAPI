/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ztore.connection;

import com.ztore.resources.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 767110
 */
public class DBusers {

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users;");

            while (rs.next()) {
                users.add(new User(rs.getString(1), "*****", rs.getString(3)));
//                users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
            }

            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public User getUser(String username) {
        User u = new User();
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "SELECT * FROM users WHERE username = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new User(rs.getString(1), "*****", rs.getString(3));
            }

            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public int newUser(User newUser) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "INSERT INTO users VALUES (?,?,?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newUser.getUsername());
            st.setString(2, newUser.getPassword());
            st.setString(3, newUser.getType());

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int updateUser(User user) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE users SET password = ?,userType = ? WHERE username = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getPassword());
            st.setString(2, user.getType());
            st.setString(3, user.getUsername());

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int deleteUser(String username) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "DELETE FROM users WHERE username = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public boolean validUser(User user) {
        boolean valid = false;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? and password = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            ResultSet rs = st.executeQuery();
            valid = rs.next();

            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

    public int changeType(String username) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();

        String type = this.getUser(username).getType();
        type = (type.equals("admin")) ? "user" : "admin";

        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE users SET userType = ? WHERE username = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, type);
            st.setString(2, username);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int updatePassword(String username, String password) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE users SET password = ? WHERE username = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, username);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }
}
