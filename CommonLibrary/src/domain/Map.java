/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author milos
 */
public class Map implements Serializable {

    private Ship[][] grid;

    public Map() {
        grid = new Ship[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = null;
            }
        }
    }

    public Map(Map otherMap) {
        grid = new Ship[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = otherMap.getShipAt(i, j);
            }
        }
    }

    public Ship[][] getGrid() {
        return grid;
    }

    public void setGrid(Ship[][] newGrid) {
        this.grid = newGrid;
    }

    public Ship getShipAt(int row, int col) {
        return grid[row][col];
    }

    public void setShipAt(int row, int col, Ship ship) {
        grid[row][col] = ship;
    }

    /**
     * Method is used to count fields with specified state
     *
     * @param fieldState state to be counted
     * @return number of fields satisfying condition
     */
    public int countFields(FieldState fieldState) {
        ArrayList<Ship> aliveShips = new ArrayList<>();
        int fieldsAlive = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Ship ship = grid[i][j];
                if (!aliveShips.contains(ship) && ship.isAlive()) {
                    aliveShips.add(ship);
                    fieldsAlive += ship.getFieldsAlive();
                }
            }
        }

        return fieldsAlive;
    }

    public Boolean updateMapWithShot(Coordinates coordinates) {
        int row = coordinates.getRow();
        int col = coordinates.getCol();

        Ship ship = getShipAt(row, col);
        if (ship != null) {
            ship.hit();
            return true;
        }

        return false;
    }

    /**
     * Method is used to count number of ships alive
     *
     * @return number of ships alive
     */
    public int getNumberOfShipsAlive() {
        ArrayList<Ship> aliveShips = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Ship ship = grid[i][j];
                if (!aliveShips.contains(ship) && ship.isAlive()) {
                    aliveShips.add(ship);
                }
            }
        }
        return aliveShips.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Map map = (Map) o;

        return Arrays.deepEquals(getGrid(), map.getGrid());
    }
}
