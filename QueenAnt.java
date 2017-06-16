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
import javax.swing.ImageIcon;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Write a description of class QueenAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class QueenAnt extends Ant implements HarmlessAnt
{
    private int swing;
    private LevelGenerator lg;

    public QueenAnt(LevelGenerator lg)
    {
        // Queen Ant always starts in the center column
        super(2000, new Location(760, 200));
        swing = 0;
        this.lg = lg;
    }

    public List<Defender> act(List<Defender> defenders)
    {

        boolean blocked = false;
        for (Defender defense : defenders)
        {
            if (loc.getCol() == defense.getLoc().getCol() && loc.getRow() == defense.getLoc().getRow())
            {
                blocked = true;
            }
        }

        if (Math.random() < .05)
        {
            List<Ant> newAnts = new LinkedList<Ant>();

            // Random number [-1, 1)
            double yMod = Math.random()*2 - 1;

            newAnts.add(
                    new WorkerAnt(
                        new Location(loc.getX(), loc.getY() + (int)(yMod*80))
                        )
                    );

            if (Math.random() < .05)
            {
                 newAnts.add(
                    new WarriorAnt(
                        new Location(loc.getX(), loc.getY() - (int)(yMod*80))
                        )
                    );


            } else if (Math.random() < 0.001)
            {
                newAnts.add(
                    new ReidAnt(
                        new Location(loc.getX(), loc.getY() - (int)(yMod*80))
                        )
                    );
            }

            lg.addAnts(newAnts);
        }
        
        if (!blocked)
            move();

        return new LinkedList<Defender>();
    }

    private void move()
    {
        setLoc(new Location(loc.getX() - 2, loc.getY()));
    }

    public int getGold()
    {
	    return 100;
    }

    public ImageIcon getInitialImageIcon()
    {
        return new ImageIcon("NullAnt.png");
    }
}
