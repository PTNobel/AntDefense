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

        while (!cakeEaten && !gameWon)
        {
            safeToAct = false;
            ActResult move = m.act();
            v.setProgress(move.getProgress());

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

            gameWon = move.getGameWon();
            cakeEaten = move.getCakeEaten();
            
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
    }

    public void pickDefender(StoreItem si)
    {
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
}
