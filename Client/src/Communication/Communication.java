/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author milos
 */
public class Communication {

    private static Communication instance;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Communication() {
        // Create new socket..
    }

    private static Communication getInstance() {
        if(instance == null){
            instance = new Communication();
        }
        return instance;
    }

}
