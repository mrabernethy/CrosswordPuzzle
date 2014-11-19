package crosswordData;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Abernethy
 * @version v1.0 - 2014.10: Created
 */
public class WordsAndCluesTest {
    private static WordsAndClues wc;
    
    public WordsAndCluesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        wc = new WordsAndClues("testing", "What is this?", "across", 0, 3, "1");
    }
    
    @AfterClass
    public static void tearDownClass() {
        wc = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getWord method, of class WordsAndClues.
     */
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        WordsAndClues instance = wc;
        String expResult = "testing";
        String result = instance.getWord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClue method, of class WordsAndClues.
     */
    @Test
    public void testGetClue() {
        System.out.println("getClue");
        WordsAndClues instance = wc;
        String expResult = "What is this?";
        String result = instance.getClue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class WordsAndClues.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        WordsAndClues instance = wc;
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX method, of class WordsAndClues.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        WordsAndClues instance = wc;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class WordsAndClues.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        WordsAndClues instance = wc;
        int expResult = 3;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSolved method, of class WordsAndClues.
     */
    @Test
    public void testIsSolved() {
        System.out.println("isSolved");
        WordsAndClues instance = wc;
        boolean expResult = false;
        boolean result = instance.isSolved();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSolved method, of class WordsAndClues.
     */
    @Test
    public void testSetSolved() {
        System.out.println("setSolved");
        WordsAndClues instance = wc;
        instance.setSolved();
        boolean expResult = true;
        boolean result = instance.isSolved();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class WordsAndClues.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        WordsAndClues instance = wc;
        String expResult = "across";
        String result = instance.getDirection();
        assertEquals(expResult, result);
    }
}
