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

        InHouse inhouse1 = new InHouse(Part.getNextPartId(), "Pedal", 25, 10, 0, 20, 1234);
        InHouse inhouse2 = new InHouse(Part.getNextPartId(), "Bike Tire", 25, 10, 0, 20, 1080);

        Outsourced outsourced1 = new Outsourced(Part.getNextPartId(), "Chain", 9.99, 2, 0, 4, "ChainCo");

        Product product1 = new Product(Product.getNextProductId(), "Bike", 199.99, 10, 1, 20);
        Product product2 = new Product(Product.getNextProductId(), "Tandem", 299.99, 5, 0, 10);
        Product product3 = new Product(Product.getNextProductId(), "Skateboard", 99.99, 20, 2, 35);

        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);
        Inventory.addPart(outsourced1);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);

        launch(args);
    }
}