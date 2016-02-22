import java.io.*;
import java.util.Scanner;



//MAKE GAMEOVER METHOD, DO IF FOR IF WON/LOST THEN WRITE TO FILE

public class Blackjack{
	
	public static void main(String args[]) throws IOException{

		
		
		Scanner sc = new Scanner(System.in);//declare scanner
		Player player = new Player(); //player object
		Cards dealerCards = new Cards();
		Cards playerCards = new Cards(); //Cards object
		boolean done = false;
		String playAgain = "y";
		String hitOrStay = null;
		float bet = 0;
		boolean loop = true;
		
		System.out.println("Welcome to Infinite Blackjack!");
		player.getInfo(); //send name to getName
		player.showInfo();
		
		System.out.println("Would you like to play a hand? (Y/N)");
		playAgain = sc.next();
		/*while (loop){
			try{
				System.out.println("Play a hand? (Y/N) > ");
				playAgain = sc.next();
				loop = false;
			}
			catch (Exception e) {
	          
	           sc.next();
	        }
		}*/
		
		while (!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n")){
			System.out.println("Play a hand? (Y/N) >> ");
			playAgain = sc.next();
		}
		while (playAgain.equalsIgnoreCase("y")){
			
			
			player.checkBet();
			
		
				
		System.out.println("PLAYER DEAL");
		playerCards.firstHand();
		
			if (playerCards.value == 21){
				done = true;
			}
	
		if (done == false){
		System.out.println("[H]it or [S]tay?");
		hitOrStay = sc.next();
		}
		
		while ((!hitOrStay.equalsIgnoreCase("s") && !hitOrStay.equalsIgnoreCase("stay")) && done == false){
		
		if ((hitOrStay.equalsIgnoreCase("hit") || hitOrStay.equalsIgnoreCase("h")) && done == false){
			System.out.println("Hit!");
			playerCards.newCard();
			
			if (playerCards.total > 21){
				System.out.println("Bust, you lose!");
				done = true;
				player.lostHand();
				hitOrStay = "s";
			}
			
			if (done != true){
			System.out.println("[H]it or [S]tay?");
			hitOrStay = sc.next();
			}
			
			
			
		}//hit if
		}
		
		
		
		
		if ((hitOrStay.equalsIgnoreCase("s") || hitOrStay.equalsIgnoreCase("stay")) && done == false ){
			System.out.println("Stay!");
			System.out.println("DEALER DEAL");
			dealerCards.firstHand();
			
			if (dealerCards.value == 21){
				System.out.println("DEALER has blackjack, you lose!");
				player.lostHand();
				done = true;
				}
		
			while (dealerCards.value < 18 || dealerCards.total < 18){
				dealerCards.newCard();
			}
			
			if (dealerCards.total > 21){
				System.out.println("Dealer Busts!");
				player.wonHand();
				done = true;
				
			}
		
		}
		
		if (done != true){
			
			if (playerCards.total > dealerCards.total){
				System.out.println("You Win!");
				player.wonHand();
			}
			if (playerCards.total < dealerCards.total){
				System.out.println("You lose!");
				player.lostHand();
			}
			if (playerCards.total == dealerCards.total){
				System.out.println("Push!");
			}
			
		}
		
			player.showInfo();
		
			System.out.println("Would you like to play a hand? [Y/N]");
			playAgain = sc.next();
		}
		sc.close();
	}
			
}
