/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author milos
 */
public class Messages {

    public static void showError(String message) {
        Alert infoAlert;
        infoAlert = new Alert(Alert.AlertType.ERROR);
        infoAlert.setTitle("Greska");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }

    public static void showSuccess(String message) {
        Alert infoAlert;
        infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Obavestenje");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }

    public static void showWarning(String message) {
        Alert infoAlert;
        infoAlert = new Alert(Alert.AlertType.WARNING);
        infoAlert.setTitle("Upozorenje");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }

    public static void dialog(String message, boolean successOperation) {
        Alert alert;
        if (successOperation) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
        }
        ButtonType buttonTypeCancel = new ButtonType("Izlaz", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
    }
}
