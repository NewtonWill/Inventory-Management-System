package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * The Add Part Controller
 * @author William Newton
 */
public class addPartController implements Initializable {

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
    private TextField partMinTxt;

    @FXML
    private RadioButton outsourceRadio;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    /**
     * Calls the data check method, adds a new part according to the validated inputs, and returns to main form
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        if (!(addPartDataCheck())){
            System.out.println("Part data invalid");
            return;
        }

        int id =        Inventory.getNextPartId();
        String name =   partNameTxt.getText();
        int inv =       Integer.parseInt(partInvTxt.getText());
        double price =  Double.parseDouble(partPriceTxt.getText());
        int max =       Integer.parseInt(partMaxTxt.getText());
        int min =       Integer.parseInt(partMinTxt.getText());
        //runtime Error had occurred when adding new outsourced using non-int in altIDTxt
        // caused by "int machineId = Integer.parseInt(altIdTxt.getText());" placed here.
        // resolved by containing line within specified if statement



        if (inhouseRadio.isSelected()) {
            int machineId = Integer.parseInt(altIdTxt.getText());

            Inventory.addPart(new InHouse(id, name, price, inv, min, max,
                    machineId));
            System.out.println("In-House part added");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else if (outsourceRadio.isSelected()) {
            String companyName = altIdTxt.getText();

            Inventory.addPart(new Outsourced(id, name, price, inv, min, max,
                    companyName));
            System.out.println("Outsourced part added");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else {
            System.out.println("Error with input, In-House or Outsourced must be specified");
        }
    }

    /**
     * Returns to Main form without saving
     */
    @FXML
    void onActionGotoMainForm(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Method sets label text appropriately according to the radio button
     */
    @FXML
    void onActionInHouse() {
        altIdLabel.setText("Machine ID");
    }

    /**
     * Method sets label text appropriately according to the radio button
     */
    @FXML
    void onActionOutsourced() {
        altIdLabel.setText("Company Name");
    }

    /**
     * Initialization method sets inhouseRadio to true
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inhouseRadio.setSelected(true);

    }

    /**
     * Method to validate if input fields conform to standards
     * @return true if all fields conform to standards
     */
    public boolean addPartDataCheck(){

        Alert checkAlert = new Alert(Alert.AlertType.ERROR);
        checkAlert.setTitle("Product Not Deleted");

        if (!(inhouseRadio.isSelected() || outsourceRadio.isSelected())) {
            checkAlert.setContentText("No Radio selection made");
            checkAlert.show();
            return false;
        }
        if ((partNameTxt.getText().isBlank())) {
            checkAlert.setContentText("Name space cannot be blank");
            checkAlert.show();
            return false;
        }
        if (!(Inventory.isInteger(partInvTxt.getText())) || partInvTxt.getText().isBlank()) {
            checkAlert.setContentText("Inventory space is invalid");
            checkAlert.show();
            return false;
        }
        if (partPriceTxt.getText().isBlank() || !(Inventory.isDouble(partPriceTxt.getText()))) {
            checkAlert.setContentText("Price space is invalid");
            checkAlert.show();
            return false;
        }
        if (partMaxTxt.getText().isBlank() || !(Inventory.isInteger(partMaxTxt.getText()))) {
            checkAlert.setContentText("Max space is invalid");
            checkAlert.show();
            return false;
        }
        if (partMinTxt.getText().isBlank() || !(Inventory.isInteger(partMinTxt.getText()))) {
            checkAlert.setContentText("Min space is invalid");
            checkAlert.show();
            return false;
        }
        if (Integer.parseInt(partMaxTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
            checkAlert.setContentText("Minimum value cannot be greater than Maximum Value");
            checkAlert.show();
            return false;
        }
        if (Integer.parseInt(partInvTxt.getText()) > Integer.parseInt(partMaxTxt.getText())) {
            checkAlert.setContentText("Current inventory cannot be greater than maximum");
            checkAlert.show();
            return false;
        }
        if (Integer.parseInt(partInvTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
            checkAlert.setContentText("Current inventory cannot be less than minimum");
            checkAlert.show();
            return false;
        }
        if (inhouseRadio.isSelected() && !(Inventory.isInteger(altIdTxt.getText()))) {
            checkAlert.setContentText("Machine ID must be integer");
            checkAlert.show();
            return false;
        }
        return true;
    }
}