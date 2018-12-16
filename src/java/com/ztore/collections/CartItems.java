/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ztore.collections;


import com.ztore.resources.CartItem;
import java.util.List;

/**
 *
 * @author 767110
 */
public class CartItems {

    private List<CartItem> cart;

    public CartItems() {
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public void setCartItems(List<CartItem> cart) {
        this.cart = cart;
    }

}
