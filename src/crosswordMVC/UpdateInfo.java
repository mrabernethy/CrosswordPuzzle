
package crosswordMVC;

import crosswordData.WordsAndClues;
import crosswordData.PuzzleSquare;
import java.util.ArrayList;

/**
 * Contains update information that gets sent to the observer.
 * 
 * Adapted from PDC Lab solution.
 */
public class UpdateInfo {
    boolean loginflag = false;
    boolean quitflag = false;
    int currentscore = 0;
    int numWords = 0;
    int puzzleNum = 1;
    ArrayList<WordsAndClues> wordClueList = new ArrayList<>();
    PuzzleSquare[][] puzzleSquares = new PuzzleSquare[10][10];
    String message = "start new puzzle";
    String currentClue = "Select a clue to solve";
}
