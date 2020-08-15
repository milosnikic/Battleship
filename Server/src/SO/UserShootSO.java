/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

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
    private Map serverMap;

    public Response userShoot(Request request, Map serverMap) {
        this.request = request;
        this.serverMap = serverMap;
        this.response = new Response();
        abstractExecuteSO();
        return response;
    }

    @Override
    public boolean executeSO() {
        Boolean hit = serverMap.updateMapWithShot(request.getCoordinates());
        this.response.setHit(hit);
        this.response.setOperation(Operation.USER_SHOOT);
        this.response.setCoordinates(this.request.getCoordinates());
        this.response.setResponseStatus(ResponseStatus.OK);
        this.response.setUserPlaying(hit);
        return true;
    }

}
