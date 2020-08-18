/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLBehaviour;

import BLStructureGeneral.GeneralDObject;
import DBBroker.DBBroker;
import TransferObject.TransferObject;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author Sinisa Vlajic
 */
public class GeneralSO 
{
  static DBBroker dbbr;
  boolean dbbrCreated = false;
  
  
  public GeneralSO() 
    {
      if (dbbrCreated == false) 
        { this.dbbr = new DBBroker();
          dbbrCreated = true;
        }
    }
  
    
  public void generalExecutionSO(TransferObject to)
  { String message;
    boolean signal = dbbr.makeConnection();
    to.setSignal(signal);
    if (signal == true)
      { signal = executionSO(to);
        to.setSignal(signal);
        if (signal==true) 
        { dbbr.commitTransation();
          message = messageSuccess(to); 
        }
        else
        { dbbr.rollbackTransation();
          message = messageFailure(to);
        }
        dbbr.closeConnection();
        to.setmessage(message);
      }   
     else
      { message = "Ne moze da se uspostavi konekcija sa bazom podataka";
        to.setmessage(message);
      }
  }  
   
public void findDomainObject(TransferObject to)
{   String message;
    boolean signal;
    dbbr.makeConnection();
    GeneralDObject obj = dbbr.findRecord(to.getDomainObject()); 
    if (obj != null) 
        { message = to.getDomainObject().messageFindSuccess(); 
          signal = true;
           
        }
        else
        { message = to.getDomainObject().messageFindFailure(); 
          signal = false;
        }
    dbbr.closeConnection();
    to.setSignal(signal);
    to.setmessage(message);
    to.setDomainObject(obj);
    
}
 


public void getAllDomainObjects(TransferObject to)
{   String message;
    boolean signal;
    dbbr.makeConnection();
    List <GeneralDObject> objList = dbbr.getAllRecords(to.getDomainObject()); 
    if (objList != null) 
        { message = to.getDomainObject().messageGetAllSuccess(); 
          signal = true;
        }
        else
        { message =to.getDomainObject().messageGetAllFailure(); 
          signal = false;
        }

    dbbr.closeConnection();
    to.setSignal(signal);
    to.setmessage(message);
    to.setObjectList(objList);
}


public void getDomainObjects(TransferObject to)
{   String message;
    boolean signal;
    dbbr.makeConnection();
    JSONArray jsa = dbbr.getRecords(to.getSQL()); 
    if (jsa != null) 
        { message = "Sistem je izvrsio SQL upit."; 
          System.out.println("Broj JSON objekata:" + jsa.length());
          signal = true;
           to.setJSONString(jsa.toString());
        }
        else
        { message = "Sistem nije izvrsio SQL upit.";
          signal = false;
        }

    dbbr.closeConnection();
    to.setSignal(signal);
    to.setmessage(message);
   
}


boolean executionSO(TransferObject to){return true;} 
String messageSuccess(TransferObject to){return "";}
String messageFailure(TransferObject to){return "";}

}
