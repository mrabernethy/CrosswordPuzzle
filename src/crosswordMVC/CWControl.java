package crosswordMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The controller class. 
 * 
 * Adapted from PDC Lab solution.
 */    
public class CWControl implements ActionListener{
    private CWModel model;
    private CWView view;

    
    /**
     * Constructor for control class.
     * 
     * Attaches the view and model, and sets the view controller as this controller.
     * 
     * @param theView the mvc view
     * @param theModel the mvc model
     */
    public CWControl(CWView theView, CWModel theModel){
        this.view = theView;
	this.model = theModel;
        view.setController(this);
        
    }
    
    /**
     * Handles events from button pushes.
     * Events include login, enter, quit, and clue selection.
     * 
     * @param e the action event 
     */
    public void actionPerformed(ActionEvent e) {
        try{    
            String eStr=e.getActionCommand();
            
            System.out.println(eStr);
            if(eStr.compareTo("Log in")==0){
                String un=view.unInput.getText();
                String pw=view.pwInput.getText();
                try {
                    model.checkid(un, pw);
                } catch (IOException ex) {
                    Logger.getLogger(CWControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(model.uInfo.currentscore);
                
            }            
            else if(eStr.compareTo("Enter")==0){
                String userSolution=view.userSolution.getText();
                String selectedClue = view.clueSelection.getText();
                model.checkAnswer(userSolution, selectedClue);
                System.out.println(model.uInfo.currentscore);
            }   
            else if(eStr.compareTo("Quit")==0){
                model.quitGame();
            }   
            else if (eStr.matches(".*\\d.*")){
                model.uInfo.currentClue = eStr;
                view.setClueSelection(eStr);
                model.highlightLetters();
            }
        }
        catch(NumberFormatException ex){
            System.out.println(ex);
	
        }
    }
}
