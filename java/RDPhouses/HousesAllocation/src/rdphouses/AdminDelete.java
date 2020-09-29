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
public class AdminDelete extends javax.swing.JFrame {

    /**
     * Creates new form AdminDelete
     */
    public AdminDelete() {
        initComponents();
    }

    private boolean validateInput()
    {
        if(textAdminUsername.getText().equals(""))
        {
            error_text.setText("Enter username");
            return false;
        }
       
        return true;
    }
    
    private boolean deleteAdmin()
    {
        Crud crud = new Crud();
        String query = "delete from ADMINISTRATOR where admin_username=?";
        
        return crud.deleteData(query);
    }
    
    private void successMessage()
    {
       error_text.setText("data deleted successfully....");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        documents = new javax.swing.JPanel();
        closeAdminDelete = new javax.swing.JLabel();
        BackTAdminAdmin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btndeleteAdmin = new javax.swing.JButton();
        error_text = new java.awt.Label();
        textAdminUsername = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        documents.setBackground(new java.awt.Color(53, 66, 74));
        documents.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 3));

        closeAdminDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rdphouses/close.png"))); // NOI18N
        closeAdminDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeAdminDeleteMouseClicked(evt);
            }
        });

        BackTAdminAdmin.setBackground(new java.awt.Color(53, 66, 74));
        BackTAdminAdmin.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        BackTAdminAdmin.setForeground(java.awt.Color.white);
        BackTAdminAdmin.setText("Back");
        BackTAdminAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        BackTAdminAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackTAdminAdminMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Username");

        btndeleteAdmin.setBackground(java.awt.Color.red);
        btndeleteAdmin.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btndeleteAdmin.setForeground(java.awt.Color.white);
        btndeleteAdmin.setText("Delete");
        btndeleteAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndeleteAdminMouseClicked(evt);
            }
        });

        error_text.setAlignment(java.awt.Label.CENTER);
        error_text.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        error_text.setForeground(java.awt.Color.red);
        error_text.setName(""); // NOI18N

        javax.swing.GroupLayout documentsLayout = new javax.swing.GroupLayout(documents);
        documents.setLayout(documentsLayout);
        documentsLayout.setHorizontalGroup(
            documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, documentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackTAdminAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 791, Short.MAX_VALUE)
                .addComponent(closeAdminDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(documentsLayout.createSequentialGroup()
                .addGroup(documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(documentsLayout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(error_text, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(documentsLayout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(btndeleteAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(documentsLayout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(textAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(documentsLayout.createSequentialGroup()
                    .addGap(308, 308, 308)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(433, Short.MAX_VALUE)))
        );
        documentsLayout.setVerticalGroup(
            documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(documentsLayout.createSequentialGroup()
                .addGroup(documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(closeAdminDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackTAdminAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(error_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(textAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btndeleteAdmin)
                .addContainerGap(139, Short.MAX_VALUE))
            .addGroup(documentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(documentsLayout.createSequentialGroup()
                    .addGap(117, 117, 117)
                    .addComponent(jLabel1)
                    .addContainerGap(268, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(documents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(documents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeAdminDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeAdminDeleteMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeAdminDeleteMouseClicked

    private void BackTAdminAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackTAdminAdminMouseClicked
        new AdminAdmin().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BackTAdminAdminMouseClicked

    private void btndeleteAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndeleteAdminMouseClicked
        
        if(!validateInput())return;
        if(!deleteAdmin())return;
         successMessage();
    }//GEN-LAST:event_btndeleteAdminMouseClicked

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
            java.util.logging.Logger.getLogger(AdminDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDelete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackTAdminAdmin;
    private javax.swing.JButton btndeleteAdmin;
    private javax.swing.JLabel closeAdminDelete;
    private javax.swing.JPanel documents;
    private java.awt.Label error_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField textAdminUsername;
    // End of variables declaration//GEN-END:variables
}
