package entities.players;


import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;
/*
    MaskGuy est un personnaeg non-jouable, ennemi de Jean-Guy.

    son constructeur initialise ses attributs en appelant le constructeur de la classe mère NonPlayable.
    il extend la classe NonPlayable qui a son tour extend la classe Players.
    Il implémente une méthode afin d'acceder à sa position de départ.
 */
public class MaskGuy extends NonPlayable{
    public MaskGuy(GamePanel panel) {
        super(panel,"MaskGuy", 5, new ArrayList<Integer>(Arrays.asList(3,3,0)), 1, 30,2,false,true,1,true, Arrays.asList("",""),Arrays.asList("",""));
    }
}
