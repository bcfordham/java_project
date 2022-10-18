import java.util.*;

/**
 * Analyses a WordleAI by running experiments and computing statistics.
 * 
 * @author StudentNumber1 AND StudentNumber2
 */
public class WordleAIAnalyser
{
    
    // Do not modify the fields of this class.
    private WordleDictionary dictionary;
    private int wordLength;
    // You should read but NOT modify the WordleExperimentResult class
    private ArrayList<WordleExperimentResult> experimentResults;
    
    /**
     * Constructor takes a dictionary and word length to run experiments with.
     */
    public WordleAIAnalyser(WordleDictionary dictionary, int wordLength)
    {
        this.wordLength = wordLength;
        this.dictionary = dictionary;
        experimentResults = new ArrayList<>();
    }
    
    /**
     * !!! DO NOT MODIFY !!!
     * This method has been implemented for you.
     */
    public ArrayList<WordleExperimentResult> getExperimentResults()
    {
        return experimentResults;
    }
    
    /**
     * Runs an experiment on a word and stores the result in experimentResults.
     * An experiment is the WordleExperimentResult from using the WordleAI on a WordleGame
     * with word as the secret word.
     * 
     * No checks or guards are needed on the word parameter.
     * It is always assumed to be the right length and to come from the dictionary.
     */
    public void runExperiment(String word)
    {
        WordleGame AIGame;
        WordleExperimentResult result;
        ArrayList<String> collection;
        
        AIGame = new WordleGame(word);
        collection= WordleAI.findWord(dictionary, AIGame);
        
        result = new WordleExperimentResult(word, collection);
        experimentResults.add(result);
    }
    
    /**
     * Runs and stores experiments for each word in the dictionary with the right length.
     * 
     * Should call runExperiment once for each word.
     */
    public void runExperimentsWithAllWords()
    {
        ArrayList<String> words = dictionary.getWordsWithLength(wordLength);
        for(String s: words) {
            runExperiment(s);
        }
    }
    
    /**
     * Runs and stores experiments for each word in the dictionary with the right length.
     * 
     * Only uses words that are lexicographically between the start and finish.
     * A word is only used if it is the same as start or comes after AND it is the same as finish or comes before.
     * 
     * For example, if our words are "act", "bat", "bet", "cat"
     * Then runExperimentsWithWordsBetween("baa", "caa")
     * would only run experiments for "bat" and "bet"
     * 
     * Should call runExperiment once for each word.
     * 
     * HINT: Recall the String compareTo method.
     */
    public void runExperimentsWithWordsBetween(String start, String finish)
    {
        ArrayList<String> list = new ArrayList<>(); 
        list = dictionary.getWordsWithLength(wordLength); //adds String with chosen length to Array List
        Collections.sort(list); //sorts array list alphabetically
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).compareTo(start) >= 0 && list.get(i).compareTo(finish) <= 0) { //compares
                runExperiment(list.get(i));
            }

    }
    
    /**
     * Returns a list of all experiment words that were not solved by WordleAI.
     * The returned list of words should be in lexicographic order.
     * 
     * There may be duplicates in experimentResults.
     * This method should NOT return any duplicated words!
     * 
     * HINT 1: Remeber that findWord returns null when it cannot solve the word.
     * HINT 2: See Collections.sort and the ArrayList contains method.
     */
    public ArrayList<String> getUnsolvedWords()
    {
        ArrayList<String> unsolvedWords = new ArrayList<>();
        String currentWord;
        
        // Loops through each experiment result
        for (WordleExperimentResult x: experimentResults) {
            currentWord = x.getWord();
            
            // Checks if the AI managed to guess the word or not, and if the word is already in our list
            if (x.getGuesses() == null && unsolvedWords.contains(currentWord) == false) 
                unsolvedWords.add(currentWord);
        }
        
        // Lexicographically sorts the list
        Collections.sort(unsolvedWords);
        
        return unsolvedWords;
    }
    
    /**
     * Returns an array with length 26.
     * The entry at index [0] is the number of 'a' characters guessed over all experiments by WordleAI.
     * The entry at index [1] is the number of 'b' characters.
     * ...
     * The entry at index [25] is the number of 'z' characters.
     * 
     * For example, if the WordleAI guessed the words "cat"+"hat" in one experiment and "log"+"cat" in another:
     * The return array would be {3, 0, 2, ... }
     * Which means 3 'a' characters, 0 'b' characters, 2 'c' characters, and so on...
     * 
     * HINT: Unsolved words have no guesses and should be skipped.
     */
    public int[] getGuessLetterFrequency()
    {
        // TODO 10
        return null;
    }
    
    /**
     * Returns an array of length 7.
     * 
     * The entry at index [0] is the number of times the WordleAI guessed a word correctly after 1 guess.
     * The entry at index [1] is the number of times exactly 2 guesses were needed.
     * and so on.
     * The entry at index [6] is the number of times the WordleAI did not correctly guess the word.
     */
    public int[] getNumGuessesFrequency()
    {
        // TODO 11
        return null;
    }
    
    /**
     * Makes a string containing a histogram picture of getNumGuessesFrequency().
     * 
     * A possible histogram might look like this:
     *     ..*....
     *     ..*..*.
     *     .**.**.
     *     .*****.
     *     ******.
     *
     * The stars form bars in a histogram, and the dots represent empty space.
     * This would correspond to a frequency table of {1, 3, 5, 2, 3, 4, 0}
     * Recall that the newline '\n' character can be used to encode a line break in a string.
     * Note that return string should end with a newline '\n' character.
     * 
     * Because the numbers can be large, we use a bucketSize.
     * The height of a bar in the chart is 0 if the corresponding number is in the inclusive range from 0 to bucketSize-1
     * The height is 1 if the number is in the inclusive range from bucketSize to bucketSize*2-1
     * The height is 2 if the number is in the inclusive range from bucketSize*2 to bucketSize*3-1
     * ...and so on.
     * 
     * The height of the histogram should be the same as the height of the tallest bar.
     * 
     */
    public String makeHistogram(int bucketSize)
    {
        // TODO 12
        return null;
    }
    
    /**
     * Prints the string made by makeHistogram(bucketSize)
     * 
     * The following code:
     * WordleAIAnalyser analyser = new WordleAIAnalyser(new WordleDictionary(), 5);
     * analyser.runExperimentsWithAllWords();
     * analyser.printHistogram(50);
     * 
     * 
     * Should print this to the terminal:
     *   ...*...
     *   ...*...
     *   ...*...
     *   ..**...
     *   ..**...
     *   ..***..
     *   ..***..
     *   ..***..
     *   ..***..
     *   .*****.
     */
    public void printHistogram(int bucketSize)
    {
        // No need to modify this method!
        // It has been provided for you.
        System.out.println(makeHistogram(bucketSize));
    }
}
