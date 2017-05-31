import javax.swing.*;
import java.net.URL;
import java.io.*;

public class PictureLoader
{
    private static boolean built = false;
    public static ImageIcon antDefenseUI;
    public static ImageIcon poisonFood, poisonSpray, squirtGun, woodenBlock;
    public static ImageIcon dancingAnt, reidAnt, warriorAnt, workerAnt;

    public PictureLoader()
    {
        if (built)
            return;
        ClassLoader cldr = this.getClass().getClassLoader();
        try {
            antDefenseUI = new ImageIcon(cldr.getResource("AntDefenseUI.png"));
            poisonFood = new ImageIcon(cldr.getResource("poisonFood.png"));
            poisonSpray = new ImageIcon(cldr.getResource("poisonSpray.png"));
            squirtGun = new ImageIcon(cldr.getResource("squirtGun.png"));
            woodenBlock = new ImageIcon(cldr.getResource("woodenBlock.png"));
            dancingAnt = new ImageIcon(cldr.getResource("dancingAnt.png"));
            reidAnt = new ImageIcon(cldr.getResource("reidAnt.png"));
            warriorAnt = new ImageIcon(cldr.getResource("warriorAnt.png"));
            workerAnt = new ImageIcon(cldr.getResource("workerAnt.png"));
            built = true;
        }
        catch (Exception e)
        {
            System.out.println("Failed to load pictures: " + e.getMessage());
        }

        System.out.println("Built: " + built);
    }
}
