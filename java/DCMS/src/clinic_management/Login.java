package clinic_management;

import javax.swing.JOptionPane;

/**
 *
 * @author tholithemba
 */

public class Login extends javax.swing.JFrame {

    private String selected_user;
    private static boolean receptionist_active;
    private static boolean doctor_active;
    private static boolean patient_active;
    DBInputValidation dbInputval = new DBInputValidation();
    ReceptionistHome recept_home = new ReceptionistHome();
    UserRegistration user_reg = new UserRegistration();
    UpdateDataFields udf = new UpdateDataFields();
    
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }
    
    //set user logged in
    public void setSelectedUser(String set_user){
        this.selected_user = set_user;
    }
    
    //get user who logged in
    public String getSelectedUser(){
        return selected_user;
    }
    
    //set receptionist active if it is receptionist who logged in
    private void setActiveRecept(boolean set_active){
        receptionist_active = set_active;
    }
    
     //return true if it is Receptionist who logged in otherwise return false
    public boolean getActiveRecept(){
        return receptionist_active;
    }
    
    //set Doctor active if it is doctor who logged in
    private void setActiveDoctor(boolean set_active){
        doctor_active = set_active;
    }
    
     //return true if it is Doctor who logged in otherwise return false
    public boolean getActiveDoctor(){
        return doctor_active;
    }
    
    //set Patient active if it is patient who logged in
    private void setActivePatient(boolean set_active){
        patient_active = set_active;
    }
    
    //return true if it is patient who logged in otherwise return false
    public boolean getActivePatien(){
        return patient_active;
    }
    
    //initialise input values from the user
    private void setvalues()
    {
        setSelectedUser((String)comb_usrtype.getSelectedItem());
        user_reg.setUsername(txtf_user_id.getText());
        user_reg.setPassword(String.valueOf(txtf_login_password.getPassword()));
    }
    
    //validate input values from the user
    private boolean validateInput(){
        
        boolean valid_input = true;
        
        if(user_reg.getUsername().equals("")) 
            return ouputStatement("please enter your user username");
        
        if(user_reg.getPassword().equals(""))
            return ouputStatement("please enter your user password");
        
        if(getSelectedUser().equals("usertype"))
            return ouputStatement("please select usertype");
        
        return valid_input;
    }
    //end of validateInput() method
    
    //display error message
    private boolean ouputStatement(String message){
        warn_message.setText(message); 
        return false;
    }
    
    //check user logged in username and password if it exist in the database 
    private void SelectedUser()
    {
        GenerateRandomChar grc = new GenerateRandomChar();
        String chosen_query = chooseQuerystatement();
        
        boolean exist = dbInputval.checkUsername(chosen_query);
        if(!exist){              
            login_error_message("incorrect username");
            return;
        }
        
        if(!grc.getPswrdTobeSent().equals(user_reg.getPassword()))
        {
            login_error_message("incorrect password");
            return;
        }
        
        //execute only if the input from the user exist in database
        userLoggedIn();
    }
    //end of SelectedUser() method
    private void userLoggedIn()
    {
        switch (getSelectedUser()) {
            
            case "Doctor":
                    setActiveDoctor(true);
                    new DoctorHome().setVisible(true);
                    this.setVisible(false);
                break;
            case "Patient":
                    setActivePatient(true);
                    new PatientHome().setVisible(true);
                    this.setVisible(false);
                break;
            case "Receptionist":
                    setActiveRecept(true);
                    recept_home.setReceptionistid(dbInputval.getUserID());
                    new ReceptionistHome().setVisible(true);
                    this.setVisible(false);
                break;
            default:
                break;    
        }        
    }
    //end of userLoggedIn() method
    
    //if user is new. the user must register first
    private void newUser()
    {
        switch (getSelectedUser()) {
            
            case "Doctor":
                    new Doctor().setVisible(true);
                    this.setVisible(false);
                break;
            case "Patient":
                    new Patient().setVisible(true);
                    this.setVisible(false);
                break;
            case "Receptionist":
                    new Receptionist().setVisible(true);
                    this.setVisible(false);
                break;
            default: ouputStatement("Please select user type");
                break;    
        }
    }
    //end if newUser() method
    
    //select query statement corressponding to user logged in
    public String chooseQuerystatement(){
        
        String quiry_statement = "";
        switch (getSelectedUser()) {
            case "Patient":
                quiry_statement = "select* from PATIENT where EMAIL=?";
                udf.setHeadingName("PATIENT_ID");
                break;
            case "Doctor":
                quiry_statement = "select* from DOCTOR where PRACTICE_NUMBER=?";
                udf.setHeadingName("DOCTOR_ID");
                break;
            case "Receptionist":
                quiry_statement = "select* from RECEPTIONIST where EMAIL=?";
                udf.setHeadingName("RECEPTIONIST_ID");
                break;
            default:
                JOptionPane.showMessageDialog(null, "please contact the Administrator");
                break;
        }
        
        return quiry_statement;
    }
    //end of chooseQuerystatement() method
 
    //login erro message
    public void login_error_message(String message){
        txtf_user_id.setText("");
        txtf_login_password.setText("");
        warn_message.setText(message);
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
        btn_login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtf_user_id = new javax.swing.JTextField();
        txtf_login_password = new javax.swing.JPasswordField();
        warn_message = new javax.swing.JLabel();
        newUser = new javax.swing.JLabel();
        comb_usrtype = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        closeLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 25, 112));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.yellow, 3));
        jPanel1.setForeground(java.awt.Color.white);

        btn_login.setBackground(java.awt.Color.blue);
        btn_login.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btn_login.setForeground(java.awt.Color.white);
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Password:");

        warn_message.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        warn_message.setForeground(java.awt.Color.red);

        newUser.setForeground(java.awt.Color.red);
        newUser.setText("       new user?  sign up");
        newUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newUserMouseClicked(evt);
            }
        });

        comb_usrtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "usertype", "Doctor", "Patient", "Receptionist" }));

        jLabel4.setIcon(new javax.swing.ImageIcon("/home/tholithemba/Desktop/my_project/My_Projests/DCMS/icon2.png")); // NOI18N

        closeLogin.setIcon(new javax.swing.ImageIcon("/home/tholithemba/Desktop/github/java/RDPhouses/close.png")); // NOI18N
        closeLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(newUser, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(comb_usrtype, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtf_user_id)
                                            .addComponent(txtf_login_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1)))
                                .addGap(174, 174, 174))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(197, 197, 197))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(warn_message, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addComponent(closeLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(closeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(warn_message, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comb_usrtype, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtf_user_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(9, 9, 9)
                        .addComponent(txtf_login_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_login)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newUser))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
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

        setSize(new java.awt.Dimension(686, 450));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed

        setvalues();
      
        if(!validateInput())return;
        
        SelectedUser();
        
    }//GEN-LAST:event_btn_loginActionPerformed

    private void newUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newUserMouseClicked
        
        setvalues();
        newUser();
    }//GEN-LAST:event_newUserMouseClicked

    private void closeLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLoginMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeLoginMouseClicked

    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel closeLogin;
    private javax.swing.JComboBox<String> comb_usrtype;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel newUser;
    private javax.swing.JPasswordField txtf_login_password;
    private javax.swing.JTextField txtf_user_id;
    private javax.swing.JLabel warn_message;
    // End of variables declaration//GEN-END:variables
}
