/**
 * Handles all Graphics
 * 
 * @author Chase Carnaroli and Parth Nobel
 * @version 0.0
 */

import java.awt.*;          // import Container
import java.util.*;         // import ArrayList
import javax.swing.*;       // import JFrame
import javax.swing.border.*;
import java.awt.event.*;    // import event listener

public class View extends JFrame
{
    private static final long serialVersionUID = 1L;
    
    // instance variables
    private Model myGame;
    private Controller control;
    private static final int WIDTH = 960;
    private static final int HEIGHT = 720;
    public static final int JBUTTONHEIGHT = 80, JBUTTONWIDTH = 80;
    private final int NUM_ROWS, NUM_COLS, NUM_ATTACKERS;
    private JButton[][] boardArray;
    private JButton[] store;
    private JLabel goldLabel;
    private JProgressBar progressBar;
    private JLayeredPane boardUI;

    /**
     * Constructor for objects of class Model
     */
    public View(Model game, Controller control)
    {
        super("Ant Defense");
        // initialise instance variables
        myGame = game;
        NUM_ROWS = game.getNumRows();
        NUM_COLS = game.getNumCols();
        NUM_ATTACKERS = game.getNumAttackers();
        this.control = control;

        // set up window
        setSize(WIDTH, HEIGHT);                                                 // sets size in pixels
        setResizable(false);                                                    // makes it resizable or not (true == resizable screen)
        this.getContentPane().setLayout(null);                                  // this allows us to place components wherever we want on the screen

        Point origin = new Point(0,0);  // variable for the origin

        /**CODE FOR THE BOARD ON THE SCREEN**/
        // Create board
        boardUI = new JLayeredPane();                                  // constructs boardUI pane
        Dimension boardSize = new Dimension(NUM_COLS*80, NUM_ROWS*80);  // dimensions of the board
        int boardUIxPos = origin.x+(WIDTH/12);                                      // x position of boardUI
        int boardUIyPos = origin.y+(HEIGHT/5);                                      // y position of the boardUI

        // set position of board
        boardUI.setBounds(boardUIxPos, boardUIyPos, (int)boardSize.getWidth(), (int)boardSize.getHeight());     // puts boardUI at (x,y) and sets width/height

        // add buttons to board
        boardArray = new JButton[NUM_ROWS][NUM_COLS];           // instantiates boardArray

        // places buttons on boardArray
        for(int r = 0; r < NUM_ROWS; r++){
            for (int c = 0; c < NUM_COLS; c++)
            {

                boardArray[r][c] = new JButton();                   // instantiate each JButton with a row/col label
                boardArray[r][c].setBounds(JBUTTONWIDTH*c, JBUTTONHEIGHT*r, JBUTTONWIDTH, JBUTTONHEIGHT);
                boardUI.add(boardArray[r][c]);                      // add the JButton to the pane
                BoardMouseHandler bmh = new BoardMouseHandler(r, c);
                boardArray[r][c].addMouseListener(bmh);    // register the JButton with the mouse handler
                boardArray[r][c].setContentAreaFilled(false);     // Makes button transparent
                // Code below is not needed but we're keeping it for reference later
                //boardArray[r][c].setOpaque(false);              // Dont know why this is needed
                //boardArray[r][c].setBorderPainted(true);        // Keeps border outlines
            }
        }
        
        /**CODE FOR THE STORE ON THE SCREEN**/
        // Create store
        JLayeredPane storeUI = new JLayeredPane();  // constructs storeUI pane                        
        int storeUIxPos = origin.x+(WIDTH/24);      // x position of storeUI
        int storeUIyPos = origin.y+(HEIGHT/20);     // y position of storeUI

        StoreItem[] storeItems = StoreItem.values();
        // set position of storeUI
        storeUI.setBounds(storeUIxPos, storeUIyPos, JBUTTONWIDTH * storeItems.length, JBUTTONHEIGHT+10);   // puts boardUI at (x,y) and sets width/height

        // add buttons to store
        store = new JButton[storeItems.length];                   // instantiates store

        // places buttons on store
        for(int r = 0; r < storeItems.length; r++){
            store[r] = new JButton();                   // instantiate each JButton with a row/col label
            store[r].setIcon(storeItems[r].INIT_IMAGE);
            store[r].addMouseListener(new StoreMouseHandler(storeItems[r]));    // register the JButton with the mouse handler
            store[r].setBounds(r*JBUTTONWIDTH, 0, JBUTTONWIDTH, JBUTTONHEIGHT);
            storeUI.add(store[r]);                      // add the JButton to the pane
            JLabel jl = new JLabel("" + storeItems[r].COST);
            jl.setBounds(r*JBUTTONWIDTH, JBUTTONHEIGHT, JBUTTONWIDTH, 10);
            storeUI.add(jl);
        }

        /* CODE FOR GOLD LABEL */
        // Create Gold Label
        goldLabel = new JLabel("0", JLabel.LEFT);
        goldLabel.setBounds(175, 8, 20000, 20); // NOTE: EVERYTHING IN THIS LINE IS A MAGIC NUMBER

        /* CODE FOR THE PROGRESS BAR */
        // Create progress bar
        progressBar = new JProgressBar(0, NUM_ATTACKERS);                                // constructs progressBar
        Dimension progressBarSize = new Dimension((int)(boardSize.getWidth()/3), HEIGHT/20);   // dimensions of the progressBar

        // set coordinate
        int progressBarxPos = boardUIxPos + (int)boardSize.getWidth() - (int)progressBarSize.getWidth();    // x position of progress bar
        //int progressBaryPos = boardUIyPos + (int)boardSize.getHeight() + HEIGHT/11;                         // y position of progress bar
        int progressBaryPos = 19*HEIGHT/20 - (int)progressBarSize.getHeight();                         // y position of progress bar
        // set position of progressBar
        progressBar.setBounds(progressBarxPos, progressBaryPos, (int)progressBarSize.getWidth(), (int)progressBarSize.getHeight()); // puts progressBar at (x,y) and sets width/height

        /* CODE FOR THE BACKGROUND */
        JLabel backgroundLabel;
        ImageIcon backgroundImage = new ImageIcon("AntDefenseUI.png", "Background");
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 960, 720);

        // Set window size and show window
        add(progressBar);       // adds progressBar to the screen     
        add(boardUI);           // adds boardUI to the screen
        add(storeUI);           // adds storeUI to the screen
        add(goldLabel);         // adds goldLabel
        add(backgroundLabel);   // adds background image
        setVisible(true);       // makes the screen visable

        // needed to close application
        addWindowListener(new java.awt.event.WindowAdapter() 
            {
                public void windowClosing(WindowEvent evt) {
                    System.exit(0);
                }
            }
        );
    }

    public void moveCharacter(Character thing)
    {
        Location loc = thing.getLoc();

        thing.getJLabel().setLocation(loc.getPoint());
    }

    /**
     * true denotes Player Win
     * false denotes Player loss
     */
    public void announceWinOrLoss(boolean wOrL)
    {
        String[] possibleValues = { "OK"};
        Integer selectedValue = JOptionPane.showOptionDialog(null,
                (wOrL)?"You won": "You lost", "Ant Defense",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null,  possibleValues, possibleValues[0]);
 
    }

    public void addCharacter(Character thing)
    {
        Location loc = thing.getLoc();
        // TODO: Generate JLabel
        ImageIcon imgIcon = thing.getInitialImageIcon();
        JLabel jl = new JLabel(imgIcon);
        jl.setBounds(loc.getX(), loc.getY(), imgIcon.getIconWidth(), imgIcon.getIconHeight());

        boardUI.add(jl);
        thing.setJLabel(jl);
    }

    public void removeCharacter(Character thing)
    {
        thing.getJLabel().setVisible(false);
        boardUI.remove(thing.getJLabel());
    }

    public void resetField()
    {

    }

    public void endProgram()
    {
        System.exit(0);
    }

    private class StoreMouseHandler extends MouseAdapter
    {
        public StoreItem si;

        public StoreMouseHandler(StoreItem si)
        {
            this.si = si;
        }
        
        public void mouseClicked(MouseEvent event)
        {
            control.pickDefender(si);
        }
    }
    
    private class BoardMouseHandler extends MouseAdapter
    {
        public int row, col;
        public BoardMouseHandler(int r, int c)
        {
            row = r;
            col = c;
        }
        public void mouseClicked(MouseEvent event)
        {
            Location loc = new Location(col*JBUTTONWIDTH , row*JBUTTONHEIGHT);
            control.placeDefender(loc);
        }
    }

    public void setProgress(int prog)
    {
        progressBar.setValue(prog);
    }
    
    public void setGoldLabel(int gold)
    {
        goldLabel.setText("" + gold);
    }
}
