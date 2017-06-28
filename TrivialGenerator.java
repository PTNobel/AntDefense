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
public class TrivialGenerator extends LevelGenerator
{
    public TrivialGenerator()
    {
        super(300);
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

            if (completed == 0)
            {
                output.add(new QueenAnt(this, selectRandomRow()));
                completed++;
            }
            else if (Math.random() < 0.02)
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
