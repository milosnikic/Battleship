/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Response;

/**
 *
 * @author milos
 */
public class Receiver extends Thread {

    private ObjectInputStream fromServer;
    private ArrayBlockingQueue<Response> responses;

    public Receiver() {
    }

    public Receiver(ObjectInputStream fromServer) {
        this.fromServer = fromServer;
        this.responses = new ArrayBlockingQueue<>(10);
    }

    public ArrayBlockingQueue<Response> getResponses() {
        return responses;
    }

    @Override
    public void run() {
        Response response;
        try {
            while ((response = (Response) fromServer.readObject()) != null) {
                responses.add(response);
            }
        } catch (Exception ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
