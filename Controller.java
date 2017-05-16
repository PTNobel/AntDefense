import java.util.List;

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
    }
    public void loop()
    {
        boolean cakeEaten = false, gameWon = false;
        
        while (!cakeEaten && !gameWon)
        {
            ActResult move = m.act();
            v.setProgress(move.progress);
            
            for (Character charac: move.deadCharacters)
            {
                v.removeCharacter(charac);
            }

            for (Character charac: move.newCharacters)
            {
                v.addCharacter(charac);
            }

            for (Character charac: move.movedCharacters)
            {
                // v.moveCharacter(charac);
            }
        }

        do {
            try {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
            }
        } while (!safeToAct);
    }

    public void pickDefender(StoreItem si)
    {
        safeToAct = false;
        if (m.selectDefenderToPlace(si))
        {
            // Update screen, with selection and Gold
        }
        safeToAct = true;
    }

    public void placeDefender(Location loc)
    {
        safeToAct = false;
        Defender newDef = m.placeDefender(loc);
        if (newDef != null)
        {
            v.addCharacter(newDef);
        }
        safeToAct = true;
    }
}
