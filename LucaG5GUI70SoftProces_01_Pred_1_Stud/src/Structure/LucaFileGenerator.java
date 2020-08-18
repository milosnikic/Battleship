/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LucaFileGenerator {
    
    public static void LucaFileGenerator(Project pr) throws FileNotFoundException,  ClassNotFoundException 
    {   Structure s=null; 
        FileInputStream fins;
        ObjectInputStream ois;
        LucaGComponents lgc = new LucaGComponents();
        
        for(LucaFile generatedFile:pr.lf) 
            {  
             if (generatedFile.getConcreteStructureFile()!= null) 
               { 
                 try {
                     fins = new FileInputStream(generatedFile.getConcreteStructureFile().getPathNameExtension());
                     ois = new ObjectInputStream(fins);
                     s = (Structure) ois.readObject();
                     ois.close();
                     fins.close();
                     if (s==null) {System.out.println("Structure can't be loaded!!!"); return;}
                     lgc.loadLucaGComponents(s,pr.getName());
                     
                     FileOutputStream fout = new FileOutputStream(generatedFile.getConcreteStructureFile().getPathNameExtension());
                     ObjectOutputStream oos = new ObjectOutputStream(fout);
                     oos.writeObject(s);
                     oos.close();
                     fout.close();
                 } catch (IOException ex) {
                     Logger.getLogger(LucaFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
                     
                 }
                 
                 
               }
            
            int i = 0;
               for(LucaFile templateFile:generatedFile.templateFiles) 
                {   if (i == 0)
                       Generator.generate(templateFile,generatedFile,s,false);
                    else
                       Generator.generate(templateFile,generatedFile,s,true);
                    i++;
                }
        
         }  
           
        
        System.out.println("Files are generated!!!");
        
       
    }
    

   /*public static boolean isStreamClosed(FileOutputStream out){
    try {
        FileChannel fc = out.getChannel();
        return fc.position() >= 0L; // This may throw a ClosedChannelException.
    } catch (java.nio.channels.ClosedChannelException cce) {
        return false;
    } catch (IOException e) {
    }
    return true;
}*/
    
  
}






