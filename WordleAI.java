import java.util.*;

/**
 * This class contains static methods that play Wordle using a simple artificial intelligence.
 *
 * @author 22708346 AND 23614901
 */
public class WordleAI
{
    private WordleAI()
    {
        // Constructor is private because all methods are static
        // and instances of WordleAI should not be created
    }
    
    
    /**
     * Returns true if guess contains the letter c and false otherwise.
     */
    public static boolean guessContains(String guess, char c)
    {
        return guess.indexOf(c) > -1;
    }
    
    /**
     * Returns true if newGuess is consistent with a previousGuess and its result
     * and false otherwise.
     * 
     * The parameter previousGuess is a previous Wordle guess made by the AI.
     * The parameter result is the result of the guessWord method in WordleGame.
     * The parameter newGuess is a potential new word to guess.
     * 
     * A newGuess is consistent with the previousGuess and result if they can be
     * explained by newGuess being the secret word. That is, newGuess should not
     * contradict results from previous guesses.
     * 
     * For example, suppose we have previousGuess="dxaxx" and the result="*_.__",
     * then newGuess="dairy" or newGuess="dzzza" would return true, but
     * newGuess="testa" or newGuess="dxiry" would be false.
     * This is because only newGuess="dairy" or newGuess="dzzza" could have been the secret word
     * for previousGuess="dxaxx" to get result="*_.__"
     * 
     * HINT: Can you use a new WordleGame(...) somehow?
     */
    public static boolean isConsistent(String previousGuess, String result, String newGuess)
    {
        char previousChar;
        char newChar;
        for (int i = 0; i < result.length(); i++) {
            previousChar = previousGuess.charAt(i);
            newChar = newGuess.charAt(i);
            
            // Checks each letter to see if its consistent with previous guesses
            switch(result.charAt(i)) {
                case '*':
                    if (previousChar != newChar)
                        return false;
                    break;
                case '.':
                    if (previousChar == newChar)
                        return false;
                    if (!guessContains(newGuess, previousChar))
                        return false;
                    break;
                default:
                    if (guessContains(newGuess, previousChar))
                        return false;
            }
        }
        return true;
    }
    
    /**
     * Returns true if result contains only '*' characters
     * and false if it contains a non-'*' character.
     */
    public static boolean isAllStars(String result)
    {
        Boolean contains = true;
        for(int i = 0; i < result.length(); i++) {
        if(result.charAt(i) != '*')
        contains = false;
    }return contains;
    }
    
    /**
     * This method runs the AI algorithm.
     * Given a dictionary and a game, makes a series of calls to game.guessWord(word)
     * to find the secret word in game.
     * Returns an ArrayList containing the words in the order they were guessed.
     * If the secret word could not be found, returns null.
     * 
     * The AI algorithm is very specific!
     * It uses a simple strategy similar to one you may have used when playing Wordle.
     * 
     * The AI starts by guessing the the lexicographicaly smallest word.
     * 
     * Then, for every guess after that, the AI guesses a word that does not contradict any previous
     * results it has seen. That is, it makes guesses that are consistent (see isConsistent)
     * with all guesses made so far.
     * 
     * If there are multiple possible guesses that are consistent, then the AI
     * will pick the lexicographically smallest option.
     * 
     * WordleGame has been modified to only allow 6 guesses. After this, it will return an
     * empty string "".
     * 
     * If the game ends because the AI has run out of guesses, then findWord returns null.
     * Otherwise, findWord returns once it has made a correct guess. This can
     * be checked using the isAllStars method above. In this case, a list of the AI's
     * guesses in the order they were made is returned.
     * 
     * HINT 1: You will almost certainly need to read the unit test and use the debugger for this method.
     * HINT 2: See Collections.sort in the Java class libraries for lexicographical ordering.
     */
    public static ArrayList<String> findWord(WordleDictionary dictionary, WordleGame game)
    {
        int counter;
        String currentWord;
        String result;
        ArrayList<String> guessedWords = new ArrayList<>();
        ArrayList<String> guessResults = new ArrayList<>();
        int secretLength = game.getWordLength();
        ArrayList<String> words = dictionary.getWordsWithLength(secretLength);

        Collections.sort(words);
        
        for (int i = 0; i < words.size(); i++) {
            counter = 0;
            currentWord = words.get(i);
            
            for (int j = 0; j < guessedWords.size(); j++) {
                if (isConsistent(guessedWords.get(j), guessResults.get(j), currentWord))
                    counter++;
                else
                    break;
            }
            
            // Use the current word as the guess only if its consistent with every previous attempt
            if (counter == guessedWords.size()) {
                result = game.guessWord(currentWord);
                guessedWords.add(currentWord);
                guessResults.add(result);
                
                if (isAllStars(result))
                    return guessedWords;
            }
            
            if (game.getNumGuesses() == 6)
                break;
        }
        
        return null;
    }
}
