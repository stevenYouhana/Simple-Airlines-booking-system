//convert date to sql format
package Imports.Eng;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Steven
 */
public class ToSQL_Time {
    String storedDate = new String();   //01/05/2017 12:05:00
    final String EXTRACT_DATE_ONLY = "\\d\\d[/]\\d\\d[/]\\d\\d\\d\\d";
    final String EXTRACT_TIME_ONLY = "(\\d{2}[:]){2}\\d{2}";
    
    public ToSQL_Time(String storedDate){
        this.storedDate = storedDate;
    }
    String extractDateOnly(){
        String out = ""; 
        Pattern pattern = Pattern.compile(EXTRACT_DATE_ONLY);
        Matcher matcher = pattern.matcher(storedDate);
        
        while(matcher.find()){
            out += matcher.group(0);
        }
        return out;
    }
    String extractTimeOnly(){
        String out = ""; 
        Pattern pattern = Pattern.compile(EXTRACT_TIME_ONLY);
        Matcher matcher = pattern.matcher(storedDate);
        
        while(matcher.find()){
            out += matcher.group(0);
        }
        return out;
    }
    public String rearrangedValue(){
        String day = extractDateOnly().substring(0,extractDateOnly().indexOf("/"));
        String month = extractDateOnly().substring(extractDateOnly().indexOf("/")+1,
                extractDateOnly().indexOf("/")+3);
        String year = extractDateOnly().substring(extractDateOnly().indexOf("/")+4,
                extractDateOnly().length());
        
        return year+"-"+month+"-"+day+" "+extractTimeOnly();
    }
}
