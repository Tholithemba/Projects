/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic_management;

/**
 *
 * @author tholithemba
 */
public class TableHeadingList {
    
    //patient heading names correspond to patient table in the database
    private final String patient[] = 
    { "NAME","SURNAME","DOB","ID_OR_PASSORT_NUMBER ","GENDER",
                      "MOBILE_NUMBER","EMAIL","PASSWORD","OCCUPATION"};
    
    //doctor heading names correspond to doctor table in the database
    private final String doctor[] =
    {"NAME","SURNAME","PRACTICE_NUMBER","SPECIALIZATION","PASSWORD"};
    
    //receptionist heading names correspond to receptionist table in the database
    private final String receptionist[] =
    {"NAME","SURNAME","EMAIL","PASSWORD"};
    
    //check selected patient heading name if it exist in patient the table
    public boolean checkInPatient(String heading_name){
        
        int r;
        int array_size = patient.length;
        for(r = 0; r < array_size; r++)
            if(patient[r].equals(heading_name))
                return true;
        
        return false;
    }
    
    //check selected doctor heading name if it exist in doctor's the table
    public boolean checkInDoctor(String heading_name){
        
        int r;
        int array_size = doctor.length;
        for(r = 0; r < array_size; r++)
            if(doctor[r].equals(heading_name))
                return true;
        
        return false;
    }
    
    //check selected receptionist heading name if it exist in receptionist the table
    public boolean checkInReceptionist(String heading_name){
        
        int r;
        int array_size = receptionist.length;
        for(r = 0; r < array_size; r++)
            if(receptionist[r].equals(heading_name))
                return true;
        
        return false;
    }
}
