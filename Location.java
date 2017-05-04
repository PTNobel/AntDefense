
/**
 * Keeps track of locations in the game grid.
 * 
 * @author Parth Nobel
 * @version 0.0
 */
public class Location
{
    private int row;
    private int col;
    
    Location(int r, int c)
    {
        row = r;
        col = c;
    }
    
    public int getRow()
    {return row;}
    
    public int getCol()
    {return col;}
}
