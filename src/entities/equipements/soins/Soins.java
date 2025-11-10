package entities.equipements.soins;

import entities.equipements.Equipements;
import java.util.List;

abstract class Soins extends Equipements{
    protected int dropPercentage;

    public Soins(boolean drop, String name, int unite, List<String> spritePath, int dropPercentage){
        super(drop, name, unite, spritePath);
        this.dropPercentage = dropPercentage;
    }

    public int getDropPercentage() {
        return dropPercentage;
    }
}
