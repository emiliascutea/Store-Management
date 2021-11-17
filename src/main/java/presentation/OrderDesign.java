package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Client;
import model.OrderTable;
import model.Product;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * The class OrderDesign is a controller class for the windows regarding operations on orders: add, view
 */

public class OrderDesign {

    ProductBLL productBLL = new ProductBLL();
    ClientBLL clientBLL = new ClientBLL();
    OrderBLL orderBLL = new OrderBLL();
    OrderTable order;
    Alert alert = new Alert();
    ObservableList<String> productObservableList = FXCollections.observableArrayList();
    ObservableList<OrderTable> observableListView = FXCollections.observableArrayList();

    @FXML
    private TableView<OrderTable> table;
    @FXML
    private TableColumn<Object, Object> col_id;
    @FXML
    private TableColumn<Object, Object> col_clientID;
    @FXML
    private TableColumn<Object, Object> col_productID;
    @FXML
    private TableColumn<Object, Object> col_quantity;
    @FXML
    private TableColumn<Object, Object> col_price;


    @FXML
    private ChoiceBox<String> productList;
    @FXML
    private Button showProductsButton;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productQuantity;
    @FXML
    private TextField orderPrice;
    @FXML
    private TextField clientID;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button createBillButton;
    @FXML
    private Button showOrders;

    public float priceValue;
    public int quantityValue;
    public float orderPriceValue;
    public int clientIDValue;
    public int productIDValue;
    public Product product = null;

    /**
     * This method sets the order design scene when the close button is pressed in the UI
     * @param event
     */
    public void closeButtonOrder(ActionEvent event) {
        setScene(event, "/OrderDesign.fxml");
    }

    /**
     * This method sets the first page scene when the close button is pressed in the UI
     * @param event
     */
    public void closeButtonMain(ActionEvent event) {
        setScene(event, "/FirstPageDesign.fxml");
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
     * This method sets the window of "Add order" in the UI
     * @param event
     */
    public void addNewOrder(ActionEvent event) {
        setScene(event, "/AddOrderDesign.fxml");
    }


    /**
     * This method gets all the products from the database and places them in an observable list
     * @return
     */
    public ObservableList<String> getProductList() {
        List<Product> productList = productBLL.findAll();
        try {
            for (Product product : productList) {
                productObservableList.add(product.getName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productObservableList;
    }

    /**
     * This method sets the items of the products choice box and makes visible all labels, text fields and buttons in the "Add order" window in the UI
     */
    public void showProducts() {
        showProductsButton.setVisible(false);
        productList.setVisible(true);
        productList.setItems(getProductList());
        productPrice.setVisible(true);
        productQuantity.setVisible(true);
        orderPrice.setVisible(true);
        clientID.setVisible(true);
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        placeOrderButton.setVisible(true);
    }

    /**
     * This method gets the product selected by the user from the choice box
     */
    public void getChosenProduct() {
        productList.setOnAction(event -> {
            Product product = null;
            try {
                product = productBLL.findByName(productList.getValue());
                productIDValue = product.getID();
                priceValue = product.getPrice();
                productPrice.setText(String.valueOf(product.getPrice()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * This method calculates the total price of the order
     */
    public void calculateOrderPrice() {
        productQuantity.setOnAction(event -> {
            try {
                if (!productQuantity.getText().equals("")) {
                    quantityValue = Integer.parseInt(productQuantity.getText());
                    try {
                        product = productBLL.findByName(productList.getValue());
                        if (quantityValue <= product.getQuantity()) {
                            orderPriceValue = quantityValue * priceValue;
                            orderPrice.setText(String.valueOf(orderPriceValue));
                        }
                    } catch (Exception ex) {
                        assert product != null;
                        alert.alertInvalidQuantity(String.valueOf(product.getQuantity()));
                    }
                }
            } catch (Exception ex) {
                alert.alertInvalidInput();
            }
        });
    }

    /**
     * This method validates the input introduced by the user in the window of "Add order"
     * @return true if the every requested data was introduced, otherwise false
     */
    public boolean validateInput() {
        return !productQuantity.getText().equals("") && !clientID.getText().equals("") && !orderPrice.getText().equals("") && !productPrice.getText().equals("");
    }

    /**
     * This method adds a new order in the database
     */
    public void placeOrder() {
        if (validateInput()) {
            Client client = null;
            try {
                clientIDValue = Integer.parseInt(clientID.getText());
                client = clientBLL.findById(clientIDValue);
                order = new OrderTable(clientIDValue, productIDValue, quantityValue, orderPriceValue);
                try {
                    orderBLL.validators.get(0).validate(order);
                    try {
                        orderBLL.insert(order);
                        product.setQuantity(product.getQuantity() - quantityValue);
                        if (product.getQuantity() == 0) {
                            productBLL.delete(product);
                        } else {
                            productBLL.update(product);
                        }
                        alert.alertInsertSuccessful();
                        createBillButton.setVisible(true);
                    } catch (Exception ex) {
                        alert.alertErrorInsert();
                    }
                } catch (Exception ex) {
                    alert.alertInvalidQuantity(String.valueOf(product.getQuantity()));
                }
            } catch (Exception ex) {
                alert.alertClientNotFound();
            }
        } else {
            alert.alertInvalidInput();
        }
    }

    /**
     * This method creates a bill of the order, with information about the product, the price and the client which made the order
     */
    public void createBill() {
        StringBuilder string = new StringBuilder();
        string.append("Order Report for Client#");
        string.append(order.getClientID());
        string.append(".pdf");

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(string.toString()));
            document.open();
            document.add(new Paragraph("                        Order Bill\n\n"));

            Client client = clientBLL.findById(order.getClientID());
            document.add(new Paragraph("ID of the client: " + client.getID()));
            document.add(new Paragraph("Name of the client: " + client.getName()));
            document.add(new Paragraph("Email of the client: " + client.getEmail()));
            document.add(new Paragraph("Address of shipping: " + client.getAddress()));

            Product product = productBLL.findById(order.getProduct());
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("ID of the product: " + product.getID()));
            document.add(new Paragraph("Price of the product: " + product.getPrice()));
            document.add(new Paragraph("Quantity of the order: " + order.getQuantity()));
            document.add(new Paragraph("Price of the order: " + order.getPrice()));
            alert.alertBillSuccessfull();
            document.close();
        } catch (DocumentException | IOException e) {
           alert.alertBillUnsuccessfull();
        }
    }

    /**
     * This method sets the window of "View orders" in the UI
     * @param event
     */
    public void viewOrders(ActionEvent event) {
        setScene(event, "/ViewOrdersDesign.fxml");
    }

    /**
     * This method populates the table with all the orders from the database
     */
    public void setTableView() {
        showOrders.setVisible(false);
        List<OrderTable> orderList = orderBLL.findAll();

        try {
            for (OrderTable order : orderList) {
                observableListView.add(new OrderTable(order.getID(), order.getClientID(), order.getProduct(), order.getQuantity(), order.getPrice()));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_clientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        col_productID.setCellValueFactory(new PropertyValueFactory<>("product"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(observableListView);
        table.setVisible(true);
    }
}
