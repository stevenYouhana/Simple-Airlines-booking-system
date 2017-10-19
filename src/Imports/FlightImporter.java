package Imports;

import BusinessObject.Flight;
import BusinessObject.Plane;
import Imports.Eng.ConvertPlane;
import Imports.Eng.Extractions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import uflybookingsystem.DatabaseOperations;

public class FlightImporter extends BaseImporter {
    String[] row = new String[7];
    boolean allFine = true;
    public FlightImporter(String fileName){
        super(fileName);
    }
    String[] checkRowItems(String[] content, int lineNo){
    Throwable badContent;
    if(!(content[0].matches("[NZ]+\\d{3}"))){
        try{
            badContent = new Throwable(
                    lineNo+": Flight Number has incorrect value");
            super.getResult().incrementFailedRows();
            allFine = false;
        throw badContent;
        }
        catch(Throwable e){
            super.getResult().getErrorMessages().add(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    if(!(content[1].matches("\\D{3}"))){
        try{
            badContent = new Throwable(
                    lineNo+": Departure Airport has incorrect value");
            super.getResult().incrementFailedRows();
            allFine = false;
        throw badContent;
        }
        catch(Throwable e){
            super.getResult().getErrorMessages().add(e.getMessage());
            System.out.println(e.getMessage());
        }  
    }
    if(!(content[2].matches("\\D\\D\\D"))){
        try{
            badContent = new Throwable(
                    lineNo+": Destination Airport has incorrect value");
            super.getResult().incrementFailedRows();
            allFine = false;
        throw badContent;
        }
        catch(Throwable e){
            super.getResult().getErrorMessages().add(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    if(!(content[3].matches("\\d\\d") || content[3].matches("\\d\\d\\d"))){
        try{
            badContent = new Throwable(
                    lineNo+": Price has incorrect value");
            super.getResult().incrementFailedRows();
            allFine = false;
        throw badContent;
        }
        catch(Throwable e){
            super.getResult().getErrorMessages().add(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    if(!(content[4].matches("(\\d\\d[/]){2}\\d{4}\\s+(\\d\\d[:]){2}\\d\\d"))){
        try{
            badContent = new Throwable(
                    lineNo+": DateTime has incorrect value");
            super.getResult().incrementFailedRows();
            allFine = false;
        throw badContent;
        }
        catch(Throwable e){
            super.getResult().getErrorMessages().add(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    for(char c : content[6].toCharArray()){
        if(!(String.valueOf(c).matches("\\d"))){
            try{
                badContent = new Throwable(
                        lineNo+": Seats Taken has incorrect value");
                super.getResult().incrementFailedRows();
                allFine = false;
            throw badContent;
            }
            catch(Throwable e){
                super.getResult().getErrorMessages().add(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }
    return content;
}
    public void checkForUpdates(String[] content){
    //content is single line of the file
    Extractions ext = new Extractions(content);   
    if(DatabaseOperations.getFlightByFlightNumber(
            ext.pullFlightNumber()) == null){
        //Add flight
        DatabaseOperations.AddFlight(
                new Flight(ext.pullFlightNumber(),
        ext.pullDeparturePort(),ext.pullDestinationPort(),
                Double.parseDouble(ext.pullPrice()),ext.pullDateTime(),
        ConvertPlane.convertPlaneEnum(ext.pullPlane()),Integer.parseInt(
                ext.pullSeatsTaken()))
        );
        if(allFine){
            super.getResult().incrementImportedRows();
        }
    }
    else{
        DatabaseOperations.UpdateFlight(new Flight(ext.pullFlightNumber(),
        ext.pullDeparturePort(),ext.pullDestinationPort(),
                Double.parseDouble(ext.pullPrice()),ext.pullDateTime(),
        ConvertPlane.convertPlaneEnum(ext.pullPlane()),Integer.parseInt(
                ext.pullSeatsTaken()))
        );
    }

}
    
    @Override
    public void run() {
        int lineNo = 1;
        int c;
        try(BufferedReader inputFile = new BufferedReader(
                new FileReader(super.getFileName()))){
            String fileData = "";
            while((c=inputFile.read()) != -1){
                fileData += (char)c;
            }
            String[] lines = fileData.replace("\n\r","\n")
                .replace("\r","\n").split("\n");
           
            int count = lines.length-1;
            int rowI = 0;
            while(count >= 1){
                if(!(lines[count].isEmpty())){
                    row = lines[count].split("[,]");
                    checkForUpdates(checkRowItems(row,lineNo)); 
                }
                count--;
                lineNo++;
            }    
        }
        catch(FileNotFoundException fnf){
            super.getResult().getErrorMessages().add(lineNo+": "+
                    fnf.getStackTrace());
            super.getResult().incrementFailedRows();
            System.out.println(fnf.getMessage());
        }
        catch(IOException ioe){
            super.getResult().getErrorMessages().add(lineNo+": "+
                    ioe.getStackTrace());
            super.getResult().incrementFailedRows();
            System.out.println(ioe.getMessage());
        }
        catch(Exception e){
            super.getResult().getErrorMessages().add(lineNo+": "+
                    e.getStackTrace());
            super.getResult().incrementFailedRows();
        }
    }

}
