import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * Write a description of class ReidAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class ReidAnt extends Ant
{
    private int swing;
    public ReidAnt(Location loc)
    {
        super(400, loc);
        swing = 15;
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
        setLoc(new Location(loc.getX() - 10, loc.getY()));
    }

    public int getGold()
    {
	    return 30;
    }
    public ImageIcon getInitialImageIcon(){
        return new ImageIcon("reidAnt.png");
    }
}
