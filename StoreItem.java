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

import javax.swing.ImageIcon;

public enum StoreItem
{
    POISONFOOD (25, PictureLoader.poisonFood),
    WOODENBLOCK (25, PictureLoader.woodenBlock),
    POISONSPRAY (50, PictureLoader.poisonSpray),
    SQUIRTGUN (75, PictureLoader.squirtGun);

    public final int COST;
    public final ImageIcon INIT_IMAGE;
    StoreItem(int cost, ImageIcon imgIcon)
    {
        COST = cost;
        INIT_IMAGE = imgIcon;
    }

    public Defender getDefender(Location loc){
        switch(this){
            case POISONFOOD:
                return new PoisonFood(loc);
            case POISONSPRAY:
                return new PoisonSpray(loc);
            case WOODENBLOCK:
                return new WoodenBlock(loc);
            case SQUIRTGUN:
                return new SquirtGun(loc);
            default:
                return null;
        }
    }
}
