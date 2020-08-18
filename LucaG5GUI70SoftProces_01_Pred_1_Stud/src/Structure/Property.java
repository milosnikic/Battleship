/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import java.io.Serializable;

public class Property implements Serializable
{ public String name;
  public String value;
  public boolean loaded;
  
  
  public Property(String name,String value)
    {this.name = name; this.value = value; loaded = false;}
  public void setValue(String value)
    {this.value = value;}     
  public void setLoad(boolean loaded)
    {this.loaded = loaded;}     
  
}


