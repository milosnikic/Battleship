/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author milos
 */
public class Coordinates {

    private int row;
    private int col;

    public Coordinates() {
    }

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coordinates(Coordinates coordinates) {
        this.row = coordinates.getRow();
        this.col = coordinates.getCol();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
