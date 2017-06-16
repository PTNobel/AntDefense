/*
 *
 * Copyright 2017 Parth Nobel, Chase Carnaroli, Tiffany Nguyen, and Ignatius Widjaja
 *
 * This file is part of Ant Defense.
 *
 * Ant Defense is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Ant Defense is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Ant Defense.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.util.List;
import java.util.ListIterator;
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
            ListIterator<Ant> iter = cachedAnts.listIterator();
            while (completed < max && iter.hasNext())
            {
                output.add(iter.next());
                iter.remove();
                completed++;
            }

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
