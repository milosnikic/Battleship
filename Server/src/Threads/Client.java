/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Controller.Controller;
import domain.Map;
import util.ResponseStatus;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import util.Operation;

/**
 *
 * @author milos
 */
public class Client extends Thread {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean end = false;
    private Socket clientSocket;
    public static Map serverMap;
    public static Map userMap;

    public Client(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            out = new ObjectOutputStream(this.clientSocket.getOutputStream());
            in = new ObjectInputStream(this.clientSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        while (!end) {
            try {
                Request request = (Request) in.readObject();
                Response response = new Response();
                switch (request.getOperation()) {
                    case CREATE_GAME:
                        response = Controller.getInstance().createGame(request);
                        createGame(response);
                        break;
                    case LOGIN:
                        response = Controller.getInstance().login(request);
                        login(response);
                        break;
                    case START_GAME:
                        startGame(request);
                        response = Controller.getInstance().startGame(request);
                        Client.serverMap = response.getMap();
                        break;
                    case USER_SHOOT:
                        response = Controller.getInstance().userShoot(request);
                        break;
                    case SERVER_SHOOT:
                        response = Controller.getInstance().serverShoot(request);
                        break;
                    case END:
                        break;
                }
                sendResponse(response);
            } catch (Exception ex) {
                end = true;
                System.out.println("Client closed connection.");
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void login(Response controllerResponse) {
        controllerResponse.setOperation(Operation.LOGIN);
        if (controllerResponse.getUser() != null) {
            controllerResponse.setResponseStatus(ResponseStatus.OK);
        } else {
            controllerResponse.setResponseStatus(ResponseStatus.ERROR);
        }

    }

    private void sendResponse(Response response) {
        try {
            out.writeObject(response);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createGame(Response response) {
        if (response.getResponseStatus() == ResponseStatus.OK) {
            serverMap = new Map();
            System.out.println("Map created!");
        }
    }

    private void startGame(Request request) {
        this.userMap = request.getMap();
    }

}
