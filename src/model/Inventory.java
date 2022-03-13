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

    public static int nextPartId = 5000;

    public static int getNextPartId(){
        do{
            nextPartId++;
        }
        while (!(lookupPart(nextPartId) == null));
        return nextPartId;
        //runtime Implemented system to check if any modified part IDs matches nextPartID
        //vestigial do while loop unnecessary
    }

    public static int nextProductId = 1000;

    public static int getNextProductId(){
        do{
            nextProductId++;
        }
        while (!(lookupProduct(nextProductId) == null));
        return nextProductId;
        //runtime Implemented system to check if any modified product IDs matches nextProductID
    }


    public static void addPart (Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct (Product newProduct){
        allProducts.add(newProduct);

    }

    public static Part lookupPart (int partId){
        for(Part part : getAllParts()){
            if(part.getId() == partId) {
                //System.out.println("Part ID match Found");
                return part;
            }
        }
        //System.out.println("Part Not Found");
        return null;
    }

    public static Product lookupProduct (int productId){
        for(Product product : getAllProducts()){
            if(product.getId() == productId) {
                //System.out.println("Product ID match Found");
                return product;
            }
        }
        //System.out.println("Product Not Found");
        return null;
    }

    public static ObservableList<Part> lookupPart (String partName){

        if(!(getFilteredParts().isEmpty()))
            getFilteredParts().clear();

        for(Part partx : getAllParts()){
            if(partx.getName().contains(partName)) {
                getFilteredParts().add(partx);
                System.out.println(partx.getName() + " added via name");
            }

            try {
                if (partx.getId() == (Integer.parseInt(partName))) {
                    getFilteredParts().add(partx);
                    System.out.println(partx.getName() + " added via ID");
                }
            }
            catch (NumberFormatException e) {
                //e.printStackTrace();
            }
        }

        if(getFilteredParts().isEmpty())
            return getAllParts();
        else
            return getFilteredParts();
    }

    public static ObservableList<Product> lookupProduct (String productName){

        if(!(getFilteredProducts().isEmpty()))
            getFilteredProducts().clear();

        for(Product productx : getAllProducts()){
            if(productx.getName().contains(productName))
                getFilteredProducts().add(productx);
        }

        if(getFilteredParts().isEmpty())
            return getAllProducts();
        else
            return getFilteredProducts();
    }

    public static void updatePart (int index, Part selectedPart){

        getAllParts().set(index, selectedPart);

        //warning remember to pass index via indexOf(object o)
        // remember to pass selectedPart via New Inhouse/Outsourced(params)
    }

    public static void updateProduct (int index, Product newProduct){

        getAllProducts().set(index, newProduct);

        //todo implement system to transfer asc parts from old product to new
    }

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
