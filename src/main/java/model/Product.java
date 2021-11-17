package model;

/**
 * The class Product represents an object having the same attributes as the table Product from the database
 */
public class Product {
    private int ID;
    private String name;
    private float price;
    private int quantity;

    /**
     * This method represents a constructor of the Product class
     * @param ID represents the ID of the product
     * @param name represents the name of the product
     * @param price represents the price of the product
     * @param quantity represents the quantity of the product
     */
    public Product(int ID, String name, float price, int quantity) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * This method represents a constructor of the Product class
     * @param name represents the name of the product
     * @param price represents the price of the product
     * @param quantity represents the quantity of the product
     */
    public Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * This method represents a default constructor of the Product class
     */
    public Product(){

    }


    /**
     * This method represents a getter of the ID of the product
     * @return the ID of the product
     */
    public int getID() {
        return ID;
    }


    /**
     * This method represents the setter of the ID of the product
     * @param ID represents the value which will be assigned to the ID of the product
     */
    public void setID(int ID) {
        this.ID = ID;
    }



    /**
     * This method represents a getter of the name of the product
     * @return the name of the product
     */
    public String getName() {
        return name;
    }


    /**
     * This method represents the setter of the name of the product
     * @param name represents the value which will be assigned to the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * This method represents a getter of the price of the product
     * @return the price of the product
     */
    public float getPrice() {
        return price;
    }

    /**
     * This method represents the setter of the price of the product
     * @param price represents the value which will be assigned to the price of the product
     */
    public void setPrice(float price) {
        this.price = price;
    }


    /**
     * This method represents a getter of the quantity of the product
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * This method represents the setter of the quantity of the product
     * @param quantity represents the value which will be assigned to the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
