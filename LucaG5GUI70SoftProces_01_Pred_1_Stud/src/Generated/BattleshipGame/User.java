 /* User.java 
 * @autor Milos Nikic,  
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-08-15 Problem oko brojaca korisnika.
 */
package DomenskeKlase;
 
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
 public class User  extends GeneralDObject implements Serializable{
    public int idUser; 
    public String username; 
    public String password; 
 
    public User()
        { 
           idUser=0; 
           username=""; 
           password=""; 
         }
 
   public User(int idUser,  String username,  String password )  
       { 
           this.idUser = idUser; 
           this.username = username; 
           this.password = password; 
       } 
      
    // primarni kljuc
    public User( int idUser)
      { this.idUser = idUser;
          }
    public void setID(int id)
      {  this.idUser =id; }
 
   public int getPrimaryKey()
      {return this.idUser;}  
 
 public  int getidUser(){ return idUser;}  
 public  String getusername(){ return username;}  
 public  String getpassword(){ return password;}  
 
 public  void setidUser(int idUser){this.idUser = idUser;}  
 public  void setusername(String username){this.username = username;}  
 public  void setpassword(String password){this.password = password;}  
    
 @Override 
 public GeneralDObject getNewRecord(ResultSet rs) throws SQLException 
 {return new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"));} 
 
 @Override 
 public String getAtrValue() {return  idUser + ", " +   (username == null ? null : "'" + username + "'") + ", " +   (password == null ? null : "'" + password + "'");} 
 @Override 
 public String setAtrValue(){return "idUser=" +  idUser + ", " +  "username=" +  (username == null ? null : "'" + username + "'") + ", " +  "password=" +  (password == null ? null : "'" + password + "'");} 
 @Override 
 public String getClassName(){return "User";} 
 @Override 
 public String getWhereCondition(){return "idUser = " + idUser;} 
 
@Override
public String getNameByColumn(int column) 
 { String names[] = {"idUser", "username", "password"}; 
 return names[column]; 
 } 
 
public String[] getNameAtributes() 
 { String names[] = {"idUser", "username", "password"}; 
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