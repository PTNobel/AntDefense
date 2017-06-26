
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
                    System.exit(0);
                }
            }
        );
    }
}
