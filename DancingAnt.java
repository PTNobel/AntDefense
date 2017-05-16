import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Write a description of class DancingAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class DancingAnt extends Ant
{
    private int swing;
    
    public DancingAnt(Location loc)
    {
        super(100, loc);
        swing = 12;
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
        int thing = (int)(Math.random()*8)*45;
        
    }
}
