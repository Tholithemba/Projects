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
public class Registration {
    private static String firstname;
    private static String lastname;
    private static String funderType;
    private static String org_name;
    private static String contact_no;
    private static String email;
    private static String status;
    private static String treshold;
    private static String amountpayed;
    private static String addr1;
    private static String addr2;
    private static String addr3 = "";
    private static String postal_code;
    private static int admin_id;
    private static String username;
    private static String password;
    private static String gender;
    private static String ethinicity;
    private static String date_of_birth;
    private static String pref_communication_medium;
    private static String proof_of_income_file;
    private static String id_file;
    private static String id_number;
    
    public Registration(){
    }
    
    public Registration(int id){
        admin_id = id;
    }
    
    public void setAdminID(int adm_id){
        admin_id = adm_id;
    }
    
    public int getAdminID(){
        return admin_id;
    }

    public void setFirstname(String f_name){
        firstname = f_name;
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public void setLastname(String l_name){
        lastname = l_name;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public void setUsername(String uname)
    {
        username = uname;
    }
    
    public String getUsername()
    {
        return username;
    }
      public void setPassword(String pword)
    {
        password = pword;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setFundertype(String set_type){
        funderType = set_type;
    }
    
    public String getFundertype(){
        return funderType;
    }
    
    public void setOrganisationName(String orgName){
        org_name = orgName;
    }
    
    public String getOrganisationName(){
        return org_name;
    }
    
    public void setContactNo(String cont_no){
        contact_no = cont_no;
    }
    
    public String getContactNo(){
        return contact_no;
    }
    
    public void setEmail(String set_email){
        email = set_email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setStatus(String set_status){
        status = set_status;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setTreshold(String set_treshold){
        treshold = set_treshold;
    }
    
    public String getTreshold(){
        return treshold;
    }
 
    public void setAmountPayed(String set_amount){
        amountpayed = set_amount;
    }
    
    public String getAmountPayed(){
        return amountpayed;
    }
    
    public void setAddress1(String set_addr1){
        addr1 = set_addr1;
    }
    
    public String getAddress1(){
        return addr1;
    }
    
    public void setAddress2(String set_addr2){
        addr2 = set_addr2;
    }
    
    public String getAddress2(){
        return addr2;
    }
    
    public void setAddress3(String set_addr3){
        addr3 = set_addr3;
    }
    
    public String getAddress3(){
        return addr3;
    }
    
    public void setPostalCode(String p_code){
        postal_code = p_code;
    }
    
    public String getPostalCode(){
        return postal_code;
    }
    
    public void setGender(String set_gender){
        gender = set_gender;
    }
    
    public String getGender(){
        return gender;
    }
    
        public void setDOB(String dob){
        date_of_birth = dob;
    }
    
    public String getDOB(){
        return date_of_birth;
    }
    
    public void setEthinicity(String set_eth){
        ethinicity = set_eth;
    }
    
    public String getEthinicity(){
        return ethinicity;
    }
    
    public void setPrefCommMedium(String p_c_medium){
        pref_communication_medium = p_c_medium;
    }
    
    public String getPrefCommMedium(){
        return pref_communication_medium;
    }
    
    public void setProofOFIncomeFile(String pr_of_income_f){
        proof_of_income_file = pr_of_income_f;
    }
    
    public String getProofOFIncomeFile(){
        return proof_of_income_file;
    }
    
    public void setIDFile(String id_f){
        id_file = id_f;
    }
    
    public String getIDFile(){
        return id_file;
    }
    
    public void setIDNumber(String id_no){
        id_number = id_no;
    }
    
    public String getIDNumber(){
        return id_number;
    }
    
}
