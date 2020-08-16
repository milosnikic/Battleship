/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import Threads.Client;
import domain.Coordinates;
import domain.Map;
import java.util.Random;
import transfer.Request;
import transfer.Response;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author milos
 */
public class ServerShootSO extends AbstractGenericOperation {

    private Response response;
    private Request request;

    public Response serverShoot(Request request) {
        this.request = request;
        this.response = new Response();
        abstractExecuteSO();
        return response;
    }

    @Override
    public boolean executeSO() {
        Random random = new Random();
        Boolean hit;
        Coordinates coordinates;
        do {
            coordinates = new Coordinates(random.nextInt(10), random.nextInt(10));
            hit = Client.userMap.updateMapWithShot(coordinates);
        } while (hit == null);
        this.response.setHit(hit);
        this.response.setOperation(Operation.SERVER_SHOOT);
        this.response.setCoordinates(coordinates);
        this.response.setShip(hit ? Client.userMap.getShipAt(
                coordinates.getRow(),
                coordinates.getCol()) : null);
        this.response.setResponseStatus(ResponseStatus.OK);
        this.response.setUserPlaying(!hit);
        return true;
    }

}
