/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shoppingcart.service;

import com.mycompany.shoppingcart.Class.Item;
import com.mycompany.shoppingcart.Class.Receipt;
import com.mycompany.shoppingcart.Class.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Djidjelly Siclait
 */
public class ThriftyManager {
    // Attributes 
    private static List<User> users;
    private static List<Item> store;
    private static List<Receipt> accounting;

    /**
     * @return the store
     */
    public static List<Item> getStore() {
        return store;
    }

    /**
     * @param aStore the store to set
     */
    public static void setStore() {
        store = new ArrayList<>();
    }

    /**
     * @return the accounting
     */
    public static List<Receipt> getAccounting() {
        return accounting;
    }

    /**
     * @param aAccounting the accounting to set
     */
    public static void setAccounting() {
        accounting = new ArrayList<>();
    }
    
    // Defining Singleton
    private ThriftyManager(){
        //setupManagerConfigurations();
    }
    
    // Setup
    public static void setupManagerConfigurations(){
        // initial setup
        if (users == null)
            users = new ArrayList<>();
        
        if (getStore() == null)
            setStore();
        
        if (getAccounting() == null)
            setAccounting();
            
        // Creating default admin
        if(users.isEmpty())
            users.add(new User("ADMIN", "Administrator", "admin", "admin", true));
        
        if(store.isEmpty())
            store.add(new Item("Green Polo", 15.95f, 29, true));
    }
    
    // Auxiliary Functions
    public static User findUserAccountByUsername(String username){
        for(User u: users)
            if(u.getUsername().equals(username.toUpperCase()))
                return u;
        
        return null; // Could not find the user Account
    }
    
    private static boolean isUsernameTaken(String username){
        User user = findUserAccountByUsername(username.toUpperCase());
        
        return (user != null);
    }
    
    public static Item findRegisteredItemByProductKey(String productKey){
        for(Item i: getStore())
            if (i.getProductKey().equals(productKey))
                return i;
        
        return null;
    }
    
    private static String hideRegisteredItemFromUsers(String productKey){
        Item item = findRegisteredItemByProductKey(productKey);
        
        getStore().remove(item);
        
        item.setVisible(false);
        
        getStore().add(item);
        return "hidden";
    }
    
    private static String showRegisteredItemToUsers(String productKey){
        Item item = findRegisteredItemByProductKey(productKey);
        
        getStore().remove(item);
        
        item.setVisible(true);
        
        getStore().add(item);
        return "revealed";
    }
    
    private static List<Item> itemCatalogSorter(List<Item> catalog){
        Collections.sort(catalog, new Comparator<Item>(){
            @Override
            public int compare(Item i1, Item i2){
                return i1.getName().compareTo(i2.getName());
            }
        });
        
        return catalog;
    }
    
    private static List<Receipt> organizeReceiptsByDate(List<Receipt> transactions){
          
        Collections.sort(transactions, new Comparator<Receipt>(){
            @Override
            public int compare(Receipt r1, Receipt r2){
                return r1.getTransactionDate().compareTo(r2.getTransactionDate());
            }
        });
        
        return transactions;
    }
    
    // User Related Functions
    public static boolean createNewNonAdminUserAccount(String username, String firstName, String lastName, String password){
        if (!isUsernameTaken(username.toUpperCase())){
            users.add(new User(username.toUpperCase(), firstName.toUpperCase(), lastName.toUpperCase(), password, false));
            return true;
        }
        else
            return false;
    }
    
    public static void deleteNonAdminUserAccount(String username){
        users.remove(findUserAccountByUsername(username.toUpperCase()));
    }
    
    public static boolean validateCredentialsForUserAccount(String username, String password){
        User user = findUserAccountByUsername(username.toUpperCase());
        
        return (user == null) ? false : user.getPassword().equals(password);
    }
    
    public static List<User> showAllRegisteredUserAccounts(){
        return users;
    }
    
    // Store Related Functions
    public static void registerNewItemInStore(String name, float price, Integer inStock){
        if (price < 0.00f || inStock < 0)
            throw new IllegalArgumentException();
        
        getStore().add(new Item(name.toUpperCase(), price, inStock, true));
    }
    
    public static void deleteRegisteredItemInStore(String productKey){
        getStore().remove(findRegisteredItemByProductKey(productKey));
    }
    
    public static List<Item> showCompleteStoreCatalog(){
        return itemCatalogSorter(getStore());
    }
    
    public static List<Item> showCompleteStoreVisibleCatalog(){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: getStore())
            if (i.isVisible())
                catalog.add(i);
        
        return catalog.isEmpty() ? null : itemCatalogSorter(catalog);
    }
    
    public static List<Item> browseStoreForRegisteredItem(String name){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: getStore())
            if (i.getName().equals(name.toUpperCase()) || i.getName().contains(name.toUpperCase()))
                catalog.add(i);
        
        return catalog.isEmpty() ? null : itemCatalogSorter(catalog);
    }
    
    public static List<Item> browseStoreForRegisteredItemCheaperThan(float price){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: getStore())
            if (i.getPrice() < price)
                catalog.add(i);
        
        return catalog.isEmpty() ? null : catalog;
    }
    
    public static List<Item> browseStoreForRegisteredItemCheaperThan(String name, float price){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: getStore())
            if (i.getName().equals(name.toUpperCase()) || i.getName().contains(name.toUpperCase()))
                if(i.getPrice() < price)
                    catalog.add(i);
        
        return catalog.isEmpty() ? null : itemCatalogSorter(catalog);
    }
    
    public static String switchVisibilityOfRegisteredItem(String productKey){
        Item item = findRegisteredItemByProductKey(productKey);
        
        return item.isVisible() ? hideRegisteredItemFromUsers(productKey) : showRegisteredItemToUsers(productKey);
    }
    
    // Receipt Related Functions
    public static void createANewTransactionReceipt(String buyer, List<Item> contents, float totalPrice){
        getAccounting().add(new Receipt(buyer.toUpperCase(), contents, totalPrice));
        
        accounting = organizeReceiptsByDate(accounting);
    }
    
    public static Receipt findRegisteredTransactionReceipt(String transactionId){
        for(Receipt r : getAccounting())
            if (r.getTransactionId().equals(transactionId))
                return r;
        
        return null;
    }
    
    public static List<Receipt> findAllRegisisterTransactionOfRegisteredUser(String username){
        List<Receipt> receipts = new ArrayList<>();
        
        for(Receipt r : getAccounting())
            if (r.getBuyer().equals(username.toUpperCase()))
                receipts.add(r);
        
        return receipts.isEmpty() ? null : organizeReceiptsByDate(receipts);
    }
}
