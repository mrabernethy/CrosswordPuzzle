package crosswordMVC;

import crosswordData.PuzzleSquare;
import crosswordData.WordsAndClues;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * The model.
 * 
 * Adapted from PDC Lab solution.
 */
public class CWModel extends Observable{
    String username=null;
    String password=null;
    CWDB dbconnection;
    UpdateInfo uInfo;
    
    /**
     * Constructor for model class.
     * 
     * Initialises database and gets update info.
     */
    CWModel(){
        dbconnection=new CWDB();
        dbconnection.dbsetup();
        uInfo=new UpdateInfo();
    }
    
    /**
     * Checks if entered username and password match any in the database.
     * Reads correct puzzle from txt file and restores solved words.
     * 
     * @param un the entered username
     * @param pw the entered password
     * @throws java.io.IOException
     */
    public void checkid(String un, String pw) throws IOException{
        this.username=un;
        this.password=pw;
        if(username!=null&&password!=null){
            uInfo.currentscore=dbconnection.checkName(username, password);
            
            if(uInfo.currentscore==-99)
                uInfo.loginflag=false;
            else
            {
                uInfo.loginflag=true;
                String solvedWords = null;
                uInfo.puzzleNum=dbconnection.getPuzzleNum();
                if (uInfo.puzzleNum==0)
                {
                    uInfo.puzzleNum++;
                }
                else if (uInfo.puzzleNum==6)
                {
                    System.out.println("All puzzles solved. Resetting...");
                    uInfo.puzzleNum=1;
                }
                else
                {
                    solvedWords = dbconnection.getSolvedWords();
                }
                
                FileReader fr = new FileReader(uInfo);                              
                
                if (solvedWords!=null)
                {
                    
                    for (WordsAndClues wc : uInfo.wordClueList)
                    {
                        if (solvedWords.toUpperCase().contains(wc.getWord()))
                        {
                            wc.setSolved();
                        }
                    }
                }
                setLetters();
            }
            setChanged();
            notifyObservers(uInfo);
        }
    
    }
    
    /**
     * Sets up data for the letters in the crossword grid. Sets them as solved if
     * the word has already been solved.
     * 
     * Method is public for testing.
     */
    public void setLetters()
    {
        // Initialise the puzzle squares
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                PuzzleSquare ps = new PuzzleSquare();
                uInfo.puzzleSquares[i][j] = ps;
            }
        }
        ArrayList<WordsAndClues> list = uInfo.wordClueList;
        for (WordsAndClues wc : list)
        {
            
            String word = wc.getWord();
            
            String direction = wc.getDirection();
            int x = wc.getX();
            int y = wc.getY();
            
            for (int k=0; k<word.length();k++)
            {
                if (direction.equals("across"))
                {
                    uInfo.puzzleSquares[x+k][y].setLetter(word.charAt(k));
                    if (wc.isSolved())
                    {
                        uInfo.puzzleSquares[x+k][y].setSolved();
                    }
                }
                else if (direction.equals("down"))
                {
                    uInfo.puzzleSquares[x][y+k].setLetter(word.charAt(k));
                    if (wc.isSolved())
                    {
                        uInfo.puzzleSquares[x][y+k].setSolved();
                    }
                }
            }
        }
    }
        
   
    /**
     * Checks the answer is correct and alters the player's score.
     * Sets up the next question.
     * 
     * @param userAns 
     * @param clue 
     */
    public void checkAnswer(String userAns, String clue)
    {
        userAns = userAns.toUpperCase();
        clue = clue.replaceAll("[^A-Za-z]", "");
        
        boolean correct = false;
        boolean allWordsSolved = true;
                
        for (WordsAndClues wc : uInfo.wordClueList)
        {
            String comparisonClue = wc.getClue();
            comparisonClue = comparisonClue.replaceAll("[^A-Za-z]", "");
            
            // Check that the answer is correct and matches the correct clue.
            if (comparisonClue.compareTo(clue)==0 && userAns.compareTo(wc.getWord())==0)
            {
                correct = true;
                System.out.println(userAns + " is correct");
                uInfo.currentscore+=10;
                revealLetters(wc.getWord(), wc.getDirection(), wc.getX(), wc.getY());
                wc.setSolved();
                uInfo.currentClue = "Select a clue to solve";
                highlightLetters();
            }
            
            // Check if the puzzle is finished.
            if (!wc.isSolved())
            {
                allWordsSolved = false;
            }
        }
        
        // Subtract points if a clue is selected and the answer is incorrect.
        // Do not lose points if the answer field is blank.
        if (!correct && userAns.compareTo("")!=0 && clue.compareTo("Selectacluetosolve")!=0)
        {
            uInfo.currentscore-=10;
        }
        
        if (allWordsSolved)
        {
            uInfo.puzzleNum += 1;
            uInfo.wordClueList.removeAll(uInfo.wordClueList);
            uInfo.message = "start new puzzle";
            uInfo.currentClue = "Select a clue to solve";
            
            // Quit the game if all puzzles are solved.
            if (uInfo.puzzleNum==6)
            {
                quitGame();
            }
            // Otherwise set up the next puzzle.
            else
            {
                try {

                    FileReader fr = new FileReader(uInfo);                              
                } catch (IOException ex) {
                    Logger.getLogger(CWModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                setLetters();
            }
            
        }
        
        setChanged();
        notifyObservers(uInfo);
    }
    
    /**
     * Sets each letter in a solved word to solved.
     * 
     * @param word the solved word
     * @param direction the direction of the word
     * @param x the initial x position
     * @param y the initial y position
     */
    private void revealLetters(String word, String direction, int x, int y)
    {
        for (int k=0; k<word.length();k++)
        {
            if (direction.equals("across"))
            {
                uInfo.puzzleSquares[x+k][y].setSolved();
            }
            else if (direction.equals("down"))
            {
                uInfo.puzzleSquares[x][y+k].setSolved();
            }
        }
    }
    
    public void highlightLetters()
    {
        PuzzleSquare[][] ps = uInfo.puzzleSquares;
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                ps[i][j].setSelected(false);
            }
        }
        
        for (WordsAndClues wc : uInfo.wordClueList)
        {
            String clue = wc.getClue();
            clue = clue.replaceAll("[^A-Za-z]", "");
            String currentClue = uInfo.currentClue;
            currentClue = currentClue.replaceAll("[^A-Za-z]", "");
            if (clue.compareTo(currentClue)==0)
            {
                int i = wc.getX();
                int j = wc.getY();
                for (int k=0; k<wc.getWord().length(); k++)
                {
                    if (wc.getDirection().compareTo("across")==0)
                    {
                        ps[i+k][j].setSelected(true);
                    }
                    else
                    {
                        ps[i][j+k].setSelected(true);
                    }
                }
            }
        }
        
        setChanged();
        notifyObservers(uInfo);
    }
    
    /**
     * Quits the game.
     * 
     * Disconnects from database and notifies the observer.
     */
    public void quitGame(){
        uInfo.quitflag=true;
        String solvedWords = "";
        for (WordsAndClues wc : uInfo.wordClueList)
        {
            if (wc.isSolved())
            {
                solvedWords+=wc.getWord();
            }
        }
        // Updates the database with score, current puzzle number, and words solved in
        // the current puzzle.
        dbconnection.updateDB(uInfo.currentscore, uInfo.puzzleNum, solvedWords);
        setChanged();
        notifyObservers(uInfo);
    }
}
