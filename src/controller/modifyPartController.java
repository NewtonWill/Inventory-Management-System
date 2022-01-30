package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class modifyPartController implements Initializable {

    Stage stage;
    Parent scene;

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
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private Button saveBtn;

    @FXML
    void onActionSavePart(ActionEvent event) {

    }

    @FXML
    void onActionGotoMainForm(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
