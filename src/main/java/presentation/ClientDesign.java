package presentation;

import bll.ClientBLL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Client;

import java.io.IOException;
import java.util.List;

/**
 * The class ClientDesign is a controller class for the windows regarding operations on clients: add, edit, view, delete
 */
public class ClientDesign {

    ClientBLL clientBLL = new ClientBLL();
    Alert alert = new Alert();

    ObservableList<Client> observableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Object, Object> col_id;
    @FXML
    private TableColumn<Object, Object> col_name;
    @FXML
    private TableColumn<Object, Object> col_address;
    @FXML
    private TableColumn<Object, Object> col_email;

    @FXML
    private Button showClients;
    @FXML
    private TextField insertName;
    @FXML
    private TextField insertAddress;
    @FXML
    private TextField insertEmail;
    @FXML
    private TextField deleteID;
    @FXML
    private TextField updateID;
    @FXML
    private TextField updateName;
    @FXML
    private TextField updateAddress;
    @FXML
    private TextField updateEmail;

    /**
     * This method sets the first page scene when the close button is pressed in the UI
     * @param event
     */
    public void closeButtonMain(ActionEvent event) {
        setScene(event, "/FirstPageDesign.fxml");
    }

    /**
     * This method sets the client design page when the close button is pressed in the UI
     * @param event
     */
    public void closeButtonClient(ActionEvent event) {
        setScene(event, "/ClientDesign.fxml");
    }

    /**
     * This method sets the scene given by an FXML file in the UI
     * @param event
     * @param FXMLFile
     */
    public void setScene(ActionEvent event, String FXMLFile) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(FXMLFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * This method sets the window of "Add client" in the UI
     * @param event
     */
    public void addNewClient(ActionEvent event) {
        setScene(event, "/AddClientDesign.fxml");
    }

    /**
     * This method validates the input introduced by the user in the window of "Add client"
     * @return true if the every requested data was introduced, otherwise false
     */
    public boolean validateAddInput() {
        return !insertName.getText().equals("") && !insertAddress.getText().equals("") && !insertEmail.getText().equals("");
    }

    /**
     * This method adds the introduced client in the database
     */
    public void addClientButton() {
        if (validateAddInput()) {
            Client client = new Client(insertName.getText(), insertEmail.getText(), insertAddress.getText());
            try{
                clientBLL.validators.get(0).validate(client);
                try {
                    clientBLL.insert(client);
                    alert.alertInsertSuccessful();
                } catch (Exception ex) {
                    alert.alertErrorInsert();
                }
            }catch (Exception ex){
                alert.alertInvalidEmail();
            }

        } else {
            alert.alertInvalidInput();
        }
    }

    /**
     * This method sets the window of "Edit client" in the UI
     * @param event
     */
    public void editClient(ActionEvent event) {
        setScene(event, "/EditClientDesign.fxml");
    }

    /**
     * This method validates the input introduced by the user in the window "Edit client"
     * @return true if every requested data was introduced, otherwise false
     */
    public boolean validateEditInput() {
        if (!updateID.getText().equals("")) {
            return !updateName.getText().equals("") || !updateAddress.getText().equals("") || !updateEmail.getText().equals("");
        } else return false;
    }

    /**
     * This method edits the introduced client in the database
     */
    public void editClientButton() {
        if (validateEditInput()) {
            Client client = null;
            try {
                client = clientBLL.findById(Integer.parseInt(updateID.getText()));
            } catch (Exception ex) {
                alert.alertInvalidID();
            }
            if (client != null) {
                if (!updateName.getText().equals("")) {
                    client.setName(updateName.getText());
                }
                if (!updateEmail.getText().equals("")) {
                    client.setEmail(updateEmail.getText());
                }
                if (!updateAddress.getText().equals("")) {
                    client.setAddress(updateAddress.getText());
                }
                try{
                    clientBLL.validators.get(0).validate(client);
                    try {
                        clientBLL.update(client);
                        alert.alertEditSuccessful();
                    } catch (Exception ex) {
                        alert.alertErrorEdit();
                    }
                }catch (Exception ex){
                    alert.alertInvalidEmail();
                }
            }
        } else {
            alert.alertInvalidInput();
        }
    }

    /**
     * This method sets the window of "Delete client" in the UI
     * @param event
     */
    public void deleteClient(ActionEvent event) {
        setScene(event, "/DeleteClientDesign.fxml");
    }

    /**
     * This method validates the input introduced by the user in the window "Delete client"
     * @return true if every requested data was introduced, otherwise false
     */
    public boolean validateDeleteInput() {
        return !deleteID.getText().equals("") && Integer.parseInt(deleteID.getText()) > 0;
    }

    /**
     * This method deletes the introduced client in the database
     */
    public void deleteClientButton() {
        if (validateDeleteInput()) {
            Client client = null;
            try{
                client = clientBLL.findById(Integer.parseInt(deleteID.getText()));
            }catch(Exception ex){
                alert.alertInvalidID();
            }
            if (client != null) {
                try{
                    clientBLL.validators.get(0).validate(client);
                    try {
                        clientBLL.delete(client);
                        alert.alertDeleteSuccessful();
                    } catch (Exception ex) {
                        alert.alertErrorDelete();
                    }
                }
                catch (Exception ex){
                    alert.alertInvalidEmail();
                }
            }
        }
        else{
            alert.alertInvalidInput();
        }
    }

    /**
     * This method sets the window of "View clients" in the UI
     * @param event
     */
    public void viewClients(ActionEvent event) {
        setScene(event, "/ViewClientsDesign.fxml");
    }

    /**
     * This method populates the table with all the clients from the database
     */
    public void setTableView(){
        showClients.setVisible(false);
        List<Client> clientList = clientBLL.findAll();

        try{
            for(Client client : clientList){
                observableList.add(new Client(client.getID(),client.getName(),client.getAddress(),client.getEmail()));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.setItems(observableList);
        table.setVisible(true);
    }
}
