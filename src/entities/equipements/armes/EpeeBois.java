package entities.equipements.armes;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class EpeeBois extends Armes{
    protected int prix = 0;

    public EpeeBois(GamePanel gp){
        super(gp, false, "EpeeBois", 1, new ArrayList<String>(Arrays.asList("","")), true, 1,new ArrayList<Integer>(Arrays.asList(100,100,0)));
    }

    public int getPrix(){
        return prix;
    }
}
