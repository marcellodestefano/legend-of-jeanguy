package entities.players;


import input.KeyHandler;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class JeanGuy extends Playable {

    protected KeyHandler keyHandler;


    public JeanGuy(GamePanel panel, KeyHandler keyHandler) {
        super(panel,"Jean-Guy", 0, new ArrayList<Integer>(Arrays.asList(100,100,0)), 2, 100,3,
                false,true,2,true, Arrays.asList("",""), Arrays.asList("/assets/player/Haut1.png",
                        "/assets/player/Haut2.png","/assets/player/Bas1.png","/assets/player/Bas2.png", "/assets/player/Gauche1.png",
                        "/assets/player/Gauche2.png","/assets/player/Droite1.png","/assets/player/Droite2.png"));

        this.keyHandler = keyHandler;


    }

    @Override
    public void update() {
        if (keyHandler.upPressed) {
            direction = "up";
            spriteCounter++;
            position.set(1, position.get(1) - speed);
        }
        if (keyHandler.downPressed) {
            direction = "down";
            spriteCounter++;
            position.set(1, position.get(1) + speed);
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            spriteCounter++;
            position.set(0, position.get(0) - speed);
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            spriteCounter++;
            position.set(0, position.get(0) + speed);
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
