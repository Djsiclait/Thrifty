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
            users.add(new User("admin", "Administrator", "admin", "admin", true));
    }
}
