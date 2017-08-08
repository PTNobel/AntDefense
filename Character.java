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
 * Characters is the super class of all Defenders and Ants
 *
 * METHODS
    Character(int baseHP, Location loc)
    void setLoc(Location loc)
        Set loc
    Location getLoc()
        Return loc
    boolean takeDamage(int damage)
        Reduces the character's hp by damage
        post: Returns whether the character is alive (Returns true iff hp > 0)
    boolean dead()
        Returns whether or not the character is dead
        post: Returns true iff hp <= 0
    void setJLabel(JLabel jl)
        Set jLabel
    JLabel getJLabel()
        Return jLabel
    abstract ImageIcon getInitialImageIcon();
        Returns InitialImageIcon
 */
public abstract class Character
{
    // INSTANCE VARIABLES
    Location loc;
    private int hp;
    private JLabel jLabel = null;

    // CONSTRUCTOR
    public Character(int baseHP, Location loc)
    {
        hp = baseHP;
        this.loc = loc;
    }

    // METHODS

    // Set loc
    public void setLoc(Location loc)
    {
        this.loc = loc;
    }

    // Return loc
    public Location getLoc()
    {
        return loc;
    }

    // Reduces the character's hp by damage
    // post: Returns whether the character is alive (Returns true iff hp > 0)
    public boolean takeDamage(int damage)
    {
        hp -= damage;
        return hp > 0;
    }

    // Returns whether or not the character is dead
    // post: Returns true iff hp <= 0
    public boolean dead()
    {
        return hp <= 0;
    }

    // Sets jLabel
    public void setJLabel(JLabel jLabel)
    {
        this.jLabel = jLabel;
    }

    // Returns jLabel
    public JLabel getJLabel()
    {
        return this.jLabel;
    }

    // Returns InitialImageIcon
    public abstract ImageIcon getInitialImageIcon();
}
