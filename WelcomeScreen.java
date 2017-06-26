
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRootPane;
import javax.swing.JButton;

public class WelcomeScreen extends JRootPane
{
    private static final long serialVersionUID = 1L;
    private Window window;

    public WelcomeScreen()
    {
        setSize(Window.WIDTH, Window.HEIGHT);
        setLayout(null);

        JButton playButton = new JButton();
        playButton.setText("PLAY");
        playButton.setBounds( (Window.WIDTH - 80)/2, 300, 80, 40);
        playButton.addMouseListener(new PauseListener());

        JButton helpButton = new JButton();
        helpButton.setText("HELP");
        helpButton.setBounds( (Window.WIDTH - 80)/2, 360, 80, 40);

        add(playButton);
        add(helpButton);
    }

    private class PauseListener extends MouseAdapter
    {
        public PauseListener()
        {
        }

        public void mouseClicked(MouseEvent event){
            LevelSelector ls = new LevelSelector(window);
            window.setNewContentPane(ls);
        }
    }


    public void setWindow(Window win)
    {
        window = win;
    }
}
