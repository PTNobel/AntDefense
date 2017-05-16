import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Write a description of class ReidAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class ReidAnt extends Ant
{
    private int swing;
    public ReidAnt(int baseHP, Location loc)
    {
        super(baseHP, loc);
        swing = 50;
    }

    public List<Defender> act(List<Defender> defenders)
    {
        List<Defender> d = new LinkedList<Defender>();
        for (Defender defense : defenders)
        {
            if (loc.getCol() == defense.getLoc().getCol() && loc.getRow() == defense.getLoc().getCol())
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
        setLoc(new Location(loc.getX() - 20, loc.getY()));
    }
}
