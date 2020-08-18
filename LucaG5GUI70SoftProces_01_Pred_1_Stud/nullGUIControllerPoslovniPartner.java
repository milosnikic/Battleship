 /* GUIControllerPoslovniPartner.java
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
 
 
 
 public class GUIControllerPoslovniPartner extends GeneralGUIController {
    PanelPoslovniPartner padok;             
    
    public GUIControllerPoslovniPartner(IScreenForm sf,GeneralDObject gdo)
    { super(sf,gdo);
     padok = (PanelPoslovniPartner) pan;
     setTable(padok.PoslovniPartner);
     setFirstEditableColumn(padok.NazivPartnera);
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
                case "SifraPartnera": 
                  o = t.getNewValue();
                  ((PoslovniPartner) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSifraPartnera((Integer)o); break; 
                case "NazivPartnera": 
                  ((PoslovniPartner) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNazivPartnera(t.getNewValue()); break; 
                case "Adresa": 
                  ((PoslovniPartner) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAdresa(t.getNewValue()); break; 
                case "PIB": 
                  ((PoslovniPartner) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPIB(t.getNewValue()); break; 
                 case "DatumOsnivanja": 
                   o = (Object)t.getNewValue();
                   SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                    Date DatumOsnivanja = (Date)o;
                   DatumOsnivanja = java.sql.Date.valueOf(sm.format(DatumOsnivanja));
                   if (DatumOsnivanja!=null)            
                      ((PoslovniPartner) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDatumOsnivanja(DatumOsnivanja); 
                   break; 
                case "UkupniIznos": 
                  o = t.getNewValue();
                  ((PoslovniPartner) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUkupniIznos((Double)o); break; 
                case "SifraGrada": 
                  o = t.getNewValue();
                  ((PoslovniPartner) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSifraGrada((Integer)o); break; 
            }
        
     }
}    
 
    
    @Override
 public TableColumn<?, ?> getColumn(String name)
      {
         switch(name)
             { 
               case "SifraPartnera": return padok.SifraPartnera; 
               case "NazivPartnera": return padok.NazivPartnera; 
               case "Adresa": return padok.Adresa; 
               case "PIB": return padok.PIB; 
               case "DatumOsnivanja": return padok.DatumOsnivanja; 
               case "UkupniIznos": return padok.UkupniIznos; 
               case "SifraGrada": return padok.SifraGrada; 
             }
         return null;
      }   
    
    
 public void initTable(String s)
    { 
      tv.setEditable(true); 
      gInitTable(s); 
      setCell(padok.SifraPartnera,"SifraPartnera",new IntegerStringConverter(),true); 
      setCell(padok.NazivPartnera,"NazivPartnera",null,true); 
      setCell(padok.Adresa,"Adresa",null,true); 
      setCell(padok.PIB,"PIB",null,true); 
      setCell(padok.DatumOsnivanja,"DatumOsnivanja",new DateStringConverter(),false); 
      setCell(padok.UkupniIznos,"UkupniIznos",new DoubleStringConverter(),true); 
      setCell(padok.SifraGrada,"SifraGrada", new ComboIntegerStringConverter("SifraGrada","NazivGrada","Grad"),false); 
      
    }  
    
  
public String getValue(GeneralDObject item1,StringConverter sc) 
      {  ComboIntegerStringConverter cisk = (ComboIntegerStringConverter) sc;
         if (cisk.getTable().equals("Grad"))
            { Grad item = (Grad) item1;
              return item.getNazivGrada();
            }          
          return "";
      }  
      
   
     
    @Override
    public String SQLExpression(StringConverter sc)
    {   ComboIntegerStringConverter cisk = (ComboIntegerStringConverter) sc;
         if (cisk.getTable().equals("Grad"))
          return "Select SifraGrada, NazivGrada From Grad";  
        
      return "";  
    }
 
    @Override
    public GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj,StringConverter sc)
  {       
      ComboIntegerStringConverter cisk = (ComboIntegerStringConverter) sc;
         if (cisk.getTable().equals("Grad"))
          {    
            try {
            Grad ob = new Grad();
            ob.setSifraGrada(jsobj.getInt("SifraGrada"));
            ob.setNazivGrada(jsobj.getString("NazivGrada")); 
            return ob;
                } catch (JSONException ex) {
                    Logger.getLogger(GUIControllerPoslovniPartner.class.getName()).log(Level.SEVERE, null, ex);
                }
          }       
      
    return null;
  } 
 
  
}
 
 
 
 
 
    
    
    
 
    