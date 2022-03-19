package model;

/** Inherited subclass of Part
 *
 * @author William Newton
 */

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max,
                      String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Getter for companyName
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Setter for companyName
     * @param companyName the companyName to set
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
