package entities.equipements.soins;

import entities.equipements.Equipements;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Soins extends Equipements{
    protected static int dropPercentage;

    public Soins(GamePanel gp, boolean drop, int dropPercentage, String name, int unite, List<String> spritePath, ArrayList<Integer> position){
        super(gp, drop, name, unite, spritePath, position);
        this.dropPercentage = dropPercentage;
        System.out.println(dropPercentage + name);
    }

    public static int getDropPercentage() {
        return dropPercentage;
    }

    public void setPosition(ArrayList<Integer>position){
        this.position = position;
    }
}
