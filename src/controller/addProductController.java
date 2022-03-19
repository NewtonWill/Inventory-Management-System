package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The Add Product Controller
 * @author William Newton
 */
public class addProductController implements Initializable {

    Stage stage;
    Parent scene;
    ObservableList<Part> tempAscParts = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TableColumn<Part, String> ascNameCol;

    @FXML
    private TableColumn<Part, Integer> ascInventoryCol;

    @FXML
    private TableColumn<Part, Double> ascPriceCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<Part, Integer> ascIdCol;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableView<Part> ascPartView;

    /**
     * Method validates selection, then adds part to tempAscParts
     */
    @FXML
    void onActionAddAscPart(ActionEvent event) {

        if (partTableView.getSelectionModel().getSelectedItem() == null){
            System.out.println("Error: No part selected");
            return;
        }

        tempAscParts.add(partTableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Method validates selection, asks user to confirm, then removes associated part
     */
    @FXML
    void onActionRemoveAscPart(ActionEvent event) {

        if(ascPartView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected to remove");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove associated part?");
        alert.setContentText("Press okay to confirm removal of part");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            tempAscParts.remove(ascPartView.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Calls the data check method, adds a new product according to the validated inputs, and returns to main form
     */
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        if (!(addProductDataCheck())){
            System.out.println("Product data invalid");
            return;
        }

        int id =        Inventory.getNextProductId();
        String name =   productNameTxt.getText();
        int inv =       Integer.parseInt(productInvTxt.getText());
        double price =  Double.parseDouble(productPriceTxt.getText());
        int max =       Integer.parseInt(productMaxTxt.getText());
        int min =       Integer.parseInt(productMinTxt.getText());

        Inventory.addProduct(new Product(id, name, price, inv, min, max, tempAscParts));
        System.out.println("Product added");

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
     * Method sets part table to the search result
     */
    @FXML
    void onActionPartSearch(ActionEvent event) {

        partTableView.setItems(Inventory.lookupPart(searchTxt.getText()));
    }

    /**
     * Initialization method sets all parts and associated parts to tableviews
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ascPartView.setItems(tempAscParts);

        ascIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ascNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ascInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ascPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Method to validate if input fields conform to standards
     * @return true if all fields conform to standards
     */
    public boolean addProductDataCheck(){

        Alert checkAlert = new Alert(Alert.AlertType.ERROR);
        checkAlert.setTitle("Product Not Deleted");

        if((productNameTxt.getText().isBlank())){
            checkAlert.setContentText("Name space cannot be blank");
            checkAlert.show();
            return false;
        }
        if(!(Inventory.isInteger(productInvTxt.getText())) || productInvTxt.getText().isBlank()){
            checkAlert.setContentText("Inventory space is invalid");
            checkAlert.show();
            return false;
        }
        if(productPriceTxt.getText().isBlank() || !(Inventory.isDouble(productPriceTxt.getText()))){
            checkAlert.setContentText("Price space is invalid");
            checkAlert.show();
            return false;
        }
        if(productMaxTxt.getText().isBlank() || !(Inventory.isInteger(productMaxTxt.getText()))){
            checkAlert.setContentText("Max space is invalid");
            checkAlert.show();
            return false;
        }
        if(productMinTxt.getText().isBlank() || !(Inventory.isInteger(productMinTxt.getText()))){
            checkAlert.setContentText("Min space is invalid");
            checkAlert.show();
            return false;
        }
        if(Integer.parseInt(productMaxTxt.getText()) < Integer.parseInt(productMinTxt.getText())){
            checkAlert.setContentText("Minimum value cannot be greater than Maximum Value");
            checkAlert.show();
            return false;
        }
        if(Integer.parseInt(productInvTxt.getText()) > Integer.parseInt(productMaxTxt.getText())){
            checkAlert.setContentText("Current inventory cannot be greater than maximum");
            checkAlert.show();
            return false;
        }
        if(Integer.parseInt(productInvTxt.getText()) < Integer.parseInt(productMinTxt.getText())){
            checkAlert.setContentText("Current inventory cannot be less than minimum");
            checkAlert.show();
            return false;
        }
        return true;
    }
}