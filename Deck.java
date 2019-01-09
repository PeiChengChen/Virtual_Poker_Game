/*
 * Pei-Cheng (Peggy) Chen
 * pc2857
 * Deck class 
 * 1)Initialize a deck of card with value
 * 2)Shuffling a well-ordared deck of card
 * 3)Dealing card to the player
*/

import java.util.ArrayList;

public class Deck {
	
	private Card[] cards;
	private int top; 
	
	public Deck(){
		
        cards = new Card[52];
        //intiAlize an empty deck of card
        
        int counter = 0;
        while(counter < 52){
            for(int s=1; s<=4; s++){
                for(int r=1; r<=13; r++){
                    cards[counter] = new Card(s,r);
                    counter++;
                }
            }
        }
        //This while loop fills suit and rank into the empty deck of card
        
        top = 0;
	}
	
	public void shuffle(){
        for(int t=0; t<1000000; t++){
            for(int d=0; d<52; d++){
                Card temp; 
                int pos = (int)(Math.random()*51);
                temp = cards[d];
                cards[d] = cards[pos];
                cards[pos] = temp;
            }
        }
    }
    //swapping card with random positions multiple times 
	
	public Card deal(){
        
        Card deal = cards[top];
        top++;
        
        if(top>51){
            shuffle();
            top = 0; 
        }
        //reset the position of top card if the last card is dealt
        
        return deal; 
        
	}
    //This method deals the top card of a deck to the player
}
