import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz{
	public static void main(String args[]) throws FileNotFoundException{
		
		File filename = new File("questions.txt"); //user has to enter text file while compiling with command line
								//File(args[0])
		Scanner sc = new Scanner(System.in);  //makes a scanner
		int right = 0;  //these keep track of how many right and wrong the user has on this instance of the test
		int wrong = 0;
		
		Scanner filesc = new Scanner(filename);
		ArrayList<Question> list = new ArrayList<Question>(); //array list of type Question
		
		while (filesc.hasNextLine()){  //while loop to read in all the text information
			
			if (!filesc.hasNextLine()){ //stops reading at end of file
				break;
			}
			else{
				
			String question = filesc.nextLine();   //this is the actual question
			int numChoices = Integer.parseInt(filesc.nextLine());  //the number of choices
			String[] choices = new String[numChoices];  //creates a string with length of the number of choices the user has
			
			for (int i = 0; i < numChoices; i++){  //this then fills that string with the String choices
				choices[i] = filesc.nextLine();				
				}
			
			int answer = Integer.parseInt(filesc.nextLine());  //the number of the correct answer
			int attempts = Integer.parseInt(filesc.nextLine()); 
			int rightAttempts = Integer.parseInt(filesc.nextLine());
			Question Q = new Question(question,numChoices,choices,answer,attempts,rightAttempts); //sends all the info to Q constructor
			
			list.add(Q); // adds the info to the array list 
			
			}
			
			
			
		}
		
		
	int[] answerInput = new int[list.size()]; //I use this array to store the users inputs in then i can recall them easily
	
	
	
	for (int i = 0; i < list.size();i++){ //this iterates through all the questions, then displays the possible answers
		System.out.println("Question " + (i+1) + " : ");
			System.out.println(list.get(i).getQuestion());
			System.out.println("Answers : ");
			for(int j = 0; j < list.get(i).getNumChoices(); j++){
				System.out.println(j + ". " + list.get(i).getAnswers()[j]);
			}
			
			System.out.println("Your answer? (Enter a number) : "); //scanner to get users answer
			answerInput[i] = sc.nextInt();
			while (answerInput[i]+1 > list.get(i).getNumChoices() || answerInput[i] < 0){ //makes sure users answer is acceptable
				
				System.out.println("Your answer? (Enter a number) : ");
				
				answerInput[i] = sc.nextInt();
			}
			if ( list.get(i).getAnswer() == answerInput[i]){ //for when user answer is right
				right++;
			}
			else{ //users answer is incorrect
				wrong++;
			}
			
		}
	System.out.println("Quiz Complete, thanks for your answers\n\n");
	
	System.out.println("\nYour overall performance was: \n");
	System.out.println("Right : " + right);
	System.out.println("Wrong : " + wrong + "\n");
	
	
	for (int i = 0 ; i < list.size() ; i++){ // this loop shows the question, right answer, and then the users answer
		System.out.println("Question : " + list.get(i).getQuestion());
		System.out.println("Answer : " + list.get(i).getrightAnswer());
		System.out.println("Player guess : " + list.get(i).getAnswers()[answerInput[i]]);
		System.out.print("\tResult : ");
		if ( list.get(i).getAnswer() == answerInput[i]){ //for when user answer is right
			System.out.print("CORRECT! Great Work\n\n");
			right++;
			list.get(i).increaseRightAttempts(); //updates 
			
		}
		else{ //users answer is incorrect
			System.out.print("Incorrect!\n\n");
			wrong++;
		}
		list.get(i).increaseAttempts(); //updates
	}
	double easiest = 0; //I used these indexes to find the easiest and the hardest questions
	int easiestIndex = 0; // the highest percentage of right is easiest
	double hardest = 100; 
	int hardestIndex = 0; //the index is set by i, so I can recall the questions later
	
	System.out.println("Here are some statistics : ");
	for (int i = 0 ; i < list.size(); i++ ){
		System.out.println("Question : " + list.get(i).getQuestion()); 
		System.out.println("\tTimes tried : " + list.get(i).getAttempts());
		double bottom = (double) list.get(i).getAttempts();
		double top = (double) list.get(i).getrightAttempts();
		
		double percent = (top/bottom)*100; //make a double of percentage, and use the next 2 if loops to decide which is easiest and hardest
		if ( percent > easiest){
			easiest = percent;
			easiestIndex = i;
		}
		
		if (percent < hardest){
			hardest = percent;
			hardestIndex = i;
		}
		
		System.out.println("\tTimes corrects : " + list.get(i).getrightAttempts());
		System.out.printf("\tPercent correct : %.2f%% \n", percent);
		
	}
	
	System.out.println("\nEasiest Question: "); //this uses the previous indexes to show the hardest and easiests questions
	System.out.println(list.get(easiestIndex).getQuestion());
	System.out.println("\tTimes tried : " + list.get(easiestIndex).getAttempts());
	System.out.println("\tTimes correct : " + list.get(easiestIndex).getrightAttempts());
	System.out.printf("\tPercent correct : %.2f%% \n", easiest);
	System.out.println("Hardest Question");
	System.out.println(list.get(hardestIndex).getQuestion());
	System.out.println("\tTimes tried : " + list.get(hardestIndex).getAttempts());
	System.out.println("\tTimes correct : " + list.get(hardestIndex).getrightAttempts());
	System.out.printf("\tPercent correct : %.2f%% \n", hardest);
	
	PrintWriter write = new PrintWriter(filename); // printwriter for updating the text info
	
	for ( int i = 0; i < list.size(); i++)
		write.println(list.get(i));
			
		
	
		
		
		
		
		filesc.close();
		write.close();

	}
	
}