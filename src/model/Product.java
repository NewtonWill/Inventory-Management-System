package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** The Product class
 *
 * @author William Newton
 */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max,
                   ObservableList<Part> ascParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = ascParts;
    }

    /**
     * Getter for ID
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for price
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for stock
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for stock
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for minimum
     * @return the minimum
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for minimum
     * @param min the minimum to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for maximum
     * @return the maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for maximum
     * @param max the maximum to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Method associates a part to the product
     * @param part the part to associate with the product
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * Method removes associated part from the product
     * @param selectedAssociatedPart the associated part to remove
     * @return true if delete is successful
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {

        if (selectedAssociatedPart == null)
            return false;

        for(Part partX : getAllAssociatedParts()){
            if(partX.getId() == selectedAssociatedPart.getId()){
                System.out.println("Part ID " + selectedAssociatedPart.getId() + " Removed");
                return getAllAssociatedParts().remove(partX);
            }
        }
        System.out.println("Part ID " + selectedAssociatedPart.getId() + " Not Found");
        return false;
    }

    /**
     * Getter for associated parts
     * @return all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}