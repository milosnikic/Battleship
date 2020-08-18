/* JSONOperations.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 23.01.2018
 */

package DBBroker;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Sinisa
 */


interface rsGetOperation
     { Object get(ResultSet rs, String column_name) throws SQLException;
     }

class JSONResultSet
   {
     int SQLType;
     rsGetOperation rsgo;
     public JSONResultSet(int SQLType,rsGetOperation rsgo) {this.SQLType = SQLType; this.rsgo = rsgo;}
     public int getSQLType(){return SQLType;}
     public rsGetOperation getRsGetOperation(){return rsgo;}
   }

public class JSONOperations {
   List <JSONResultSet> ljsrs;
    
   public JSONOperations()
   {
    ljsrs = new ArrayList<>();
    ljsrs.add(new JSONResultSet(java.sql.Types.ARRAY, (ResultSet rs,String column_name) -> rs.getArray(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.BIGINT, (ResultSet rs,String column_name) -> rs.getInt(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.BOOLEAN, (ResultSet rs,String column_name) -> rs.getBoolean(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.BLOB, (ResultSet rs,String column_name) -> rs.getBlob(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.DOUBLE, (ResultSet rs,String column_name) -> rs.getDouble(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.FLOAT, (ResultSet rs,String column_name) -> rs.getFloat(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.INTEGER, (ResultSet rs,String column_name) -> rs.getInt(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.NVARCHAR, (ResultSet rs,String column_name) -> rs.getNString(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.VARCHAR, (ResultSet rs,String column_name) -> rs.getString(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.TINYINT, (ResultSet rs,String column_name) -> rs.getInt(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.SMALLINT, (ResultSet rs,String column_name) -> rs.getInt(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.DATE, (ResultSet rs,String column_name) -> rs.getDate(column_name)));
    ljsrs.add(new JSONResultSet(java.sql.Types.TIMESTAMP, (ResultSet rs,String column_name) -> rs.getTimestamp(column_name)));
   
   }
    
    
public JSONArray convertResultSetToJSON( ResultSet rs )  throws SQLException, JSONException
  { JSONArray json = new JSONArray();
    ResultSetMetaData rsmd = rs.getMetaData();   
        
    while(rs.next()) 
      {
          int numColumns = rsmd.getColumnCount();
          JSONObject obj = new JSONObject();

          for( int i=1; i<numColumns+1; i++) 
            {  String column_name = rsmd.getColumnName(i);
               boolean signal = false;
               for(JSONResultSet jrs:ljsrs)
                   { if (jrs.getSQLType() == rsmd.getColumnType(i))
                        { obj.put(column_name, jrs.getRsGetOperation().get(rs,column_name)); signal = true; break;} 
                   }
               if (signal == false)
                   obj.put(column_name, rs.getObject(column_name)); 
            }
         json.put(obj);
     }
 return json; 
} 
    
    
}





