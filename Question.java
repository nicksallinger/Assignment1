public class Question{
	

	private String s1;
	private int numChoices;
	private String[] choices;
	private int answer;
	private int attempts;
	private int rightAttempts;
	
	
	
	public Question(String s1, int numChoices, String choices[], int answer, int attempts, int rightAttempts){
		//constructor for making a questions
		this.s1=s1;
		this.numChoices = numChoices;
		this.choices = choices;
		this.answer = answer;
		this.attempts = attempts;
		this.rightAttempts = rightAttempts;

		}
	public int getAttempts(){ 
		return attempts;
	}
	public int getNumChoices(){
		return numChoices;
	}
	
	public int getrightAttempts(){
		return rightAttempts;
	}
   public String toString() {
		StringBuilder questionString = new StringBuilder(s1+"\n"+numChoices+"\n");
		for (int i = 0; i<numChoices; i++){
			questionString.append(choices[i]+"\n");
		}
		questionString.append(answer + "\n" + attempts + "\n" + rightAttempts);
		
		
       return questionString.toString();
    }
	
	public void increaseAttempts(){
		
		attempts++;
	}
	
	public void increaseRightAttempts(){
		
		rightAttempts++;
		
	}
	public String[] getAnswers(){
		return choices;
	}
	public int getAnswer(){
		return answer;
	}
	
	public String getQuestion(){
		return s1;
	}
	
	public String getrightAnswer(){
		return choices[answer];
	}
	
	
}