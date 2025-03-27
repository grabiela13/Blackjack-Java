package application.model;

import java.util.Scanner;

import application.BlackJackController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Game {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Action action;
    private BlackJackController controller;

    // Define the constructors for the game
    public Game(BlackJackController controller) {
        this.player = new Player();
        this.dealer = new Dealer();
        this.deck = new Deck();
        this.controller = controller; // Initialize the controller
        this.action = new Action(player, dealer, deck, controller);  // Initialize the Action classq
    }
    
    // Existing constructor for compatibility
    public Game() {
        this(null); // Call the primary constructor with no controller
    }
    
    // Getter methods
    
    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Action getAction() {
        return action;
    }
    
    
    public void start() {
        // Deal initial cards and update UI
        dealInitialCards();
    }
    
    private void dealInitialCards() {
    	if (controller != null) controller.hideActionButtons(); // Hide buttons
        Timeline timeline = new Timeline();

        // Deal player card 1
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0), event -> {
            Card player_card1 = deck.drawCard();
            player.addCard(player_card1);
            System.out.println("\nCard dealt to Player is: " + player_card1 + " [score: " + player.calculateScore() + "]");
            if (controller != null) controller.addCardToPlayer(player_card1);
        }));

        // Deal dealer card 1
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            Card dealer_card1 = deck.drawCard();
            dealer.addCard(dealer_card1);
            System.out.println("Card dealt to Dealer is: " + dealer_card1 + " [score: " + dealer.calculateScore() + "]");
            if (controller != null) controller.addCardToDealer(dealer_card1);
        }));

        // Deal player card 2
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), event -> {
            Card player_card2 = deck.drawCard();
            player.addCard(player_card2);
            System.out.println("Card dealt to Player is: " + player_card2 + " [score: " + player.calculateScore() + "]");
            if (controller != null) controller.addCardToPlayer(player_card2);
        }));

        // Deal dealer card 2
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), event -> {
            Card dealer_card2 = deck.drawCard();
            dealer.addCard(dealer_card2);
            System.out.println("Card dealt to Dealer is: " + dealer_card2);
            if (controller != null) controller.addCardToDealer(dealer_card2);
            
            // Check for blackjack after all cards are dealt
            if (player.calculateScore() == 21) {
                System.out.println("Blackjack! You have a score of 21.");
                player.stake = player.stake + player.bet;
                action.setGameOver(true);
                if (controller != null) controller.showEndGameMessage("Blackjack! You have a score of 21.");
            }

        }));
        
        // Final KeyFrame: Show buttons after all cards are dealt
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4), event -> {
            if (controller != null) controller.showActionButtons(); // Show buttons after animations
        }));

        // Play the timeline
        timeline.setCycleCount(1); // Run once
        timeline.play();
    }
      
    

           
}
