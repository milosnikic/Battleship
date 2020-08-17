/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import transfer.Response;

/**
 *
 * @author milos
 */
public class OperationHandler extends Thread {

    private Controller controller;
    private ArrayBlockingQueue<Response> responses;

    public OperationHandler(Controller controller) {
        this.controller = controller;
        this.responses = controller.getConnectionHandler().getResponses();
    }

    @Override
    public void run() {
        Response response;

        try {
            while ((response = responses.take()) != null) {
                final Response finalResponse = response;
                switch (finalResponse.getOperation()) {
                    case LOGIN:
                        Platform.runLater(() -> {
                            controller.loadUser(finalResponse);
                        });
                        break;
                    case END:
                        Platform.runLater(() -> {
//                            controller.exit(finalResponse);
                        });
                        break;
                    case CREATE_GAME:
                        Platform.runLater(() -> {
                            controller.createGame(finalResponse);
                        });
                        break;
                    case START_GAME:
                        Platform.runLater(() -> {
                            controller.startGame(finalResponse);
                        });
                        break;
                    case USER_SHOOT:
                        Platform.runLater(() -> {
                            controller.userShoot(finalResponse);
                        });
                        break;
                    case SERVER_SHOOT:
                        Platform.runLater(() -> {
                            controller.serverShoot(finalResponse);
                        });
                        break;
                    case SERVER_WIN:
                        Platform.runLater(() -> {
                            controller.serverWin(finalResponse);
                        });
                        break;
                    case USER_WIN:
                        Platform.runLater(() -> {
                            controller.userWin(finalResponse);
                        });
                        break;

                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(OperationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
