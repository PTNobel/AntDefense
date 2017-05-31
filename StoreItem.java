import javax.swing.ImageIcon;

public enum StoreItem
{
    POISONFOOD (25, new ImageIcon("poisonFood.png")),
    WOODENBLOCK (25, new ImageIcon("woodenBlock.png")),
    POISONSPRAY (50, new ImageIcon("poisonSpray.png")),
    SQUIRTGUN (75, new ImageIcon("squirtGun.png"));

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
            case WOODENBLOCK:
                return new WoodenBlock(loc);
            case SQUIRTGUN:
                return new SquirtGun(loc);
            default:
                return null;
        }
    }
}
