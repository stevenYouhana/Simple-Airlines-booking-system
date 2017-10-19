/*
* Form that contains navigation between the other two forms
 */
package uflybookingsystem;

public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */

        
    public MainForm() {
        initComponents();
        this.setResizable(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImportForm = new javax.swing.JButton();
        btnBookingForm = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnImportForm.setText("Import Locations and Flights");
        btnImportForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportFormActionPerformed(evt);
            }
        });

        btnBookingForm.setText("Book a Flight");
        btnBookingForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingFormActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitle.setText("uFly Flight Booking System");

        btnClose.setText("Close");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnClose)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitle)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBookingForm, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImportForm, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTitle)
                .addGap(40, 40, 40)
                .addComponent(btnImportForm)
                .addGap(60, 60, 60)
                .addComponent(btnBookingForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportFormActionPerformed
           this.setVisible(false);
           ImportForm iF = new ImportForm();
           iF.setVisible(true);
    }//GEN-LAST:event_btnImportFormActionPerformed

    private void btnBookingFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingFormActionPerformed
        this.setVisible(false);
        BookingForm bF = new BookingForm();
        bF.setVisible(true);
    }//GEN-LAST:event_btnBookingFormActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBookingForm;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnImportForm;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
