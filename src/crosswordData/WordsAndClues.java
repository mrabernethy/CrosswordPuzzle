package crosswordData;

/**
 *
 * @author Mike
 * 
 * Class to hold individual puzzle questions and data.
 */
public class WordsAndClues {
    private String word;
    private String clue;
    private String id;
    private boolean solved;
    private int x, y;
    private String direction;
    
    /**
     * Contains all the data for a single crossword question.
     * 
     * @param word
     * @param clue
     * @param direction
     * @param id the word number
     * @param x the starting x position in the grid
     * @param y the starting y position in the grid
     */
    public WordsAndClues(String word, String clue, String direction, int x, int y, String id)
    {
        this.word = word;
        this.clue = clue;
        this.direction = direction;
        this.id = id;
        this.x = x;
        this.y = y;
        solved = false;
    }

    public String getWord()
    {
        return word;
    }
    
    public String getClue()
    {
        return clue;
    }
    
    public String getId()
    {
        return id;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean isSolved()
    {
        return solved;
    }
    
    public void setSolved()
    {
        solved = true;
    }
    
    public String getDirection()
    {
        return direction;
    }
}
