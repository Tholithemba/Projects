/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            ps.setString(1, reg.getUsername());
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
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
