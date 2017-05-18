import java.util.List;

/**
 * Abstract class Defender - It is the base design for all defenders.
 * 
 * @author Parth
 * @version 0.0
 */
public abstract class Defender extends Character
{
    int cost;
    
    public Defender(int baseHP, Location loc, int cost)
    {
        super(baseHP, loc);
        this.cost = cost;
    }

    /**
     * Takes ants, does damage to any damage should be done too,
     * Returns ants killed.
     */   
    abstract public List<Ant> processAnts(List<Ant> ants);
    
    public int getCost(){
        return cost;
    }
}