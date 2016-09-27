/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shoppingcart.Class;

import com.mycompany.shoppingcart.Tools.Cart;


/**
 *
 * @author Djidjelly Siclait
 */
public class User {
    //Attributes
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private boolean admin;
    private Cart cart;

    //Constructors
    public User(){
        this.setCart();
    }

    public User(String username, String firstname, String lastname, String password, boolean admin){
        this.setUsername(username);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPassword(password);
        this.setAdmin(admin);
        this.setCart();
    }
 
    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
   
    public Cart getCart(){
        return this.cart;
    }
    
    public void setCart(){
        this.cart = new Cart();
    }
}
