package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    //warning Not defined in UML
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    //warning Not defined in UML


    public static void addPart (Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct (Product newProduct){
        allProducts.add(newProduct);

    }

    public static Part lookupPart (int partId){
        for(Part part : getAllParts()){
            if(part.getId() == partId) {
                System.out.println("Part Found");
                return part;
            }
        }
        System.out.println("Part Not Found");
        return null;
    }

    public static Product lookupProduct (int productId){
        for(Product product : getAllProducts()){
            if(product.getId() == productId) {
                System.out.println("Product Found");
                return product;
            }
        }
        System.out.println("Product Not Found");
        return null;
    }

    public static ObservableList<Part> lookupPart (String partName){
        return null; //fixme
    }

    public static ObservableList<Product> lookupProduct (String productName){
        return null; //fixme
    }

    public static void updatePart (int index, Part selectedPart){
        //todo transfer from mainformcontroller
    }

    public static void updateProduct (int index, Product newProduct){
        //todo transfer from mainformcontroller
    }

    public static boolean deletePart (Part selectedPart){

        if (selectedPart == null)
            return false; //runtime used these lines to fix error when lookupPart() returned null

        for(Part partX : Inventory.getAllParts()){
            if(partX.getId() == selectedPart.getId()){
                System.out.println("Part ID " + selectedPart.getId() + " Deleted");
                return Inventory.getAllParts().remove(partX);
            }
        }
        System.out.println("Part ID " + selectedPart.getId() + " Not Found");
        return false;
    }

    public static boolean deleteProduct (Product selectedProduct){
        return true;
        //todo transfer from mainformcontroller
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }



    public static ObservableList<Part> getFilteredParts() {
        return filteredParts;
        //warning Not defined in UML
    }

    public static ObservableList<Product> getFilteredProducts() {
        return filteredProducts;
        //warning Not defined in UML
    }
}
