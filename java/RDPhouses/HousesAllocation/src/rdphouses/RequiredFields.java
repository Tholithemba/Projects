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
public class RequiredFields {

  private String field_name;
    private String field_data;
    private String username;
    
    public void setFieldName(String set_field){
        this.field_name = set_field;
    }
    
    public String getFieldName(){
        return field_name;
    }
    
    public void setFieldData(String set_field_data){
        this.field_data = set_field_data;
    }
    
    public String getFieldData(){
        return field_data;
    }
    
    public void setRFUsername(String set_username){
        this.username = set_username;
    }
    
    public String getRFUsername(){
        return username;
    }
    
    public String getDataTobeUpdated(String query_statement){

        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            ps.setString(1, getRFUsername());
            
            rs = ps.executeQuery();
            
            if(rs.next())
               setFieldData(rs.getString(getFieldName()));

        } catch (SQLException ex) {
            Logger.getLogger(RequiredFields.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return getFieldData();    
    }
    
    public void addUpdatedData(String query_statement){
        
        PreparedStatement ps;
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, getFieldData());
            ps.setString(2, getRFUsername());
            
            if(ps.executeUpdate() > 0)
                JOptionPane.showMessageDialog(null, "data updated successfully...");
            
        } catch (SQLException ex) {
          Logger.getLogger(RequiredFields.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}
