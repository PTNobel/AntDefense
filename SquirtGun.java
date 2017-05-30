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

    /**
     * Constructor for objects of class PoisonFood
     */
    public SquirtGun(Location loc)
    {
        super(400, loc, 8);
        dmg = 30;
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        List<Ant> d = new LinkedList<Ant>();
        for (Ant ant : ants)
        {
            if (loc.getCol() == ant.getLoc().getCol() && loc.getRow() == ant.getLoc().getRow())
            {
                if(!ant.takeDamage(dmg))
                    d.add(ant);
            }
        }
        return d;
    }
    
    public ImageIcon getInitialImageIcon()
    {
        return new ImageIcon("squirtGun.png");
    }
}