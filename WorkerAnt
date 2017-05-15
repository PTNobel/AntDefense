import java.util.List;
/**
 * Write a description of class WorkerAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class WorkerAnt extends Ant
{
    private int swing;
    public WorkerAnt(int baseHP, Location loc)
    {
        super(baseHP, loc);
        swing = 10;
    }

    public List<Defender> act(List<Defender> defenders)
    {
        List<Defender> d = new ArrayList<Defender>();
        for (Defender defense : defenders)
        {

            if (loc.getCol() < 5 + defense.getLoc().getCol() && loc.getCol() > defense.getLoc().getCol() - 5)
            {
                if(defense.takeDamage(swing))
                    d.add(defense);
            }
            else
                loc = move(loc);

        }
        return d;
    }

    private Location move(Location loc)
    {

    }
}
