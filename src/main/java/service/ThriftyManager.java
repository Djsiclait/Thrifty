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
    private static List<User> users;
    private static List<Item> store;
    
    // Defining Singleton
    private ThriftyManager(){
        setupManagerConfigurations();
    }
    
    // Setup
    private static void setupManagerConfigurations(){
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
    
    private static Item findRegisteredItemByProductKey(String productKey){
        for(Item i: store)
            if (i.getProductKey().equals(productKey))
                return i;
        
        return null;
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
    
    // Store Related Functions
    public static void registerNewItemInStore(String name, float price, Integer inStock){
        if (price < 0.00f || inStock < 0)
            throw new IllegalArgumentException();
        
        store.add(new Item(name.toUpperCase(), price, inStock, true));
    }
    
    public static void deleteRegisteredItemInStore(String productKey){
        store.remove(findRegisteredItemByProductKey(productKey));
    }
    
    public static List<Item> browseStoreForRegisteredItem(String name){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: store)
            if (i.getName().equals(name.toUpperCase()) || i.getName().contains(name.toUpperCase()))
                catalog.add(i);
        
        return catalog.isEmpty() ? null : catalog;
    }
    
    public static List<Item> browseStoreForRegisteredItemCheaperThan(float price){
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: store)
            if (i.getPrice() < price)
                catalog.add(i);
        
        return catalog.isEmpty() ? null : catalog;
    }
    
    public static List<Item> browseStoreForRegisteredItemCheaperThan(String name, float price){
        
        List<Item> catalog = new ArrayList<>();
        
        for(Item i: store)
            if (i.getName().equals(name.toUpperCase()) || i.getName().contains(name.toUpperCase()))
                if(i.getPrice() < price)
                    catalog.add(i);
        
        return catalog.isEmpty() ? null : catalog;
    }
    
}
