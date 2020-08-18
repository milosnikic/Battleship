/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import Structures.BaseStructure;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectFileResourceSS1 extends ProjectFileResource
{   
   File projectFolder;
   public String projectFolderPath;
   File srcFolder; 
   public String srcFolderPath;
   File nbprojectFolder; 
   public String nbprojectFolderPath;
   File domainClassesFolder; 
   public String domainClassesFolderPath;
   File mainFolder; 
   public String mainFolderPath;
   File fileFolder; 
   public String fileFolderPath;
   
      
    
   /*public DirFilesDKJavaProject(String rootFolderPath)
   { super(rootFolderPath);
   }*/
   
   public ProjectFileResourceSS1(String projectName)
   { super(projectName);
     
   }
 
  
 @Override
 public boolean createFileResourceProject ()
  {  
             
     domainClassesFolderPath = "src/Generated/" + projectName + "/";
     domainClassesFolder = new File(domainClassesFolderPath);
     if (!create(domainClassesFolder)) return false;
     
     
    readConfigProperties(); 
     
       try {
           new BaseStructure().create();
       } catch (IOException ex) {
           Logger.getLogger(ProjectFileResourceSS1.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(ProjectFileResourceSS1.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        return true;
  }
   
 
 


}