package GUIGeneral;


import BLStructureGeneral.GeneralDObject;
import ControllerGeneral.GeneralGUIController;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;

public class DatePickerCell<S, T> extends TableCell<GeneralDObject, Date> {

    DatePicker datePicker;
    ObservableList<GeneralDObject> list;
    int indexdp;
    TableView<GeneralDObject> table;
    String columnName;
    GeneralDObject gdo;
    GeneralGUIController gc;
    

    public DatePickerCell(ObservableList<GeneralDObject> list,TableView<GeneralDObject> table,String columnName, GeneralDObject gdo,GeneralGUIController gc) {

        super();
        this.table = table;
        this.list = list;
        this.gdo = gdo;
        this.columnName = columnName;
        this.gc = gc;
        indexdp = table.getFocusModel().getFocusedCell().getRow();
        
        
        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

    
    }

    @Override // Na pocetku u svakom redu se napuni datepicker sa odgovarajucim datumom.
    public void updateItem(Date item, boolean empty) {

        super.updateItem(item, empty);

        SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");

            
        
        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            } else {
                setDate(item);
                setText(smp.format(item));
                setGraphic(this.datePicker);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    private void setDate(Date d) {

        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        d = java.sql.Date.valueOf(sm.format(d));
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        datePicker.setValue(LocalDate.of(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH) + 1,gc.get(Calendar.DAY_OF_MONTH)));
   
    }

    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("yyyy-MM-dd");
        datePicker.setEditable(true);

        datePicker.setOnAction(new EventHandler() {
            // Kada se klikne na novi datum ili se promeni postojeci datum.
            public void handle(Event t) {
                int index = table.getFocusModel().getFocusedCell().getRow();
                
                if (index!=getIndex())
                  {   table.requestFocus();
                      table.getSelectionModel().select(getIndex());
                      index = getIndex();
                      //return; 
                  }   
                
                SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate LDatum = datePicker.getValue();
                Date NoviDatum = java.sql.Date.valueOf(LDatum); // duzi datum
                NoviDatum = java.sql.Date.valueOf(sm.format(NoviDatum)); // kraci datum
                
                System.out.println("Size:" + list.size() + " Index:" + index);
                        
                if (getList()!=null && list.size() > index) {
                    setDatum(index,NoviDatum);
                     TablePosition<GeneralDObject, String> focusedCell = table.getFocusModel().getFocusedCell();
                     gc.columnOrder(columnName,focusedCell);
                    
                  }
            }
        });

        
        
        
        setAlignment(Pos.CENTER);
    }

    
    public ObservableList<GeneralDObject> getList() {
        return list;
    }
    
    void setDatum(int index, Date NoviDatum)
    {
    Field[] f = gdo.getClass().getDeclaredFields();
    for (Field f1 : f) 
      { if (f1.getName().equals(columnName))
         {try {
             f1.setAccessible(true);
             f1.set(getList().get(index), NoviDatum);
          } catch (IllegalArgumentException ex) {
              Logger.getLogger(DatePickerCell.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
              Logger.getLogger(DatePickerCell.class.getName()).log(Level.SEVERE, null, ex);
          }
          }     
      }
    
    }
    

}

