import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Write a description of class WarriorAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class WarriorAnt extends Ant
{
    private int swing;
    public WarriorAnt(Location loc)
    {
        super(250, loc);
        swing = 15;
    }

    public List<Defender> act(List<Defender> defenders)
    {
        List<Defender> d = new LinkedList<Defender>();
        for (Defender defense : defenders)
        {
            if (loc.getCol() == defense.getLoc().getCol() && loc.getRow() == defense.getLoc().getRow())
            {
                if(!defense.takeDamage(swing))
                    d.add(defense);
            }
            else
                move(loc);

        }
        return d;
    }

    private void move(Location loc)
    {
        setLoc(new Location(loc.getX() - 10, loc.getY()));
    }

    public int getGold()
    {
	    return 10;
    }
}
