package projeOdevi2;
import java.util.ArrayList;

public class HangmanPuzzle 
{
	private String answer;
	private String[] words = {"quiz", "fax", "jinx", "fox", "jays","buff", "puff", "fizz", "huh", "babes"};
    private boolean[] guessed;
    private ArrayList<String> wordList, wrongLetters;

    /**
     * The constructor initializes the boolean array, the wrongLetters ArrayList and picked randomly from the words array
     */
    public HangmanPuzzle(){
    	answer = words[(int)(words.length*Math.random())];
		guessed = new boolean[answer.length()];
		for (int i=1; i<guessed.length; i++){
			guessed[i]=false;
		}
    	wrongLetters = new ArrayList<String>();
	}
    

    public void checkGuess(String s){
    	boolean found=false;
    	for (int i=0; i<guessed.length; i++){
    		if (s.toUpperCase().equals(answer.substring(i, i+1).toUpperCase())){
    			guessed[i]=true;
    			found=true;
    		}
    	}
    	if (!found)
    		wrongLetters.add(s.toUpperCase());
    }
    public String clue(){
    	String result="";
    	for (int i=0; i<answer.length();i++){
    		if (guessed[i]){
    			result+=answer.substring(i, i+1).toUpperCase()+" ";
    		}else{
    			result+="_ ";
    		}
    	}
    	return result;
    }

}
