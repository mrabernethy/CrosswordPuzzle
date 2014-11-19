package crosswordMVC;

import crosswordData.WordsAndClues;
import java.awt.event.ActionListener;
import java.util.Observable;
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
public class CWViewTest {
    private static WordsAndClues wc;
    private static UpdateInfo update;
    private static CWModel model;
    
    public CWViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        wc = new WordsAndClues("TESTING", "What is this?", "across", 0, 0, "1");
//        update = new UpdateInfo();
        model = new CWModel();
        model.uInfo.wordClueList.add(wc);
        model.uInfo.message = "start new puzzle";
        model.setLetters();
    }
    
    @AfterClass
    public static void tearDownClass() {
        wc = null;
        update = null;
        model = null;
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updatePuzzle method, of class CWView.
     */
    @Test
    public void testUpdatePuzzle() {
        System.out.println("updatePuzzle");
        
        
        CWView instance = new CWView();
        instance.updatePuzzle(update);
    }

    /**
     * Test of setClueSelection method, of class CWView.
     */
    @Test
    public void testSetClueSelection() {
        System.out.println("setClueSelection");
        String clue = "";
        CWView instance = new CWView();
        instance.setClueSelection(clue);
    }

//    /**
//     * Test of startQuiz method, of class CWView.
//     */
//    @Test
//    public void testStartQuiz() {
//        System.out.println("startQuiz");
//        UpdateInfo uInfo = null;
//        CWView instance = new CWView();
//        instance.startQuiz(uInfo);
//    }

    /**
     * Test of setController method, of class CWView.
     */
    @Test
    public void testSetController() {
        System.out.println("setController");
        ActionListener cntrl = null;
        CWView instance = new CWView();
        instance.setController(cntrl);
    }

//    /**
//     * Test of update method, of class CWView.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Observable obs = null;
//        Object obj = null;
//        CWView instance = new CWView();
//        instance.update(obs, obj);
//    }
}
