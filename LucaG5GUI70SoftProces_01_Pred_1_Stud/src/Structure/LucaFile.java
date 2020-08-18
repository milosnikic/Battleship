/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;


import java.util.ArrayList;
import java.util.List;

public class LucaFile
{ String filePath; 
  String fileName; 
  String extension;
  LucaFile concreteStructureFile;
  List <LucaFile> templateFiles;
  public LucaFile(String filePath,String fileName,String extension)
  { this.filePath = filePath;
    this.fileName = fileName;
    this.extension = extension;
    templateFiles = new ArrayList<>();
  }
  
   public LucaFile(String filePath,String fileNameExt)
  { 
    String fileName = "";
    String extension = "";
    
    int i = fileNameExt.lastIndexOf('.');
    if (i > 0)
     {
       extension = fileNameExt.substring(i+1);
       fileName = fileNameExt.substring(0, i);
     }

    
    
    this.filePath = filePath;
    this.fileName = fileName;
    this.extension = extension;
    templateFiles = new ArrayList<>();
  }
  
  public void addTeplateFile(LucaFile lf)
  {templateFiles.add(lf);}
  
  public void addConcreteStructureFile(LucaFile concreteStructureFile)
  {this.concreteStructureFile = concreteStructureFile;}
  
  public String getPathNameExtension()
  {return filePath + fileName + "." + extension; }

  public LucaFile getConcreteStructureFile()
    {return concreteStructureFile;}
  
  public String getFileName() {return fileName;}
  
  public String getFileNameExt() {return fileName + "." + extension;}
   
  public LucaFile getCTemplateFile(int index)
    {return templateFiles.get(index);}
  
}
