package application.model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> cards;

    // Initialize the dealer's hand
    public Dealer() {
        cards = new ArrayList<>();
    }

    // Add a card to the dealer's hand
    public void addCard(Card card) {
        if (cards.size() == 1) {
            card.flipCard();    // Call the flipCard method on the second card
        }
        cards.add(card);
    }
    
    // Reveal the dealer's hidden card
    public void revealHiddenCard() {
        if (cards.size() > 1 && !cards.get(1).isFaceUp()) {
            cards.get(1).flipCard();
        }
    }

    // Return the list of cards held by the dealer
    public List<Card> getCards() {
        return cards;
    }
    
    // Reset dealers hand (for after each round)
    public void resetHand() {
        cards.clear(); // Clear the current hand
    }
    
    
    // Calculate hand's score, handle aces too
    public int calculateScore() {
        int score = 0;
        int aceCount = 0;
        
        for (Card card : cards) {
            score += card.getValue();
            if (card.getValue() == 11) 
            	aceCount++; // Count aces initially as 11 points
        }
        
        // Adjust for Aces if score > 21
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }
        
        return score;
    }


}