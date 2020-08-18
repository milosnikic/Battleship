/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;

public class Panel implements IPanel {
    public IEkranskaForma ef;
    
    @FXML
    public TextFlow LucaExpressionText;

    @FXML
    public Label LSifraPartnera1;

    @FXML
     public TextField UnvisibleField;
    
    @FXML
    public TextField ProjectName;

    @FXML
    public Label LSifraPartnera;

    @FXML
    public HTMLEditor TemplateText;

    @FXML
    public TextField StructureName;

    @FXML
    public TextField TemplateName;

    @FXML
    public ComboBox<String> ComponentName;

    @FXML
    public TreeView<String> StructureText; 

    @FXML
    public TextFlow ComponentText;
    
    @FXML
    public Button SaveTemplate;
    
    @FXML
    public Button SaveComponent;
    
    @FXML
    public Button GenerateComponent;
    
    @FXML
    public TextFlow HtmlView;
    

    @Override
    public void setEkranskaForma(IEkranskaForma ef) { this.ef = ef;}

    

}
