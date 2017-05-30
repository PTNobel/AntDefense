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
    private static final int NUM_ROWS = 5, NUM_COLS = 10;
    private final int NUM_ATTACKERS;
    private Defender[][] board;
    private List<Defender> defenderList;
    private List<Ant> antList;
    private LevelGenerator lg;
    private int gold, progress;
    private StoreItem curSelectedDefender = null;

    public Model(LevelGenerator levelGenerator)
    {
        lg = levelGenerator;
        NUM_ATTACKERS = lg.getNumAttackers();
        board = new Defender[NUM_ROWS][NUM_COLS];
        antList = new LinkedList<Ant>();
        defenderList = new LinkedList<Defender>();
        progress = 0;
        gold = 50;
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
    defenderList.add(def);
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
 
    public ActResult act()
    {
        ActResult output = new ActResult();
        List<Character> deadCharacters = new LinkedList<Character>();
        List<Character> newCharacters = new LinkedList<Character>();
        List<Character> movedCharacters = new LinkedList<Character>();
        output.setCakeEaten(false);
        // move ants
        for (Ant ant:antList)
        {
            Location oldLoc = ant.getLoc();
            List<Defender> killedDefs = ant.act(defenderList);
            for (Defender def: killedDefs)
            {
                deadCharacters.add(def);
                defenderList.remove(def);
            }
            if (!oldLoc.equals(ant.getLoc()))
            {
                movedCharacters.add(ant);
            }
            if (ant.getLoc().getX() < 0)
                output.setCakeEaten(true);
        }
        // add new ants
        for (Ant newAnt: lg.generateAnts())
        {
            antList.add(newAnt);
            newCharacters.add(newAnt);
        }
        // process defenders
        for (Defender def: defenderList)
        {
            for (Ant ant: def.processAnts(antList))
            {
                deadCharacters.add(ant);
                antList.remove(ant);
                progress++;
                gold += ant.getGold();
            }
        }


        //
        output.setDeadCharacters(deadCharacters);
        output.setNewCharacters(newCharacters);
        output.setMovedCharacters(movedCharacters);
        output.setProgress(progress);
        output.setGold(gold);
        output.setGameOver(progress==NUM_ATTACKERS);

        return output;
    }

    public boolean selectDefenderToPlace(StoreItem si)
    {
        if(gold >= si.COST){
            curSelectedDefender = si;
            return true;
        } else {
            return false;
        }
    }

    /*
     * pre-condition: gold > curSelectedDefender.getCost()
     */
    public Defender placeDefender(Location loc)
    {
        if(curSelectedDefender == null){
            return null;
        } else if(getDefenderAtLoc(loc) != null){
            return null;
        } else {
            Defender def = curSelectedDefender.getDefender(loc);
            gold -= def.getCost();
            setDefenderAtLoc(def);
            curSelectedDefender = null;
            return def;
        }
    }
    
    public boolean getGameWon(){
        return NUM_ATTACKERS == progress;
    }

    public int getGold()
    {
        return gold;
    }
}
