/*
 *
 * Copyright 2017 Parth Nobel, Chase Carnaroli, Tiffany Nguyen, and Ignatius Widjaja
 *
 * This file is part of Ant Defense.
 *
 * Ant Defense is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Ant Defense is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Ant Defense.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.util.List;

/**
 * This is solely an encapsulating class for moving the data on a round from 
 * Model to Controller
 * 
 * METHODS
 *  void setDeadCharacters(List<Character> deadCharacters)
 *  List<Character> getDeadCharacters()
 *  void setNewCharacters(List<Character> newCharacters)
 *  List<Character> getNewCharacters()
 *  void setMovedCharacters(List<Character> movedCharacters)
 *  List<Character> getMovedCharacters()
 *  void setCakeEaten(boolean eaten)
 *  boolean getCakeEaten()
 *  void setGold(int gold)
 *  int getGold()
 *  void setProgress(int prog)
 *  int getProgress()
 *  void setGameOver(boolean gameOver)
 *  boolean getGameWon()
 *
 */
public class ActResult
{
    private List<Character> deadCharacters;
    private List<Character> newCharacters;
    private boolean cakeEaten, gameOver;
    // Progress is the number of ants killed so far 
    private int progress;
    private int gold;
    private List<Character> movedCharacters;
    
    public void setDeadCharacters(List<Character> deadCharacters)
    {
        this.deadCharacters = deadCharacters;
    }
    
    public List<Character> getDeadCharacters()
    {
        return deadCharacters;
    }
    
    public void setNewCharacters(List<Character> newCharacters)
    {
        this.newCharacters = newCharacters;
    }
    
    public List<Character> getNewCharacters()
    {
        return newCharacters;
    }
    
    public void setMovedCharacters(List<Character> movedCharacters)
    {
        this.movedCharacters = movedCharacters;
    }
    
    public List<Character> getMovedCharacters()
    {
        return movedCharacters;
    }
    
    public void setCakeEaten(boolean eaten)
    {
        cakeEaten = eaten;
    }
    
    public boolean getCakeEaten()
    {
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
    
    public void setProgress(int prog)
    {
        progress = prog;
    }
    
    public int getProgress()
    {
        return progress;
    }
    
    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }
    
    public boolean getGameWon()
    {
        return gameOver;
    }
}
