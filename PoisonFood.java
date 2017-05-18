import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Write a description of class PoisonFood here.
 * 
 * @author Ignatius Widjaja
 * @version 0.0
 */
public class PoisonFood extends Defender
{
    // instance variables - replace the example below with your own
    private int dmg;

    /**
     * Constructor for objects of class PoisonFood
     */
    public PoisonFood(Location loc)
    {
        super(200, loc, 5);
        dmg = 20;
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
}
