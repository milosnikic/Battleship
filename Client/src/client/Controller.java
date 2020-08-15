/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import wsclient.User;
import gui.MainMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import util.Messages;
import wsclient.ILogin;
import wsclient.LoginService;
import wsclient.Request;
import wsclient.Response;

/**
 *
 * @author milos
 */
public class Controller {

    FXMLDocumentController document;

    public Controller(FXMLDocumentController document) {
        this.document = document;
        this.document.login.setOnAction(new LoginHandler(this));
    }

    public void login() {
        String username = this.document.usernameField.getText().trim();
        String password = this.document.passwordField.getText().trim();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        LoginService service = new LoginService();
        ILogin iservice = service.getLoginPort();

        Request request = new Request();
        request.setUser(user);

        Response response = iservice.login(request);
        if (response != null) {
            if (response.getUser() != null) {
                Messages.showSuccess("Uspesno ste se prijavili!!");
            } else {
                Messages.showError("Neuspesno prijavljivanje!");
                return;
            }

            try {
                this.document.stage.close();

                // We check if credentials are ok we go and close this stage, and
                // call a new JavaFX form
                MainMenu mainMenu = new MainMenu();
                Stage s = new Stage();
                mainMenu.setUser(username, password);
                mainMenu.start(s);

            } catch (IOException ex) {
                // Error creating stage
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Messages.showError("Neuspesno prijavljivanje!");
        }
    }

}
