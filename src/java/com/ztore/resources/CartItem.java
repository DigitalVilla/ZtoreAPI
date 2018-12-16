/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ztore.resources;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CartItem {

    private String username;
    private int id;
    private int qty;

    public CartItem() {
    }

    public CartItem(String username, int id, int qty) {
        this.username = username;
        this.id = id;
        this.qty = qty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String toString () {
        return "User: "+username+" Item id: "+id+" Qty: "+qty; 
    }
}
