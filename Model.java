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

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.List;

/**
 * Write a description of class Model here.
 * 
 * @author Parth
 * @version 0.0
 */
public class Model
{
    private static final int NUM_ROWS = 5, NUM_COLS = 10;
    private int NUM_ATTACKERS;
    private Defender[][] board;
    private List<Defender> defenderList;
    private List<Ant> antList;
    private LevelGenerator lg;
    private int gold, progress;
    private StoreItem curSelectedDefender = null;

    public Model(LevelGenerator levelGenerator)
    {
        lg = levelGenerator;
        NUM_ATTACKERS = lg.getNumAttackers();
        board = new Defender[NUM_ROWS][NUM_COLS];
        antList = new LinkedList<Ant>();
        defenderList = new LinkedList<Defender>();
        progress = 0;
        gold = 250;
    }

    public int getNumRows()
    {
        return NUM_ROWS;
    }
    
    public int getNumCols()
    {
        return NUM_COLS;
    }
    
    public int getNumAttackers()
    {
        return NUM_ATTACKERS;
    }

    public Defender getDefenderAtLoc(Location loc)
    {
        return board[loc.getRow()][loc.getCol()];
    }

    public void setDefenderAtLoc(Defender def)
    {
        Location loc = def.getLoc();
        board[loc.getRow()][loc.getCol()] = def;
        defenderList.add(def);
    }

    public void removeDefender(Defender def)
    {
        Location loc = def.getLoc();
        board[loc.getRow()][loc.getCol()] = null;
        defenderList.remove(def);
    }

    public void addAnts(Ant... ants)
    {
        for (Ant ant: ants)
        {
            antList.add(ant);
        }
    }

    /**
     * Returns true if defender was placed;
     * Returns false if defender was not placed.
     */
    public boolean addDefender(Defender defend)
    {
        if (getDefenderAtLoc(defend.getLoc()) == null)
        {
            setDefenderAtLoc(defend);
            defenderList.add(defend);
            return true;
        } 
        else
            return false;
    }
 
    public ActResult act()
    {
        ActResult output = new ActResult();
        List<Character> deadCharacters = new LinkedList<Character>();
        List<Character> newCharacters = new LinkedList<Character>();
        List<Character> movedCharacters = new LinkedList<Character>();
        output.setCakeEaten(false);
        // move ants
        for (Ant ant:antList)
        {
            Location oldLoc = ant.getLoc();
            List<Defender> killedDefs = ant.act(defenderList);
            for (Defender def: killedDefs)
            {
                deadCharacters.add(def);
                removeDefender(def);
            }
            if (!oldLoc.equals(ant.getLoc()))
            {
                movedCharacters.add(ant);
            }
            if (ant.getLoc().getX() < 0)
                output.setCakeEaten(true);
        }
        // add new ants
        for (Ant newAnt: lg.generateAnts())
        {
            antList.add(newAnt);
            newCharacters.add(newAnt);
        }
        // process defenders
        for (Defender def: defenderList)
        {
            for (Ant ant: def.processAnts(antList))
            {
                deadCharacters.add(ant);
                antList.remove(ant);
                progress++;
                gold += ant.getGold();
            }
        }


        //
        output.setDeadCharacters(deadCharacters);
        output.setNewCharacters(newCharacters);
        output.setMovedCharacters(movedCharacters);
        output.setProgress(progress);
        output.setGold(gold);
        output.setGameOver(progress == NUM_ATTACKERS);

        return output;
    }

    public boolean selectDefenderToPlace(StoreItem si)
    {
        if(gold >= si.COST){
            curSelectedDefender = si;
            return true;
        } else {
            return false;
        }
    }

    /*
     * pre-condition: gold > curSelectedDefender.getCost()
     */
    public Defender placeDefender(Location loc)
    {
        if(curSelectedDefender == null){
            return null;
        } else if(getDefenderAtLoc(loc) != null){
            return null;
        } else {
            Defender def = curSelectedDefender.getDefender(loc);
            if (def == null)
                return null;
            gold -= curSelectedDefender.COST;
            setDefenderAtLoc(def);
            curSelectedDefender = null;
            return def;
        }
    }
   
    /**
     * Postcondition: Returns all characters currently stored in model.
     *      Resets the rest of model.
     */
    public List<Character> resetModel()
    {
        // Create the list
        List<Character> output = new LinkedList<Character>();

        output.addAll(antList);
        antList = new LinkedList<Ant>();
        output.addAll(defenderList);
        defenderList = new LinkedList<Defender>();

        gold = 250;
        progress = 0;
        board = new Defender[NUM_ROWS][NUM_COLS];
        lg.resetGenerator();

        return output;
    }

    public List<Character> changeGenerator(LevelGenerator levelGenerator)
    {
        lg = levelGenerator;
        NUM_ATTACKERS = lg.getNumAttackers();

        return resetModel();
    }

    public boolean getGameWon(){
        return NUM_ATTACKERS == progress;
    }

    public int getGold()
    {
        return gold;
    }
}
