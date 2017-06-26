/*
 *
 * Copyright 2017 Parth Nobel, Chase Carnaroli, Tiffany Nguyen, and Ignatius Widjaja
 *
 * This file is part of Ant Defense.
 *
 * Ant Defense is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Ant Defense is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Ant Defense.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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

public class GameView extends JRootPane
{
    private static final long serialVersionUID = 1L;
    
    // instance variables
    private Controller control;
    public static final int JBUTTONHEIGHT = 80, JBUTTONWIDTH = 80;
    private final int NUM_ROWS, NUM_COLS, NUM_ATTACKERS;
    private JButton[][] boardArray;
    private JButton[] store;
    private StoreItem[] storeItems;
    private JLabel goldLabel;
    private JProgressBar progressBar;
    private JLayeredPane boardUI;
    private JButton pauseButton;
    private JPanel pauseMenu;

    /**
     * Constructor for objects of class Model
     */
    public GameView()
    {
        // initialise instance variables
        NUM_ROWS = Model.getNumRows();
        NUM_COLS = Model.getNumCols();
        NUM_ATTACKERS = 1;
        this.control = control;

        Point origin = new Point(0,0);  // variable for the origin

        setSize(Window.WIDTH, Window.HEIGHT);
        setLayout(null);
        /**CODE FOR THE BOARD ON THE SCREEN**/
        // Create board
        boardUI = new JLayeredPane();                                  // constructs boardUI pane
        Dimension boardSize = new Dimension(NUM_COLS*80, NUM_ROWS*80);  // dimensions of the board
        int boardUIxPos = origin.x+(Window.WIDTH/12);                                      // x position of boardUI
        int boardUIyPos = origin.y+(Window.HEIGHT/5);                                      // y position of the boardUI

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
        int storeUIxPos = origin.x+(Window.WIDTH/24);      // x position of storeUI
        int storeUIyPos = origin.y+(Window.HEIGHT/20);     // y position of storeUI

        storeItems = StoreItem.values();
        // set position of storeUI
        storeUI.setBounds(storeUIxPos, storeUIyPos, JBUTTONWIDTH * storeItems.length, JBUTTONHEIGHT+10);   // puts boardUI at (x,y) and sets width/height

        // add buttons to store
        store = new JButton[storeItems.length];                   // instantiates store

        // places buttons on store
        for(int r = 0; r < storeItems.length; r++){
            store[r] = new JButton();                   // instantiate each JButton with a row/col label
            store[r].setIcon(storeItems[r].INIT_IMAGE);
            store[r].addMouseListener(new StoreMouseHandler(storeItems[r], store[r]));    // register the JButton with the mouse handler
            store[r].setBounds(r*JBUTTONWIDTH, 0, JBUTTONWIDTH, JBUTTONHEIGHT);
            storeUI.add(store[r]);                      // add the JButton to the pane
            JLabel jl = new JLabel("" + storeItems[r].COST);
            jl.setBounds(r*JBUTTONWIDTH, JBUTTONHEIGHT, JBUTTONWIDTH, 10);
            storeUI.add(jl);
        }

        /** CODE FOR GOLD LABEL **/
        // Create Gold Label
        goldLabel = new JLabel("0", JLabel.LEFT);
        goldLabel.setBounds(175, 8, 20000, 20); // NOTE: EVERYTHING IN THIS LINE IS A MAGIC NUMBER

        /** CODE FOR THE PROGRESS BAR **/
        // Create progress bar
        progressBar = new JProgressBar(0, NUM_ATTACKERS);                                // constructs progressBar
        Dimension progressBarSize = new Dimension((int)(boardSize.getWidth()/3), Window.HEIGHT/20);   // dimensions of the progressBar

        // set coordinate
        int progressBarxPos = boardUIxPos + (int)boardSize.getWidth() - (int)progressBarSize.getWidth();    // x position of progress bar
        //int progressBaryPos = boardUIyPos + (int)boardSize.getHeight() + Window.HEIGHT/11;                         // y position of progress bar
        int progressBaryPos = 19*Window.HEIGHT/20 - (int)progressBarSize.getHeight();                         // y position of progress bar
        // set position of progressBar
        progressBar.setBounds(progressBarxPos, progressBaryPos, (int)progressBarSize.getWidth(), (int)progressBarSize.getHeight()); // puts progressBar at (x,y) and sets width/height

        
        /** CODE FOR THE PAUSE BUTTON **/
        pauseButton = new JButton();
        Dimension pauseButtonSize = new Dimension(JBUTTONWIDTH, JBUTTONHEIGHT/2);
        pauseButton.setText("PAUSE");
        int pauseButtonX = boardUIxPos + (int)boardSize.getWidth() - JBUTTONWIDTH;
        int pauseButtonY = storeUIyPos;
        pauseButton.setBounds(pauseButtonX, pauseButtonY, (int)pauseButtonSize.getWidth(), (int)pauseButtonSize.getHeight());
        pauseButton.addMouseListener(new PauseListener());
        
        /** CODE FOR PAUSE MENU **/
        pauseMenu = new JPanel();
        pauseMenu.setLayout(null);
        Dimension pauseMenuSize = new Dimension(Window.WIDTH/5,Window.HEIGHT/5);
        int pauseMenuUIxPos = origin.x+(Window.WIDTH/2) - (int)pauseMenuSize.getWidth()/2;                      // x position of storeUI
        int pauseMenuUIyPos = origin.y+(Window.HEIGHT/2) - (int)pauseMenuSize.getHeight()/2;                     // y position of storeUI
        //pauseMenu.setBackground(Color.GRAY);
        
        // set position of pauseMenu
        pauseMenu.setBounds(pauseMenuUIxPos, pauseMenuUIyPos, (int)pauseMenuSize.getWidth(), (int)pauseMenuSize.getHeight());   // puts boardUI at (x,y) and sets width/height
        
        // pause menu text
        JLabel gamePauseText = new JLabel("Game Paused", SwingConstants.CENTER);
        gamePauseText.setBounds(0,20, (int)pauseMenuSize.getWidth(), 30);
        pauseMenu.add(gamePauseText);
        
        // resume button
        JButton resumeButton = new JButton("Resume");
        resumeButton.addMouseListener(new ResumeListener());
        resumeButton.setBounds((int)pauseMenuSize.getWidth()/2-50,(int)pauseMenuSize.getHeight()-2*(int)pauseMenuSize.getHeight()/3, 100,40);
        pauseMenu.add(resumeButton);
        
        // reset button
        JButton resetButton = new JButton("Reset");
        resetButton.addMouseListener(new ResetListener());
        resetButton.setBounds((int)pauseMenuSize.getWidth()/2-50,(int)pauseMenuSize.getHeight()-(int)pauseMenuSize.getHeight()/3, 100,40);
        pauseMenu.add(resetButton);
        
        
        
        /** CODE FOR THE BACKGROUND **/
        JLabel backgroundLabel;
        ImageIcon backgroundImage = PictureLoader.antDefenseUI;
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 960, 720);

        // Set window size and show window
        add(progressBar);       // adds progressBar to the screen     
        add(pauseMenu);         // adds pauseMenu
        pauseMenu.setVisible(false);
        add(boardUI);           // adds boardUI to the screen
        add(storeUI);           // adds storeUI to the screen
        add(goldLabel);         // adds goldLabel
        add(pauseButton);       // adds pauseButton
        add(backgroundLabel);   // adds background image
        //setVisible(true);       // makes the screen visable

        
    }

    public void setControl(Controller control)
    {
        this.control = control;
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
        String[] possibleValues = {"I'll try a different level", "I'll try again", "I'm done"};
        Integer selectedValue = JOptionPane.showOptionDialog(null,
                ((wOrL)?"You won.": "You lost.") + " What next?", "Ant Defense",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null,  possibleValues, possibleValues[2]);

        switch (selectedValue)
        {
            case 0: LevelGenerator lg = AntDefense.getDifficulty();
                    control.resetGame(lg);
                    control.loop();
                break;
            case 1: resetField();
                    control.loop();
                break;
            case 2:
            default:
                endProgram();
                break;
        }
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
    
    public void setStoreButtonPressed(StoreItem si, boolean pressed){
        JButton button = null;
        for (int r = 0; r < store.length; r++)
        {
            store[r].setBorder(new LineBorder(Color.GRAY));
            if (storeItems[r].equals(si))
            {
                button = store[r];
            }
        }

        if(pressed && button != null){
            button.setBorder(new LineBorder(Color.GRAY, 3));
        }
    }
    
    public void setStoreEnabled(boolean enabled){
        for(JButton storeButton: store){
            storeButton.setEnabled(enabled);
        }
    }

    public void setBoardEnabled(boolean enabled){
        for(JButton[] boardRow: boardArray)
        {
            for (JButton boardButton: boardRow)
                boardButton.setEnabled(enabled);
        }
    }

    public void resetField()
    {
        control.resetGame();
    }
    
    public void unPause(){
        pauseMenu.setVisible(false);
        pauseButton.setEnabled(true);
        setStoreEnabled(true);
        setBoardEnabled(true);
        control.pauseGame();
    }

    public void endProgram()
    {
        System.exit(0);
    }

    private class StoreMouseHandler extends MouseAdapter
    {
        public StoreItem si;
        public JButton button;

        public StoreMouseHandler(StoreItem si, JButton button)
        {
            this.si = si;
            this.button = button;
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

    private class ResetListener extends MouseAdapter
    {
        public ResetListener(){
            // nothing needed
        }
        
        public void mouseClicked(MouseEvent event){
            resetField();
            unPause();
        }
    }
    
    private class ResumeListener extends MouseAdapter
    {
        public ResumeListener(){
            // nothing needed
        }
        
        public void mouseClicked(MouseEvent event){
            unPause();
        }
    }
    
    private class PauseListener extends MouseAdapter
    {
        public PauseListener(){
           // nothing needed
        }

        public void mouseClicked(MouseEvent event){
            if(!pauseMenu.isVisible()){
                pauseButton.setEnabled(false);
                pauseMenu.setVisible(true);
                setStoreEnabled(false);
                setBoardEnabled(false);
                control.pauseGame();
            }
        }
    }

    public void setMaxProgress(int max)
    {
        progressBar.setMaximum(max);
    }
    
    public void setProgress(int prog)
    {
        progressBar.setValue(prog);
    }
    
    public void setGoldLabel(int gold)
    {
        goldLabel.setText("" + gold);
    }

    public void startGame()
    {
        control.loop();
    }
}
