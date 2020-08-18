/* BrokerBazePodataka1.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
 
package DBBroker;
 
 
import BLStructureGeneral.GeneralDObject;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
 
 
 
 
public class DBBroker extends GeneralDBBroker
{
   Connection conn = null;
   
   // Promenljivo!!! 
   @Override
    public boolean makeConnection() 
    {
        String Url;
        try {
             Class.forName("com.mysql.jdbc.Driver");
             Url = "jdbc:mysql://127.0.0.1:3306/DKExample73G";
             conn = DriverManager.getConnection(Url,"root","root");
             conn.setAutoCommit(false); // Ako se ovo ne uradi nece moci da se radi roolback.
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
  
  
    @Override
    public boolean insertRecord(GeneralDObject odo)
    {  
       String upit = "INSERT INTO " + odo.getClassName() +  " VALUES (" + odo.getAtrValue() + ")";
       return executeUpdate(upit);
    }
 
 
   
    @Override
    public boolean getCounter(GeneralDObject odo,AtomicInteger counter) 
    {  
        String sql = "SELECT Counter FROM Counter WHERE TableName = '" + odo.getClassName() + "'";
       
        ResultSet rs = null;
        Statement st = null;
               
        boolean signal = false; // record doesn't exist
        try  { st  = conn.prepareStatement(sql);
               rs = st.executeQuery(sql);
               signal = rs.next(); 
               counter.set(rs.getInt("Counter") + 1);
	     } catch (SQLException  ex)  
                { Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                  signal = false;
                } 
               finally {close(null,st,rs);}
        return signal;
       
    }
  
    @Override
    public boolean increaseCounter(GeneralDObject odo,AtomicInteger counter) 
    {   String  upit = "UPDATE Counter SET Counter =" + counter.get() + " WHERE TableName = '" + odo.getClassName() + "'";
       return executeUpdate(upit);
    }
  
    @Override
    public boolean deleteRecord(GeneralDObject odo) 
    {   String upit ="DELETE FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        return executeUpdate(upit);
    }
 
    @Override
    public boolean updateRecord(GeneralDObject odo,GeneralDObject odoold) 
    {   String  upit = "UPDATE " + odo.getClassName() +  " SET " + odo.setAtrValue() +   " WHERE " + odoold.getWhereCondition();
        return executeUpdate(upit);       
    }
 
    @Override
    public boolean updateRecord(GeneralDObject odo) 
    {   String  upit = "UPDATE " + odo.getClassName() +  " SET " + odo.setAtrValue() +   " WHERE " + odo.getWhereCondition();
        return executeUpdate(upit);       
    }
     
    public boolean executeUpdate(String upit) 
    {   Statement st=null;
        boolean signal = false;
  	try {   st  = conn.prepareStatement(upit);
                int rowcount = st.executeUpdate(upit);
                if (rowcount > 0) 
                    signal = true;    
	    } catch (SQLException ex)  
                {   Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                    signal = false;
                } finally {close(null,st,null);}
	return signal;
    }
   
    @Override
    public GeneralDObject findRecord(GeneralDObject odo) 
    {   ResultSet rs = null;
        Statement st = null;
        String  upit = "SELECT * FROM " + odo.getClassName() +  " WHERE " + odo.getWhereCondition();
        boolean signal; 
  	try {   st  = conn.prepareStatement(upit);
                rs = st.executeQuery(upit);
                signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
                if (signal == true)
                    odo = odo.getNewRecord(rs);
                else
                    odo = null;
	    } catch (SQLException  ex)  
               {   Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
               } finally {close(null,st,rs);}
	return odo;
    }
    
    
    public List<GeneralDObject> getAllRecords(GeneralDObject gdo) {
 
        List<GeneralDObject> l = new LinkedList<>();
        ResultSet rs = null;
        Statement st = null;
        
        String sql = "select * from " + gdo.getClassName();
        try {
            
            st = conn.prepareStatement(sql);
 
            rs = st.executeQuery(sql);
            while (rs.next()) {
                GeneralDObject a = gdo.getNewRecord(rs); 
                l.add(a);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            
        }  finally {close(null,st,rs);
           }
 
        return l;
 
} 
 
 
   public JSONArray getRecords(String sql) {
 
        JSONArray jsa = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            JSONOperations jsop = new JSONOperations();
            jsa = jsop.convertResultSetToJSON(rs); 
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (JSONException ex) {
           Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
       }  finally {close(null,st,rs);
           }
 
        return jsa;
 
}  
 
    
    
    
    
    
    
    @Override
    public boolean commitTransation()
    {   try { conn.commit();
	    } catch(SQLException esql){ return false; }
	return true;
    }
 
	
   @Override
    public boolean rollbackTransation()
    {   try { conn.rollback();
	    } catch(SQLException esql){ return false;  }
		   
	return true;
    }
        
    @Override
    public void closeConnection() 
    { close(conn,null,null);
    }
        
    public void close(Connection conn, Statement st, ResultSet rs) 
    { if (rs != null) 
        {  try { rs.close(); 
               } catch (SQLException ex) 
                   { Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);}
        }
        
      if (st != null) 
        { try { st.close();
              } catch (SQLException ex) 
                  { Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);}
        }
      if (conn != null) 
        { try { conn.close();
              } catch (SQLException ex) 
                  { Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
    
 // Preuzeto sa https://stackoverflow.com/questions/6514876/most-efficient-conversion-of-resultset-to-json
 // @Jim Cook.
 public static JSONArray convert( ResultSet rs )
    throws SQLException, JSONException
  {
    JSONArray json = new JSONArray();
    ResultSetMetaData rsmd = rs.getMetaData();   
    
    while(rs.next()) {
    int numColumns = rsmd.getColumnCount();
    JSONObject obj = new JSONObject();
 
  for( int i=1; i<numColumns+1; i++) {
    String column_name = rsmd.getColumnName(i);
 
    switch( rsmd.getColumnType( i ) ) {
      case java.sql.Types.ARRAY:
        obj.put(column_name, rs.getArray(column_name));     break;
      case java.sql.Types.BIGINT:
        obj.put(column_name, rs.getInt(column_name));       break;
      case java.sql.Types.BOOLEAN:
        obj.put(column_name, rs.getBoolean(column_name));   break;
      case java.sql.Types.BLOB:
        obj.put(column_name, rs.getBlob(column_name));      break;
      case java.sql.Types.DOUBLE:
        obj.put(column_name, rs.getDouble(column_name));    break;
      case java.sql.Types.FLOAT:
        obj.put(column_name, rs.getFloat(column_name));     break;
      case java.sql.Types.INTEGER:
        obj.put(column_name, rs.getInt(column_name));       break;
      case java.sql.Types.NVARCHAR:
        obj.put(column_name, rs.getNString(column_name));   break;
      case java.sql.Types.VARCHAR:
        obj.put(column_name, rs.getString(column_name));    break;
      case java.sql.Types.TINYINT:
        obj.put(column_name, rs.getInt(column_name));       break;
      case java.sql.Types.SMALLINT:
        obj.put(column_name, rs.getInt(column_name));       break;
      case java.sql.Types.DATE:
        obj.put(column_name, rs.getDate(column_name));      break;
      case java.sql.Types.TIMESTAMP:
        obj.put(column_name, rs.getTimestamp(column_name)); break;
      default:
        obj.put(column_name, rs.getObject(column_name));    break;
    }
  }
  json.put(obj);
 }
 return json; 
}
 
}