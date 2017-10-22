package Imports;

import BusinessObject.Location;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import uflybookingsystem.DatabaseOperations;

public class LocationImporter extends BaseImporter {
    private boolean allFine = true;
    public LocationImporter(String fileName){
        super(fileName);
    }
    //extract only content
    String[] checkContent(String[] lines, int lineNo){
        String[] content = new String[lines.length-1];
        int count = 0;
        for(String s : lines){
            if(count > 0){
                content[count-1] = s;
            }
            count++;
        }
        for(String c : content){
            if(c.contains(",")){
                if(extractCode(c).contains(",")){    
                    try{
                        Throwable badContent = new Throwable(
                                lineNo+": content has incorrect values");
                        super.getResult().incrementFailedRows();
                        allFine = false;
                        throw badContent;
                    }
                    catch(Throwable e){
                        super.getResult().getErrorMessages().
                                add(e.getMessage());
                    }
                    //check airport code format
                    final String CODE_FORMAT = "\\D\\D\\D";
                    if(!(c.substring(c.indexOf(",")+1).matches(CODE_FORMAT))){
                        try{
                            Throwable wrongHeaders = new Throwable(
                                    lineNo+": Check airport code!");
                            super.getResult().incrementFailedRows();
                            allFine = false;
                            throw wrongHeaders;
                        }
                        catch(Throwable e){
                            super.getResult().getErrorMessages().
                                add(e.getMessage());
                            System.out.println(e.getMessage());
                        }
                    }  
                }
            }
        }
        return content;
    }
    public String[] prepColumns(String[] lines,int lineNo){
        final int COLUMN_NO = 2;
        String[] columns = new String[COLUMN_NO];
        if(lines[0].contains(",")){
        columns[0] = lines[0].substring(0,
                lines[0].indexOf(','));
        columns[1] = lines[0].substring((lines[0].indexOf(',')+1),
                (lines[0].length()));
        }
        else{
            try{
                Throwable wrongHeaders = new Throwable(
                        lineNo+": Single field found!");
                super.getResult().incrementFailedRows();
                allFine = false;
                throw wrongHeaders;
            }
            catch(Throwable e){
                super.getResult().getErrorMessages().add(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        return columns;
    }
    void checkFormating(String line, String[] columns,
            String city, String portCode, int lineNo){
        //check for empty fields
        if(!(line.isEmpty())){
            if(columns[0].equals("")){
                try{
                    Throwable emptyLine = new Throwable(
                            lineNo+": has an empty City field");
                    super.getResult().incrementFailedRows();
                    allFine = false;
                    throw emptyLine;
                }
                catch(Throwable e){
                    super.getResult().getErrorMessages().add(e.getMessage());
                    System.out.println(e.getMessage());
                }
            }
            if(columns[1].equals("")){
                try{
                    Throwable emptyLine = new Throwable(
                            lineNo+": Empty Airport code field");
                    super.getResult().incrementFailedRows();
                    allFine = false;
                    throw emptyLine;
                }
                catch(Throwable e){
                    super.getResult().getErrorMessages().add(e.getMessage());
                    System.out.println(e.getMessage());
                }      
            }
        }
        //check if there are more elements in first line
        if(line.substring(line.indexOf(',')+1,
                line.length()).contains(",")){
            try{
                Throwable wrongHeaders = new Throwable(
                        lineNo+": Headers do not match");
                super.getResult().incrementFailedRows();
                allFine = false;
                throw wrongHeaders;
            }
            catch(Throwable e){
                super.getResult().getErrorMessages().add(e.getMessage());
                System.out.println("COLUMN NO: "+e.getMessage());
            }
        }
        //a check for correct columns
        if(!(columns[0].equals(city) && 
                columns[1].equals(portCode))){
            try{   
                super.getResult().incrementFailedRows();
                Throwable wrongHeaders = new Throwable(
                        lineNo+": Headers do not match");
                allFine = false;
                throw wrongHeaders;
            }
            catch(Throwable e){
                super.getResult().getErrorMessages().add(e.getMessage());
                System.out.println("HEADERS CAUGHT: "+e.getMessage());
            }
        }
}
    String extractCity(String c){
        if(!(c.isEmpty())){
            return c.substring(0,c.indexOf(","));
        }
        return null;
    }
    String extractCode(String c){
        if(!(c.isEmpty())){
            return c.substring(c.indexOf(",")+1);
        }
        return null;
    }
    public void checkForUpdates(String[] content){
        DatabaseOperations.checkNonExistingLocations();
        for(String c : content){
            if(!(c.isEmpty())){
                if(DatabaseOperations.getLocationByAirportCode(extractCode(c))
                    == null){
                    
                    //ADD NEW
                    DatabaseOperations.AddLocation(new Location(extractCity(c),
                            extractCode(c)));
                    System.out.println("Adding new");
                    if(allFine){
                        super.getResult().incrementImportedRows();
                    }
                }
                else{
                    //UPDATE
                    DatabaseOperations.UpdateLocation(new Location(extractCity(c),
                            extractCode(c)
                    ));
                    System.out.println("Updating");
                }
            }
        }
    }
    
    @Override
    public void run(){
        final String CITY_COLUMN = "City";
        final String AIRPORT_CODE_COLUMN = "Airport Code";
        int lineNo = 1;
        //REMOVE HARDCODE!
        try(BufferedReader inputFile = new BufferedReader(
                new FileReader(super.getFileName()))){    
            String fileData = "";
            int c = 0;
            while((c = inputFile.read()) != -1){
              fileData += (char)c;
              
            }
            String[] lines = fileData.replace("\n\r","\n")
                .replace("\r","\n").split("\n");
            
        checkFormating(lines[0], (prepColumns(lines, lineNo)),
            CITY_COLUMN, AIRPORT_CODE_COLUMN,lineNo);
        String[] content = checkContent(lines, lineNo);
        checkForUpdates(content);
        
        }
        catch(FileNotFoundException fnf){
            super.getResult().getErrorMessages().add(lineNo+": "+
                    fnf.getStackTrace()+"\n"+fnf.getMessage());
            super.getResult().incrementFailedRows();
            System.out.println("FNF----"+fnf.getMessage());
        }
        catch(IOException ioe){
            super.getResult().getErrorMessages().add(lineNo+": "+
                    ioe.getStackTrace()+"\n"+ioe.getMessage());
            super.getResult().incrementFailedRows();
            System.out.println("IOE----"+ioe.getMessage());
        }
        catch(Exception e){
            super.getResult().getErrorMessages().add(lineNo+": "+
                    e.getStackTrace()+"\n"+e.getMessage());
            super.getResult().incrementFailedRows();
            System.out.println("e-----"+e.getMessage());
        }
    }
}
