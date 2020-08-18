/* GeneralDObject.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package BLStructureGeneral;




import java.sql.*;
import org.json.JSONObject;

// Operacije navedenog interfejsa je potrebno da implementira svaka od domenskih klasa,
// koja zeli da joj bude omogucena komunikacija sa Database broker klasom.
public interface GeneralDObject
{ String getAtrValue();
  String setAtrValue();
  String getClassName();
  String getWhereCondition();
  String getNameByColumn(int column);
  GeneralDObject getNewRecord(ResultSet rs) throws SQLException;
  public abstract void println();
  public abstract String messageCreateSuccess();
  public abstract  String messageCreateFailure();
  public abstract  String messageInsertSuccess();
  public abstract  String messageInsertFailure();
  public abstract  String messageDeleteSuccess();
  public abstract  String messageDeleteFailure();
  public abstract  String messageUpdateSuccess();
  public abstract  String messageUpdateFailure();
  public abstract  String messageFindSuccess();
  public abstract  String messageFindFailure();
  public abstract  String messageGetAllSuccess();
  public abstract String messageGetAllFailure();
  public abstract void setPrimaryKey(int value);
  public abstract GeneralDObject createDomainObject();
  public abstract int getPrimaryKey();
  public abstract String getPrimaryKeyName();
  public abstract GeneralDObject convertJSONObjectToDomainObject(JSONObject jsobj);
  public abstract String makeQuery(String criteria, String attribute);
  public abstract String[] getNameAtributes(); 
}