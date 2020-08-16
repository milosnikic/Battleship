/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import Threads.Client;
import domain.Map;
import transfer.Request;
import transfer.Response;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author milos
 */
public class UserShootSO extends AbstractGenericOperation {

    private Response response;
    private Request request;

    public Response userShoot(Request request) {
        this.request = request;
        this.response = new Response();
        abstractExecuteSO();
        return response;
    }

    @Override
    public boolean executeSO() {
        Boolean hit = Client.serverMap.updateMapWithShot(request.getCoordinates());
        this.response.setHit(hit);
        this.response.setOperation(Operation.USER_SHOOT);
        this.response.setCoordinates(this.request.getCoordinates());
        this.response.setShip(hit ? Client.serverMap.getShipAt(
                this.request.getCoordinates().getRow(),
                this.request.getCoordinates().getCol()) : null);
        this.response.setResponseStatus(ResponseStatus.OK);
        this.response.setUserPlaying(hit);
        return true;
    }

}
