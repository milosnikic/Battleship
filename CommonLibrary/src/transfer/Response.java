/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import domain.Map;
import util.ResponseStatus;
import domain.User;
import java.io.Serializable;
import util.Operation;

/**
 *
 * @author milos
 */
public class Response implements Serializable {

    private User user;
    private Operation operation;
    private ResponseStatus responseStatus;
    private Map map;
    private boolean userPlaying;

    public Response() {
    }

    public boolean isUserPlaying() {
        return userPlaying;
    }

    public void setUserPlaying(boolean userPlaying) {
        this.userPlaying = userPlaying;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
