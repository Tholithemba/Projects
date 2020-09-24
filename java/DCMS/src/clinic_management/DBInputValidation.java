
package clinic_management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tholithemba
 */
public class DBInputValidation {
    
    private static int user_id;
    UserRegistration user_reg = new UserRegistration();
    UpdateDataFields udf = new UpdateDataFields();
    PreparedStatement ps;
    ResultSet rs;
    
    //set user id
    public void setUserID(int set_userID){
        user_id = set_userID;
    }
    
    //get uder id
    public int getUserID(){
        return user_id;
    }   
    
    //this function checks if the email/practice number exist in 
    //the table and set user id if it exists
    public boolean checkUsername(String query_statement)
    {
        GenerateRandomChar grc = new GenerateRandomChar();
        boolean exist = false;
        
        try
        {
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            ps.setString(1, user_reg.getUsername());
             
            rs = ps.executeQuery();
             
            if(rs.next()){
                setUserID(rs.getInt(udf.getHeadingName()));
                grc.setPswrdTobeSent(grc.passwordDecryption(rs.getString("PASSWORD")));
                exist = true;
            }
        }
        catch(SQLException sq)
        {
            sq.getSQLState();
        }
         
        return exist;
    }
    //end of check_email function
    
    //this function checks if the Patient is uncharged 
    //using patient id
    public boolean checkPatientID(String query_statement)
    {
        Consultation c = new Consultation();
        boolean exist = false;
        
        try
        {
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            ps.setInt(1, c.getPatientID());
            ps.setString(2, "uncharged");
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                setUserID(rs.getInt(udf.getHeadingName()));
                exist = true;
            }
        }
        catch(SQLException sq)
        {
            sq.getSQLState();
        }
         
        return exist;
    }
}//end of checkPatientID mrthod
