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
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class addPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private Label altIdLabel; //fixme figure out how to change label on radio switch

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
    void onActionSavePart(ActionEvent event) throws IOException {

        //int id =        Integer.parseInt(partIdTxt.getText());
        int id = 5; //fixme temp until autogen is figured out

        String name =   partNameTxt.getText();
        int inv =       Integer.parseInt(partInvTxt.getText());
        double price =  Double.parseDouble(partPriceTxt.getText());
        int max =       Integer.parseInt(partMaxTxt.getText());
        int min =       Integer.parseInt(partMinTxt.getText());
        int machineId = Integer.parseInt(altIdTxt.getText());
        String companyName = altIdTxt.getText();


        if (inhouseRadio.isSelected()) {
            Inventory.addPart(new InHouse(id, name, price, inv, min, max,
                    machineId));
            System.out.println("In-House part added");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            return;
        }

        if (outsourceRadio.isSelected()) {
            Inventory.addPart(new Outsourced(id, name, price, inv, min, max,
                    companyName)); //fixme figure out why this isn't accepting non ints
            System.out.println("Outsourced part added");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            return;
        }


        else {
            System.out.println("Error with input");
        }
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
