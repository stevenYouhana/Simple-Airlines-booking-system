/*
    holding Plane types with their passenger capacities
 */
package BusinessObject;

public enum Plane {
    AIRBUSA350(270),
    AIRBUSA280(500),
    BOEING737(215),
    BOEING747(460);
    
    Plane(int passengerCapacity){
        this.passengerCapacity = passengerCapacity;
    }
    private int passengerCapacity;
    
    public int getPassengerCapacity(){
        return passengerCapacity;
    }
}
