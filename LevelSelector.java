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

        JButton easyButton = new JButton();
        easyButton.setText("EASY");
        easyButton.setBounds( (Window.WIDTH - 80)/2, 300, 80, 40);
        easyButton.addMouseListener(new LevelSelectListner(0));


        add(easyButton);
    }

    private class LevelSelectListner extends MouseAdapter
    {
        private int diff;
        public LevelSelectListner(int difficutly)
        {
            diff = difficutly;
        }

        public void mouseClicked(MouseEvent event){
            LevelGenerator lg;
            switch (diff)
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
            Model model = new Model(lg);
            GameView gv = new GameView();
            Controller control = new Controller(model, gv);
            control.setGameView(gv);
            window.setNewContentPane(gv);
        }
    }

}
