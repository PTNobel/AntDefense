public enum StoreItem
{
    POISONFOOD (50),
    POISONSPRAY (500);

    public final int COST;
    StoreItem(int cost)
    {
        COST = cost;
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
