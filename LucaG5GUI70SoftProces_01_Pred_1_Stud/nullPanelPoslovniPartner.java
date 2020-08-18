package GUISpec;
 
import GUIGeneral.Panel;
 import BLStructureSpec.PoslovniPartner;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
 
 
 public class PanelPoslovniPartner extends Panel implements Initializable {
 
    
    @FXML
    public TableView<PoslovniPartner> PoslovniPartner;
    
    @FXML
 public TableColumn<?, Integer> SifraPartnera; 
 public TableColumn<?, String> NazivPartnera; 
 public TableColumn<?, String> Adresa; 
 public TableColumn<?, String> PIB; 
 public TableColumn<?, Date> DatumOsnivanja; 
 public TableColumn<?, Double> UkupniIznos; 
 public TableColumn<?, Integer> SifraGrada; 
    
 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
 
    
}