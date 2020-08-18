 /* PoslovniPartner.java
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
 
 public class PoslovniPartner implements Serializable, GeneralDObject { 
 
 public int SifraPartnera; 
 public String NazivPartnera; 
 public String Adresa; 
 public String PIB; 
 public Date DatumOsnivanja; 
 public double UkupniIznos; 
 public int SifraGrada; 
 
 public PoslovniPartner() 
 { init(); 
 } 
 
 public PoslovniPartner(int SifraPartnera,  String NazivPartnera,  String Adresa,  String PIB,  Date DatumOsnivanja,  double UkupniIznos,  int SifraGrada )
 { 
 this. SifraPartnera = SifraPartnera;  
 this. NazivPartnera = NazivPartnera;  
 this. Adresa = Adresa;  
 this. PIB = PIB;  
 this. DatumOsnivanja = DatumOsnivanja;  
 this. UkupniIznos = UkupniIznos;  
 this. SifraGrada = SifraGrada;  
 } 
 
public void init() 
 { 
  SifraPartnera=0;  
  NazivPartnera="";  
  Adresa="";  
  PIB="";  
 SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
  DatumOsnivanja = new Date();
  DatumOsnivanja = java.sql.Date.valueOf(sm.format(DatumOsnivanja));   
  UkupniIznos=0;  
  SifraGrada=0;  
 } 
 
// Primary key
 public PoslovniPartner(int SifraPartnera)
 { 
 this.SifraPartnera = SifraPartnera;  
 } 
 
 @Override
 public GeneralDObject createDomainObject(){return new PoslovniPartner();}
 
 @Override 
 public String getPrimaryKeyName() {return "SifraPartnera";} 
 
 @Override 
 public int getPrimaryKey() {return  SifraPartnera;} 
 
 @Override 
 public void setPrimaryKey(int value) {SifraPartnera = value;} 
 
  public int getSifraPartnera(){ return SifraPartnera;}  
  public String getNazivPartnera(){ return NazivPartnera;}  
  public String getAdresa(){ return Adresa;}  
  public String getPIB(){ return PIB;}  
  public Date getDatumOsnivanja(){ return DatumOsnivanja;}  
  public double getUkupniIznos(){ return UkupniIznos;}  
  public int getSifraGrada(){ return SifraGrada;}  
 
  public void setSifraPartnera(int SifraPartnera){this.SifraPartnera = SifraPartnera;}  
  public void setNazivPartnera(String NazivPartnera){this.NazivPartnera = NazivPartnera;}  
  public void setAdresa(String Adresa){this.Adresa = Adresa;}  
  public void setPIB(String PIB){this.PIB = PIB;}  
  public void setDatumOsnivanja(Date DatumOsnivanja){this.DatumOsnivanja = DatumOsnivanja;}  
  public void setUkupniIznos(double UkupniIznos){this.UkupniIznos = UkupniIznos;}  
  public void setSifraGrada(int SifraGrada){this.SifraGrada = SifraGrada;}  
 @Override 
 public String getNameByColumn(int column) 
 { String names[] = {"SifraPartnera", "NazivPartnera", "Adresa", "PIB", "DatumOsnivanja", "UkupniIznos", "SifraGrada"}; 
 return names[column]; 
 } 
 
 @Override 
 public String[] getNameAtributes() 
 { String names[] = {"SifraPartnera", "NazivPartnera", "Adresa", "PIB", "DatumOsnivanja", "UkupniIznos", "SifraGrada"}; 
 return names; 
 } 
 
@Override
 public void println()
  { System.out.println(" SifraPartnera:" + SifraPartnera +  " NazivPartnera:" + NazivPartnera +  " Adresa:" + Adresa +  " PIB:" + PIB +  " DatumOsnivanja:" + DatumOsnivanja +  " UkupniIznos:" + UkupniIznos +  "  SifraGrada:" + SifraGrada);}
 
 @Override 
 public GeneralDObject getNewRecord(ResultSet rs) throws SQLException 
 {return new PoslovniPartner(rs.getInt("SifraPartnera"), rs.getString("NazivPartnera"), rs.getString("Adresa"), rs.getString("PIB"), rs.getDate("DatumOsnivanja"), rs.getDouble("UkupniIznos"), rs.getInt("SifraGrada"));} 
 
 @Override 
 public String getAtrValue() {return  SifraPartnera + ", " +   (NazivPartnera == null ? null : "'" + NazivPartnera + "'") + ", " +   (Adresa == null ? null : "'" + Adresa + "'") + ", " +   (PIB == null ? null : "'" + PIB + "'") + ", " +   "'" + DatumOsnivanja + "'" + ", " +   UkupniIznos + ", " +   SifraGrada;} 
 @Override 
 public String setAtrValue(){return "SifraPartnera=" +  SifraPartnera + ", " +  "NazivPartnera=" +  (NazivPartnera == null ? null : "'" + NazivPartnera + "'") + ", " +  "Adresa=" +  (Adresa == null ? null : "'" + Adresa + "'") + ", " +  "PIB=" +  (PIB == null ? null : "'" + PIB + "'") + ", " +  "DatumOsnivanja=" +  "'" + DatumOsnivanja + "'" + ", " +  "UkupniIznos=" +  UkupniIznos + ", " +  "SifraGrada=" +  SifraGrada;} 
 @Override 
 public String getClassName(){return "PoslovniPartner";} 
 @Override 
 public String getWhereCondition(){return "SifraPartnera = " + SifraPartnera;} 
 
 
@Override
 public String messageCreateSuccess(){return "Sistem je kreirao novog poslovnog partnera.";}
@Override
 public String messageCreateFailure(){return "Sistem ne moze da kreira novog poslovnog partnera.";}
@Override
 public String messageInsertSuccess(){return "Sistem je zapamtio novog poslovnog partnera.";}
@Override
 public String messageInsertFailure(){return "Sistem ne moze da zapamti novog poslovnog partnera.(";}
@Override
 public String messageDeleteSuccess(){return "Sistem je obrisao poslovnog partnera.";}
@Override
 public String messageDeleteFailure(){return "Sistem ne moze da obrise poslovnog partnera.";}
@Override
 public String messageUpdateSuccess(){return "Sistem je promenio poslovnog partnera.";}
@Override
 public String messageUpdateFailure(){return "Sistem ne moze da promeni poslovnog partnera.";}
@Override
 public String messageFindSuccess(){return "Sistem je nasao poslovnog partnera.";}
@Override
 public String messageFindFailure(){return "Sistem ne moze da nadje poslovnog partnera.";}
@Override
 public String messageGetAllSuccess(){return "Sistem je vratio sve poslovne partnere.";}
@Override
 public String messageGetAllFailure(){return "Sistem ne moze da nadje i vrati poslovne partnere.";}
@Override
 public GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj)
 {  try { PoslovniPartner obj = new PoslovniPartner();
  obj.setSifraPartnera(jsobj.getInt("SifraPartnera"));     
  obj.setNazivPartnera(jsobj.getString("NazivPartnera"));     
  obj.setAdresa(jsobj.getString("Adresa"));     
  obj.setPIB(jsobj.getString("PIB"));     
 SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
  Date DatumOsnivanja = sm.parse((String)jsobj.get("DatumOsnivanja"));
 DatumOsnivanja = java.sql.Date.valueOf(sm.format(DatumOsnivanja));  
 obj.setDatumOsnivanja(DatumOsnivanja);     
  obj.setUkupniIznos(jsobj.getDouble("UkupniIznos"));     
  obj.setSifraGrada(jsobj.getInt("SifraGrada"));     
      return obj;
      } catch (JSONException ex) {
               Logger.getLogger(PoslovniPartner.class.getName()).log(Level.SEVERE, null, ex);
      } catch (Exception ex) {
              Logger.getLogger(PoslovniPartner.class.getName()).log(Level.SEVERE, null, ex);
      }
    return null;
}  
    
    
 @Override
    public String makeQuery(String criteria, String attribute)
    { String query = "Select * from PoslovniPartner";
      if (criteria.equals(""))
          return query;    
      switch (attribute)
         {
  case "SifraPartnera": query = query + " Where SifraPartnera = " + criteria; break; 
  case "NazivPartnera": query = query + " Where NazivPartnera like '%" + criteria + "%'"; break; 
  case "Adresa": query = query + " Where Adresa like '%" + criteria + "%'"; break; 
  case "PIB": query = query + " Where PIB like '%" + criteria + "%'"; break; 
  case "DatumOsnivanja": query = query + " Where DatumOsnivanja = " + criteria; break; 
  case "UkupniIznos": query = query + " Where UkupniIznos = " + criteria; break; 
  case "SifraGrada": query = "Select * from PoslovniPartner,Grad Where PoslovniPartner.SifraGrada = Grad.SifraGrada and NazivGrada like '%" + criteria + "%'";break; 
         }     
     return query;
    
    }
 
 @Override
  public String toString() { return NazivPartnera;}
 
}