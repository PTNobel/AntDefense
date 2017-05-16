import java.util.List;

public class ActResult
{
    public List<Character> deadCharacters;
    public List<Character> newCharacters;
    public boolean cakeEaten;
    public int progress;
    List<Character> movedCharacters;
    
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
    
    public List<Character> setMovedCharacters(){
        return movedCharacters;
    }
    
    public void setCakeEater(boolean eaten){
        cakeEaten = eaten;
    }
    
    public boolean getCakeEaten(){
        return cakeEaten;
    }
    
    public void progressIncreased(){
        // finish later
    }
    
    public void getProgress(){
        // finish later
    }
}