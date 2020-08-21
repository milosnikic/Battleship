/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import communication.ConnectionHandler;
import domain.Coordinates;
import domain.Map;
import domain.RankItem;
import domain.Ship;
import util.ResponseStatus;
import domain.User;
import gui.handlers.AuthorInformationHandler;
import gui.handlers.ExitHandler;
import gui.handlers.NewGameHandler;
import gui.handlers.RulesHandler;
import gui.handlers.ScoreboardHandler;
import gui.handlers.SelectShip;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
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

        this.document.newGame.setOnAction(new NewGameHandler(this));
        this.document.authorInformation.setOnAction(new AuthorInformationHandler(this));
        this.document.shipsList.setOnAction(new SelectShip(this));
        this.document.exitGame.setOnAction(new ExitHandler(this));
        this.document.scoreboard.setOnAction(new ScoreboardHandler(this));
        this.document.rules.setOnAction(new RulesHandler(this));
    }

    public void exit() {
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
                        currentSelectedShip.setVertical(false);
                        if (placeShip(node, currentSelectedShip)) {
                            // Ship has been successfully placed
                            updateStatus("Brod '" + currentSelectedShip.getName() + "' duzine: "
                                    + currentSelectedShip.getLength() + " postavljen.", true);
                            removeFromCombobox(currentSelectedShip, ships);
                        }
                    }
                    // If mouse 2 is clicked we place it vertically
                    if (event.getButton() == MouseButton.SECONDARY) {
                        currentSelectedShip.setVertical(true);
                        if (placeShip(node, currentSelectedShip)) {
                            // Ship has been successfully placed
                            updateStatus("Brod '" + currentSelectedShip.getName() + "' duzine: "
                                    + currentSelectedShip.getLength() + " postavljen.", true);
                            removeFromCombobox(currentSelectedShip, ships);
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
        if (!node.isDisabled()) {
            Coordinates coordinates = new Coordinates(this.document.userMap.getRowIndex(node),
                    this.document.userMap.getColumnIndex(node));
            Request request = new Request();
            request.setCoordinates(coordinates);
            request.setOperation(Operation.USER_SHOOT);
            connectionHandler.getRequests().add(request);
            node.setDisable(true);
        } else {
            updateStatus("Polje je prethodno bilo gadjano! Probajte drugo.");
        }

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
        System.out.println(x + "\t" + y);
        if (!ship.isVertical()) {
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

    private void killShip(Ship ship, boolean userMap) {
        int y = ship.getCoordinates().getCol();
        int x = ship.getCoordinates().getRow();
        Node node;
        if (!ship.isVertical()) {
            for (int i = y; i < y + ship.getLength(); i++) {
                if (userMap) {
                    node = this.document.userMap.getChildren().get(x * 10 + i);
                } else {
                    node = this.document.serverMap.getChildren().get(x * 10 + i);
                }
                node.setStyle("-fx-background-color: red");
            }
        } else {
            for (int i = x; i < x + ship.getLength(); i++) {
                if (userMap) {
                    node = this.document.userMap.getChildren().get(i * 10 + y);
                } else {
                    node = this.document.serverMap.getChildren().get(i * 10 + y);
                }
                node.setStyle("-fx-background-color: red");
            }
        }
    }

    private void updateStatus(String message) {
        String tmp = this.document.statusTxtArea.getText();
        tmp = message + "\n" + tmp;
        this.document.statusTxtArea.setText(tmp);
    }

    private void updateStatus(String message, boolean clear) {
        this.document.statusTxtArea.setText(message);
    }

    private <T> void putItemsIntoCombobox(List<T> elements) {
        ObservableList<T> options
                = FXCollections.observableArrayList();
        for (T t : elements) {
            options.add(t);
        }

        this.document.shipsList.setItems(options);
    }

    private <T> void removeFromCombobox(T item, List<T> items) {
        ObservableList<T> options
                = FXCollections.observableArrayList();
        if (item instanceof Ship
                && items.contains(item)) {
            items.remove(item);
        }
        for (T t : items) {
            options.add(t);
        }

        this.document.shipsList.setItems(options);
    }

    public void selectShip() {
        currentSelectedShip = (Ship) this.document.shipsList.getSelectionModel().getSelectedItem();
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
            this.document.shipsList.setDisable(false);
            updateStatus("Počnite sa postavljanjem brodova!", true);
            seTGridIsDisable(this.document.userMap, false);
            resetGrid(this.document.userMap);
            resetGrid(this.document.serverMap);

            gameStatus = GameStatus.PLACING_SHIPS;
            yourMap = new Map();
            ships = Ships.getShips();
            putItemsIntoCombobox(ships);
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
    private void updateGUI(String text, GridPane gridToUpdate, String color, Integer row, Integer col, Ship ship) {
        Platform.runLater(() -> {
            updateStatus(text);

            if (ship != null && !ship.isAlive()) {
                // If ship is not alive we should all his fields color and write number
                killShip(ship, gridToUpdate.equals(this.document.userMap));
                return;
            }
            gridToUpdate.getChildren().get(row * 10 + col).setStyle("-fx-background-color: " + color);
        });
    }

    void userShoot(Response finalResponse) {
        if (finalResponse.getResponseStatus() == ResponseStatus.OK) {
            if (finalResponse.getHit() != null && finalResponse.getUserPlaying() != null) {
                int row = finalResponse.getCoordinates().getRow();
                int col = finalResponse.getCoordinates().getCol();
                updateGUI("Korisnik je gadjao polje " + (row + 1) + " , " + (col + 1),
                        this.document.serverMap,
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
            }
        } else {
            Messages.showError("Greska prilikom gadjanja od strane servera!");
        }
    }

    void manageWin(Response finalResponse) {
        this.gameStatus = GameStatus.END;
        String message;
        GridPane map;
        if (finalResponse.getOperation() == Operation.USER_WIN) {
            message = "Cestitamo na pobedi :)!";
            map = this.document.serverMap;
        } else {
            message = "Nazalost ste izgubili!";
            map = this.document.userMap;
        }
        updateGUI("",
                map,
                getFieldColor(finalResponse.getHit()),
                finalResponse.getCoordinates().getRow(),
                finalResponse.getCoordinates().getCol(),
                finalResponse.getShip());
        seTGridIsDisable(this.document.serverMap, true);
        seTGridIsDisable(this.document.userMap, true);

        Request request = new Request();
        request.setOperation(Operation.END);
        request.setUser(this.user);
        connectionHandler.getRequests().add(request);

        Messages.showWarning(message);
    }

    private void resetGrid(GridPane serverMap) {
        serverMap.getChildren().forEach((node) -> {
            node.setStyle("-fx-background-color:none");
        });
    }

    public void getScoreboard() {
        Request request = new Request();
        request.setOperation(Operation.SCOREBOARD);
        connectionHandler.getRequests().add(request);
    }

    void showScoreboard(Response finalResponse) {
        if (finalResponse.getResponseStatus() == ResponseStatus.OK) {
            String temp = "No.\tUsername\tScore\n";
            LinkedList<RankItem> rankList = (LinkedList<RankItem>) finalResponse.getRankList();
            int counter = 1;
            for (RankItem rankItem : rankList) {
                temp += counter++ + ".\t" + rankItem.getUser().getUsername() + "\t\t" + rankItem.getGame().getscore() + "\n";
            }
            Messages.showInformation(temp);
        } else {
            Messages.showError("Problem prilikom dovlacenja podataka o rang listi.");
        }
    }

    public void showRules() {
        try {
            InputStream in = new FileInputStream("src/assets/rules.properties");
            Properties prop = new Properties();
            prop.load(in);
            String text = prop.getProperty("text");
            Messages.showInformation(text);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
