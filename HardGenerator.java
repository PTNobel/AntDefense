import java.util.List;
import java.util.LinkedList;

public class HardGenerator extends LevelGenerator
{

    public HardGenerator()
    {
        super(1000);
    }

    public List<Ant> generateAnts()
    {
        List<Ant> output = new LinkedList<Ant>();
        if (completed < max)
        {
            if (completed < 5)
            {
                if (Math.random() < 0.5)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 25)
            {
                if (Math.random() < 0.75)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.25)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 100)
            {
                if (Math.random() < 0.8)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random < 0.5)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random < 0.5)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
                
            }
        }
        return output;
    }

}