import java.util.List;
/**
 * Abstract class Ant - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Ant
{
    Location loc;
    private int hp;
    
    public Location getLoc()
    {
        return loc;
    }
    
    public void setLoc(Location loc)
    {
        this.loc = loc;
    }
    
    public Ant(Location loc)
    {
        this.loc = loc;
    }
    
    /**
     * Ant should act. If it kills a defender, then it should 
     * return an instance of them.
     */
    abstract public List<Defender> act(List<Defender> defenders);
}
