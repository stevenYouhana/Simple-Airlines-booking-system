/*
 * Import form that allow you to select Location and Flight files to import tot eh Location and Flight tables respectively
 */
package uflybookingsystem;

import Imports.BaseImporter;
import Imports.FlightImporter;
import Imports.LocationImporter;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ImportForm extends javax.swing.JFrame {

    /**
     * Creates new form ImportForm
     */
    InputStream in = null;
    public ImportForm() {
        initComponents();
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        txtFlights = new javax.swing.JTextField();
        btnImportFlights = new javax.swing.JButton();
        txtLocations = new javax.swing.JTextField();
        btnImportLocations = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBrowseFlight = new javax.swing.JButton();
        btnBrowseLocation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Import Files");

        txtFlights.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFlights.setName(""); // NOI18N

        btnImportFlights.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnImportFlights.setText("Import Flights");
        btnImportFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportFlightsActionPerformed(evt);
            }
        });

        txtLocations.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnImportLocations.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnImportLocations.setText("Import Locations");
        btnImportLocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportLocationsActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Select Locations File:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Select Flights File:");

        btnBrowseFlight.setText("Browse");
        btnBrowseFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseFlightActionPerformed(evt);
            }
        });

        btnBrowseLocation.setText("Browse");
        btnBrowseLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseLocationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnClose)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnImportFlights, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBrowseFlight))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImportLocations)
                                    .addComponent(txtLocations, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBrowseLocation)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseLocation))
                .addGap(24, 24, 24)
                .addComponent(btnImportLocations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFlights, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseFlight))
                .addGap(24, 24, 24)
                .addComponent(btnImportFlights)
                .addGap(18, 18, 18)
                .addComponent(btnClose)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    void chooseFile(JTextField field){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        
        if(result == JFileChooser.APPROVE_OPTION){
            field.setText(fileChooser.getSelectedFile().toString());
        }
    }
    
    

    private void btnBrowseLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseLocationActionPerformed
        chooseFile(txtLocations);
    }//GEN-LAST:event_btnBrowseLocationActionPerformed
    

    private void btnBrowseFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseFlightActionPerformed
        chooseFile(txtFlights);
    }//GEN-LAST:event_btnBrowseFlightActionPerformed

    //Location Import button action. Creates new instance of the LocationImporter
    //and calls it's run() method
    //Then the ImportFeedbackForm instance is created and displayed to see the result of the import
    private void btnImportLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportLocationsActionPerformed
        LocationImporter locationImporter = new LocationImporter(
                txtLocations.getText());
        locationImporter.run();
        ImportFeedbackForm feedbackForm = new ImportFeedbackForm(
                locationImporter.getResult());
        feedbackForm.setVisible(true);
    }//GEN-LAST:event_btnImportLocationsActionPerformed

    //Location Import button action. Creates new instance of the LocationImporter
    //and calls it's run() method
    //Then the ImportFeedbackForm instance is created and displayed to see the result of the import
    private void btnImportFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportFlightsActionPerformed
        FlightImporter flightImporter = new FlightImporter(
                txtFlights.getText());
        flightImporter.run();
        ImportFeedbackForm feedbackForm = new ImportFeedbackForm(
                flightImporter.getResult());
        feedbackForm.setVisible(true);
    }//GEN-LAST:event_btnImportFlightsActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
        MainForm f = new MainForm();
        f.setVisible(true);       
    }//GEN-LAST:event_btnCloseActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseFlight;
    private javax.swing.JButton btnBrowseLocation;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnImportFlights;
    private javax.swing.JButton btnImportLocations;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtFlights;
    private javax.swing.JTextField txtLocations;
    // End of variables declaration//GEN-END:variables
}
