package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainFormController implements Initializable {

    Stage stage;
    Parent scene;

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
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

        System.out.println("Delete Part Request");
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

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        System.out.println("Delete Product Request");
    }

    @FXML
    void onActionExitProgram(ActionEvent event) {
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
