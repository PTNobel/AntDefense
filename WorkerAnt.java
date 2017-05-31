import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * Write a description of class WorkerAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class WorkerAnt extends Ant
{
    private int swing;
    public WorkerAnt(Location loc)
    {
        super(100, loc);
        swing = 10;
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
        setLoc(new Location(loc.getX() - 3, loc.getY()));
    }
    
    public int getGold()
    {
	    return 5;
    }

    public ImageIcon getInitialImageIcon(){
        return new ImageIcon("workerAnt.png");
    }
}
