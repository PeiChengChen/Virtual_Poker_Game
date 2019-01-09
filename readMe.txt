Explanation for each class: 

---Card(s, r)
*Sorting the hand of player with interface - Comparable<Card>
*Converting a card to String
*Return the rank and suit of a card

-s is encoded with integer 1-4
-r is encoded with integer 1-13

1) compareTo(): for sorting the hand, using integer 1,-1 to distinguish the order
2) toString(): return the description of rank and suit of the card
3) s(): return the suit of the card -- in integer
4) r(): return the rank of the card



---Deck
*Initialize a deck of card with value (s: 1-4; r: 1-13)
*Shuffling a well-ordared deck of card
*Dealing card to the player

1) shuffle(): using Math.random() to swap the position of card multiple times
2) deal(): using 'top' to keep track of the position of the top card and deal it to the player


---Player
*add, remove a card in a player's hand
*clear, return a player's hand
*return bankroll and adjust bankroll


---Game
*Overloading constructor: testing game or actual game
-->using boclean to determine
*play method: two parts -> testing game or actual game
*checkHand method: evaluate the player's hand
*redraw: replacement of cards in the player's hand (max=5)
*showHand: print out each card in the player's hand in String

1) Game(String[] testHand): Constructor for testing checkHand method
- create a card object with the number converted from the string array in the argument
- put the card object created into the player's hand
- test = true

2) Game(): Constructor for playing an actual game
- test = false

3) play(): 
- Restrict player from entering token that is less than one or more than five (while loop)
- (for playing the actual game): Ask the player to enter their current bankroll and the bet she wish to play in this round. After that, deal five cards to the player, sort the player's hand and show the player the value of each card. Ask the players if she want to redraw any card: ask her to enter the card she wishes to replace (max five redraw). Evaluate the card with checkHand() method, print out the evaluation result and adjust bankroll accordingly. Ask if the player wish to continue playing the game after each round.
- (for testing checkHand method): jump to checkHand() method but still ask player to enter bankroll and bet. Stop the game after first round. 
- "test = false" --> starting from second round, all games become regular games. 

4) checkHand(): the types of  are ordered from the highest payout to lowest payout. Each type of hand has its own method. if statements are used to evaluate the player's hand with the boolean return from each hand's method.

5) redraw(): This method is used to replace the card of the player's hand. The player is asked whether she wants to replace the card or not. if statement is used to evaluate the answer(in string format) from the player. If answer.equals("yes"), the the method proceeds to the redrawing process. The cards in the initial player's hand are first made into an array in order to keep track of the card that has already been removed from the p.getHand() (ArrayList). After that, p.removeCard() is used to remove a card from the player's hand and refill it with p.addCard(cards.deal()). "time" is created to keep track of the number of redraw made by the player. "again" is made to determine whether the player wants to continue redrawing or not. If "again = false" the redrawing process stops; if "time==6", the redrawing process stops.

6) showHand(): this method prints out the player's hand

7) forCounting(ArrayList<Card> h): This method is for counting the cards with same ranking in a player's hand. An 0 array list of length 13 is created. A for loop is created to run through all the cards in the player's hand, and the corresponding position of the ranking of each card in the array increments by 1. 

8) royalFlush(ArrayList<Card> h): true, 
- only if 1st card=1 and 5th card =13
- also meet the condition for straightflush

9) StraightFlush(ArrayList<Card> h): true, 
- if meet conditions for both straight and flush

10)fourOfAKind(ArrayList<Card> t): true, 
- using forCounting method --> 
- if a 4 is found
  --> there are four cards with the same rank in the player's hand

11)fullHouse(ArrayList<Card> t): true, 
- meet conditions for both three of a kind and one pair

12)flush(ArrayList<Card> t): true, 
- if all cards have the same suit
- compare the suit of each 2 consecutive cards, and use count to keep track of the valid comparisons
- if count == 4, the hand is flush

13)straight(ArrayList<Card> t): true, 
-for general straight cases
 --> if the difference between each two consecutive cards is always one
-for a special case of hand that is:10,11,12,13,1
 --> the difference between the first and the last card has to be 12 and from 2nd to 5th card, the difference between each two consecutive cards is always one

14)threeOfAKind(ArrayList<Card> t): true,
- using forCounting method --> if a 3 is found
  --> there are four cards with the same rank in the player's hand

15)twoPairs(ArrayList<Card> t): true,
- using forCounting method --> 
- if a 2 is found --> there are two cards with the same rank in the player's hand
- use count to keep track of the number of pair
- if count =2 --> there are two pairs

16)onePair(ArrayList<Card> t): true,
- using forCounting method --> 
- if a 2 is found --> there are two cards with the same rank in the player's hand
- use count to keep track of the number of pair
- if count =1 --> there is one pair





