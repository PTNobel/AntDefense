
public class LoopThread extends Thread
{
    private Controller control;

    public LoopThread(Controller c)
    {
        control = c;
    }

    public void run()
    {
        control.loop();
    }
}
