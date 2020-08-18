/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectFileResource 
{
   File rootFolder;
   public String rootFolderPath;
   public String componentFilesFolderPath;
   public String structureFilesFolderPath;
   public String templateFilesFolderPath;
   Project pr;
   String projectName;
   
   public ProjectFileResource(String projectName, String rootFolderPath)
   { this.rootFolderPath = rootFolderPath;
     this.projectName = this.projectName;
    
   }
   
   public ProjectFileResource(String projectName)
   { this.projectName = projectName;
     readConfigProperties();
    
   }
   
   
    public ProjectFileResource(){}
   
   public boolean isExist()
   {
     File dir = new File(rootFolderPath);
     if (!dir.exists()) 
       { System.out.println("Root directory doesn't exist!!!");
         return false;
       }
     return true;
   }
 
   
  public void setProject(Project pr) 
  {this.pr = pr;}
   
  public boolean createFileResourceProject (String projectDirName)
  {  File dirProject = new File(rootFolderPath + projectDirName);
     if (!delete(dirProject)) return false;
     if (!create(dirProject)) return false;
     componentFilesFolderPath = rootFolderPath + projectDirName + "/GeneratedComponents/";
     File dirGeneratedFiles = new File(componentFilesFolderPath);
     if (!create(dirGeneratedFiles)) return false;
     
     
     return true;
  }
 

public boolean createFileResourceProject (String projectFolderName,Project pr)
  {return true;}

public boolean createFileResourceProject ()
  {return createFileResourceProject (projectName);}
  
   
public boolean create(File dir) 
   {    boolean result = false;
   
       if (!dir.exists()) {
        
        try{
            dir.mkdir();
            result = true;
        } 
        catch(SecurityException se){
            System.out.println("Directory or file: " + dir.getName() + " is not created!!!");
        }        
        if(result) {    
             System.out.println("Directory or file: " + dir.getName() + " is created!!!");
        }
     }
     return result;
   } 
  
 
 public boolean delete(File dir) 
   {  boolean result = true;
   
       if (dir.exists()) {
        

        try{
            result = deleteDirectoriesAndFiles(dir);
            
        } 
        catch(SecurityException se){ result = false;
         System.out.println("Directories and files are not deleted!!!");  
       
        }        
        if(result) {    
            System.out.println("Directories and files are deleted!!!");  
        }
     }
     return result;
   }


public static boolean deleteDirectoriesAndFiles(File dir) 
{ if (dir.isDirectory()) 
     { File[] children = dir.listFiles();
       for (int i = 0; i < children.length; i++) 
        { boolean success = deleteDirectoriesAndFiles(children[i]); 
          if (!success) { return false; }
        }
     }
   System.out.println("Removing file or directory : " + dir.getName()); 
   return dir.delete();


}

public String getProjectName(){return projectName;}


void readConfigProperties()
    { Properties prop = new Properties();
      InputStream input = null;
      try { input = new FileInputStream("config.properties");
  	    prop.load(input);
	    structureFilesFolderPath = prop.getProperty("sfPath");
            templateFilesFolderPath = prop.getProperty("tfPath");
            rootFolderPath = prop.getProperty("rootFolderPath");
            
	  } catch (IOException ex){} 
            finally 
              { if (input != null) 
                 {  try { input.close();} catch (IOException e) {}
	         }
	      }
    }
 

String getComponentFilesFolderPath() {return componentFilesFolderPath;}

void connectSTC(LucaEngine lengine, Property[] prop){}
 

}
