/**
 * Created by Djidjelly Siclait on 9/23/2016.
 */
package Class;

public class User {
    //Attributes
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private boolean admin;

    //Constructors
    public User(){

    }

    public User(String username, String firstname, String lastname, String password, boolean admin){
        this.setUsername(username);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPassword(password);
        this.setAdmin(admin);
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
