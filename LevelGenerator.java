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
import java.util.LinkedList;


/**
 * The Super class of the level generators
 * 
 * METHODS
 *  LevelGenerator(int max)
 *  int getNumAttackers()
 *  Location selectRandomRow()
 *  abstract List<Ant> generateAnts();
 *  void resetGenerator()
 *  void addAnts(List<Ant> ants)
 *
 */
public abstract class LevelGenerator
{
    // instance variables
    protected int completed = 0; 
    protected int max;
    protected List<Ant> cachedAnts;
    
    // location variables
    private int numRows = 5;
    private int yGap = 40;
    private int xStart = 760;
  
    public LevelGenerator(int max)
    {
        cachedAnts = new LinkedList<Ant>();
        this.max = max;
    }
    
    // The total number of attackers in this round.
    public int getNumAttackers()
    {
        return max;
    }

    public Location selectRandomRow()
    {
        int randomRow = (int)(Math.random()*numRows);
        
        // yStart = Random number in [0, 4] times 80 + random number [0, 55]
        int yStart = ((int)(Math.random()*numRows))*80 + (int)(Math.random() * 55);

        //return rows.get(randomRow);
        return new Location(xStart, yStart);
    }

    // Each turn this will be called. It will return a list of ants for that
    // turn.
    // i.e. on turn 1 it may return no ants.
    // On turn 3 it may return a single worker ant
    // On turn 50 it may return a queen ant and three warrior ants
    // These ants will recieve their location from level generator.
    public abstract List<Ant> generateAnts();

    /**
     * Resets the generator to the initial state.
     * Must be overridden if a sub-class keeps track of other variables then
     * completed.
     */
    public void resetGenerator()
    {
        completed = 0;
    }

    // Adds ants to the cached list.
    // Generators should pull everything from cachedAnts at the beginning of a
    // generateAnts, if possible.
    // If progress = max, don't.
    public void addAnts(List<Ant> ants)
    {
        cachedAnts.addAll(ants);
    }
}
