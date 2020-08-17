/* User.java 
 * @autor Milos Nikic,  
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-08-14 Problem oko brojaca korisnika.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class User extends GeneralDObject implements Serializable {

    public int id;
    public String username;
    public String password;

    public User() {
        id = 0;
        username = "";
        password = "";
    }

    public User(int idUser, String username, String password) {
        this.id = idUser;
        this.username = username;
        this.password = password;
    }

    // primarni kljuc
    public User(int idUser) {
        this.id = idUser;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getPrimaryKey() {
        return this.id;
    }

    public int getIdUser() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setIdUser(int idUser) {
        this.id = idUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
    }

    @Override
    public String getAtrValue() {
        return (username == null ? null : "'" + username + "'") + ", " + (password == null ? null : "'" + password + "'");
    }

    @Override
    public String setAtrValue() {
        return "username=" + (username == null ? null : "'" + username + "'") + ", " + "password=" + (password == null ? null : "'" + password + "'");
    }

    @Override
    public String getClassName() {
        return "User";
    }

    @Override
    public String getWhereCondition() {
        return "username = '" + username + "' AND " + "password = '" + password + "'";
    }

    @Override
    public String getNameByColumn(int column) {
        String names[] = {"idUser", "username", "password"};
        return names[column];
    }

    public String[] getNameAtributes() {
        String names[] = {"idUser", "username", "password"};
        return names;
    }

    @Override
    public String getColumnNames() {
        return " (username, password) ";
    }

}
