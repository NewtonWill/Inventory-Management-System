package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * The Inventory class. Houses all parts/products and the filtered parts/products
 * @author William Newton
 */

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    public static int nextPartId = 5000;

    /**
     * Method generates and returns a unique part ID
     * @return the next generated part ID
     */
    public static int getNextPartId(){
        do{
            nextPartId++;
        }
        while (!(lookupPart(nextPartId) == null));
        return nextPartId;
    }

    public static int nextProductId = 1000;

    /**
     * Method generates and returns a unique product ID
     * @return the next generated product ID
     */
    public static int getNextProductId(){
        do{
            nextProductId++;
        }
        while (!(lookupProduct(nextProductId) == null));
        return nextProductId;
    }

    /**
     * Method adds part to allParts
     * @param newPart the part to add to allParts
     */
    public static void addPart (Part newPart){
        allParts.add(newPart);
    }

    /** Method adds product to allProducts
     * @param newProduct the product to add to allProducts
     */
    public static void addProduct (Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * Searches all parts to find a match with ID
     * @param partId the part ID to search for
     * @return the part that matches the ID parameter
     */
    public static Part lookupPart (int partId){
        for(Part part : getAllParts()){
            if(part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * Searches all products to find a match with ID
     * @param productId the product ID to search for
     * @return the product that matches the ID parameter
     */
    public static Product lookupProduct (int productId){
        for(Product product : getAllProducts()){
            if(product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Creates a list of all parts that match the productName parameter
     * @param partName the name to match with allProducts list
     * @return filtered list of matching parts or all parts if no matches are found
     */
    public static ObservableList<Part> lookupPart (String partName){

        if(!(getFilteredParts().isEmpty()))
            getFilteredParts().clear();

        for(Part partx : getAllParts()){
            if(partx.getName().contains(partName)) {
                getFilteredParts().add(partx);
                System.out.println(partx.getName() + " added via name");
            }

            else {
                try {
                    if (partx.getId() == (Integer.parseInt(partName))) {
                        getFilteredParts().add(partx);
                        System.out.println(partx.getName() + " added via ID number");
                    }
                } catch (NumberFormatException ignored) {
                    //Ignored: empty catch
                }
            }
        }

        if(getFilteredParts().isEmpty()) {
            Alert noMatchAlert = new Alert(Alert.AlertType.WARNING);
            noMatchAlert.setTitle("Part Search Error");
            noMatchAlert.setContentText("No matching part(s) found");
            noMatchAlert.show();
            return getAllParts();
        }
        else
            return getFilteredParts();
    }

    /**
     * Creates a list of all products that match the productName parameter
     * @param productName the name to match with allProducts list
     * @return filtered list of matching products or all products if no matches are found
     */
    public static ObservableList<Product> lookupProduct (String productName){

        if(!(getFilteredProducts().isEmpty()))
            getFilteredProducts().clear();

        for(Product productx : getAllProducts()){

            if(productx.getName().contains(productName)){
                getFilteredProducts().add(productx);
                System.out.println(productx.getName() + " added via name");
            }
            else {
                try{
                    if(productx.getId() == Integer.parseInt(productName)){
                        getFilteredProducts().add(productx);
                        System.out.println(productx.getName() + " added via ID number");
                    }
                }
                catch (NumberFormatException ignored){
                    //Ignored: empty catch
                }
            }
        }

        if(getFilteredProducts().isEmpty()) {
            Alert noMatchAlert = new Alert(Alert.AlertType.WARNING);
            noMatchAlert.setTitle("Product Search Error");
            noMatchAlert.setContentText("No matching product(s) found");
            noMatchAlert.show();
            return getAllProducts();
        }
        else
            return getFilteredProducts();
    }

    /**
     * Method replaces part at index with part parameter
     * @param index the index of the part to replace
     * @param selectedPart the part to place at the specified index
     */
    public static void updatePart (int index, Part selectedPart){

        getAllParts().set(index, selectedPart);
    }

    /**
     * Method replaces product at index with product parameter
     * @param index the index of the product to replace
     * @param newProduct the product to place at the specified index
     */
    public static void updateProduct (int index, Product newProduct){

        getAllProducts().set(index, newProduct);
    }

    /**
     * Method verifies selection, checks for associated parts, then deletes the part
     * @param selectedPart the part to delete
     * @return true if the deletion is successful
     */
    public static boolean deletePart (Part selectedPart){

        if (selectedPart == null) {
            System.out.println("Selected Part is null. Delete aborted");
            return false; //runtime used these lines to fix error when lookupPart() returned null
        }

        for(Part partX : getAllParts()){
            if(partX.getId() == selectedPart.getId()){
                System.out.println("Part ID " + selectedPart.getId() + " Deleted");
                return getAllParts().remove(partX);
            }
        }
        System.out.println("Part ID " + selectedPart.getId() + " Not Found");
        return false;
    }

    /**
     * Method verifies selection, checks for associated parts, then deletes the product
     * @param selectedProduct the product to delete
     * @return true if the deletion is successful
     */
    public static boolean deleteProduct (Product selectedProduct){

        if (selectedProduct == null) {
            System.out.println("Selected Product is null. Delete aborted");
            return false;
        }

        if (!((selectedProduct.getAllAssociatedParts() == null) || selectedProduct.getAllAssociatedParts().isEmpty())) {
            System.out.println("Associated part(s) found. Delete aborted");
            return false;
        }

        for(Product productX : Inventory.getAllProducts()){
            if(productX.getId() == selectedProduct.getId()){
                System.out.println("Product ID " + selectedProduct.getId() + " Deleted");
                return Inventory.getAllProducts().remove(productX);
            }
        }

        System.out.println("Product ID " + selectedProduct.getId() + " Not Found");
        return false;
    }

    /**
     * @return all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * @return all filtered parts
     */
    public static ObservableList<Part> getFilteredParts() {
        return filteredParts;
    }

    /**
     * @return all filtered products
     */
    public static ObservableList<Product> getFilteredProducts() {
        return filteredProducts;
    }

    /**
     * Method to test if a given string is an integer
     * @param string the string to test
     * @return true if the string is an integer
     */
    public static boolean isInteger(String string)
    {
        try
        {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * Method to test if a given string is a double
     * @param string the string to test
     * @return true if the string is a double
     */
    public static boolean isDouble(String string)
    {
        try
        {
            Double.parseDouble(string);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
}