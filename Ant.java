import java.util.List;
/**
 * 
 * 
 * @author Parth
 * @version 0.0
 */
public abstract class Ant extends Character
{
    public Ant(int baseHP, Location loc)
    {
        super(baseHP, loc);
    }

    private Icon icon;
    public void setIcon(Icon icon)
    {
        this.icon = icon;
    }
    
    public Icon getIcon()
    {
        return this.icon;
    }

    /**
     * Ant should act. If it kills a defender, then it should 
     * return an instance of them.
     */
    abstract public List<Defender> act(List<Defender> defenders);
}
