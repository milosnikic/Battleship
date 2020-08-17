/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import Threads.Client;
import domain.Game;
import util.ResponseStatus;
import domain.User;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import util.Operation;

/**
 *
 * @author milos
 */
public class CreateGameSO extends AbstractGenericOperation {

    Request request;
    Response response;

    public Response createGame(Request request) {
        this.request = request;
        this.response = new Response();
        abstractExecuteSO();
        return response;
    }

    @Override
    public boolean executeSO() {

        User user = request.getUser();
        Game game = new Game();
        game.setidUser(user.getIdUser());
        Client.game = game;
        if (bbp.insertRecord(game)) {
            response.setResponseStatus(ResponseStatus.OK);
        } else {
            response.setResponseStatus(ResponseStatus.ERROR);
        }
        response.setOperation(Operation.CREATE_GAME);

        return true;
    }

}
