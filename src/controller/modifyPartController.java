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
    private RadioButton outsourceRadio;

    @FXML
    private Button saveBtn;

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException{

        if (!(modPartDataCheck())){
            System.out.println("Part data invalid");
            return;
        }

        int id =        Integer.parseInt(partIdTxt.getText());
        String name =   partNameTxt.getText();
        int inv =       Integer.parseInt(partInvTxt.getText());
        double price =  Double.parseDouble(partPriceTxt.getText());
        int max =       Integer.parseInt(partMaxTxt.getText());
        int min =       Integer.parseInt(partMinTxt.getText());
        //runtime Error had occurred when adding new outsourced using non-int in altIDTxt
        // caused by "int machineId = Integer.parseInt(altIdTxt.getText());" placed here.
        // resolved by containing line within specified if statement

        int currentIndex = Inventory.getAllParts().indexOf(Inventory.lookupPart(id));

        if (inhouseRadio.isSelected()) {

            int machineId = Integer.parseInt(altIdTxt.getText());

            Inventory.updatePart(currentIndex, new InHouse(id, name, price, inv, min, max,
                    machineId));
            System.out.println("In-House part updated");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else if (outsourceRadio.isSelected()) {
            String companyName = altIdTxt.getText();

            Inventory.updatePart(currentIndex, new Outsourced(id, name, price, inv, min, max,
                    companyName));
            System.out.println("Outsourced part updated");

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

    public void sendPart(Part part) {
        partIdTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(part.getName());
        partInvTxt.setText(String.valueOf(part.getStock()));
        partPriceTxt.setText(String.valueOf(part.getPrice()));
        partMaxTxt.setText(String.valueOf(part.getMax()));
        partMinTxt.setText(String.valueOf(part.getMin()));
        if (part instanceof InHouse) { //used to check if instanceOf inhouse or outsourced to set altIdtxt and set radio buttons
            altIdTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
            inhouseRadio.setSelected(true);
        }
        else {
            altIdTxt.setText(((Outsourced) part).getCompanyName());
            outsourceRadio.setSelected(true);
        }
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

    }

    public boolean modPartDataCheck(){
        //runtime Future Enhancement
        // A good thing to work on in the future is to create a global version of
        // this function in inventory that would take parameters instead of directly
        // from the fxml areas
        // It may also be beneficial to implement try/catch handlers for
        // certain exceptions

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
