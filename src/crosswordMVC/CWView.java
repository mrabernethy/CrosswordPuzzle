package crosswordMVC;

import crosswordData.PuzzleSquare;
import crosswordData.WordsAndClues;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * The view.
 * 
 * Adapted from PDC Lab solution.
 */
public class CWView extends JFrame implements Observer{
    private JPanel userPanel=new JPanel();
    private JPanel controlPanel=new JPanel();
    private JPanel puzzlePanel;
    private JPanel cluePanel;
    private JLabel message=new JLabel("");
    private JLabel uName=new JLabel("Username: ");
    private JLabel pWord=new JLabel("Password: ");
    public JTextField unInput = new JTextField(10);
    public JTextField pwInput = new JTextField(10);
    private JLabel wrongName=new JLabel("Wrong username or password!");

    private JButton enterButton = new JButton("Enter");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");
    private JPanel[][] letterPanels = new JPanel[10][10];
    private ArrayList<JButton> clueButtons = new ArrayList<>();
    private ArrayList<JLabel> clues = new ArrayList<>();
    //private ArrayList<String> words = new ArrayList<>();
    public boolean started=false;

    public JLabel clueSelection=new JLabel();
    public JTextField userSolution = new JTextField(10); 
    
    private ActionListener cntrl;
    
    /**
     * Constructor for view class.
     * 
     * Sets up database log in panel.
     */
    CWView(){    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        userPanel.add(uName);
        userPanel.add(unInput);
        userPanel.add(pWord);
        userPanel.add(pwInput);
        userPanel.add(loginButton);
        userPanel.add(message);
        this.add(userPanel);
    }
    
    /**
     * Updates the puzzle.
     * 
     * @param update the update information
     */
    public void updatePuzzle(UpdateInfo update)
    {
        System.out.println("Update puzzle");
        
        // Initialise the puzzle and frame if it's a new puzzle
        if (update.message.compareTo("start new puzzle")==0)
        {
            update.message = "";
            startQuiz(update);
        }
        
        
        PuzzleSquare[][] ps = update.puzzleSquares;
        
        // Add the puzzle squares
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                if (ps[j][i].isSolved())
                {
                    letterPanels[j][i].removeAll();
                    JLabel l = new JLabel(String.valueOf(ps[j][i].getLetter()), SwingConstants.CENTER);
                    letterPanels[j][i].add(l);
                    letterPanels[j][i].setVisible(true);
                }
                
                if (ps[j][i].isSelected())
                {
                    letterPanels[j][i].setBackground(Color.red);
                }
                else if (ps[j][i].hasLetter())
                {
                    letterPanels[j][i].setBackground(Color.white);
                }
            }
        }
        
//        for (WordsAndClues wc : update.wordClueList)
//        {
//            
//            String clue = wc.getClue();
//            clue = clue.replaceAll("[^A-Za-z]", "");
//            String currentClue = update.currentClue;
//            currentClue = currentClue.replaceAll("[^A-Za-z]", "");
//            if (clue.compareTo(currentClue)==0)
//            {
//                int i = wc.getX();
//                int j = wc.getY();
//                for (int k=0; k<wc.getWord().length(); k++)
//                {
//                    if (wc.getDirection().compareTo("across")==0)
//                    {
//                        letterPanels[i+k][j].setBackground(Color.red);
//                    }
//                    else
//                    {
//                        letterPanels[i][j+k].setBackground(Color.red);
//                    }
//                }
//            }
//        }
        
        // Set the selected clue
        clueSelection.setText(update.currentClue);
        // Reset the user answer
        userSolution.setText("");
                
        // Disable buttons for solved clues
        for (JButton b : clueButtons)
        {
            for (WordsAndClues wc : update.wordClueList)
            {
                String buttonStr = b.getText().replaceAll("[^A-Za-z]", "");
                String clueStr = wc.getClue().replaceAll("[^A-Za-z]", "");
                if (buttonStr.compareTo(clueStr)==0 && wc.isSolved())
                {
                    b.setEnabled(false);
                }
            }
          
        }
        
        controlPanel.repaint();
        cluePanel.repaint();
    }
    
    /**
     * Sets the text field the the selected clue/
     * 
     * @param clue the selected clue 
     */
    public void setClueSelection(String clue)
    {
        clueSelection.setText(clue);
        
        
    }
   
    /**
     * Sets up the panel and renders it in the frame.
     */
    void startQuiz(UpdateInfo uInfo)
    {
        System.out.println("Start crossword");
        //this.removeAll();
        this.setSize(600, 400);
        
        // Initialise the panels
        puzzlePanel = new JPanel();
        cluePanel = new JPanel();
        //words = null;
        // Remove any old clue buttons
        clueButtons.removeAll(clueButtons);
        
        // Set size and layout
        puzzlePanel.setLayout(new GridLayout(10,10));
        puzzlePanel.setSize(400, 300);
        cluePanel.setLayout(new GridLayout(0,1));
        cluePanel.setSize(200, 300);
                
        PuzzleSquare[][] ps = uInfo.puzzleSquares;
        
        // Add the puzzle squares
        for (int i=0; i<10; i++)
        {
            for (int j=0; j<10; j++)
            {
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                
                // Sets square background either black or white, and adds the letter if
                // the word is solved.
                if (ps[j][i].hasLetter() && ps[j][i].isSolved())
                {
                    JLabel l = new JLabel(String.valueOf(ps[j][i].getLetter()), SwingConstants.CENTER);
                    panel.add(l);
                    panel.setBackground(Color.white);
                    panel.setVisible(true);
                    letterPanels[j][i] = panel;
                }
                else if (ps[j][i].hasLetter())
                {
                    panel.setBackground(Color.white);
                    panel.setVisible(true);
                    letterPanels[j][i] = panel;
                }
                else
                {
                    panel.setBackground(Color.black);
                    panel.setVisible(true);
                    letterPanels[j][i] = panel;
                }

                puzzlePanel.add(panel);
            }
        }
        
        // Set up the clue panel
        boolean acrossLabel = false;
        boolean downLabel = false;
        for (WordsAndClues wc : uInfo.wordClueList)
        {
            // Add across and down labels
            if (wc.getDirection().equals("across") && !acrossLabel)
            {
                JLabel l = new JLabel("Across:", SwingConstants.CENTER);
                cluePanel.add(l);
                acrossLabel = true;
            }
            else if (wc.getDirection().equals("down") && !downLabel)
            {
                JLabel l = new JLabel("Down:", SwingConstants.CENTER);
                cluePanel.add(l);
                downLabel = true;
            }
            
            // Add the clue buttons
            JButton button = new JButton(wc.getId() + ". " + wc.getClue());                                        //(wc.getId());
            button.addActionListener(cntrl);
            clueButtons.add(button);
            cluePanel.add(button);
        }
       
        // Set up the controlPanel
        controlPanel.add(clueSelection);
        controlPanel.add(userSolution);
        controlPanel.add(enterButton);
        controlPanel.add(quitButton);
       
        this.getContentPane().removeAll();
        controlPanel.setVisible(true);
        cluePanel.setVisible(true);
        puzzlePanel.setVisible(true);

        this.add(puzzlePanel, BorderLayout.CENTER);
        this.add(cluePanel, BorderLayout.EAST);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
   }
   
    /**
     * Attaches the control to this view and sets control as the action listener
     * for the buttons.
     * 
     * @param cntrl the mvc control
     */ 
    public void setController(ActionListener cntrl)
    {
        this.cntrl = cntrl;
        enterButton.addActionListener(cntrl);
        loginButton.addActionListener(cntrl);
        quitButton.addActionListener(cntrl);
    }
    
   /**
    * Fires on notifyObservers command.
    * Updates login message if login failed.
    * Starts the quiz if login succeeded.
    * 
    * @param obs
    * @param obj 
    */
   public void update(Observable obs, Object obj) {
       UpdateInfo update=(UpdateInfo)obj;
       if(!update.loginflag)
       {    
           message.setText("Wrong password or username");
           System.out.println("Wrong password");
           pwInput.setText("");
           userPanel.repaint();
       }
       else if(!started){
           updatePuzzle(update);
           started=true;
       }
       else if(update.quitflag)
       {
           JPanel quitPanel=new JPanel();
           String str;
           if (update.puzzleNum==6)
           {
               str = "You completed all the crosswords. Your score is "+update.currentscore;
           }
           else
           {
               str = "Your score: "+update.currentscore;
           }
           JLabel scoreLabel=new JLabel(str);
           quitPanel.add(scoreLabel);
           this.getContentPane().removeAll();
           this.add(quitPanel);
           this.revalidate();
           this.repaint();
       }
       else{
           updatePuzzle(update);
           
       }
   }
}

