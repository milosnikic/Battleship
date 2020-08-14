/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class FXMLDocumentController implements Initializable {

    Stage stage;
    Controller controller;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public MenuBar MainMenu;
    @FXML
    public Menu game;
    @FXML
    public MenuItem newGame;
    @FXML
    public Menu gameRules;
    @FXML
    public MenuItem rules;
    @FXML
    public MenuItem authorInformation;
    @FXML
    public MenuItem scoreboard;
    @FXML
    public Menu exitGame;
    @FXML
    public MenuItem exit;
    @FXML
    public ImageView boatImage;
    @FXML
    public Label titleLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new Controller(this);
        this.boatImage.setVisible(true);
        this.titleLabel.setVisible(true);
    }

    @FXML
    public void newGame(ActionEvent event) {
    }

    @FXML
    public void showRules(ActionEvent event) {
    }

    @FXML
    public void showAuthorInformation(ActionEvent event) {
    }

    @FXML
    public void showScoreboard(ActionEvent event) {
    }

    @FXML
    public void exitGame(ActionEvent event) {
    }

}
