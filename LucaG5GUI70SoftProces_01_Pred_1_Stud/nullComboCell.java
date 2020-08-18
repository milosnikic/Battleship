package GUIGeneral;


import BLStructureGeneral.GeneralDObject;
import ControllerGeneral.ComboIntegerStringConverter;
import ControllerGeneral.GeneralGUIController;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

public class ComboCell<S, T> extends TableCell<GeneralDObject, Integer> {

    private ComboBox combo;
    String columnName;
    TableView<GeneralDObject> table;
    int indexdp;
    ObservableList<GeneralDObject> listObjects;
    ObservableList<GeneralDObject> listCombo;
    GeneralGUIController gc;
    ComboIntegerStringConverter sc;
    

    public ComboCell(ObservableList<GeneralDObject> listObjects, GeneralGUIController gc,TableView<GeneralDObject> table,StringConverter sc,String columnName) {

        super();
        this.table = table;
        this.gc = gc;
        this.listObjects = listObjects; 
        this.columnName = columnName;
        this.sc = (ComboIntegerStringConverter) sc;
        
        if (combo == null) {
            createCombo();
        }
        setGraphic(combo);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

    
    }

    @Override // Na pocetku u svakom redu se napuni combo boks sa odgovarajucom vrednoscu.
    public void updateItem(Integer item, boolean empty) {

        super.updateItem(item, empty);

    
        
        if (null == this.combo) {
            System.out.println("Combo is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            } else {
                combo.setValue(getName(item));
                setGraphic(this.combo);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    

    public void createCombo() {
        
        this.combo = new ComboBox();
        this.listCombo = gc.initCombo(combo,sc);

        
               
        combo.setOnAction(new EventHandler() {
              public void handle(Event t) {
                int index = table.getFocusModel().getFocusedCell().getRow();
                // Ako se pokusa promena gradu u redu koji nije selektovan, vratice se fokus na selektovani red.
                if (index!=getIndex())
                  {   table.requestFocus();
                      table.getSelectionModel().select(getIndex());
                      index = getIndex();
                      
                  }   
                
                 if (getList()!=null && !combo.getValue().getClass().getName().equals("java.lang.String")) {
                     
                     setKey(index);
                     TablePosition<GeneralDObject, String> focusedCell = table.getFocusModel().getFocusedCell();
                     gc.columnOrder(columnName,focusedCell);
                     //Platform.runLater( () -> {table.edit(getIndex(), pan.NazivPartnera);});
                     //gc.promeniT(getList().get(index));
                     
                  }
            }
        });

        
        
        
        setAlignment(Pos.CENTER);
    }

    
    public ObservableList<GeneralDObject> getList() {
        return listObjects;
    }    
    
    String getName(int key)
    { 
      for(GeneralDObject obj:listCombo)
        { Field[] f = obj.getClass().getDeclaredFields(); 
          
          // Trazi vrednost sifre u tekucem objektu na osnovu vrednosti kljuca.
          for (Field f1 : f) 
          {  if (f1.getName().equals(sc.getKey()))
              {  try { f1.setAccessible(true);
                  int objectKey = (Integer)f1.get(obj);
                  // Pronalazi objekat (obj) koji ima odgovarajucu sifru.
                  if (objectKey == key)
                  {
                      for (Field f2 : f)
                      {  if (f2.getName().equals(sc.getName()))
                          // Vraca vrednost (name) objekta.
                          { f2.setAccessible(true);
                            return (String)f2.get(obj);
                          }  
                      }
                  }
              } catch (IllegalArgumentException ex) {
                  Logger.getLogger(ComboCell.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  Logger.getLogger(ComboCell.class.getName()).log(Level.SEVERE, null, ex);
              }
                  
              }
          }
          
        }   
        
       
     return ""; 
    }   
    
    
    void setKey(int index)
    {
    Field[] f = null;  
    for(GeneralDObject obj:listObjects)
        { f = obj.getClass().getDeclaredFields(); 
          break;
        }    
    
     for (Field f1 : f) 
      { if (f1.getName().equals(sc.getKey()))
         {try {
             f1.setAccessible(true);
             f1.set(getList().get(index), ((GeneralDObject) combo.getValue()).getPrimaryKey());
          } catch (IllegalArgumentException ex) {
              Logger.getLogger(DatePickerCell.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
              Logger.getLogger(DatePickerCell.class.getName()).log(Level.SEVERE, null, ex);
          }
          }     
      }
    
    }
    
// getList().get(index).setSifraGrada(((DKGrad)combo.getValue()).getSifraGrada());

    
//***Operacije nad kombo boksom - kraj****************************************************************    

}        
