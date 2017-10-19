
package Imports;
import java.util.ArrayList;

public class ImportResult {
    private int totalRows, importedRows, failedRows;
    private ArrayList<String> errorMessages = new ArrayList();
    
    public int getTotalRows(){
        return totalRows;
    }
    public int getImportedRows(){
        return importedRows;
    }
    public int getFailedRows(){
        return failedRows;
    }
    void incrementImportedRows(){
        importedRows++;
        totalRows++;
    }
    void incrementFailedRows(){
        failedRows++;
        totalRows++;
    }
    ImportResult(){
        totalRows = 0;
        importedRows = 0;
        failedRows = 0;
        errorMessages.clear();
    }
    void setErrorMessages(ArrayList<String> list){
        errorMessages = list;
    }
    void addErrorMessage(String message){
        errorMessages.add(message);
    }
    public ArrayList<String> getErrorMessages(){
        return errorMessages;
    }
}
