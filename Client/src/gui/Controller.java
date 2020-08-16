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
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import util.Ships;

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
    private boolean userPlaying;
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
                        this.gameStatus = GameStatus.PLAYING;
                    }
                    break;
                } else {
                    updateStatus("Izaberite prvo brod.");
                }
                break;
            case END:
                seTGridIsDisable(this.document.userMap, true);
                seTGridIsDisable(this.document.serverMap, true);
                break;
        }
    }

    void handleEnemyGridCellButtonFired(MouseEvent event) {
        Node node = (Node) event.getSource();
        // We get coordinates of field clicked
        // And if it is not disabled, we want to check for hit
        Coordinates coordinates = new Coordinates(this.document.userMap.getRowIndex(node),
                this.document.userMap.getColumnIndex(node));
        Request request = new Request();
        request.setCoordinates(coordinates);
        request.setOperation(Operation.USER_SHOOT);
        connectionHandler.getRequests().add(request);

    }

    private boolean placeShip(Node node, Ship ship) {
        if (Ships.shipCanBePlaced(ship, ships, yourMap)) {
            colorShip(ship);
            node.setDisable(true);
            return true;
        }
        updateStatus("Brod nije moguce postaviti na trazenu poziciju!");
        return false;
    }

    private void colorShip(Ship ship) {
        int y = ship.getCoordinates().getCol();
        int x = ship.getCoordinates().getRow();
        if (ship.isVertical()) {
            for (int i = y; i < y + ship.getLength(); i++) {
                this.document.userMap.getChildren().get(x * 10 + i).setStyle("-fx-background-color: blue");
                yourMap.setShipAt(x, i, ship);
            }
        } else {
            for (int i = x; i < x + ship.getLength(); i++) {
                this.document.userMap.getChildren().get(i * 10 + y).setStyle("-fx-background-color: blue");
                yourMap.setShipAt(i, y, ship);
            }
        }
    }

    private void killShip(Ship ship) {
        int y = ship.getCoordinates().getCol();
        int x = ship.getCoordinates().getRow();
        Node node;
        if (ship.isVertical()) {
            for (int i = y; i < y + ship.getLength(); i++) {
                node = this.document.userMap.getChildren().get(x * 10 + i);
                node.setStyle("-fx-background-color: red");
                node.setStyle("-fx-opacity: 1");
            }
        } else {
            for (int i = x; i < x + ship.getLength(); i++) {
                node = this.document.userMap.getChildren().get(i * 10 + y);
                node.setStyle("-fx-background-color: red");
                node.setStyle("-fx-opacity: 1");
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
            ships = Ships.getShips();
            putShipsIntoCombobox();
        } else {
            Messages.showError("Nije moguce kreirati igru!");
        }
    }

    void confirmFormation(ActionEvent event) {
        // Initialize server map
        // get who is playing first
        Request request = new Request();
        request.setMap(yourMap);
        request.setOperation(Operation.START_GAME);
        connectionHandler.getRequests().add(request);
    }

    void startGame(Response finalResponse) {
        if (finalResponse.getResponseStatus() == ResponseStatus.OK) {
            // See who is playing first
            // And start game
            updateStatus("Protivnik je izabrao formaciju, igra moze da pocne!", true);
            seTGridIsDisable(this.document.serverMap, false);
            this.document.confirmButton.setDisable(true);
        } else {
            Messages.showError("Problem prilikom startovanja igre!");
        }
    }

    /**
     * Method is used to update single cell of grid
     *
     * @param text Text which will appear in status TextField
     * @param gridToUpdate Grid which will be updated with a shot result
     * @param gridToChangeIsDisable Grid which status will be changed to enable
     * or prevent shooting
     * @param newGridState A value to which gridToChangeIsDisable will be
     * changed
     * @param color Color which will appear in cell of updated grid
     * @param row Row of cell to update
     * @param col Column of cell to update
     * @param ship Ship that is on that cell
     */
    private void updateGUI(String text, GridPane gridToUpdate, GridPane gridToChangeIsDisable, boolean newGridState,
            String color, Integer row, Integer col, Ship ship) {
        Platform.runLater(() -> {
            updateStatus(text);

            if (ship != null && !ship.isAlive()) {
                // If ship is not alive we should all his fields color and write number
                killShip(ship);
            }
            gridToUpdate.getChildren().get(row * 10 + col).setStyle("-fx-background-color: " + color);
            seTGridIsDisable(gridToUpdate, newGridState);
        });
    }

    void userShoot(Response finalResponse) {
        if (finalResponse.getResponseStatus() == ResponseStatus.OK) {
            if (finalResponse.getHit() != null && finalResponse.getUserPlaying() != null) {
                int row = finalResponse.getCoordinates().getRow();
                int col = finalResponse.getCoordinates().getCol();
                updateGUI("Korisnik je gadjao polje " + (row + 1) + " , " + (col + 1),
                        this.document.serverMap,
                        this.document.userMap,
                        !finalResponse.getUserPlaying(),
                        getFieldColor(finalResponse.getHit()),
                        row,
                        col,
                        finalResponse.getShip());
                if (finalResponse.getShip() != null) {
                    System.out.println("Number of fields alive: " + finalResponse.getShip().getFieldsAlive());
                }
                System.out.println(finalResponse.getUserPlaying());
                // See if is now turn for server to shoot
                // Then we create call to server and after that we update gui
                if (!finalResponse.getUserPlaying()) {
                    // Send request to wait for move
                    Request request = new Request();
                    request.setOperation(Operation.SERVER_SHOOT);
                    connectionHandler.getRequests().add(request);
                }
            } else {
                updateStatus("Nije moguce gadjati prethodno izabrana polja!");
            }
        } else {
            Messages.showError("Greska prilikom gadjanja od strane korisnika!");
        }
    }

    private String getFieldColor(Boolean hit) {
        return hit ? "green" : "black";
    }

    void serverShoot(Response finalResponse) {
        if (finalResponse.getResponseStatus() == ResponseStatus.OK) {
            int row = finalResponse.getCoordinates().getRow();
            int col = finalResponse.getCoordinates().getCol();
            updateGUI("Server je gadjao polje " + (row + 1) + " , " + (col + 1),
                    this.document.userMap,
                    this.document.serverMap,
                    finalResponse.getUserPlaying(),
                    getFieldColor(finalResponse.getHit()),
                    row,
                    col,
                    finalResponse.getShip());
            // See if is now turn for server to shoot
            // Then we create call to server and after that we update gui
            if (!finalResponse.getUserPlaying()) {
                // Send request to wait for move
                Request request = new Request();
                request.setOperation(Operation.SERVER_SHOOT);
                connectionHandler.getRequests().add(request);
            } else {
                seTGridIsDisable(this.document.serverMap, false);
            }
        } else {
            Messages.showError("Greska prilikom gadjanja od strane servera!");
        }
    }
}
