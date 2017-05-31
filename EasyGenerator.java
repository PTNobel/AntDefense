import java.util.List;
import java.util.LinkedList;

public class EasyGenerator extends LevelGenerator
{

    public EasyGenerator()
    {
        super(200);
    }

    public List<Ant> generateAnts()
    {
        List<Ant> output = new LinkedList<Ant>();
        if (completed < max)
        {
            if (completed < 5)
            {
                if (Math.random() < 0.02)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 30)
            {
                if (Math.random() < 0.03)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.005)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 90)
            {
                if (Math.random() < 0.04)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.0075)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.005)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.005)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
            
            }
            else if (completed < 110)
            {
                if (Math.random() < 0.07)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.01)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.01)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.006)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
                
            }
            else if (completed < 130)
            {
                if (Math.random() < 0.06)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.02)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.008)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.005)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
                
            }
            else if (completed < 170)
            {
                if (Math.random() < 0.07)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.03)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.01)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.005)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 180)
            {
                if (Math.random() < 0.08)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.03)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.01)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.008)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 200)
            {
                if (Math.random() < 0.1)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.05)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.02)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.01)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
            }
            
        }
        return output;
    }

}
