/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import Structures.BaseStructure;
import GUI.EkranskaForma3;
import GUI.Kontroler4;
import com.sun.javafx.application.PlatformImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LucaEngine {
 Project pr;
 
 public LucaEngine(Project pr) {this.pr = pr;}   
  
 public void connectSTC(BaseStructure bs, String LCTMFile, String componentFile, String outputFolderPath) throws IOException, FileNotFoundException, ClassNotFoundException
 {  bs.create(pr.getName());
    LucaFileSTC lf1 = new LucaFileSTC(bs.getLCSTName(),LCTMFile, componentFile, outputFolderPath);
    pr.createLucaFiles(lf1);
 } 

public void connectSTC(BaseStructure bs, String LCTMFile, String componentFile) throws IOException, FileNotFoundException, ClassNotFoundException
 {  bs.create(pr.getName());
    LucaFileSTC lf1 = new LucaFileSTC(bs.getLCSTName(),LCTMFile, componentFile, pr.getProjectFileResource().getComponentFilesFolderPath());
    pr.createLucaFiles(lf1);
 } 
 
 public Project getProject(){return pr;}
 
 public void showJavaFXForm()
 {
     try {
         EkranskaForma3 ef = new EkranskaForma3("Panel.fxml");
         PlatformImpl.startup(ef);
         while(ef.getPanel()==null) {try {Thread.sleep(10);} catch (InterruptedException ex) {
             Logger.getLogger(LucaEngine.class.getName()).log(Level.SEVERE, null, ex);
         }}
         
         Kontroler4 kon = new Kontroler4(ef,pr);
     } catch (IOException ex) {
         Logger.getLogger(LucaEngine.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(LucaEngine.class.getName()).log(Level.SEVERE, null, ex);
     } catch (InterruptedException ex) {
         Logger.getLogger(LucaEngine.class.getName()).log(Level.SEVERE, null, ex);
     }
 }     

public void runGenerator() { try {
    LucaFileGenerator.LucaFileGenerator(pr);
     } catch (IOException ex) {
         Logger.getLogger(LucaEngine.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(LucaEngine.class.getName()).log(Level.SEVERE, null, ex);
     }
}

public void connectSTC(LucaEngine lengine, Property[] prop)
 {pr.connectSTC(lengine,prop);}  


 
}
