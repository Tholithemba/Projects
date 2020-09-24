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
public class PatientRegistration {
    
        private String dob;
        private static String id_no;
        private static String gender;
        private static String mob_number;
        private static String occupation;
        
        private static String line_1;
        private static String line_2 ;
        private static String line_3;
        private static String postal_code;
        private static String city ;
        private static String province;
    
        //set patient id
        public void setPatientID(String set_id_no){
            id_no = set_id_no;
        }
        
        //get Patient id
        public String getPatientID(){
            return id_no;
        }

        //set paient date of birth
        public void setPatientDOB(String set_dob){
            this.dob = set_dob;
        }
        
        //get patient date of birth
        public String getPatientDOB(){
            return dob;
        }

        //set patient gender
        public void setPatientGender(String set_gender){
            gender = set_gender;
        }
        
        //get patient gender
        public String getPatientGender(){
            return gender;
        }

        //set patient mobile number
        public void setPatientMobileNo(String set_mobile_no){
            mob_number = set_mobile_no;
        }
        
        //get patient mobile number
        public String getPatientMobileNo(){
            return mob_number;
        }

        //set patient occupation
        public void setPatientOccupation(String set_occup){
            occupation = set_occup;
        }
        
        //get patient occupation
        public String getPatientOccupation(){
            return occupation;
        }

        //set patient address 1
        public void setPatientAddress1(String set_addr1){
            line_1 = set_addr1;
        }
        
        //get patient address 1
        public String getPatientAddress1(){
            return line_1;
        }

        //set patient address 2
        public void setPatientAddress2(String set_addr2){
            line_2 = set_addr2;
        }
        
        //get patient address 2
        public String getPatientAddress2(){
            return line_2;
        }

        //set patient address 3
        public void setPatientAddress3(String set_addr3){
           line_3 = set_addr3;
        }
        
        //get patient address 3
        public String getPatientAddress3(){
            return line_3;
        }

        //set patient postal code
        public void setPatientPostalCode(String set_pcode){
            postal_code = set_pcode;
        }
        
        //get patient postal code
        public String getPatientPostalCode(){
            return postal_code;
        }

        //set patient city
        public void setPatientCity(String set_city){
            city = set_city;
        }
        
        //get patient city
        public String getPatientCity(){
            return city;
        }

        //set patient province
        public void setPatientProvince(String set_province){
           province = set_province;
        }
        
        //get patient province
        public String getPatientProvince(){
            return province;
        }
}
