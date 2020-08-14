/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.handlers;

import gui.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author milos
 */
public class NewGameHandler implements EventHandler {

    Controller controller;

    public NewGameHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(Event event) {
        this.controller.newGame();
    }

}
