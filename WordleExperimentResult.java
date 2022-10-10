
import java.util.*;
/**
 * Used to store the result of a WordleExperiment.
 * 
 * !!! DO NOT MODIFY THIS CLASS !!!
 *
 * @author Max Ward
 */
public class WordleExperimentResult
{
    // The secret word
    private String word;
    // The guesses made by the WordleAI
    private ArrayList<String> guesses;

    public WordleExperimentResult(String word, ArrayList<String> guesses)
    {
        this.word = word;
        this.guesses = guesses;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public ArrayList<String> getGuesses()
    {
        return guesses;
    }
}
