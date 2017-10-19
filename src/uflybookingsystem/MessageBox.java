package uflybookingsystem;

import javax.swing.JOptionPane;

public class MessageBox {
    
    public static void msg(String title,String msg,int type){
        javax.swing.JOptionPane pane = new javax.swing.JOptionPane();
        pane.showMessageDialog(null,msg,title,type);
    }
}
