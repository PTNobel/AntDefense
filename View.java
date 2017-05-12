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
    private Dimension screenSize;
    private final int NUM_ROWS, NUM_COLS, NUM_STORE_ITEMS;
    private JButton[][] boardArray;
    private JButton[] store;

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
        NUM_STORE_ITEMS = game.getNumStoreItems();

        MouseHandler mouseHandler = new MouseHandler();     // creates a mouseHandler

        // set up window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     // gets size of the screen and saves as a Dimension
        int width = (int)screenSize.getWidth();                                 // gets width of screen
        int height = (int)screenSize.getHeight();                               // gets height of screen
        setSize(width, height);                                                 // sets size in pixels
        setResizable(false);                                                    // makes it resizable or not (true == resizable screen)
        this.getContentPane().setLayout(null);                                  // this allows us to place components wherever we want on the screen

        Point origin = new Point(0,0);  // variable for the origin

        /**CODE FOR THE BOARD ON THE SCREEN**/
        // Create board
        JLayeredPane boardUI = new JLayeredPane();                                                          // constructs boardUI pane          
        boardUI.setBounds(origin.x+(width/12), origin.y+(height/5), width-(width/6), height-(2*height/5));  // puts boardUI at (x,y) and sets width/height

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

        
        /**CODE FOR THE STORE ON THE SCREEN**/
        // Create store
        JLayeredPane storeUI = new JLayeredPane();                                                                                              // constructs storeUI pane                        
        Dimension storeItem = new Dimension((height/5)-(height/10), (height/5)-(height/10));                                                    // dimensions of each storeItem button
        storeUI.setBounds(origin.x+(width/24), origin.y+(height/20), (int)storeItem.getWidth() * NUM_STORE_ITEMS, (int)storeItem.getHeight());  // puts boardUI at (x,y) and sets width/height

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

        // Set window size and show window
        add(boardUI);          // adds boardUI to the screen
        add(storeUI);          // adds storeUI to the screen
        setVisible(true);      // makes the screen visable

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