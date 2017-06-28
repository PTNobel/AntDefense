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
 * Write a description of class WorkerAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class WorkerAnt extends Ant
{
    private int swing;
    public WorkerAnt(Location loc)
    {
        super(100, loc);
        swing = 5;
    }

    public List<Defender> act(List<Defender> defenders)
    {
        List<Defender> d = new LinkedList<Defender>();

        boolean blocked = false;
        for (Defender defense : defenders)
        {
            if (loc.getCol() == defense.getLoc().getCol() && loc.getRow() == defense.getLoc().getRow())
            {
                if(!defense.takeDamage(swing))
                    d.add(defense);
                else
                    blocked = true;
            }
        }

        if (!blocked)
            move(loc);
        return d;
    }

    protected void move(Location loc)
    {
        setLoc(new Location(loc.getX() - 3, loc.getY()));
    }
    
    public int getGold()
    {
	    return 5;
    }

    public ImageIcon getInitialImageIcon(){
        return PictureLoader.workerAnt;
    }
}
