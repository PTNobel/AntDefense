import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * @author Parth
 * @version 0.0
 */
public abstract class Ant extends Character
{
    public Ant(int baseHP, Location loc)
    {
        super(baseHP, loc);
    }

    /**
     * REturns the appropriate gold amount gained from killing the Ant.
     */
    abstract public int getGold();

    /**
     * Ant should act. If it kills a defender, then it should 
     * return an instance of them.
     */
    abstract public List<Defender> act(List<Defender> defenders);
    
    public ImageIcon getInitialImageIcon(){
        return new ImageIcon("NullAnt.png");
    }
}