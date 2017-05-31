import java.util.List;
import java.util.LinkedList;
public class TrivialGenerator extends LevelGenerator
{
    public TrivialGenerator()
    {
        super(3);
    }

    public List<Ant> generateAnts()
    {
        List<Ant> output = new LinkedList<Ant>();
        if (completed < max)
        {
            if (Math.random() < 0.02)
            {
                output.add(new WorkerAnt(selectRandomRow()));
                completed++;
            }
        }
        return output;
    }

}
