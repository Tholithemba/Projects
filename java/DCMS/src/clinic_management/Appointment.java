/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic_management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author tholithemba
 */
public class Appointment extends javax.swing.JFrame {

    private static String patient_email;
    private String doctor_name;
    private static int patient_id;
    private int doctor_id;
    
    UpdateDataFields udf = new UpdateDataFields();
    ReceptionistHome recept_home = new ReceptionistHome();
    DBInputValidation dbInputval = new DBInputValidation();
    ValidateBookings valid_booking = new ValidateBookings();
    DateFormat date_format = new DateFormat();
    
    
    /**
     * Creates new form Appointment
     */
    public Appointment() {
        initComponents();
        selectedDoctor();
    }
    //set patient email
    public void setPatientEmail(String set_pemail){
        patient_email = set_pemail;
    }
    //get patient email
    public String getPatientEmail(){
        return patient_email;
    }

    //set doctor name
    public void setDoctorName(String set_dr_name){
        this.doctor_name = set_dr_name;
    }
    //get doctor name
    public String getDoctorName(){
        return doctor_name;
    }
    //set patient id
    public void setPatientid(int set_p_id){
        patient_id = set_p_id;
    }
    //get patient id
    public int getPatientid(){
        return patient_id;
    }

    //set doctor id
    public void setDoctorid(int set_r_id){
        this.doctor_id = set_r_id;
    }
    //get doctor id
    public int getDoctorid(){
        return doctor_id;
    }
    //initialise input values from the user
    private void setValues(){
        
        setPatientEmail(txt_pateint_email.getText());
        date_format.setDate(jCalendar2.getDate());
        
        valid_booking.setCheckin((String)cboxCheck_in.getSelectedItem());
        valid_booking.setCheckout((String)cboxCheck_out.getSelectedItem());
    }
    //validate slots to avoid double bookings
    private boolean validateSlotSelection(){
        
        boolean valid_selection = true;
        String checkin = valid_booking.getCheckin();
        String checkout = valid_booking.getCheckout();
        
        if(checkin.equals(checkout) || (checkin.compareTo(checkout) > 0))
            return errorOutputStatement("Please select time slot properly");
        
        return valid_selection;
    } 
    //validate email address as an input from the user
    private boolean validateInput(){
        
        if(getPatientEmail().equals("")){
            return errorOutputStatement("Enter patient email address");
        }else if(lbldate_chosen.getText().equals("")){
            return errorOutputStatement("show chosen date by clicking the button");
        }
        
        return true;
    }
    //display error message
    public boolean errorOutputStatement(String error_msg){
        
        warn_text.setText(error_msg);
        clearCells();
        
        return false;
    }
    
    private void clearCells(){
        txt_pateint_email.setText("");
        txt_recept_email.setText("");
        lbldate_chosen.setText("");
    }
    
    //display warning text to guide the user
    private void clearWarningMessage(){
        warn_text.setText("");
    }
    
    //check if patient email exist in the database
    public boolean patientEmailExist(){
        
        boolean exist;
        UserRegistration user_reg = new UserRegistration();
        String query = 
                    "select* from PATIENT where EMAIL=?";
        
        user_reg.setUsername(getPatientEmail());
        udf.setHeadingName("PATIENT_ID");
        exist  = dbInputval.checkUsername(query);
        
        if(!exist){
            clearCells();
            return errorOutputStatement("patient email address does not exist");
        }
        setPatientid(dbInputval.getUserID());//set patient id
        return exist;
    }
    
    //check if doctor practice number exist from database
    private boolean doctorPracticeNo(){
        
        boolean exist;
        UserRegistration user_reg = new UserRegistration();
        String practiceNo = returnPracticeNo(getDoctorName());
        
        String query = 
                    "select* from DOCTOR where PRACTICE_NUMBER=?";
        
        user_reg.setUsername(practiceNo);
        udf.setHeadingName("DOCTOR_ID");
        exist  = dbInputval.checkUsername(query);
        if(!exist){
            JOptionPane.showMessageDialog(null, "Doctor's id problem, please "
                    + "contact the Administrator");
            return exist;
        }
        setDoctorid(dbInputval.getUserID());
        return exist;
    }
    //given doctor name rturn doctor practice number
    public String returnPracticeNo(String name)
    {
        String practiceNo = "";
        
        switch (name) 
        {
            case "Dr Klein": practiceNo = "A525";
            break;
            
            case "Dr Mkhize": practiceNo = "A524";
            break;
            
            case "Dr Adams": practiceNo = "A526";
            break;
            
            default:JOptionPane.showMessageDialog(null,
                    "option chosen is not available please"
                            + " contact the administrator");
            break;
        }
        return practiceNo;
    }
    
    //dislay chosen date
    private void showDate(){
        
        Date d = new Date();
        
        try{
            d= date_format.sdf.parse(date_format.sdf.format(jCalendar2.getDate()));
        } catch (ParseException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbldate_chosen.setText(date_format.sdf.format(d));
    }
    
    //saturday time validation as it is different from weekly time
    private boolean saturday(){
        
        boolean is_saturday;
        boolean valid_hours;
        Date d = date_format.getDate();
        is_saturday = valid_booking.isSaturday(d);
        if(is_saturday){
            valid_hours = valid_booking.validSaturdayHours();
            
            if(!valid_hours){
               errorOutputStatement("Invalid slot, working hours on Saturday"
                       + " starts at 09:00 and ends at 13:00");
               return is_saturday;
            }
            
            String query = queryStatement();
            
            if(createAppointment(query)){
                JOptionPane.showMessageDialog(null,"appointment created successfully...");
                return is_saturday;
            }
        }
        
        return is_saturday;
    }
    
    //weekly time validation
    private boolean duringtheWeek(){
        
        boolean valid;
        valid  = valid_booking.validLunchSlot();
        
        if(!valid)
           return errorOutputStatement("Lunch time, Lunch starts at 12:00 and "
                   + "ends at 13:00");
  
        valid = valid_booking.isSlotBooked(bookedDaySlot());
        
        if(!valid)
           return errorOutputStatement("Slot chosen is already booked");
  
        String query = queryStatement();
        valid = createAppointment(query);//create appointment
        
        if(valid){
            JOptionPane.showMessageDialog(null,"appointment created successfully...");
        }
        return valid;
    }
    
    //set query statemt
    private String queryStatement(){
        
        String query_statement;
        query_statement = "insert into APPOINTMENT(DATE,CHECKIN,CHECKOUT"
        + ",DOCTOR_ID,PATIENT_ID)values(?,?,?,?,?)";
        
        if(recept_home.getReceptionistid() != 0)
            query_statement = 
            "insert into APPOINTMENT(DATE,CHECKIN,CHECKOUT"
            +",DOCTOR_ID,PATIENT_ID,RECEPTIONIST_ID)values(?,?,?,?,?,?)";
        
        return query_statement;
    }
    
    //add all booked slot to arraylist
    public ArrayList<String> bookedDaySlot(){
        
        PreparedStatement ps;
        ResultSet rs;
        
        ArrayList<String> time_booked = new ArrayList();
        String query_statement = "SELECT CHECKIN, CHECKOUT FROM APPOINTMENT"
                + " WHERE DATE=? ";

        String appointment_date = date_format.simpl_date.format(date_format.getDate());
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, appointment_date);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                time_booked.add(rs.getString("CHECKIN"));
                time_booked.add(rs.getString("CHECKOUT"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ValidateBookings.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return time_booked;
    }
    
    //create patient appointment
    private boolean createAppointment(String query_statement){
        PreparedStatement ps;
        verifySlot();
        boolean created = false;
        String appointment_date = date_format.simpl_date.format(date_format.getDate());
        try{
            ps = Connect2database.getConnection().prepareStatement(query_statement);
            
            ps.setString(1, appointment_date);
            ps.setString(2, valid_booking.getCheckin());
            ps.setString(3, valid_booking.getCheckout());
            ps.setInt(4, getDoctorid());
            ps.setInt(5, getPatientid());
            if(recept_home.getReceptionistid() != 0)
                ps.setInt(6, recept_home.getReceptionistid());
            
            if(ps.executeUpdate() > 0){
                created = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return created;
    }
    
    //get doctor schedule
    private HashSet<String> doctorSchedule(){
        
        PreparedStatement ps;
        ResultSet rs;
        HashSet<String> date_set = new HashSet<>();
        String query = "select DATE,CHECKIN, CHECKOUT "
                + "from APPOINTMENT where DATE>=? and DOCTOR_ID=?";
        String todays_date = date_format.simpl_date.format(new java.util.Date());
        
        try {
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setString(1, todays_date);
            ps.setInt(2, getDoctorid());
            
            rs = ps.executeQuery();
            
            while(rs.next())
                date_set.add(rs.getString("DATE"));

        } catch (SQLException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date_set;
    }
    
    //get booked time slot
    private ArrayList<String> bookedTime(){
        
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String> date_set = new ArrayList<>();
        String query = "select DATE,CHECKIN, CHECKOUT "
                + "from APPOINTMENT where DATE=? and DOCTOR_ID=?";
        String appointment_date = date_format.simpl_date.format(date_format.getDate());
        try {
            ps = Connect2database.getConnection().prepareStatement(query);
            ps.setString(1, appointment_date);
            ps.setInt(2, getDoctorid());
            
            rs = ps.executeQuery();
            
            while(rs.next())
                date_set.add(rs.getString("CHECKIN")+" - "+rs.getString("CHECKOUT"));

        } catch (SQLException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date_set;
    }
    
    //display schedule for each doctor
    public boolean showTableSlot(HashSet<String> sort_array){
        
        boolean valid_booking = true;
        String[] sorted_array = sortArray(sort_array).clone();
        int r;
        int i;
        int arr_size;
        
        ArrayList<String> time_booked ;
        String[] sorted_time ;
        for(r = 0; r < sorted_array.length; r++){

            date_format.setDateFormat(sorted_array[r]);
            time_booked =  (ArrayList<String>)bookedTime().clone();
            sorted_time = sortArray(time_booked).clone();
            
            arr_size = sorted_time.length;
            
            if(arr_size != 0){
                for(i = 0; i < arr_size; i++)
                    DisplaySchedule(sorted_time[i]);
            }
        }

        return valid_booking;
    }
    

    //sort time 
    private String[] sortArray(HashSet<String> sort_array){
        
        Object[] object_sort = sort_array.toArray();
        Arrays.sort(object_sort);
        String[] sorted_array = Arrays.copyOf(object_sort, object_sort.length , String[].class);
        
        return sorted_array;
    }
    
    //sort time input datatype is different from above sortArray
    private String[] sortArray(ArrayList<String> sort_array){
        
        Object[] object_sort = sort_array.toArray();
        Arrays.sort(object_sort);
        String[] sorted_array = Arrays.copyOf(object_sort, object_sort.length , String[].class);
        
        return sorted_array;
    }
    
    //add booked slot to the table
    private void DisplaySchedule(String check_inCheck_out ){
        
        DefaultTableModel schedule = (DefaultTableModel)doctorsSchedule.getModel();
        Object[] row = new Object[3];
        
        row[0] = date_format.day.format(date_format.getDate());
        row[1] = date_format.full_date.format(date_format.getDate());
        row[2] = check_inCheck_out;
        
        schedule.addRow(row); 
    }
    
    //clear old schedule
    private void clearSchedule(){
        
        DefaultTableModel table = (DefaultTableModel)doctorsSchedule.getModel();
        
        int row ;
        for(row = table.getRowCount()-1; row >= 0; row--)
            table.removeRow(row);
    }
    
    //user select doctor for an appointment
    private void selectedDoctor(){
        
        String doctors_name = (String)cboxDr_name.getSelectedItem();
        
        switch (doctors_name) {
            case "Dr Adams":
                show_doctorsName.setText("Dr Adams Schedule");
                break;
            case "Dr Klein":
                show_doctorsName.setText("Dr Klein Schedule");
                break;
            case "Dr Mkhize":
                show_doctorsName.setText("Dr Mkhize Schedule");
                break;
            default:JOptionPane.showMessageDialog(null, "Please contact the administrator");
                break;
        }
        
        setDoctorName(doctors_name);//set selected doctor
    }
    
    //verify if user chose the correct slot
    private void verifySlot()
    {
        String Doctor_name = getDoctorName();
        String date = date_format.full_date.format(date_format.getDate()) ;
        String day = date_format.day.format(date_format.getDate());
        String time =
                valid_booking.getCheckin()+" - "+valid_booking.getCheckout();
        JOptionPane.showMessageDialog(null,"Doctor's name: "+ Doctor_name+"\n"+
                "Date: "+date+"\n"+"Day: "+day+"\n"+"Time: "+time);
    }
    
    //back to active user home page
    private void backTo(){
        
        Login back_to = new Login();
        
        if(back_to.getActivePatien())
            new PatientHome().setVisible(true);
        else if(back_to.getActiveRecept())
            new ReceptionistHome().setVisible(true);
        else
            new DoctorHome().setVisible(true);
        
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
        closeAppointment = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doctorsSchedule = new javax.swing.JTable();
        txt_pateint_email = new javax.swing.JTextField();
        CreateAppointment = new javax.swing.JButton();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        cboxCheck_in = new javax.swing.JComboBox<>();
        cboxCheck_out = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_recept_email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboxDr_name = new javax.swing.JComboBox<>();
        lbldate_chosen = new java.awt.Label();
        btnCreateAppointment = new javax.swing.JButton();
        label1 = new java.awt.Label();
        warn_text = new javax.swing.JLabel();
        show_doctorsName = new java.awt.Label();
        ApointBackToRP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(25, 25, 112));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.yellow, 3));

        closeAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/close.png"))); // NOI18N
        closeAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeAppointmentMouseClicked(evt);
            }
        });

        doctorsSchedule.setBackground(java.awt.Color.blue);
        doctorsSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Date", "Time"
            }
        ));
        jScrollPane1.setViewportView(doctorsSchedule);

        CreateAppointment.setBackground(new java.awt.Color(25, 25, 112));
        CreateAppointment.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        CreateAppointment.setForeground(java.awt.Color.white);
        CreateAppointment.setText("show date selected");
        CreateAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAppointmentActionPerformed(evt);
            }
        });

        jCalendar2.setBackground(java.awt.Color.green);
        jCalendar2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.yellow));

        cboxCheck_in.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        cboxCheck_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00" }));

        cboxCheck_out.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        cboxCheck_out.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Patient Email");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Receptionist Email");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Select Doctor");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Check in");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Check out");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.red);
        jLabel6.setText("*");

        cboxDr_name.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        cboxDr_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dr Adams", "Dr Klein", "Dr Mkhize" }));
        cboxDr_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxDr_nameActionPerformed(evt);
            }
        });

        lbldate_chosen.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbldate_chosen.setForeground(java.awt.Color.green);

        btnCreateAppointment.setBackground(java.awt.Color.blue);
        btnCreateAppointment.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnCreateAppointment.setForeground(java.awt.Color.white);
        btnCreateAppointment.setText("Create");
        btnCreateAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateAppointmentMouseClicked(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label1.setForeground(java.awt.Color.white);
        label1.setText("APPOINTMENT");

        warn_text.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        warn_text.setForeground(java.awt.Color.red);

        show_doctorsName.setAlignment(java.awt.Label.CENTER);
        show_doctorsName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        show_doctorsName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        show_doctorsName.setForeground(java.awt.Color.white);

        ApointBackToRP.setBackground(new java.awt.Color(53, 66, 74));
        ApointBackToRP.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        ApointBackToRP.setForeground(java.awt.Color.white);
        ApointBackToRP.setText("Back");
        ApointBackToRP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 66, 74), 0));
        ApointBackToRP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ApointBackToRPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                        .addComponent(show_doctorsName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ApointBackToRP))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(227, 227, 227))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(CreateAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbldate_chosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jCalendar2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(closeAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6))
                                    .addComponent(txt_pateint_email)
                                    .addComponent(txt_recept_email)
                                    .addComponent(cboxDr_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboxCheck_in, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(cboxCheck_out, 0, 118, Short.MAX_VALUE)))
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnCreateAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(warn_text, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(closeAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ApointBackToRP)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(show_doctorsName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_pateint_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_recept_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboxDr_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboxCheck_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxCheck_out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CreateAppointment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbldate_chosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(warn_text, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateAppointment)
                        .addGap(25, 25, 25))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeAppointmentMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeAppointmentMouseClicked

    private void CreateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateAppointmentActionPerformed
        
        clearWarningMessage();
        showDate();
    }//GEN-LAST:event_CreateAppointmentActionPerformed

    private void btnCreateAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateAppointmentMouseClicked
        
        clearWarningMessage();
        setValues();
        if(!validateInput())return;
        Date d = date_format.getDate();
        if(!valid_booking.validChosenDay(d)){
            errorOutputStatement("Bookind today or previous day is not possible");
            return;
        }
        if(!validateSlotSelection())return;
        if(!patientEmailExist())return;
        
        if(valid_booking.isSunday(d)){
            errorOutputStatement("Doctors are not availbale on Sunday");
            return;
        }
        
        if(saturday())return;

        if(!duringtheWeek())return;
        
        clearCells();
        clearWarningMessage();
    }//GEN-LAST:event_btnCreateAppointmentMouseClicked

    private void cboxDr_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxDr_nameActionPerformed
        
        clearSchedule();
        selectedDoctor();
        if(!doctorPracticeNo())return;
        showTableSlot(doctorSchedule());
    }//GEN-LAST:event_cboxDr_nameActionPerformed

    private void ApointBackToRPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApointBackToRPMouseClicked
        
        backTo();
    }//GEN-LAST:event_ApointBackToRPMouseClicked

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
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Appointment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ApointBackToRP;
    private javax.swing.JButton CreateAppointment;
    private javax.swing.JButton btnCreateAppointment;
    private javax.swing.JComboBox<String> cboxCheck_in;
    private javax.swing.JComboBox<String> cboxCheck_out;
    private javax.swing.JComboBox<String> cboxDr_name;
    private javax.swing.JLabel closeAppointment;
    private javax.swing.JTable doctorsSchedule;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label lbldate_chosen;
    private java.awt.Label show_doctorsName;
    private javax.swing.JTextField txt_pateint_email;
    private javax.swing.JTextField txt_recept_email;
    private javax.swing.JLabel warn_text;
    // End of variables declaration//GEN-END:variables
}
