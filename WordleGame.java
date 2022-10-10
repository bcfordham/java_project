/**
 * Manages a single game of Wordle.
 * Modified slighted from Project 1.
 * It is no longer possible to get the secret word.
 * Also, there is a getWordLength method.
 * 
 * !!! DO NOT MODIFY THIS CLASS !!!
 * 
 * @author Max Ward
 */
public class WordleGame {
    private String secretWord;
    private int numGuesses;
    
    /**
     * Constructs a WordleGame with a specified secret word.
     * 
     * Note that we assume the secretWord contains only lowercase letters ('a'-'z').
     */
    public WordleGame(String secretWord)
    {
        this.secretWord = secretWord;
    }
    
    /**
     * Returns true if the given letter is in the secret word.
     * For example, if secretWord was "haste" and we called isInSecretWord('e'),
     * then the output would be true because 'e' is in "haste".
     * However, if we called isInSecretWord('z'), then the output would be false.
     */
    private boolean isInSecretWord(char letter)
    {
        return secretWord.contains(""+letter);
    }
    
    /**
     * Returns the length of the secret word.
     */
    public int getWordLength()
    {
        return secretWord.length();
    }
    
    /**
     * Returns the number of guesses made so far.
     */
    public int getNumGuesses()
    {
        return numGuesses;
    }
    
    /**
     * Takes a word being guessed and outputs a string describing the result of the guess.
     * 
     * Returns the empty string "" if the guess is the wrong length.
     * 
     * A maximum of 6 guesses are allowed. The empty string "" is returned after 6 guesses.
     * 
     */
    public String guessWord(String wordToGuess)
    {
        if (wordToGuess.length() != secretWord.length())
            return "";
        if (numGuesses == 6)
            return "";
        String description = "";
        for (int i = 0; i < secretWord.length(); ++i) {
            if (wordToGuess.charAt(i) == secretWord.charAt(i))
                description += "*";
            else if (isInSecretWord(wordToGuess.charAt(i)))
                description += ".";
            else
                description += "_";
        }
        ++numGuesses;
        return description;
    }
}
