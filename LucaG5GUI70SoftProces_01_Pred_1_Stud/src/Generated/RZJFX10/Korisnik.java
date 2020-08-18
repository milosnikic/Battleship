 /* Korisnik.java 
 * @autor prof. dr Sinisa Vlajic,  
 * Univerzitet u Beogradu'
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-04-08 Problem oko brojaca korisnika.
 */
package DomenskeKlase;
 
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
 public class Korisnik  extends GeneralDObject implements Serializable{
    public int idKorisnik; 
    public String korisnickoIme; 
    public String sifra; 
    public String ime; 
    public String prezime; 
    public Date datumRodjenja; 
 
    public Korisnik()
        { 
           idKorisnik=0; 
           korisnickoIme=""; 
           sifra=""; 
           ime=""; 
           prezime=""; 
          SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
  datumRodjenja = new Date();
  datumRodjenja = java.sql.Date.valueOf(sm.format(datumRodjenja));  
         }
 
   public Korisnik(int idKorisnik,  String korisnickoIme,  String sifra,  String ime,  String prezime,  Date datumRodjenja )  
       { 
           this.idKorisnik = idKorisnik; 
           this.korisnickoIme = korisnickoIme; 
           this.sifra = sifra; 
           this.ime = ime; 
           this.prezime = prezime; 
           this.datumRodjenja = datumRodjenja; 
       } 
      
    // primarni kljuc
    public Korisnik( int idKorisnik)
      { this.idKorisnik = idKorisnik;
          }
    public void setID(int id)
      {  this.idKorisnik =id; }
 
   public int getPrimaryKey()
      {return this.idKorisnik;}  
 
 public  int getidKorisnik(){ return idKorisnik;}  
 public  String getkorisnickoIme(){ return korisnickoIme;}  
 public  String getsifra(){ return sifra;}  
 public  String getime(){ return ime;}  
 public  String getprezime(){ return prezime;}  
 public  Date getdatumRodjenja(){ return datumRodjenja;}  
 
 public  void setidKorisnik(int idKorisnik){this.idKorisnik = idKorisnik;}  
 public  void setkorisnickoIme(String korisnickoIme){this.korisnickoIme = korisnickoIme;}  
 public  void setsifra(String sifra){this.sifra = sifra;}  
 public  void setime(String ime){this.ime = ime;}  
 public  void setprezime(String prezime){this.prezime = prezime;}  
 public  void setdatumRodjenja(Date datumRodjenja){this.datumRodjenja = datumRodjenja;}  
    
 @Override 
 public GeneralDObject getNewRecord(ResultSet rs) throws SQLException 
 {return new Korisnik(rs.getInt("idKorisnik"), rs.getString("korisnickoIme"), rs.getString("sifra"), rs.getString("ime"), rs.getString("prezime"), rs.getDate("datumRodjenja"));} 
 
 @Override 
 public String getAtrValue() {return  idKorisnik + ", " +   (korisnickoIme == null ? null : "'" + korisnickoIme + "'") + ", " +   (sifra == null ? null : "'" + sifra + "'") + ", " +   (ime == null ? null : "'" + ime + "'") + ", " +   (prezime == null ? null : "'" + prezime + "'") + ", " +   "'" + datumRodjenja + "'";} 
 @Override 
 public String setAtrValue(){return "idKorisnik=" +  idKorisnik + ", " +  "korisnickoIme=" +  (korisnickoIme == null ? null : "'" + korisnickoIme + "'") + ", " +  "sifra=" +  (sifra == null ? null : "'" + sifra + "'") + ", " +  "ime=" +  (ime == null ? null : "'" + ime + "'") + ", " +  "prezime=" +  (prezime == null ? null : "'" + prezime + "'") + ", " +  "datumRodjenja=" +  "'" + datumRodjenja + "'";} 
 @Override 
 public String getClassName(){return "Korisnik";} 
 @Override 
 public String getWhereCondition(){return "idKorisnik = " + idKorisnik;} 
 
@Override
public String getNameByColumn(int column) 
 { String names[] = {"idKorisnik", "korisnickoIme", "sifra", "ime", "prezime", "datumRodjenja"}; 
 return names[column]; 
 } 
 
public String[] getNameAtributes() 
 { String names[] = {"idKorisnik", "korisnickoIme", "sifra", "ime", "prezime", "datumRodjenja"}; 
 return names; 
 } 
    
    @Override
    public String poruka1() {return "Problem oko brojaca korisnika.";}
    @Override
   public String poruka2() {return "Ne moze da se poveca brojac korisnika.";}
    @Override
  public String poruka3() {return "Korisnik je kreiran.";}
    @Override
   public String poruka4() {return "Nije mogao da bude kreiran korisnik.";}
    @Override
    public String poruka5() {return "Korisnik je obrisan.";}
    @Override
    public String poruka6() {return "Nije mogao da se obrise korisnik.";}
    @Override
    public String poruka7() {return "Ne moze se obrisati korisnik jer ne postoji.";}
    @Override
    public String poruka8() {return "Korisnik je promenjen.";}
    @Override
    public String poruka9() {return "Nije mogao da se promeni korisnik.";}
    @Override
    public String poruka10() {return "Ne moze se promeniti korisnik jer ne postoji.";}
 
   
}