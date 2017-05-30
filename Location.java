import java.awt.Point;
/**
 * Keeps track of locations in the game grid.
 * 
 * @author Parth Nobel
 * @version 0.0
 */
public class Location
{
    private Point pt;
    Location(int x, int y)
    {
        pt = new Point(x, y);
    }

    public int getRow()
    {
        return pt.x/View.jbuttonWidth;
    }

    public int getCol()
    {
        return pt.y/View.jbuttonWidth;
    }

    public Point getPoint()
    {
        return pt;
    }
    
    public int getX()
    {
        return pt.x;
    }
    
    public int getY()
    {
        return pt.y;
    }

    public String toString()
    {
        return "Location: (" + pt.x + ", " + pt.y + ")";
    }

    public boolean equals(Location loc)
    {
        return loc.getX() == getX() && loc.getY() == getY();
    }
}
