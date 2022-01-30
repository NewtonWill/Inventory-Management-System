package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart (Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct (Product newProduct){
        allProducts.add(newProduct);

    }

    public static Part lookupPart (int partId){
        return null; //fixme
    }

    public static Product lookupProduct (int productId){
        return null; //fixme
    }

    public static ObservableList<Part> lookupPart (String partName){
        return null; //fixme
    }

    public static ObservableList<Product> lookupProduct (String productName){
        return null; //fixme
    }

    public static void updatePart (int index, Part selectedPart){
        //fixme
    }

    public static void updateProduct (int index, Product newProduct){
        //fixme
    }

    public static boolean deletePart (Part selectedPart){
        return true; //fixme
    }

    public static boolean deleteProduct (Product selectedProduct){
        return true; //fixme
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
