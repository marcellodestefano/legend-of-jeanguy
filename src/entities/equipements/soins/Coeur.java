package entities.equipements.soins;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class Coeur extends Soins{

    Coeur(GamePanel gp)
    {
        super(gp,true, "Coeur", 1, new ArrayList<String>(Arrays.asList("","")), 0, new ArrayList<Integer>(Arrays.asList(100,100,0)));
    }
}
