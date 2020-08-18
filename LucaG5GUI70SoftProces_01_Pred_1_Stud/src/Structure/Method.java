/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Method {
    String name;
    
    public Method(String name) {this.name = name;}
    
    String execute()
    {
        switch (name)
        { case "getDate": return GlobalLucaG.getDate(); 
          default: 
              System.out.println("Method:" + name + " doesn't exist!!!");
              return ""; 
                
        }
     }
}

class GlobalLucaG
{
 public static String getDate()
 {
   SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
   Date dDatum = new Date();
   return sm.format(dDatum);
  }

}





