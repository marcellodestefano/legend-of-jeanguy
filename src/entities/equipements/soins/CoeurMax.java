package entities.equipements.soins;

import entities.players.JeanGuy;
import main.GamePanel;

import java.util.*;

public class CoeurMax extends Soins{

    CoeurMax(GamePanel gp){
        super(gp,true, "CoeurMax", 1, new ArrayList<String>(Arrays.asList("","")), 5,new ArrayList<Integer>(Arrays.asList(100,100,0)));
    }
}
