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
 * Write a description of class PoisonFood here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class PoisonFood extends Defender
{
    // instance variables - replace the example below with your own
    private int dmg;

    /**
     * Constructor for objects of class PoisonFood
     */
    public PoisonFood(Location loc)
    {
        super(500, loc);
        dmg = 15;
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        List<Ant> d = new LinkedList<Ant>();
        for (Ant ant : ants)
        {
            if (loc.getCol() == ant.getLoc().getCol() && loc.getRow() == ant.getLoc().getRow())
            {
                // Don't damage dancing ants, they are not damaging us
                if (!(ant instanceof HarmlessAnt))
                {
                    if(!ant.takeDamage(dmg))
                        d.add(ant);
                }
            }
        }
        return d;
    }
    
    public ImageIcon getInitialImageIcon()
    {
        return PictureLoader.poisonFood;
    }
}
