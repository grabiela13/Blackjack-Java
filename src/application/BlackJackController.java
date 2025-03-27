package application;

import java.net.URL;

import application.model.Action;
import application.model.Game;
import application.model.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class BlackJackController {

    @FXML
    private HBox playerCardBox;

    @FXML
    private HBox dealerCardBox;
    
    @FXML
    private Button hitButton; // Declare hitButton

    @FXML
    private Button standButton; // Declare standButton
    
    @FXML
    private StackPane overlayPane;

    @FXML
    private Label resultLabel;


    private Game game;
    private Action action;

    public void initialize() {
        System.out.println("Initializing Blackjack Game...");
        
//        playerCardBox.setSpacing(-30); // If negative, cards overlap
//        dealerCardBox.setSpacing(-30);
        
        // Initialize game logic in a background thread
        new Thread(() -> {
            game = new Game(this); // Pass the controller to the Game class
            action = game.getAction(); // Get the Action instance for player actions

            // Start the game logic and deal initial cards
            Platform.runLater(() -> {
                game.start(); // Start the game (deals cards and updates UI)
            });
        }).start();
    }
    
    // Replace the dealer's hidden card with the face-up card
    public void revealDealerHiddenCard(application.model.Card card) {
        if (dealerCardBox.getChildren().size() > 1) { // Ensure there is a second card
            // Replace the second card's ImageView
            ImageView cardImageView = new ImageView(new Image(getClass().getResource(card.getImagePath()).toExternalForm()));
            cardImageView.setFitWidth(100);
            cardImageView.setPreserveRatio(true);
            dealerCardBox.getChildren().set(1, cardImageView); // Replace the second card
        }
    }

    // Add a card to the player's display with animation
    public void addCardToPlayer(application.model.Card card) {
        ImageView cardImageView = new ImageView(new Image(getClass().getResource(card.getImagePath()).toExternalForm()));
        cardImageView.setFitWidth(100);
        cardImageView.setPreserveRatio(true);

        // Set initial position (off-screen to the right)
        cardImageView.setTranslateX(500);

        // Create the transition animation
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), cardImageView);
        transition.setToX(0); // Move to its final position

        // Add a delay before showing the card
        Timeline delayTimeline = new Timeline();
        delayTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            playerCardBox.getChildren().add(cardImageView); // Add card to UI
            transition.play(); // Play the animation
        }));

        delayTimeline.setCycleCount(1);
        delayTimeline.play(); // Start the delay
    }

    // Add a card to the dealer's display with animation
    public void addCardToDealer(application.model.Card card) {
        ImageView cardImageView = new ImageView(new Image(getClass().getResource(card.getImagePath()).toExternalForm()));
        cardImageView.setFitWidth(100);
        cardImageView.setPreserveRatio(true);

        // Set initial position (off-screen to the right)
        cardImageView.setTranslateX(500);

        // Create the transition animation
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), cardImageView);
        transition.setToX(0); // Move to its final position

        // Add a delay before showing the card
        Timeline delayTimeline = new Timeline();
        delayTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            dealerCardBox.getChildren().add(cardImageView); // Add card to UI
            transition.play(); // Play the animation
        }));

        delayTimeline.setCycleCount(1);
        delayTimeline.play(); // Start the delay
	}

    // Handle the "Hit" button click
    @FXML
    private void onHitButtonClicked() {
        action.hit(game.getPlayer());
    }

    // Handle the "Stand" button click
    @FXML
    private void onStandButtonClicked() {
        action.stand();
    }
    
    // Hide the action buttons
    public void hideActionButtons() {
        hitButton.setVisible(false);
        standButton.setVisible(false);
    }

    // Show the action buttons
    public void showActionButtons() {
        hitButton.setVisible(true);
        standButton.setVisible(true);
    } 
    

    // Show the overlay with the message
    public void showEndGameMessage(String message) {
        resultLabel.setText(message); // Set the end-game message
        overlayPane.setVisible(true); // Show the overlay
        hitButton.setDisable(true); // Disable buttons
        standButton.setDisable(true);
        hideActionButtons();
        
    }

    
    // Hide the overlay and reset for a new game
    public void hideEndGameOverlay() {
        resultLabel.setText(""); // Clear the message
        overlayPane.setVisible(false); // Hide the overlay
        hitButton.setDisable(false); // Re-enable buttons
        standButton.setDisable(false);
    }
    


}
