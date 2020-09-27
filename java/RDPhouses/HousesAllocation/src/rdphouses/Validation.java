/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tholithemba
 */
public class Validation {

    Registration reg = new Registration();
    
    public boolean checkUniqueness(String query_statement, String check_value)
    {
        boolean exist = false;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, check_value);
            rs = ps.executeQuery();
            
            if(rs.next())return true;
        }
        catch (SQLException ex)
        {
            ex.getSQLState();
        }
        
        return exist;
    }
    
    public boolean EmailPasswordVerification(String query_statement)
    {
        boolean exist = false;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, reg.getUsername());
            ps.setString(2, reg.getPassword());
            rs = ps.executeQuery();
            
            if(rs.next())return true;
        }
        catch (SQLException ex)
        {
            ex.getSQLState();
        }
        
        return exist;
    }
}
