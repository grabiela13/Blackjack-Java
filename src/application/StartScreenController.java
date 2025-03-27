package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController {
	
    @FXML
    private Button startButton; // Declare hitButton
    
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void onStartButtonClicked() {
        try {
            // Load the blackjack table scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("blackjacktest.fxml"));
            Parent root = loader.load();

            // Create a new scene and set it
            Scene scene = new Scene(root, 800, 500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
