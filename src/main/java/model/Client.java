package model;

/**
 * The class Client represents an object having the same attributes as the table Client from the database
 */
public class Client {
    private int ID;
    private String name;
    private String email;
    private String address;

    /**
     * This method represents a constructor of the Client class
     * @param ID represents the ID of the client
     * @param name represents the name of the client
     * @param email represents the name of the client
     * @param address represents the address of the client
     */
    public Client(int ID, String name, String email, String address) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * This method represents a constructor of the Client class
     * @param name represents the name of the client
     * @param email represents the name of the client
     * @param address represents the address of the client
     */
    public Client(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * This method represents a default constructor of the Client class
     */
    public Client() {

    }

    /**
     * This method represents a getter of the ID of the client
     * @return the ID of the client
     */
    public int getID() {
        return ID;
    }


    /**
     * This method represents a setter of the ID of the client
     * @param ID represents the value which will be assigned to the ID of the client
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * This method represents a getter of the name of the client
     * @return the name of the client
     */
    public String getName() {
        return name;
    }


    /**
     * This method represents a setter of the name of the client
     * @param name represents the value which will be assigned to the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * This method represents a getter of the email of the client
     * @return the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method represents a setter of the email of the client
     * @param email represents the value which will be assigned to the email of the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method represents a getter of the address of the client
     * @return the address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method represents a setter of the address of the client
     * @param address represents the value which will be assigned to the address of the client
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
