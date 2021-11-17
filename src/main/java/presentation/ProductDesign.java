package presentation;

import bll.ProductBLL;
import bll.validators.Validator;
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
import model.Product;
import java.io.IOException;
import java.util.List;

/**
 * The class ProductDesign is a controller class for the windows regarding operations on products: add, edit, view, delete
 */
public class ProductDesign {

    ProductBLL productBLL = new ProductBLL();
    Alert alert = new Alert();

    ObservableList<Product> observableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Object, Object> col_id;
    @FXML
    private TableColumn<Object, Object> col_name;
    @FXML
    private TableColumn<Object, Object> col_price;
    @FXML
    private TableColumn<Object, Object> col_quantity;

    @FXML
    private Button showProducts;
    @FXML
    private TextField insertName;
    @FXML
    private TextField insertPrice;
    @FXML
    private TextField insertQuantity;
    @FXML
    private TextField deleteID;
    @FXML
    private TextField updateID;
    @FXML
    private TextField updateName;
    @FXML
    private TextField updatePrice;
    @FXML
    private TextField updateQuantity;

    /**
     * This method sets the first page scene when the close button is pressed in the UI
     * @param event
     */
    public void closeButtonMain(ActionEvent event) {

        setScene(event, "/FirstPageDesign.fxml");
    }

    /**
     * This method sets the product design page when the close button is pressed in the UI
     * @param event
     */
    public void closeButtonProduct(ActionEvent event){
        setScene(event,"/ProductDesign.fxml");
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
     * This method sets the window of "Add product" in the UI
     * @param event
     */
    public void addProduct(ActionEvent event) {
        setScene(event, "/AddProductDesign.fxml");
    }

    /**
     * This method validates the input introduced by the user in the window of "Add product"
     * @return true if the every requested data was introduced, otherwise false
     */
    public boolean validateAddInput(){
        return !insertName.getText().equals("") && !insertPrice.getText().equals("") && !insertQuantity.getText().equals("");
    }

    /**
     * This method adds the introduced product in the database
     */
    public void addProductButton(){
        if (validateAddInput()) {
            Product product = new Product(insertName.getText(), Float.parseFloat(insertPrice.getText()), Integer.parseInt(insertQuantity.getText()));
            try{
                for(Validator<Product> validator: productBLL.validators){
                    validator.validate(product);
                    try {
                        productBLL.insert(product);
                        alert.alertInsertSuccessful();
                    } catch (Exception ex) {
                        alert.alertErrorInsert();
                    }
                }
            }catch (Exception ex){
                alert.alertInvalidInput();
            }
        } else {
            alert.alertInvalidInput();
        }
    }

    /**
     * This method sets the window of "Edit product" in the UI
     * @param event
     */
    public void editProduct(ActionEvent event){
        setScene(event, "/EditProductDesign.fxml");
    }

    /**
     * This method validates the input introduced by the user in the window "Edit product"
     * @return true if every requested data was introduced, otherwise false
     */
    public boolean validateEditInput() {
        if (!updateID.getText().equals("")) {
            return !updateName.getText().equals("") || !updatePrice.getText().equals("") || !updateQuantity.getText().equals("");
        } else return false;
    }

    /**
     * This method edits the introduced product in the database
     */
    public void editProductButton() {
        if (validateEditInput()) {
            Product product = null;
            try {
                product = productBLL.findById(Integer.parseInt(updateID.getText()));
            } catch (Exception ex) {
                alert.alertInvalidID();
            }
            if (product != null) {
                if (!updateName.getText().equals("")) {
                    product.setName(updateName.getText());
                }
                if (!updatePrice.getText().equals("")) {
                    product.setPrice(Float.parseFloat(updatePrice.getText()));
                }
                if (!updateQuantity.getText().equals("")) {
                    product.setQuantity(Integer.parseInt(updateQuantity.getText()));
                }
                try{
                    for(Validator<Product> validator: productBLL.validators) {
                        validator.validate(product);
                    }
                    try {
                        productBLL.update(product);
                        alert.alertEditSuccessful();
                    } catch (Exception ex) {
                        alert.alertErrorEdit();
                    }
                }catch (Exception ex){
                    alert.alertInvalidInput();
                }
            }
        } else {
            alert.alertInvalidInput();
        }
    }

    /**
     * This method sets the window of "Delete product" in the UI
     * @param event
     */
    public void deleteProduct(ActionEvent event){
        setScene(event,"/DeleteProductDesign.fxml");
    }

    /**
     * This method validates the input introduced by the user in the window "Delete product"
     * @return true if every requested data was introduced, otherwise false
     */
    public boolean validateDeleteInput() {
        return !deleteID.getText().equals("") && Integer.parseInt(deleteID.getText()) > 0;
    }

    /**
     * This method deletes the introduced product in the database
     */
    public void deleteProductButton() {
        if (validateDeleteInput()) {
            Product product = null;
            try{
                product = productBLL.findById(Integer.parseInt(deleteID.getText()));
            }catch(Exception ex){
                alert.alertInvalidID();
            }
            if (product != null) {
                try{
                    for(Validator<Product> validator: productBLL.validators) {
                        validator.validate(product);
                    }
                    try {
                        productBLL.delete(product);
                        alert.alertDeleteSuccessful();
                    } catch (Exception ex) {
                        alert.alertErrorDelete();
                    }
                }catch (Exception ex){
                    alert.alertInvalidInput();
                }
            }
        }
        else{
            alert.alertInvalidInput();
        }
    }

    /**
     * This method sets the window of "View products" in the UI
     * @param event
     */
    public void viewProducts(ActionEvent event){
        setScene(event,"/ViewProductsDesign.fxml");
    }

    /**
     * This method populates the table with all the products from the database
     */
    public void setTableView(){
        showProducts.setVisible(false);
        List<Product> productList = productBLL.findAll();

        try{
            for(Product product : productList){
                observableList.add(new Product(product.getID(),product.getName(),product.getPrice(),product.getQuantity()));

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.setItems(observableList);
    }
}
