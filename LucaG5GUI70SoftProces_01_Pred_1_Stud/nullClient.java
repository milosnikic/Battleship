/* Client.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
 
package main;
 
import BLStructureGeneral.GeneralDObject;
import BLStructureSpec.*;
import ControllerGeneral.GeneralGUIController;
import ControllerSpec.*;
import GUIGeneral.IScreenForm;
import GUIGeneral.ScreenForm;
import com.sun.javafx.application.PlatformImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
 
 
public class Client { 
      
 
public static void main(String args[])  {  
Client cl; 
cl = new Client();
 cl.createPoslovniPartner("PanelPoslovniPartner.fxml","Poslovni partner",new PoslovniPartner()); 
 cl.createGrad("PanelGrad.fxml","Grad",new Grad()); 
 cl.createProizvod("PanelProizvod.fxml","Proizvod",new Proizvod()); 
}
 
 void createPoslovniPartner(String pfxml, String formName, GeneralDObject gdo){ IScreenForm sf = createScreenForm(pfxml,formName); GeneralGUIController con = new GUIControllerPoslovniPartner(sf,gdo); } 
 void createGrad(String pfxml, String formName, GeneralDObject gdo){ IScreenForm sf = createScreenForm(pfxml,formName); GeneralGUIController con = new GUIControllerGrad(sf,gdo); } 
 void createProizvod(String pfxml, String formName, GeneralDObject gdo){ IScreenForm sf = createScreenForm(pfxml,formName); GeneralGUIController con = new GUIControllerProizvod(sf,gdo); } 
 
 public IScreenForm createScreenForm(String pfxml, String formName) {
        ScreenForm sf = new ScreenForm(pfxml,formName);
        PlatformImpl.startup(sf);
        while(sf.getPanel()==null) {try {Thread.sleep(10);} catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }}
        return sf;
    }  
 
    
}