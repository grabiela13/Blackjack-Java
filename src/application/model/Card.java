package application.model;

public class Card {
	// Card fields
	private String rank; 
	private String suit;
	private boolean face_up;
	
	// Card constructor 
	public Card(String rank, String suit, boolean face_up) {
		this.rank = rank;
		this.suit = suit; 
		this.face_up = face_up;
	}
	
	@Override // To override the toString method. Helps print out the card beautifully
	public String toString() {
		if (this.face_up) 
			return rank + " of " + suit;
		else 
			return "?";
	}
	
	// To turn card to face down or up 
	public void flipCard() {
		if (this.face_up)
			this.face_up = false;
		else 
			this.face_up = true;
	}
	
    // Check if card is face up
    public boolean isFaceUp() {
        return face_up;
    }
	
	// Function to return the card's value
	public int getValue(){
		if(rank == "jack" || rank == "queen" || rank == "king") {
			return 10;
		} else if (rank == "ace") {
			return 11; // Default to 11, can later handle Ace as 1 depending on hand 
		} else {
			return Integer.valueOf(rank);
		}
	}
	
    // Generate the file path for the card's image
	public String getImagePath() {
	    String path;
	    if (this.face_up) {
	        path = "/application/resources/cards/" + rank + "_of_" + suit + ".png";
	    } else {
	        path = "/application/resources/cards/face_down.png";
	    }

	    return path;
	}

}
