/* BrokerBazePodataka.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package DBBroker;

import BLStructureGeneral.GeneralDObject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;



public abstract class GeneralDBBroker 
{   public abstract boolean makeConnection();
    public abstract boolean insertRecord(GeneralDObject odo);
    public abstract boolean updateRecord(GeneralDObject odo,GeneralDObject odoold);
    public abstract boolean updateRecord(GeneralDObject odo); 
    public abstract boolean deleteRecord(GeneralDObject odo);
    public abstract GeneralDObject findRecord(GeneralDObject odo);
    public abstract boolean commitTransation();
    public abstract boolean rollbackTransation();
    public abstract boolean getCounter(GeneralDObject odo,AtomicInteger counter); 
    public abstract boolean increaseCounter(GeneralDObject odo,AtomicInteger counter); 
    public abstract void closeConnection();
    public abstract List<GeneralDObject> getAllRecords(GeneralDObject gdo); 
    public abstract JSONArray getRecords(String sql);
}
