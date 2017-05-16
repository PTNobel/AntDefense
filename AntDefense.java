public class AntDefense
{
    public static void main(String[] args)
    {
        Model game = new Model();
        Controller control = new Controller(game);
        View view = new View(game, control);
        control.setView(view);

        control.loop();
    }
}
