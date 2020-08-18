 /* FizickoLice.java 
 * @autor prof. dr Sinisa Vlajic,  
 * Univerzitet u Beogradu'
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-04-08 Problem oko brojaca fizickog lica.
 */
package DomenskeKlase;
 
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
 public class FizickoLice  extends GeneralDObject implements Serializable{
    public int idKorisnik; 
    public String imePrezime; 
    public int starost; 
    public Date datumRodjenja; 
 
    public FizickoLice()
        { 
           idKorisnik=0; 
           imePrezime=""; 
           starost=0; 
          SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
  datumRodjenja = new Date();
  datumRodjenja = java.sql.Date.valueOf(sm.format(datumRodjenja));  
         }
 
   public FizickoLice(int idKorisnik,  String imePrezime,  int starost,  Date datumRodjenja )  
       { 
           this.idKorisnik = idKorisnik; 
           this.imePrezime = imePrezime; 
           this.starost = starost; 
           this.datumRodjenja = datumRodjenja; 
       } 
      
    // primarni kljuc
    public FizickoLice( int idKorisnik)
      { this.idKorisnik = idKorisnik;
          }
    public void setID(int id)
      {  this.idKorisnik =id; }
 
   public int getPrimaryKey()
      {return this.idKorisnik;}  
 
 public  int getidKorisnik(){ return idKorisnik;}  
 public  String getimePrezime(){ return imePrezime;}  
 public  int getstarost(){ return starost;}  
 public  Date getdatumRodjenja(){ return datumRodjenja;}  
 
 public  void setidKorisnik(int idKorisnik){this.idKorisnik = idKorisnik;}  
 public  void setimePrezime(String imePrezime){this.imePrezime = imePrezime;}  
 public  void setstarost(int starost){this.starost = starost;}  
 public  void setdatumRodjenja(Date datumRodjenja){this.datumRodjenja = datumRodjenja;}  
    
 @Override 
 public GeneralDObject getNewRecord(ResultSet rs) throws SQLException 
 {return new FizickoLice(rs.getInt("idKorisnik"), rs.getString("imePrezime"), rs.getInt("starost"), rs.getDate("datumRodjenja"));} 
 
 @Override 
 public String getAtrValue() {return  idKorisnik + ", " +   (imePrezime == null ? null : "'" + imePrezime + "'") + ", " +   starost + ", " +   "'" + datumRodjenja + "'";} 
 @Override 
 public String setAtrValue(){return "idKorisnik=" +  idKorisnik + ", " +  "imePrezime=" +  (imePrezime == null ? null : "'" + imePrezime + "'") + ", " +  "starost=" +  starost + ", " +  "datumRodjenja=" +  "'" + datumRodjenja + "'";} 
 @Override 
 public String getClassName(){return "FizickoLice";} 
 @Override 
 public String getWhereCondition(){return "idKorisnik = " + idKorisnik;} 
 
@Override
public String getNameByColumn(int column) 
 { String names[] = {"idKorisnik", "imePrezime", "starost", "datumRodjenja"}; 
 return names[column]; 
 } 
 
public String[] getNameAtributes() 
 { String names[] = {"idKorisnik", "imePrezime", "starost", "datumRodjenja"}; 
 return names; 
 } 
    
    @Override
    public String poruka1() {return "Problem oko brojaca fizickog lica.";}
    @Override
   public String poruka2() {return "Ne moze da se poveca brojac fizickog lica.";}
    @Override
  public String poruka3() {return "Fizicko lice je kreirano.";}
    @Override
   public String poruka4() {return "Nije moglo da bude kreirano fizicko lice.";}
    @Override
    public String poruka5() {return "Korisnik je obrisan.";}
    @Override
    public String poruka6() {return "Nije moglo da se obrise fizicko lice.";}
    @Override
    public String poruka7() {return "Ne moze se obrisati fizicko lice jer ne postoji.";}
    @Override
    public String poruka8() {return "Fizicko lice je promenjeno.";}
    @Override
    public String poruka9() {return "Nije moglo da se promeni fizicko lice.";}
    @Override
    public String poruka10() {return "Ne moze se promeniti fizicko lice jer ne postoji.";}
 
   
}