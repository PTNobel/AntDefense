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

import javax.swing.*;
import java.net.URL;
import java.io.*;

public class PictureLoader
{
    private static boolean built = false;
    public static ImageIcon antDefenseUI;
    public static ImageIcon poisonFood, poisonSpray, squirtGun, woodenBlock;
    public static ImageIcon dancingAnt, reidAnt, warriorAnt, workerAnt;

    public PictureLoader()
    {
        if (built)
            return;
        ClassLoader cldr = this.getClass().getClassLoader();
        try {
            antDefenseUI = new ImageIcon(cldr.getResource("AntDefenseUI.png"));
            poisonFood = new ImageIcon(cldr.getResource("poisonFood.png"));
            poisonSpray = new ImageIcon(cldr.getResource("poisonSpray.png"));
            squirtGun = new ImageIcon(cldr.getResource("squirtGun.png"));
            woodenBlock = new ImageIcon(cldr.getResource("woodenBlock.png"));
            dancingAnt = new ImageIcon(cldr.getResource("dancingAnt.png"));
            reidAnt = new ImageIcon(cldr.getResource("reidAnt.png"));
            warriorAnt = new ImageIcon(cldr.getResource("warriorAnt.png"));
            workerAnt = new ImageIcon(cldr.getResource("workerAnt.png"));
            built = true;
        }
        catch (Exception e)
        {
            System.out.println("Failed to load pictures: " + e.getMessage());
        }
    }
}
