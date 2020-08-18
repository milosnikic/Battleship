package Structures;

/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */



import Structure.FileURL;
import Structure.Property;
import Structure.Structure;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseStructure {
    
    Structure s;
    String projectName = "";
    
    Property[] prop;
    
    
    public BaseStructure(){prop = null;}
    public BaseStructure(Property[] prop) {this.prop = prop;}
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        new BaseStructure().create();     
    } 
    
    public void create() throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        
        String structureFilesFolderPath = getStructurePath();
        
        String fileName = getStructureName();
        
        /* Create structure s.*/
        Structure s = new Structure(getStructureName(),prop);
      
                
        /* Save structure s to lcst file.*/
        FileURL structURLFile = new FileURL(fileName,".lcst",structureFilesFolderPath);
        structURLFile.loadLCSTFile(s);
        
    }

  public void create(String projectName) throws IOException, FileNotFoundException, ClassNotFoundException 
     {this.projectName = projectName; create();}
    
    
  public String getLCSTName() {return this.getClass().getSimpleName() + ".lcst";} 
  
  public String getStructureName() { return this.getClass().getSimpleName();}
  
  public String getStructurePath(){return "src/Structures/";}
  
  public String getUserRequirements() {return "src/UserRequirements/";}
  
  public void saveStructureToLCSTFile()
     {    
        try {
            String filePath = getStructurePath();
            String fileName = getStructureName();
            String extension = ".lcst";
            
            
            /* Save structure s to lcst file.*/
            FileURL structURLFile = new FileURL(fileName,extension,filePath);
            structURLFile.loadLCSTFile(s);
        } catch (IOException ex) {
            Logger.getLogger(BaseStructure.class.getName()).log(Level.SEVERE, null, ex);
        }
     } 
  
  


    
}
