package model;

/**
 * The class OrderTable represents an object having the same attributes as the table OrderTable from the database
 */
public class OrderTable {
    private int ID;
    private int clientID; // client ID
    private int product; // product ID
    private int quantity; // quantity
    private float price; // price of the order

    /**
     * This method represents a constructor of the OrderTable class
     * @param ID represents the ID of the order
     * @param clientID represents the ID of the client performing the order
     * @param product represents the ID of the product being ordered
     * @param quantity represents the quantity of the product being ordered
     * @param price represents the price of the product being ordered
     */
    public OrderTable(int ID, int clientID, int product, int quantity, float price) {
        this.ID = ID;
        this.clientID = clientID;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * This method represents a constructor of the OrderTable class
     * @param clientID represents the ID of the client performing the order
     * @param product represents the ID of the product being ordered
     * @param quantity represents the quantity of the product being ordered
     * @param price represents the price of the product being ordered
     */
    public OrderTable(int clientID, int product, int quantity, float price) {
        this.clientID = clientID;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * This method represents a default constructor of the OrderTable class
     */
    public OrderTable(){

    }

    /**
     * This method represents a getter of the ID of the order
     * @return the ID of the order
     */
    public int getID() {
        return ID;
    }

    /**
     * This method represents the setter of the ID of the order
     * @param ID represents the value which will be assigned to the ID of the order
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * This method represents a getter of the ID of the client performing the order
     * @return the ID of the client
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * This method represents the setter of the ID of the client performing the order
     * @param clientID represents the value which will be assigned to the ID of the client
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * This method represents a getter of the product being ordered
     * @return the product being ordered
     */
    public int getProduct() {
        return product;
    }

    /**
     * This method represents the setter of the product being ordered
     * @param product represents the value which will be assigned to the product
     */
    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * This method represents a getter of the quantity of the order
     * @return the quantity of the order
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method represents the setter of the quantity of the order
     * @param quantity represents the value which will be assigned to the quantity of the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * This method represents a getter of the price of the order
     * @return the price of the order
     */
    public float getPrice() {
        return price;
    }

    /**
     * This method represents the setter of the price of the order
     * @param price represents the value which will be assigned to the price of the order
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
