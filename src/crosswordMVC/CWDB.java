package crosswordMVC;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The database class.
 * 
 * Adapted from PDC Lab solution.
 */
public class CWDB {
    Connection conn=null;
    private String url="jdbc:derby:PlayersDB;create=true";  //url of the DB host
    private String dbusername="pdc";  //your DB username
    private String dbpassword="pdc";   //your DB password
    private String userid=null;
    
    /**
     * Creates the database.
     */    
    public void dbsetup()
    {
        try {
            
            conn=DriverManager.getConnection(url, dbusername, dbpassword);
            
            Statement statement = conn.createStatement();
            
            String tableName = "UserInfo";
            
            // If the table doesn't exist create it.
            if(!checkTableExisting(tableName))
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(12), password VARCHAR(12), score INT, puzzleNum INT, solvedWords LONG VARCHAR)");
             
            statement.close();
            
        } catch (Throwable e) {
            System.out.println("error " + e.getMessage());
            
        }
    }
    
    /**
     * Checks if the table exists.
     * 
     * @param newTableName the table name to check
     * @return true if table exists
     */
    private boolean checkTableExisting(String newTableName)
    {
        boolean flag=false;
        try {
            System.out.println("check existing tables.... ");
            String[] types={"TABLE"};
            DatabaseMetaData dbmd=conn.getMetaData();
            ResultSet rsDBMeta=dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while(rsDBMeta.next())
            {
                    String tableName=rsDBMeta.getString("TABLE_NAME");
                    if(tableName.compareToIgnoreCase(newTableName)==0)
                    {
                        System.out.println(tableName+"  is there");
                        flag=true;
                    }
                }
                if(rsDBMeta!=null)
                    rsDBMeta.close();
            }catch (SQLException ex) {
            }
        return flag; 
    }
    
    /**
     * Checks if the username exists in the database.
     * If user exists password must be correct. 
     * If user does not exist new username and password are added to database.
     * 
     * @param username the username
     * @param password the password
     * @return -99 if login failed, 0 for new user, or score for returning user
     */
    public int checkName(String username, String password){
        int score=-99;
        try {
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT userid, password, score FROM UserInfo "
                    + "WHERE userid = '"+username+"'");
            if(rs.next()) {
                String pass = rs.getString("password");
                System.out.println("***"+pass);
                System.out.println("found user");
                if(password.compareTo(pass)==0)
                {
                    score=rs.getInt("score");
                    this.userid=username;
                    
                }
            } 
            else{
                System.out.println("no such user");
                String initialSolvedWords="";
                statement.executeUpdate("INSERT INTO UserInfo "
                        + "VALUES('"+username+"', '"+password+"', 0, 0, '"+initialSolvedWords+"')");
                score=0;
                this.userid=username;
                
            }            
  
        } catch (SQLException ex) {
            Logger.getLogger(CWDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return score;
    
    }
    
    /**
     * Updates the database when a player exits the crossword.
     * 
     * @param score the current score
     * @param puzzleNum the current puzzle number
     * @param solvedWords the words solved in the current puzzle
     */
    void updateDB(int score, int puzzleNum, String solvedWords){
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE UserInfo SET score="+score+" WHERE userid='"+this.userid+"'");
            statement.executeUpdate("UPDATE UserInfo SET puzzleNum="+puzzleNum+" WHERE userid='"+this.userid+"'");
            statement.executeUpdate("UPDATE UserInfo SET solvedWords='"+solvedWords+"' WHERE userid='"+this.userid+"'");
            System.out.println(userid+score + " " + puzzleNum + " " + solvedWords);
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CWDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    /**
     * Gets the puzzle number the user is up to.
     * 
     * @return the puzzle number 
     */
    public int getPuzzleNum()
    {
        int puzzleNum = 0;
        
        try {
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT userid, puzzleNum FROM UserInfo "
                    + "WHERE userid = '"+this.userid+"'");
            if(rs.next()) {
                puzzleNum=rs.getInt("puzzleNum");
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(CWDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puzzleNum;
    }
    
    /**
     * Gets the words the user has solved in the current puzzle.
     * 
     * @return a string containing the solved words 
     */
    public String getSolvedWords()
    {
        String solvedWords=null;
        
        try {
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT userid, solvedWords FROM UserInfo "
                    + "WHERE userid = '"+this.userid+"'");
            if(rs.next()) {
                solvedWords=rs.getString("solvedWords");
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(CWDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return solvedWords;
    }
}
