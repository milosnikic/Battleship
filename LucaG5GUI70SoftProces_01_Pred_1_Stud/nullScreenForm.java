/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIGeneral;



import GUIGeneral.Panel;
import GUIGeneral.IScreenForm;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sinisa
 */

public class ScreenForm extends Application implements Runnable,IScreenForm
{  Panel pan;
   Stage s;
   String FXMLName;
   String name;
      
   public ScreenForm(String FXMLName1,String name)
   { FXMLName = FXMLName1;
   
     this.name = name;
    
     
   }
   
   @Override
   public void run()
   {
    try {
        s = new Stage();
        s.setTitle(this.name);
        start(s);
        }  catch (Exception ex) {
            Logger.getLogger(ScreenForm.class.getName()).log(Level.SEVERE, null, ex);}
   }
   
   @Override
   public void start(Stage stage) throws Exception {
        
        URL location = getClass().getResource("/GUISpec/" + FXMLName);
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();
        pan = fxmlLoader.getController();
        
        Scene scene = new Scene(root);
            
        stage.setScene(scene);
        stage.show(); 
}   
   
    @Override
    public Panel getPanel() { return pan;
         }

    
    
  
}

