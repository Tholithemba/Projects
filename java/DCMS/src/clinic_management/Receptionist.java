package clinic_management;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tholithemba
 */
public class Receptionist extends javax.swing.JFrame {

    UserRegistration user_reg = new UserRegistration();
    GenerateRandomChar g_rand_pswrd = new GenerateRandomChar();
    DBInputValidation dbInputval = new DBInputValidation();
    SendEmail send_email = new SendEmail();
    
    /**
     * Creates new form Receptionist
     */
    public Receptionist() {
        initComponents();
    }

    //initialise input values fromthe user
    private void setValues(){
        
        user_reg.setFirstname(firstname.getText());
        user_reg.setLastname(lastname.getText());
        user_reg.setUsername(recept_email.getText());
        user_reg.setPassword(g_rand_pswrd.generatePassword());
    }
    
    //validate input from the user
    private boolean validateInput(){
        
        if(user_reg.getFirstName().equals(""))
            return outputStatement("insert name");
        
        if(user_reg.getLastname().equals(""))
            return outputStatement("insert your lastname");
        
        if(user_reg.getUsername().equals(""))
            return outputStatement("insert your email address");
        
        return true;
    }
    
    
    //display error message
    private boolean outputStatement(String message){
        warning.setText(message);
        return false;
    }
    
    //return true if th email exist, false otherwise
    private boolean email_notexist(){
       
        String query_statement = "select* from RECEPTIONIST where EMAIL=?";
        boolean exist = dbInputval.checkUsername(query_statement);
        if(exist)
            return outputStatement("Email already exist");
     
        return true;
    }
    
    //create reciptionist by entering valid input
    //valid input will be stored on database
    void create_receptionist()
    {

        PreparedStatement ps;
        String query_statement = "INSERT INTO `RECEPTIONIST`("
                + "`NAME`, `SURNAME`, `EMAIL`,`PASSWORD`)"
                + " VALUES (?,?,?,?)";
    
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);

            ps.setString(1, user_reg.getFirstName());
            ps.setString(2, user_reg.getLastname());
            ps.setString(3, user_reg.getUsername());
            ps.setString(4, user_reg.getPassword());
            if(ps.executeUpdate()>0)
            {    
                warning.setForeground(Color.green);
                warning.setText("data captured successfully");
                clearCells();
            }
            
        }catch(SQLException sq)
        {
            sq.getSQLState();
        }
        
        
    }
    
    //clear input fields after data was captured
    public void clearCells()
    {
        firstname.setText("");
        lastname.setText("");
        recept_email.setText("");
    }
    //end of clear_cells function
    
    
    //back to login page
    private void backTo(){
        
        new Login().setVisible(true);
        this.setVisible(false);        
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        firstname = new javax.swing.JTextField();
        lastname = new javax.swing.JTextField();
        recept_email = new javax.swing.JTextField();
        warning = new javax.swing.JLabel();
        recept_register = new javax.swing.JButton();
        goto_login = new javax.swing.JLabel();
        closeRecept = new javax.swing.JLabel();
        newReceptionst = new javax.swing.JLabel();
        ReceptBackToL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 25, 112));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.yellow, 3));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Firstname:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Lastname:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Email:");

        warning.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        warning.setForeground(java.awt.Color.red);

        recept_register.setBackground(java.awt.Color.blue);
        recept_register.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        recept_register.setForeground(java.awt.Color.white);
        recept_register.setText("Register");
        recept_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recept_registerActionPerformed(evt);
            }
        });

        goto_login.setForeground(java.awt.Color.red);
        goto_login.setText("            Already a user?");
        goto_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goto_loginMouseClicked(evt);
            }
        });

        closeRecept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/close.png"))); // NOI18N
        closeRecept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeReceptMouseClicked(evt);
            }
        });

        newReceptionst.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        newReceptionst.setForeground(java.awt.Color.white);
        newReceptionst.setText("New Receptionist");

        ReceptBackToL.setBackground(new java.awt.Color(53, 66, 74));
        ReceptBackToL.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        ReceptBackToL.setForeground(java.awt.Color.white);
        ReceptBackToL.setText("Back");
        ReceptBackToL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        ReceptBackToL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReceptBackToLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeRecept, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(goto_login, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(firstname)
                                .addComponent(lastname)
                                .addComponent(recept_email, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ReceptBackToL)
                        .addGap(201, 201, 201)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newReceptionst)
                            .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(238, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(recept_register, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(329, 329, 329))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(closeRecept, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ReceptBackToL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(newReceptionst)
                        .addGap(18, 18, 18)))
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(recept_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(62, 62, 62)
                .addComponent(recept_register)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goto_login)
                .addContainerGap(77, Short.MAX_VALUE))
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

        setSize(new java.awt.Dimension(777, 428));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void recept_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recept_registerActionPerformed
       
        setValues();
       
        if(!validateInput())return;
        
        if(!email_notexist())return;
        
        if(!send_email.sendEmail())return;
        create_receptionist();  
    }//GEN-LAST:event_recept_registerActionPerformed

    private void goto_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goto_loginMouseClicked
       
        backTo();
    }//GEN-LAST:event_goto_loginMouseClicked

    private void closeReceptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeReceptMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeReceptMouseClicked

    private void ReceptBackToLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReceptBackToLMouseClicked

        backTo();
    }//GEN-LAST:event_ReceptBackToLMouseClicked
      
    
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
        
        
        //create_receptionist();
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
            java.util.logging.Logger.getLogger(Receptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receptionist().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ReceptBackToL;
    private javax.swing.JLabel closeRecept;
    private javax.swing.JTextField firstname;
    private javax.swing.JLabel goto_login;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastname;
    private javax.swing.JLabel newReceptionst;
    private javax.swing.JTextField recept_email;
    private javax.swing.JButton recept_register;
    private javax.swing.JLabel warning;
    // End of variables declaration//GEN-END:variables
}
