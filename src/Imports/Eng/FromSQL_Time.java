package Imports.Eng;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Steven
 */
public class FromSQL_Time {
    String sqlFormat = new String();   //              2017-05-01 12:05:00
                                       //stored        01/05/2017 12:05  
    final String EXTRACT_DATE_ONLY = "\\d{4}([-]+\\d\\d){2}";
    final String EXTRACT_TIME_ONLY = "\\d{2}[:]+\\d{2}";
    
    public FromSQL_Time(String sqlFormat){
        this.sqlFormat = sqlFormat;
    }
    public String extractDateOnly(){
        String out = ""; 
        Pattern pattern = Pattern.compile(EXTRACT_DATE_ONLY);
        Matcher matcher = pattern.matcher(sqlFormat);
        
        while(matcher.find()){
            out += matcher.group(0);
        }
        return out;
    }
    
    public String extractTimeOnly(){
        String out = ""; 
        Pattern pattern = Pattern.compile(EXTRACT_TIME_ONLY);
        Matcher matcher = pattern.matcher(sqlFormat);
        
        while(matcher.find()){
            out += matcher.group(0);
        }
        return out;
    }
    public String rearrangedValue(){
        String day = extractDateOnly().substring(extractDateOnly().length()-2,
                extractDateOnly().length());
        
        String month = extractDateOnly().substring(
                extractDateOnly().indexOf('-')+1,
                extractDateOnly().indexOf('-')+3);
        
        String year = extractDateOnly().substring(0,4);

        return day+"/"+month+"/"+year+" "+extractTimeOnly();
    }
}
