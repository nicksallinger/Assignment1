import java.io.*;
import java.util.Scanner;



public class Blackjack{
	
	public static void main(String args[]) throws IOException{ 
		//main where the objects are called and comparisons are made to see who wins

		
		
		Scanner sc = new Scanner(System.in);//declare scanner
		Player player = new Player(); //player object
		Cards dealerCards = new Cards();
		Cards playerCards = new Cards(); //Cards object
		boolean done = false;
		String playAgain = "y";  //sets if to keep playing or not
		String hitOrStay = null;
		
		System.out.println("Welcome to Infinite Blackjack!");
		player.getInfo(); //send name to getName
		player.showInfo();//this prints out the players info
		
		System.out.println("Would you like to play a hand? (Y/N)");
		playAgain = sc.next();
		
		while (!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n")){ //checks input
			System.out.println("Play a hand? (Y/N) >> ");
			playAgain = sc.next();
		}
		while (playAgain.equalsIgnoreCase("y")){
			
			
			player.checkBet();
			
		
				
		System.out.println("PLAYER DEAL");
		playerCards.firstHand(); //calls the users first 2 card hand
		
			if (playerCards.value == 21){
				done = true;
				player.blackJack(); //stops everything because of blackjack
			}
	
		if (done == false){
			//keeps going if no blackjack and if under 21
			
		System.out.println("[H]it or [S]tay?");
		hitOrStay = sc.next();
		
		}
		
		while ((!hitOrStay.equalsIgnoreCase("s") && !hitOrStay.equalsIgnoreCase("stay")) && done == false){
		
		if ((hitOrStay.equalsIgnoreCase("hit") || hitOrStay.equalsIgnoreCase("h")) && done == false){
			System.out.println("Hit!");
			playerCards.newCard(); //calls 1 new card for the user and updates total
			
			if (playerCards.total > 21){ //checks for the user busting
				System.out.println("Bust, you lose!");
				done = true;
				player.lostHand(); //updates player info for losing
				hitOrStay = "s";
			}
			
			if (done != true){
			System.out.println("[H]it or [S]tay?");
			hitOrStay = sc.next();
			}
			
			
			
		}//hit if
		}
		
		
		
		
		if ((hitOrStay.equalsIgnoreCase("s") || hitOrStay.equalsIgnoreCase("stay")) && done == false ){
			//this starts after user busts or stays
			System.out.println("Stay!");
			System.out.println("DEALER DEAL");
			dealerCards.firstHand(); //dealers first 2 card hand
			
			if (dealerCards.value == 21){
				System.out.println("DEALER has blackjack, you lose!");
				player.lostHand();
				done = true;
				}
	
			
			while (dealerCards.value < 18 || dealerCards.total < 18){
				dealerCards.newCard();
				//the dealer hits on 17
			}
			
			if (dealerCards.total > 21){ //checks for dealer bust, updates players money and hands if true
				System.out.println("Dealer Busts!");
				player.wonHand();
				done = true;
				
				
			}
		
		}
		
		if (done != true){ //this is used if neither player or dealer has blackjack and has not busted
			
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
		
			player.showInfo(); //prints new player info after update from last hand
		
			System.out.println("Would you like to play a hand? [Y/N]"); //determines if loop runs again
			playAgain = sc.next();
		}
		sc.close();
	}
			
}
