package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class mainFormController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private Button addProductBtn;

    @FXML
    private TableColumn<Product, Integer> productInventoryCol;

    @FXML
    private Button modifyPartBtn;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private Button exitBtn;

    @FXML
    private Button modifyProductBtn;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;


    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        if (partTableView.getSelectionModel().getSelectedItem() == null){
            System.out.println("Error: No part selected");
            return; //runtime Added if statement to catch if no part is selected
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyPart.fxml"));
        loader.load();

        modifyPartController MPartController = loader.getController();
        MPartController.sendPart(partTableView.getSelectionModel().getSelectedItem());


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

        if(partTableView.getSelectionModel().getSelectedItem() == null){
            Alert noPartAlert = new Alert(Alert.AlertType.ERROR);
            noPartAlert.setTitle("Part Not Deleted");
            noPartAlert.setContentText("No part selected to delete");
            noPartAlert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part?");
        alert.setContentText("Press okay to confirm deletion of part");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            Inventory.deletePart(partTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        if (productTableView.getSelectionModel().getSelectedItem() == null){
            System.out.println("Error: No product selected");
            return; //runtime Added if statement to catch if no product is selected
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyProduct.fxml"));
        loader.load();

        modifyProductController MProductController = loader.getController();
        MProductController.sendProduct(productTableView.getSelectionModel().getSelectedItem());


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        if(productTableView.getSelectionModel().getSelectedItem() == null){
            Alert noProdAlert = new Alert(Alert.AlertType.ERROR);
            noProdAlert.setTitle("Error");
            noProdAlert.setContentText("No product selected");
            noProdAlert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Product?");
        alert.setContentText("Press okay to confirm deletion of product");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            if(!(Inventory.deleteProduct(productTableView.getSelectionModel().getSelectedItem()))){
                Alert ascAlert = new Alert(Alert.AlertType.ERROR);
                ascAlert.setTitle("Product Not Deleted");
                ascAlert.setContentText("Product has associated parts");
                ascAlert.showAndWait();
            }
        }
    }

    @FXML
    void onActionExitProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionPartSearch(ActionEvent event) {

        partTableView.setItems(Inventory.lookupPart(partSearchTxt.getText()));
    }

    @FXML
    void onActionProductSearch(ActionEvent event) {

        productTableView.setItems(Inventory.lookupProduct(productSearchTxt.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        partTableView.setItems(Inventory.getAllParts());
        productTableView.setItems(Inventory.getAllProducts());

        //partTableView.setItems(Inventory.lookupPart(""));
        //productTableView.setItems(Inventory.lookupProduct(""));

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
