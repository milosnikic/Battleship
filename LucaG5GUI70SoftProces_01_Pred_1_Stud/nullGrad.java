 /* Grad.java
 * @autor prof. dr Sinisa Vlajic,  
* Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
* Datum:2018-02-16 
 */ 
 
package BLStructureSpec; 
 
import BLStructureGeneral.GeneralDObject;
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
 
 public class Grad implements Serializable, GeneralDObject { 
 
 public int SifraGrada; 
 public String NazivGrada; 
 
 public Grad() 
 { init(); 
 } 
 
 public Grad(int SifraGrada,  String NazivGrada )
 { 
 this. SifraGrada = SifraGrada;  
 this. NazivGrada = NazivGrada;  
 } 
 
public void init() 
 { 
  SifraGrada=0;  
  NazivGrada="";  
 } 
 
// Primary key
 public Grad(int SifraGrada)
 { 
 this.SifraGrada = SifraGrada;  
 } 
 
 @Override
 public GeneralDObject createDomainObject(){return new Grad();}
 
 @Override 
 public String getPrimaryKeyName() {return "SifraGrada";} 
 
 @Override 
 public int getPrimaryKey() {return  SifraGrada;} 
 
 @Override 
 public void setPrimaryKey(int value) {SifraGrada = value;} 
 
  public int getSifraGrada(){ return SifraGrada;}  
  public String getNazivGrada(){ return NazivGrada;}  
 
  public void setSifraGrada(int SifraGrada){this.SifraGrada = SifraGrada;}  
  public void setNazivGrada(String NazivGrada){this.NazivGrada = NazivGrada;}  
 @Override 
 public String getNameByColumn(int column) 
 { String names[] = {"SifraGrada", "NazivGrada"}; 
 return names[column]; 
 } 
 
 @Override 
 public String[] getNameAtributes() 
 { String names[] = {"SifraGrada", "NazivGrada"}; 
 return names; 
 } 
 
@Override
 public void println()
  { System.out.println(" SifraGrada:" + SifraGrada +  "  NazivGrada:" + NazivGrada);}
 
 @Override 
 public GeneralDObject getNewRecord(ResultSet rs) throws SQLException 
 {return new Grad(rs.getInt("SifraGrada"), rs.getString("NazivGrada"));} 
 
 @Override 
 public String getAtrValue() {return  SifraGrada + ", " +   (NazivGrada == null ? null : "'" + NazivGrada + "'");} 
 @Override 
 public String setAtrValue(){return "SifraGrada=" +  SifraGrada + ", " +  "NazivGrada=" +  (NazivGrada == null ? null : "'" + NazivGrada + "'");} 
 @Override 
 public String getClassName(){return "Grad";} 
 @Override 
 public String getWhereCondition(){return "SifraGrada = " + SifraGrada;} 
 
 
@Override
 public String messageCreateSuccess(){return "Sistem je kreirao novi grad.";}
@Override
 public String messageCreateFailure(){return "Sistem ne moze da kreira novi grad.";}
@Override
 public String messageInsertSuccess(){return "Sistem je zapamtio novi grad.";}
@Override
 public String messageInsertFailure(){return "Sistem ne moze da zapamti novi grad.(";}
@Override
 public String messageDeleteSuccess(){return "Sistem je obrisao grad.";}
@Override
 public String messageDeleteFailure(){return "Sistem ne moze da obrise grad.";}
@Override
 public String messageUpdateSuccess(){return "Sistem je promenio grad.";}
@Override
 public String messageUpdateFailure(){return "Sistem ne moze da promeni grad.";}
@Override
 public String messageFindSuccess(){return "Sistem je nasao grad.";}
@Override
 public String messageFindFailure(){return "Sistem ne moze da nadje grad.";}
@Override
 public String messageGetAllSuccess(){return "Sistem je vratio sve gradove.";}
@Override
 public String messageGetAllFailure(){return "Sistem ne moze da nadje grad.";}
@Override
 public GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj)
 {  try { Grad obj = new Grad();
  obj.setSifraGrada(jsobj.getInt("SifraGrada"));     
  obj.setNazivGrada(jsobj.getString("NazivGrada"));     
      return obj;
      } catch (JSONException ex) {
               Logger.getLogger(Grad.class.getName()).log(Level.SEVERE, null, ex);
      } catch (Exception ex) {
              Logger.getLogger(Grad.class.getName()).log(Level.SEVERE, null, ex);
      }
    return null;
}  
    
    
 @Override
    public String makeQuery(String criteria, String attribute)
    { String query = "Select * from Grad";
      if (criteria.equals(""))
          return query;    
      switch (attribute)
         {
  case "SifraGrada": query = query + " Where SifraGrada = " + criteria; break; 
  case "NazivGrada": query = query + " Where NazivGrada like '%" + criteria + "%'"; break; 
         }     
     return query;
    
    }
 
 @Override
  public String toString() { return NazivGrada;}
 
}