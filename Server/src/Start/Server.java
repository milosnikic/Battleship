/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import Threads.Client;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Server extends Thread {

    private int portNumber;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        try {
            // Listening to users connections
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Listening for connections...");

            while (true) {
                // Accept client
                Socket clientSocket = serverSocket.accept();
                System.out.println("User accepted: " + clientSocket.getInetAddress().toString());

                // Now with client socket we can create new client thread
                Client client = new Client(clientSocket);
                client.start();
            }
        } catch (IOException ex) {
            // Error creating server socker
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
