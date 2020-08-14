/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseBroker;

import domain.GeneralDObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class DatabaseBroker implements IDatabaseBroker {

    /**
     * Connection to database
     */
    Connection connection = null;

    
    /**
     * Method is used to make connection to database
     * @return result of connecting operation
     */
    @Override
    public boolean makeConnection() {
        String url;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://127.0.0.1:3306/battleship";
            connection = DriverManager.getConnection(url, "root", "");
            connection.setAutoCommit(false); // Ako se ovo ne uradi nece moci da se radi roolback.
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Method is used to insert new record
     * @param odo General object
     * @return result of insert operation
     */
    @Override
    public boolean insertRecord(GeneralDObject odo) {
        String query = "INSERT INTO " + odo.getClassName() + odo.getColumnNames() + " VALUES (" + odo.getAtrValue() + ")";
        return executeUpdate(query);
    }

    /**
     * Method is used to update record
     * @param odo new object 
     * @param odoold object that is being updated
     * @return result of update operation
     */
    @Override
    public boolean updateRecord(GeneralDObject odo, GeneralDObject odoold) {
        String query = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odoold.getWhereCondition();
        return executeUpdate(query);
    }

    /**
     * Method is used to update record
     * @param odo object that is being updated
     * @return result of update operation
     */
    @Override
    public boolean updateRecord(GeneralDObject odo) {
        String query = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odo.getWhereCondition();
        return executeUpdate(query);
    }

    /**
     * Method is used to delete record
     * @param odo general object
     * @return result of delete operation
     */
    @Override
    public boolean deleteRecord(GeneralDObject odo) {
        String query = "DELETE FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        return executeUpdate(query);
    }

    /**
     * Method is used to delete records
     * @param odo general object
     * @param where where clause
     * @return result of delete operation
     */
    @Override
    public boolean deleteRecords(GeneralDObject odo, String where) {
        String query = "DELETE FROM " + odo.getClassName() + " " + where;
        return executeUpdate(query);
    }

    /**
     * Method is used to find record by its clause
     * @param odo general object
     * @return object if found, null otherwise
     */
    @Override
    public GeneralDObject findRecord(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        String query = "SELECT * FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        boolean signal;
        try {
            st = connection.prepareStatement(query);
            rs = st.executeQuery(query);
            signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
            if (signal == true) {
                odo = odo.getNewRecord(rs);
            } else {
                odo = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return odo;
    }

    /**
     * Method is used to find records
     * @param odo general object
     * @param where where clause
     * @return list of objects that match where clause
     */
    @Override
    public List<GeneralDObject> findRecords(GeneralDObject odo, String where) {
        ResultSet rs = null;
        Statement st = null;
        String query = "SELECT * FROM " + odo.getClassName() + " " + where;
        List<GeneralDObject> ls = new ArrayList<>();

        try {
            st = connection.prepareStatement(query);
            rs = st.executeQuery(query);
            while (rs.next()) {
                ls.add(odo.getNewRecord(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return ls;
    }

    /**
     * Method is used to commit transaction
     * @return result of committing operation
     */
    @Override
    public boolean commitTransation() {
        try {
            connection.commit();
        } catch (SQLException esql) {
            return false;
        }
        return true;
    }

    /**
     * Method is used to roll back transaction
     * @return result of rolling back operation
     */
    @Override
    public boolean rollbackTransation() {
        try {
            connection.rollback();
        } catch (SQLException esql) {
            return false;
        }
        return true;
    }

    /**
     * Method is used to increase counter
     * @param odo general object
     * @param counter counter object
     * @return result of increase counter operation
     */
    @Override
    public boolean increaseCounter(GeneralDObject odo, AtomicInteger counter) {
        String query = "UPDATE Counter SET Counter =" + counter.get() + " WHERE TableName = '" + odo.getClassName() + "'";
        return executeUpdate(query);
    }

    /**
     * Method is used to close connection to database
     */
    @Override
    public void closeConnection() {
        close(connection, null, null);
    }

    /**
     * Method is used to get record
     * @param odo general object
     * @param index index of object
     * @return object if found, otherwise null
     */
    @Override
    public GeneralDObject getRecord(GeneralDObject odo, int index) {
        ResultSet rs = null;
        Statement st = null;
        String query = "SELECT * FROM " + odo.getClassName();
        query = query + " order by " + odo.getNameByColumn(0) + " ASC LIMIT " + index + ",1";
        boolean signal;
        try {
            st = connection.prepareStatement(query);
            rs = st.executeQuery(query);
            signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
            if (signal == true) {
                odo = odo.getNewRecord(rs);
            } else {
                odo = null;
            }
        } catch (SQLException ex) {
            odo = null;
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return odo;
    }

    /**
     * Method is used to get records number
     * @param odo general object
     * @return number of records for specified object
     */
    @Override
    public int getRecordsNumber(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        int recordsNumber = 0;
        String query = "SELECT * FROM " + odo.getClassName();
        boolean signal;
        try {
            st = connection.prepareStatement(query);
            rs = st.executeQuery(query);
            if (rs.last()) {
                recordsNumber = rs.getRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return recordsNumber;
    }

    /**
     * Method is used to execute INSERT,UPDATE,DELETE operations
     * @param query 
     * @return result of execute update operation
     */
    private boolean executeUpdate(String query) {
        Statement st = null;
        boolean signal = false;
        try {
            st = connection.prepareStatement(query);
            int rowcount = st.executeUpdate(query);
            if (rowcount > 0) {
                signal = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            signal = false;
        } finally {
            close(null, st, null);
        }
        return signal;
    }

    /**
     * Method used to close database connection
     *
     * @param connection connection that is going to be closed
     * @param st statement object
     * @param rs result set object
     */
    public void close(Connection connection, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
