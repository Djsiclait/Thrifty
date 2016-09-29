/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shoppingcart.controller;

import com.mycompany.shoppingcart.Class.Item;
import com.mycompany.shoppingcart.Class.Receipt;
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
    // User
    private String username;
    private Cart cart;
    // Item
    private String productKey;
    private String itemName;
    private float itemPrice;
    private Integer inStock;
    private boolean visible;
    // Receipt
    private String transactionId;
    
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
        ThriftyManager.createANewTransactionReceipt(getUsername(), getCart().getContent(), getCart().getTotalPrice());
        return ""; // TODO: redirect to purchase detail
    }
    
    public String addItemToCart(String ID){
        // Finding the item in the store
        Item i = ThriftyManager.findRegisteredItemByProductKey(ID);
        
        if (i.getInStock() == 0)
            return "";
        
        // Updating the cart
        getCart().getContent().add(new Item(i.getProductKey(), i.getName(), i.getPrice()));
        getCart().setTotalPrice(getCart().getTotalPrice() + i.getPrice());
        
        // Updating the store
        ThriftyManager.getStore().remove(i);
        i.setInStock(i.getInStock() - 1);
        ThriftyManager.getStore().add(i);
        
        return "";
    }
    
    public String removeItemFromCart(String ID){
        if (ThriftyManager.getStore().isEmpty())
            return ""; // TODO: redirect somewhere else
        
        Item i = ThriftyManager.findRegisteredItemByProductKey(ID);
        
        // Updating the cart
        getCart().getContent().remove(new Item(i.getProductKey(), i.getName(), i.getPrice()));
        getCart().setTotalPrice(getCart().getTotalPrice() - i.getPrice());
        
        // Updating store
        ThriftyManager.getStore().remove(i);
        i.setInStock(i.getInStock() + 1);
        ThriftyManager.getStore().add(i);
        
        return "";
    }    
    
    public List<Receipt> showAllBuyerTransactions(){
        return ThriftyManager.findAllRegisisterTransactionOfRegisteredUser(getUsername());
    }
    
    public Receipt showTransactionDetails(){
        return ThriftyManager.findRegisteredTransactionReceipt(getTransactionId());
    }
    
    public List<Item> searchItem(){
        return ThriftyManager.browseStoreForRegisteredItem(getItemName());
    }
    
    public List<Item> searchStoreByPriceRange(float price){
        return ThriftyManager.browseStoreForRegisteredItemCheaperThan(price);
    }
    
    public List<Item> searchItemByPriceRange(float price){
        return ThriftyManager.browseStoreForRegisteredItemCheaperThan(getItemName(), price);
    }
    
    // Admin Functions
    public String registerNewItem(){
        try{
            ThriftyManager.registerNewItemInStore(getItemName(), getItemPrice(), getInStock());
            return "";
        } catch (IllegalArgumentException exp){
            System.out.println("\n\n\nIllegal Argument! Values must be positives");
            System.out.println(exp.getMessage());
            // TODO: Add an error return;
        }
        
        return "";
    }
    
    public void deleteItemInStore(String ID){
        ThriftyManager.deleteRegisteredItemInStore(ID);
    }
    
    public List displayAllItems(){
        return ThriftyManager.showCompleteStoreCatalog();
    }
    
    public void configureItemVisibility(){
        ThriftyManager.switchVisibilityOfRegisteredItem(getProductKey());
    }
    
    public List<Receipt> showAllTransactions(){
        return ThriftyManager.getAccounting();
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

    /**
     * @return the productKey
     */
    public String getProductKey() {
        return productKey;
    }

    /**
     * @param productKey the productKey to set
     */
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemPrice
     */
    public float getItemPrice() {
        return itemPrice;
    }

    /**
     * @param itemPrice the itemPrice to set
     */
    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * @return the inStock
     */
    public Integer getInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
}
