package entities.equipements.soins;

import entities.equipements.Equipements;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Soins extends Equipements{
    protected int dropPercentage;

    public Soins(GamePanel gp, boolean drop, String name, int unite, List<String> spritePath, ArrayList<Integer> position){
        super(gp, drop, name, unite, spritePath, position);
        this.dropPercentage = dropPercentage;

    }



    public void setPosition(ArrayList<Integer>position){
        this.position = position;
    }
}
