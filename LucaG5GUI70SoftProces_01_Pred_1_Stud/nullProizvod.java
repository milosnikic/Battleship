 /* Proizvod.java
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
 
 public class Proizvod implements Serializable, GeneralDObject { 
 
 public int SifraProizvoda; 
 public String NazivProizvoda; 
 
 public Proizvod() 
 { init(); 
 } 
 
 public Proizvod(int SifraProizvoda,  String NazivProizvoda )
 { 
 this. SifraProizvoda = SifraProizvoda;  
 this. NazivProizvoda = NazivProizvoda;  
 } 
 
public void init() 
 { 
  SifraProizvoda=0;  
  NazivProizvoda="";  
 } 
 
// Primary key
 public Proizvod(int SifraProizvoda)
 { 
 this.SifraProizvoda = SifraProizvoda;  
 } 
 
 @Override
 public GeneralDObject createDomainObject(){return new Proizvod();}
 
 @Override 
 public String getPrimaryKeyName() {return "SifraProizvoda";} 
 
 @Override 
 public int getPrimaryKey() {return  SifraProizvoda;} 
 
 @Override 
 public void setPrimaryKey(int value) {SifraProizvoda = value;} 
 
  public int getSifraProizvoda(){ return SifraProizvoda;}  
  public String getNazivProizvoda(){ return NazivProizvoda;}  
 
  public void setSifraProizvoda(int SifraProizvoda){this.SifraProizvoda = SifraProizvoda;}  
  public void setNazivProizvoda(String NazivProizvoda){this.NazivProizvoda = NazivProizvoda;}  
 @Override 
 public String getNameByColumn(int column) 
 { String names[] = {"SifraProizvoda", "NazivProizvoda"}; 
 return names[column]; 
 } 
 
 @Override 
 public String[] getNameAtributes() 
 { String names[] = {"SifraProizvoda", "NazivProizvoda"}; 
 return names; 
 } 
 
@Override
 public void println()
  { System.out.println(" SifraProizvoda:" + SifraProizvoda +  "  NazivProizvoda:" + NazivProizvoda);}
 
 @Override 
 public GeneralDObject getNewRecord(ResultSet rs) throws SQLException 
 {return new Proizvod(rs.getInt("SifraProizvoda"), rs.getString("NazivProizvoda"));} 
 
 @Override 
 public String getAtrValue() {return  SifraProizvoda + ", " +   (NazivProizvoda == null ? null : "'" + NazivProizvoda + "'");} 
 @Override 
 public String setAtrValue(){return "SifraProizvoda=" +  SifraProizvoda + ", " +  "NazivProizvoda=" +  (NazivProizvoda == null ? null : "'" + NazivProizvoda + "'");} 
 @Override 
 public String getClassName(){return "Proizvod";} 
 @Override 
 public String getWhereCondition(){return "SifraProizvoda = " + SifraProizvoda;} 
 
 
@Override
 public String messageCreateSuccess(){return "Sistem je kreirao novi proizvod.";}
@Override
 public String messageCreateFailure(){return "Sistem ne moze da kreira novi proizvod.";}
@Override
 public String messageInsertSuccess(){return "Sistem je zapamtio novi proizvod.";}
@Override
 public String messageInsertFailure(){return "Sistem ne moze da zapamti novi proizvod.(";}
@Override
 public String messageDeleteSuccess(){return "Sistem je obrisao proizvod.";}
@Override
 public String messageDeleteFailure(){return "Sistem ne moze da obrise proizvod.";}
@Override
 public String messageUpdateSuccess(){return "Sistem je promenio proizvod.";}
@Override
 public String messageUpdateFailure(){return "Sistem ne moze da promeni proizvod.";}
@Override
 public String messageFindSuccess(){return "Sistem je nasao proizvod.";}
@Override
 public String messageFindFailure(){return "Sistem ne moze da nadje proizvod.";}
@Override
 public String messageGetAllSuccess(){return "Sistem je vratio sve proizvodove.";}
@Override
 public String messageGetAllFailure(){return "Sistem ne moze da nadje proizvod.";}
@Override
 public GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj)
 {  try { Proizvod obj = new Proizvod();
  obj.setSifraProizvoda(jsobj.getInt("SifraProizvoda"));     
  obj.setNazivProizvoda(jsobj.getString("NazivProizvoda"));     
      return obj;
      } catch (JSONException ex) {
               Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
      } catch (Exception ex) {
              Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
      }
    return null;
}  
    
    
 @Override
    public String makeQuery(String criteria, String attribute)
    { String query = "Select * from Proizvod";
      if (criteria.equals(""))
          return query;    
      switch (attribute)
         {
  case "SifraProizvoda": query = query + " Where SifraProizvoda = " + criteria; break; 
  case "NazivProizvoda": query = query + " Where NazivProizvoda like '%" + criteria + "%'"; break; 
         }     
     return query;
    
    }
 
 @Override
  public String toString() { return NazivProizvoda;}
 
}