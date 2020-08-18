/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import domain.Coordinates;
import domain.Game;
import domain.Map;
import domain.RankItem;
import domain.Ship;
import util.ResponseStatus;
import domain.User;
import java.io.Serializable;
import java.util.List;
import util.Operation;

/**
 *
 * @author milos
 */
public class Response implements Serializable {

    private User user;
    private Operation operation;
    private ResponseStatus responseStatus;
    private Boolean hit;
    private Ship ship;
    private Boolean userPlaying;
    private Coordinates coordinates;
    private Map map;
    private List<RankItem> rankList;

    public Response() {
    }

    public List<RankItem> getRankList() {
        return rankList;
    }

    public void setRankList(List<RankItem> rankList) {
        this.rankList = rankList;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Boolean getUserPlaying() {
        return userPlaying;
    }

    public void setUserPlaying(Boolean userPlaying) {
        this.userPlaying = userPlaying;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
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
