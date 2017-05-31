import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.ImageIcon;
/**
 * Class PoisonSpray - It is the defender that hurts ants that walk into its location.
 * 
 * @author Tiffany Nguyen
 * @version 0.0
 */
public class PoisonSpray extends Defender
{
    // instance variables - replace the example below with your own
    private int dmg;

    /**
     * Constructor for objects of class PoisonFood
     */
    public PoisonSpray(Location loc)
    {
        super(500, loc); 
        dmg = 30;
    }

    public List<Ant> processAnts(List<Ant> ants)
    {
        List<Ant> a = new LinkedList<Ant>();
        for (Ant ant : ants)
        {
            if (loc.getCol() == ant.getLoc().getCol()+1 && (loc.getRow() == ant.getLoc().getRow() || 
                loc.getRow() == ant.getLoc().getRow()+1 ||  loc.getRow() == ant.getLoc().getRow()-1))
            {
                if(!ant.takeDamage(dmg))
                    a.add(ant);
            }
            else if (loc.getCol() == ant.getLoc().getCol() && loc.getRow() == ant.getLoc().getRow())
            {
                if(!ant.takeDamage(dmg))
                    a.add(ant);
            }
        }
        return a;
    }

    public ImageIcon getInitialImageIcon(){
        return PictureLoader.poisonSpray;
    }
}
