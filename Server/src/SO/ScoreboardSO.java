/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import domain.Game;
import domain.GeneralDObject;
import domain.RankItem;
import domain.User;
import java.util.LinkedList;
import java.util.List;
import transfer.Request;
import transfer.Response;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author milos
 */
public class ScoreboardSO extends AbstractGenericOperation {

    private Response response;
    private Request request;

    public Response getScoreboard(Request request) {
        this.request = request;
        this.response = new Response();
        abstractExecuteSO();
        return response;
    }

    @Override
    public boolean executeSO() {
        List<GeneralDObject> games = bbp.findRecords(new Game(), "WHERE end=1 AND score <> 0 ORDER BY score DESC");
        List<RankItem> rankList = new LinkedList<RankItem>();
        if (games != null) {
            for (int i = 0; i < games.size(); i++) {
                Game game = (Game) games.get(i);
                List<GeneralDObject> users = bbp.findRecords(new User(), "WHERE id = " + game.getidUser());
                if (users == null && users.size() == 0) {
                    response.setResponseStatus(ResponseStatus.ERROR);
                    return true;
                }
                User user = (User) users.get(0);
                rankList.add(new RankItem(user, game));
            }
            response.setOperation(Operation.SCOREBOARD);
            response.setRankList(rankList);
            response.setResponseStatus(ResponseStatus.OK);
        }else {
            response.setResponseStatus(ResponseStatus.ERROR);
        }
        return true;
    }

}
