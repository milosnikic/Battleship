 /* GUIControllerProizvod.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 07.02.2018
 */
 
package ControllerSpec;
 
import ControllerGeneral.ComboIntegerStringConverter;
import ControllerGeneral.GeneralGUIController;
import BLStructureSpec.*;
import BLStructureGeneral.GeneralDObject;
import GUIGeneral.IScreenForm;
import GUISpec.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.json.JSONException;
import org.json.JSONObject;
 
 
 
 public class GUIControllerProizvod extends GeneralGUIController {
    PanelProizvod padok;             
    
    public GUIControllerProizvod(IScreenForm sf,GeneralDObject gdo)
    { super(sf,gdo);
     padok = (PanelProizvod) pan;
     setTable(padok.Proizvod);
     Platform.runLater(()-> {initTable("");} );
     Platform.runLater(()-> {initCombo1(padok.Atribut); });
    }
  
    
 
    @Override
    public void setDomainObject(TableColumn.CellEditEvent<GeneralDObject, String> t,String columnName,TablePosition<GeneralDObject, String> focusedCell,boolean signal)
     {  Object o;
             
        if (signal == false)
          {}
        else
     {
            switch (columnName)
             { 
                 case "SifraProizvoda": 
                  o = t.getNewValue();
                  ((Proizvod) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSifraProizvoda((Integer)o); break; 
                case "NazivProizvoda": 
                  ((Proizvod) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNazivProizvoda(t.getNewValue()); break; 
            }
        
     }
}    
 
    
    @Override
 public TableColumn<?, ?> getColumn(String name)
      {
         switch(name)
             { 
               case "SifraProizvoda": return padok.SifraProizvoda; 
               case "NazivProizvoda": return padok.NazivProizvoda; 
             }
         return null;
      }   
    
    
 public void initTable(String s)
    { 
      tv.setEditable(true); 
      gInitTable(s); 
      setCell(padok.SifraProizvoda,"SifraProizvoda",new IntegerStringConverter(),true); 
      setCell(padok.NazivProizvoda,"NazivProizvoda",null,true); 
      
    }  
    
  
public String getValue(GeneralDObject item1,StringConverter sc) 
      {  ComboIntegerStringConverter cisk = (ComboIntegerStringConverter) sc;
 
          return "";
      }  
      
   
     
    @Override
    public String SQLExpression(StringConverter sc)
    {   ComboIntegerStringConverter cisk = (ComboIntegerStringConverter) sc;
 
        
      return "";  
    }
 
    @Override
    public GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj,StringConverter sc)
  {       
      ComboIntegerStringConverter cisk = (ComboIntegerStringConverter) sc;
 
      
    return null;
  } 
 
  
}
 
 
 
 
 
    
    
    
 
    