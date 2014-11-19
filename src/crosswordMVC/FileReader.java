package crosswordMVC;

import crosswordData.WordsAndClues;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Mike
 * 
 * Class to read from txt files.
 */
public class FileReader 
{
    private File file;
    
    /**
     * Takes the UpdateInfo class as a parameter and adds the crossword data to the 
     * wordClueList collection.
     * 
     * @param uInfo the update info
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public FileReader(UpdateInfo uInfo) throws FileNotFoundException, IOException 
    {
        if (loadFile(uInfo.puzzleNum))
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            String line;
            String[] lineSplit;
            // Read the document
            while ((line = br.readLine()) != null) {
                // The data recorded in the file is in a format of
                // Word : Clue : Direction : InitialXPos : InitialYPos : WordNumber
                // Split the line into six parts, the delimiter is :
                lineSplit = line.split("[\\:]+");   
                // Add the words and clues to the update info
                uInfo.wordClueList.add(new WordsAndClues(lineSplit[0], lineSplit[1], lineSplit[2], 
                        Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), lineSplit[5]));
            }
        }
    }
    
    /**
     * Load puzzle.txt
     *
     * @return true if the file exists otherwise false
     * @throws IOException
     */
    private boolean loadFile(int puzzleNum) throws IOException 
    {
        switch (puzzleNum)
        {
            case 1: 
                file = new File("puzzle1.txt");                                    
                break;
            case 2:
                file = new File("puzzle2.txt");                                    
                break;
            case 3: 
                file = new File("puzzle3.txt");                                    
                break;
            case 4:
                file = new File("puzzle4.txt");                                    
                break;
            case 5:
                file = new File("puzzle5.txt");                                    
                break;
        }
        
        return file.exists();
    }
}
