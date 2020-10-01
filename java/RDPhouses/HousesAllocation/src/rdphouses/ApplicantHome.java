/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdphouses;

import javax.swing.JOptionPane;

/**
 *
 * @author tholithemba
 */
public class ApplicantHome extends javax.swing.JFrame {

    Registration reg = new Registration();
    
    /**
     * Creates new form UpdateApplicant
     */
    public ApplicantHome() {
        initComponents();
    }
    
    /*private void updateTdatabase(String updatedValue)
    {
        PreparedStatement ps;
        String query_statement = "update APPLICANT set "+column_tobe_updated+"=? where applicant_username=?";   
        
        try {
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            ps.setString(1, updatedValue);
            ps.setString(2,reg.getUsername());
            
            if(ps.executeUpdate()>0)
            {
                warning_message.setForeground(Color.green);
                warning_message.setText("data updated successfully....");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateApplicant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        documents = new javax.swing.JPanel();
        closeUpdateApllicant = new javax.swing.JLabel();
        viewStatus = new javax.swing.JLabel();
        UpdateApplBackTAdminHome = new javax.swing.JLabel();
        warning_message = new javax.swing.JLabel();
        updatePassword = new javax.swing.JLabel();
        updateApplica = new javax.swing.JLabel();
        witdrawApplication = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        documents.setBackground(new java.awt.Color(53, 66, 74));
        documents.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 3));

        closeUpdateApllicant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rdphouses/close.png"))); // NOI18N
        closeUpdateApllicant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeUpdateApllicantMouseClicked(evt);
            }
        });

        viewStatus.setFont(new java.awt.Font("Ubuntu", 1, 28)); // NOI18N
        viewStatus.setForeground(java.awt.Color.white);
        viewStatus.setText("View Status");
        viewStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewStatusMouseClicked(evt);
            }
        });

        UpdateApplBackTAdminHome.setBackground(new java.awt.Color(53, 66, 74));
        UpdateApplBackTAdminHome.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        UpdateApplBackTAdminHome.setForeground(java.awt.Color.white);
        UpdateApplBackTAdminHome.setText("Back");
        UpdateApplBackTAdminHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        UpdateApplBackTAdminHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateApplBackTAdminHomeMouseClicked(evt);
            }
        });

        warning_message.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        warning_message.setForeground(java.awt.Color.red);

        updatePassword.setFont(new java.awt.Font("Ubuntu", 1, 28)); // NOI18N
        updatePassword.setForeground(java.awt.Color.white);
        updatePassword.setText("Update Password");
        updatePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatePasswordMouseClicked(evt);
            }
        });

        updateApplica.setFont(new java.awt.Font("Ubuntu", 1, 28)); // NOI18N
        updateApplica.setForeground(java.awt.Color.white);
        updateApplica.setText("Update ");
        updateApplica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateApplicaMouseClicked(evt);
            }
        });

        witdrawApplication.setFont(new java.awt.Font("Ubuntu", 1, 28)); // NOI18N
        witdrawApplication.setForeground(java.awt.Color.white);
        witdrawApplication.setText("Withdraw Application");
        witdrawApplication.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                witdrawApplicationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout documentsLayout = new javax.swing.GroupLayout(documents);
        documents.setLayout(documentsLayout);
        documentsLayout.setHorizontalGroup(
            documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(documentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UpdateApplBackTAdminHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeUpdateApllicant, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(documentsLayout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(warning_message, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, documentsLayout.createSequentialGroup()
                .addContainerGap(409, Short.MAX_VALUE)
                .addGroup(documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateApplica)
                    .addComponent(viewStatus)
                    .addComponent(updatePassword)
                    .addComponent(witdrawApplication))
                .addGap(338, 338, 338))
        );
        documentsLayout.setVerticalGroup(
            documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(documentsLayout.createSequentialGroup()
                .addGroup(documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpdateApplBackTAdminHome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeUpdateApllicant, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addComponent(viewStatus)
                .addGap(18, 18, 18)
                .addComponent(updateApplica)
                .addGap(18, 18, 18)
                .addComponent(witdrawApplication)
                .addGap(18, 18, 18)
                .addComponent(updatePassword)
                .addGap(319, 319, 319)
                .addComponent(warning_message, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(documents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(documents, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeUpdateApllicantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeUpdateApllicantMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeUpdateApllicantMouseClicked

    private void UpdateApplBackTAdminHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateApplBackTAdminHomeMouseClicked
        new AdminHome().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_UpdateApplBackTAdminHomeMouseClicked

    private void viewStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewStatusMouseClicked
        new CheckStatus().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_viewStatusMouseClicked

    private void updateApplicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateApplicaMouseClicked
        new ApplicantUpdates().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_updateApplicaMouseClicked

    private void witdrawApplicationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_witdrawApplicationMouseClicked
       
        JOptionPane.showConfirmDialog(null, "Are you sure you want to withdraw your application?");
    }//GEN-LAST:event_witdrawApplicationMouseClicked

    private void updatePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePasswordMouseClicked
        new ApplicantPassword().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_updatePasswordMouseClicked

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
            java.util.logging.Logger.getLogger(ApplicantHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicantHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicantHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicantHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApplicantHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UpdateApplBackTAdminHome;
    private javax.swing.JLabel closeUpdateApllicant;
    private javax.swing.JPanel documents;
    private javax.swing.JLabel updateApplica;
    private javax.swing.JLabel updatePassword;
    private javax.swing.JLabel viewStatus;
    private javax.swing.JLabel warning_message;
    private javax.swing.JLabel witdrawApplication;
    // End of variables declaration//GEN-END:variables
}
