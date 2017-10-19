
package Imports;

public abstract class BaseImporter implements Runnable {
    private String fileName;
    protected ImportResult results = new ImportResult();
    
    public BaseImporter(String fileName){
        this.fileName = fileName;
    }
    
    String getFileName(){
        return fileName;
    }
    public void setFileName(String name){
        fileName = name;
    }
    public ImportResult getResult(){
        return results;
    }
}
