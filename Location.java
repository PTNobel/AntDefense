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

import java.awt.Point;
/**
 * Keeps track of locations in the game grid.
 * 
 * @author Parth Nobel
 * @version 0.0
 */
public class Location
{
    private Point pt;
    Location(int x, int y)
    {
        pt = new Point(x, y);
    }

    public int getRow()
    {
        return pt.y/GameView.JBUTTONHEIGHT;
    }

    public int getCol()
    {
        return pt.x/GameView.JBUTTONWIDTH;
    }

    public Point getPoint()
    {
        return pt;
    }
    
    public int getX()
    {
        return pt.x;
    }
    
    public int getY()
    {
        return pt.y;
    }

    public String toString()
    {
        return "Location: (" + pt.x + ", " + pt.y + ")";
    }

    public boolean equals(Location loc)
    {
        return loc.getX() == getX() && loc.getY() == getY();
    }
}
