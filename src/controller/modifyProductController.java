package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;


public class modifyProductController implements Initializable {

    @FXML
    private TableColumn<?, ?> partPriceCol;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TableColumn<?, ?> ascNameCol;

    @FXML
    private TableColumn<?, ?> ascInventoryCol;

    @FXML
    private TableColumn<?, ?> ascPriceCol;

    @FXML
    private TableColumn<?, ?> partInventoryCol;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private Button addBtn;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<?, ?> ascIdCol;

    @FXML
    private Button removeAscBtn;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TableColumn<?, ?> partIdCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private Button saveBtn;

    @FXML
    void onActionAddAscPart(ActionEvent event) {

    }

    @FXML
    void onActionRemoveAscPart(ActionEvent event) {

    }

    @FXML
    void onActionSaveProduct(ActionEvent event) {

    }

    @FXML
    void onActionGotoMainForm(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}