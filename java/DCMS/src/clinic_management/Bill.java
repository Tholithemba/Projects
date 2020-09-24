/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic_management;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author tholithemba
 */
public class Bill extends javax.swing.JFrame {

    Login back_to = new Login();
    DBInputValidation dbInputval = new DBInputValidation();
    UserRegistration user_reg = new UserRegistration();
    PatientRegistration p_reg = new PatientRegistration();
    DateFormat date_format = new DateFormat();
    Document doc = new Document();
    PdfPTable table = new PdfPTable(3);

    private static ArrayList<String> pdf_data;
    
    /**
     * Creates new form Bill
     */
    public Bill() {
        initComponents();
    }
    
    //validate input from the user
    private boolean validateInput()
    {
        boolean not_empty = true;
        if(txtpatient_email.getText().equals(""))
            return erroroutputMessage("enter patient email address");

        return not_empty;
    }
    
    
    //display error message
    private boolean erroroutputMessage(String message)
    {
        clearEmailText();
        lblerrormsg.setText(message);
        return false;
    }
    
    //crear displayed error message
    private void clearErrorMessage()
    {
        lblerrormsg.setText("");
    }
    
    //clear user input text
    private void clearEmailText()
    {
        txtpatient_email.setText("");
    }
    
    //view bill in pdf format
    public void viewBillpdf()
    {
        date_format.setDate(new java.util.Date());
        String path = locateFile();
        
        try {
          
            PdfWriter.getInstance(doc, new FileOutputStream(path+"/bill.pdf"));
           
            doc.open();
            doc.addTitle("Bill");
            addressFullname();
             
            pdfTable();
            
            insertData();

            doc.add(table);
            doc.add(new Paragraph("********************************************"
                    + "*********the end***************************************"
                    + "*******"));
            
            doc.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addressFullname()
    {
        String full_name = user_reg.getFirstName()+" "+user_reg.getLastname();
        String formated_date = date_format.simpl_date.format(date_format.getDate());
        
        try {
            doc.add(new Paragraph(p_reg.getPatientAddress1()));
            doc.add(new Paragraph(p_reg.getPatientAddress2()));
            doc.add(new Paragraph(p_reg.getPatientAddress3()));
            doc.add(new Paragraph(p_reg.getPatientCity()));
            doc.add(new Paragraph(p_reg.getPatientPostalCode()));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(full_name));
            doc.add(new Paragraph(formated_date));
            
        } catch (DocumentException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    //add table to pdf file
    private void pdfTable()
    {
        table.setWidthPercentage(105);
        table.setSpacingBefore(11f);
        table.setSpacingAfter(11f);
       
        try 
        {
            float[] col_width = {2f,2f,2f};
            table.setWidths(col_width);
            PdfPCell cell_1 = new PdfPCell(new Paragraph("Date"));
            PdfPCell cell_2 = new PdfPCell(new Paragraph("Descriptoin"));
            PdfPCell cell_3 = new PdfPCell(new Paragraph("Charges"));
            
            table.addCell(cell_1);
            table.addCell(cell_2);
            table.addCell(cell_3);
            
        } catch (DocumentException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    //add data to pdf file
    private void insertData()
    {
        int array_size = pdf_data.size()/2 -1;
        int r;
        int col;

        for(r = 0; r < array_size; r++)
        {
            col = 3*r;
            table.addCell(pdf_data.get(col));
            table.addCell(pdf_data.get(col + 1));
            table.addCell("R"+pdf_data.get(col + 2));
        }        
    }
    
    
    ///save file in a chosen location
    private String locateFile()
    {
        String path = "";

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int show = chooser.showSaveDialog(this);
        
        if(show == JFileChooser.APPROVE_OPTION)
            path = chooser.getSelectedFile().getPath();
        
        return path;
    }
    
    
    //back to active user home page
    private void backTo(){
        
        if(back_to.getActiveRecept())
            new ReceptionistHome().setVisible(true);
        else
            new PatientHome().setVisible(true);
        
        this.setVisible(false);
    }
    
    //read bill from the database
    private boolean readBill()
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean patient_exist = false;
        String query = 
                "select DESCRIPTION, COST, CONSULTATION.DATE "
                + "from CONSULTATION"
                + " join APPOINTMENT on "
                + " CONSULTATION.APPOINTMENT_ID = APPOINTMENT.APPOINTMENT_ID "
                + " join PATIENT on "
                + " APPOINTMENT.PATIENT_ID = PATIENT.PATIENT_ID "
                + " where PATIENT.EMAIL=? ";
    
        pdf_data = new ArrayList<>();
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setString(1, user_reg.getUsername());
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                pdf_data.add(rs.getString("DATE"));
                pdf_data.add(rs.getString("DESCRIPTION"));
                pdf_data.add(rs.getString("COST"));
                patient_exist = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return patient_exist;
    }
    
    
    //get patioent details from the database
    private void patientDetails()
    {
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "select NAME, SURNAME, ADDRESS_ID "
                + "FROM PATIENT WHERE EMAIL=?";
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setString(1, user_reg.getUsername());
            
            rs = ps.executeQuery();
            if(rs.next())
            {
                user_reg.setFirstname(rs.getString("NAME"));
                user_reg.setLastname(rs.getString("SURNAME"));
                dbInputval.setUserID(rs.getInt("ADDRESS_ID"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //get patient address from the database
    private void getPatiendAddress()
    {
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "select ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, " 
                + " CITY, POSTAL_CODE from ADDRESS where ADDRESS_ID=?";
        
        try{
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setInt(1, dbInputval.getUserID());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                p_reg.setPatientAddress1(rs.getString("ADDRESS_LINE_1"));
                p_reg.setPatientAddress2(rs.getString("ADDRESS_LINE_2"));
                p_reg.setPatientAddress3(rs.getString("ADDRESS_LINE_3"));
                p_reg.setPatientCity(rs.getString("CITY"));
                int post_code = rs.getInt("POSTAL_CODE");
                p_reg.setPatientPostalCode(String.valueOf(post_code));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        closeBill = new javax.swing.JLabel();
        billBackToRP = new javax.swing.JLabel();
        lblviewBill = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtpatient_email = new javax.swing.JTextField();
        lblerrormsg = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 25, 112));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.yellow, 3));

        closeBill.setIcon(new javax.swing.ImageIcon("/home/tholithemba/Desktop/github/java/RDPhouses/close.png")); // NOI18N
        closeBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeBillMouseClicked(evt);
            }
        });

        billBackToRP.setBackground(new java.awt.Color(53, 66, 74));
        billBackToRP.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        billBackToRP.setForeground(java.awt.Color.white);
        billBackToRP.setIcon(new javax.swing.ImageIcon("/home/tholithemba/Desktop/github/java/RDPhouses/arrow.png")); // NOI18N
        billBackToRP.setText("Back");
        billBackToRP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        billBackToRP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billBackToRPMouseClicked(evt);
            }
        });

        lblviewBill.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblviewBill.setForeground(java.awt.Color.white);
        lblviewBill.setText("View Bill");
        lblviewBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblviewBillMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Patient Email");

        lblerrormsg.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        lblerrormsg.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billBackToRP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 748, Short.MAX_VALUE)
                .addComponent(closeBill, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(lblviewBill)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtpatient_email, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblerrormsg, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeBill, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billBackToRP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(lblerrormsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtpatient_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblviewBill)
                .addGap(129, 129, 129))
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

    private void closeBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeBillMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeBillMouseClicked

    private void billBackToRPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billBackToRPMouseClicked

        backTo();
    }//GEN-LAST:event_billBackToRPMouseClicked

    private void lblviewBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblviewBillMouseClicked
        
        clearErrorMessage();
        if(!validateInput())return;
        
        user_reg.setUsername(txtpatient_email.getText());
        if(!readBill())
        {
            erroroutputMessage("no patient was found");
            return;
        }
        patientDetails();
        getPatiendAddress();
        viewBillpdf();
        JOptionPane.showMessageDialog(null, "bill was downloaded successfully");
        
        clearEmailText();
        backTo();
    }//GEN-LAST:event_lblviewBillMouseClicked

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
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel billBackToRP;
    private javax.swing.JLabel closeBill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label lblerrormsg;
    private javax.swing.JLabel lblviewBill;
    private javax.swing.JTextField txtpatient_email;
    // End of variables declaration//GEN-END:variables
}
