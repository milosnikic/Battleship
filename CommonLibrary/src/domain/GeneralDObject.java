/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author milos
 */
public abstract class GeneralDObject {

    abstract public String getAtrValue();

    abstract public String setAtrValue();

    abstract public String getClassName();

    abstract public String getWhereCondition();

    abstract public String getNameByColumn(int column);

    abstract public String getColumnNames();

    abstract public GeneralDObject getNewRecord(ResultSet rs) throws SQLException;

    abstract public int getPrimaryKey();

    abstract public void setID(int id);
}
