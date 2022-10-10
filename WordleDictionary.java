import java.io.*;
import java.util.*;
/**
 * Manages a dictionary of words for use in Wordle.
 * Modifed slightly from Project 1.
 * Now has a new constructor.
 * 
 * !!! DO NOT MODIFY THIS CLASS !!!
 *
 * @author Max Ward
 */
public class WordleDictionary
{
    // Contains all the words in the dictionary.
    private ArrayList<String> words;

    /**
     * Constructs a WordleDictionary from a single parameter which gives a String
     * containing the path to the file of words to use.
     */
    public WordleDictionary(String wordsFilePath)
    {
        words = new ArrayList<>();
        File wordsFile = new File(wordsFilePath);
        try {
            Scanner fileScanner = new Scanner(wordsFile);
            while (fileScanner.hasNext()) {
                words.add(fileScanner.next());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the word file: " + wordsFilePath);
            e.printStackTrace();
        }
    }
    
    /**
     * Default constructor for use in BlueJ that uses the default path to "words.txt".
     */
    public WordleDictionary()
    {
        this("words.txt");
    }
    
    
    /**
     * Constructs a dictionary with a user-specified list of words.
     */
    public WordleDictionary(ArrayList<String> words)
    {
        this.words = words;
    }
    
    /**
     * Returns an ArrayList containg all the words in the dictionary with a specified length (in number of letters).
     */
    public ArrayList<String> getWordsWithLength(int length)
    {
        ArrayList<String> wordsWithLength = new ArrayList<>();
        for (String word : this.words) {
            if (word.length() == length) {
                wordsWithLength.add(word);
            }
        }
        return wordsWithLength;
    }
    
    /**
     * Returns a randomly selected word from the dictionary with a specified length.
     */
    public String getRandomWord(int length)
    {
        ArrayList<String> words = getWordsWithLength(length);
        return words.get(new Random().nextInt(words.size()));
    }
}
