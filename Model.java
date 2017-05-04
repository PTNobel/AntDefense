import java.util.LinkedList;
import java.util.ListIterator;
import java.util.List;

/**
 * Write a description of class Model here.
 * 
 * @author Parth
 * @version 0.0
 */
public class Model
{
    private final int NUM_ROWS = 5;
    private final int NUM_COLS = 10;
    private Defender[][] board;
    private List<Defender> defenderList;
    private List<Ant> antList;
    public Model()
    {
        board = new Defender[NUM_ROWS][NUM_COLS];
        antList = new LinkedList<Ant>();
        defenderList = new LinkedList<Defender>();
    }

    public void addAnts(Ant... ants)
    {
        for (Ant ant: ants)
        {
            antList.add(ant);
        }
    }

    /**
     * Returns true if defender was placed;
     * Returns false if defender was not placed.
     */
    public boolean addDefender(Defender defend)
    {
        if (getDefenderAtLoc(defend.getLoc()) == null)
        {
            setDefenderAtLoc(defend);
            defenderList.add(defend);
            return true;
        } 
        else
            return false;
    }

    public Defender getDefenderAtLoc(Location loc)
    {
        return board[loc.getRow()][loc.getCol()];
    }

    public void setDefenderAtLoc(Defender def)
    {
        Location loc = def.getLoc();
        board[loc.getRow()][loc.getCol()] = def;
    }

    /**
     * Returns a List containing all the ants that died because of the defender run.
     */
    public List<Ant> runDefenders()
    {
        List<Ant> output = new LinkedList<Ant>();

        for (Defender def: defenderList)
        {
            for (Ant ant: def.processAnts(antList))
            {
                output.add(ant);
                antList.remove(ant);
            }
        }

        return output;
    }

    /**
     * Returns a List containing all the ants that died because of the defender run.
     */
    public List<Defender> runAnts()
    {
        List<Defender> output = new LinkedList<Defender>();

        for (Ant ant: antList)
        {
            for (Defender def: ant.act(defenderList))
            {
                output.add(def);
                defenderList.remove(def);
            }
        }

        return output;
    }

    public List<Defender> reapDefenders()
    {
        List<Defender> output = new LinkedList<Defender>();

        for (int c, r = 0; r < board.length; r++)
            for (c = 0; c < board[r].length; c++)
            {
                if (board[r][c] != null && board[r][c].dead())
                {
                    output.add(board[r][c]);
                    board[r][c] = null;
                }
            }

        return output;
    }
}