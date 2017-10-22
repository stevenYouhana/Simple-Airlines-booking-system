//check current Location.txt content
package Imports.Eng;

import static Imports.Eng.FlightNumbers.getCurrentFlightNumbers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import uflybookingsystem.MessageBox;

public class AirportCodes {
    static ArrayList<String> airportCodes = new ArrayList();
    static String path = new String();
    
    public AirportCodes(String path){
        this.path = path;
    }
    public AirportCodes(){
       
    }
    public static ArrayList<String> getCurrentAirportCodes(){
        return airportCodes;
        
    }
    public static void addLocation(String filePath){
        airportCodes.clear();
        try(BufferedReader locations = new BufferedReader(
                new FileReader(filePath))){
            int c = 0;
            String airportCode = "";
            while((c = locations.read()) != -1){
                airportCode += (char)c;
                for(int i=0; i<airportCode.length(); i++){
                    if(airportCode.substring(i).matches("[,][A-Z]{3}")){
                        airportCodes.add(airportCode.substring(i+1));
                    }
                }
            }
        }
        catch(IOException ioe){
            MessageBox.msg("File Path issue", ioe.getMessage(), javax.swing.
                    JOptionPane.ERROR_MESSAGE);       
        }        
    }
}
