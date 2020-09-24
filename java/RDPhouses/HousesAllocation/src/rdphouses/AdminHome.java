
package rdphouses;

/**
 *
 * @author tholithemba
 */
public class AdminHome extends javax.swing.JFrame {

    /**
     * Creates new form AdminHome
     */
    public AdminHome() {
        initComponents();
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
        createAllocation = new javax.swing.JLabel();
        createFunder = new javax.swing.JLabel();
        createAdmin = new javax.swing.JLabel();
        closeAdminHompage = new javax.swing.JLabel();
        createApplcant = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(53, 66, 74));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 73, 29), 3));

        createAllocation.setBackground(new java.awt.Color(53, 66, 74));
        createAllocation.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        createAllocation.setForeground(new java.awt.Color(232, 73, 29));
        createAllocation.setText("Allocate Amount");
        createAllocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createAllocationMouseClicked(evt);
            }
        });

        createFunder.setBackground(new java.awt.Color(53, 66, 74));
        createFunder.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        createFunder.setForeground(new java.awt.Color(232, 73, 29));
        createFunder.setText("Create Funder");
        createFunder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createFunderMouseClicked(evt);
            }
        });

        createAdmin.setBackground(new java.awt.Color(53, 66, 74));
        createAdmin.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        createAdmin.setForeground(new java.awt.Color(232, 73, 29));
        createAdmin.setText("Create Administrator");
        createAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createAdminMouseClicked(evt);
            }
        });

        closeAdminHompage.setIcon(new javax.swing.ImageIcon("/home/tholithemba/Desktop/github/java/RDPhouses/close.png")); // NOI18N
        closeAdminHompage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeAdminHompageMouseClicked(evt);
            }
        });

        createApplcant.setBackground(new java.awt.Color(53, 66, 74));
        createApplcant.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        createApplcant.setForeground(new java.awt.Color(232, 73, 29));
        createApplcant.setText("Create Applicant");
        createApplcant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createApplcantMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 778, Short.MAX_VALUE)
                .addComponent(closeAdminHompage, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createAllocation, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createFunder, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createApplcant, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(closeAdminHompage, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(createAllocation, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createFunder, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createApplcant, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
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

    private void closeAdminHompageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeAdminHompageMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeAdminHompageMouseClicked

    private void createAllocationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAllocationMouseClicked
        new Allocation().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_createAllocationMouseClicked

    private void createFunderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createFunderMouseClicked
        //int admin_id = 1;
        new Funder().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_createFunderMouseClicked

    private void createAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAdminMouseClicked
        new Administrator().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_createAdminMouseClicked

    private void createApplcantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createApplcantMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_createApplcantMouseClicked

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
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel closeAdminHompage;
    private javax.swing.JLabel createAdmin;
    private javax.swing.JLabel createAllocation;
    private javax.swing.JLabel createApplcant;
    private javax.swing.JLabel createFunder;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}