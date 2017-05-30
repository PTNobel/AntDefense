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

        boolean blocked = false;
        for (Defender defense : defenders)
        {
            if (loc.getCol() == defense.getLoc().getCol() && loc.getRow() == defense.getLoc().getRow())
            {
                if(!defense.takeDamage(swing))
                    d.add(defense);
                else
                    blocked = true;
            }
        }

        if (!blocked)
            move(loc);
        return d;
    }

    private void move(Location loc)
    {
        int thing = (int)(Math.random()*8);
        Location newLoc;
        switch (thing)
        {
            case 0: newLoc = new Location(loc.getX() - 1, loc.getY());
                break;
            case 1: newLoc = new Location(loc.getX() + 1, loc.getY());
                break;
            case 2: newLoc = new Location(loc.getX(), loc.getY() - 1);
                break;
            case 3: newLoc = new Location(loc.getX(), loc.getY() + 1);
                break;
            case 4: newLoc = new Location(loc.getX() - 1, loc.getY() - 1);
                break;
            case 5: newLoc = new Location(loc.getX() - 1, loc.getY() + 1);
                break;
            case 6: newLoc = new Location(loc.getX() + 1, loc.getY() - 1);
                break;
            case 7: newLoc = new Location(loc.getX() + 1, loc.getY() + 1);
        }
        
        if(newLoc.getY() <= 0 || newLoc.getY() >= 400)
        {
            move(loc);
        } 
        else 
        {
            setLoc(newLoc);
        }
    }

    public int getGold()
    {
	    return 20;
    }
}
