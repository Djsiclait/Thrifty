/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shoppingcart.controller;

import com.mycompany.shoppingcart.Class.Item;
import com.mycompany.shoppingcart.Tools.Cart;
import com.mycompany.shoppingcart.service.ThriftyManager;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private String productKey;
    private String itemName;
    private float itemPrice;
    private Integer inStock;
    
    // Initializer
    @PostConstruct
    public void init(){
        setCart(new Cart());
    }
    
    // Non Admin Functions
    public List<Item> displayCatalog(){
        return ThriftyManager.showCompleteStoreVisibleCatalog();
    }
    
    public String buyItemsInCart(){
        ThriftyManager.createANewTransactionReceipt(username, cart.getContent(), cart.getTotalPrice());
        return ""; // TODO: redirect to purchase detail
    }
    
    public String addItemToCart(){
        // Finding the item in the store
        Item i = ThriftyManager.findRegisteredItemByProductKey(productKey);
        
        if (i.getInStock() == 0)
            return "";
        
        // Updating the cart
        cart.getContent().add(new Item(i.getProductKey(), i.getName(), i.getPrice()));
        cart.setTotalPrice(cart.getTotalPrice() + i.getPrice());
        
        // Updating the store
        ThriftyManager.getStore().remove(i);
        i.setInStock(i.getInStock() - 1);
        ThriftyManager.getStore().add(i);
        
        return "";
    }
    
    public String removeItemFromCart(){
        if (ThriftyManager.getStore().isEmpty())
            return ""; // TODO: redirect somewhere else
        
        Item i = ThriftyManager.findRegisteredItemByProductKey(productKey);
        
        // Updating the cart
        cart.getContent().remove(new Item(i.getProductKey(), i.getName(), i.getPrice()));
        cart.setTotalPrice(cart.getTotalPrice() - i.getPrice());
        
        // Updating store
        ThriftyManager.getStore().remove(i);
        i.setInStock(i.getInStock() + 1);
        ThriftyManager.getStore().add(i);
        
        return "";
    }    
    
    public List<Item> searchItem(){
        return ThriftyManager.browseStoreForRegisteredItem(itemName);
    }
    
    public List<Item> searchStoreByPriceRange(float price){
        return ThriftyManager.browseStoreForRegisteredItemCheaperThan(price);
    }
    
    public List<Item> searchItemByPriceRange(float price){
        return ThriftyManager.browseStoreForRegisteredItemCheaperThan(itemName, price);
    }
    
    // Admin Functions
    public String registerNewItem(){
        try{
            ThriftyManager.registerNewItemInStore(itemName, itemPrice, inStock);
            return "";
        } catch (IllegalArgumentException exp){
            System.out.println("\n\n\nIllegal Argument! Values must be positives");
            System.out.println(exp.getMessage());
            // TODO: Add an error return;
        }
        
        return "";
    }
    
    public void deleteItemInStore(){
        ThriftyManager.deleteRegisteredItemInStore(productKey);
    }
    
    public List<Item> displayAllItems(){
        return ThriftyManager.showCompleteStoreCatalog();
    }
    
    public void configureItemVisibility(){
        ThriftyManager.switchVisibilityOfRegisteredItem(productKey);
    }

    // Getters and Setters
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
