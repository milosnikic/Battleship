/* Autor programa:  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package GUI;

public interface IEkranskaForma {
    public IPanel getPanel();  
    public void setPanel(IPanel pan1); 
    public void prikaziEkranskuFormu();
    public void odrediDekoracijuForme();
}

