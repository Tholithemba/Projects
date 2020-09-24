
package clinic_management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tholithemba
 */
public class UpdateDataFields {
    
    private String field_data;
    private static String heading_name;
    UserRegistration user_reg = new UserRegistration();
    GenerateRandomChar grc = new GenerateRandomChar();
   
    //set data to be updated
    public void setFieldData(String set_field_data){
        this.field_data = set_field_data;
    }
    
    //get date to be  updated
    public String getFieldData(){
        return field_data;
    }
    
    //set heading name
    public void setHeadingName(String set_head_name){
        heading_name = set_head_name;
    }
    
    //get heading name
    public String getHeadingName(){
        return heading_name;
    }
    
    //get data to be updated in the database
    public String getDataTobeUpdated(String query_statement){

        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            ps.setString(1, user_reg.getUsername());
            
            rs = ps.executeQuery();
            
            if(rs.next())
               setFieldData(rs.getString(getHeadingName()));
            else
                setFieldData(null);

        } catch (SQLException ex) {
            Logger.getLogger(UpdateDataFields.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return getFieldData();    
    }
    //end of getDataTobeUpdated method
    
    //add updated data to database
    public boolean addUpdatedData(String query_statement){
        
        PreparedStatement ps;
        boolean update_succeed = false;

        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            ps.setString(1, getFieldData());
            ps.setString(2, user_reg.getUsername());
            
            if(ps.executeUpdate() > 0)
                update_succeed = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDataFields.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return update_succeed;
    }
}
