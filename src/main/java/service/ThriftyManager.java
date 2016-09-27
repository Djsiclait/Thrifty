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
}
