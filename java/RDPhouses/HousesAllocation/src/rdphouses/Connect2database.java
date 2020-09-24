/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tholithemba
 */
public class Connect2database {
    public static Connection getConnection()
    {
        Connection connection = null;
        String con_str;

        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con_str = "jdbc:mysql://localhost/housesallocation?characterEncoding=UTF-8&useSSL=false";
            connection = DriverManager.getConnection(con_str, "root", "Tholithemba");
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return connection;   
    }
}
