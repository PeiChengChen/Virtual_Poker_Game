/*
 * Pei-Cheng (Peggy) Chen
 * pc2857
 * Game class: 
 * 1)Overloading constructor: testing game or actual game
 * -->using boclean to determine
 * 2)play method: two parts -> testing game or actual game
 * 3)checkHand method: evaluate the player's hand
 * 4)redraw: replacement of cards in the player's hand (max=5)
 * 5)showHand: print out each card in the player's hand in String
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
	
	private Player p;
	private Deck cards;
    
    private double bR;//bankroll
    private double bet;
    private double payout;
    
    private boolean test;//Determine if it is a testing game or not
        
	public Game(String[] testHand){
        
        System.out.println("Please enter your current bankroll.");
        Scanner input1 = new Scanner(System.in);
        bR = input1.nextDouble();
        bet = 0.0;
        p = new Player(bR);
        cards = new Deck();
        payout = 0.0;
        test = true;
        
        for(int t=0; t<5; t++){
            
            int s = 0; 
            int r = Integer.valueOf(testHand[t].substring(1));
            
            if(testHand[t].substring(0,1).equals("c")){
                s = 1;
                p.addCard(new Card(s, r));
            }else if(testHand[t].substring(0,1).equals("d")){
                s = 2; 
                p.addCard(new Card(s, r));
            }else if(testHand[t].substring(0,1).equals("h")){
                s = 3;
                p.addCard(new Card(s, r));
            }else if(testHand[t].substring(0,1).equals("s")){
                s = 4; 
                p.addCard(new Card(s, r));
            }
        }
	}
    //Constructor for testing checkHand method
	
	public Game(){
		
        System.out.println("Please enter your current bankroll.");
        Scanner input1 = new Scanner(System.in);
        bR = input1.nextDouble();
        p = new Player(bR);
        
        bet = 0.0; 
        cards = new Deck();
        payout = 0.0;
        test = false;
  	}
    //Constructor for playing an actual game
	
	public void play(){
        
        boolean again = true;
        // Ask the player if want to continue the game or not
            
        while(again == true){
            
            System.out.println("You may only bet 1-5 tokens each round.");
            System.out.println("Please enter your bet for this round:"); 
            Scanner input2 = new Scanner(System.in);
            bet = input2.nextDouble();
            
            while(bet<1 || bet>5){
                System.out.println("Your bet is invalid. Please re-enter.");
                bet = input2.nextDouble();
            }
            //check if the player's bet is valid or not
            
            p.bets(bet);
            
        	System.out.println("Your current bankroll is: " + p.getBankroll());
                        
        	if(!test){
        	
        		cards.shuffle();
            	for(int f=0; f<5; f++){
                	p.addCard(cards.deal());
           		}

            	Collections.sort(p.getHand());
            
            	System.out.println("This is your current hand:");
            
            	showHand();
        
            	redraw();
            
            	ArrayList<Card> finalHand = p.getHand();
            
            	System.out.println("Your score is " + checkHand(finalHand));
            
           		System.out.println("Your final hand is:");
            
            	showHand();
            
            	//This part of method is for playing an actual game
            	//Print out the initial hand of the player
            	//Ask if the player want any redraw
            	//Evaluate the player's hand: determine payout 
            	//Print out the final hand of the player
        
        	}else{
        		
            	ArrayList<Card> forTest = p.getHand();
                        
            	System.out.println("Your score is " + checkHand(forTest));
                
            	//Evaluate the player's hand: determine payout 
        	}
        	
        	System.out.println("Your payout is " + payout); 
                    
            p.winnings(payout);
        
            System.out.println("Your bankroll is now: " + p.getBankroll());
            
            p.clearHand();
            //change the player's bankroll 
        	//clear the player's hand
        	
        	test = false;
        	//starting from second round
        	//-->all games are regular games 
        	
        	System.out.println("Do you want to continue playing? yes/no");
        	Scanner nextR = new Scanner(System.in);
        	String n = nextR.next();
        	if (!(n.equals("yes"))){
                again = false; 
            }//ask the player if she want to continue playing

        }
    }
	
	public String checkHand(ArrayList<Card> hand){
        
        Collections.sort(p.getHand());
        
        if(royalFlush(hand)){
            payout = 250;
            return "Royal Flush";
        }else if(straightFlush(hand)){
            payout = 50;
            return "Straight Flush";
        }else if(fourOfAKind(hand)){
            payout = 25;
            return "Four of a Kind";
        }else if(fullHouse(hand)){
            payout = 6;
            return "Full House";
        }else if(flush(hand)){
            payout = 5;
            return "Flush";
        }else if(straight(hand)){
            payout = 4;
            return "Straight";
        }else if(threeOfAKind(hand)){
            payout = 3;
            return "Three of a Kind";
        }else if(twoPairs(hand)){
            payout = 2;
            return "Two Pairs";
        }else if(onePair(hand)){
            payout = 1;
            return "One Pair"; 
        }else{
            payout = 0;
            return "No Pair"; 
        }
        		
	}
    //Evaluate the player's hand
    
    private void redraw(){
        
        System.out.println("You may only redraw each card in your hand ONCE.");
        System.out.println("Do you want any redraw? yes/no");
        Scanner answer = new Scanner(System.in);
        String ans = answer.next(); 
                        
        boolean again = true;
        int time = 1;
        
        Card[] replaced = new Card[5];
        for(int a=0; a<5; a++){
            replaced[a]=p.getHand().get(a);
        }
        
        if(ans.equals("yes")){
            
            while(again == true){
                
                System.out.println("Which card do you want to replace? 1-5");
                Scanner index = new Scanner(System.in);
                int i = index.nextInt();
                p.removeCard(replaced[i-1]);
                p.addCard(cards.deal());
                                
                time++;
                
                if(time == 6){
                    break;
                }
                //this statement sets the max # of redrawing to 5
                
                System.out.println("More redraw? yes/no");
                Scanner more = new Scanner(System.in);
                String m = more.next();
               
                if (!(m.equals("yes"))){
                    again = false; 
                }
                //Stop the while loop for redrawing if the player enters no                
            }
        }        
    }
    
    private void showHand(){        
        int m = 1; 
        for(Card s: p.getHand()){
            System.out.println("#" + m + " : "+s+"\n");
            m++;
        }
    }
    //This method prints the player's hand
    
    private int[] forCounting(ArrayList<Card> h) {
        int[] counting = new int[13];
        for(Card card: h) {
            counting[card.r()-1]++;
        }
        return counting;
    }
    //This method is for counting the cards with same ranking in a player's hand
    
    private boolean royalFlush(ArrayList<Card> t){
        if(t.get(4).r() == 13 && (t.get(4).r()-t.get(0).r()) == 12){
            if(straightFlush(t)){
                return true;
            }
        }
        return false; 
    }
    //only if 1st card=1 and 5th card =13
    //also meet the condition for straightflush
    
    private boolean straightFlush(ArrayList<Card> t){
        if(straight(t) && flush(t)){
            return true;
        }
        return false; 
    }
    //meet conditions for both straight and flush
    
    private boolean fourOfAKind(ArrayList<Card> t){
        int[] counting = forCounting(t);
        for(int n: counting){
            if(n==4){
                return true;
            }
        }
        return false; 
    }
    //using forCounting method --> 
    //if a 4 is found
    //--> there are four cards with the same rank in the player's hand
    
    private boolean fullHouse(ArrayList<Card> t){
        if(threeOfAKind(t) && onePair(t)){
            return true;
        }
        return false; 
    }
    //meet conditions for both three of a kind and one pair
    
    private boolean flush(ArrayList<Card> t){
        int count = 0; 
        for(int c=0; c<4; c++){
            if(t.get(c).s() == t.get(c+1).s()){
                    count++;
            }
        }
        if(count == 4){
            return true;
        }
        return false; 
    }
    //if all cards have the same suit
    
    private boolean straight(ArrayList<Card> t){
        int countL = 0;//for high ace case
        int countH = 0;//for low ace case
        
        for(int d =4; d>0; d=d-1){
            if(t.get(d).r() - t.get(d-1).r() == 1){
                countL++;
            }
        }
        //for general straight cases
        //if the difference between each two consecutive cards is always one
        
        if(t.get(4).r() ==13 && t.get(0).r() ==1){
            for(int d =4; d>1; d=d-1){
                if(t.get(d).r() - t.get(d-1).r() == 1){
                    countH++;
                }
            }
        }
        //for a special case of hand that is:10,11,12,13,1
        
        if(countL == 4 || countH == 3){
            return true;
        }
        return false; 
    }
        
    private boolean threeOfAKind(ArrayList<Card> t){
        int[] counting = forCounting(t);
        
        for(int n: counting){
            if(n==3){
                return true;
            }
        }
        return false; 
    }
    //using forCounting method --> 
    //if a 3 is found
    //--> there are three cards with the same rank in the player's hand
    
    private boolean twoPairs(ArrayList<Card> t){
        
        int[] counting = forCounting(t);
        int count = 0;
        for(int n: counting){
            if(n == 2){
                count++;
            }
        }
        if(count ==2){
            return true;
        }
        return false; 
    }
    //using forCounting method --> 
    //if a 2 is found
    //--> there are two cards with the same rank in the player's hand
    //use count to keep track of the number of pair
    //if count =2
    //--> there are two pairs
    
    private boolean onePair(ArrayList<Card> t){
        
        int[] counting = forCounting(t);
        int count = 0; 
        for(int n: counting){
            if(n == 2){
                count++;
            }
        }
        if(count ==1){
            return true;
        }
        return false; 
    }
    //using forCounting method --> 
    //if a 2 is found
    //--> there are two cards with the same rank in the player's hand
    //use count to keep track of the number of pair
    //if count =1
    //--> there is one pair

}