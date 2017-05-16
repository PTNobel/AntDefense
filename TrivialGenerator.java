import java.util.List;
import java.util.LinkedList;
public class TrivialGenerator implements LevelGenerator
{
    private int completed = 0, max = 3;

    public int getNumAttackers()
    {
        return max;
    }

    public List<Ant> generateAnts()
    {
        List<Ant> output = new LinkedList<Ant>();
        if (completed < max)
        {
            if (Math.random() > 0.75)
            output.add(new WorkerAnt(100, new Location(0, 0)));
        }
        return output;
    }

}
