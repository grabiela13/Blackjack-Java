package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

        // Loop to create the deck of cards 
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit, true));
            }
        }
        // Shuffle the deck
        Collections.shuffle(cards);
    }

    // Remove and return the last card from the deck
    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }
}