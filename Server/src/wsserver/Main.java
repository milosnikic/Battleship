/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsserver;

import Start.Server;
import ws.*;
import javax.xml.ws.*;

/**
 *
 * @author milos
 */
public class Main {

    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:4789/ws/login", new Login());
            System.out.println("Done");
            
            //Start server
            Server server = new Server(9999);
            server.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
