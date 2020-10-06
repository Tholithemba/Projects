/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tholithemba
 */
public class Validation {

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
        PreparedStatement ps;
        ResultSet rs;
        Registration reg = new Registration();
        RequiredFields rf = new RequiredFields();
        GenerateRandomChar grc = new GenerateRandomChar();
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, reg.getUsername());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                String password = grc.passwordDecryption(rs.getString(rf.getFieldName()));
                grc.setPswrdTobeSent(password);
            }
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "error occured please contact the administrator");
        }
        
        return comparePassword();
    }
    
    private boolean comparePassword()
    {
        Registration reg = new Registration();
        GenerateRandomChar grc = new GenerateRandomChar();
        
        if(reg.getPassword().equals(grc.getPswrdTobeSent()))
            return true;
        else
            return false;
    }
}
