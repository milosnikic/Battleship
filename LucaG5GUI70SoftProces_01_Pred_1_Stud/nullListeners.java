/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;




import BLStructureGeneral.GeneralDObject;
import ControllerGeneral.GeneralGUIController;
import javafx.event.EventHandler;


/**
 *
 * @author Sinisa
 */

public class Listeners {


public class ListenerCreate implements EventHandler
{   GeneralGUIController con;
 
    public ListenerCreate(GeneralGUIController con1) {con = con1;}
    
@Override
     public void handle(javafx.event.Event e) {
          con.kreirajT();
        
    }
    }

public class ListenerDelete implements EventHandler
{   GeneralGUIController con;
 
    public ListenerDelete(GeneralGUIController con1) {con = con1;}
    
@Override
    public void handle(javafx.event.Event e) {
        GeneralDObject gdo = con.getActiveRow(); 
        con.obrisiT(gdo);
       }
    }

public class ListenerUpdate implements EventHandler
{   GeneralGUIController con;
 
    public ListenerUpdate(GeneralGUIController con1) {con = con1;}
    
@Override
    public void handle(javafx.event.Event e) {
        
        GeneralDObject gdo = con.getActiveRow();
        con.promeniT(gdo);
     
    }
    }

public class ListenerFind implements EventHandler
{   GeneralGUIController con;
 
    public ListenerFind(GeneralGUIController con1) {con = con1;}
    
@Override
    public void handle(javafx.event.Event e) {
    
        //GeneralDObject gdo = con.getActiveRow();
        //con.prikaziIzabranogT(gdo);
        String kriterijum = con.getPanel().Kriterijum.getText();
        con.selektujDomenskeObjekte(kriterijum);    
    }
    }


}    

