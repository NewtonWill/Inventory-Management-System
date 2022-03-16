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


public class modifyProductController implements Initializable {

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
    private Button addBtn;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<Part, Integer> ascIdCol;

    @FXML
    private Button removeAscBtn;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableView<Part> ascTableView;

    @FXML
    void onActionAddAscPart(ActionEvent event) {

        if (partTableView.getSelectionModel().getSelectedItem() == null){
            System.out.println("Error: No part selected");
            return; //runtime Added if statement to catch if no product is selected
        }

        tempAscParts.add(partTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionRemoveAscPart(ActionEvent event) {

        if(ascTableView.getSelectionModel().getSelectedItem() == null){
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
            tempAscParts.remove(ascTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        if (!(modProductDataCheck())){
            System.out.println("Product data invalid");
            return;
        }

        int id =        Integer.parseInt(productIdTxt.getText());
        String name =   productNameTxt.getText();
        int inv =       Integer.parseInt(productInvTxt.getText());
        double price =  Double.parseDouble(productPriceTxt.getText());
        int max =       Integer.parseInt(productMaxTxt.getText());
        int min =       Integer.parseInt(productMinTxt.getText());

        int currentIndex = Inventory.getAllProducts().indexOf(Inventory.lookupProduct(id));

        Inventory.updateProduct(currentIndex, new Product(id, name, price, inv, min, max,
                tempAscParts));
        System.out.println("Product updated");

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionGotoMainForm(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void sendProduct(Product product) {
        productIdTxt.setText(String.valueOf(product.getId()));
        productNameTxt.setText(product.getName());
        productInvTxt.setText(String.valueOf(product.getStock()));
        productPriceTxt.setText(String.valueOf(product.getPrice()));
        productMaxTxt.setText(String.valueOf(product.getMax()));
        productMinTxt.setText(String.valueOf(product.getMin()));

        if (!(product.getAllAssociatedParts() == null))
            tempAscParts = FXCollections.observableArrayList(product.getAllAssociatedParts());
        ascTableView.setItems(tempAscParts);
    }

    @FXML
    void onActionPartSearch(ActionEvent event) {

        partTableView.setItems(Inventory.lookupPart(searchTxt.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ascTableView.setItems(tempAscParts);

        ascIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ascNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ascInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ascPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public boolean modProductDataCheck(){

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