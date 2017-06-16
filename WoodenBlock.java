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
 * Write a description of class WoodenBlock here.
 * 
 * @author Tiffany Nguyen
 * @version 0.0
 */
public class WoodenBlock extends Defender
{
    /**
     * Constructor for objects of class PoisonFood
     */
    public WoodenBlock(Location loc)
    {
        super(4000, loc);
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        return new LinkedList<Ant>(); //returns an empty list since no damage is done
    }
    
    public ImageIcon getInitialImageIcon()
    {
        return PictureLoader.woodenBlock;
    }
}
