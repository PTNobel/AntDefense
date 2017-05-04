import java.util.List;

/**
 * Abstract class Defender - It is the base design for all defenders.
 * 
 * @author Parth
 * @version 0.0
 */
public abstract class Defender
{
    private int hp;
    private Location loc;
    public Defender(int baseHP)
    {
        hp = baseHP;
    }

    public boolean giveDamage(int damage)
    {
        hp -= damage;
        return hp > 0;
    }

    public boolean dead()
    {
        return hp <= 0;
    }

    /**
     * Takes ants, does damage to any damage should be done too,
     * Returns ants killed.
     */   
    abstract public List<Ant> processAnts(List<Ant> ants); 

    public Location getLoc()
    {
        return loc;
    }
}
