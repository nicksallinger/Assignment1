import java.util.Random;

public class Cards{
	
	//I again kept all the variables in this class, rather than send them from object to main and 
	//back to update
	//I used string arrays with the possible cards and suits then used random numbers to choose the
	//variable inside them
	
	public int total;
	public int value;
	public String cards[] = {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
	public String suits[] = {"s","c","d","h"};
	public int value1, value2, newValue;
	Random rng = new Random();
	public int finalValue;
	public String finalSuit;
	
	public Cards(){ //Cards constructor
		//I do realized that this could contain the card, but i didnt do that
		//instead I made a first hand method that picks 2 hands
	}
	
	
	
	
	public void firstHand(){
		//this is the first hand, picks 2 cards and calculates the values, then prints them back to main
		int cardChooser1 = rng.nextInt(cards.length);
		int cardChooser2 = rng.nextInt(cards.length);
		String card1 = cards[cardChooser1];
		String suit1 = suits[rng.nextInt(suits.length)];
		String card2 = cards[cardChooser2];
		String suit2 = suits[rng.nextInt(suits.length)];
		
		switch (cardChooser1){ //this determines the value of the cards based on the position in the array
			case 0:
				
				value1 = 11;
				if (value > 21){
					value1 = 1;
				}
				break;
			case 1:
				value1 = 2;
				break;
			case 2:
				value1 = 3;
				break;
			case 3:
				value1 = 4;
				break;
			case 4:
				value1 = 5;
				break;
			case 5:
				value1 = 6;
				break;
			case 6:
				value1 = 7;
				break;
			case 7:
				value1 = 8;
				break;
			case 8:
				value1 = 9;
				break;
			case 9:
				value1 = 10;
				break;
			case 10:
				value1 = 10;
				break;
			case 11:
				value1 = 10;
				break;
			case 12:
				value1 = 10;
				break;
			
		}
		switch (cardChooser2){ //second card
		case 0:
			
			value2 = 11;
			if (value1 + value2 > 21){
				value2 = 1;
			}
			break;
		case 1:
			value2 = 2;
			break;
		case 2:
			value2 = 3;
			break;
		case 3:
			value2 = 4;
			break;
		case 4:
			value2 = 5;
			break;
		case 5:
			value2 = 6;
			break;
		case 6:
			value2 = 7;
			break;
		case 7:
			value2 = 8;
			break;
		case 8:
			value2 = 9;
			break;
		case 9:
			value2 = 10;
			break;
		case 10:
			value2 = 10;
			break;
		case 11:
			value2 = 10;
			break;
		case 12:
			value2 = 10;
			break;
		
	}
		
		value = value1 + value2; //this adds the cards together for the players actual total
		System.out.println("Cards Dealt : " + card1 + suit1 + " " + card2 + suit2); //prints the cards to main
		System.out.println("Score: " + value );
		if (value == 21){
			System.out.println("Blackjack!");
			}
		total = value;
		
		
	}
	
	
	
	
	public void newCard(){ //this is used when the player chooses hit, or when the dealer has under 18
		Random rng = new Random();
		//this is essentially the same thing in the firstHand method, but with only 1 card
		int cardChooser = rng.nextInt(cards.length);
		String card = cards[cardChooser];
		String suit = suits[rng.nextInt(suits.length)];
		
		switch (cardChooser){
			case 0:
				
				newValue = 11;
				if (newValue + value > 21){
					newValue = 1;
				}
				break;
			case 1:
				newValue = 2;
				break;
			case 2:
				newValue = 3;
				break;
			case 3:
				newValue = 4;
				break;
			case 4:
				newValue = 5;
				break;
			case 5:
				newValue = 6;
				break;
			case 6:
				newValue = 7;
				break;
			case 7:
				newValue = 8;
				break;
			case 8:
				newValue = 9;
				break;
			case 9:
				newValue = 10;
				break;
			case 10:
				newValue = 10;
				break;
			case 11:
				newValue = 10;
				break;
			case 12:
				newValue = 10;
				break;
			
		}
		//this sums the first hand and the new card
		value = value + newValue;
		total = value;
		
		System.out.println(card + suit);
		System.out.println("Score: " + (total));
		
		
		
		
		
		
		//return value; for the card, also need to take into accound ace = 11 or 1, may need to bring in variable
		
	}
	
}
	
