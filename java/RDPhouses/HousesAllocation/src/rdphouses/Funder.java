/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tholithemba
 */
public class Funder extends javax.swing.JFrame {
   
    Registration reg = new Registration();
    Validation v = new Validation();
    
    /**
     * Creates new form Funder
     */
    public Funder() {
        initComponents();
    }
    
    private boolean validateInput()
    {
        boolean valid_input = true;
        
        if(reg.getFirstname().equals(""))
           return outputStatement(" firstname");
        
        if(reg.getLastname().equals(""))
           return outputStatement(" lastname");
        
        if(reg.getFundertype().equals(""))
           return outputStatement(" funder type");

        if(reg.getOrganisationName().equals(""))
            return outputStatement(" Organisation name");

        if(reg.getContactNo().equals(""))
            return outputStatement(" Contact number");

        if(reg.getEmail().equals("")){
            return outputStatement(" email");
        }

        if(reg.getStatus().equals(""))
            return outputStatement(" status");
        
        if(reg.getTreshold().equals("")){
            return outputStatement(" treshold");
        }

        if(reg.getAmountPayed().equals("")){
            return outputStatement(" amount payed");
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
        String query_statement = "select* from FUNDER where funder_email=?";
        exist = v.checkUniqueness(query_statement, reg.getEmail());
        
        if(exist){
            outputStatement("ed email already exist");
            return true;
        }
        
        return exist;
    }
    
    private boolean contactUniqueness(){
        boolean exist;
        String query_statement = "select* from FUNDER where funder_cell_no=?";
        exist = v.checkUniqueness(query_statement, reg.getContactNo());
        if(exist){
            outputStatement("ed contact number already exist");
            return true;
        }
        
        return exist;
    }
    
    private boolean OrganisationUniqueness(){
        boolean exist;
        String query_statement = "select* from FUNDER where funder_organisation_name=?";
        exist = v.checkUniqueness(query_statement, reg.getOrganisationName());
        if(exist){
            outputStatement("ed organisation name already exist");
            return true;
        }
        
        return exist;
    }
    
    private boolean outputStatement(String name)
    {
        warning_msg.setText("enter"+name);
        clearCells();
        return false;
    }
    
    private void clearCells()
    {
        funder_firstname.setText("");
        funder_lastname.setText("");
        funder_type.setText("");
        organiz_name.setText("");
        funder_contact.setText("");
        funder_email.setText("");
        funder_status.setText("");
        funder_treshold.setText("");
        amount_payed.setText("");
        funder_addr1.setText("");
        funder_addr2.setText("");
        funder_addr3.setText("");
        funder_postalcode.setText("");
    }
    
    private void AddTodatabase()
    {
        PreparedStatement ps;
        String query_statement = "insert into FUNDER(funder_fname,funder_lname,funder_cell_no,funder_email,funder_type,"
                +"funder_organisation_name,funder_treshold,funder_balance,funder_status,funder_addr1,funder_addr2,"
                + "funder_addr3,funder_postal_code,Admin_id)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int pos_code  = Integer.parseInt(reg.getPostalCode());
        Double  balance = new Double(reg.getAmountPayed());
        Double  Treshold = new Double(reg.getTreshold());
        
        try
        {
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, reg.getFirstname());
            ps.setString(2, reg.getLastname());
            ps.setString(3, reg.getContactNo());
            ps.setString(4, reg.getEmail());
            ps.setString(5, reg.getFundertype());
            ps.setString(6, reg.getOrganisationName());
            ps.setDouble(7, Treshold);
            ps.setDouble(8, balance);
            ps.setString(9, reg.getStatus());
            ps.setString(10, reg.getAddress1());
            ps.setString(11, reg.getAddress2());
            ps.setString(12, reg.getAddress3());
            ps.setInt(13, pos_code);
            ps.setInt(14, 2);
            
            if(ps.executeUpdate() > 0)
            {
                warning_msg.setForeground(Color.green);
                warning_msg.setText("data captured successfully...");
                clearCells();
            }
             
        }
        catch(SQLException sq)
        {
            sq.getStackTrace();
        }
    }
    
    private void setValues()
    { 
        reg.setFirstname(funder_firstname.getText());
        reg.setLastname(funder_lastname.getText());
        reg.setFundertype(funder_type.getText());
        reg.setOrganisationName(organiz_name.getText());
        reg.setContactNo(funder_contact.getText());
        reg.setEmail(funder_email.getText());
        reg.setStatus(funder_status.getText());
        reg.setTreshold(funder_treshold.getText());
        reg.setAmountPayed(amount_payed.getText());
        reg.setAddress1(funder_addr1.getText());
        reg.setAddress2(funder_addr2.getText());
        reg.setAddress3(funder_addr3.getText());
        reg.setPostalCode(funder_postalcode.getText());
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
        label18 = new java.awt.Label();
        label19 = new java.awt.Label();
        funder_addr2 = new java.awt.TextField();
        funder_addr1 = new java.awt.TextField();
        funder_addr3 = new java.awt.TextField();
        funder_postalcode = new java.awt.TextField();
        label13 = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        label10 = new java.awt.Label();
        label9 = new java.awt.Label();
        label6 = new java.awt.Label();
        label3 = new java.awt.Label();
        label5 = new java.awt.Label();
        label7 = new java.awt.Label();
        label1 = new java.awt.Label();
        funder_firstname = new java.awt.TextField();
        funder_lastname = new java.awt.TextField();
        funder_type = new java.awt.TextField();
        organiz_name = new java.awt.TextField();
        funder_contact = new java.awt.TextField();
        funder_email = new java.awt.TextField();
        label12 = new java.awt.Label();
        funder_status = new java.awt.TextField();
        label11 = new java.awt.Label();
        funder_treshold = new java.awt.TextField();
        label4 = new java.awt.Label();
        amount_payed = new java.awt.TextField();
        label8 = new java.awt.Label();
        closeFunder = new javax.swing.JLabel();
        FunderBackToAdminHome = new javax.swing.JLabel();
        warning_msg = new java.awt.Label();
        FunderRegistration = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(53, 66, 74));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 3));

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

        label18.setBackground(new java.awt.Color(53, 66, 74));
        label18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label18.setForeground(java.awt.Color.white);
        label18.setText("Building");

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
                            .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(funder_addr2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(funder_addr1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(funder_addr3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(funder_postalcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(funder_addr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(funder_addr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(funder_addr3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(funder_postalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );

        jPanel4.setBackground(new java.awt.Color(53, 66, 74));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 2));

        label10.setBackground(new java.awt.Color(53, 66, 74));
        label10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label10.setForeground(java.awt.Color.white);
        label10.setText("Status");

        label9.setBackground(new java.awt.Color(53, 66, 74));
        label9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label9.setForeground(java.awt.Color.white);
        label9.setText("Email");

        label6.setBackground(new java.awt.Color(53, 66, 74));
        label6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label6.setForeground(java.awt.Color.white);
        label6.setText("Funder type");

        label3.setBackground(new java.awt.Color(53, 66, 74));
        label3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label3.setForeground(java.awt.Color.white);
        label3.setText("Last name");

        label5.setBackground(new java.awt.Color(53, 66, 74));
        label5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label5.setForeground(java.awt.Color.white);
        label5.setText("First name(s)");

        label7.setBackground(new java.awt.Color(53, 66, 74));
        label7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label7.setForeground(java.awt.Color.white);
        label7.setText("Contact No.");

        label1.setBackground(new java.awt.Color(53, 66, 74));
        label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label1.setForeground(java.awt.Color.white);
        label1.setText("Organisation name");

        label12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label12.setForeground(java.awt.Color.white);
        label12.setText("Details");

        label11.setBackground(new java.awt.Color(53, 66, 74));
        label11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label11.setForeground(java.awt.Color.white);
        label11.setText("Treshold");

        label4.setBackground(new java.awt.Color(53, 66, 74));
        label4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label4.setForeground(java.awt.Color.white);
        label4.setText("Amount payed");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(funder_treshold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(funder_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(amount_payed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(funder_status, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(funder_email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(funder_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(funder_contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(organiz_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(funder_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(funder_firstname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(funder_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(funder_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(organiz_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(funder_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(funder_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(funder_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(funder_treshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amount_payed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        label8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label8.setForeground(java.awt.Color.white);
        label8.setText("Funder");

        closeFunder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rdphouses/close.png"))); // NOI18N
        closeFunder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeFunderMouseClicked(evt);
            }
        });

        FunderBackToAdminHome.setBackground(new java.awt.Color(53, 66, 74));
        FunderBackToAdminHome.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        FunderBackToAdminHome.setForeground(java.awt.Color.white);
        FunderBackToAdminHome.setText("Back");
        FunderBackToAdminHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        FunderBackToAdminHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FunderBackToAdminHomeMouseClicked(evt);
            }
        });

        warning_msg.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        warning_msg.setForeground(java.awt.Color.red);

        FunderRegistration.setBackground(java.awt.Color.blue);
        FunderRegistration.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        FunderRegistration.setForeground(java.awt.Color.white);
        FunderRegistration.setText("Register");
        FunderRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FunderRegistrationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FunderBackToAdminHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(307, 307, 307)
                .addComponent(closeFunder, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(warning_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(FunderRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(FunderBackToAdminHome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeFunder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(warning_msg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FunderRegistration)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeFunderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeFunderMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeFunderMouseClicked

    private void FunderBackToAdminHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FunderBackToAdminHomeMouseClicked
        new AdminHome().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_FunderBackToAdminHomeMouseClicked

    private void FunderRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FunderRegistrationMouseClicked
        
        boolean valid_input;
        
        setValues();
        
        valid_input = validateInput();
        if(!valid_input)return;
        
        valid_input = emailUniqueness();
        if(valid_input)return;
        
        valid_input = contactUniqueness();
        if(valid_input)return;
        
        valid_input = OrganisationUniqueness();
        if(valid_input )return;
        
        AddTodatabase();
    }//GEN-LAST:event_FunderRegistrationMouseClicked

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
            java.util.logging.Logger.getLogger(Funder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Funder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Funder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Funder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Funder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FunderBackToAdminHome;
    private javax.swing.JButton FunderRegistration;
    private java.awt.TextField amount_payed;
    private javax.swing.JLabel closeFunder;
    private java.awt.TextField funder_addr1;
    private java.awt.TextField funder_addr2;
    private java.awt.TextField funder_addr3;
    private java.awt.TextField funder_contact;
    private java.awt.TextField funder_email;
    private java.awt.TextField funder_firstname;
    private java.awt.TextField funder_lastname;
    private java.awt.TextField funder_postalcode;
    private java.awt.TextField funder_status;
    private java.awt.TextField funder_treshold;
    private java.awt.TextField funder_type;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label16;
    private java.awt.Label label17;
    private java.awt.Label label18;
    private java.awt.Label label19;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.TextField organiz_name;
    private java.awt.Label warning_msg;
    // End of variables declaration//GEN-END:variables
}
