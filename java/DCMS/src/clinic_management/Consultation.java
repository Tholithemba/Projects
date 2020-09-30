package clinic_management;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


/**
 *
 * @author tholithemba
 */
public class Consultation extends javax.swing.JFrame {

    private String description;
    private static double cost;
    private static int appointment_id;
    private Double total=0.0;
    private static boolean successful_charged;
    private static int patient_id;
    PreparedStatement p;
    DBInputValidation dbInputval = new DBInputValidation();
    UpdateDataFields udf = new UpdateDataFields();
    UserRegistration user_reg = new UserRegistration();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
    
    
    /**
     * Creates new form Consultation
     */
    public Consultation() {
        initComponents();
    }
    
    //set appointment id
    private void setAppointmentID(int set_id){
        appointment_id = set_id;
    }
    
    // get apppointment id
    private int getAppointmentID(){
        return appointment_id;
    }
    
    //set patient id
    private void setPatientID(int set_id){
        patient_id = set_id;
    }
    
    //get patient id
    public int getPatientID(){
        return patient_id;
    }
    
    //set selected description
    public void setSelectedDescr(String set_descr){
        this.description = set_descr;
    }

    //get selected description
    public String getSelectedDescr(){
        return description;
    }
    
    //set cost
    public void setCost(Double set_cost){
        cost = set_cost;
    }
       
    //get cost
    public Double getCost(){
        return cost;
    }
    
    //get current time
    private String getCurrentTime(){
        return time_format.format(new java.util.Date());
    }
    
    //get current today's date
    private String getCurrentDate(){
        return sdf.format(new java.util.Date());
    }
    
    //set that patient is successfuly charged
    private void setSuccessfulCharged(boolean set_charged){
        successful_charged = set_charged;
    }
    
    //confirm that patient is successfull charged
    private boolean getSuccessfulCharged(){
        return successful_charged;
    }
    
    //validate input from the user
    private boolean validateInput(){
        
        boolean input_valid = true;

        if(txt_patient_email.getText().equals(""))
            return outputStatement("enter patient email");
        
        if(txt_added_item.getText().equals(""))
            return outputStatement("Description not selected");
        
        return input_valid;
    }
    
    //display error message
    private boolean outputStatement(String err_msg){
        
        lblcharged.setForeground(Color.red);
        lblcharged.setText(err_msg);
        return false;
    }

    //this function fetch id of a selected user
    public boolean fetchPatientID()
    {
        String query = "SELECT PATIENT_ID FROM PATIENT"
                    + " WHERE EMAIL=?";
        
        user_reg.setUsername(txt_patient_email.getText());
        udf.setHeadingName("PATIENT_ID");
        
        boolean found  = dbInputval.checkUsername(query);
        
        if(!found){
            return outputStatement("email does not exist");
            
        }
        setPatientID(dbInputval.getUserID());
        
        return found;
    }
    //end of fetchPatientID function
    
    
    //this function fetch id of a selected user
    public boolean fetchAppointmentID()
    {
        String query = "SELECT APPOINTMENT_ID FROM APPOINTMENT"
                    + " WHERE PATIENT_ID=? AND CHARGE=?";
        
        udf.setHeadingName("APPOINTMENT_ID");
        boolean found  = dbInputval.checkPatientID(query);
        
        if(!found)
            return outputStatement("no appointment was created by the patient");

        setAppointmentID(dbInputval.getUserID());
        
        return found;
    }
    //end of fetchAppointmentID function
    
    //set prices
    public void item_cost()
    {
        String list_item = getSelectedDescr();
        
        switch(list_item)
        {
            case "consultation fee": txtf_cost.setText("350");
            break;
            case "veneer": txtf_cost.setText("4000");
            break;
            case "crown": txtf_cost.setText("11152");
            break;
            case "Inlays": txtf_cost.setText("1200");
            break;
            case "Bridges": txtf_cost.setText("800");
            break;
            case "dental implant": txtf_cost.setText("8000");
            break;
            case "Silver Crowns": txtf_cost.setText("2500");
            break;
            case "Simple tooth removal": txtf_cost.setText("120");
            break;
            case "white filling": txtf_cost.setText("450");
            break;
            case "RCT cleans and disinfects": txtf_cost.setText("230");
            break;
            case "Fissure sealants": txtf_cost.setText("3000");
            break;  
            default : JOptionPane.showMessageDialog(null, "item not in list");
        }
    }
    
    //charge patient as save it to database
    public boolean chargePatient()
    {
        PreparedStatement ps;
        boolean successful = false;
        
        String add_data = "INSERT INTO CONSULTATION(DESCRIPTION, COST,"
                + "TIME,DATE, APPOINTMENT_ID ) VALUES (?,?,?,?,?)";
        try
        {
            ps = Connect2database.getConnection().prepareStatement(add_data);

            ps.setString(1, getSelectedDescr());
            ps.setDouble(2, getCost());
            ps.setString(3, getCurrentTime());
            ps.setString(4, getCurrentDate());
            ps.setInt(5, getAppointmentID());
            
            if(ps.executeUpdate()>0){
                successful = true;
            }
            
        }catch(SQLException sq)
        {
            sq.getSQLState();
        }
        return successful;
    }
    //end of chargePatient method
    
    //update uncharged to charged in the database
    public void changeto_charged()
    {
        PreparedStatement ps;
        
        String quiry = "UPDATE APPOINTMENT SET CHARGE=? WHERE APPOINTMENT_ID=?";
        
        try
        {
            ps = Connect2database.getConnection().prepareStatement(quiry);

            ps.setString(1, "charged");
            ps.setInt(2, getAppointmentID());
            
            if(ps.executeUpdate()>0){
                lblcharged.setForeground(Color.green);
                lblcharged.setText("Patient successfully charged");
            }
        }
        catch(SQLException sq)
        {
            sq.getSQLState();
        }         
    }
    //end of changeto_charged() method
    
    //clear input text fields
    private void clearCells(){
        txt_added_item.setText("");
        txtf_cost.setText("");
    }
   
    //clear warning text
    private void clearWarningText(){
       lblcharged.setText(""); 
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCansel = new javax.swing.JButton();
        btncharge = new javax.swing.JButton();
        txt_total = new javax.swing.JTextField();
        lblcharged = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        treatment = new javax.swing.JList<>();
        txt_added_item = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtf_cost = new javax.swing.JTextField();
        btnDone = new javax.swing.JButton();
        show_name1 = new javax.swing.JLabel();
        closeConsultation = new javax.swing.JLabel();
        ConsultBackToRecptH = new javax.swing.JLabel();
        txt_patient_email = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 25, 112));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.yellow, 3));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Cost:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Description:");

        btnCansel.setBackground(java.awt.Color.red);
        btnCansel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnCansel.setForeground(java.awt.Color.white);
        btnCansel.setText("Cancel");
        btnCansel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanselActionPerformed(evt);
            }
        });

        btncharge.setBackground(java.awt.Color.blue);
        btncharge.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btncharge.setForeground(java.awt.Color.white);
        btncharge.setText("Charge");
        btncharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchargeActionPerformed(evt);
            }
        });

        lblcharged.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblcharged.setForeground(java.awt.Color.blue);

        treatment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 25, 112), 4, true));
        treatment.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        treatment.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "consultation fee", "veneer", "crown", "Inlays", "Bridges", "dental implant", "Silver Crowns", "Simple tooth removal", "white filling", "RCT cleans and disinfects", "Fissure sealants" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        treatment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treatmentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(treatment);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Total:");

        btnDone.setBackground(java.awt.Color.blue);
        btnDone.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnDone.setForeground(java.awt.Color.white);
        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        show_name1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        show_name1.setForeground(java.awt.Color.white);
        show_name1.setText("patient email");

        closeConsultation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/close.png"))); // NOI18N
        closeConsultation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeConsultationMouseClicked(evt);
            }
        });

        ConsultBackToRecptH.setBackground(new java.awt.Color(53, 66, 74));
        ConsultBackToRecptH.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        ConsultBackToRecptH.setForeground(java.awt.Color.white);
        ConsultBackToRecptH.setText("Back");
        ConsultBackToRecptH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        ConsultBackToRecptH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConsultBackToRecptHMouseClicked(evt);
            }
        });

        txt_patient_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_patient_emailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ConsultBackToRecptH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(show_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_patient_email, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCansel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(btncharge, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 52, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_added_item, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtf_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblcharged, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeConsultation, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(show_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_patient_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ConsultBackToRecptH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(closeConsultation, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblcharged, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_added_item, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtf_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCansel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncharge, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)))
                .addComponent(btnDone)
                .addGap(24, 24, 24))
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

        setSize(new java.awt.Dimension(879, 472));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCanselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanselActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCanselActionPerformed

    private void btnchargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchargeActionPerformed
     
        clearWarningText();
        if(!validateInput())return;
        setCost(Double.parseDouble(txtf_cost.getText()));
      
        total += getCost();
        if(getAppointmentID() == 0){
            outputStatement("No appointment was created by the patient");
            return;
        }
        boolean success = chargePatient();
        setSuccessfulCharged(success);
        clearCells();
        txt_total.setText("R"+total);
    }//GEN-LAST:event_btnchargeActionPerformed

    private void treatmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treatmentMouseClicked

        clearWarningText();
        String sel_value = treatment.getSelectedValue();
        setSelectedDescr(sel_value);
        txt_added_item.setText(sel_value);

        item_cost();
    }//GEN-LAST:event_treatmentMouseClicked

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        
        clearWarningText();
        if(getSuccessfulCharged())
            changeto_charged();
    }//GEN-LAST:event_btnDoneActionPerformed

    private void closeConsultationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeConsultationMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeConsultationMouseClicked

    private void ConsultBackToRecptHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConsultBackToRecptHMouseClicked
       
        new ReceptionistHome().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ConsultBackToRecptHMouseClicked

    private void txt_patient_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_patient_emailActionPerformed
        
        clearWarningText();
        if(txt_patient_email.getText().equals("")){
            outputStatement("enter correct Patient email address");
            return;            
        }
        
        if(!fetchPatientID())return;
        
        if(!fetchAppointmentID())return;
    }//GEN-LAST:event_txt_patient_emailActionPerformed
 
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
            java.util.logging.Logger.getLogger(Consultation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ConsultBackToRecptH;
    private javax.swing.JButton btnCansel;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btncharge;
    private javax.swing.JLabel closeConsultation;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblcharged;
    private javax.swing.JLabel show_name1;
    private javax.swing.JList<String> treatment;
    private javax.swing.JTextField txt_added_item;
    private javax.swing.JTextField txt_patient_email;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txtf_cost;
    // End of variables declaration//GEN-END:variables
}
