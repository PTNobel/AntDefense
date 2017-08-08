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

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * GameView is the JRootPane for the actual game board.
 *
 * METHODS:
 *  static final int JBUTTONHEIGHT
 *  static final int JBUTTONWIDTH
 *  GameView(Window window)
 *  void setControl(Controller control)
        Sets control
 *  void moveCharacter(Character thing)
        Moves a character, updating its location on the screen
 *  void announceWinOrLoss(boolean wOrL)
        Returns whether the player won or loss
 *  void addCharacter(Character thing)
        Adds character to the screen
 *  void removeCharacter(Character thing)
        Removes character from the screen
 *  void setStoreButtonPressed(StoreItem si, boolean pressed)
        Changes store buttons apperance to indicate that it is selected
 *  void setStoreEnabled(boolean enabled)
        Enables or disables the store buttons depending on the value of enabled
 *  void setBoardEnabled(boolean enabled)
        Enables or disables the board buttons depending on the value of enabled
 *  void enableButtons(boolean enabled)
        Enables or disables the store buttons, board buttons, and pause button
        depending on the value of enabled
 *  void setMaxProgress(int max)
        Sets max value of progress bar
 *  void setProgress(int prog)
        Updates value of the progress bar
 *  void setGoldLabel(int gold)
        Updates gold label on the screen
 *  void startGame()
        Starts game
 *  void resetField()
        Resets the game, clearing the characters on the screen
 *  void unPause()
        Unpauses the game
 *
 */
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
    private JPanel pauseMenu, gameOverMenu;
    private JLabel gameOverMessage;
    private Window window;
    private String gameResult = "Game Over";

    /**
     * Constructor for objects of class Model
     */
    public GameView(Window window)
    {
        // initialise instance variables
        this.window = window;
        // These are constants.
        NUM_ROWS = Model.getNumRows();
        NUM_COLS = Model.getNumCols();
        // Sane default. We'll change it later.
        NUM_ATTACKERS = 1;

        // We should fill the entire Window
        setSize(Window.WIDTH, Window.HEIGHT);
        // We want to freely place things.
        setLayout(null);



        /**CODE FOR THE BOARD ON THE SCREEN**/
        // Create board
        // We need things on top of each other without anything misbehaving, so
        // we'll go with a JLayeredPane
        boardUI = new JLayeredPane();
        Dimension boardSize = new Dimension(NUM_COLS*JBUTTONWIDTH, NUM_ROWS*JBUTTONHEIGHT);
        // Magic numbers used to choose the boardUI's x and y positions.
        int boardUIxPos = Window.WIDTH/12;
        int boardUIyPos = Window.HEIGHT/5;

        // set position of board
        boardUI.setBounds(boardUIxPos, boardUIyPos, (int)boardSize.getWidth(), (int)boardSize.getHeight());

        // add buttons to board
        boardArray = new JButton[NUM_COLS][NUM_ROWS];

        // places buttons on boardArray
        for(int r = 0; r < NUM_ROWS; r++)
        {
            for (int c = 0; c < NUM_COLS; c++)
            {
                // Let's make the button
                boardArray[c][r] = new JButton();
                // Set position and size
                boardArray[c][r].setBounds(JBUTTONWIDTH*c, JBUTTONHEIGHT*r, JBUTTONWIDTH, JBUTTONHEIGHT);
                boardUI.add(boardArray[c][r]);
                // The BMH is used to place defenders
                BoardMouseHandler bmh = new BoardMouseHandler(c, r);
                boardArray[c][r].addActionListener(bmh);
                // Let's make the buttons transparent
                boardArray[c][r].setContentAreaFilled(false);
            }
        }



        /**CODE FOR THE STORE ON THE SCREEN**/
        // Create store
        JLayeredPane storeUI = new JLayeredPane();
        // More magic numbers to place storeUI.
        int storeUIxPos = Window.WIDTH/24;
        int storeUIyPos = Window.HEIGHT/20;

        // Let's ask the enum listing everything that belongs in the store,
        // for that list
        storeItems = StoreItem.values();
        // set position of storeUI
        storeUI.setBounds(storeUIxPos, storeUIyPos, JBUTTONWIDTH * storeItems.length, JBUTTONHEIGHT+10);

        store = new JButton[storeItems.length];

        // places buttons on store
        for(int i = 0; i < storeItems.length; i++)
        {
            store[i] = new JButton();
            // Set the icon to that of the store item
            store[i].setIcon(storeItems[i].INIT_IMAGE);
            store[i].addActionListener(new StoreMouseHandler(storeItems[i], store[i]));
            // Upper left corner should be at (i buttons, 0)
            store[i].setBounds(i*JBUTTONWIDTH, 0, JBUTTONWIDTH, JBUTTONHEIGHT);
            // add the JButton to the pane
            storeUI.add(store[i]);
            // Content of the jl should be the numerical representation of the cost
            JLabel jl = new JLabel("" + storeItems[i].COST);
            // the JLabel goes right below the button
            jl.setBounds(i*JBUTTONWIDTH, JBUTTONHEIGHT, JBUTTONWIDTH, 10);
            storeUI.add(jl);
        }



        /** CODE FOR GOLD LABEL **/
        // Create Gold Label, we'll default to zero gold, Controller should
        // set the real number before the user sees the screen
        goldLabel = new JLabel("0", JLabel.LEFT);
        // MAGIC NUMBERS, based off the background image
        goldLabel.setBounds(175, 8, 20000, 20);



        /** CODE FOR THE PROGRESS BAR **/
        // Create progress bar
        // realize that NUM_ATTACKERS is still set to 1.
        progressBar = new JProgressBar(0, NUM_ATTACKERS);
        Dimension progressBarSize = new Dimension((int)(boardSize.getWidth()/3), Window.HEIGHT/20);

        // set coordinate
        int progressBarxPos = boardUIxPos + (int)boardSize.getWidth() - (int)progressBarSize.getWidth();
        int progressBaryPos = 19*Window.HEIGHT/20 - (int)progressBarSize.getHeight();
        // set position of progressBar
        progressBar.setBounds(progressBarxPos, progressBaryPos, (int)progressBarSize.getWidth(), (int)progressBarSize.getHeight());



        /** CODE FOR THE PAUSE BUTTON **/
        pauseButton = new JButton();
        Dimension pauseButtonSize = new Dimension(JBUTTONWIDTH, JBUTTONHEIGHT/2);
        pauseButton.setText("PAUSE");
        int pauseButtonX = boardUIxPos + (int)boardSize.getWidth() - JBUTTONWIDTH;
        int pauseButtonY = storeUIyPos;
        pauseButton.setBounds(pauseButtonX, pauseButtonY, (int)pauseButtonSize.getWidth(), (int)pauseButtonSize.getHeight());
        // If clicked open the pause menu
        pauseButton.addActionListener(new PauseListener());



        /** CODE FOR PAUSE MENU **/
        pauseMenu = new JPanel();
        // Allow arbitrary placement
        pauseMenu.setLayout(null);
        Dimension pauseMenuSize = new Dimension(Window.WIDTH/4,Window.HEIGHT/2);
        int pauseMenuUIxPos = Window.WIDTH/2 - (int)pauseMenuSize.getWidth()/2;          // x position of storeUI
        int pauseMenuUIyPos = Window.HEIGHT/2 - (int)pauseMenuSize.getHeight()/2;        // y position of storeUI
        //pauseMenu.setBackground(Color.GRAY);

        // set position of pauseMenu
        pauseMenu.setBounds(pauseMenuUIxPos, pauseMenuUIyPos, (int)pauseMenuSize.getWidth(), (int)pauseMenuSize.getHeight());   // puts boardUI at (x,y) and sets width/height

        // pause menu text
        JLabel gamePauseText = new JLabel("Game Paused", SwingConstants.CENTER);
        gamePauseText.setBounds(0,20, (int)pauseMenuSize.getWidth(), 30);
        pauseMenu.add(gamePauseText);

        Dimension buttonSize = new Dimension(100,40);
        int buttonMargin = 10;  // distance between buttons

        // resume button
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ResumeListener());
        // button location and size
        int resumeWidth = (int) buttonSize.getWidth();    // width
        int resumeHeight = (int) buttonSize.getHeight();  // height
        int resumeX = ((int)pauseMenuSize.getWidth() - resumeWidth)/2;                                    // x-position
        int resumeY = ((int)pauseMenuSize.getHeight() - resumeHeight)/2 - (resumeHeight + buttonMargin);  // y-position
        resumeButton.setBounds(resumeX, resumeY, resumeWidth, resumeHeight);
        pauseMenu.add(resumeButton);

        // reset button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetListener());
        // button location and size
        int resetWidth = (int) buttonSize.getWidth();    // width
        int resetHeight = (int) buttonSize.getHeight();  // height
        int resetX = ((int)pauseMenuSize.getWidth() - resetWidth)/2;    // x-position
        int resetY = ((int)pauseMenuSize.getHeight() - resetHeight)/2;  // y-position
        resetButton.setBounds(resetX, resetY, resetWidth, resetHeight);
        pauseMenu.add(resetButton);

        // quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitListener());
        // button location and size
        int quitWidth = (int) buttonSize.getWidth();    // width
        int quitHeight = (int) buttonSize.getHeight();  // height
        int quitX = ((int)pauseMenuSize.getWidth() - quitWidth)/2;                               // x-position
        int quitY = ((int)pauseMenuSize.getHeight() - quitHeight)/2 + quitHeight + buttonMargin; // y-position
        quitButton.setBounds(quitX, quitY, quitWidth, quitHeight);
        pauseMenu.add(quitButton);


        /** CODE FOR GAME OVER SCREEN **/
        gameOverMenu = new JPanel();
        gameOverMenu.setLayout(null);
        Dimension gameOverSize = new Dimension(500,200);
        int gameOverX = Window.WIDTH/2 - (int)gameOverSize.getWidth()/2;
        int gameOverY = Window.HEIGHT/2 - (int)gameOverSize.getHeight()/2;
        gameOverMenu.setBounds(gameOverX, gameOverY, (int)gameOverSize.getWidth(), (int)gameOverSize.getHeight());
        gameOverMenu.setBackground(Color.GRAY);

        // game over message
        gameOverMessage = new JLabel(gameResult, SwingConstants.CENTER);
        gameOverMessage.setFont(new Font("Serif", Font.BOLD, 20));
        gameOverMessage.setBounds(0, 20, (int)gameOverSize.getWidth(), 30);
        gameOverMenu.add(gameOverMessage);

        // game over choices buttons
        Dimension gameOverButtonSize = new Dimension(100,40);
        int gameOverButtonMargin = 10;

        // different difficulty button
        JButton changeDiffButton = new JButton("Change Difficulty");
        changeDiffButton.addActionListener(new changeDiffListener());
        // button location and size
        int changeDiffWidth = (int) gameOverButtonSize.getWidth()+50;    // width
        int changeDiffHeight = (int) gameOverButtonSize.getHeight();  // height
        int changeDiffX = ((int)gameOverSize.getWidth() - changeDiffWidth)/2;      // x-position
        int changeDiffY = ((int)gameOverSize.getHeight() - changeDiffHeight)/2;  // y-position
        changeDiffButton.setBounds(changeDiffX, changeDiffY, changeDiffWidth, changeDiffHeight);
        gameOverMenu.add(changeDiffButton);

        // reset button
        JButton gameOverResetButton = new JButton("Restart");
        gameOverResetButton.addActionListener(new ResetListener());
        // button location and size
        int gameOverResetWidth = (int) gameOverButtonSize.getWidth();    // width
        int gameOverResetHeight = (int) gameOverButtonSize.getHeight();  // height
        int gameOverResetX = ((int)gameOverSize.getWidth() - resumeWidth)/2 - (changeDiffWidth + gameOverButtonMargin);      // x-position
        int gameOverResetY = ((int)gameOverSize.getHeight() - gameOverResetHeight)/2;  // y-position
        gameOverResetButton.setBounds(gameOverResetX, gameOverResetY, gameOverResetWidth, gameOverResetHeight);
        gameOverMenu.add(gameOverResetButton);

        // exit button
        JButton exitButton = new JButton("Main Menu");
        exitButton.addActionListener(new QuitListener());
        // button location and size
        int exitWidth = (int) gameOverButtonSize.getWidth() + 20;    // width
        int exitHeight = (int) gameOverButtonSize.getHeight();  // height
        int exitX = ((int)gameOverSize.getWidth() - exitWidth)/2 + (changeDiffWidth + gameOverButtonMargin);      // x-position
        int exitY = ((int)gameOverSize.getHeight() - exitHeight)/2;  // y-position
        exitButton.setBounds(exitX, exitY, exitWidth, exitHeight);
        gameOverMenu.add(exitButton);



        /** CODE FOR THE BACKGROUND **/
        JLabel backgroundLabel;
        ImageIcon backgroundImage = PictureLoader.antDefenseUI;
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 960, 720);

        // Set window size and show window
        add(progressBar);             // adds progressBar to the screen
        add(gameOverMenu);            // adds game over menu to screen
        gameOverMenu.setVisible(false); // hides game over menu
        add(pauseMenu);               // adds pauseMenu
        pauseMenu.setVisible(false);  // hides pause menu
        add(boardUI);                 // adds boardUI to the screen
        add(storeUI);                 // adds storeUI to the screen
        add(goldLabel);               // adds goldLabel
        add(pauseButton);             // adds pauseButton
        add(backgroundLabel);         // adds background image
        //setVisible(true);       // makes the screen visable


    }

    /* METHODS */

    // Sets control
    public void setControl(Controller control)
    {
        this.control = control;
    }

    // Moves a character, updating its location on the screen
    public void moveCharacter(Character thing)
    {
        Location loc = thing.getLoc();

        thing.getJLabel().setLocation(loc.getPoint());
    }

    /**
     *  Returns whether the player won or loss
     *
     *  post: true denotes Player Win, false denotes Player loss
     */
    public void announceWinOrLoss(boolean wOrL)
    {
        if(wOrL)
        {
          gameResult = "You Won!";
        } else {
          gameResult = "You Loss!";
        }

        enableButtons(false);
        gameOverMessage.setText(gameResult);
        gameOverMenu.setVisible(true);
        /*
        String[] possibleValues = {"I'll try a different level", "I'll try again", "I'm done"};
        Integer selectedValue = JOptionPane.showOptionDialog(null,
                ((wOrL)?"You won.": "You lost.") + " What next?", "Ant Defense",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null,  possibleValues, possibleValues[2]);

        switch (selectedValue)
        {
            case 1: resetField();
                    control.loop();
                break;
            case 2:
            default:
                endProgram();
                break;
        }*/
    }

    // Adds character to the screen
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

    // Removes character from the screen
    public void removeCharacter(Character thing)
    {
        thing.getJLabel().setVisible(false);
        boardUI.remove(thing.getJLabel());
    }

    // Changes store buttons apperance to indicate that it is selected
    public void setStoreButtonPressed(StoreItem si, boolean pressed)
    {
        JButton button = null;
        for (int r = 0; r < store.length; r++)
        {
            store[r].setBorder(new LineBorder(Color.GRAY));
            if (storeItems[r].equals(si))
            {
                button = store[r];
            }
        }

        if(pressed && button != null)
        {
            button.setBorder(new LineBorder(Color.GRAY, 3));
        }
    }

    // Enables or disables the store buttons depending on the value of enabled
    public void setStoreEnabled(boolean enabled)
    {
        for(JButton storeButton: store)
        {
            storeButton.setEnabled(enabled);
        }
    }

    // Enables or disables the board buttons depending on the value of enabled
    public void setBoardEnabled(boolean enabled)
    {
        for(JButton[] boardRow: boardArray)
        {
            for (JButton boardButton: boardRow)
                boardButton.setEnabled(enabled);
        }
    }

    // Enables or disables the store buttons, board buttons, and pause button depending on the value of enabled
    public void enableButtons(boolean enabled)
    {
      setBoardEnabled(enabled);
      setStoreEnabled(enabled);
      pauseButton.setEnabled(enabled);
    }

    // Sets max value of progress bar
    public void setMaxProgress(int max)
    {
        progressBar.setMaximum(max);
    }

    // Updates value of the progress bar
    public void setProgress(int prog)
    {
        progressBar.setValue(prog);
    }

    // Updates gold label on the screen
    public void setGoldLabel(int gold)
    {
        goldLabel.setText("" + gold);
    }

    // Starts game
    public void startGame()
    {
        control.loop();
    }

    // Resets the game, clearing the characters on the screen
    public void resetField()
    {
        gameOverMenu.setVisible(false);
        control.resetGame();
    }

    // Unpauses the game
    public void unPause()
    {
        pauseMenu.setVisible(false);
        pauseButton.setEnabled(true);
        setStoreEnabled(true);
        setBoardEnabled(true);
        control.pauseGame(false);
    }

    // Ends program
    // post: Closes window
    public void endProgram()
    {
        System.exit(0);
    }

    /* PRIVATE MOUSE HANDLERS */

    private class StoreMouseHandler implements ActionListener
    {
        public StoreItem si;
        public JButton button;

        public StoreMouseHandler(StoreItem si, JButton button)
        {
            this.si = si;
            this.button = button;
        }

        public void actionPerformed(ActionEvent event)
        {
            control.pickDefender(si);
        }
    }

    private class BoardMouseHandler implements ActionListener
    {
        private int row, col;

        public BoardMouseHandler(int c, int r)
        {
            col = c;
            row = r;
        }

        public void actionPerformed(ActionEvent event)
        {
            Location loc = new Location(col*JBUTTONWIDTH , row*JBUTTONHEIGHT);
            control.placeDefender(loc);
        }
    }

    private class PauseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(!pauseMenu.isVisible())
            {
                pauseButton.setEnabled(false);
                pauseMenu.setVisible(true);
                setStoreEnabled(false);
                setBoardEnabled(false);
                control.pauseGame(true);
            }
        }
    }

    private class ResumeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            unPause();
        }
    }

    private class ResetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            resetField();
        }
    }

    private class changeDiffListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            LevelSelector ls = new LevelSelector(window);
            window.setContentPane(ls);
        }
    }

    private class QuitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            control.quitGame();
            window.switchToWelcomeScreen();
        }
    }
}
