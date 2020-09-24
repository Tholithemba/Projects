package clinic_management;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tholithemba
 */
public class Connect2database 
{
    //connect to database  
    public static Connection getConnection()
    {
        Connection connection = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/testDB?characterEncoding=UTF-8&useSSL=false", "root", "Tholithemba");
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return connection;   
    }
}
