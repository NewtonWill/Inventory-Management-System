package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class addPartController implements Initializable {

    @FXML
    private TextField partPriceTxt;

    @FXML
    private Label altIdLabel;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private RadioButton inhouseRadio;

    @FXML
    private TextField altIdTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField partMinTxt;

    @FXML
    private TextField partIdTxt;

    @FXML
    private RadioButton outsourceRadio;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private Button saveBtn;

    @FXML
    void onActionSavePart(ActionEvent event) {

    }

    @FXML
    void onActionGotoMainForm(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
