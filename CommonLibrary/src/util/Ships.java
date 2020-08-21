/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Coordinates;
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
//        ships.add(new Ship("Cruiser 1 - 3", 3));
//        ships.add(new Ship("Cruiser 2 - 3", 3));
//        ships.add(new Ship("Destroyer 1 - 2", 2));
//        ships.add(new Ship("Destroyer 2 - 2", 2));
//        ships.add(new Ship("Destroyer 3 - 2", 2));
//        ships.add(new Ship("Singleton 1 - 1", 1));
//        ships.add(new Ship("Singleton 2 - 1", 1));
//        ships.add(new Ship("Singleton 3 - 1", 1));
//        ships.add(new Ship("Singleton 4 - 1", 1));

        return ships;
    }

    public static boolean shipCanBePlaced(Ship ship, LinkedList<Ship> ships, Map yourMap) {
        if (!ships.isEmpty()) {
            int y = ship.getCoordinates().getCol();
            int x = ship.getCoordinates().getRow();

            if (!ship.isVertical()) {
                for (int i = y; i < y + ship.getLength(); i++) {
                    if (!isValidPoint(new Coordinates(x, i))) {
                        return false;
                    }
                    if (yourMap.getShipAt(x, i) != null) {
                        return false;
                    }
                    for (Ship neighbour : getNeighbours(x, i, yourMap)) {
                        if (neighbour != null && neighbour != ship) {
                            return false;
                        }
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
                    for (Ship neighbour : getNeighbours(i, y, yourMap)) {
                        if (neighbour != null && neighbour != ship) {
                            return false;
                        }
                    }
                }
            }
            return true;

        }
        return false;
    }

    private static boolean isValidPoint(Coordinates coordinates) {
        return coordinates.getRow() >= 0 && coordinates.getRow() < 10
                && coordinates.getCol() >= 0 && coordinates.getCol() < 10;
    }

    private static LinkedList<Ship> getNeighbours(int row, int col, Map yourMap) {
        LinkedList<Ship> neighbours = new LinkedList<>();

        if (isValidPoint(new Coordinates(row - 1, col - 1))) {
            Ship ship = yourMap.getShipAt(row - 1, col - 1);
            neighbours.add(ship);
        }
        if (isValidPoint(new Coordinates(row - 1, col + 1))) {
            Ship ship = yourMap.getShipAt(row - 1, col + 1);
            neighbours.add(ship);
        }
        if (isValidPoint(new Coordinates(row - 1, col))) {
            Ship ship = yourMap.getShipAt(row - 1, col);
            neighbours.add(ship);
        }
        if (isValidPoint(new Coordinates(row + 1, col))) {
            Ship ship = yourMap.getShipAt(row + 1, col);
            neighbours.add(ship);
        }
        if (isValidPoint(new Coordinates(row, col - 1))) {
            Ship ship = yourMap.getShipAt(row, col - 1);
            neighbours.add(ship);
        }
        if (isValidPoint(new Coordinates(row, col + 1))) {
            Ship ship = yourMap.getShipAt(row, col + 1);
            neighbours.add(ship);
        }
        if (isValidPoint(new Coordinates(row + 1, col + 1))) {
            Ship ship = yourMap.getShipAt(row + 1, col + 1);
            neighbours.add(ship);
        }
        if (isValidPoint(new Coordinates(row + 1, col - 1))) {
            Ship ship = yourMap.getShipAt(row + 1, col - 1);
            neighbours.add(ship);
        }
        return neighbours;
    }
}
