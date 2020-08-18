/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package GUI;


import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EkranskaForma3 extends Application implements Runnable,IEkranskaForma
{  IPanel pan;
   public Stage s;
   String nazivFXML;
   
   public EkranskaForma3(String nazivFXML1)
   { nazivFXML = nazivFXML1;
   }
   
   @Override
   public void run()
   {
    try {
        s = new Stage();
        start(s);
        }  catch (Exception ex) {
            Logger.getLogger(EkranskaForma3.class.getName()).log(Level.SEVERE, null, ex);}
   }
   
   public void start(Stage stage) throws Exception {
        
        URL location = getClass().getResource(nazivFXML);
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();
        pan = fxmlLoader.getController();
        pan.setEkranskaForma(this);
        
        
        Scene scene = new Scene(root);
        
        //scene.getStylesheets().add(getClass().getResource("three.css").toExternalForm()); // ili getClass().getResource("resource/three.css")

        
        stage.setScene(scene);
     
        
        
        stage.show();

}
   
   
    @Override
    public IPanel getPanel() { return pan;
         }

    @Override
    public void setPanel(IPanel pan1) {
          }

    @Override
    public void prikaziEkranskuFormu() {    }

    @Override
    public void odrediDekoracijuForme() {  }
}

