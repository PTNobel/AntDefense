import javax.swing.JRootPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class LevelSelector extends JRootPane
{
    private static final long serialVersionUID = 1L;
    private Window window;
    private Dimension buttonSize;

    public LevelSelector(Window win)
    {
        window = win;
        setSize(Window.WIDTH, Window.HEIGHT);
        setLayout(null);
        buttonSize = new Dimension(90,50);

        // Easy Button
        JButton easyButton = new JButton();
        easyButton.setText("EASY");
        easyButton.setBounds( (Window.WIDTH - (int) buttonSize.getWidth())/2 - ((int) buttonSize.getWidth()+20), 300, (int) buttonSize.getWidth(), (int) buttonSize.getHeight());
        easyButton.addMouseListener(new LevelSelectListner(LevelDifficulty.EASY));

        // Medium Button
        JButton medButton = new JButton();
        medButton.setText("MEDIUM");
        medButton.setBounds( (Window.WIDTH - (int) buttonSize.getWidth())/2, 300, (int) buttonSize.getWidth(), (int) buttonSize.getHeight());
        medButton.addMouseListener(new LevelSelectListner(LevelDifficulty.MEDIUM));

        // Hard Button
        JButton hardButton = new JButton();
        hardButton.setText("HARD");
        hardButton.setBounds( (Window.WIDTH - (int) buttonSize.getWidth())/2 + ((int) buttonSize.getWidth()+20), 300, (int) buttonSize.getWidth(), (int) buttonSize.getHeight());
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
            GameView gv = new GameView(window);
            Controller control = new Controller(model, gv);
            control.setGameView(gv);
            window.setNewContentPane(gv);
        }
    }

}
