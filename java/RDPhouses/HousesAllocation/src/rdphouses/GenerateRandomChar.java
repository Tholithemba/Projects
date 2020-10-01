/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

import java.util.Random;

/**
 *
 * @author tholithemba
 */
public class GenerateRandomChar {
    
    //initialised array whith dinstict charactors to create password
    private static final char[] char_password={'q','w','6','e','9','r','t',
        'y','u','8','i','o','p','a','s','d','2','f','g','h','3',
        'j','k','l','z','x','c','0','v','b',',','.',';','@','1',
        '!','7','#','%','^','&','_','=','5','-','+','(',')','?','<','>'};
    
    private final char[] char_username={'q','w','e','r','t','y','u','i','o'
    ,'p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
    
    Random rand = new Random();
    private static final int char_pass_size = char_password.length;
    private int r;
    private static String pswrd_tobe_sent;
    


    
    public String generateUsername()
    {
        int random;
        String random_username ="";
        int char_username_size = char_username.length;
        int size = 10;
        for(r = 0; r < size; r++){
            random = rand.nextInt(char_username_size);
            random_username = random_username+char_username[random];
        }
                
        return random_username;
    }
    
    //set password to be send to the user via email
    public void setPswrdTobeSent(String setpassword)
    {
        pswrd_tobe_sent = setpassword;
    }
    
    //get password to be send to the user via email
    public String getPswrdTobeSent()
    {
        return pswrd_tobe_sent;
    }
    
    //generate radom charactors to create password of length 10
    public String generatePassword()
    {
        int random;
        String random_password ="";

        int size = 10;
        for(r = 0; r < size; r++){
            random = rand.nextInt(char_pass_size);
            random_password = random_password+char_password[random];
        }
        
        return passwordEncryption(random_password);
    }
    //end of generatePassword() method
    
    //encrytpe password
    public String passwordEncryption(String password)
    {
        String encrypted_password = "";
        setPswrdTobeSent(password);
        String tobe_reversed = strTobeReversed(password);
        String reverse_str = reverseString(tobe_reversed);
        
        char[] passwrd = reverse_str.toCharArray();
       
        int key = 4;
        for(char generate_char: passwrd){
            generate_char += key;
            encrypted_password += generate_char;
        }
        
        return encrypted_password;
    }
    //end of passwordEncryption method
    
    //decrypte password
    public String passwordDecryption(String password)
    {        
        char[] passwrd = password.toCharArray();
        String decrypted_password = "";
        String reverse_str;
        String orig_str;
        int key = 4;
        
        for(char generate_char: passwrd){
            generate_char -= key;
            decrypted_password += generate_char;
        }
        
        reverse_str = reverseString(decrypted_password);
        
        orig_str = toOriginalString(reverse_str);
        
        return orig_str;
    }
    // end of passwordDecryption method
    
    //reverse password string
    private String reverseString(String str)
    {
        int size = str.length();
       
        String reverse_str = "";
        for(r = size - 1; r >= 0; r--)
            reverse_str += str.charAt(r);
        
        return reverse_str;
    }
    
    
    //reverse back to origina string( for decryption)
    private String toOriginalString(String str)
    {
        String original_str = "";
        int size = str.length()/2;
        
        for(r = 0; r < size; r++){
            original_str = original_str + str.charAt(2*r+1);
        }
        
        return original_str;
    }
    
    //add more chractors to the password
    private String strTobeReversed(String str)
    {
        int random;      
        String tobe_reversed = "";
        int size = str.length();
        
        for(r = 0; r < size; r++){
            random = rand.nextInt(char_pass_size);
            tobe_reversed = tobe_reversed +char_password[random] + str.charAt(r);
        }
       
        return tobe_reversed;
    }
      
}
