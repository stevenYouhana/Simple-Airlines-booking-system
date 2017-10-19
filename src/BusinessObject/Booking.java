/*
 * do booking operations by retrieving travel dates and destination
 * from Location and Flight
 * 
 */
package BusinessObject;

public class Booking {
    //booking No AI
    private String flightNo = new String();
    private double price;
    private String cabinClass = new String();
    private int quantity = 0;
    private boolean insurance = false;
    Flight flight = new Flight();
    
    public Booking(String flightNo,double price,String cabinClass,
            int quantity, boolean insurance){
        this.flightNo = flightNo;
        this.price = price;
        this.cabinClass = cabinClass;
        this.quantity = quantity;
        this.insurance = insurance;
    }
    public Booking(Flight flight, String cabinClass){
        this.flight = flight;
        this.cabinClass = cabinClass;
    }
    public Booking(){
        
    } 
    public String getFlightNo(){
        return flightNo;
    }
    public void setFlightNo(String s){
        flightNo = s;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double d){
        price = d;
    }
    public String getCabinClass(){
        return cabinClass;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int q){
        quantity = q;
    }
    public byte getInsuranceStat(){
        return (byte)(insurance?1:0);
    }
    public boolean getInsurance(){
        return insurance;
    }
    public void setInsurance(boolean b){
        insurance = b;
    }
    public Flight getFlight(){
        return flight;
    }
}
