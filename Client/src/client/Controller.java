/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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
        
        System.out.println("Username: " + username + "\nPassword: " + password);
    }

}
