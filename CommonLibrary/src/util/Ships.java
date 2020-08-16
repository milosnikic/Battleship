/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Coordinates;
import domain.FieldState;
import domain.Map;
import domain.Ship;
import java.util.LinkedList;

/**
 *
 * @author milos
 */
public class Ships {

    public static LinkedList<Ship> getShips() {
        LinkedList<Ship> ships = new LinkedList<>();
        ships.add(new Ship("Battleship - 4", 4));
        ships.add(new Ship("Cruiser 1 - 3", 3));
        ships.add(new Ship("Cruiser 2 - 3", 3));
        ships.add(new Ship("Destroyer 1 - 2", 2));
        ships.add(new Ship("Destroyer 2 - 2", 2));
        ships.add(new Ship("Destroyer 3 - 2", 2));
        ships.add(new Ship("Singleton 1 - 1", 1));
        ships.add(new Ship("Singleton 2 - 1", 1));
        ships.add(new Ship("Singleton 3 - 1", 1));
        ships.add(new Ship("Singleton 4 - 1", 1));

        return ships;
    }

    public static boolean shipCanBePlaced(Ship ship, LinkedList<Ship> ships, Map yourMap) {
        if (!ships.isEmpty()) {
            int y = ship.getCoordinates().getCol();
            int x = ship.getCoordinates().getRow();
            if (ship.isVertical()) {
                for (int i = y; i < y + ship.getLength(); i++) {
                    if (!isValidPoint(new Coordinates(x, i))) {
                        return false;
                    }
                    if (yourMap.getShipAt(x, i) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = x; i < x + ship.getLength(); i++) {
                    if (!isValidPoint(new Coordinates(i, y))) {
                        return false;
                    }
                    if (yourMap.getShipAt(i, y) != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidPoint(Coordinates coordinates) {
        return coordinates.getRow() >= 0 && coordinates.getRow() < 10
                && coordinates.getCol() >= 0 && coordinates.getCol() < 10;
    }
}
