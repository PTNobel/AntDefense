/**
 * Write a description of class Model here.
 * 
 * @author Chase Carnaroli
 * @version 0.0
 */

import java.awt.*;          // import Container
import java.util.*;         // import ArrayList
import javax.swing.*;       // import JFrame
import javax.swing.border.*;
import java.awt.event.*;    // import event listener

public class View extends JFrame
{
    // instance variables
    private Model myGame;
    private static final int WIDTH = 960;
    private static final int HEIGHT = 720;
    public static int jbuttonHeight = 1, jbuttonWidth = 1;
    private final int NUM_ROWS, NUM_COLS, NUM_STORE_ITEMS, NUM_ATTACKERS;
    private JButton[][] boardArray;
    private JButton[] store;
    private JProgressBar progressBar;

    /**
     * Constructor for objects of class Model
     */
    public View(Model game)
    {
        super("AntDefense");
        // initialise instance variables
        myGame = game;
        NUM_ROWS = game.getNumRows();
        NUM_COLS = game.getNumCols();
        NUM_ATTACKERS = game.getNumAttackers();
        NUM_STORE_ITEMS = game.getNumStoreItems();

        MouseHandler mouseHandler = new MouseHandler();     // creates a mouseHandler

        // set up window
        setSize(WIDTH, HEIGHT);                                                 // sets size in pixels
        setResizable(false);                                                    // makes it resizable or not (true == resizable screen)
        this.getContentPane().setLayout(null);                                  // this allows us to place components wherever we want on the screen

        Point origin = new Point(0,0);  // variable for the origin

        /**CODE FOR THE BOARD ON THE SCREEN**/
        // Create board
        JLayeredPane boardUI = new JLayeredPane();                                  // constructs boardUI pane
        Dimension boardSize = new Dimension(WIDTH-(WIDTH/6), HEIGHT-(2*HEIGHT/5));  // dimensions of the board
        int boardUIxPos = origin.x+(WIDTH/12);                                      // x position of boardUI
        int boardUIyPos = origin.y+(HEIGHT/5);                                      // y position of the boardUI

        // set position of board
        boardUI.setBounds(boardUIxPos, boardUIyPos, (int)boardSize.getWidth(), (int)boardSize.getHeight());     // puts boardUI at (x,y) and sets width/height

        // add buttons to board
        boardUI.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));  // creates grid layout for the buttons

        boardArray = new JButton[NUM_ROWS][NUM_COLS];           // instantiates boardArray

        // places buttons on boardArray
        for(int r = 0; r < NUM_ROWS; r++){
            for (int c = 0; c < NUM_COLS; c++)
            {
                boardArray[r][c] = new JButton();                   // instantiate each JButton with a row/col label
                boardUI.add(boardArray[r][c]);                      // add the JButton to the pane
                boardArray[r][c].addMouseListener(mouseHandler);    // register the JButton with the mouse handler

                boardArray[r][c].setContentAreaFilled(false);     // Makes button transparent
                // Code below is not needed but we're keeping it for reference later
                //boardArray[r][c].setOpaque(false);              // Dont know why this is needed
                //boardArray[r][c].setBorderPainted(true);        // Keeps border outlines
            }
        }
        // Let's share the constants Location needs
        jbuttonHeight = boardArray[0][0].getHeight();
        jbuttonWidth = boardArray[0][0].getWidth();
        
        /**CODE FOR THE STORE ON THE SCREEN**/
        // Create store
        JLayeredPane storeUI = new JLayeredPane();                                                  // constructs storeUI pane                        
        Dimension storeItemSize = new Dimension((HEIGHT/5)-(HEIGHT/10), (HEIGHT/5)-(HEIGHT/10));    // dimensions of each storeItem button
        int storeUIxPos = origin.x+(WIDTH/24);                                                      // x position of storeUI
        int storeUIyPos = origin.y+(HEIGHT/20);                                                     // y position of storeUI

        // set position of storeUI
        storeUI.setBounds(storeUIxPos, storeUIyPos, (int)storeItemSize.getWidth() * NUM_STORE_ITEMS, (int)storeItemSize.getHeight());     // puts boardUI at (x,y) and sets width/height

        // add buttons to store
        storeUI.setLayout(new GridLayout(1, NUM_STORE_ITEMS));  // creates grid layout for the buttons

        store = new JButton[NUM_STORE_ITEMS];                   // instantiates store

        // places buttons on store
        for(int r = 0; r < NUM_STORE_ITEMS; r++){
            store[r] = new JButton();                   // instantiate each JButton with a row/col label
            storeUI.add(store[r]);                      // add the JButton to the pane
            store[r].addMouseListener(mouseHandler);    // register the JButton with the mouse handler

            // store[r].setIcon(game.getStore[r].getIcon());
        }

        /**CODE FOR THE PROGRESS BAR**/
        // Create progress bar
        progressBar = new JProgressBar(0,NUM_ATTACKERS);                                // constructs progressBar
        Dimension progressBarSize = new Dimension((int)(boardSize.getWidth()/3), HEIGHT/10);   // dimensions of the progressBar

        // set coordinate
        int progressBarxPos = boardUIxPos + (int)boardSize.getWidth() - (int)progressBarSize.getWidth();    // x position of progress bar
        //int progressBaryPos = boardUIyPos + (int)boardSize.getHeight() + HEIGHT/11;                         // y position of progress bar
        int progressBaryPos = 19*HEIGHT/20 - (int)progressBarSize.getHeight();                         // y position of progress bar
        // set position of progressBar
        progressBar.setBounds(progressBarxPos, progressBaryPos, (int)progressBarSize.getWidth(), (int)progressBarSize.getHeight()); // puts progressBar at (x,y) and sets width/height

        // Set window size and show window
        add(progressBar);       // adds progressBar to the screen     
        add(boardUI);           // adds boardUI to the screen
        add(storeUI);           // adds storeUI to the screen
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

    public void addCharacter(Character thing){

    }

    public void removeCharacter(Character thing){

    }

    public void resetField(){

    }

    public void endProgram(){
        System.exit(0);
    }

    // THE CODE BELOW THIS IS NOT DONE
    private class MouseHandler extends MouseAdapter
    {
        public int row, col;
    }
}
