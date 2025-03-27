package application.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards;
    public int stake = 150;
    public int bet = 0;
    // public int score = 0;
    // Initialize the player's hand
    public Player() {
        cards = new ArrayList<>();
    }

    // Add card to player's hand
    public void addCard(Card card) {
        cards.add(card);
    }

    // Return the list of cards of the player
    public List<Card> getCards() {
        return cards;
    }
    
    // Reset players hand (for after each round)
    public void resetHand() {
        cards.clear(); // Clear the current hand
    }
    
    // Handle a hand's score, also handles aces if present
    public int calculateScore() {
    	int score = 0;
        int aceCount = 0;
      
        
        for (Card card : cards) {
            score += card.getValue();
            if (card.getValue() == 11) aceCount++; // Count aces initially as 11 points
        }
        
        // Adjust for Aces if score > 21
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }
        
        return score;
    }
    

}