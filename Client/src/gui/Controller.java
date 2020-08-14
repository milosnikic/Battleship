/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import gui.handlers.AuthorInformationHandler;
import gui.handlers.ExitHandler;
import gui.handlers.NewGameHandler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import util.Messages;

/**
 *
 * @author milos
 */
public class Controller {
    
    FXMLDocumentController document;
    
    public Controller(FXMLDocumentController document) {
        this.document = document;
        this.document.exit.setOnAction(new ExitHandler(this));
        this.document.newGame.setOnAction(new NewGameHandler(this));
        this.document.authorInformation.setOnAction(new AuthorInformationHandler(this));
    }
    
    public void exit() {
        // Send request to exit game
        // endGame();
        this.document.stage.close();
        Platform.exit();
        System.exit(0);
    }
    
    public void newGame() {
        this.document.boatImage.setVisible(false);
        this.document.titleLabel.setVisible(false);
        this.document.stage.setHeight(700);
    }
    
    public void showAuthorInformation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Dipl. inž. organizac. nauk. Miloš Nikić");
        alert.setTitle("Informacije o authoru");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
}
