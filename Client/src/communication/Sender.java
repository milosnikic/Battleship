/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;

/**
 *
 * @author milos
 */
public class Sender extends Thread {

    private ArrayBlockingQueue<Request> requests;
    private ObjectOutputStream toServer;

    public Sender(ObjectOutputStream toServer) {
        this.toServer = toServer;
        this.requests = new ArrayBlockingQueue<>(10);
    }

    public ArrayBlockingQueue<Request> getRequests() {
        return requests;
    }

    @Override
    public void run() {
        Request request;
        try {
            while ((request = requests.take()) != null) {
                toServer.writeObject(request);
            }
        } catch (InterruptedException ex) {
            // User exited program
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
