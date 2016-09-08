import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

public class Voting{
	
	//Making global variables
	
	static int i = 0;
	static boolean auth = false;
	static int numBallots;
	static int voterIndex = -1;
	static String[][] voters;
	
	
public static void main(String args[]) throws FileNotFoundException{
		
	String filename = args[0];
	
		//Making the various scanners and gui components
		File filenameBallots = new File(filename);
		Scanner filesc = new Scanner(filenameBallots);
		File filenameVoters = new File("voters.txt");
		Scanner filesc2 = new Scanner(filenameVoters);
		Scanner filesc3 = new Scanner(filenameVoters);
		Scanner sc = new Scanner(System.in);
		filesc.useDelimiter(":");
		filesc3.useDelimiter(":");
		ArrayList<Ballot> ballotList = new ArrayList<Ballot>();
		JFrame frame = new JFrame("Voting");
		String[] ballotChoices = null;
		String[] ballotArr = null;
		int[] votesTotal = null;

		
		
while (filesc.hasNextLine()){  //while loop to read in all the text information
	int panelIndex = 0;
	
			if (!filesc.hasNextLine()){ //stops reading at end of file
				break;
			}
			else{
			numBallots = Integer.parseInt(filesc.nextLine()); //number of ballots in txt file
				
				frame.setLayout(new GridLayout(1, numBallots+2)); //make a frame with the layout i think is appropriate
					
				for (int i = 0; i < numBallots;i++){ //this sends the info to ballot class for the number of ballots
													//then adds to array list
				
				String ballot = filesc.nextLine(); //the entire line

				ballotArr = ballot.split(":|\\n"); //makes ballot into arr 
												//ballotarr 0 = id, 1 = name, 2 = choices
			
				ballotChoices = new String[ballotArr[2].length()];
				
				for (int k = 0; k < ballotArr[2].length(); k++){
					//making an array of just choices from the ballot
					ballotChoices = ballotArr[2].split(",");
					
				}
				
				
				//ARRAY TO SAVE NUMBER OF VOTES FOR EACH
				//I made an array to save votes for each candidate
				votesTotal = new int[ballotChoices.length];
				for( int p = 0; p < votesTotal.length; p++){
					votesTotal[p] = 0;
					
				}
				
				Ballot B = new Ballot(numBallots,ballotArr,ballotChoices,votesTotal,frame); //sends all the info to Q constructor
				
				ballotList.add(B); // adds the info to the array list 
				panelIndex++;
				}
			}	
	}


	 	while (filesc2.hasNextLine()){  //while loop to read in all the text information
	 		String tempVoter = filesc2.nextLine();
	 		
			i++; //This is the index of the voter, and i use it alot to determine what parts of arrays 
				//to use
		
		}

		String[] tempVoter = new String[i];
		voters = new String[i][3];
		//string 2x2
		//each voter in a row
		//id in col 1
		//name in col 2
		//boolean in col 3
		
		boolean[] voterBool = new boolean[i];
		//array for booleans of the users from text
		
		while (filesc3.hasNextLine()){
	
				if (!filesc3.hasNextLine()){
					break;
				}
				else{
					for (int j = 0; j < i; j ++){ //til j is less than 5 *******
						
						//need 1d array with each voter in own col
						
						tempVoter[j] = filesc3.nextLine();
						
					}//end of for loop to make 3d array of voter lines *******
					
					for (int k = 0;k<i;k++){ //parses temp array into 2d array, 0 is ID, 1 is name, 2 is boolean (HOW TO CONVERT TO BOOLEAN?)
						for ( int x = 0; x < 3;x++){ 
							voters[k] = tempVoter[k].split(":");
							//similar to ballot array that I split by :
						}
						
					
						
						
					}
					for (int x = 0; x < i ; x++){
						voterBool[x] = Boolean.parseBoolean(voters[x][2]);
						//array with only voters boolean
					}
				}
			}
		//GUI stuff
		JPanel voting = new JPanel();
		JPanel loginPan = new JPanel();
		JButton login = new JButton("Login");
		JButton vote = new JButton("Vote");
		vote.setEnabled(false);
				
//*************** VOTE BUTTON		
		
		vote.addActionListener(new ActionListener() 
		
		{
			public void actionPerformed(ActionEvent e){	
			int confirmVote = JOptionPane.showConfirmDialog(null, "Confirm Vote");
			//System.out.println("CONFIRM VOTE: " + confirmVote);
			//0 = yes // 1 = no // 2 = cancel
			
			if (confirmVote == 0){
				
				for( int q = 0; q < numBallots; q++){
					
					ballotList.get(q).getVotesTotal()[ballotList.get(q).votersVote]++;
					//adds 1 to what the voter chose
		
				}
				
				
				for(int l = 0; l < numBallots; l++){
				
				voterBool[voterIndex] = true; //now they cant vote again
				
				//PRINTWRITER STUFF
				
				//now this makes a file based off the ballot ID
				File write = new File(ballotList.get(l).getBallotArr()[0] + ".txt");
		 try {
					PrintWriter writer = new PrintWriter(write);
					for(int n = 0 ; n < ballotList.get(l).getBallotChoices().length ; n++){
						writer.println(ballotList.get(l).getBallotChoices()[n] + ":" + ballotList.get(l).getVotesTotal()[n]);
					}
					writer.close();
	             }
				catch (FileNotFoundException e1) {
					//do nothing
				}
	             
				}
				
				for(Ballot B : ballotList){
					for(JToggleButton btn : B.JToggleButtons){
						//iterates through everything and sets them disabled and unselected
						btn.setSelected(false);
						btn.setEnabled(false);
						vote.setEnabled(false);
						
						
					}
				}
				
			//UPDATE VOTER FILES
			try {
				File votersTemp = new File("temptempvoters.txt");
				PrintWriter voterWrite = new PrintWriter(votersTemp);
				
				for(int 
						k = 0; k < i ;k++){
					
					//this is in format : voterID:VoterName:voterBool
					voterWrite.println(voters[k][0]+":"+voters[k][1]+":"+voterBool[k]);
					
				}
				
			
				
				//Files.copy(votersTemp.toPath(), temptemp.toPath(), StandardCopyOption.REPLACE_EXISTING );	
				// Files.delete(votersTemp.toPath());
				
				//I could not get the files.copy to work,
				//it correctly updates the votersTemp file, but does not copy the stuff to the
				//original voters file, it just erases everything in it
				
				
				
				voterWrite.close();
				
			} catch (IOException e1) {
				//do nothing
			}
			
				
				
			}
			
			//***END OF CONFIRM = 0 if LOOP
			
	
				}
		});
		
		
///**************** LOGIN BUTTON
		
		
		login.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent l){
				String voterID = JOptionPane.showInputDialog("Enter your Voter ID:");
				//user enters voter ID
				
				for (int x = 0; x < i; x++){
					
				if(voterID.equals(voters[x][0])){
					voterIndex = x;
					//gets a the voters index
					
				}
				else{

				}
				
				
				}
				
		
			if (voterIndex != -1){
				if (voterBool[voterIndex] == false){
					//if the voters boolean isnt true, then the program lets them in
					//and comtinues
					
					JOptionPane.showConfirmDialog(null,
			                "Hello, " + voters[voterIndex][1] + ". Please Make your choices.",
			                "Welcome",
			                JOptionPane.DEFAULT_OPTION,
			                JOptionPane.PLAIN_MESSAGE);
					
					for(Ballot B : ballotList){
						for(JToggleButton btn : B.JToggleButtons){
							//Now allowing the user to vote
							btn.setEnabled(true);
							vote.setEnabled(true);
							
						}
					}
					
				
					
				}
			
				else if (voterBool[voterIndex] == true){
					//occurs if user has a boolean that is true, and now they cant vote
					JOptionPane.showConfirmDialog(null,
			                "Hello, " + voters[voterIndex][1] + ". You already voted",
			                "Welcome",
			                JOptionPane.DEFAULT_OPTION,
			                JOptionPane.PLAIN_MESSAGE);
					
						
				}
			}
				else{
					//Displayed if userID isnt in the file
					JOptionPane.showConfirmDialog(null,
			                "Voter Not Recognized",
			                "Invalid",
			                JOptionPane.DEFAULT_OPTION,
			                JOptionPane.PLAIN_MESSAGE);
				}
			}
				});
				
		loginPan.add(login);
		voting.add(vote);
		frame.add(voting);
		frame.add(loginPan);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);


	filesc.close();
	filesc2.close();
	filesc3.close();
	sc.close();
	}
}