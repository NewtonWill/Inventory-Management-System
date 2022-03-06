package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class modifyProductController implements Initializable {

    Stage stage;
    Parent scene;

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

    }

    @FXML
    void onActionRemoveAscPart(ActionEvent event) {

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

        Inventory.updateProduct(currentIndex, new Product(id, name, price, inv, min, max));
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partTableView.setItems(Inventory.getAllParts());

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public boolean modProductDataCheck(){

        if((productNameTxt.getText().isBlank())){
            System.out.println("Name space cannot be blank");
            return false;
        }
        if(productInvTxt.getText().isBlank() || !(Inventory.isInteger(productInvTxt.getText()))){
            System.out.println("Inventory space is invalid");
            return false;
        }
        if(Integer.parseInt(productInvTxt.getText()) > Integer.parseInt(productMaxTxt.getText())){
            System.out.println("Current inventory cannot be greater than maximum");
            return false;
        }
        if(Integer.parseInt(productInvTxt.getText()) < Integer.parseInt(productMinTxt.getText())){
            System.out.println("Current inventory cannot be less than minimum");
            return false;
        }
        if(productPriceTxt.getText().isBlank() || !(Inventory.isDouble(productPriceTxt.getText()))){
            System.out.println("Price space is invalid");
            return false;
        }
        if(productMaxTxt.getText().isBlank() || !(Inventory.isInteger(productMaxTxt.getText()))){
            System.out.println("Max space is invalid");
            return false;
        }
        if(productMinTxt.getText().isBlank() || !(Inventory.isInteger(productMinTxt.getText()))){
            System.out.println("Min space is invalid");
            return false;
        }
        if(Integer.parseInt(productMaxTxt.getText()) < Integer.parseInt(productMinTxt.getText())){
            System.out.println("Minimum value cannot be greater than Maximum Value");
            return false;
        }
        return true;
    }

}