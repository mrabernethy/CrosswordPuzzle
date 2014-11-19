package crosswordMVC;

/**
 * The main class.
 * 
 * Adapted from PDC Lab solution.
 * 
 */
public class CWMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CWView cwview = new CWView();
        CWModel cwmodel = new CWModel();
        CWControl cwcontrol = new CWControl(cwview, cwmodel);
        cwmodel.addObserver(cwview);
        cwview.setVisible(true);
    }
    
}
