/*
 * Initial booking form that allows you to select travel destinations and dates
 * as well as selecting the cabin class 
 */
package uflybookingsystem;

import BusinessObject.Booking;
import BusinessObject.CabinClass;
import BusinessObject.Flight;
import BusinessObject.Location;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;


public class BookingForm extends javax.swing.JFrame {
    Booking booking;
    Flight flight = new Flight();
    Date flightDate;
    private DefaultComboBoxModel model;
    String selectedLocation = new String();
    /**
     * Creates new form BookingForm
     * and populates cabin class, from and to combo Boxes
     */
    public BookingForm() {
        initComponents();
        this.setResizable(false);
        pnlBookTheFlight.setVisible(false);
        cboTo.setModel(new DefaultComboBoxModel());
        createFromLocationsModel();
        cboDate.setEnabled(false);
    }

    //Creates cabin class model for populating the combo box based on the values in the enumeration
    public void createCabinClassModel(){
        //CALLED IN CREATE DATES METHOD
        model = new DefaultComboBoxModel();
        model.addElement(CabinClass.ECONOMY_CLASS);
        model.addElement(CabinClass.PRESTIGE_CLASS);
        model.addElement(CabinClass.FIRST_CLASS);
        cboClass.setModel(model);
    }
    
    //Creates To model for populating the combo box based on the values in location list
    //the location selected in the from combo box should not be displayed in the to combo box
    public void createToLocationsModel(){
        model = new DefaultComboBoxModel();
        for(Location location : DatabaseOperations.getAllLocations()){
            if(!(location.toString().equals(selectedLocation))){
                model.addElement(location);
            }
        }
        cboTo.setModel(model);
    }

    //Cr eates From model for populating the combo box based on the values in location list
    public void createFromLocationsModel(){
        model = new DefaultComboBoxModel();
        for(Location location : DatabaseOperations.getAllLocations()){
            model.addElement(location);
        }
        cboFrom.setModel(model);
        cboFrom.setSelectedIndex(0);
    }
    
    //this method populate travel dates and times available to the Dates combo box 
    //based on the values selected in the To and From combo boxes
    public void createDatesModel(){
        model = new DefaultComboBoxModel();
        try{
            for(Flight flight : DatabaseOperations.getAllFlights(
                    cboFrom.getSelectedItem().toString(),cboTo.getSelectedItem().
                            toString())){
                model.addElement(flight);
            }
        }
        catch(Exception e){
            MessageBox.msg("Error", e.getStackTrace().toString(),
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            e.getMessage();
        }
        cboDate.setModel(model);
        if(model.getSize() != 0){
            pnlBookTheFlight.setVisible(true);
            createCabinClassModel();
        }
        else{
            model.addElement("No flights available");
            cboDate.setModel(model);
        }
        cboDate.setEnabled(true);
    }
    void setSelectedFromLocation(Location location){
        selectedLocation = location.toString();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgMessage = new javax.swing.JOptionPane();
        lblTitle = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboTo = new javax.swing.JComboBox();
        cboFrom = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        pnlBookTheFlight = new javax.swing.JPanel();
        cboClass = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        btnBook = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        cboDate = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitle.setText("Booking");

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("To:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("From:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Date you want to travel:");

        cboTo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboTo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboToItemStateChanged(evt);
            }
        });

        cboFrom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboFrom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFromItemStateChanged(evt);
            }
        });
        cboFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFromActionPerformed(evt);
            }
        });

        cboClass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Choose the class:");

        btnBook.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBook.setText("Book");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBookTheFlightLayout = new javax.swing.GroupLayout(pnlBookTheFlight);
        pnlBookTheFlight.setLayout(pnlBookTheFlightLayout);
        pnlBookTheFlightLayout.setHorizontalGroup(
            pnlBookTheFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookTheFlightLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(pnlBookTheFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBookTheFlightLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBookTheFlightLayout.createSequentialGroup()
                        .addComponent(cboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        pnlBookTheFlightLayout.setVerticalGroup(
            pnlBookTheFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookTheFlightLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBookTheFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBook))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnClose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        cboDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "no times for specified locations" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBookTheFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(jLabel2)
                            .addComponent(cboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cboTo, 0, 300, Short.MAX_VALUE))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboDate, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(209, 209, 209)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(cboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBookTheFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //action for the Book button that sets price, cabin class and flight number for the booking.
    //It then calculates ticket price based on the cabin class selected.
    //Lastly, the BookingFinalForm instance is created
    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        flight = (Flight)cboDate.getSelectedItem();
        booking = new Booking(flight,cboClass.getSelectedItem().toString());
        BookingFinalForm bookingFinalForm = new BookingFinalForm(booking);
        bookingFinalForm.setVisible(true);
    }//GEN-LAST:event_btnBookActionPerformed

    //every time the To combo box is updated the Date combo box should be updated
    private void cboToItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboToItemStateChanged
       
    }//GEN-LAST:event_cboToItemStateChanged

    //every time the From combo box is updated the Date combo box should be updated as well
    //and the To combo box should be updated listing all the locations but the one selected in the From combo box
    private void cboFromItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFromItemStateChanged

    }//GEN-LAST:event_cboFromItemStateChanged

    //Search button action performed that uncovers the Book the flight panel to select the cabin class and continue to the next form
    //also fligth should be obtained from the date combo box, as it contains flight objects
    //Then the flight information should be obtained for the flights fitting the criteria selected by user 
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        createDatesModel();
    }//GEN-LAST:event_btnSearchActionPerformed

    //close button action
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
        MainForm f = new MainForm();
        f.setVisible(true);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void cboFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFromActionPerformed
        selectedLocation = cboFrom.getSelectedItem().toString();
        createToLocationsModel();
    }//GEN-LAST:event_cboFromActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cboClass;
    private javax.swing.JComboBox cboDate;
    private javax.swing.JComboBox cboFrom;
    private javax.swing.JComboBox cboTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JOptionPane msgMessage;
    private javax.swing.JPanel pnlBookTheFlight;
    // End of variables declaration//GEN-END:variables
}
