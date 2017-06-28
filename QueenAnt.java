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
 * QueenAnts generate other ants as they move.
 * 
 * @author Parth Nobel and Ignatius Widjaja
 * @version 0.0
 */
public class QueenAnt extends Ant implements HarmlessAnt
{
    private int swing;
    private LevelGenerator lg;

    public QueenAnt(LevelGenerator lg, Location loc)
    {
        // Queen Ant always starts in the center column
        //super(2000, loc);
        super(2000, new Location(loc.getX(), 25));
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

            if (Math.random() < .05)
            {
                newAnts.add(
                    new WarriorAnt(
                        getRandomLocation()
                        )
                    );
            } else if (Math.random() < 0.001)
            {
                newAnts.add(
                    new ReidAnt(
                        getRandomLocation()
                    )
                );
            } else
            {
                newAnts.add(
                    new WorkerAnt(
                        getRandomLocation()
                        )
                    );
            }

            lg.addAnts(newAnts);
        }
        
        
        if (!blocked)
            move();

        return new LinkedList<Defender>();
    }

    private Location getRandomLocation()
    {
            // Random number [-1, 1)
            double yMod = Math.random()*2 - 1;
            // Same column, variable row
            Location output = new Location(loc.getX(), loc.getY() + (int)(yMod*80));
            // if we are off the screen, let's get on the screen
            if (output.getY() < 0)
            {
                output = new Location(loc.getX(), (int)(Math.random()*10));
            } else if (output.getY() > 375)
            {
                output = new Location(loc.getX(), 375 - (int)(Math.random()*10));
            }
            return output;
    }

    private void move()
    {
        setLoc(new Location(loc.getX() - 1, loc.getY()));
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
