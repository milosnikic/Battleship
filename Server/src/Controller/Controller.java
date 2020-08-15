/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SO.AbstractGenericOperation;
import SO.CreateGameSO;
import SO.LoginSO;
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

}
