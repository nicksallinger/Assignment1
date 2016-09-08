import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

public class Ballot extends JPanel{
	
	
	private String[] ballotChoices;
	private int numBallots;
	private String[] ballotArr;
	protected ArrayList<JToggleButton> JToggleButtons = new ArrayList<JToggleButton>();
	protected int votersVote;
	protected int[] votesTotal;
	
	public Ballot(int numBallots, String[] ballotArr, String[] ballotChoices, int[] votesTotal, JFrame frame ){
		
		this.numBallots = numBallots;
		this.ballotChoices = ballotChoices;
		this.ballotArr = ballotArr;
		this.votesTotal = votesTotal;
		
		//makes a panel for everything in it
		
		JPanel ballotObj = new JPanel();
		
		 ballotObj.setLayout(new BoxLayout(ballotObj, BoxLayout.Y_AXIS));
		
		JLabel ballotName = new JLabel(ballotArr[1]);
		ballotObj.add(ballotName);
		
		
	
		
		for (int j = 0; j< ballotChoices.length ; j++){
			int k = j;
			JToggleButton ballotChoiceButton = new JToggleButton(ballotChoices[j]);
			//starts off disables, so user cant vote until its set enabled in the main
			ballotChoiceButton.setEnabled(false);
			ballotObj.add(ballotChoiceButton);
			
			ballotChoiceButton.addActionListener(new ActionListener() {
				
				 public void actionPerformed(ActionEvent e){ 
				//this listener get the index of the candidate the user chose
				//based off the index of the selected button
				
				if(ballotChoiceButton.isSelected()){
					votersVote=k;
					//changes appearance based on selected/not selected
				ballotChoiceButton.setForeground(Color.RED);
				
				}
				

					
				
				else{
					
					ballotChoiceButton.setForeground(Color.BLACK);
				}
				
				
			}});
			JToggleButtons.add(ballotChoiceButton);
			
			
		}
		//actually adds the panel to the frame
		frame.add(ballotObj);
		
		
	}
	
	public int getNumBallots(){
		return numBallots;
	}
	
	public String[] getBallotChoices(){
		return ballotChoices;
	}
	public String[] getBallotArr(){
		return ballotArr;
	}
	public int[] getVotesTotal(){
		return votesTotal;
	}
	


}
