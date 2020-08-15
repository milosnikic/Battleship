/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Controller.Controller;
import domain.ResponseStatus;
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
                        break;
                    case END:
                        break;
                    case LOGIN:
                        response = Controller.getInstance().login(request);
                        login(response);
                        break;
                }
                sendResponse(response);
            } catch (Exception ex) {
                end = true;
                System.out.println("Client closed connection.");
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
}
