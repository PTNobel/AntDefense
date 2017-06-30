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
import java.util.ListIterator;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Write a description of class DancingAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class DancingAnt extends Ant implements HarmlessAnt
{
    private int swing;
    private int turnsInDir;
    private int dir;
    private int blockedDir;
    
    public DancingAnt(Location loc)
    {
        super(100, loc);
        swing = 5;
        turnsInDir = 0;
        dir = 0;
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
                // If this in top row
                if (loc.getY() <= 40)
                {
                    // See move method for definition of dir, moves downard
                    dir = 4;
                    // Create new blockedir biased down,
                    blockedDir = (Math.random() < .75)? 4: 3;
                }
                // If we're in the fifth (bottom) row
                else if (loc.getY() >= 5*80 - 40)
                {
                    // See move method for definition of dir, moves upward
                    dir = 3;
                    // Create new blockedir biased up,
                    blockedDir = (Math.random() < .75)? 3: 4;
                }
                else
                {
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
            dir = 2;
            turnsInDir = 15;
        }
        else if (newLoc.getY() >= 375)
        {
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

    public ImageIcon getInitialImageIcon(){
        return PictureLoader.dancingAnt;
    }
}
