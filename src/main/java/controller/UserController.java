package controller;



import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import service.ThriftyManager;


@ManagedBean
@Named("userControler")
@SessionScoped
public class UserController {
    private static final Logger _log = Logger.getLogger(UserController.class);

    private String loginStatus;
    private String userName;
    private String password;
    private boolean loggedIn;
    
    @PostConstruct
    public void init(){
        
    }

    public String loginMeIn() {
        System.out.println("\n\n\nHello");
        _log.info("Trying to Logging in now with UserName : " + userName);
        
        System.out.println("\n\n\nworld");
        boolean loggedIn = ThriftyManager.validateCredentialsForUserAccount(userName, password);
        this.loginStatus = loggedIn ? "Login Successful" : "Login failed";
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
