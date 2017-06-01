import java.util.List; 

/**
 * This class communicates information from the Model to the View and vica
 * versa
 *
 * @author Parth Nobel and Chase Carnaroli
 * @version 0.0
 */
public class Controller
{
    private Model m;
    private View v;
    private boolean safeToAct = true;
    private boolean paused = false;

    public Controller(Model model)
    {
        m = model;
    }

    public void setView(View view)
    {
        v = view;
        v.setGoldLabel(m.getGold());
    }

    public void loop()
    {
        boolean cakeEaten = false, gameWon = false;
        int turn = 0;

        while (!cakeEaten && !gameWon)
        {
            safeToAct = false;
            ActResult move = m.act();

            for (Character charac: move.getDeadCharacters())
            {
                v.removeCharacter(charac);
            }

            for (Character charac: move.getNewCharacters())
            {
                v.addCharacter(charac);
            }

            for (Character charac: move.getMovedCharacters())
            {
                v.moveCharacter(charac);
            }

            v.setProgress(move.getProgress());
            v.setGoldLabel(move.getGold());
            gameWon = move.getGameWon();
            cakeEaten = move.getCakeEaten();
            turn++;


            safeToAct = true;
            do {
                try {
                    Thread.sleep(100);
                }
                catch (Exception e)
                {
                }
            } while (!safeToAct);
        }
        
        if (gameWon)
            v.announceWinOrLoss(true);
        if (cakeEaten)
            v.announceWinOrLoss(false);
    }

    public void pickDefender(StoreItem si)
    {
        if (paused)
            return;

        while (!safeToAct){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        safeToAct = false;
        if (m.selectDefenderToPlace(si))
        {
            // Update screen, with selection and Gold
        }
        safeToAct = true;
    }

    public void placeDefender(Location loc)
    {
        if (paused)
            return;

        while (!safeToAct){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        safeToAct = false;
        Defender newDef = m.placeDefender(loc);
        if (newDef != null)
        {
            v.addCharacter(newDef);
        }
        safeToAct = true;
    }
    
    public void pauseGame()
    {
        while (!safeToAct && !paused){
            try {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
            }
        }

        paused = !paused;
        safeToAct = !paused;
    }
}
