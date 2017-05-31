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
    /**
     * Constructor for objects of class PoisonFood
     */
    public WoodenBlock(Location loc)
    {
        super(4000, loc);
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        return new LinkedList<Ant>(); //returns an empty list since no damage is done
    }
    
    public ImageIcon getInitialImageIcon()
    {
        return PictureLoader.woodenBlock;
    }
}
