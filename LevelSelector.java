import javax.swing.JRootPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevelSelector extends JRootPane
{
    private static final long serialVersionUID = 1L;
    private Window window;

    public LevelSelector(Window win)
    {
        window = win;
        setSize(Window.WIDTH, Window.HEIGHT);
        setLayout(null);

        // Easy Button
        JButton easyButton = new JButton();
        easyButton.setText("EASY");
        easyButton.setBounds( (Window.WIDTH - 80)/2 - 100, 300, 80, 40);
        easyButton.addMouseListener(new LevelSelectListner(LevelDifficulty.EASY));

        // Medium Button
        JButton medButton = new JButton();
        medButton.setText("MEDIUM");
        medButton.setBounds( (Window.WIDTH - 80)/2, 300, 80, 40);
        medButton.addMouseListener(new LevelSelectListner(LevelDifficulty.MEDIUM));

        // Hard Button
        JButton hardButton = new JButton();
        hardButton.setText("HARD");
        hardButton.setBounds( (Window.WIDTH - 80)/2 + 100, 300, 80, 40);
        hardButton.addMouseListener(new LevelSelectListner(LevelDifficulty.HARD));

        // Add buttons to screen
        add(easyButton);
        add(medButton);
        add(hardButton);
    }

    private class LevelSelectListner extends MouseAdapter
    {
        private LevelDifficulty diff;
        public LevelSelectListner(LevelDifficulty difficutly)
        {
            diff = difficutly;
        }

        public void mouseClicked(MouseEvent event){
            LevelGenerator lg;
            switch (diff)
            {
            case EASY: lg = new EasyGenerator();
                break;

            case MEDIUM: lg = new MediumGenerator();
                break;

            case HARD: lg = new HardGenerator();
                break;

            default: lg = new TrivialGenerator();
                break;
            }
            Model model = new Model(lg);
            GameView gv = new GameView();
            Controller control = new Controller(model, gv);
            control.setGameView(gv);
            window.setNewContentPane(gv);
        }
    }

}
