/*
 * Pei-Cheng (Peggy) Chen
 * pc2857
 * Card class:
 * 1)Sorting the hand of player with interface - Comparable<Card>
 * 2)Converting a card to String
 * 3)Return the rank and suit of a card
*/

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
        
        suit = s;
        rank = r;
        
	}
	
	public int compareTo(Card c){
		
        int answer = 0;
        
        if(this.rank>c.rank){
            answer = 1;
        }else if(this.rank<c.rank){
            answer = -1;            
        }else if(this.rank == c.rank){
            
            if(this.suit>c.suit){
                answer = 1;
            }else{
                answer = -1;                
            }
        }
        
        return answer;
       
	}
    //This method is for sorting the hand
	
	public String toString(){
        
        String suitR = "";
        String rankR = "";
        String description; 
        
        if(suit == 1){
            suitR = "clubs";
        }else if(suit == 2){
            suitR = "daimond";
        }else if(suit == 3){
            suitR = "heart";
        }else if(suit == 4){
            suitR = "spade";
        }
        
        if(rank == 1){
            rankR = "ace";
        }else{
            rankR = String.valueOf(rank);
        }
        
        description = rankR + " of " + suitR;
        
        return description;
                        
	}
    //This method is for returning the card in String
    
    public int s(){
        return suit;
    }
    //This method is for returning the suit of a card
    //1 = club
    //2 = diamond
    //3 = heart
    //4 = spade
    
    public int r(){
        return rank;
    }
    //This method is for returning the rank of a card
    
}
