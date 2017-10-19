package Imports.Eng;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Steven
 */
public class Extractions {
    String[] content = new String[7];
    String line = new String();
    
    public Extractions(String line){
        
    }
    public Extractions(String[] row){
        this.content = row;
    }
    
    public String pullFlightNumber(){
        return content[0];
    }
    public String pullDeparturePort(){
        return content[1];
    }
    public String pullDestinationPort(){  
        return content[2];
    }
    public String pullPrice(){
        return content[3];
    }
    public String pullDateTime(){
        return content[4];
    }
    public String pullPlane(){
        return content[5];
    }
    public String pullSeatsTaken(){
        return content[6];
    }
    public String[] getContent(){
        return content;
    }
}
