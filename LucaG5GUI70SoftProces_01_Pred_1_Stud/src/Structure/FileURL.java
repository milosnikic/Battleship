/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileURL {
    public String rootDirectory;
    public String additionalDirectory;
    public String fileName;
    public String extension;
         
    public FileURL(String fileName, String extension, String rootDirectory, String additionalDirectory)
    { this.rootDirectory = rootDirectory;
      this.additionalDirectory = additionalDirectory;
      this.fileName = fileName;
      this.extension = extension;
    }
    
    public FileURL(String fileName, String extension, String rootDirectory)
    { this.rootDirectory = rootDirectory;
      this.fileName = fileName;
      this.extension = extension;
      this.additionalDirectory = "";
    }
    
    public FileURL(){}
    
    public void loadJavaFile(Structure s) throws FileNotFoundException, IOException
      {
        File f = new File(rootDirectory + additionalDirectory);
        new ProjectFileResource("").delete(f);
        new ProjectFileResource("").create(f);
        FileOutputStream fout = new FileOutputStream(rootDirectory + additionalDirectory + "/" + fileName + extension);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(s);
        oos.close();
        fout.close();
      }
    
     public void loadLCSTFile(Structure s) throws FileNotFoundException, IOException
      {
        FileOutputStream fout = new FileOutputStream(rootDirectory + additionalDirectory + "/" + fileName + extension);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(s);
        oos.close();
        fout.close();
      }
     
     public void loadLStringFile(String s) throws FileNotFoundException, IOException
      {
        FileOutputStream fout = new FileOutputStream(rootDirectory + additionalDirectory + "/" + fileName + extension);
        DataOutputStream oos = new DataOutputStream(fout);
        oos.writeBytes(s);
        oos.close();
        fout.close();
      }
     
     public void loadLStringFile(String s,String pathNameExtension) throws FileNotFoundException, IOException
      {
        FileOutputStream fout = new FileOutputStream(pathNameExtension);
        DataOutputStream oos = new DataOutputStream(fout);
        oos.writeBytes(s);
        oos.close();
        fout.close();
      }
    
    
    public String getPathNameExtension()
      {return rootDirectory + additionalDirectory + fileName + extension; }
    
    
   public String readFile() 
   { 
     String pathNameExtension = getPathNameExtension();  
     return readFile(pathNameExtension);
   }    
    
   public String readFile (String pathNameExtension)
    {   String value = "";
        File f = new File(pathNameExtension); 
        try 
        {  
           if(f.exists())
            {
                BufferedReader br;
                br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) 
                      { 
                        if (line.isEmpty()) 
                           value = value + "" + "\n";
                        else
                           value = value + line + "\n";
                      }
                br.close();
            }
           else
            { System.out.println("File: " + getPathNameExtension() + " doesn't exist!");
              return "";
            }
         
             
         } catch (Exception ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            return "";
         }  
     
    
      return value;
    }
    
    
}
