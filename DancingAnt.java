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
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Dancing Ant moves throughout the board, randomly traveling, either up, or down
 * a row as it crosses.
 * 
 * METHODS
 *  DancingAnt(Location loc)
 *  List<Defender> act(List<Defender> defenders)
 *  int getGold()
 *  ImageIcon getInitialImageIcon()
 *
 */
public class DancingAnt extends Ant implements HarmlessAnt
{
    private int turnsInDir;
    private int dir;
    private int blockedDir;
    
    public DancingAnt(Location loc)
    {
        super(100, loc);
        turnsInDir = 0;
        // dir is an int [0, 4].
        // Their is no symbolism between the number and the direction
        dir = 0;
        // In the event the ant is blocked by a block in front, it will randomly
        // move either up or down.
        blockedDir = (Math.random() < .5)? 3: 4;
    }

    public List<Defender> act(List<Defender> defenders)
    {
        List<Defender> d = new LinkedList<Defender>();

        for (Defender defense : defenders)
        {
            // If we're colliding with this defense
            if (loc.getCol() == defense.getLoc().getCol() && loc.getRow() == defense.getLoc().getRow())
            {
                // Ensures move doesn't randomize direction.
                // by making turnsInDir > 0
                turnsInDir = 5;
                // If this ant in in the top half of the top row
                if (loc.getY() <= 40)
                {
                    // moves downwards
                    dir = 4;
                    // Create new blockedDir biased to move down,
                    blockedDir = (Math.random() < .75)? 4: 3;
                }
                // If we're in the fifth (bottom) row
                else if (loc.getY() >= 5*80 - 40)
                {
                    // moves upward
                    dir = 3;
                    // Create new blockedir biased up,
                    blockedDir = (Math.random() < .75)? 3: 4;
                }
                else
                {
                    // Use the randomly selected direction
                    dir = blockedDir;
                }
            }
        }

        move(loc);
        return d;
    }

    private void move(Location loc)
    {
        if (turnsInDir <= 0)
        {
            // Let's choose a new direction!
            // but only consider forward moving directions
            dir = (int)(Math.random()*3);
            turnsInDir = 15;
        }
        else
        {
            turnsInDir--;
        }

        Location newLoc;
        switch (dir)
        {
            // Move forward
            case 0: newLoc = new Location(loc.getX() - 4, loc.getY());
                break;
            // Move diagnoally down
            case 1: newLoc = new Location(loc.getX() - 2, loc.getY() - 2);
                break;
            // Move diagnoally up
            case 2: newLoc = new Location(loc.getX() - 2, loc.getY() + 2);
                break;
            // Move down
            case 3: newLoc = new Location(loc.getX(), loc.getY() - 4);
                break;
            // Move up
            case 4: newLoc = new Location(loc.getX(), loc.getY() + 4);
                break;

            default: newLoc = null;
                break;
        }
        
        if(newLoc.getY() <= 0)
        {
            // We're off the top of the screen!
            // Move down diagnoally.
            dir = 2;
            turnsInDir = 15;
        }
        else if (newLoc.getY() >= 375)
        {
            // We're off the bottom of the screen!
            // Move up diagnoally.
            dir = 1;
            turnsInDir = 15;
        }
        else 
        {
            setLoc(newLoc);
        }
    }

    public int getGold()
    {
	    return 20;
    }

    public ImageIcon getInitialImageIcon()
    {
        return PictureLoader.dancingAnt;
    }
}
