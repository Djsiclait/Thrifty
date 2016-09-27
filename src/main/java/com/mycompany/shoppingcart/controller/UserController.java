package com.mycompany.shoppingcart.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
//import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.mycompany.shoppingcart.service.ThriftyManager;

@ManagedBean(name ="userControler")
@SessionScoped
public class UserController implements Serializable {
    //private static final Logger _log = Logger.getLogger(UserController.class);

    private String loginStatus;
    private String userName;
    private String password;
    private boolean loggedIn;
    
    @PostConstruct
    public void init(){
        //userName = password = "";
        ThriftyManager.setupManagerConfigurations();
    }

    public String loginMeIn() {
      //  _log.info("Trying to Logging in now with UserName : " + userName);
       boolean loggedIn = ThriftyManager.validateCredentialsForUserAccount(userName, password);
       this.loginStatus = loggedIn ? "Login Successful" : "Login failed";
       userName = password = "";
       return loggedIn ? "/welcome.jsf?faces-redirect=true" : "/logmein.jsf?error=true";
    }

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
}
