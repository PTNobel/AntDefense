import java.util.List;
import java.util.LinkedList;

public class MediumGenerator extends LevelGenerator
{

    public MediumGenerator()
    {
        super(500);
    }

    public List<Ant> generateAnts()
    {
        List<Ant> output = new LinkedList<Ant>();
        if (completed < max)
        {
            if (completed < 5)
            {
                if (Math.random() < 0.1)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 25)
            {
                if (Math.random() < 0.02)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.01)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 100)
            {
                if (Math.random() < 0.03)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.01)
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
            else if (completed < 200)
            {
                if (Math.random() < 0.04)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.02)
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
            else if (completed < 275)
            {
                if (Math.random() < 0.05)
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
                else if (Math.random() < 0.0075)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
                
            }
            else if (completed < 300)
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
                if (Math.random() < 0.03)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.02)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
            }
            else if (completed < 400)
            {
                if (Math.random() < 0.06)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.04)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.01)
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
            else if (completed < 470)
            {
                if (Math.random() < 0.08)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.05)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.013)
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
            else if (completed < 500)
            {
                if (Math.random() < 0.15)
                {
                    output.add(new WorkerAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.1)
                {
                    output.add(new DancingAnt(selectRandomRow()));
                    completed++;
                }
                if (Math.random() < 0.05)
                {
                    output.add(new WarriorAnt(selectRandomRow()));
                    completed++;
                }
                else if (Math.random() < 0.03)
                {
                    output.add(new ReidAnt(selectRandomRow()));
                    completed++;
                }
            }
            
        }
        return output;
    }

}
