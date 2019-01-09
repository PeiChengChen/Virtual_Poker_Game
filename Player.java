/*
 * Pei-Cheng (Peggy) Chen
 * pc2857
 * Player class:
 * 1)add, remove a card in a player's hand
 * 2)clear, return a player's hand
 * 3)return bankroll and adjust bankroll
*/

import java.util.ArrayList;

public class Player {
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
    
	public Player(double b){
        hand = new ArrayList<Card>();
	    bankroll = b;
        bet = 0.0;
	}

	public void addCard(Card c){
        hand.add(c);
	}
    // add the card c to the player's hand
    
	public void removeCard(Card c){
        hand.remove(c);
    }
    //remove the card c from the player's hand
	
    public ArrayList<Card> getHand(){
        return hand;
    }
    //return the player's hand 
    
    public void clearHand(){
        hand.clear();
    }
    //clear the player's hand
    
    public void bets(double amt){
        bet = amt;
        bankroll = bankroll - amt; 
    }
    //Player makes a bet and adjust bankroll

    public void winnings(double odds){
        bankroll = bankroll + bet*odds;
    }
    //adjust bankroll after the hand is evaluated

    public double getBankroll(){
        return bankroll; 
    }
    // return current balance of bankroll
    
}