package Imports.Eng;
import static Imports.Eng.AirportCodes.airportCodes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import uflybookingsystem.MessageBox;

public class FlightNumbers {
    static ArrayList<String> flightNumbers = new ArrayList();
    static String path = new String();
    
    public FlightNumbers(String path){
        this.path = path;
    }
    public FlightNumbers(){
        
    }
    public static ArrayList<String> getCurrentFlightNumbers(){
        return flightNumbers;
    }
    public static void addFlightNumbers(String filePath){
        flightNumbers.clear();
        try(BufferedReader inputFile = new BufferedReader(
                new FileReader(filePath))){
            int c = 0;
            String line = "";
            while((c = inputFile.read()) != -1){
                line += (char)c;
            }
            for(int i=line.length(); i>-1; i--){
                if(i>5 && line.substring(i-5,i).matches("[NZ]+\\d{3}")){
                    flightNumbers.add(line.substring(i-5,i));
                }
            }
        }
        catch(IOException ioe){
            MessageBox.msg("File Path issue", ioe.getMessage(), javax.swing.
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
