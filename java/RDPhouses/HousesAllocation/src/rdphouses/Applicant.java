
package rdphouses;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author tholithemba
 */
public class Applicant extends javax.swing.JFrame {
  
    Registration reg = new Registration();
    Validation v = new Validation();
    GenerateRandomChar grc = new GenerateRandomChar();
    
    public Applicant() {
        initComponents();
        getCurrentDate();
    }
    
    
    private String getCurrentDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        
        return sdf.format(date);
    }
    
    private String filePath()
    {
       JFileChooser jfc = new JFileChooser();
       jfc.showOpenDialog(null);
       File f = jfc.getSelectedFile();
       String file_name = f.getAbsolutePath();
       return file_name;
    }
    
    private boolean validateInput(){
        boolean valid_input = true;
        
        if(reg.getFirstname().equals(""))
           return outputStatement(" firstname");
        
        if(reg.getLastname().equals(""))
           return outputStatement(" lastname");
        
        if(reg.getIDNumber().equals(""))
           return outputStatement(" ID number");

        if(!validateDate())
            return outputStatement(" or Select date of birth");
        
        if(reg.getGender().equals("Select"))
            return outputStatement(" or select gender");

        if(reg.getEthinicity().equals("Select"))
            return outputStatement(" or select ethinicity");
        
        if(reg.getContactNo().equals(""))
            return outputStatement(" Contact number");
        
        if(reg.getEmail().equals("")){
            return outputStatement(" email");
        }

        if(reg.getPrefCommMedium().equals("Select")){
            return outputStatement(" or select prefered communication medium");
        }

        if(reg.getAddress1().equals(""))
            return outputStatement(" address 1");

        if(reg.getAddress2().equals(""))
           return outputStatement(" address 2");

        if(reg.getPostalCode().equals(""))
           return outputStatement(" postal code");
        
        return valid_input;
    }

    private boolean emailUniqueness(){
        boolean exist;
        String query_statement = "select* from APPLICANT where applicant_email=?";
        exist = v.checkUniqueness(query_statement, reg.getEmail());
        
        if(exist){
           outputStatement("ed email already exist"); 
           return true;
        }
        return exist;
    }
    
    private boolean contactUniqueness(){
        boolean exist;
        String query_statement = "select* from APPLICANT where applicant_cell_no=?";
        exist = v.checkUniqueness(query_statement, reg.getContactNo());
        if(exist){
           outputStatement("ed contact number already exist");
           return true;
        }
        return exist;
    }
    
    private boolean IDUniqueness(){
        boolean exist;
        String query_statement = "select* from APPLICANT where applicant_id_no=?";
        exist = v.checkUniqueness(query_statement, reg.getIDNumber());
        if(exist){
           outputStatement("ed ID number name already exist");
           return true;
        }
        return exist;
    }
   
    private boolean UsernameUniqueness(){
        boolean exist;
        String query_statement = "select* from APPLICANT where applicant_username=?";
        exist = v.checkUniqueness(query_statement, reg.getUsername());
        if(exist){
            outputStatement("ed username name already exist");
            return true;
        }
        return exist;
    }
    
    private boolean outputStatement(String name){
        warning.setText("enter"+name);
        clearCells();
        return false;
    }
    
    private void uploadeFiles(){
        if(checkUploads())
            reg.setStatus("Red");
        else
            reg.setStatus("Green");      
    }
    
    private boolean checkUploads(){
        return reg.getProofOFIncomeFile().equals("") || reg.getIDFile().equals("");
    }
    
    private void createApplicant(){
        PreparedStatement ps;
        AdminHome admin = new AdminHome();
        String query_statement = "insert into APPLICANT(applicant_fname,applicant_lname,applicant_id_no,applicant_DOB,"
                + "applicant_gender,applicant_ethinicity,applicant_cell_no,applicant_email,applicant_username,"
                + "applicant_password,applicant_status,applicant_comm_medium,"
                + "applicant_addr1,applicant_addr2,applicant_postal_code,Admin_id)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        int postal_code = Integer.parseInt(reg.getPostalCode());
        InputStream input_stream;
        
        try
        {
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, reg.getFirstname());
            ps.setString(2, reg.getLastname());
            ps.setString(3, reg.getIDNumber());
            ps.setString(4, reg.getDOB());
            ps.setString(5, reg.getGender());
            ps.setString(6, reg.getEthinicity());
            ps.setString(7, reg.getContactNo());
            ps.setString(8, reg.getEmail());
            ps.setString(9, reg.getUsername());
            ps.setString(10, reg.getPassword());
            ps.setString(11, reg.getStatus());
            ps.setString(12, reg.getPrefCommMedium());
            
            input_stream = new FileInputStream(new File(reg.getProofOFIncomeFile()));
            ps.setBlob(13, input_stream);
            input_stream = new FileInputStream(new File(reg.getIDFile()));
            ps.setBlob(14, input_stream);
            ps.setString(13, reg.getAddress1());
            ps.setString(14, reg.getAddress2());
            ps.setInt(15, postal_code);
            ps.setInt(16, admin.getAdminID());
            
            if(ps.executeUpdate() > 0)
            {
                warning.setForeground(Color.green);
                warning.setText("data captured successfully....");
                clearCells();
            }
        }
        catch(SQLException | FileNotFoundException ex)
        {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean allDocumentsUploaded()
    {
        if((reg.getProofOFIncomeFile().equals("")))
            return false;
        else if(reg.getIDFile().equals(""))
            return false;
        else  
            return true;
    }
    
    
    private void createApplication()
    {
        if(!allDocumentsUploaded())return;
        
        AdminHome admin = new AdminHome();
        RequiredFields applicant_id = new RequiredFields();
        PreparedStatement ps;
        double requested_amount = 150000;
        double balance = 0;
        setID();
        String query = "insert into APPLICATION"
                +" (application_status,application_amount,application_balance,"
                +"application_date,Admin_id,applicant_id) values (?,?,?,?,?,?)";
        try
        {
            ps = Connect2database.getConnection().prepareStatement(query);
            
            ps.setString(1,"unfunded");
            ps.setDouble(2, requested_amount);
            ps.setDouble(3,balance);
            ps.setString(4, getCurrentDate());
            ps.setInt(5, admin.getAdminID());
            ps.setInt(6, applicant_id.getID());
            
            if(ps.executeUpdate() > 0)
            {
                warning.setForeground(Color.green);
                warning.setText("Application was created successsfully....");
                clearCells();               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setID()
    {
        RequiredFields set_id = new RequiredFields();
        Registration reg = new Registration();
        String query = "select id from APPLICANT where applicant_username=?";
        
        set_id.setFieldName("id");
        set_id.setRFUsername(reg.getUsername());
        set_id.setUserID(query);
    }
    
    private void loginDetails(){
        JOptionPane.showMessageDialog(null, "Username: "+reg.getUsername()+
        " "+"Passord: "+reg.getPassword());
    }
    
    private void clearCells()
    {
        applicant_firstname.setText("");
        applicant_lastname.setText("");
        applicant_IDno.setText("");
        applicant_contact.setText("");
        applicant_email.setText("");
        income_filepath.setText("");
        id_filepath.setText("");
        applicant_addr1.setText("");
        applicant_addr2.setText("");
        applicant_postalcode.setText("");
    }
    
    private void setValues()
    { 
        reg.setFirstname(applicant_firstname.getText());
        reg.setLastname(applicant_lastname.getText());
        reg.setIDNumber(applicant_IDno.getText());
        reg.setGender((String)select_gender.getSelectedItem());
        reg.setEthinicity((String)select_ethinicity.getSelectedItem());
        reg.setContactNo(applicant_contact.getText());
        reg.setEmail(applicant_email.getText());
        reg.setUsername(grc.generateUsername());
        reg.setPassword(grc.generatePassword());
        reg.setProofOFIncomeFile(income_filepath.getText());
        reg.setIDFile(id_filepath.getText());
        reg.setPrefCommMedium((String)comm_medium_pref.getSelectedItem());
        reg.setAddress1(applicant_addr1.getText());
        reg.setAddress2(applicant_addr2.getText());
        reg.setPostalCode(applicant_postalcode.getText());
        uploadeFiles();
    }
    
    private boolean validateDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(applicant_DOB.getDate() == null)return false;
        String str_date = sdf.format(applicant_DOB.getDate());
        reg.setDOB(str_date);
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        label16 = new java.awt.Label();
        label17 = new java.awt.Label();
        label19 = new java.awt.Label();
        applicant_addr2 = new java.awt.TextField();
        applicant_addr1 = new java.awt.TextField();
        applicant_postalcode = new java.awt.TextField();
        label13 = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        label6 = new java.awt.Label();
        label3 = new java.awt.Label();
        label5 = new java.awt.Label();
        label1 = new java.awt.Label();
        applicant_lastname = new java.awt.TextField();
        applicant_IDno = new java.awt.TextField();
        applicant_firstname = new java.awt.TextField();
        label4 = new java.awt.Label();
        label18 = new java.awt.Label();
        label20 = new java.awt.Label();
        select_gender = new javax.swing.JComboBox<>();
        select_ethinicity = new javax.swing.JComboBox<>();
        applicant_DOB = new com.toedter.calendar.JDateChooser();
        label8 = new java.awt.Label();
        jPanel5 = new javax.swing.JPanel();
        label7 = new java.awt.Label();
        applicant_contact = new java.awt.TextField();
        label9 = new java.awt.Label();
        applicant_email = new java.awt.TextField();
        label12 = new java.awt.Label();
        label21 = new java.awt.Label();
        comm_medium_pref = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        label10 = new java.awt.Label();
        label11 = new java.awt.Label();
        label14 = new java.awt.Label();
        browseproof = new java.awt.Button();
        browseID = new java.awt.Button();
        income_filepath = new java.awt.TextField();
        id_filepath = new java.awt.TextField();
        alreadyUserApplicant = new java.awt.Label();
        ApplicantBackToLogin = new javax.swing.JLabel();
        closeApplicant = new javax.swing.JLabel();
        warning = new java.awt.Label();
        RegisterApplicant = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(53, 66, 74));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 3));
        jPanel1.setForeground(java.awt.Color.white);
        jPanel1.setName("browse"); // NOI18N

        jPanel3.setBackground(new java.awt.Color(53, 66, 74));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 2));

        label16.setBackground(new java.awt.Color(53, 66, 74));
        label16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label16.setForeground(java.awt.Color.white);
        label16.setText("Street address");

        label17.setBackground(new java.awt.Color(53, 66, 74));
        label17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label17.setForeground(java.awt.Color.white);
        label17.setText("City/Town");

        label19.setBackground(new java.awt.Color(53, 66, 74));
        label19.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label19.setForeground(java.awt.Color.white);
        label19.setText("Postal code");

        label13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label13.setForeground(java.awt.Color.white);
        label13.setText("Address");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(applicant_addr2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(applicant_addr1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(applicant_postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(applicant_postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applicant_addr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(applicant_addr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(53, 66, 74));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 2));

        label6.setBackground(new java.awt.Color(53, 66, 74));
        label6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label6.setForeground(java.awt.Color.white);
        label6.setText("ID number");

        label3.setBackground(new java.awt.Color(53, 66, 74));
        label3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label3.setForeground(java.awt.Color.white);
        label3.setText("Last name");

        label5.setBackground(new java.awt.Color(53, 66, 74));
        label5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label5.setForeground(java.awt.Color.white);
        label5.setText("First name(s)");

        label1.setBackground(new java.awt.Color(53, 66, 74));
        label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label1.setForeground(java.awt.Color.white);
        label1.setText("Date of Birth");

        label4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label4.setForeground(java.awt.Color.white);
        label4.setText("Personal Information");

        label18.setBackground(new java.awt.Color(53, 66, 74));
        label18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label18.setForeground(java.awt.Color.white);
        label18.setText("Gender");

        label20.setBackground(new java.awt.Color(53, 66, 74));
        label20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label20.setForeground(java.awt.Color.white);
        label20.setText("Ethinicity");

        select_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Male", "Female" }));

        select_ethinicity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Black", "White", "Coloured" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(applicant_lastname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applicant_IDno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(applicant_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(select_ethinicity, 0, 152, Short.MAX_VALUE)
                            .addComponent(applicant_DOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applicant_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applicant_IDno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(applicant_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applicant_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(select_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select_ethinicity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        label8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label8.setForeground(java.awt.Color.white);
        label8.setText("Applicant");

        jPanel5.setBackground(new java.awt.Color(53, 66, 74));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 2));

        label7.setBackground(new java.awt.Color(53, 66, 74));
        label7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label7.setForeground(java.awt.Color.white);
        label7.setText("Contact No.");

        label9.setBackground(new java.awt.Color(53, 66, 74));
        label9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label9.setForeground(java.awt.Color.white);
        label9.setText("Email");

        label12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label12.setForeground(java.awt.Color.white);
        label12.setText("Contact Details");

        label21.setBackground(new java.awt.Color(53, 66, 74));
        label21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label21.setForeground(java.awt.Color.white);
        label21.setText("Communication medium");

        comm_medium_pref.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "sms", "email" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comm_medium_pref, 0, 156, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(applicant_email, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(applicant_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comm_medium_pref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applicant_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applicant_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(53, 66, 74));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 2));

        label10.setBackground(new java.awt.Color(53, 66, 74));
        label10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label10.setForeground(java.awt.Color.white);
        label10.setText("Proof of Income");

        label11.setBackground(new java.awt.Color(53, 66, 74));
        label11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label11.setForeground(java.awt.Color.white);
        label11.setText("ID");

        label14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label14.setForeground(java.awt.Color.white);
        label14.setText("Uploads");

        browseproof.setBackground(new java.awt.Color(53, 66, 74));
        browseproof.setForeground(java.awt.Color.white);
        browseproof.setLabel("browse");
        browseproof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseproofMouseClicked(evt);
            }
        });

        browseID.setBackground(new java.awt.Color(53, 66, 74));
        browseID.setForeground(java.awt.Color.white);
        browseID.setLabel("browse");
        browseID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseIDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browseproof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(income_filepath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_filepath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(income_filepath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseproof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_filepath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(browseID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        alreadyUserApplicant.setForeground(java.awt.Color.green);
        alreadyUserApplicant.setText("Already a user?");
        alreadyUserApplicant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alreadyUserApplicantMouseClicked(evt);
            }
        });

        ApplicantBackToLogin.setBackground(new java.awt.Color(53, 66, 74));
        ApplicantBackToLogin.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        ApplicantBackToLogin.setForeground(java.awt.Color.white);
        ApplicantBackToLogin.setText("Back");
        ApplicantBackToLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        ApplicantBackToLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ApplicantBackToLoginMouseClicked(evt);
            }
        });

        closeApplicant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rdphouses/close.png"))); // NOI18N
        closeApplicant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeApplicantMouseClicked(evt);
            }
        });

        warning.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        warning.setForeground(java.awt.Color.red);

        RegisterApplicant.setBackground(java.awt.Color.blue);
        RegisterApplicant.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        RegisterApplicant.setForeground(java.awt.Color.white);
        RegisterApplicant.setText("Register");
        RegisterApplicant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterApplicantMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(warning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(alreadyUserApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RegisterApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ApplicantBackToLogin)
                .addGap(275, 275, 275)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ApplicantBackToLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(RegisterApplicant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alreadyUserApplicant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeApplicantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeApplicantMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeApplicantMouseClicked

    private void ApplicantBackToLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApplicantBackToLoginMouseClicked
       new Login().setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_ApplicantBackToLoginMouseClicked

    private void alreadyUserApplicantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alreadyUserApplicantMouseClicked
       new Login().setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_alreadyUserApplicantMouseClicked

    private void browseproofMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseproofMouseClicked
        income_filepath.setText(filePath());
    }//GEN-LAST:event_browseproofMouseClicked

    private void browseIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseIDMouseClicked
        id_filepath.setText(filePath());
    }//GEN-LAST:event_browseIDMouseClicked

    private void RegisterApplicantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterApplicantMouseClicked
        boolean valid_input;
        boolean input_exist;
        
        setValues();
        
        valid_input = validateInput();
        if(!valid_input)return;
        
        input_exist = IDUniqueness();
        if(input_exist)return;
        
        input_exist = contactUniqueness();
        if(input_exist)return;
        
        input_exist = emailUniqueness();
        if(input_exist)return;
        
        input_exist = UsernameUniqueness();
        if(input_exist)return;
        
        createApplicant();
        createApplication();
        
        loginDetails();
    }//GEN-LAST:event_RegisterApplicantMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Applicant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Applicant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Applicant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Applicant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Applicant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ApplicantBackToLogin;
    private javax.swing.JButton RegisterApplicant;
    private java.awt.Label alreadyUserApplicant;
    private com.toedter.calendar.JDateChooser applicant_DOB;
    private java.awt.TextField applicant_IDno;
    private java.awt.TextField applicant_addr1;
    private java.awt.TextField applicant_addr2;
    private java.awt.TextField applicant_contact;
    private java.awt.TextField applicant_email;
    private java.awt.TextField applicant_firstname;
    private java.awt.TextField applicant_lastname;
    private java.awt.TextField applicant_postalcode;
    private java.awt.Button browseID;
    private java.awt.Button browseproof;
    private javax.swing.JLabel closeApplicant;
    private javax.swing.JComboBox<String> comm_medium_pref;
    private java.awt.TextField id_filepath;
    private java.awt.TextField income_filepath;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label14;
    private java.awt.Label label16;
    private java.awt.Label label17;
    private java.awt.Label label18;
    private java.awt.Label label19;
    private java.awt.Label label20;
    private java.awt.Label label21;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private javax.swing.JComboBox<String> select_ethinicity;
    private javax.swing.JComboBox<String> select_gender;
    private java.awt.Label warning;
    // End of variables declaration//GEN-END:variables
}
