import java.util.List;
import java.util.LinkedList;
public class TrivialGenerator implements LevelGenerator
{
    private int completed = 0, max = 1;

    public int getNumAttackers()
    {
        return max;
    }

    public List<Ant> generateAnts()
    {
        List<Ant> output = new LinkedList<Ant>();
        if (completed < max)
        {
            if (Math.random() > 0.95)
            {
                output.add(new WorkerAnt(new Location(400, 100)));
                completed++;
            }
        }
        return output;
    }

}
