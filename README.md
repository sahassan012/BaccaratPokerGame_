#  ->♣ ♤ ♠ Baccarat ♠ ♤ ♣<-
## About
This program is an implementation of a one player version of the casino game Baccarat. 
It is developed as a Maven project in JAVA using JavaFX

## How it works:
#### Rules 
- The user first bids some amount of money on either the Banker to win or the Player to win or it will be a Draw.
- The dealer then deals two cards each to the Player and the Banker. 
- The Player goes first. 
- For the cards, 10's and face cards are worth zero points.
- Ace's are worth one point and all other cards are worth their face value.
- If either the Banker's hand or the Player's hand add up to 8 or 9, it's a natural win and the game is over. 
- The winning hand is the one with a total of 9 or as close to 9 as possible.

Resource for more detailed rules: https://www.fgbradleys.com/rules/rules4/Baccarat%20-%20rules.pdf

#### Player
If the player's hand totals to 5 or less, the Player gets dealt another card. If the hand totals to 6 or 7 points, no more cards are dealt. 

#### Banker
If the banker's hand totals to 7 or more, no more cards are dealt. If the hand is 2 or less, banker gets one new card.

#### Counting
In terms of counting, if the total value of the two cards is > 9, subtract by 10. 
For example, 8 + 5 = 13. 13 - 10 = 3, so the overall value of the hand would be 3.
