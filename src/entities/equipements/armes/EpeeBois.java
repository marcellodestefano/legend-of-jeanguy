package entities.equipements.armes;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class EpeeBois extends Armes{
    protected int prix = 0;

    public EpeeBois(GamePanel gp){
        super(gp, false, "EpeeBois", 5, new ArrayList<String>(Arrays.asList("","")), true, 1);
    }

    public int getPrix(){
        return prix;
    }
}
