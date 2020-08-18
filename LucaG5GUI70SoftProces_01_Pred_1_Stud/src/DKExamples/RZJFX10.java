package DKExamples;

import java.io.FileNotFoundException;
import java.io.IOException;
import Structure.*;
import Structures.Korisnik;


/**
 *
 * @author Sinisa Vlajic
 */


public class RZJFX10 {
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException {
       
        String projectName = "RZJFX10";
        ProjectFileResourceSS1 pfr = new ProjectFileResourceSS1(projectName);
        Project pr = new Project(pfr);
        LucaEngine lengine = new LucaEngine(pr);
        
        lengine.connectSTC(new Korisnik(),"DKObject.lctm", "Korisnik.java", pfr.domainClassesFolderPath);
                
        lengine.showJavaFXForm();
        lengine.runGenerator();
        
    } 
    
   
    
}
