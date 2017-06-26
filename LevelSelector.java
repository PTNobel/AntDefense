import javax.swing.JRootPane;

public class LevelSelector extends JRootPane
{
    private static final long serialVersionUID = 1L;
    private Window window;

    public LevelSelector(Window win)
    {
        window = win;
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


}
