/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ztore.connection;

import com.ztore.resources.Item;
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
public class DBztore {

    //returns all items in store
    public List<Item> getItems() {
        List<Item> ztore = new ArrayList<>();
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products;");

            while (rs.next()) {
                ztore.add(new Item(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }

            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBztore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ztore;
    }

    //returns the specific item by id 
    public Item getItem(int id) {
        Item item = new Item();
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "SELECT * FROM products WHERE productID = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                item = new Item(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
            }

            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBztore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    public int newItem(Item newItem) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "INSERT INTO products VALUES (?,?,?,?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, nextId());
            st.setString(2, newItem.getDescription());
            st.setDouble(3, newItem.getPrice());
            st.setInt(4, newItem.getQty());

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int updateItem(Item item) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE products SET productDesc = ?, productUnitPrice = ?, productUnitsInStock = ? WHERE productID = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, item.getDescription());
            st.setDouble(2, item.getPrice());
            st.setInt(3, item.getQty());
            st.setInt(4, item.getId());

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    //update Description
    public int updateItem(int id, String description) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE products SET productDesc = ? WHERE productID = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, description);
            st.setInt(2, id);
            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    //update Quantity 
    public int updateItem(int id, int qty) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE products SET productUnitsInStock = ? WHERE productID = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, qty);
            st.setInt(2, id);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    //update Price 
    public int updateItem(int id, double price) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE products SET productUnitPrice = ? WHERE productID = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, price);
            st.setInt(2, id);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int deleteItem(int id) {
        int affected = 0;

        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "DELETE FROM products WHERE productID";
            sql += (id == 0) ? " >= ?" : " = ?;";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int nextId() {
        int id = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(productID) FROM products;");

            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }

            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBztore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
