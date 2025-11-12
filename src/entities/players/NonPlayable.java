package entities.players;


import main.GamePanel;
import utils.AlgorithmMovement;

import java.util.*;

public abstract class NonPlayable extends Players{
    protected int id;
    protected static int counter = 0;
    protected Playable cible;

    public NonPlayable(GamePanel panel , String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths) {
        super (panel, name, damage, position, range,  hp,  speed,  isMelee,  attackSpeed,  killable,  soundPaths,spritePaths);
        id = counter++;
        this.name = name + this.id;
    }
    public void cible(Playable cible){
        this.cible = cible;
    }
    public int getId() {
        return id;
    }


    public void update() {
        String dir = AlgorithmMovement.movements(gamePanel,this, cible);

        if (dir == "up" ) {
            direction = "up";
            spriteCounter++;
            position.set(1, Math.max(0,position.get(1) - speed));
        }
        if (dir == "down") {
            direction = "down";
            spriteCounter++;
            position.set(1, Math.min(gamePanel.getHeight()- gamePanel.tileSize,position.get(1) + speed));
        }
        if (dir == "left") {
            direction = "left";
            spriteCounter++;
            position.set(0, Math.max(0,position.get(0) - speed));
        }
        if (dir == "right") {
            direction = "right";
            spriteCounter++;
            position.set(0, Math.min(gamePanel.getWidth()- gamePanel.tileSize,position.get(0) + speed));
        }




        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }



}
