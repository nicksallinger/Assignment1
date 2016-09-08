import java.io.*;
import java.util.Scanner;

public class Player{
	//I set these to public so i could call them in the main using there object.variable 
	//I wasnt sure if I was allowed to pass the users information back and forth from the main to the
	//methods in the player object, so i just kept everything here, rather than passing variables 
	//from main to object and return updated value
	//
	
	
	public Scanner sc = new Scanner(System.in);
	public float money;
	public float bet;
	public String name = null, ignore = null, handsPlayedString = null, handsWonString = null, moneyString = null;
	public int handsPlayed = 0, handsWon = 0;
	
	
	public Player(){ //constructor for player
		
		}
	
	public void getInfo() throws IOException{
		//this gets the persons name, then goes to the text file and gets the info 
		
		
		System.out.println("Please enter your name >");
		name = sc.next();
		System.out.println("Welcome " + name);
		File filename = new File(name + ".txt");
		
		if(!filename.exists()) { //this creates new text file if the person is new player
			System.out.println("Have fun on your first time playing Infinite Blackjack.");
		    filename.createNewFile();
		    PrintWriter writer = new PrintWriter(name + ".txt");
			writer.println(100.00);
			writer.println(0);
			writer.println(0);
			writer.close();
		} 
		
		//the following gets the info out as strings, then changes them to ints or floats
		Scanner filesc = new Scanner(filename);
		
		moneyString = filesc.nextLine();
		
		money = Float.parseFloat(moneyString);
		handsPlayedString = filesc.nextLine();
		
		
		handsPlayed = Integer.parseInt(handsPlayedString);
		
		handsWonString = filesc.nextLine();
		handsWon = Integer.parseInt(handsWonString);
		

	
		
	} //for getInfo
	
	public void checkBet(){
		boolean loop = true;
		
		while (loop){ //this checks the input to make sure its a float
			try{
				System.out.println("How much would you like to bet? > ");
				bet = sc.nextFloat();
				loop = false;
			}
			catch (Exception e) {
	          
	           sc.next();
	        }
		}
		while (bet > money || bet < 0){
			System.out.println("Please enter a valid bet >");
			bet = sc.nextInt();
		}
		
	}
	
	
	public void lostHand() throws FileNotFoundException{
		//this now updates hands played and money, as well as rewrites the info to the users text file
		money = money - bet;
		handsPlayed++;
		System.out.printf("You lost\t\t$%.2f\n ",bet);
		
		PrintWriter writer = new PrintWriter(name + ".txt");
		writer.println(money);
		writer.println(handsPlayed);
		writer.println(handsWon);
		
		writer.close();
		
		
		
	}
	public void wonHand() throws FileNotFoundException{
		//updates the info for a win, and rewrites them to the users text file
		money = money + bet;
		handsPlayed++;
		handsWon++;
		System.out.printf("You won\t\t$%.2f\n",bet);
		
		PrintWriter writer = new PrintWriter(name + ".txt");
		writer.println(money);
		writer.println(handsPlayed);
		writer.println(handsWon);
		
		writer.close();
		
		
	}
	
	public void blackJack(){
		//this is for a blackjack only. kind of unnecessary, but this is just how i did it
		money = (money + bet + (bet/2));
		System.out.printf("You won\t\t%.2f", (bet+bet*.5));
		
	}
	
	public void showInfo(){
		//this shows the persons info
		
		System.out.println("Name:\t\t\t " + name);
		
		System.out.println("Total Hands:\t\t   " + handsPlayed);
		System.out.println("Hands Won:\t\t   " + handsWon);
		System.out.printf("Money:\t\t\t%.02f\n", money);
	}
	
	
	
}