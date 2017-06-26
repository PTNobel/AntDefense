
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
        playButton.addMouseListener(new PlayListener());

        JButton helpButton = new JButton();
        helpButton.setText("HELP");
        helpButton.setBounds( (Window.WIDTH - 80)/2, 360, 80, 40);
        helpButton.addMouseListener(new HelpListener());
        
        JButton creditsButton = new JButton();
        creditsButton.setText("CREDITS");
        creditsButton.setBounds( ((Window.WIDTH - 80)/2, 420, 80, 40);
        creditsButton.addMouseListener(new CreditsListener);

        add(playButton);        // adds play button to screen
        add(helpButton);        // adds help button to screen
        add(creditsButton);     // adds credits button to screen
    }

    private class PlayListener extends MouseAdapter
    {
        public PlayListener()
        {
        }

        public void mouseClicked(MouseEvent event){
            LevelSelector ls = new LevelSelector(window);
            window.setContentPane(ls);
        }
    }

    private class HelpListener extends MouseAdapter
    {
        public HelpListener()
        {
        }

        public void mouseClicked(MouseEvent event){
            // Code to open help screen
        }
    }
    
    private class CreditsListener extends MouseAdapter
    {
        public CreditsListener()
        {
        }

        public void mouseClicked(MouseEvent event){
            // Code to open credit screen
        }
    }

    public void setWindow(Window win)
    {
        window = win;
    }
}
