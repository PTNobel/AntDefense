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
import javax.swing.ImageIcon;
/**
 * Write a description of class SquirtGun here.
 * 
 * @author Tiffany Nguyen
 * @version 0.0
 */
public class SquirtGun extends Defender
{
    // instance variables - replace the example below with your own
    private int dmg;

    public SquirtGun(Location loc)
    {
        super(200, loc);
        dmg = 3;
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        // Finds closest in column ant
        Ant closestAnt = null;
        for (Ant ant: ants)
        {
            if (ant.getLoc().getRow() == loc.getRow())
            {
                Location antLoc = ant.getLoc();
                if (loc.getX() < antLoc.getX())
                {
                    if (closestAnt == null)
                    {
                        closestAnt = ant;
                    }
                    else if (closestAnt.getLoc().getX() > antLoc.getX())
                    {
                        closestAnt = ant;
                    }
                }
            }
        }

        List<Ant> d = new LinkedList<Ant>();
        if (closestAnt != null)
        {
            if(!closestAnt.takeDamage(dmg))
                d.add(closestAnt);
        }
        return d;
    }
    
    public ImageIcon getInitialImageIcon()
    {
        return PictureLoader.squirtGun;
    }
}
