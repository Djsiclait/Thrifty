/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Class.Item;
import Class.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djidjelly Siclait
 */
public class ThriftyManager {
    // Attributes 
    private List<User> users;
    private List<Item> store;
    
    // Defining Singleton
    private ThriftyManager(){
        setupManagerConfigurations();
    }
    
    // Setup
    private void setupManagerConfigurations(){
        // initial setup
        if (users == null)
            users = new ArrayList<>();
        
        if (store == null)
            store = new ArrayList<>();
            
        // Creating default admin
        if(users.isEmpty())
            users.add(new User("ADMIN", "Administrator", "admin", "admin", true));
    }
    
    // Auxiliary Functions
    public User findUserAccountByUsername(String username){
        for(User u: users)
            if(u.getUsername().equals(username.toUpperCase()))
                return u;
        
        return null; // Could not find the user Account
    }
    
    private boolean isUsernameTaken(String username){
        User user = findUserAccountByUsername(username.toUpperCase());
        
        return (user != null);
    }
    
    private Item findRegisteredItemByProductKey(String productKey){
        for(Item i: store)
            if (i.getProductKey().equals(productKey))
                return i;
        
        return null;
    }
    
    private String hideRegisteredItemFromUsers(String productKey){
        Item item = findRegisteredItemByProductKey(productKey);
        
        store.remove(item);
        
        item.setVisible(false);
        
        store.add(item);
        return "hidden";
    }
    
    private String showRegisteredItemToUsers(String productKey){
        Item item = findRegisteredItemByProductKey(productKey);
        
        store.remove(item);
        
        item.setVisible(true);
        
        store.add(item);
        return "revealed";
    }
    
    // User Related Functions
    public boolean createNewNonAdminUserAccount(String username, String firstName, String lastName, String password){
        if (!isUsernameTaken(username.toUpperCase())){
            users.add(new User(username.toUpperCase(), firstName.toUpperCase(), lastName.toUpperCase(), password, false));
            return true;
        }
        else
            return false;
    }
    
    public void deleteNonAdminUserAccount(String username){
        users.remove(findUserAccountByUsername(username.toUpperCase()));
    }
    
    public boolean validateCredentialsForUserAccount(String username, String password){
        User user = findUserAccountByUsername(username.toUpperCase());
        
        return (user == null) ? false : user.getPassword().equals(password);
    }
    
    // Store Related Functions
    public void registerNewItemInStore(String name, float price, Integer inStock){
        if (price < 0.00f || inStock < 0)
            throw new IllegalArgumentException();
        
        store.add(new Item(name.toUpperCase(), price, inStock, true));
    }
    
    public void deleteRegisteredItemInStore(String productKey){
        store.remove(findRegisteredItemByProductKey(productKey));
    }
    
    public List<Item> browseStoreForRegisteredItem(String name){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: store)
            if (i.getName().equals(name.toUpperCase()) || i.getName().contains(name.toUpperCase()))
                catalog.add(i);
        
        return catalog.isEmpty() ? null : catalog;
    }
    
    public List<Item> browseStoreForRegisteredItemCheaperThan(float price){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: store)
            if (i.getPrice() < price)
                catalog.add(i);
        
        return catalog.isEmpty() ? null : catalog;
    }
    
    public List<Item> browseStoreForRegisteredItemCheaperThan(String name, float price){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: store)
            if (i.getName().equals(name.toUpperCase()) || i.getName().contains(name.toUpperCase()))
                if(i.getPrice() < price)
                    catalog.add(i);
        
        return catalog.isEmpty() ? null : catalog;
    }
    
    public String switchVisibilityOfRegisteredItem(String productKey){
        Item item = findRegisteredItemByProductKey(productKey);
        
        return item.isVisible() ? hideRegisteredItemFromUsers(productKey) : showRegisteredItemToUsers(productKey);
    }
    
}
