package application.model;

import application.BlackJackController;

public class Compare {

    public static void compareScores(Player player, Dealer dealer, BlackJackController controller) {
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();
        
        System.out.println("Player final score: " + playerScore);
        System.out.println("Dealer final score: " + dealerScore);
        
        // Determine and display game outcome
        if (playerScore > 21) {
            System.out.println("Player busts! Dealer wins.");
            player.stake = player.stake - player.bet;
            if (controller != null) controller.showEndGameMessage("Player busts! Dealer wins.");
            // player.score = 0;
        } else if (dealerScore > 21) {
            System.out.println("Dealer busts! Player wins.");
            player.stake = player.stake + player.bet;
            if (controller != null) controller.showEndGameMessage("Dealer busts! Player wins.");
            // player.score = 0;
        } else if (playerScore > dealerScore) {
            System.out.println("Player wins!");
            player.stake = player.stake + player.bet;
            if (controller != null) controller.showEndGameMessage("Player wins!");
        } else if (dealerScore > playerScore) {
            System.out.println("Dealer wins!");
            // player.score = 0;
            player.stake = player.stake - player.bet;
            if (controller != null) controller.showEndGameMessage("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
            // player.score = 0;
            if (controller != null) controller.showEndGameMessage("It's a tie!");
        }
    }
}
