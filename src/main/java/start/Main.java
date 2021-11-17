package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/FirstPageDesign.fxml"));
        primaryStage.setTitle("Order Management Interface");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }

    public static void main(String[] args) {
          launch();
    }
}
