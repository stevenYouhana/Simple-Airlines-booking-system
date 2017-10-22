/*
* This form allows you to select final booking preferences and export the booking to the database
 */
package uflybookingsystem;

import BusinessObject.Booking;
import Imports.Eng.ConvertPlane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;


public class BookingFinalForm extends javax.swing.JFrame {

   static Booking booking = new Booking();
   private DefaultComboBoxModel model;
    /**
     * Creates new form BookingFinalForm
     * sets Flight label to display the flight selected in the Booking form
     * populates the quantity combo box
     */
    public BookingFinalForm(Booking booking) {
        initComponents();
        CompanyColors.setButtons(btnBook);
        CompanyColors.setButtons(btnClose);
        CompanyColors.setFrameColor(this);
        CompanyColors.setLabelColor(jLabel2);
        CompanyColors.setLabelColor(jLabel4);
        CompanyColors.setLabelColor(jLabel5);
        CompanyColors.setLabelColor(lblTitle);
        CompanyColors.setComboboxColor(cboQuantity);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.booking = booking;
        lblFlight.setText(booking.getFlight().getFlightNo());
        createQuantityModel();
    }
    
    //creates the quantity model to populate the combo box
    //person cannot book more than 12 tickets at a time
    //check is performed to see how many tickets are available for booking
    public void createQuantityModel(){
        model = new DefaultComboBoxModel();
        int seatsLeft = ((ConvertPlane.convertPlaneEnum(
                booking.getFlight().getPlane()).getPassengerCapacity()) -
                (booking.getFlight().getSeatsTaken()));
        
        System.out.println("SeatsLeft: "+seatsLeft+"\tPlane Seats: "+
                ConvertPlane.convertPlaneEnum(
                booking.getFlight().getPlane()).getPassengerCapacity());
        
        if(booking.getFlight().getSeatsTaken() > seatsLeft){
            System.out.println("less than capacity");
            if(seatsLeft > 0){
                System.out.println("seats > 0");
                final int MAX_TICKETS = 12;
                int counter = 1;
                while(MAX_TICKETS != counter-1 & seatsLeft > counter-1){
                    model.addElement(counter);
                    counter++;
                }
            }
            else{
                model.addElement("No tickets available for this flight");
                btnBook.setEnabled(false);
            }
        }
        else{
            model.addElement("No tickets available for this flight");
            btnBook.setEnabled(false);
        }
        System.out.println("seats update: "+booking.getFlight().getSeatsTaken());
        cboQuantity.setModel(model);
    }
    
    double getInsurancePrice(){
        int qty = 0;
        if(cboQuantity.getSelectedItem().toString().matches("\\d") ||
                cboQuantity.getSelectedItem().toString().matches("\\d\\d")){
            qty = Integer.parseInt(cboQuantity.getSelectedItem().toString());
        }
        if(chkInsurance.isSelected()){
            return qty * 20;
            }
        else{
            return 0;
        }
    }
    
    double getClassPrice(){
        if(booking.getCabinClass().toString().equals("PRESTIGE_CLASS")){
           return booking.getFlight().getPrice() * 1.2;
        }
        else if(booking.getCabinClass().toString().equals("FIRST_CLASS")){
            return booking.getFlight().getPrice() * 1.35;
        }
        else {
            return booking.getFlight().getPrice();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgMessage = new javax.swing.JOptionPane();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFlight = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboQuantity = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        chkInsurance = new javax.swing.JCheckBox();
        btnBook = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitle.setText("Select Tickets");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Flight:");

        lblFlight.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFlight.setText("Not Selected");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("How many tickets would you like to get:");

        cboQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboQuantity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Would you like to get insurance?");

        chkInsurance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkInsurance.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkInsuranceItemStateChanged(evt);
            }
        });

        btnBook.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBook.setText("Book the Flight");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClose)
                            .addComponent(btnBook)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkInsurance))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(lblFlight))
                                    .addGap(64, 64, 64)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboQuantity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFlight)
                    .addComponent(cboQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkInsurance)
                    .addComponent(jLabel5))
                .addGap(46, 46, 46)
                .addComponent(btnBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //action for the checkbox to set insurance
    private void chkInsuranceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkInsuranceItemStateChanged

    }//GEN-LAST:event_chkInsuranceItemStateChanged

    //action for the Booking button
    //final booking details are set and the object is exported to the Booking table
    //The message is displayed that the booking was added successfuly
    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        booking.setFlightNo(booking.getFlight().getFlightNo());
//        booking.getFlight().setSeatsTaken(booking.getFlight().getSeatsTaken()+
//                Integer.parseInt(cboQuantity.getSelectedItem().toString()));
        booking.setPrice(getClassPrice()+getInsurancePrice());
        booking.setQuantity(Integer.parseInt(
                cboQuantity.getSelectedItem().toString()));
        booking.setInsurance(chkInsurance.isSelected());
        //update seatsTaken from flight
        booking.getFlight().setSeatsTaken(booking.getFlight().getSeatsTaken()+
                Integer.parseInt(cboQuantity.getSelectedItem().toString()));
        DatabaseOperations.AddBooking(booking);
        DatabaseOperations.UpdateFlight(booking.getFlight());
        MessageBox.msg("Booking","Booking added succesfully",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        if(cboQuantity.getSelectedItem().toString().equals("12")){
            model = new DefaultComboBoxModel();
            model.removeAllElements();
            model.addElement("Maximum tickets purchased");
            cboQuantity.setModel(model);
        }
        else{
            createQuantityModel();
        }
    }//GEN-LAST:event_btnBookActionPerformed

    //action for closing the form
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

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
            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingFinalForm(booking).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnClose;
    private javax.swing.JComboBox cboQuantity;
    private javax.swing.JCheckBox chkInsurance;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblFlight;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JOptionPane msgMessage;
    // End of variables declaration//GEN-END:variables
}
