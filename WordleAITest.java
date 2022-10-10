import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * The test class WordleAITest.
 *
 * @author  Max Ward
 */
public class WordleAITest
{
    
    @Test
    public void testGuessContains()
    {
        assertTrue(WordleAI.guessContains("test", 't'));
        assertTrue(WordleAI.guessContains("test", 'e'));
        assertFalse(WordleAI.guessContains("test", 'f'));
    }
    
    @Test
    public void testIsAllStars()
    {
        assertTrue(WordleAI.isAllStars("***"));
        assertTrue(WordleAI.isAllStars("*"));
        assertFalse(WordleAI.isAllStars("*a*"));
        assertFalse(WordleAI.isAllStars("a"));
    }
    
    @Test
    public void testIsConsistent()
    {
        assertTrue(WordleAI.isConsistent("dxaxx", "*_.__", "dairy"));
        assertFalse(WordleAI.isConsistent("daddy", "*_.__", "dairy"));
        
        assertTrue(WordleAI.isConsistent("cat", ".._", "acz"));
        assertFalse(WordleAI.isConsistent("cat", ".._", "act"));
    }
    
    
    @Test
    public void testFindWord()
    {
        String[] words = {"hit", "log", "ate", "cat", "axe"};
        WordleDictionary dict = new WordleDictionary(new ArrayList<>(Arrays.asList(words)));
        
        WordleGame game = new WordleGame("hit");
        ArrayList<String> guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"ate", "hit"}, guesses.toArray());
        
        game = new WordleGame("log");
        guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"ate", "log"}, guesses.toArray());
        
        words = new String[] {"ate", "bat", "cat"};
        dict = new WordleDictionary(new ArrayList<>(Arrays.asList(words)));
        
        game = new WordleGame("cat");
        guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"ate", "bat", "cat"}, guesses.toArray());
        
        words = new String[] {"cat", "bat", "ate"};
        dict = new WordleDictionary(new ArrayList<>(Arrays.asList(words)));
        
        game = new WordleGame("cat");
        guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"ate", "bat", "cat"}, guesses.toArray());
        
        words = new String[] {"acat", "abat", "aate"};
        dict = new WordleDictionary(new ArrayList<>(Arrays.asList(words)));
        
        game = new WordleGame("acat");
        guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"aate", "abat", "acat"}, guesses.toArray());
        
        words = new String[] {"aa", "bb", "cc", "ca", "cb", "ab", "ba"};
        dict = new WordleDictionary(new ArrayList<>(Arrays.asList(words)));
        
        game = new WordleGame("ca");
        guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"aa", "ba", "ca"}, guesses.toArray());
        game = new WordleGame("cb");
        guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"aa", "bb", "cb"}, guesses.toArray());
        game = new WordleGame("ca");
        guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"aa", "ba", "ca"}, guesses.toArray());
    }
    
    @Test
    public void testFindWordReturnsNull()
    {
        String[] words = {"aaa","baa", "bba", "dda", "eea", "ffa", "gga", "hha"};
        WordleDictionary dict = new WordleDictionary(new ArrayList<>(Arrays.asList(words)));
        
        WordleGame game = new WordleGame("hha");
        ArrayList<String> guesses = WordleAI.findWord(dict, game);
        assertEquals(null, guesses);
    }
    
    @Test
    public void testFindWordIsConsistentWithAllPreviousGuesses()
    {
        String[] words = {"bba", "bcc", "bda", "bdd"};
        WordleDictionary dict = new WordleDictionary(new ArrayList<>(Arrays.asList(words)));
        
        WordleGame game = new WordleGame("bdd");
        ArrayList<String> guesses = WordleAI.findWord(dict, game);
        assertEquals(new String[] {"bba", "bcc", "bdd"}, guesses.toArray());
    }
}
