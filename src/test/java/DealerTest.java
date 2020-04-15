import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

//
// Project 2  
//		Baccarat
//
//
// Syed Hassan
// NetID: shassa25
//

class DealerTest {
	
	
	//Testing constructor for Baccarat Dealer
	@Test
	void testDealerConstr1() throws FileNotFoundException{;
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		
		b.generateDeck();
		ArrayList<Card> playerHand = b.dealHand();
		ArrayList<Card> bankerHand = b.dealHand();
		
		//System.out.println(g.gameLogic.whoWon(g.playerHand, g.bankerHand) + " won! Sum: " + g.gameLogic.handTotal(g.playerHand));
		assertEquals(g.whoWon(playerHand, bankerHand), "Tie");
	}
	
	//Testing constructor for Baccarat Dealer
	@Test
	void testDealerConstr2() throws FileNotFoundException{;
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		
		b.generateDeck();
		ArrayList<Card> playerHand = b.dealHand();
		ArrayList<Card> bankerHand = b.dealHand();
		
		//System.out.println(g.gameLogic.whoWon(g.playerHand, g.bankerHand) + " won! Sum: " + g.gameLogic.handTotal(g.playerHand));
		assertEquals(g.whoWon(playerHand, bankerHand), "Tie");
	}
	
	//Testing Card class constructor and when banker wins
	@Test
	void testCardConstr1() 
	{
		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Ace", 1, 10));
		playerHand.add(new Card("King", 10, 10));
		
		bankerHand.add(new Card("Spades", 8, 10));
		bankerHand.add(new Card("Jack", 10, 10));
		assertEquals(g.whoWon(playerHand, bankerHand), "Banker");
	}
	
	//Testing Card class constructor and when player wins
	@Test
	void testCardConstr2() 
	{
		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		bankerHand.add(new Card("Ace", 1, 10));
		bankerHand.add(new Card("King", 10, 10));
		
		playerHand.add(new Card("Spades", 8, 10));
		playerHand.add(new Card("Jack", 10, 10));
		//System.out.println(g.gameLogic.whoWon(g.playerHand, g.bankerHand) + " won! Sum: " + g.gameLogic.handTotal(g.playerHand));
		assertEquals(g.whoWon(playerHand, bankerHand), "Player");		
	}
	
	//Testing deckSize in class BaccaratDealer
	@Test
	void testDeckSize1() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		b.generateDeck();
		assertEquals(52, b.deckSize());		
	}
	
	//Testing size of deck in class BaccaratDealer
	@Test
	void testDeckSize2() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		assertEquals(0, b.deckSize());		
	}
		
	//Testing generateDeck method in class BaccaratDealer
	@Test
	void testGenerateDeck1() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		b.generateDeck();
		assertEquals(52, b.deckSize());		
	}
	
	//Testing generateDeck method in class BaccaratDealer
	@Test
	void testGenerateDeck2() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		
		b.generateDeck();
		b.drawOne();
		b.deleteDeck();
		b.generateDeck();
	
		assertEquals(52, b.deckSize());		
	}
		
	//Testing drawOne method in class BaccaratDealer
	@Test
	void testDrawOne1() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		b.generateDeck();
		b.drawOne();
		assertEquals(51, b.deckSize());	
	}
	
	//Testing drawOne method in class BaccaratDealer
	@Test
	void testDrawOne2() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		b.generateDeck();
		b.drawOne();
		b.drawOne();
		assertEquals(50, b.deckSize());	
	}
		
	//Testing dealHand method in class BaccaratDealer
	@Test
	void  testDealHand1() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		b.generateDeck();
		
		ArrayList<Card> playerHand = b.dealHand();
		ArrayList<Card> bankerHand = b.dealHand();
		assertEquals(2, playerHand.size());	
		assertEquals(2, bankerHand.size());	
	}
	
	//Testing dealHand method in class BaccaratDealer
	@Test
	void  testDealHand2() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		b.generateDeck();
		
		ArrayList<Card> playerHand = b.dealHand();
		ArrayList<Card> bankerHand = b.dealHand();
		int p = g.handTotal(playerHand);
		int b1 = g.handTotal(bankerHand);
		
		assertEquals(p, g.handTotal(playerHand));	
		assertEquals(b1, g.handTotal(bankerHand));	
	}

	//Testing deleteDeck method in class BaccaratDealer
	@Test
	void  testdeleteDeck() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		b.generateDeck();
		b.deleteDeck();
		
		assertEquals(0, b.deckSize());		
	}

	//Testing handTotal method in class BaccaratGameLogic
	@Test
	void  testHandTotal() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Ace", 1, 10));
		playerHand.add(new Card("King", 10, 10));
		
		bankerHand.add(new Card("Spades", 8, 10));
		bankerHand.add(new Card("Jack", 10, 10));
		
		assertEquals(1, g.handTotal(playerHand));
		assertEquals(8, g.handTotal(bankerHand));
	}
	
	//Testing handTotal method in class BaccaratGameLogic
	@Test
	void  testHandTotal2() throws FileNotFoundException
	{
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Spades", 8, 10));
		playerHand.add(new Card("Clubs", 2, 10));
		
		bankerHand.add(new Card("King", 10, 10));
		bankerHand.add(new Card("Jack", 10, 10));
		
		assertEquals(0, g.handTotal(playerHand));
		assertEquals(0, g.handTotal(bankerHand));
	}
	
	//Testing whoWon method in class BaccaratGameLogic
	@Test
	void testWhoWon1() throws FileNotFoundException{;
		BaccaratDealer b = new BaccaratDealer();
		BaccaratGameLogic g = new BaccaratGameLogic();
		b.generateDeck();
		ArrayList<Card> playerHand = b.dealHand();
		ArrayList<Card> bankerHand = b.dealHand();
		
		assertEquals(g.whoWon(playerHand, bankerHand), "Tie");
	}
	
	//Testing whoWon method in class BaccaratGameLogic
	@Test
	void testWhoWon2() throws FileNotFoundException{;

		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Spades", 9, 10));
		playerHand.add(new Card("Clubs", 2, 10));
		
		bankerHand.add(new Card("King", 10, 10));
		bankerHand.add(new Card("Jack", 10, 10));
		
		assertEquals(g.whoWon(playerHand, bankerHand), "Player");
	}

	//Testing evaluateBankerDraw method in class BaccaratGameLogic
	@Test
	void evaluateBankerDraw1() throws FileNotFoundException{;

		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Spades", 1, 10));
		playerHand.add(new Card("Clubs", 2, 10));
		
		bankerHand.add(new Card("Spades", 1, 10));
		bankerHand.add(new Card("Jack", 1, 10));
		Card c = null;
		assertEquals(g.evaluateBankerDraw(bankerHand, c), true);
	}
	
	//Testing evaluateBankerDraw method in class BaccaratGameLogic
	@Test
	void evaluateBankerDraw2() throws FileNotFoundException{;

		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Spades", 1, 10));
		playerHand.add(new Card("Clubs", 2, 10));
		
		bankerHand.add(new Card("Spades", 8, 10));
		bankerHand.add(new Card("Jack", 1, 10));
		Card c = null;
		assertEquals(g.evaluateBankerDraw(bankerHand, c), false);
	}

	//Testing evaluatePlayerDraw method in class BaccaratGameLogic
	@Test
	void evaluatePlayerDraw1() throws FileNotFoundException{;

		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Spades", 5, 10));
		playerHand.add(new Card("Clubs", 2, 10));
		
		bankerHand.add(new Card("Spades", 1, 10));
		bankerHand.add(new Card("Jack", 1, 10));
		
		assertEquals(g.evaluatePlayerDraw(playerHand), false);
	}
	
	//Testing evaluatePlayerDraw method in class BaccaratGameLogic
	@Test
	void evaluatePlayerDraw2() throws FileNotFoundException{;

		BaccaratGameLogic g = new BaccaratGameLogic();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		playerHand.add(new Card("Spades", 1, 10));
		playerHand.add(new Card("Clubs", 2, 10));
		
		bankerHand.add(new Card("Spades", 8, 10));
		bankerHand.add(new Card("Jack", 1, 10));
		
		assertEquals(g.evaluatePlayerDraw(playerHand), true);
	}

}
