/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author milos
 */
public class LoginHandler implements EventHandler{

    // We need controller reference
    Controller controller;

    public LoginHandler(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void handle(Event event) {
        controller.login();
    }
    
}
