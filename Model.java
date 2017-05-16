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
    private final int NUM_ROWS, NUM_COLS, NUM_ATTACKERS;
    private Defender[][] board;
    private List<Defender> defenderList;
    private List<Ant> antList;

    private int gold;
    private StoreItem curSelectedDefender = null;

    public Model()
    {
        NUM_ATTACKERS = 20;
        NUM_ROWS = 5;
        NUM_COLS = 10;
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
    
    public int getNumRows()
    {
        return NUM_ROWS;
    }
    
    public int getNumCols()
    {
        return NUM_COLS;
    }
    
    public int getNumAttackers()
    {
        return NUM_ATTACKERS;
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

    public ActResult act()
    {
        ActResult output = new ActResult();
        // add new defenders on board
        // move ants
        // add new ants
        // process actors
        // track dead ants
        return output;
    }

    public boolean selectDefenderToPlace(StoreItem si)
    {
        curSelectedDefender = si;

        // Switch for defender cost
        switch (si)
        {
        }

        // check if player can afford defender
            // subtract from gold if they can
            // return true
        // Can't afford it?
            // return false;
        return false;
    }

    public Defender placeDefender(Location loc)
    {
        // Switch curSelectedDefender
        // init and place if valid
        // else return null
        return null;
    }

}
