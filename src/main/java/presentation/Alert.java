package presentation;

/**
 * The class Alert generates different alerts for errors occurring in introducing the input in the UI
 */
public class Alert {

    /**
     * This method is used to create an alert which informs the user about an invalid ID introduced
     */
    public void alertInvalidID() {
        javafx.scene.control.Alert invalidID = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        invalidID.setTitle("Invalid ID");
        invalidID.setHeaderText("The introduced ID does not exist");
        invalidID.setContentText("Please introduce an existing ID.");
        invalidID.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user about an invalid input
     */
    public void alertInvalidInput() {
        javafx.scene.control.Alert invalidInput = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        invalidInput.setTitle("Invalid Input");
        invalidInput.setHeaderText("The introduced input is not valid.");
        invalidInput.setContentText("Please introduce a valid input in all provided text fields.");
        invalidInput.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user about an invalid email introduced
     */
    public void alertInvalidEmail(){
        javafx.scene.control.Alert invalidEmail = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        invalidEmail.setTitle("Invalid Email");
        invalidEmail.setHeaderText("The introduced email is not valid.");
        invalidEmail.setContentText("Please introduce a valid email.");
        invalidEmail.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user that the introduced quantity is invalid
     * @param quantity represents the value of the quantity available
     */
    public void alertInvalidQuantity(String quantity) {
        javafx.scene.control.Alert invalidQuantity = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        invalidQuantity.setTitle("Invalid Quantity");
        invalidQuantity.setHeaderText("The introduced quantity is greater than the quantity in the warehouse.");
        invalidQuantity.setContentText("Please introduce a quantity less or equal to " + quantity);
        invalidQuantity.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user that a client was not found in the database
     */
    public void alertClientNotFound() {
        javafx.scene.control.Alert invalidInput = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        invalidInput.setTitle("Client not found");
        invalidInput.setHeaderText("The introduced ID is not valid.");
        invalidInput.setContentText("Please introduce a valid ID of a client.");
        invalidInput.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user about a failed insert query
     */
    public void alertErrorInsert() {
        javafx.scene.control.Alert errorInsert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        errorInsert.setTitle("Error INSERT");
        errorInsert.setHeaderText("The object could not be introduced in the database");
        errorInsert.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user about a failed update query
     */
    public void alertErrorEdit() {
        javafx.scene.control.Alert errorEdit = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        errorEdit.setTitle("Error EDIT");
        errorEdit.setHeaderText("The object could not be edited in the database");
        errorEdit.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user about a failed delete query
     */
    public void alertErrorDelete() {
        javafx.scene.control.Alert errorDelete = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        errorDelete.setTitle("Error DELETE");
        errorDelete.setHeaderText("The object could not be deleted from the database");
        errorDelete.showAndWait();
    }


    /**
     * This method is used to create an alert which informs the user about a successful insert query
     */
    public void alertInsertSuccessful(){
        javafx.scene.control.Alert insertSuccessful = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        insertSuccessful.setTitle("Successful Insert");
        insertSuccessful.setContentText("Object was successfully introduced in the database");
        insertSuccessful.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user about a successful update query
     */
    public void alertEditSuccessful(){
        javafx.scene.control.Alert editSuccessful = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        editSuccessful.setTitle("Successful Edit");
        editSuccessful.setContentText("Object was successfully edited in the database");
        editSuccessful.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user about a successful delete query
     */
    public void alertDeleteSuccessful(){
        javafx.scene.control.Alert deleteSuccessful = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        deleteSuccessful.setTitle("Successful Delete");
        deleteSuccessful.setContentText("Object was successfully deleted from the database");
        deleteSuccessful.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user the bill of the order was successfully created
     */
    public void alertBillSuccessfull(){
        javafx.scene.control.Alert billSuccessfull = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        billSuccessfull.setTitle("Successful Bill");
        billSuccessfull.setContentText("The bill was successfully created.");
        billSuccessfull.showAndWait();
    }

    /**
     * This method is used to create an alert which informs the user the generation of the order's bill failed
     */
    public void alertBillUnsuccessfull(){
        javafx.scene.control.Alert billUnsuccessfull = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        billUnsuccessfull.setTitle("Unsuccessful Bill");
        billUnsuccessfull.setContentText("The bill could not be created.");
        billUnsuccessfull.showAndWait();
    }

}