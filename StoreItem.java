import javax.swing.ImageIcon;

public enum StoreItem
{
    POISONFOOD (50),
    POISONSPRAY (500);

    public final int COST;
    public final ImageIcon INIT_IMAGE;
    StoreItem(int cost, ImageIcon imgIcon)
    {
        COST = cost;
        INIT_IMAGE = imgIcon;
    }

    StoreItem(int cost)
    {
        this(cost, new ImageIcon("NullDefender.png"));
    }
    public Defender getDefender(Location loc){
        switch(this){
            case POISONFOOD:
                return new PoisonFood(loc);
            case POISONSPRAY:
                return new PoisonSpray(loc);
            default:
                return null;
        }
    }
}
