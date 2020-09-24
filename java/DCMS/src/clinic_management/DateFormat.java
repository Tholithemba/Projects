
package clinic_management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tholithemba
 */
public class DateFormat {
    
    private static Date booked_date;
    //set all types of date format required in this project
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
    SimpleDateFormat simpl_date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat full_date = new SimpleDateFormat("dd-MMMM-yyyy");
    SimpleDateFormat day = new SimpleDateFormat("EEEE");
    
    //set date
    public void setDate(Date set_date){
        booked_date = set_date;
    }
    
    //get date
    public Date getDate(){
        return booked_date;
    }
    
    
    //set date format
    public void setDateFormat(String date_format){
        try {
            setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date_format));
        } catch (ParseException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    //format date to full text
    public String dateFormat(String set_date){
        
        Date d;
        String date_formated = "";
        try {
            d = simpl_date.parse(set_date);
            date_formated = full_date.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(CancelAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date_formated;
    }
    
    
    //date in day format
    public String dayFormat(String set_date){

        Date d;
        String date_formated = "";
        try {
            d = simpl_date.parse(set_date);
            date_formated = day.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(CancelAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date_formated;
    }
}
