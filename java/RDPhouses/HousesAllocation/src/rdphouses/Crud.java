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
public class Crud {
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean updateData(String query)
    {
        Registration reg = new Registration();
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setString(1, reg.getPassword());
            ps.setString(2, reg.getUsername());
            
            if(ps.executeUpdate() > 0)
                return true;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error occured Please contact the administrator");
        }
  
        return false;
    }
    
    public boolean readData(String query)
    {
        Registration reg = new Registration();
        CheckStatus cs = new CheckStatus();
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setString(2, reg.getUsername());
            rs = ps.executeQuery();
            if(rs.next())
            {
                String field_data = rs.getString("");
                
                cs.setStatus(query);
                return true;
            }
                
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error occured Please contact the administrator");
        }
  
        return false;
    }
   
    
    public boolean deleteData(String query)
    {
        Registration reg = new Registration();
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setString(1, reg.getUsername());
            
            if(ps.executeUpdate()> 0)
                return true;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "data was not deleted!!!");
        }
        
        return false;
    }
}
