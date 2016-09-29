/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shoppingcart.controller;

import com.mycompany.shoppingcart.Tools.Cart;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Djidjelly Siclait
 */
@ManagedBean(name="store")
@SessionScoped
public class StoreController {
    // Attributes
    private String username;
    private Cart cart;
    
    // Initializer
    @PostConstruct
    public void init(){
        setCart(new Cart());
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    
}
