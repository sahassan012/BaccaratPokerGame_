import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.util.Pair;

//BaccaratDealer
public class BaccaratDealer {
	
	//Deck and map that will handle images of cards
	ArrayList<Card> deck;
	HashMap<Integer, Pair<Card, Image>> cardImages;
	
	//Initialize the dealer with cards and a deck
	BaccaratDealer(){
		deck = new ArrayList<Card>();
		cardImages = new HashMap<Integer, Pair<Card, Image> >();
	}
	
	//StoreCardImages
	//	Stores images of cards with pair values
	public void storeCardImages() throws FileNotFoundException
	{ 
		int card = 0;
		for (Card c: deck) {
			card++;
			//System.out.println("Card: " + card + ", " + c.value + " pulling image from " + "src/cards/" + card + ".png");
			Image cardImage = new Image(new FileInputStream("src/cards/" + card + ".png" ));
			Pair<Card,Image> p = new Pair<Card,Image>(c, cardImage);	
			cardImages.put(card, p);
		}
	}
	
	//generateDeck
	//	Generates deck with all of the cards
	public void generateDeck() throws FileNotFoundException
	{
		Card c1 = new Card("King", 10, 1); 
		Card c2 = new Card("King", 10, 2); 
		Card c3 = new Card("King", 10, 3); 
		Card c4 = new Card("King", 10, 4); 
		
		Card c5 = new Card("Queen", 10, 5);
		Card c6 = new Card("Queen", 10, 6);
		Card c7 = new Card("Queen", 10, 7);
		Card c8 = new Card("Queen", 10, 8);
		
		Card c9 = new Card("Jack", 10, 9); 
		Card c10 = new Card("Jack", 10, 10); 
		Card c11 = new Card("Jack", 10, 11); 
		Card c12 = new Card("Jack", 10, 12); 
		
		Card c13 = new Card("Hearts", 10, 13); 
		Card c14 = new Card("Spades", 10, 14);
		Card c15 = new Card("Clubs", 10, 15); 
		Card c16 = new Card("Diamonds", 10, 16);
		
		Card c17 = new Card("Hearts", 9, 17); 
		Card c18 = new Card("Spades", 9, 18);  
		Card c19 = new Card("Clubs", 9, 19);  
		Card c20 = new Card("Diamonds", 9, 20); 
		
		Card c21 = new Card("Hearts", 8, 21); 
		Card c22 = new Card("Spades", 8, 22); 
		Card c23 = new Card("Clubs", 8, 23);  
		Card c24 = new Card("Diamonds", 8, 24); 
		
		Card c25 = new Card("Hearts", 7, 25); 
		Card c26 = new Card("Spades", 7, 26);  
		Card c27 = new Card("Clubs", 7, 27);  
		Card c28 = new Card("Diamonds", 7, 28); 
		
		Card c29 = new Card("Hearts", 6, 29);
		Card c30 = new Card("Spades", 6, 30);
		Card c31 = new Card("Clubs", 6, 31);
		Card c32 = new Card("Diamonds", 6, 32);
		
		Card c33 = new Card("Hearts", 5, 33);
		Card c34 = new Card("Spades", 5, 34);
		Card c35 = new Card("Clubs", 5, 35);
		Card c36 = new Card("Diamonds", 5, 36);

		Card c37 = new Card("Hearts", 4, 37);
		Card c38 = new Card("Spades", 4, 38);
		Card c39 = new Card("Clubs", 4, 39);
		Card c40 = new Card("Diamonds", 4, 40);
		
		Card c41 = new Card("Hearts", 3, 41);
		Card c42 = new Card("Spades", 3, 42);
		Card c43 = new Card("Clubs", 3, 43);
		Card c44 = new Card("Diamonds", 3, 44);

		Card c45 = new Card("Hearts", 2, 45);
		Card c46 = new Card("Spades", 2, 46);
		Card c47 = new Card("Clubs", 2, 47);
		Card c48 = new Card("Diamonds", 2, 48);
		
		Card c49 = new Card("Ace", 1, 49); 
		Card c50 = new Card("Ace", 1, 50); 
		Card c51 = new Card("Ace", 1, 51); 
		Card c52 = new Card("Ace", 1, 52); 

		deck.add(c1); 
		deck.add(c2); 
		deck.add(c3); 
		deck.add(c4); 
		deck.add(c5); 
		deck.add(c6); 
		deck.add(c7); 
		deck.add(c8); 
		deck.add(c9); 
		deck.add(c10); 
		deck.add(c11); 
		deck.add(c12); 
		deck.add(c13); 
		deck.add(c14); 
		deck.add(c15); 
		deck.add(c16); 
		deck.add(c17); 
		deck.add(c18); 
		deck.add(c19); 
		deck.add(c20); 
		deck.add(c21); 
		deck.add(c22); 
		deck.add(c23); 
		deck.add(c24); 
		deck.add(c25); 
		deck.add(c26); 
		deck.add(c27); 
		deck.add(c28); 
		deck.add(c29); 
		deck.add(c30); 
		deck.add(c31); 
		deck.add(c32); 
		deck.add(c33); 
		deck.add(c34); 
		deck.add(c35); 
		deck.add(c36); 
		deck.add(c37); 
		deck.add(c38); 
		deck.add(c39); 
		deck.add(c40); 
		deck.add(c41); 
		deck.add(c42); 
		deck.add(c43); 
		deck.add(c44); 
		deck.add(c45); 
		deck.add(c46); 
		deck.add(c47); 
		deck.add(c48); 
		deck.add(c49); 
		deck.add(c50); 
		deck.add(c51); 
		deck.add(c52); 
		
		storeCardImages();
	}
	
	//dealhand
	//	Returns arraylist of cards	
	public ArrayList<Card> dealHand()
	{
		ArrayList<Card> dealtCards = new ArrayList<Card>();
		
		Card c = deck.get(0);
		Card c2 = deck.get(1);
		
		dealtCards.add(c);
		dealtCards.add(c2);
		
		deck.remove(0);
		deck.remove(0);
		
		return dealtCards;
	}
	
	//drawOne
	//	Draws one cards and returns it
	public Card drawOne()
	{	
		Card c = deck.get(0);
		deck.remove(0);
		return c;	
	}
	
	//shuffleDeck
	//	Randomizes the deck
	public void shuffleDeck()
	{
		Collections.shuffle(deck);
	}
	
	//deckSize
	//	returns the size of the deck
	public int deckSize()
	{
		return deck.size();
		
	}
	
	//deleteDeck
	//	deletes the deck
	public void deleteDeck()
	{
		deck = new ArrayList<Card>();
	}

}
