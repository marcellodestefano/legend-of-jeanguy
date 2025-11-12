package entities.equipements.soins;

import entities.equipements.Equipements;
import main.GamePanel;

import java.util.List;

abstract class Soins extends Equipements{
    protected int dropPercentage;

    public Soins(GamePanel gp, boolean drop, String name, int unite, List<String> spritePath, int dropPercentage){
        super(gp, drop, name, unite, spritePath);
        this.dropPercentage = dropPercentage;

    }

    public int getDropPercentage() {
        return dropPercentage;
    }
}
