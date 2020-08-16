/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SO.AbstractGenericOperation;
import SO.CreateGameSO;
import SO.LoginSO;
import SO.ServerShootSO;
import SO.StartGameSO;
import SO.UserShootSO;
import domain.Map;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author milos
 */
public class Controller {

    private static Controller instance;
    private AbstractGenericOperation so;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Response login(Request request) {
        so = new LoginSO();
        return ((LoginSO) so).login(request);
    }

    public Response createGame(Request request) {
        so = new CreateGameSO();
        return ((CreateGameSO) so).createGame(request);
    }

    public Response startGame(Request request) {
        so = new StartGameSO();
        return ((StartGameSO) so).startGame(request);
    }

    public Response userShoot(Request request) {
        so = new UserShootSO();
        return ((UserShootSO) so).userShoot(request);
    }

    public Response serverShoot(Request request) {
        so = new ServerShootSO();
        return ((ServerShootSO) so).serverShoot(request);
    }

}
