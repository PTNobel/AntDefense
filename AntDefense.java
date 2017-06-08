import javax.swing.JOptionPane; 

public class AntDefense
{
    public static void main(String[] args)
    {
        PictureLoader picLoader =  new PictureLoader();

        Model game = new Model(getDifficulty());
        Controller control = new Controller(game);
        View view = new View(game, control);
        control.setView(view);

        control.loop();
    }

    public static LevelGenerator getDifficulty()
    {
        // Create the dialog.
        // String[] possibleValues = {"Trivial", "Easy", "Medium", "Hard"};
        String[] possibleValues = {"Easy", "Medium", "Hard"};
        Integer selectedValue = JOptionPane.showOptionDialog(null,
                "Choose a difficulty.", "Ant Defense",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null,  possibleValues, possibleValues[0]);
        LevelGenerator lg;
        switch (selectedValue)
        {

            case 0: lg = new EasyGenerator();
                break;
            
            case 1: lg = new MediumGenerator();
                break;
            
            case 2: lg = new HardGenerator();
                break;

            default: lg = new TrivialGenerator();
                break;
        }

        return lg;
    }
}
