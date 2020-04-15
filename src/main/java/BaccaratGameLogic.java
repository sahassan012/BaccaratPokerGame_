import java.util.ArrayList;

//
//GameLogic
public class BaccaratGameLogic {
	
	// Determines which hand won
	// 	returns a string
	public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2)
	{
		String player = "Player";
		String banker = "Banker";
		String tie = "Tie";
		
		//Determine whether the player won, the banker won, or if there is a tie
		if (handTotal(hand1) == 8 || handTotal(hand1) == 9 || handTotal(hand2) == 8 || handTotal(hand2) == 9) {
			if (handTotal(hand1) > handTotal(hand2)) {
				return player;
			}
			else if (handTotal(hand1) < handTotal(hand2)) {
				return banker;
			}
			else {
				return tie;
			}
		}
		else if (handTotal(hand1) > handTotal(hand2)) {
			return player;
		}
		else if (handTotal(hand1) < handTotal(hand2)) {
			return banker;
		}
		else {
			return tie;
		}
	}
	
	//handTotal
	//   Takes an array of of cards and returns the total
	public int handTotal(ArrayList<Card> hand)
	{
		int total = 0;
		
		//Iterate through array of hands and sum up all values
		for(Card c : hand) {
			if (c.value == 10)
			{
				total = total + 0;
			}
			else if (c.suite == "Ace") {
				total += 1;
			}
			else {
				total += c.value;
			}
		}
		//If sum is over 9, subtract 10
		if (total > 9)
		{
			return total % 10;
		}
		return total;
	}
	
	//evaluateBankerDraw
	//	Takes an array of cards and a playerCard to determine if there is a special case of a draw
	public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
	{	
		//If the hand is between 0-2 (inclusive) and the player's card hasn't been draw
		if (handTotal(hand) >= 0 && handTotal(hand) <= 2 && playerCard == null) {
			return true;
		}
		
		//Banker cannot draw if the total is 7 or above
		if (handTotal(hand) > 7) {
			return false;
		}
		//Banker can draw if the total is between 0-2
		else if (handTotal(hand) >= 0 && handTotal(hand) <= 2) {
			return true;
		}
		//Banker can draw if the total is between  3-6 
		else if (handTotal(hand) >= 3 && handTotal(hand) <= 6 && playerCard != null)
		{
			//If the banker's total is 3
			if (handTotal(hand) == 3) 
			{   
				//If the player's card is between 0-10 and not 8, or a Jack, Queen, King, or a 10, return true
				if ( (playerCard.value < 10 && playerCard.value != 8) || (playerCard.suite == "Jack") 
						|| (playerCard.suite == "Queen")
						|| (playerCard.suite == "King") 
						|| (playerCard.value == 10) )
				{
					return true;
				}
				else {
					return false;
				}
			}
			//If the banker's total is 4
			else if (handTotal(hand) == 4)
			{
				//If the player's card is between 2-7
				if ((playerCard.value < 8 && playerCard.value > 1) )
				{
					return true;
				}
				else{
					return false;
				}
			}
			//If the banker's total is 5
			else if (handTotal(hand) == 5)
			{	
				//If the player's card is betwen 4-7
				if (playerCard.value > 3 && playerCard.value < 8)
				{
					return true;
				}
				else {
					return false;
				}
			}
			//If the banker's total is 6
			else if (handTotal(hand) == 6)
			{
				//If the player's card is 6 or 7
				if (playerCard.value == 6 || playerCard.value == 7) 
				{
					return true;
				}
				else 
				{
					return false;
				}
			}
		}
		return false;
	}
	
	//evaluatePlayerDraw
	//	Takes an arraw of cards and returns TRUE/FALSE depending on if the player should draw
	public boolean evaluatePlayerDraw(ArrayList<Card> hand)
	{
		//Draw a card if the total is 5 or less
		if (handTotal(hand) <= 5 && handTotal(hand) >= 0) {
			return true;
		}
		//Can't  draw card if it is 6, 7, 8 or 9
		else {
			return false;
		}
	}
}
