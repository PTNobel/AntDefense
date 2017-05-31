import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Write a description of class DancingAnt here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class DancingAnt extends Ant
{
    private int swing;
    private int turnsInDir;
    private int dir;
    
    public DancingAnt(Location loc)
    {
        super(100, loc);
        swing = 5;
        turnsInDir = 0;
        dir = 0;
    }

    public List<Defender> act(List<Defender> defenders)
    {
        List<Defender> d = new LinkedList<Defender>();

        boolean blocked = false;
        for (Defender defense : defenders)
        {
            if (loc.getCol() == defense.getLoc().getCol() && loc.getRow() == defense.getLoc().getRow())
            {
                turnsInDir = 10;
                if (loc.getCol() == 0)
                    dir = 2; // down
                if (loc.getCol() == 5)
                    dir = 3; // down
                else
                    dir = (Math.random() > .5)? 2: 3;
            }
        }

        if (!blocked)
            move(loc);
        return d;
    }

    private void move(Location loc)
    {
        if (turnsInDir <= 0)
        {
            dir = (int)(Math.random()*8);
            turnsInDir = 15;
        }
        else
        {
            turnsInDir--;
        }

        Location newLoc;
        switch (dir)
        {
            // Move forward
            case 5:
            case 0: newLoc = new Location(loc.getX() - 4, loc.getY());
                break;
            // Move diagnoally down
            case 7:
            case 1: newLoc = new Location(loc.getX() - 2, loc.getY() - 2);
                break;
            // Move down
            case 2: newLoc = new Location(loc.getX(), loc.getY() - 4);
                break;
            // Move up
            case 3: newLoc = new Location(loc.getX(), loc.getY() + 4);
                break;
            // Move diagnoally up
            case 6:
            case 4: newLoc = new Location(loc.getX() - 2, loc.getY() - 2);
                break;
            default: newLoc = null;
                break;
        }
        
        if(newLoc.getY() <= 0)
        {
            dir = 3;
            turnsInDir = 15;
        }
        else if (newLoc.getY() >= 400)
        {
            dir = 2;
            turnsInDir = 15;
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

    public ImageIcon getInitialImageIcon(){
        return PictureLoader.dancingAnt;
    }
}
