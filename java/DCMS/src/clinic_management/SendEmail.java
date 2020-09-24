/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic_management;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author tholithemba
 */
public class SendEmail 
{

    //send username and password to the user via email
    public boolean sendEmail()
    {
        UserRegistration user_reg = new UserRegistration();
        GenerateRandomChar g_rand_pswrd = new GenerateRandomChar();
        
        String email_to = user_reg.getUsername();
        String email_from = "myjavaprogrammingemail@gmail.com";
        String email_subject = "login details";
        String mypassword = "myjavaemail";
        String text = "Your username is "+user_reg.getUsername()
                + " and your password is "+g_rand_pswrd.getPswrdTobeSent();
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(email_from, mypassword);
            }
        });
        
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email_from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_to));
            message.setSubject(email_subject);
            message.setText(text);
            
            Transport.send(message);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "connection failure.......");
            return false;
        }
        
        JOptionPane.showMessageDialog(null, "Email and Username has been sent to your email");
        
        return true;
    }
    
}
