import java.util.List;

public interface LevelGenerator
{
    // The total number of attackers in this round.
    public int getNumAttackers();

    // Each turn this will be called. It will return a list of ants for that
    // turn.
    // i.e. on turn 1 it may return no ants.
    // On turn 3 it may return a single worker ant
    // On turn 50 it may return a queen ant and three warrior ants
    // These ants will recieve their location from level generator.
    public List<Ant> generateAnts();
}
