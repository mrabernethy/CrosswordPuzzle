package crosswordData;

/**
 *
 * @author Mike
 * 
 * Class for a single square in the crossword grid.
 * 
 */
public class PuzzleSquare 
{
    private boolean hasLetter, solved, isFirstLetter, selected;
    private char letter;
    private String id;
    
    /**
     * Constructor for PuzzleSquare.
     * Sets default values.
     */
    public PuzzleSquare()
    {
        hasLetter = false;
        solved = false;
        isFirstLetter = false;
        selected = false;
        letter = 0;

    }
    
    public boolean isSolved()
    {
        return solved;
    }
    
    public void setSolved()
    {
        solved = true;
    }
    
    public boolean hasLetter()
    {
        return hasLetter;
    }
    
    public char getLetter()
    {
        return letter;
    }
    
    public void setLetter(char letter)
    {
        this.letter = letter;
        hasLetter = true;
    }
    
    public boolean isFirstLetter()
    {
        return isFirstLetter;
    }
    
    /**
     * Sets question number and true if this is the first letter square in a word.
     * 
     * @param id the crossword question number 
     */
    public void setFirstLetter(String id)
    {
        isFirstLetter = true;
        this.id = id;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
    
    public boolean isSelected()
    {
        return selected;
    }
}
