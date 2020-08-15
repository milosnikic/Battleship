/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import communication.ConnectionHandler;
import domain.Coordinates;
import domain.FieldState;
import domain.Map;
import domain.Ship;
import util.ResponseStatus;
import domain.User;
import gui.handlers.AuthorInformationHandler;
import gui.handlers.ExitHandler;
import gui.handlers.NewGameHandler;
import gui.handlers.SelectShip;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import transfer.Request;
import transfer.Response;
import util.GameStatus;
import util.Messages;
import util.Operation;

/**
 *
 * @author milos
 */
public class Controller {

    User user;
    Map yourMap;
    GameStatus gameStatus;
    private LinkedList<Ship> ships;
    private Ship currentSelectedShip;

    private final ConnectionHandler connectionHandler;
    FXMLDocumentController document;
    private final OperationHandler operationHandler;

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public Controller(FXMLDocumentController document) {
        this.document = document;
        connectionHandler = new ConnectionHandler("localhost", 9999);
        operationHandler = new OperationHandler(this);
        operationHandler.start();

        Request request = new Request();
        request.setUser(MainMenu.user);
        request.setOperation(Operation.LOGIN);
        connectionHandler.getRequests().add(request);

        this.document.exit.setOnAction(new ExitHandler(this));
        this.document.newGame.setOnAction(new NewGameHandler(this));
        this.document.authorInformation.setOnAction(new AuthorInformationHandler(this));
        this.document.shipsList.setOnAction(new SelectShip(this));
    }

    public void exit() {
        // Send request to exit game
        // endGame();
        this.document.stage.close();
        Platform.exit();
        System.exit(0);
    }

    public void newGame() {
        Request request = new Request();
        request.setUser(user);
        request.setOperation(Operation.CREATE_GAME);
        connectionHandler.getRequests().add(request);
    }

    public void showAuthorInformation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Dipl. inž. organizac. nauk. Miloš Nikić");
        alert.setTitle("Informacije o authoru");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void close() {
        if (connectionHandler != null) {
            connectionHandler.closeConnection();
        }
    }

    void setInitialState() {
        this.document.boatImage.setVisible(true);
        this.document.titleLabel.setVisible(true);
        this.document.confirmButton.setVisible(false);
        this.document.userMap.setVisible(false);
        this.document.serverMap.setVisible(false);
        this.document.enemyBoardLbl.setVisible(false);
        this.document.statusTxtArea.setVisible(false);
        this.document.statusLbl.setVisible(false);
    }

    public void seTGridIsDisable(GridPane grid, boolean isDisable) {
        grid.getChildren().forEach((node) -> {
            node.setDisable(isDisable);
        });
    }

    void handleYourGridCellButtonFired(MouseEvent event) {
        switch (gameStatus) {
            case PLACING_SHIPS:

                if (!ships.isEmpty() && currentSelectedShip != null) {

                    Node node = (Node) event.getSource();

                    // We get coordinates of field clicked
                    Coordinates coordinates = new Coordinates(this.document.userMap.getRowIndex(node),
                            this.document.userMap.getColumnIndex(node));
                    currentSelectedShip.setCoordinates(coordinates);
                    // If mouse 1 is clicked we place it horizontally
                    if (event.getButton() == MouseButton.PRIMARY) {
                        // Ship has been successfully placed
                        currentSelectedShip.setVertical(true);
                        if (placeShip(node, currentSelectedShip)) {
                            updateStatus("Brod '" + currentSelectedShip.getName() + "' duzine: "
                                    + currentSelectedShip.getLength() + " postavljen.", true);
                            removeFromCombobox(currentSelectedShip);
                        }
                    }
                    // If mouse 2 is clicked we place it vertically
                    if (event.getButton() == MouseButton.SECONDARY) {
                        // Ship has been successfully placed
                        currentSelectedShip.setVertical(false);
                        if (placeShip(node, currentSelectedShip)) {
                            updateStatus("Brod '" + currentSelectedShip.getName() + "' duzine: "
                                    + currentSelectedShip.getLength() + " postavljen.", true);
                            removeFromCombobox(currentSelectedShip);
                        }
                    }
                    if (ships.isEmpty()) {
                        updateStatus("Postavili ste sve brodove, sada mozete potvrditi formaciju!", true);
                        this.document.confirmButton.setDisable(false);
                        this.document.shipsList.setDisable(true);
                        seTGridIsDisable(this.document.userMap, true);
                    }
                    break;
                } else {
                    updateStatus("Izaberite prvo brod.");
                }
                break;
            case PLAYING:
                break;
            case END:
                seTGridIsDisable(this.document.userMap, true);
                seTGridIsDisable(this.document.serverMap, true);
                break;
        }
    }

    void handleEnemyGridCellButtonFired(MouseEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private LinkedList<Ship> getShips() {
        LinkedList<Ship> ships = new LinkedList<>();
        ships.add(new Ship("Battleship - 4", 4));
        ships.add(new Ship("Cruiser 1 - 3", 3));
        ships.add(new Ship("Cruiser 2 - 3", 3));
        ships.add(new Ship("Destroyer 1 - 2", 2));
        ships.add(new Ship("Destroyer 2 - 2", 2));
        ships.add(new Ship("Destroyer 3 - 2", 2));
        ships.add(new Ship("Singleton 1 - 1", 1));
        ships.add(new Ship("Singleton 2 - 1", 1));
        ships.add(new Ship("Singleton 3 - 1", 1));
        ships.add(new Ship("Singleton 4 - 1", 1));

        return ships;
    }

    private boolean placeShip(Node node, Ship ship) {
        if (shipCanBePlaced(ship)) {
            colorShip(ship);
            node.setDisable(true);
            return true;
        }
        updateStatus("Brod nije moguce postaviti na trazenu poziciju!");
        return false;
    }

    private boolean shipCanBePlaced(Ship ship) {
        if (!ships.isEmpty()) {
            int y = ship.getCoordinates().getCol();
            int x = ship.getCoordinates().getRow();
            if (ship.isVertical()) {
                for (int i = y; i < y + ship.getLength(); i++) {
                    if (!isValidPoint(new Coordinates(x, i))) {
                        return false;
                    }
                    if (yourMap.getFieldState(x, i) != FieldState.EMPTY) {
                        return false;
                    }
                }
            } else {
                for (int i = x; i < x + ship.getLength(); i++) {
                    if (!isValidPoint(new Coordinates(i, y))) {
                        return false;
                    }
                    if (yourMap.getFieldState(i, y) != FieldState.EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean isValidPoint(Coordinates coordinates) {
        return coordinates.getRow() >= 0 && coordinates.getRow() < 10
                && coordinates.getCol() >= 0 && coordinates.getCol() < 10;
    }

    private void colorShip(Ship ship) {
        int y = ship.getCoordinates().getCol();
        int x = ship.getCoordinates().getRow();
        if (ship.isVertical()) {
            for (int i = y; i < y + ship.getLength(); i++) {
                this.document.userMap.getChildren().get(x * 10 + i).setStyle("-fx-background-color: blue");
                yourMap.setFieldState(x, i, FieldState.SHIP);
            }
        } else {
            for (int i = x; i < x + ship.getLength(); i++) {
                this.document.userMap.getChildren().get(i * 10 + y).setStyle("-fx-background-color: blue");
                yourMap.setFieldState(i, y, FieldState.SHIP);
            }
        }
    }

    private void updateStatus(String message) {
        String tmp = this.document.statusTxtArea.getText();
        tmp += "\n" + message;
        this.document.statusTxtArea.setText(tmp);
    }

    private void updateStatus(String message, boolean clear) {
        this.document.statusTxtArea.setText(message);
    }

    private void putShipsIntoCombobox() {
        ObservableList<Ship> options
                = FXCollections.observableArrayList();

        for (Ship ship : ships) {
            options.add(ship);
        }

        this.document.shipsList.setItems(options);
    }

    private void removeFromCombobox(Ship currentSelectedShip) {
        ObservableList<Ship> options
                = FXCollections.observableArrayList();
        ships.remove(currentSelectedShip);
        for (Ship ship : ships) {
            options.add(ship);
        }

        this.document.shipsList.setItems(options);
    }

    public void selectShip() {
        currentSelectedShip = this.document.shipsList.getSelectionModel().getSelectedItem();
    }

    void loadUser(Response response) {
        if (response.getResponseStatus() == ResponseStatus.OK) {
            User user = response.getUser();
            if (user != null) {
                this.user = user;
                Messages.showSuccess("Uspesno ucitan korisnik!");
            }
        } else {
            Messages.showError("Problem sa ucitavanjem korisnika." + response.getResponseStatus() + response.getUser() + response.getOperation());
        }
    }

    public void createGame(Response finalResponse) {
        if (finalResponse.getResponseStatus() == ResponseStatus.OK) {
            this.document.boatImage.setVisible(false);
            this.document.titleLabel.setVisible(false);
            this.document.stage.setHeight(800);
            this.document.confirmButton.setVisible(true);
            this.document.userMap.setVisible(true);
            this.document.serverMap.setVisible(true);
            this.document.enemyBoardLbl.setVisible(true);
            this.document.confirmButton.setDisable(true);
            this.document.statusTxtArea.setVisible(true);
            this.document.statusTxtArea.setEditable(false);
            this.document.statusLbl.setVisible(true);
            this.document.statusTxtArea.setWrapText(true);
            updateStatus("Počnite sa postavljanjem brodova!");
            seTGridIsDisable(this.document.userMap, false);

            gameStatus = GameStatus.PLACING_SHIPS;
            yourMap = new Map();
            ships = getShips();
            putShipsIntoCombobox();
        } else {
            Messages.showError("Nije moguce kreirati igru!");
        }
    }

}
