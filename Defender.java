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

/**
 * Defender is the super class for all defenders.
 * 
 * METHODS
 *  Defender(int baseHP, Location loc)
 *  abstract List<Ant> processAnts(List<Ant> ants);
 *
 */
public abstract class Defender extends Character
{
    public Defender(int baseHP, Location loc)
    {
        super(baseHP, loc);
    }

    /**
     * Takes ants, does damage to any damage should be done too,
     * Returns ants killed.
     */   
    public abstract List<Ant> processAnts(List<Ant> ants);
    
}
