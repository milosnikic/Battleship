/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;

/**
 * Class is used to manipulate with sending and receiving responses and requests
 *
 * @author milos
 */
public class ConnectionHandler extends Thread {

    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    /**
     * Method is used to create connection to server initialize threads and
     * start them
     */
    public ConnectionHandler(String address, int port) {
        try {
            socket = new Socket(address, port);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        sender = new Sender(toServer);
        receiver = new Receiver(fromServer);

        sender.start();
        receiver.start();

    }

    /**
     * Method is used to retrieve all responses
     *
     * @return responses
     */
    public ArrayBlockingQueue<Response> getResponses() {
        return receiver.getResponses();
    }

    /**
     * Method is used to retrieve requests
     *
     * @return requests
     */
    public ArrayBlockingQueue<Request> getRequests() {
        return sender.getRequests();
    }

    /**
     * Close all connections All threads stop and close socket connection
     */
    public void closeConnection() {
        if (receiver.isAlive()) {
            receiver.interrupt();
        }

        if (sender.isAlive()) {
            sender.interrupt();
        }

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
