import javax.swing.JOptionPane; 

public class TrivialDefense
{
    public static void main(String[] args)
    {
        PictureLoader picLoader =  new PictureLoader();
        LevelGenerator lg = new TrivialGenerator();


        Model game = new Model(lg);
        Controller control = new Controller(game);
        View view = new View(game, control);
        control.setView(view);

        control.loop();
    }
}
