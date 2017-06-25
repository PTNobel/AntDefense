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
 * This class communicates information from the Model to the View and vica
 * versa
 *
 * @author Parth Nobel and Chase Carnaroli
 * @version 0.0
 */
public class Controller
{
    private Model m;
    private View v;
    private boolean safeToAct = true;
    private boolean paused = false;

    public Controller(Model model)
    {
        m = model;
    }

    public void setView(View view)
    {
        v = view;
        v.setControl(this);
        v.setGoldLabel(m.getGold());
        v.setMaxProgress(m.getNumAttackers());
    }

    public void loop()
    {
        boolean cakeEaten = false, gameWon = false;
        int turn = 0;

        while (!cakeEaten && !gameWon)
        {
            safeToAct = false;
            ActResult move = m.act();

            for (Character charac: move.getDeadCharacters())
            {
                v.removeCharacter(charac);
            }

            for (Character charac: move.getNewCharacters())
            {
                v.addCharacter(charac);
            }

            for (Character charac: move.getMovedCharacters())
            {
                v.moveCharacter(charac);
            }

            v.setProgress(move.getProgress());
            v.setGoldLabel(move.getGold());
            gameWon = move.getGameWon();
            cakeEaten = move.getCakeEaten();
            turn++;


            safeToAct = true;
            do {
                try {
                    Thread.sleep(100);
                }
                catch (Exception e)
                {
                }
            } while (!safeToAct);
        }
        
        if (gameWon)
            v.announceWinOrLoss(true);
        if (cakeEaten)
            v.announceWinOrLoss(false);
    }

    public void pickDefender(StoreItem si)
    {
        if (paused)
            return;

        while (!safeToAct){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        safeToAct = false;
        if (m.selectDefenderToPlace(si))
        {
            // Update screen, with selection
            v.setStoreButtonPressed(si, true);
        }
        safeToAct = true;
    }

    public void placeDefender(Location loc)
    {
        if (paused)
            return;

        while (!safeToAct){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        safeToAct = false;
        Defender newDef = m.placeDefender(loc);
        if (newDef != null)
        {
            v.addCharacter(newDef);
            v.setStoreButtonPressed(null, false);
        }
        safeToAct = true;
    }

    public void pauseGame()
    {
        while (!safeToAct && !paused){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        paused = !paused;
        safeToAct = !paused;
    }

    public void resetGame()
    {
        while (!safeToAct && !paused){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        boolean origPaused = paused;
        paused = false;
        safeToAct = false;

        for (Character charac: m.resetModel())
        {
            v.removeCharacter(charac);
        }
    
        v.setGoldLabel(m.getGold());
        v.setProgress(0);
        v.setMaxProgress(m.getNumAttackers());

        paused = origPaused;
        safeToAct = !origPaused;
    }

    public void resetGame(LevelGenerator lg)
    {
        while (!safeToAct && !paused){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        paused = false;
        safeToAct = false;

        for (Character charac: m.changeGenerator(lg))
        {
            v.removeCharacter(charac);
        }
    
        v.setGoldLabel(m.getGold());
        v.setProgress(0);
        v.setMaxProgress(m.getNumAttackers());

        safeToAct = true;
    }
}
