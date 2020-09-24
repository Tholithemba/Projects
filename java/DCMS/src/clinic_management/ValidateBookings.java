
package clinic_management;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tholithemba
 */
public class ValidateBookings {
    
    private String check_in;
    private String check_out;
    DateFormat date_format = new DateFormat();
    
    //set check in time
    public void setCheckin(String set_checkin){
        this.check_in = set_checkin;
    }
    
    //get check in time
    public String getCheckin(){
        return check_in;
    }
    
    //set checkout time
    public void setCheckout(String set_checkout){
        this.check_out = set_checkout;
    }
    
    //get checkout time
    public String getCheckout(){
        return check_out;
    }
    
    //check if user make saturday booking
    public boolean isSaturday(Date date){
        
        String check_weekend = date_format.day.format(date);
        boolean saturday = false;
        
        if(check_weekend.equals("Saturday"))
            saturday = true;
        
        return saturday;
    }
    
    //check if user mak sunday booking
    public boolean isSunday(Date date){
        
        String check_weekend = date_format.day.format(date);
        boolean sunday = false;
        
        if(check_weekend.equals("Sunday"))
            sunday = true;
        
        return sunday;
    }
    
    //validate saturday slots
    public boolean validSaturdayHours(){
        
        String saturday_firstinHour = "09:00";
        String saturday_lastoutHour = "13:00";
        int first_hourin = getCheckin().compareTo(saturday_firstinHour);
        int last_hourout = getCheckout().compareTo(saturday_lastoutHour);
        boolean valid_hours = false;
        
        if(first_hourin < 0 || last_hourout > 0)return valid_hours;
        
        valid_hours = true;
        
        return valid_hours;
    }
    
    //validate lunch slot
    public boolean validLunchSlot(){
        
        String lunch_start = "12:00";
        String lunch_end = "13:00";
        boolean valid_slot = false;
        int slotstart = getCheckin().compareTo(lunch_start);
        int slot_end = getCheckout().compareTo(lunch_end);
        
        if(slotstart == 0 || slot_end == 0)return valid_slot;
        
        if(slotstart < 0 && slot_end >= 0)return valid_slot;
        
        valid_slot = true;
        
        return valid_slot;
    }
    
    //check if the selected slot is booked
    public boolean isSlotBooked(ArrayList<String> slot_booked){
        
        boolean valid_booking = true;
        String booked_in;
        String booked_out;
        int r;
        for(r = 0; r < slot_booked.size()/2; r++){
            booked_in = slot_booked.get(2*r);
            booked_out = slot_booked.get(2*r + 1);
            valid_booking = isBookingValid(booked_in, booked_out);
            if(!valid_booking)return valid_booking;
        }
        
        return valid_booking;
    }
    
    //validate booking
    private boolean isBookingValid(String booked_in, String booked_out){
        
        boolean valid_booking = false;
        String checkin = getCheckin();
        String checkout = getCheckout();
        int bookedin;
        int bookedout;
        int notlessbookedin;
        int notlessbookedout;
        
        
        if(checkin.equals(booked_in)
           || checkout.equals(booked_out))
            return valid_booking;

        bookedin = checkin.compareTo(booked_in);
        bookedout = checkout.compareTo(booked_out);
        notlessbookedin = checkout.compareTo(booked_in);
        notlessbookedout = checkin.compareTo(booked_out);
        
        
        if((bookedin < 0 && bookedout < 0 && notlessbookedin > 0)
           || (bookedin > 0 && bookedout > 0 && notlessbookedout < 0))
            return valid_booking;
        
        if(bookedin < 0 && bookedout > 0)
            return valid_booking;
        
        if(bookedin > 0 && bookedout < 0)
            return valid_booking;
        
        valid_booking = true;
        
        return valid_booking;
    }
    //end of isBookingValid method
    
    //validate chosen day
    public boolean validChosenDay(Date date){
        
        String today = date_format.simpl_date.format(new java.util.Date());
        String chosen_date = date_format.simpl_date.format(date);
        boolean valid =true;
        int compare_date = chosen_date.compareTo(today);
        
        if(compare_date <= 0)
            valid = false;
        
        return valid;
    }
    
}