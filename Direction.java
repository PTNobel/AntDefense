
/**
 * Keeps track of the direction.
 * 
 * @author Parth
 * @version 0.0
 */
public enum Direction
{
    NORTH (90),

    EAST (0),

    SOUTH (270),

    WEST (180),

    NORTHEAST (45),

    SOUTHEAST (315),

    NORTHWEST (135),

    SOUTHWEST (225);
    public final int DIR;

    Direction(int dir)
    {
        this.DIR = dir;
    }

    public static Direction getCardinalOfDir(int dir)
    {
        // Force a positive direction in [0, 359];
        dir = ((dir % 360) + 360) % 360;

        switch (dir)
        {   
            case 0:
            return EAST;
            case 45:
            return NORTHEAST;
            case 90:
            return NORTH;
            case 135:
            return NORTHWEST;
            case 180:
            return WEST;
            case 225:
            return SOUTHWEST;
            case 270:
            return SOUTH;
            case 315:
            return SOUTHEAST;
            default:
            return null;
        }
    }

    public Direction turnLeft()
    {
        return getCardinalOfDir(DIR + 90);
    }

    public Direction turnRight()
    {
        return getCardinalOfDir(DIR - 90);
    }
}
