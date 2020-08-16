/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import domain.Coordinates;
import domain.FieldState;
import domain.Map;
import domain.Ship;
import java.util.LinkedList;
import java.util.Random;
import transfer.Request;
import transfer.Response;
import util.Operation;
import util.ResponseStatus;
import util.Ships;

/**
 *
 * @author milos
 */
public class StartGameSO extends AbstractGenericOperation {
    
    private LinkedList<Ship> ships = Ships.getShips();
    private Response response;
    private Request request;
    private boolean userPlaying;
    
    public Response startGame(Request request) {
        this.request = request;
        this.response = new Response();
        abstractExecuteSO();
        return response;
    }
    
    @Override
    public boolean executeSO() {
        Map serverMap = new Map();
        initializeServerMap(serverMap);
        this.response.setMap(serverMap);
        this.response.setOperation(Operation.START_GAME);
        this.response.setResponseStatus(ResponseStatus.OK);
        this.response.setUserPlaying(userPlaying);
        return true;
    }
    
    private void initializeServerMap(Map serverMap) {
        for (int i = 0; i < ships.size(); i++) {
            placeShip(ships.get(i), serverMap);
        }
    }
    
    private boolean placeShip(Ship ship, Map serverMap) {
        Random random = new Random();
        do {
            userPlaying = random.nextDouble() > 0.5;
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            boolean vertical = random.nextDouble() > 0.5;
            Coordinates coordinates = new Coordinates(row, col);
            ship.setCoordinates(coordinates);
            ship.setVertical(vertical);
        } while (!Ships.shipCanBePlaced(ship, ships, serverMap));
        setMapFields(ship, serverMap);
        return true;
    }
    
    private void setMapFields(Ship ship, Map serverMap) {
        int y = ship.getCoordinates().getCol();
        int x = ship.getCoordinates().getRow();
        if (ship.isVertical()) {
            for (int i = y; i < y + ship.getLength(); i++) {
                serverMap.setShipAt(x, i, ship);
            }
        } else {
            for (int i = x; i < x + ship.getLength(); i++) {
                serverMap.setShipAt(i, y, ship);
            }
        }
    }
    
}
