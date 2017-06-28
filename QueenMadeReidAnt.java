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
 * @author Parth Nobel
 * @version 0.0
 */
public class QueenMadeReidAnt extends ReidAnt
{
    private Location targetLoc;
    public QueenMadeReidAnt(Location startingLoc, Location targetLoc)
    {
        super(startingLoc);
        this.targetLoc = targetLoc;
    }

    public List<Defender> act(List<Defender> defenders)
    {
        if (loc.getY() != targetLoc.getY())
            correctYMove();
        return super.act(defenders);
    }

    private void correctYMove()
    {
        // If we can get to the location in a single move, then let's just
        // make the move
        if (Math.abs(loc.getY() - targetLoc.getY()) < 10)
            setLoc(new Location(loc.getX(), targetLoc.getY()));
        // If our y is less then we want, let's increase y
        else if (loc.getY() < targetLoc.getY())
            setLoc(new Location(loc.getX(), loc.getY() + 10));
        // If our y is greater then we want, let's decrease y
        else if (loc.getY() > targetLoc.getY())
            setLoc(new Location(loc.getX(), loc.getY() - 10));
    }

    protected void move(Location loc)
    {
        if (loc.getY() == targetLoc.getY())
            super.move(loc);
    }
}
