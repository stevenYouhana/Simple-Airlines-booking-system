//set company colors
package uflybookingsystem;
import java.awt.Color;
import java.awt.Font;


public class CompanyColors {
    public final static Color BLUE = new Color(0,20,180);
    public final static Color ORANGE = new Color(255,128,0);
    final static Font buttonFont = new Font("Arial",Font.BOLD,14);
    public static void setButtons(javax.swing.JButton button){
        button.setBackground(BLUE);
        button.setForeground(ORANGE);
        button.setFont(buttonFont);
    }
    public static void setLabelColor(javax.swing.JLabel label){
        label.setForeground(BLUE);
    }
    public static void setFrameColor(javax.swing.JFrame frame){
        frame.getContentPane().setBackground(new Color(208,208,210));
    }
    public static void setComboboxColor(javax.swing.JComboBox cbo){
        cbo.setForeground(ORANGE);
        cbo.setBackground(BLUE);
        cbo.setFont(buttonFont);
    }
}
