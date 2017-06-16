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


/**
 * Keeps track of the direction.
 * 
 * @author Parth
 * @version 0.0
 */
public enum Direction
{
    NORTH (90),

    EAST (0),

    SOUTH (270),

    WEST (180),

    NORTHEAST (45),

    SOUTHEAST (315),

    NORTHWEST (135),

    SOUTHWEST (225);
    public final int DIR;

    Direction(int dir)
    {
        this.DIR = dir;
    }

    public static Direction getCardinalOfDir(int dir)
    {
        // Force a positive direction in [0, 359];
        dir = ((dir % 360) + 360) % 360;

        switch (dir)
        {   
            case 0:
            return EAST;
            case 45:
            return NORTHEAST;
            case 90:
            return NORTH;
            case 135:
            return NORTHWEST;
            case 180:
            return WEST;
            case 225:
            return SOUTHWEST;
            case 270:
            return SOUTH;
            case 315:
            return SOUTHEAST;
            default:
            return null;
        }
    }

    public Direction turnLeft()
    {
        return getCardinalOfDir(DIR + 90);
    }

    public Direction turnRight()
    {
        return getCardinalOfDir(DIR - 90);
    }
}
