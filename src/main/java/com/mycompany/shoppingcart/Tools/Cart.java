/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shoppingcart.Tools;

import com.mycompany.shoppingcart.Class.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djidjelly Siclait
 */
public class Cart {
    // Attributes
    private List<Item> content;
    private float totalPrice;
    
    // Constructor
    public Cart(){
        content = new ArrayList<>();
        this.setTotalPrice(0);
    }
    
    public void setContent(List<Item> content){
        this.content = content;
    }
    
    public List<Item> getContent(){
        return content;
    }
    
    public void setTotalPrice(float totalPrice){
        this.totalPrice = totalPrice;
    }
    
    public float getTotalPrice(){
        return totalPrice;
    }
}
