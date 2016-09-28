package com.mycompany.shoppingcart.controller;

import com.mycompany.shoppingcart.Class.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
//import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.mycompany.shoppingcart.service.ThriftyManager;
import java.util.List;

@ManagedBean(name ="userControler")
@SessionScoped
public class UserController implements Serializable {
    //private static final Logger _log = Logger.getLogger(UserController.class);

    private String loginStatus;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private boolean loggedIn;
    
    @PostConstruct
    public void init(){
        //userName = password = "";
        ThriftyManager.setupManagerConfigurations();
    }

    // Functions
    public String loginMeIn() {
      //  _log.info("Trying to Logging in now with UserName : " + userName);
       boolean loggedIn = ThriftyManager.validateCredentialsForUserAccount(userName, password);
       this.loginStatus = loggedIn ? "Login Successful" : "Login failed";
       userName = password = "";
       return loggedIn ? "/welcome.jsf?faces-redirect=true" : "/logmein.jsf?error=true";
    }
    
    public String createNewAccount(){
        return ThriftyManager.createNewNonAdminUserAccount(userName, firstName, lastName, password) ? 
                "/logmein.jsf?error=false" : // new user account was created
                "/register.jsf?error=true"; // register form was filled incorrectly
    }
    
    // This is only for ADMIN
    public List<User> showAllUserAccount(){
        return ThriftyManager.showAllRegisteredUserAccounts();
    }
    
    public void deleteUser(){
        ThriftyManager.deleteNonAdminUserAccount(userName);
    }

    // Getters and Setters
    public String getLoginStatus() {
        return this.loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
