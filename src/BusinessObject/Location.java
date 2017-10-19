package BusinessObject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Location {
    private String city = new String();
    private String portCode = new String();
    
    public Location(String city, String portCode){
        this.city = city;
        this.portCode = portCode;
        System.out.println("City: "+city+" Code: "+portCode);
    }
    public Location(){
        
    }
    //DO ALL WORK AT FORM SIDE I GUESS...
    @Override
    public String toString(){
        return city+" "+portCode;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getAirportCode(){
        return portCode;
    }
    
    public void setCity(String s){
        city = s;
    }
    
    public void setAirportCode(String s){
        portCode = s;
    }
}
