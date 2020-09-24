/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

/**
 *
 * @author tholithemba
 */
public class TableHeadings {
    private final String[] funder =
    {"funder_fname","funder_lname","funder_cell_no","funder_email","funder_type",
        "funder_organisation_name"};
    
    private final String applicant[] = 
    {"applicant_fname","applicant_lname","applicant_id_no","applicant_DOB",
    "applicant_cell_no","applicant_email","applicant_username","applicant_password"};
    
    private final String admin[] = {"firstname","lastname","admin_username",
    "admin_password"};
    
    public boolean checkApplicantTable(String heading_name){
        
        int r;
        int array_size = applicant.length;
        for(r = 0; r < array_size; r++)
            if(applicant[r].equals(heading_name))
                return true;
        
        return false;
    }
    
    public boolean checkFunderTable(String heading_name){
        
        int r;
        int array_size = funder.length;
        for(r = 0; r < array_size; r++)
            if(funder[r].equals(heading_name))
                return true;
        
        return false;
    }
    
    public boolean checkAdministratorTable(String heading_name){
        
        int r;
        int array_size = admin.length;
        for(r = 0; r < array_size; r++)
            if(admin[r].equals(heading_name))
                return true;
        
        return false;
    }
}
