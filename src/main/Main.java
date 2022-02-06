package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Part;
import model.Product;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 1000, 500));
        stage.show();
    }


    public static void main(String[] args){

        InHouse inhouse1 = new InHouse(1, "Pedal", 25, 10, 0, 20, 1234);
        Outsourced outsourced1 = new Outsourced(2, "Chain", 9.99, 2, 0, 4, "ChainCo");

        Product product1 = new Product(10, "Bike", 199.99, 5, 1, 10);

        Inventory.addPart(inhouse1);
        Inventory.addPart(outsourced1);
        Inventory.addProduct(product1);

        launch(args);
    }
}