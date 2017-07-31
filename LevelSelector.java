import javax.swing.JRootPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

/**
 * Level Selector is the screen with all the level options
 *
 * METHODS
 *  public LevelSelector(Window win)
 *  
 */
public class LevelSelector extends JRootPane
{
    private static final long serialVersionUID = 1L;
    private Window window;
    private Dimension buttonSize;

    public LevelSelector(Window win)
    {
        // Let's store the window
        window = win;
        setSize(Window.WIDTH, Window.HEIGHT);
        setLayout(null);
        buttonSize = new Dimension(90,50);
        int buttonMargin = 20; // distance between buttons

        // EASY BUTTON
        JButton easyButton = new JButton();
        easyButton.setText("EASY");
        // button location and size
        int easyWidth = (int) buttonSize.getWidth();    // width
        int easyHeight = (int) buttonSize.getHeight();  // height
        int easyX = (Window.WIDTH - easyWidth)/2 - (easyWidth+buttonMargin);    // x-position
        int easyY = 300;                                                        // y-position
        easyButton.setBounds(easyX, easyY, easyWidth, easyHeight);
        easyButton.addMouseListener(new LevelSelectListner(LevelDifficulty.EASY));

        // MEDIUM BUTTON
        JButton medButton = new JButton();
        medButton.setText("MEDIUM");
        // button location and size
        int medWidth = (int) buttonSize.getWidth();     // width
        int medHeight = (int) buttonSize.getHeight();   // height
        int medX = (Window.WIDTH - medWidth)/2;         // x-position
        int medY = 300;                                 // y-position
        medButton.setBounds(medX, medY, medWidth, medHeight);
        medButton.addMouseListener(new LevelSelectListner(LevelDifficulty.MEDIUM));

        // HARD BUTTON
        JButton hardButton = new JButton();
        hardButton.setText("HARD");
        // button location and size
        int hardWidth = (int) buttonSize.getWidth();    // width
        int hardHeight = (int) buttonSize.getHeight();  // height
        int hardX = (Window.WIDTH - easyWidth)/2 + (hardWidth+buttonMargin);    // x-position
        int hardY = 300;                                                        // y-position
        hardButton.setBounds(hardX, hardY, hardWidth, hardHeight);
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

        public void mouseClicked(MouseEvent event)
        {
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
            GameView gv = window.switchToGameView();
            Controller control = new Controller(model, gv);
            (new LoopThread(control)).start();
        }
    }

}
