package BusinessObject;
 
import java.text.ParseException;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.text.SimpleDateFormat;

public class Flight {
    private String flightNo = new String();
    private String depAirport = new String();
    private String destAirport = new String();
    double price;
    String date = new String();
    String plane = new String();
    int seatsTaken;
    
    public Flight(String flightNo,String depAirport,String destAirport,
            double price, String date, Plane plane, int seatsTaken){
        this.flightNo = flightNo;
        this.depAirport = depAirport;
        this.destAirport = destAirport;
        this.price = price;
        this.date = date;
        this.plane = plane.toString();
        this.seatsTaken = seatsTaken;
    }
    
    public Flight(String departure, String destination){
        this.depAirport = departure;
        this.destAirport = destination;
    }
    public Flight(){
        
    }
    public String getFlightNo(){
        return flightNo;
    }
    public void setFlightNo(String n){
        flightNo = n;
    }
    public String getDepAirport(){
        return depAirport;
    }
    public void setDepAirport(String s){
        depAirport = s;
    }
    public String getDestAirport(){
        return destAirport;
    }
    public void setDestAirport(String s){
        destAirport = s;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double d){
        price = d;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String d){
        date = d;
    }
    public String getPlane(){
        return plane;
    }
    public void setPlane(Plane p){
        plane = p.toString();
    }
    public int getSeatsTaken(){
        return seatsTaken;
    }
    public void setSeatsTaken(int seats){
        seatsTaken = seats;
    }
 
    @Override
    public String toString(){
        return date;
    }
}
