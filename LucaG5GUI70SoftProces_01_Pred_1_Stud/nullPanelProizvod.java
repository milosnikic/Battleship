package GUISpec;
 
import GUIGeneral.Panel;
 import BLStructureSpec.Proizvod;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
 
 
 public class PanelProizvod extends Panel implements Initializable {
 
    
    @FXML
    public TableView<Proizvod> Proizvod;
    
    @FXML
 public TableColumn<?, Integer> SifraProizvoda; 
 public TableColumn<?, String> NazivProizvoda; 
    
 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
 
    
}