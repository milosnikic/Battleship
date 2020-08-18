/* GenericGUIController.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 27.01.2018
 */

package ControllerGeneral;



import BLStructureGeneral.GeneralDObject;
import GUIGeneral.IScreenForm;
import GUIGeneral.DatePickerCell;
import GUIGeneral.ComboCell;
import GUIGeneral.Panel;
import Listeners.Listeners;
import TransferObject.TransferObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



abstract public class GeneralGUIController  {
     IScreenForm sf;
     protected Panel pan;
     Socket soketK;
     ObjectOutputStream out;
     ObjectInputStream in;
     Listeners ls;
     List<GeneralDObject> list;
     GeneralDObject gdo;
     protected TableView tv;
     Object firstEditableColumn;
    
    // Povezivanje kontrolera sa ekranskom formom i sa kontrolerom brokera baze podataka. 
    // Povezivanje osluskivaca sa grafickim elementima ekranske forme.  
    public GeneralGUIController(IScreenForm sf,GeneralDObject gdo)
    { this.sf=sf;
      this.gdo = gdo;
      
      pan = sf.getPanel();
      list = new ArrayList<>(); 
      
         try {
             soketK = new Socket("127.0.0.1",8189);
             out = new ObjectOutputStream(soketK.getOutputStream());
             in = new ObjectInputStream(soketK.getInputStream());
         } catch (IOException ex) {
             Logger.getLogger(GeneralGUIController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
      
      
      ls = new Listeners();
      pan.Kreiraj.setOnAction( ls.new ListenerCreate(this));
      pan.Promeni.setOnAction( ls.new ListenerUpdate(this));
      pan.Obrisi.setOnAction( ls.new ListenerDelete(this));
      pan.Nadji.setOnAction( ls.new ListenerFind(this));
        
    }
    
    
    
    public GeneralDObject getActiveRow()
    { TablePosition<GeneralDObject, String> focusedCell = tv.getFocusModel().getFocusedCell(); 
       GeneralDObject gdo = (GeneralDObject) list.get(focusedCell.getRow());
       return gdo;
    }
   
     
     public void setTable(TableView tv) {this.tv = tv;}
     public void setFirstEditableColumn(Object column) {firstEditableColumn = column;}
     
//***Operacije kontrolera koje pozivaju sistemske operacije - pocetak****************************************************************
public void kreirajT() 
    {  GeneralDObject obj = gdo.createDomainObject(); 
       TransferObject to = new TransferObject(obj);
       to.setSOName("createDomainObject");
       to = pozivSistemskeOperacije(to);
         
       showMessage(to.getMessage());
       refreshTable("");
       setLastPosition();
       
    }
 
      
      
      public void promeniT(GeneralDObject obj)  
      {   
          TransferObject to = new TransferObject(obj);
                    
          to.setSOName("findDomainObject"); to = pozivSistemskeOperacije(to);
          
          showMessage(to.getMessage());

         if (to.getSignal() == true) 
          { to.setDomainObject(obj);
            to.setSOName("updateDomainObject"); to = pozivSistemskeOperacije(to);
            //refreshTable();
            showMessage(to.getMessage());
          }
         else
           { //refreshTable();
           }
      }
      
 
 public void prikaziIzabranogT(GeneralDObject obj) 
  {   
      TransferObject to = new TransferObject(obj); 
            
      to.setSOName("findDomainObject"); to = pozivSistemskeOperacije(to);
      
      showMessage(to.getMessage());
      if (to.getSignal() == true) 
        { 
        }
      else
       { 
       }
   }
 
 
  
 
  public void obrisiT(GeneralDObject obj ) 
  {   
  
      TransferObject to = new TransferObject(obj); 
        
      to.setSOName("findDomainObject"); to = pozivSistemskeOperacije(to);
      
      showMessage(to.getMessage());
      
      if (to.getSignal() == true) 
         { to.setSOName("deleteDomainObject"); to = pozivSistemskeOperacije(to);
           refreshTable("");
           showMessage(to.getMessage());
         }
      else
        { refreshTable("");
        }
      
  }
 
 boolean procitajIzBazePodataka()
{    GeneralDObject obj = gdo.createDomainObject();
     TransferObject to = new TransferObject(obj); 
     
     to.setSOName("getAllDomainObjects"); to = pozivSistemskeOperacije(to);
     
     showMessage(to.getMessage());
     if (to.getSignal() == true)
        { list = (List<GeneralDObject>)(List<?>) to.getObjectList(); // Puni se lista domenskih objekata
          return true;
        }
     return false; 

}

 public void selektujDomenskeObjekte(String kriterijum)
{    
     TransferObject to = new TransferObject();
     JSONArray jsa;  
     List<GeneralDObject> list = new ArrayList<>(); 
     String attribute = getAttribute();
     
     String upit = makeQuery(kriterijum,attribute);
     to.setSQL(upit);
         
     
     to.setSOName("getDomainObjects"); to = pozivSistemskeOperacije(to); 
          
     if (to.getSignal() == true)
        { try {
            jsa = new JSONArray(to.getJSONString());
            for (int i = 0; i < jsa.length(); i++)
            {
                
                JSONObject jsobj = (JSONObject) jsa.getJSONObject(i);
                GeneralDObject gdo1 = this.gdo.convertJSONObjectToDomainObject(jsobj);
                if (gdo1!=null)
                    list.add(gdo1);
                
            }
            
             this.list = list;
             refreshTable("select");
             
    
            
            
      } catch (JSONException ex) {
          Logger.getLogger(GeneralGUIController.class.getName()).log(Level.SEVERE, null, ex);
      }
            
       }
    
     
  }

// Ovaj upit moze da se prekrije ... ako neko zeli drugacije da implementira upite.  
public String makeQuery(String kriterijum,String attribute){ return gdo.makeQuery(kriterijum,attribute);} 
 
public String getAttribute() {return pan.Atribut.getValue();}
 
//abstract void setLastPosition();

  void setLastPosition()
    {   tv.scrollTo(tv.getItems().size()-1);
        
        tv.requestFocus();
        tv.getSelectionModel().select(tv.getItems().size()-1);
        
        Platform.runLater( () -> {tv.edit(tv.getItems().size()-1, (TableColumn) firstEditableColumn);});
    }


public TableView getTable() {return tv;}
//***Operacije kontrolera koje pozivaju sistemske operacije - kraj****************************************************************
 
public void gInitTable(String s)
{ boolean signal = true;
  if (s.equals(""))
    signal =  procitajIzBazePodataka();
  if (signal)
    {  
      ObservableList<GeneralDObject> olist = FXCollections.observableArrayList();
      olist.addAll(list);
      tv.setItems(olist);
    }   

}     


public void setCell(TableColumn tc,String columnName,StringConverter sc, boolean signal)
     { 
                  
         tc.setCellValueFactory(new PropertyValueFactory<>(columnName));
       
       
          if (sc!=null)
            { 
              if (sc.getClass().getName().equals("javafx.util.converter.DateStringConverter"))
                  setDatePickerCell(tc,columnName);
              else
               { if (sc.getClass().getName().equals("ControllerGeneral.ComboIntegerStringConverter"))
                   setCombo(tc,columnName,sc);  
                 else  
                   { tc.setCellFactory(TextFieldTableCell.forTableColumn(sc));       
                   }
               }
            }  
          else
              { tc.setCellFactory(TextFieldTableCell.forTableColumn());       
              }
       
         tc.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<GeneralDObject, String>>() {
                   @Override
                   public void handle(TableColumn.CellEditEvent<GeneralDObject, String> t) {
                        TablePosition<GeneralDObject, String> focusedCell = tv.getFocusModel().getFocusedCell();
                        setDomainObject(t,columnName,focusedCell,signal);
                        columnOrder(columnName,focusedCell);
                  }
             }
            );
         
       
     }

public void columnOrder(String columnName,TablePosition<GeneralDObject, String> focusedCell)
{
    List <String> list1 = getComboValueAttributeName();
        for(int i =0 ; i < list1.size(); i++)
          { if (columnName.equals(list1.get(i)))
              { if (i+1 < list1.size())
                 { int i1 = i;
                   Platform.runLater( () -> {tv.edit(focusedCell.getRow(), getColumn(list1.get(i1+1)));});
                   
                 } 
                else
                 { 
                   Platform.runLater( () -> {tv.edit(focusedCell.getRow(), (TableColumn) firstEditableColumn);}); 
                 }
                  
              
              }    
        
          }
     if (columnName.equals(list1.get(list1.size()-1)))
        promeniT(getActiveRow());         
        
}

abstract public TableColumn<?, ?> getColumn(String name);

abstract public void setDomainObject(TableColumn.CellEditEvent<GeneralDObject, String> t,String columnName,TablePosition<GeneralDObject, String> focusedCell,boolean signal);
    

void setDatePickerCell(TableColumn tc,String columnName)
{   ObservableList<GeneralDObject> olist = FXCollections.observableArrayList();
    olist.addAll(list);
    tc.setCellFactory(c -> new DatePickerCell(olist,getTable(),columnName,gdo,this)); // Povezivanje kolone sa datepicker-om.      
}
    
void setCombo(TableColumn tc,String columnName,StringConverter sc)
{   ObservableList<GeneralDObject> olist = FXCollections.observableArrayList();
    olist.addAll(list);
    tc.setCellFactory(c -> new ComboCell(olist,this,getTable(),sc,columnName)); // Povezivanje kolone sa combo boksom.      
}


 
 void refreshTable(String s) {initTable(s);}
abstract public void initTable(String s);
 
//***Operacije nad ekranskom formom - pocetak****************************************************************
void showMessage(String message) 
{ pan.Poruka.setText(message);
      
  Timer timer = new Timer();
  
  timer.schedule(new TimerTask() {
  @Override
  public void run() {
    pan.Poruka.setText(""); 
  }
}, 3000);
  
}



//***Operacije nad ekranskom formom - kraj****************************************************************

// Konverzija stringa u ceo broj.
int parseInt(String s)
  { int n;
    try {
       n = Integer.parseInt(s);
    } catch (NumberFormatException e) {n = 0;}   
    return n;
  }          



TransferObject pozivSistemskeOperacije(TransferObject to)
{
         try {
             if (to.getDomainObject()!=null)
                to.getDomainObject().println();
             out.writeObject(to); // poziv sistemske operacije
             out.reset();
             to = (TransferObject) in.readObject();
             
             
         } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(GeneralGUIController.class.getName()).log(Level.SEVERE, null, ex);
         }
      return to;   
}



//***Operacije nad kombo boksom - pocetak****************************************************************
void formatVrednostiKomboBoksa(ComboBox combo,StringConverter sc)
 {
    combo.setCellFactory(new Callback<ListView<GeneralDObject>, ListCell<GeneralDObject>>() {
 
            @Override
            public ListCell<GeneralDObject> call(ListView<GeneralDObject> param) {
                final ListCell<GeneralDObject> cell = new ListCell<GeneralDObject>() {
 
                    @Override
                    protected void updateItem(GeneralDObject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            // Ovo ce biti pojavljivanje (item) u listi vrednosti kombo boksa.
                            setText(getValue(item,sc));
                            
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
 }


public ObservableList<GeneralDObject> initCombo(ComboBox combo, StringConverter sc)
    { List<GeneralDObject> list = getComboList(combo,sc);  
      ObservableList<GeneralDObject> olist = FXCollections.observableArrayList();
      olist.addAll(list);
      combo.setItems(olist);
      combo.getSelectionModel().selectFirst();
      formatVrednostiKomboBoksa(combo,sc);
      combo.setEditable(true);
      return olist;
    
    }

public void initCombo1(ComboBox combo)
    { List <String> list = getComboValueAttributeName();  
      ObservableList<String> olist = FXCollections.observableArrayList();
      olist.addAll(list);
      combo.setItems(olist);
      combo.getSelectionModel().selectFirst();
      combo.setEditable(true);
    }

List <String> getComboValueAttributeName()
 { List <String> l = new ArrayList();
   
    Field[] f = gdo.getClass().getDeclaredFields();
    for (Field f1 : f) 
      { l.add(f1.getName());
      }
   
    return l;
 }  


List<GeneralDObject> getComboList(ComboBox combo, StringConverter sc) 
  {  TransferObject to = new TransferObject();
     JSONArray jsa;  
     List<GeneralDObject> listCombo = new ArrayList<>(); 
     to.setSQL(SQLExpression(sc));
         
     //while (to.getJSONArray().length()==0)
        to.setSOName("getDomainObjects"); to = pozivSistemskeOperacije(to); 
          
     if (to.getSignal() == true)
        { try {
            jsa = new JSONArray(to.getJSONString());
            for (int i = 0; i < jsa.length(); i++)
            {
                
                JSONObject jsobj = (JSONObject) jsa.getJSONObject(i);
                GeneralDObject gdo = convertJSONObjectToDomainObject(jsobj,sc);
                if (gdo!=null)
                    listCombo.add(gdo);
                
            }
      } catch (JSONException ex) {
          Logger.getLogger(GeneralGUIController.class.getName()).log(Level.SEVERE, null, ex);
      }
            
       }
     return listCombo;
  }  



//abstract public void setName(boolean signal,ComboBox combo);
abstract public String getValue(GeneralDObject item,StringConverter sc); 
abstract public String SQLExpression(StringConverter sc);
abstract public GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj,StringConverter sc);
//abstract public GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj);
//***Operacije nad kombo boksom - kraj****************************************************************

public Panel getPanel(){return pan;}
}


