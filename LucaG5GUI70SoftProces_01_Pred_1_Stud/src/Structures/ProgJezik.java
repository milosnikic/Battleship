package Structures;
/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

import Structure.*;
import java.io.FileNotFoundException;

import java.io.IOException;

public class ProgJezik extends BaseStructure {
     
    public static void main(String[] args) throws FileNotFoundException, IOException {
    new ProgJezik().create();
    }
    
    
    @Override
    public void create () throws FileNotFoundException, IOException {
        String structureName = getStructureName();
        String userReqFileFolderPath = getUserRequirements();
        s = new Structure(structureName);
        s.addProperty("Autor:", "dr. Sinisa Vlajic, red. prof.");
        FileURL fu = new FileURL(projectName,".txt",userReqFileFolderPath);
        s.addProperty(new PropFile("userreq",fu) );
        s.addProperty("dk","Test1");
        
        
        Structure at = new Structure("Skraceni Java jezik");
        
        at.addProperty("pack","#1 package Generated.~f projectName;");
        
        at.addProperty("cl","#1 class ~f dk \n"
                + "{ \n"
                + "}");
        at.addProperty("skr", "Skraceni java jezik");
        at.addProperty("sout", "System.out.println(***);");
        at.addProperty("if2", "    if(***)\n" +
"         {\n" +
"         }   \n" +
"        else\n" +
"         {\n" +
"         }");
        at.addProperty("for1","    for(int i=0;i<***;i++)\n" +
"         {\n" +
"         }");
        at.addProperty("psvm","    public static void main(String[] args)\n" +
"        {\n" +
"        }");
        
        s.addStructure(at);
        

        
        
        
        saveStructureToLCSTFile();

        
    }
   
   
}



