package entities.players;


import main.GamePanel;
import utils.Collisions;

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
        super(panel,"MaskGuy#", 1, new ArrayList<Integer>(Arrays.asList(0,0,0)), 1, 30,1,
                false,true,1,true, Arrays.asList("",""),Arrays.asList("/assets/ennemies/maskass/Haut1.png",
                        "/assets/ennemies/maskass/Haut2.png","/assets/ennemies/maskass/Bas1.png","/assets/ennemies/maskass/Bas2.png",
                        "/assets/ennemies/maskass/Gauche1.png", "/assets/ennemies/maskass/Gauche2.png","/assets/ennemies/maskass/Droite1.png",
                        "/assets/ennemies/maskass/Droite2.png"));
        this.startPosition();
    }

    public void startPosition(){
        this.position.set(0, 400);
        this.position.set(1, 50+this.id*100);
    }






}
