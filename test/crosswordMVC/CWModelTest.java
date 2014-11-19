package crosswordMVC;

import crosswordData.WordsAndClues;
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
public class CWModelTest {
    private static WordsAndClues wc;
    
    public CWModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        wc = new WordsAndClues("TESTING", "What is this?", "across", 0, 0, "1");
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
     * Test of checkid method, of class CWModel.
     */
    @Test
    public void testCheckid() throws Exception {
        System.out.println("checkid");
        String un = "";
        String pw = "";
        CWModel instance = new CWModel();
        instance.checkid(un, pw);
    }

    /**
     * Test of checkAnswer method, of class CWModel.
     */
    @Test
    public void testCheckAnswer() {
        System.out.println("checkAnswer");
        String userAns = "testing";
        String clue = "What is this?";
        CWModel instance = new CWModel();
        instance.uInfo.wordClueList.add(wc);
        instance.setLetters();
        instance.checkAnswer(userAns, clue);
    }

    /**
     * Test of quitGame method, of class CWModel.
     */
    @Test
    public void testQuitGame() {
        System.out.println("quitGame");
        CWModel instance = new CWModel();
        instance.quitGame();
    }
    
}
