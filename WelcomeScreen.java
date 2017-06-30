
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRootPane;
import javax.swing.JButton;
import java.awt.Dimension;

public class WelcomeScreen extends JRootPane
{
    private static final long serialVersionUID = 1L;
    private Window window;

    public WelcomeScreen()
    {
        setSize(Window.WIDTH, Window.HEIGHT);
        setLayout(null);
        Dimension buttonSize = new Dimension(80,40);
        int buttonMargin = 20;  // distance between buttons

        // PLAY BUTTON
        JButton playButton = new JButton();
        playButton.setText("PLAY");
        // button location and size
        int playWidth = (int) buttonSize.getWidth();    // width
        int playHeight = (int) buttonSize.getHeight();  // height
        int playX = (Window.WIDTH - playWidth)/2;                                   // x-position
        int playY = (Window.HEIGHT - playHeight)/2 - (playHeight + buttonMargin);   // y-position
        playButton.setBounds(playX, playY, playWidth, playHeight);
        playButton.addMouseListener(new PlayListener());

        // HELP BUTTON
        JButton helpButton = new JButton();
        helpButton.setText("HELP");
        // button location and size
        int helpWidth = (int) buttonSize.getWidth();    // width
        int helpHeight = (int) buttonSize.getHeight();  // height
        int helpX = (Window.WIDTH - helpWidth)/2;       // x-position
        int helpY = (Window.HEIGHT - helpHeight)/2;     // y-position
        helpButton.setBounds(helpX, helpY, helpWidth, helpHeight);
        helpButton.addMouseListener(new HelpListener());

        // CREDITS BUTTON
        JButton creditsButton = new JButton();
        creditsButton.setText("CREDITS");
        // button location and size
        int creditsWidth = (int) buttonSize.getWidth()+20; // width
        int creditsHeight = (int) buttonSize.getHeight();  // height
        int creditsX = (Window.WIDTH - creditsWidth)/2;                                   // x-position
        int creditsY = (Window.HEIGHT - creditsHeight)/2 + creditsHeight + buttonMargin;  // y-position
        creditsButton.setBounds(creditsX, creditsY, creditsWidth, creditsHeight);
        creditsButton.addMouseListener(new CreditsListener());

        // Add buttons to screen
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
