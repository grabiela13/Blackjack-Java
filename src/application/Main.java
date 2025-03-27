package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the start screen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startscreen.fxml"));
            Parent root = loader.load();

            // Get the StartScreenController and set the primary stage
            StartScreenController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            // Create the scene
            Scene scene = new Scene(root, 800, 500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Set up the stage
            primaryStage.setTitle("Blackjack Game");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}