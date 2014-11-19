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
public class PuzzleSquareTest {
        
    public PuzzleSquareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isSolved method, of class PuzzleSquare.
     */
    @Test
    public void testIsSolved() {
        System.out.println("isSolved");
        PuzzleSquare instance = new PuzzleSquare();
        boolean expResult = false;
        boolean result = instance.isSolved();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSolved method, of class PuzzleSquare.
     */
    @Test
    public void testSetSolved() {
        System.out.println("setSolved");
        PuzzleSquare instance = new PuzzleSquare();
        instance.setSolved();
        boolean expResult = true;
        boolean result = instance.isSolved();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasLetter method, of class PuzzleSquare.
     */
    @Test
    public void testHasLetter() {
        System.out.println("hasLetter");
        PuzzleSquare instance = new PuzzleSquare();
        boolean expResult = false;
        boolean result = instance.hasLetter();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLetter method, of class PuzzleSquare.
     */
    @Test
    public void testGetLetter() {
        System.out.println("getLetter");
        PuzzleSquare instance = new PuzzleSquare();
        char expResult = 0;
        char result = instance.getLetter();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLetter method, of class PuzzleSquare.
     */
    @Test
    public void testSetLetter() {
        System.out.println("setLetter");
        char letter = 'a';
        PuzzleSquare instance = new PuzzleSquare();
        instance.setLetter(letter);
        boolean expResult = true;
        boolean result1 = instance.hasLetter();
        assertEquals(expResult, result1);
        char result2 = instance.getLetter();
        assertEquals(letter, result2);
    }

    /**
     * Test of isFirstLetter method, of class PuzzleSquare.
     */
    @Test
    public void testIsFirstLetter() {
        System.out.println("isFirstLetter");
        PuzzleSquare instance = new PuzzleSquare();
        boolean expResult = false;
        boolean result = instance.isFirstLetter();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstLetter method, of class PuzzleSquare.
     */
    @Test
    public void testSetFirstLetter() {
        System.out.println("setFirstLetter");
        String id = "1";
        PuzzleSquare instance = new PuzzleSquare();
        instance.setFirstLetter(id);
        boolean expResult = true;
        boolean result = instance.isFirstLetter();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class PuzzleSquare.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PuzzleSquare instance = new PuzzleSquare();
        String id = "1";
        instance.setFirstLetter(id);
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }
    
}
