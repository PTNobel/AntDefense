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

import java.util.List;

/**
 * This class controls the Model and the GameView.
 * METHODS
 *  Controller(Model model, GameView view)
 *  void loop()
 *      The main loop of the game
 *  void pickDefender(StoreItem si)
 *      Inidcates which store item was picked
 *  void placeDefender(Location loc)
 *      Places the previously indicated store item
 *  void pauseGame()
 *      Pauses the game
 *  void resetGame()
 *      Resets game, including the LevelGenerator
 *  void resetGame(LevelGenerator lg)
 *      Resets game with a new LevelGenerator
 *  void quitGame()
 *      Quits the game
 */
public class Controller
{
    private Model m;
    private GameView v;
    private boolean safeToAct;
    private boolean paused;
    private boolean gameQuit;

    public Controller(Model model, GameView view)
    {
        safeToAct = true;
        paused = false;
        gameQuit = false;
        m = model;
        setGameView(view);
    }

    private void setGameView(GameView view)
    {
        v = view;
        v.setControl(this);
        v.setGoldLabel(m.getGold());
        v.setMaxProgress(m.getNumAttackers());
    }

    // The main loop of the game
    public void loop()
    {
        boolean cakeEaten = false, gameWon = false;
        int turn = 0;
        long timeOfStartedMove;
        while (!cakeEaten && !gameWon && !gameQuit)
        {
            // Let's prohibit the other methods from acting.
            safeToAct = false;
            // Let's keep track of what time it is.
            timeOfStartedMove = System.nanoTime();
            // Here's the magic moment, we call the Model's act method.
            ActResult move = m.act();

            // Now we update the display wit the information in move

            // Let's clear out the dead characters
            for (Character charac: move.getDeadCharacters())
            {
                v.removeCharacter(charac);
            }

            // Let's add the new characters
            for (Character charac: move.getNewCharacters())
            {
                v.addCharacter(charac);
            }

            // Let's move the characters that moved
            for (Character charac: move.getMovedCharacters())
            {
                v.moveCharacter(charac);
            }

            // Finally let's update all our variables and the counters on view.
            v.setProgress(move.getProgress());
            v.setGoldLabel(move.getGold());
            gameWon = move.getGameWon();
            cakeEaten = move.getCakeEaten();
            turn++;
            System.out.println("Turn: " + turn + ", Time: " + System.nanoTime());

            // Allow the other methods to do work
            safeToAct = true;

            // We wait 10 milliseconds, so if another method wants to make it unsafe to act,
            // they can poll every 5ms to ensure they can.
            do
            {
                try
                {
                    Thread.sleep(10);
                }
                catch (Exception e)
                {
                }
            } while (timeOfStartedMove + 1e8 > System.nanoTime() || !safeToAct && !gameQuit);
            // Basically keep on waiting while, it is not safe to act, we haven't quit,
            // and it has not been 100 ms from the last move.
        }

        if (gameWon)
            v.announceWinOrLoss(true);
        if (cakeEaten)
            v.announceWinOrLoss(false);

        gameQuit = false;
    }

    // Inidcates which store item was picked
    public void pickDefender(StoreItem si)
    {
        // We don't allow the selection of defenders while we are paused.
        if (paused)
            return;

        // Wait for our turn to act.
        while (!safeToAct)
        {
            try
            {
                Thread.sleep(5);
            }
            catch (Exception e)
            {
            }
        }

        safeToAct = false;
        // Check with model to see if they have the gold the need
        if (m.selectDefenderToPlace(si))
        {
            // Update screen, with selection
            v.setStoreButtonPressed(si, true);
        }
        safeToAct = true;
    }

    // Places the previously indicated store item
    public void placeDefender(Location loc)
    {
        // Don't allow defender placement if paused.
        if (paused)
            return;

        while (!safeToAct)
        {
            try
            {
                Thread.sleep(5);
            }
            catch (Exception e)
            {
            }
        }

        safeToAct = false;
        // Let's place the defender at the location
        Defender newDef = m.placeDefender(loc);
        // m.placeDefender returns null if the spot is full
        // if the spot is full, then we don't change any aspect of our state
        if (newDef != null)
        {
            // Add the character to the screen
            v.addCharacter(newDef);
            v.setStoreButtonPressed(null, false);
        }
        safeToAct = true;
    }

    // Pauses the game
    public void pauseGame(boolean newPause)
    {
        // If we are already in whatever state we are being set to, just return
        if (paused == newPause)
            return;

        while (!safeToAct && !paused)
        {
            try
            {
                Thread.sleep(5);
            }
            catch (Exception e)
            {
            }
        }

        // If we are paused, then unpause, and make it safeToAct
        // If we are not paused, then pause, and make it unsafeToAct
        paused = newPause;
        safeToAct = !paused;
    }

    // Resets game, including the LevelGenerator
    public void resetGame()
    {
        resetGame(null);
    }

    // Resets game, including the LevelGenerator
    public void resetGame(LevelGenerator lg)
    {
        while (!safeToAct && !paused)
        {
            try
            {
                Thread.sleep(5);
            }
            catch (Exception e)
            {
            }
        }

        // Let's make it impossible for pauseGame() to unpause while we clear
        // the board
        paused = false;
        safeToAct = false;

        List<Character> listOfAllCharacters;
        // If lg is null, let's use the resetModel method. Otherwise let's
        // change the level generator
        if (lg == null)
            listOfAllCharacters = m.resetModel();
        else
            listOfAllCharacters = m.changeGenerator(lg);
        // let's remove the characters from view.
        for (Character charac: listOfAllCharacters)
        {
            v.removeCharacter(charac);
        }

        // // Set a new gold label, progress, and numAttackers
        v.setGoldLabel(m.getGold());
        v.setProgress(0);
        v.setMaxProgress(m.getNumAttackers());

        // Let's unpause to kill loop()
        v.unPause();
        gameQuit = true;
        safeToAct = true;

        // Wait for loop to die...
        while (gameQuit)
        {
            try
            {
                Thread.sleep(5);
            }
            catch (Exception e)
            {
            }
        }

        // Now let's start a new loop, with a clean controller.
        (new LoopThread(new Controller(m, v))).start();
    }

    // Quits the game
    public void quitGame()
    {
       gameQuit = true;
    }
}
