import java.util.List;
import java.util.ArrayList;

public abstract class LevelGenerator
{
    // instance variables
    protected int completed = 0; 
    protected int max;
    
    // location variables
    private int numRows = 5;
    private int yGap = 40;
    private int xStart = 760;
    protected List<Location> rows = new ArrayList<Location>();
    protected Location row1 = new Location(xStart, yGap);
    protected Location row2 = new Location(xStart, yGap * 2);
    protected Location row3 = new Location(xStart, yGap * 3);
    protected Location row4 = new Location(xStart, yGap * 4);
    protected Location row5 = new Location(xStart, yGap * 5);
  
    public LevelGenerator(int max){
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        this.max = max;
    }
    
    // The total number of attackers in this round.
    public int getNumAttackers(){
        return max;
    }

    public Location selectRandomRow(){
        int randomRow = (int)(Math.random()*numRows);
        return rows.get(randomRow);
    }

    // Each turn this will be called. It will return a list of ants for that
    // turn.
    // i.e. on turn 1 it may return no ants.
    // On turn 3 it may return a single worker ant
    // On turn 50 it may return a queen ant and three warrior ants
    // These ants will recieve their location from level generator.
    public abstract List<Ant> generateAnts();
}
