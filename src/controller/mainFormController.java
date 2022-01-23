package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class mainFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> partPriceCol;

    @FXML
    private Button addProductBtn;

    @FXML
    private TableColumn<?, ?> productInventoryCol;

    @FXML
    private Button modifyPartBtn;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private Button exitBtn;

    @FXML
    private Button modifyProductBtn;

    @FXML
    private TableView<?> partTableView;

    @FXML
    private TableColumn<?, ?> partInventoryCol;

    @FXML
    private TableColumn<?, ?> productIdCol;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private TableColumn<?, ?> productNameCol;

    @FXML
    private TableView<?> productTableView;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private TableColumn<?, ?> partIdCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> productPriceCol;


    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionModifyPart(ActionEvent event) {

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

    }

    @FXML
    void onActionAddProduct(ActionEvent event) {

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionExitProgram(ActionEvent event) {
        System.out.println("Exit button Pressed");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized!");
    }
}
