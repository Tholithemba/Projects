/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic_management;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author tholithemba
 */
public class AllUpdates extends javax.swing.JFrame {

    private String user_type;
    private String emailORpracticeNo;
    UpdateDataFields udf = new UpdateDataFields();
    UserRegistration user_reg = new UserRegistration();
    TableHeadingList thl = new TableHeadingList();
    /**
     * Creates new form AllUpdates
     */
    public AllUpdates() {
        initComponents();
    }
    //set user type
    public void setUserType(String set_user_type){
        this.user_type = set_user_type;
    }
    //get user type
    public String getUserType(){
        return user_type;
    }
    //set email or practice number
    public void setEmailPracticeNo(String set_email_pract){
        this.emailORpracticeNo = set_email_pract;
    }
    //get email or practice number
    public String getEmailPracticeNo(){
        return emailORpracticeNo;
    }
    //initialise all input values
    private String setValues(){
        
        String selected_item = (String)fieldToUpdate.getSelectedItem();
        
        user_reg.setUsername(username_txt.getText());
        
        switch(selected_item){
            
            case "Firstname": udf.setHeadingName("NAME");
                break;
            case "Lastname": udf.setHeadingName("SURNAME");
                break;
            case "ID/Passport number": udf.setHeadingName("ID_OR_PASSORT_NUMBER");
                break;
            case "Date of birth": udf.setHeadingName("DOB");
                break; 
            case "Occupation": udf.setHeadingName("OCCUPATION");
                break;
            case "Contact": udf.setHeadingName("MOBILE_NUMBER");
                break;
            case "Email": udf.setHeadingName("EMAIL");
                break;
            case "Specialization": udf.setHeadingName("SPECIALIZATION");
                break;
                
            default:JOptionPane.showMessageDialog(null, "selection problem contact administrator");
                    System.exit(0);
                   break;
        }
        
        String query_statement = "select "+udf.getHeadingName()+" from "+getUserType()+
                                 " where "+getEmailPracticeNo()+"=?";
        String userType = getUserType();
        String heading_name = udf.getHeadingName();
        boolean exist = false;
        
        switch (userType) {
            case "PATIENT":
                exist = thl.checkInPatient(heading_name);
                break;
            case "DOCTOR":
                exist = thl.checkInDoctor(heading_name);
                break;
            case "RECEPTIONIST":
                exist = thl.checkInReceptionist(heading_name);
                break;
            default:
                break;
        }
        
        if(!exist){
            JOptionPane.showMessageDialog(null, "heading name selected does not exist in the chosen table");
            clear();
            query_statement="";
        }
        
        return query_statement;
    }
    
    //validate input from the user
    private boolean validateInput(){
        boolean input_valid = true;
        
        if(select_user_type.getSelectedItem().equals("Select"))
            return outputStatement("select user type you want to update");
        
        if(fieldToUpdate.getSelectedItem().equals("Select"))
            return outputStatement("select field you want to update");
        
        if(username_txt.getText().equals(""))
            return outputStatement("Enter username");
        
        return input_valid;
    }
    //display error message
    private boolean outputStatement(String message){
        
        warning_msg.setText(message);
        clear();
        return false;
    }
    
    //check if selected exits in the current table
    private boolean selectedField(){
        
        String query = setValues();
        
        if(query.equals(""))return false;
        
        String fied_data = udf.getDataTobeUpdated(query);
        
        if(fied_data != null){
            val_tobe_updated.setText(fied_data);
            return true;
        }else{
            warning_msg.setText("username does not exist");
            return false;
        }
    }
    //validate input data to be modified
    private boolean setAndValidateUpdateField(){
        
        udf.setFieldData(val_tobe_updated.getText());
        if(udf.getFieldData().equals("")){
            warning_msg.setText("enter data to be updated");
            return false;
        }
        return true;
    }
    //validate and submit query statement
    private boolean submitQuery(){
        
        String query_statement = "update "+getUserType()+" set "+udf.getHeadingName()+
                                 "=? where "+getEmailPracticeNo()+"=?";
        
        boolean updated = udf.addUpdatedData(query_statement);
        
        if(updated){
            warning_msg.setForeground(Color.green);
            warning_msg.setText("update was successfull....");
        }else{
            JOptionPane.showMessageDialog(null, "Please check username or contact administrator");
            return false;
        }
        
        return true;
    }
    //update user password
    private void updatePassword()
    {
        String userType = (String)select_user_type.getSelectedItem();
        int update = JOptionPane.showConfirmDialog(null, "Are you sure you want to update password for "+userType+"?");
        if(update == 1)return;
        user_reg.setUsername(username_txt.getText());
        GenerateRandomChar grc = new GenerateRandomChar();
        SendEmail se = new SendEmail();
        udf.setHeadingName("PASSWORD");
        udf.setFieldData(grc.generatePassword());
        
        if(!submitQuery())return;
        se.sendEmail();
        clear();
    }
    //set selected user type
    private void userTypeSelected(){
        String selectUserType = (String)select_user_type.getSelectedItem();
        
        switch (selectUserType) {
            
            case "Doctor": setUserType("DOCTOR");
                           setEmailPracticeNo("PRACTICE_NUMBER");
                break;
            case "Patient": setUserType("PATIENT");
                            setEmailPracticeNo("EMAIL");
                break;
            case "Receptionist": setUserType("RECEPTIONIST");
                                 setEmailPracticeNo("EMAIL");
                break;
            default:JOptionPane.showMessageDialog(null, "Please contact the administrator");
                break;
        }
    }
    //clear input text
    private void clear(){
        
        username_txt.setText("");
        val_tobe_updated.setText("");
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
        closeAllUpdates = new javax.swing.JLabel();
        val_tobe_updated = new java.awt.TextField();
        UpdateData = new javax.swing.JButton();
        warning_msg = new java.awt.Label();
        fieldToUpdate = new javax.swing.JComboBox<>();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        select_user_type = new javax.swing.JComboBox<>();
        label4 = new java.awt.Label();
        username_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        allBackToRecptH = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 25, 112));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.yellow, 3));

        closeAllUpdates.setIcon(new javax.swing.ImageIcon("/home/tholithemba/Desktop/github/java/RDPhouses/close.png")); // NOI18N
        closeAllUpdates.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeAllUpdatesMouseClicked(evt);
            }
        });

        val_tobe_updated.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        UpdateData.setBackground(java.awt.Color.blue);
        UpdateData.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        UpdateData.setForeground(java.awt.Color.white);
        UpdateData.setText("Update");
        UpdateData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateDataMouseClicked(evt);
            }
        });

        warning_msg.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        warning_msg.setForeground(java.awt.Color.red);

        fieldToUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Firstname", "Lastname", "ID/Passport number", "Date of birth", "Occupation", "Specialization", "Contact", "Email", "Password" }));
        fieldToUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldToUpdateActionPerformed(evt);
            }
        });

        label2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label2.setForeground(java.awt.Color.white);
        label2.setText("Field to Modify");

        label3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label3.setForeground(java.awt.Color.white);
        label3.setText("Modify");

        select_user_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Doctor", "Patient", "Receptionist" }));
        select_user_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_user_typeActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label4.setForeground(java.awt.Color.white);
        label4.setText("Select user type to update");

        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Email");

        allBackToRecptH.setBackground(new java.awt.Color(53, 66, 74));
        allBackToRecptH.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        allBackToRecptH.setForeground(java.awt.Color.white);
        allBackToRecptH.setIcon(new javax.swing.ImageIcon("/home/tholithemba/Desktop/github/java/RDPhouses/arrow.png")); // NOI18N
        allBackToRecptH.setText("Back");
        allBackToRecptH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        allBackToRecptH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allBackToRecptHMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(allBackToRecptH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeAllUpdates, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 282, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warning_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(username_txt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(select_user_type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(val_tobe_updated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fieldToUpdate, javax.swing.GroupLayout.Alignment.LEADING, 0, 204, Short.MAX_VALUE))
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UpdateData, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeAllUpdates, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allBackToRecptH, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(select_user_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(username_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(val_tobe_updated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(fieldToUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(warning_msg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UpdateData)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeAllUpdatesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeAllUpdatesMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeAllUpdatesMouseClicked
    
    private void UpdateDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateDataMouseClicked
 
        if(!validateInput())return;

        if(!setAndValidateUpdateField())return;

        submitQuery();//submit query statement
        clear();//clear input text
    }//GEN-LAST:event_UpdateDataMouseClicked

    private void fieldToUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldToUpdateActionPerformed
        
        warning_msg.setText("");
        
        if(!validateInput())return;
        
        if(fieldToUpdate.getSelectedItem().equals("Password"))
        {
            updatePassword();
            return;
        }
        if(!selectedField())return;
    }//GEN-LAST:event_fieldToUpdateActionPerformed

    private void select_user_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_user_typeActionPerformed
   
        warning_msg.setText("");
        clear();
        userTypeSelected();
    }//GEN-LAST:event_select_user_typeActionPerformed

    private void allBackToRecptHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allBackToRecptHMouseClicked
        new ReceptionistHome().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_allBackToRecptHMouseClicked

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
            java.util.logging.Logger.getLogger(AllUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllUpdates().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UpdateData;
    private javax.swing.JLabel allBackToRecptH;
    private javax.swing.JLabel closeAllUpdates;
    private javax.swing.JComboBox<String> fieldToUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private javax.swing.JComboBox<String> select_user_type;
    private javax.swing.JTextField username_txt;
    private java.awt.TextField val_tobe_updated;
    private java.awt.Label warning_msg;
    // End of variables declaration//GEN-END:variables
}
