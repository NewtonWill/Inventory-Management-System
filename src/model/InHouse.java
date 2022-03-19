package model;

/**
 * Inherited subclass of Part
 * @author William Newton
 */

public class InHouse extends Part {

    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max,
                   int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * @return the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param machineId the machineID to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
