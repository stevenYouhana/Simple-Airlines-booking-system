package Imports.Eng;
import static Imports.Eng.AirportCodes.airportCodes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FlightNumbers {
    static ArrayList<String> flightNumbers = new ArrayList();
    static String path = new String();
    
    public FlightNumbers(String path){
        this.path = path;
    }
    public FlightNumbers(){
        
    }
    //REMOVE HARD CODE
    public static ArrayList<String> getCurrentFlightNumbers(){
        try(BufferedReader flights = new BufferedReader(
                new FileReader("../Flights.txt"))){
            int c = 0;
            String flightNumber = "";
            while((c = flights.read()) != -1){
                flightNumber += (char)c;
            }
            for(int i=flightNumber.length(); i>-1; i--){
                if(i>5 && flightNumber.substring(i-5,i).matches("[NZ]+\\d{3}")){
                    flightNumbers.add(flightNumber.substring(i-5,i));
                }
            }
        }
        catch(IOException ioe){
            System.out.println("getCurrentFlightNumbers: "+ioe.getMessage());
        }
        return flightNumbers;
    }
}
