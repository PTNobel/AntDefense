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

import javax.swing.JOptionPane; 
import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException; 

public class AntDefense
{
    public static void main(String[] args)
    {
        
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        PictureLoader picLoader =  new PictureLoader();
   
        WelcomeScreen ws = new WelcomeScreen();
        Window win = new Window(ws);
        ws.setWindow(win);

    }

    public static LevelGenerator getDifficulty()
    {
        // Create the dialog.
        // String[] possibleValues = {"Trivial", "Easy", "Medium", "Hard"};
        String[] possibleValues = {"Easy", "Medium", "Hard"};
        Integer selectedValue = JOptionPane.showOptionDialog(null,
                "Choose a difficulty.", "Ant Defense",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null,  possibleValues, possibleValues[0]);
        LevelGenerator lg;
        switch (selectedValue)
        {

            case 0: lg = new EasyGenerator();
                break;
            
            case 1: lg = new MediumGenerator();
                break;
            
            case 2: lg = new HardGenerator();
                break;

            default: lg = new TrivialGenerator();
                break;
        }

        return lg;
    }
}
