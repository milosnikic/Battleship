/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

public class PropFile extends Property{
   public PropFile(String property,FileURL furl) 
    {super(property,"");
     value = furl.readFile();
    } 
}
