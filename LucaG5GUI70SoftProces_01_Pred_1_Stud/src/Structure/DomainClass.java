/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Structure;

import Structures.BaseStructure;

public class DomainClass {
    String name;
    BaseStructure bs;
    public DomainClass (String name, BaseStructure bs) {this.name = name; this.bs = bs;}
    public String getName() {return name;}
    public BaseStructure getStructure(){return bs;}
}
