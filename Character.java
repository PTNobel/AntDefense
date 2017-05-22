import javax.swing.JLabel;
import javax.swing.ImageIcon;
/**
 * Characters is the base class of all defenders and actors
 * 
 * @author Parth
 * @version 0.0
 */
abstract public class Character
{
    Location loc;
    private int hp;
    public Character(int baseHP, Location loc)
    {
        hp = baseHP;
        this.loc = loc;
    }

    public Location getLoc()
    {
        return loc;
    }

    public void setLoc(Location loc)
    {
        this.loc = loc;
    }

    public boolean takeDamage(int damage)
    {
        hp -= damage;
        return hp > 0;
    }

    public boolean dead()
    {
        return hp <= 0;
    }

    private JLabel jLabel = null;
    public void setJLabel(JLabel jl)
    {
        jLabel = jl;
    }
    
    public JLabel getJLabel()
    {
        return this.jLabel;
    }

    abstract public ImageIcon getInitialImageIcon();
}
