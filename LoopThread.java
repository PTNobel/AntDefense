
public class LoopThread extends Thread
{
    private GameView v;

    public LoopThread(GameView view)
    {
        v = view;
    }

    public void run() {
        v.startGame();
    }
}
