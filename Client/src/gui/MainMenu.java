/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author milos
 */
public class MainMenu extends Application {

    FXMLDocumentController controller;

    @Override
    public void start(Stage stage) throws IOException {

        String resourcePath = "FXMLDocument.fxml";
        URL location = getClass().getResource(resourcePath);
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();
        controller = (FXMLDocumentController) fxmlLoader.getController();

        Scene scene = new Scene(root);

        stage.setTitle("Igra potpanje brodova © Miloš Nikić v1.0");
        stage.setScene(scene);
        stage.setHeight(400);
        stage.show();
        controller.setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
