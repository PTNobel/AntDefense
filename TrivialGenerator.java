import java.util.List;
import java.util.ListIterator;
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
            ListIterator<Ant> iter = cachedAnts.listIterator();
            while (completed < max && iter.hasNext())
            {
                output.add(iter.next());
                iter.remove();
                completed++;
            }

            if (Math.random() < 0.02)
            {
                output.add(new DancingAnt(selectRandomRow()));
                completed++;
            } else if (Math.random() < 0.01)
            {
                output.add(new WorkerAnt(selectRandomRow()));
                completed++;
            }
        }
        return output;
    }

}
