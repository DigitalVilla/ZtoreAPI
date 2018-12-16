/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ztore.connection;

import com.ztore.collections.CartItems;
import com.ztore.resources.CartItem;
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
public class DBcart {

    public List<CartItem> getCart() {
        List<CartItem> cart = new ArrayList<>();
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM savedcart");
            while (rs.next()) {
                cart.add(new CartItem(rs.getString(1), rs.getInt(2), rs.getInt(3)));
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBcart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public CartItem getItem(String username, int id) {
        CartItem c = null;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "SELECT * FROM savedcart WHERE username = ? and productID = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                c = new CartItem(rs.getString(1), rs.getInt(2), rs.getInt(3));
            }

            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBcart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public List<CartItem> getCartFor(String username) {
        List<CartItem> cart = new ArrayList<>();
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "SELECT * FROM savedcart WHERE username = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cart.add(new CartItem(rs.getString(1), rs.getInt(2), rs.getInt(3)));
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBcart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public int newItem(CartItem newItem) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "INSERT INTO savedcart VALUES (?,?,?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newItem.getUsername());
            st.setInt(2, newItem.getId());
            st.setInt(3, newItem.getQty());
            
            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int updateItem(CartItem item) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE savedcart SET numberUnitsOrdered = ? WHERE productID = ? and username = ? ";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, item.getQty());
            st.setInt(2, item.getId());
            st.setString(3, item.getUsername());
            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;

    }

    public int updateItem(String username, int itemID, int qty) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "UPDATE savedcart SET numberUnitsOrdered = ? WHERE productID = ? and username = ? ";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, qty);
            st.setInt(2, itemID);
            st.setString(3, username);
            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;

    }

    public int deleteItem(CartItem item) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "DELETE FROM savedCart  WHERE productID = ? and username = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, item.getId());
            st.setString(2, item.getUsername());

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }
    public int deleteAll(String username) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "DELETE FROM savedCart  WHERE username = ?;";
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

    public int deleteItem(String username, int itemID) {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "DELETE FROM savedCart  WHERE username = ? and productID = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, itemID);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

    public int deleteCart() {
        int affected = 0;
        DBCP cp = DBCP.getInstance();
        try {
            Connection conn = cp.getConnection();
            String sql = "DELETE FROM savedCart;";
            PreparedStatement st = conn.prepareStatement(sql);

            affected = st.executeUpdate();

            st.close();
            cp.freeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affected;
    }

}
