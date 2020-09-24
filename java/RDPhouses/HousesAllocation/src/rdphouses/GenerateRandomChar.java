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
    private final char[] char_password={'q','4','w','#','7','e','2','r','$',
        'r','t','y','&','5','u','i','o','?','0','p','a','*','9','s','!',
        'd','f','1','g','h','j','k','8','l','z','x','@','c','3','v','%','b',
        'n','6','_','m'};
    
    private final char[] char_username={'q','w','e','r','t','y','u','i','o'
    ,'p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
    Random rand = new Random();
    private static String pswrd_tobe_sent;
    private final int char_pass_size = char_password.length;
    int r;
    
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
    
 public void setPswrdTobeSent(String setpassword)
    {
        pswrd_tobe_sent = setpassword;
    }
    
    public String getPswrdTobeSent()
    {
        return pswrd_tobe_sent;
    }
    
    public String generatePassword()
    {
        int random;
        String random_password ="";

        int size = 10;
        for(r = 0; r < size; r++){
            random = rand.nextInt(char_pass_size);
            random_password = random_password+char_password[random];
        }
        
        setPswrdTobeSent(random_password);
        
        return passwordEncryption(random_password);
    }
    
    public String passwordEncryption(String password)
    {
        String encrypted_password = "";
        
        String tobe_reversed = strTobeReversed(password);
        String reverse_str = reverseString(tobe_reversed);
        
        char[] passwrd = reverse_str.toCharArray();
       
        int key = 8;
        for(char generate_char: passwrd){
            generate_char += key;
            encrypted_password += generate_char;
        }
        
        return encrypted_password;
    }
    
    public String passwordDecryption(String password)
    {
        
        char[] passwrd = password.toCharArray();
        String decrypted_password = "";
        String reverse_str;
        String orig_str;
        int key = 8;
        
        for(char generate_char: passwrd){
            generate_char -= key;
            decrypted_password += generate_char;
        }
        
        reverse_str = reverseString(decrypted_password);
        
        orig_str = toOriginalString(reverse_str);
        
        return orig_str;
    }
    
    private String reverseString(String str)
    {
        int size = str.length();
        String reverse_str = "";
        for(r = size - 1; r >= 0; r--)
            reverse_str += str.charAt(r);
        
        return reverse_str;
    }
    
    private String toOriginalString(String str)
    {
        String original_str = "";
        int size = str.length()/2;
        
        for(r = 0; r < size; r++){
            original_str = original_str + str.charAt(2*r+1);
        }
        
        return original_str;
    }
    
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
