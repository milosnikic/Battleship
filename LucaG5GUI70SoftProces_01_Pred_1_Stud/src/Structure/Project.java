/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Project
{ String projectName;
  public List<LucaFile> lf;
  String rootFolder;
  ProjectFileResource df;
  LucaFile currentGeneratedFile;
  LucaFile currentTemplateFile;
  
  
  public Project(String projectName, String rootFolder)
     { this.projectName = projectName;
       lf = new ArrayList<>();
       this.rootFolder = rootFolder;
     }
  
  public Project(String projectName, ProjectFileResource df)
     { this.projectName = projectName;
       lf = new ArrayList<>();
       this.rootFolder = df.rootFolderPath;
       this.df = df;
       df.setProject(this);
       if (!df.createFileResourceProject()) return;
     }
  
  public Project(ProjectFileResource df)
     { this.projectName = df.getProjectName();
       lf = new ArrayList<>();
       this.rootFolder = df.rootFolderPath;
       this.df = df;
       df.setProject(this);
       if (!df.createFileResourceProject()) return;
     }
  
  
  
  public ProjectFileResource getProjectFileResource()
  { return df;
  }
  
  public void addFile(LucaFile lf1)
  { lf.add(lf1);
  }
  
  public String getName()
  {
   return projectName;
  }
 
  
public void createProject() throws IOException {
        
        String fileName = projectName;
        df = new ProjectFileResource(rootFolder);
        if (!df.isExist()) return;
        String projectDirName = projectName;
        if (!df.createFileResourceProject(projectDirName)) return;
       
        
    }


public void createLucaFiles(String generatedFilesFolderPath, String structureFilesFolderPath, String templateFilesFolderPath, String fileName)
     {          
        LucaFile generatedFile = new LucaFile(generatedFilesFolderPath,fileName,"txt");
                
        LucaFile concreteStructureFile = new LucaFile(structureFilesFolderPath,fileName,"lcst");
        generatedFile.addConcreteStructureFile(concreteStructureFile);
        
        LucaFile templateFile = new LucaFile(templateFilesFolderPath,fileName + "Main","lctm");
        generatedFile.addTeplateFile(templateFile);
        addFile(generatedFile);
        
        currentGeneratedFile = generatedFile;
        currentTemplateFile = templateFile;
        
     }



public void createLucaFiles(String gfName, String gfExt, String gfPath, String sfName, String sfExt, String sfPath, String tfName, String tfExt, String tfPath)
      {              
        LucaFile generatedFile = new LucaFile(gfPath,gfName,gfExt);
                
        LucaFile concreteStructureFile = new LucaFile(sfPath,sfName,sfExt);
        generatedFile.addConcreteStructureFile(concreteStructureFile);
        
        LucaFile templateFile = new LucaFile(tfPath,tfName,tfExt);
        generatedFile.addTeplateFile(templateFile);
        
        addFile(generatedFile);
        
        currentGeneratedFile = generatedFile;
        currentTemplateFile = templateFile;
        
     }


// Structure + Template ---> Concrete component
public void createLucaFiles(LucaFileSTC lf)
      {              
        LucaFile generatedFile = new LucaFile(lf.cfPath,lf.cfNameExt);
                
        LucaFile concreteStructureFile = new LucaFile(df.structureFilesFolderPath,lf.sfNameExt);
        generatedFile.addConcreteStructureFile(concreteStructureFile);
        
        LucaFile templateFile = new LucaFile(df.templateFilesFolderPath,lf.tfNameExt);
        generatedFile.addTeplateFile(templateFile);
        
       
        addFile(generatedFile);
        
        currentGeneratedFile = generatedFile;
        currentTemplateFile = templateFile;
        
     }


public LucaFile getCurrentTemplateFile()
  { return currentTemplateFile;}

public LucaFile getCurrentGeneratedFile()
  { return currentGeneratedFile;}

  
 void createDir(File dir) 
   {
   
       if (!dir.exists()) {
        System.out.println("creating directory: " + dir.getName());
        boolean result = false;

        try{
            dir.mkdir();
            result = true;
        } 
        catch(SecurityException se){

        }        
        if(result) {    
            System.out.println("Directory created!!!");  
        }
     }
   } 
  
 
 void deleteDir(File dir) 
   {
   
       if (dir.exists()) {
        boolean result = false;

        try{
            result = deleteDirectory(dir);
            
        } 
        catch(SecurityException se){

        }        
        if(result) {    
            System.out.println("Directories and files are deleted!!!");  
        }
     }
   }


public static boolean deleteDirectory(File dir) 
{ if (dir.isDirectory()) 
     { File[] children = dir.listFiles();
       for (int i = 0; i < children.length; i++) 
        { boolean success = deleteDirectory(children[i]); 
          if (!success) { return false; }
        }
     }
   System.out.println("Removing file or directory : " + dir.getName()); 
   return dir.delete();


}

public String getTemplateFileNameExt(String generatedFileName)
{ for(LucaFile generatedFile :lf)
   { if(generatedFileName.equals(generatedFile.getFileNameExt()))
       return generatedFile.getCTemplateFile(0).getFileNameExt();
   }
  return "";
}

public String getTemplateFilePathNameExt(String generatedFileName)
{ for(LucaFile generatedFile :lf)
   { if(generatedFileName.equals(generatedFile.getFileNameExt()))
       return generatedFile.getCTemplateFile(0).getPathNameExtension();
   }
  return "";
}



public String getStructureFileNameExt(String generatedFileName)
{ for(LucaFile generatedFile :lf)
   { if(generatedFileName.equals(generatedFile.getFileNameExt()))
       return generatedFile.getConcreteStructureFile().getFileNameExt();
   }
  return "";
}

public String getStructureFilePathNameExt(String generatedFileName)
{ for(LucaFile generatedFile :lf)
   { if(generatedFileName.equals(generatedFile.getFileNameExt()))
       return generatedFile.getConcreteStructureFile().getPathNameExtension();
   }
  return "";
}

public String getGeneratedFilePathNameExt(String generatedFileName)
{ for(LucaFile generatedFile :lf)
   { if(generatedFileName.equals(generatedFile.getFileNameExt()))
       return generatedFile.getPathNameExtension();
   }
  return "";
}


void connectSTC(LucaEngine lengine, Property[] prop)
 {df.connectSTC(lengine,prop);}  

}
