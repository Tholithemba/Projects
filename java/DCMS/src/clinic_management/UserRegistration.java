
package clinic_management;

/**
 *
 * @author tholithemba
 */
public class UserRegistration {
    
    private static String firstname;
    private static String lastname;
    private static String username;
    private static String password;
    
    //set first name
    public void setFirstname(String set_firstname){
        firstname = set_firstname;
    }
    
    //get first name
    public String getFirstName(){
        return firstname;
    }
    
    //set lastname
    public void setLastname(String set_lastname){
        lastname = set_lastname;
    }
    
    //get lastname
    public String getLastname(){
        return lastname;
    }

    //set username/email
    public void setUsername(String set_username){
        username = set_username;
    }
    
    //get username/email
    public String getUsername(){
        return username;
    }
    
    //set password
    public void setPassword(String set_password){
        password = set_password;
    }
  
    //get password
    public String getPassword(){
        return password;
    }
}