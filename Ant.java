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
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * @author Parth
 * @version 0.0
 */
public abstract class Ant extends Character
{
    public Ant(int baseHP, Location loc)
    {
        super(baseHP, loc);
    }

    /**
     * REturns the appropriate gold amount gained from killing the Ant.
     */
    abstract public int getGold();

    /**
     * Ant should act. If it kills a defender, then it should 
     * return an instance of them.
     */
    abstract public List<Defender> act(List<Defender> defenders);
    
}
