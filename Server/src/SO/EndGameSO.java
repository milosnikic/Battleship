/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import static SO.AbstractGenericOperation.bbp;
import Threads.Client;
import domain.Game;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author milos
 */
public class EndGameSO extends AbstractGenericOperation {

    private Response response;
    private Request request;

    public Response endGame(Request request) {
        this.request = request;
        this.response = new Response();
        abstractExecuteSO();
        return response;
    }

    @Override
    public boolean executeSO() {
        try {
            Field game = Client.class.getDeclaredField("game");
            game.setAccessible(true);
            Game gameReflection = (Game) game.get(new Client());
            gameReflection.setend(true);
            if (this.request.getOperation() == Operation.SERVER_WIN) {
                gameReflection.setidWinner(-1);
                // 20 is number of total fields
                gameReflection.setnumberOfFieldsHit(20 - Client.serverMap.countAliveFields());
                gameReflection.setnumberOfFieldsLeft(Client.serverMap.countAliveFields());
                gameReflection.setscore(Client.serverMap.countAliveFields() * 5 - (20 - Client.serverMap.countAliveFields()) * 2);
            } else {
                gameReflection.setidWinner(request.getUser().getIdUser());
                // 20 is number of total fields
                gameReflection.setnumberOfFieldsHit(20 - Client.userMap.countAliveFields());
                gameReflection.setnumberOfFieldsLeft(Client.userMap.countAliveFields());
                gameReflection.setscore(Client.userMap.countAliveFields() * 5 - (20 - Client.userMap.countAliveFields()) * 2);
            }

            if (bbp.updateRecord(gameReflection)) {
                response.setResponseStatus(ResponseStatus.OK);
            } else {
                response.setResponseStatus(ResponseStatus.ERROR);
            }
            response.setOperation(Operation.END);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(EndGameSO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(EndGameSO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EndGameSO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EndGameSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
