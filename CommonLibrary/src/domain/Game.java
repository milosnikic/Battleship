/* Game.java 
 * @autor Milos Nikic,  
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo 
 * Laboratorija za softversko inzenjerstvo 
 * Datum:2020-08-15 poruka1
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Game extends GeneralDObject implements Serializable {

    public int idGame;
    public int idUser;
    public int idWinner;
    public boolean end;
    public int numberOfFieldsLeft;
    public int numberOfFieldsHit;
    public int score;

    public Game() {
        idGame = 0;
        idUser = 0;
        idWinner = 0;
        end = false;
        numberOfFieldsLeft = 0;
        numberOfFieldsHit = 0;
        score = 0;
    }

    public Game(int idGame, int idUser, int idWinner, boolean end, int numberOfFieldsLeft, int numberOfFieldsHit, int score) {
        this.idGame = idGame;
        this.idUser = idUser;
        this.idWinner = idWinner;
        this.end = end;
        this.numberOfFieldsLeft = numberOfFieldsLeft;
        this.numberOfFieldsHit = numberOfFieldsHit;
        this.score = score;
    }

    // primarni kljuc
    public Game(int idGame) {
        this.idGame = idGame;
    }

    public void setID(int id) {
        this.idGame = id;
    }

    public int getPrimaryKey() {
        return this.idGame;
    }

    public int getidGame() {
        return idGame;
    }

    public int getidUser() {
        return idUser;
    }

    public int getidWinner() {
        return idWinner;
    }

    public boolean getend() {
        return end;
    }

    public int getnumberOfFieldsLeft() {
        return numberOfFieldsLeft;
    }

    public int getnumberOfFieldsHit() {
        return numberOfFieldsHit;
    }

    public int getscore() {
        return score;
    }

    public void setidGame(int idGame) {
        this.idGame = idGame;
    }

    public void setidUser(int idUser) {
        this.idUser = idUser;
    }

    public void setidWinner(int idWinner) {
        this.idWinner = idWinner;
    }

    public void setend(boolean end) {
        this.end = end;
    }

    public void setnumberOfFieldsLeft(int numberOfFieldsLeft) {
        this.numberOfFieldsLeft = numberOfFieldsLeft;
    }

    public void setnumberOfFieldsHit(int numberOfFieldsHit) {
        this.numberOfFieldsHit = numberOfFieldsHit;
    }

    public void setscore(int score) {
        this.score = score;
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Game(rs.getInt("idGame"), rs.getInt("idUser"), rs.getInt("idWinner"), rs.getBoolean("end"), rs.getInt("numberOfFieldsLeft"), rs.getInt("numberOfFieldsHit"), rs.getInt("score"));
    }

    @Override
    public String getAtrValue() {
        return idUser + ", " + idWinner + ", " + end + ", " + numberOfFieldsLeft + ", " + numberOfFieldsHit + ", " + score;
    }

    @Override
    public String setAtrValue() {
        return "idGame=" + idGame + ", " + "idUser=" + idUser + ", " + "idWinner=" + idWinner + ", " + "end=" + end + ", " + "numberOfFieldsLeft=" + numberOfFieldsLeft + ", " + "numberOfFieldsHit=" + numberOfFieldsHit + ", " + "score=" + score;
    }

    @Override
    public String getClassName() {
        return "Game";
    }

    @Override
    public String getWhereCondition() {
        return "idGame = " + idGame;
    }

    @Override
    public String getNameByColumn(int column) {
        String names[] = {"idGame", "idUser", "idWinner", "end", "numberOfFieldsLeft", "numberOfFieldsHit", "score"};
        return names[column];
    }

    public String[] getNameAtributes() {
        String names[] = {"idGame", "idUser", "idWinner", "end", "numberOfFieldsLeft", "numberOfFieldsHit", "score"};
        return names;
    }

    @Override
    public String getColumnNames() {
        return " (idUser, idWinner, end, numberOfFieldsLeft, numberOfFieldsHit, score)";
    }

}
