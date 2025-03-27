package application.model;
import java.util.Scanner;

import application.BlackJackController;

public class Action {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private BlackJackController controller;
    private boolean playerTurn;
    private boolean gameOver; // New flag to track if the game is over
    private boolean playerStood; // New flag to track if the player has already stood

    public Action(Player player, Dealer dealer, Deck deck, BlackJackController controller) {
        this.player = player;
        this.dealer = dealer;
        this.deck = deck;
        this.controller = controller;
        this.playerTurn = false;
        this.gameOver = false;
        this.playerStood = false;
    }

    
    
    // Method to place a bet
    public void placeBet() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("*** Player place one bet ***");
//        while (true) {
//            if (scanner.hasNextInt()) {
//                int userBet = scanner.nextInt();
//                if (userBet > 1) {
//                    System.out.println("*** You can only place one bet ***");
//                } else {
//                    player.bet = userBet; // Directly assign the bet value to player.bet
//                   
//                    break;
//                }
//            } else {
//                System.out.println("Invalid input. Please enter a numeric value.");
//                scanner.next(); // Clear invalid input
//            }
//        }
    	player.bet = 1;
    }

    // Set game over 
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    // Start the player's action sequence
    public void playerAction() {
//        Scanner scanner = new Scanner(System.in);
        playerTurn = true; // Start the player's turn

//        while (playerTurn) {
//            while (player.bet > 1) {
//              
//                System.out.println("*** Player place one bet ***");
//                int userBet = scanner.nextInt();
//                if (userBet > 1) {
//                    System.out.println("*** You can only place one bet ***");
//                }
//                player.bet = userBet;
//            }

//            System.out.println("\n*** Choose your action ***");
//            System.out.println("*** [hit, stand] ***\n");
//            String action = scanner.nextLine().toLowerCase();
//
//            if (action.equals("hit")) {
//                hit(player);
//            } else if (action.equals("stand")) {
//                stand();
//                playerTurn = false; // End the player's turn
//            } else {
//                System.out.println("Invalid action. Please choose 'hit' or 'stand'.");
//            }            
//        }
    }

    // "Hit" action for the player
    public void hit(Player player) {
        if (gameOver || playerStood) {
            System.out.println("You cannot hit anymore. Game is either over or you already stood.");
            return;
        }
        
        System.out.println("Your action: hit\n");

        // Draw a card from the deck and add it to the player's hand
        Card newCard = deck.drawCard();
        player.addCard(newCard);
        
        if (controller != null) controller.addCardToPlayer(newCard); // Update UI


        System.out.println("Card drawn by player: " + newCard);

        int playerScore = player.calculateScore();
        System.out.println("Player's score: " + playerScore);

        // Check if the player busts or gets a blackjack
        if (playerScore > 21) {
            System.out.println("You busted with a score of " + playerScore + "!");
            gameOver = true;
            playerTurn = false;
            player.stake = player.stake - player.bet;
            if (controller != null) controller.showEndGameMessage("You busted with a score of " + playerScore + "!");
            // player.score = 0;
            
            dealer.revealHiddenCard();
            if (controller != null) controller.revealDealerHiddenCard(dealer.getCards().get(1)); // Reveal the dealer's second card (face-up)
            
        } else if (playerScore == 21) {
            System.out.println("Blackjack! You have a score of 21.");
            gameOver = true;
            playerTurn = false;
            player.stake = player.stake + player.bet;
            if (controller != null) controller.showEndGameMessage("Blackjack! You have a score of 21.");
            // player.score = 0;
        }
    }

    // "Stand" action
    public void stand() {
    	
        if (gameOver || playerStood) {
            System.out.println("You cannot stand anymore. Game is either over or you already stood.");
            return;
        }
        
        playerStood = true;
        
        System.out.println("Your action: stand\n");
        
        dealer.revealHiddenCard();
        if (controller != null) controller.revealDealerHiddenCard(dealer.getCards().get(1)); // Reveal the dealer's second card (face-up)
         
        dealerAction(); // Call dealer's actions
    }

    // Dealer's actions
    public void dealerAction() {
        dealer.revealHiddenCard();
        if (controller != null) controller.revealDealerHiddenCard(dealer.getCards().get(1)); // Reveal the dealer's second card (face-up)
        
        
        System.out.println("Dealer's hidden card is: " + dealer.getCards().get(1));

        int dealerScore = dealer.calculateScore();
        while (dealerScore < 17) {
            System.out.println("Dealer hits.");
            Card dealerNewCard = deck.drawCard();
            dealer.addCard(dealerNewCard);
            
            if (controller != null) controller.addCardToDealer(dealerNewCard); // Update UI
            

            dealerScore = dealer.calculateScore();
            System.out.println("Dealer's new score: " + dealerScore);
        }

        Compare.compareScores(player, dealer, controller); // Compare scores
    }
    
    // Method to reset the game (for after each round)
    public void resetGame() {
        playerTurn = false;
        gameOver = false;
        playerStood = false;

        // Reset the player's and dealer's hands
        player.resetHand();
        dealer.resetHand();
    }
    
    
    
}

