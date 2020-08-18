/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIGeneral;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Sinisa
 */
public abstract class Panel {
    
    @FXML
    public Button Nadji;

    @FXML
    public Button Obrisi;

    @FXML
    public Button Promeni;

    @FXML
    public Label LPoruka;

    @FXML
    public Button Kreiraj;

    @FXML
    public TextField Poruka;

    @FXML
    public ImageView Poslednji;

    @FXML
    public Label LIndex;

    @FXML
    public ImageView Prvi;

        @FXML
    public TextField Kriterijum;

    @FXML
    public Label LKriterijum;

    @FXML
    public ComboBox<String> Atribut;

    @FXML
    public Label LAtribut;
    
}
