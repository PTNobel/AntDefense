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


import java.awt.*;          // import Container
import java.util.*;         // import ArrayList
import javax.swing.*;       // import JFrame
import javax.swing.border.*;
import java.awt.event.*;    // import event listener

public class Window extends JFrame
{
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;
    private WindowController winControl;

    public Window(JRootPane initialPane)
    {
        super("Ant Defense");
        // set up window
        setSize(WIDTH, HEIGHT);    // sets size in pixels
        setResizable(false);   // makes it resizable or not (true == resizable screen)

        setContentPane(initialPane);
        setVisible(true);

        // needed to close application
        addWindowListener(new java.awt.event.WindowAdapter() 
            {
                public void windowClosing(WindowEvent evt) {
                    exit();
                }
            }
        );
    }

    public void startWindowController()
    {
        winControl = new WindowController(this);
        winControl.loop();
    }

    public void setNewContentPane(JRootPane jcp)
    {
        winControl.setNewContentPane(jcp);
    }

    public void exit()
    {
        winControl.exit();
        System.exit(0);
    }
}

class WindowController
{
    private boolean safeToAct = true;
    private boolean updateController = false;
    private JRootPane newContent = null;
    private boolean keepOnWatching = true;
    private Window window;

    public WindowController(Window win)
    {
        window = win;
    }

    public void loop()
    {
        while (keepOnWatching)
        {
            safeToAct = false;
            if (updateController)
            {
                window.setContentPane(newContent);
                updateController = false;
            }
            if (newContent instanceof GameView)
            {
                ((GameView)newContent).startGame();
            }
            
            safeToAct = true;
            do {
                try {
                    Thread.sleep(100);
                }
                catch (Exception e)
                {
                }
            } while (!safeToAct);
        }
        
    }

    public void setNewContentPane(JRootPane pane)
    {
        while (!safeToAct)
        {
            try
            {
                Thread.sleep(5);
            }
            catch (Exception e)
            {
            }
        }
        safeToAct = false;
        updateController = true;
        newContent = pane;
        safeToAct = true;
    }

    public void exit()
    {
        keepOnWatching = false;
    }
}
