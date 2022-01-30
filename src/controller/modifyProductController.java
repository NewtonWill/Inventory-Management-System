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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class modifyProductController implements Initializable {

    Stage stage;
    Parent scene;

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
    void onActionGotoMainForm(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}