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

import javax.swing.JLabel;
import javax.swing.ImageIcon;
/**
 * Characters is the base class of all defenders and actors
 * 
 * @author Parth
 * @version 0.0
 */
abstract public class Character
{
    Location loc;
    private int hp;
    public Character(int baseHP, Location loc)
    {
        hp = baseHP;
        this.loc = loc;
    }

    public Location getLoc()
    {
        return loc;
    }

    public void setLoc(Location loc)
    {
        this.loc = loc;
    }

    public boolean takeDamage(int damage)
    {
        hp -= damage;
        return hp > 0;
    }

    public boolean dead()
    {
        return hp <= 0;
    }

    private JLabel jLabel = null;
    public void setJLabel(JLabel jl)
    {
        jLabel = jl;
    }
    
    public JLabel getJLabel()
    {
        return this.jLabel;
    }

    abstract public ImageIcon getInitialImageIcon();
}
