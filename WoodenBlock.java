import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.ImageIcon;
/**
 * Write a description of class WoodenBlock here.
 * 
 * @author Tiffany Nguyen
 * @version 0.0
 */
public class WoodenBlock extends Defender
{
    // instance variables - replace the example below with your own
    private int dmg;

    /**
     * Constructor for objects of class PoisonFood
     */
    public WoodenBlock(Location loc)
    {
        super(300, loc, 2);
        dmg = 0;
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        List<Ant> d = new LinkedList<Ant>();
        return d;                               //returns an empty list since no damage is done
    }
    
    public ImageIcon getInitialImageIcon()
    {
        return new ImageIcon("woodenBlock.png");
    }
}