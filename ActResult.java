import java.util.List;

public class ActResult
{
    private List<Character> deadCharacters;
    private List<Character> newCharacters;
    private boolean cakeEaten;
    private int progress, gold;
    private List<Character> movedCharacters;
    
    public ActResult(){
        // do we need anything in here?
    }
    
    public void setDeadCharacters(List<Character> deadCharacters){
        this.deadCharacters = deadCharacters;
    }
    
    public List<Character> getDeadCharacters(){
        return deadCharacters;
    }
    
    public void setNewCharacters(List<Character> newCharacters){
        this.newCharacters = newCharacters;
    }
    
    public List<Character> getNewCharacters(){
        return newCharacters;
    }
    
    public void setMovedCharacters(List<Character> movedCharacters){
        this.movedCharacters = movedCharacters;
    }
    
    public List<Character> getMovedCharacters(){
        return movedCharacters;
    }
    
    public void setCakeEaten(boolean eaten){
        cakeEaten = eaten;
    }
    
    public boolean getCakeEaten(){
        return cakeEaten;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public int getGold()
    {
        return this.gold;
    }
    
    public void setProgress(int prog){
        progress = prog;
    }
    
    public int getProgress(){
        return progress;
    }
}
