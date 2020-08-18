package TransferObject;

import BLStructureGeneral.GeneralDObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sinisa Vlajic
 */
public class TransferObject implements Serializable{ 
boolean signal; // Signal o uspesnosti izvrsenja operacije.
String message; // Poruka o uspesnosti izvrsenja operacije
String SOName; // Naziv sistemske operacije
GeneralDObject obj;
List <GeneralDObject> objList;
String jsaString;
//JSONArrayS jsa;
String sql;
public TransferObject(){signal = false; message = ""; objList = new ArrayList<>(); jsaString = "";//jsa = new JSONArrayS(); 
sql = ""; }
public TransferObject(GeneralDObject obj){this.obj = obj;signal = false; message = ""; objList = new ArrayList<>(); jsaString = "";//jsa = new JSONArrayS(); 
sql = ""; }
public GeneralDObject getDomainObject(){return obj;}
public List <GeneralDObject> getObjectList(){return objList;}
public boolean getSignal(){return signal;}
public String getMessage(){return message;}
public String getSOName(){return SOName;}
//public JSONArrayS getJSONArray(){return jsa;} 
public String getJSONString(){return jsaString;} 
public String getSQL(){return sql;}
public void setDomainObject(GeneralDObject obj){this.obj= obj;}
public void setObjectList(List <GeneralDObject> objList){this.objList = objList;}
public void setSignal(boolean signal){this.signal = signal;}
public void setmessage(String message){this.message = message;}
public void setSOName(String SOName){this.SOName = SOName;}
//public void setJSONArray(JSONArrayS jsa) {this.jsa= jsa;}
public void setSQL(String sql){this.sql = sql;}
public void setJSONString(String jsaString){this.jsaString = jsaString;}
}
