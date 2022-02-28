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
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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

        if (!(addPartDataCheck())){
            System.out.println("Part data invalid");
            return;
        }


        //int id = Integer.parseInt(partIdTxt.getText());
        // - vestigial Save for use in mod part scene

        int id = Inventory.getNextPartId();
        String name =   partNameTxt.getText();
        int inv =       Integer.parseInt(partInvTxt.getText());
        double price =  Double.parseDouble(partPriceTxt.getText());
        int max =       Integer.parseInt(partMaxTxt.getText());
        int min =       Integer.parseInt(partMinTxt.getText());
        /*runtime Error had occurred when adding new outsourced using non-int in altIDTxt
        caused by "int machineId = Integer.parseInt(altIdTxt.getText());" placed here.
        resolved by containing line within specified if statement
        */


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

    @FXML
    void onActionGotoMainForm(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionInHouse() {
        altIdLabel.setText("Machine ID");
    }

    @FXML
    void onActionOutsourced() {
        altIdLabel.setText("Company Name");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inhouseRadio.setSelected(true);

    }

    public boolean addPartDataCheck(){

        if(!(inhouseRadio.isSelected() || outsourceRadio.isSelected())) {
            System.out.println("No Radio selection made");
            return false;
        }
        if((partNameTxt.getText().isBlank())){
            System.out.println("Name space cannot be blank");
            return false;
        }
        if(partInvTxt.getText().isBlank() || !(Inventory.isInteger(partInvTxt.getText()))){
            System.out.println("Inventory space is invalid");
            return false;
        }
        if(Integer.parseInt(partInvTxt.getText()) > Integer.parseInt(partMaxTxt.getText())){
            System.out.println("Current inventory cannot be greater than maximum");
            return false;
        }
        if(Integer.parseInt(partInvTxt.getText()) < Integer.parseInt(partMinTxt.getText())){
            System.out.println("Current inventory cannot be less than minimum");
            return false;
        }
        if(partPriceTxt.getText().isBlank() || !(Inventory.isDouble(partPriceTxt.getText()))){
            System.out.println("Price space is invalid");
            return false;
        }
        if(partMaxTxt.getText().isBlank() || !(Inventory.isInteger(partMaxTxt.getText()))){
            System.out.println("Max space is invalid");
            return false;
        }
        if(partMinTxt.getText().isBlank() || !(Inventory.isInteger(partMinTxt.getText()))){
            System.out.println("Min space is invalid");
            return false;
        }
        if(Integer.parseInt(partMaxTxt.getText()) < Integer.parseInt(partMinTxt.getText())){
            System.out.println("Minimum value cannot be greater than Maximum Value");
            return false;
        }
        if(altIdTxt.getText().isBlank()){
            System.out.println("Machine ID/ Company Name space cannot be blank");
            return false;
        }
        if(inhouseRadio.isSelected() && !(Inventory.isInteger(altIdTxt.getText()))){
            System.out.println("Machine ID must be integer");
            return false;
        }

        return true;
    }
}