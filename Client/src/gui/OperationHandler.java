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
                switch (response.getOperation()) {
                    case LOGIN:
                        Platform.runLater(() -> {
                            controller.loadUser(finalResponse);
                        });
                        break;
                    case END:
                        Platform.runLater(() -> {
                            controller.exit();
                        });
                        break;
                    case CREATE_GAME:
                        Platform.runLater(() -> {
                            controller.newGame();
                        });
                        break;
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(OperationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}