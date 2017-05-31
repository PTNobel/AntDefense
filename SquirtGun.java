import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.ImageIcon;
/**
 * Write a description of class SquirtGun here.
 * 
 * @author Tiffany Nguyen
 * @version 0.0
 */
public class SquirtGun extends Defender
{
    // instance variables - replace the example below with your own
    private int dmg;

    public SquirtGun(Location loc)
    {
        super(200, loc);
        dmg = 2;
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        // Finds closest in column ant
        Ant closestAnt = null;
        for (Ant ant: ants)
        {
            if (ant.getLoc().getRow() == loc.getRow())
            {
                Location antLoc = ant.getLoc();
                if (loc.getX() < antLoc.getX())
                {
                    if (closestAnt == null)
                    {
                        closestAnt = ant;
                    }
                    else if (closestAnt.getLoc().getX() > antLoc.getX())
                    {
                        closestAnt = ant;
                    }
                }
            }
        }

        List<Ant> d = new LinkedList<Ant>();
        if (closestAnt != null)
        {
            if(!closestAnt.takeDamage(dmg))
                d.add(closestAnt);
        }
        return d;
    }
    
    public ImageIcon getInitialImageIcon()
    {
        return new ImageIcon("squirtGun.png");
    }
}
