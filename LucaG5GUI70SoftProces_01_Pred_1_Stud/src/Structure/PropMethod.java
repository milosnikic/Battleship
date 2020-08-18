/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

public class PropMethod extends Property{
   public PropMethod(String property,LucaMethod lm) 
    {super(property,"");
     value = lm.run();
    } 
   
    public PropMethod(String property,Method met) 
    {super(property,"");
     value = met.execute();
    } 
   
}


