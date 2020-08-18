package GUISpec;
 
import GUIGeneral.Panel;
 import BLStructureSpec.Grad;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
 
 
 public class PanelGrad extends Panel implements Initializable {
 
    
    @FXML
    public TableView<Grad> Grad;
    
    @FXML
 public TableColumn<?, Integer> SifraGrada; 
 public TableColumn<?, String> NazivGrada; 
    
 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
 
    
}