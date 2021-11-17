package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The class FirstPageDesign is a controller class for the windows regarding operations on each item: client, product and order
 */
public class FirstPageDesign {
    /**
     * This method sets the window of the first page in the UI
     * @param event
     */
    public void setScene(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FirstPageDesign.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Order Management Interface");
        window.setScene(scene);
        window.show();
    }

    /**
     * This method creates a new instance of the ClientDesign class and sets the window of the client design in the UI
     * @param event
     */
    public void performClientOperation(ActionEvent event){
        ClientDesign clientDesign = new ClientDesign();
        clientDesign.setScene(event,"/ClientDesign.fxml");

    }

    /**
     * This method creates a new instance of the ProductDesign class and sets the window of the product design in the UI
     * @param event
     */
    public void performProductOperation(ActionEvent event){
        ProductDesign productDesign=new ProductDesign();
        productDesign.setScene(event,"/ProductDesign.fxml");

    }

    /**
     * This method creates a new instance of the OrderDesign class and sets the window of the order design in the UI
     * @param event
     */
    public void performOrderOperation(ActionEvent event){
        OrderDesign orderDesign = new OrderDesign();
        orderDesign.setScene(event, "/OrderDesign.fxml");
    }
}
