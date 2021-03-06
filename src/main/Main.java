package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Product;

/**
 * The main class of the program
 * @author William Newton
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 1000, 500));
        stage.show();
    }


    /**
     * The main executable for the program. Houses test data and launches args
     * @param args the args to launch
     */
    public static void main(String[] args){

        InHouse inhouse1 = new InHouse(Inventory.getNextPartId(), "Pedal", 25, 10, 0, 20, 1234);
        InHouse inhouse2 = new InHouse(Inventory.getNextPartId(), "Bike Tire", 25, 10, 0, 20, 1080);

        Outsourced outsourced1 = new Outsourced(Inventory.getNextPartId(), "Chain", 9.99, 2, 0, 4, "ChainCo");

        Product product1 = new Product(Inventory.getNextProductId(), "Bike", 199.99, 10, 1, 20, null);
        Product product2 = new Product(Inventory.getNextProductId(), "Tandem", 299.99, 5, 0, 10, null);
        Product product3 = new Product(Inventory.getNextProductId(), "Skateboard", 99.99, 20, 2, 35,null);

        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);
        Inventory.addPart(outsourced1);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);

        launch(args);
    }
}