package entities.players;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Bat extends NonPlayable{
    BufferedImage move1, move2, dead;
    public Bat(GamePanel gamePanel) {
        super(gamePanel,"Bat#", 1,new ArrayList<Integer>(Arrays.asList(0,0,0)),1,3,2,
                false, true, 1,true, Arrays.asList("",""),Arrays.asList("/assets/ennemies/batattck/Bat1.png",
                        "/assets/ennemies/batattck/Bat2.png","/assets/ennemies/batattck/BatDeath.png"));

    }


    @Override
    public void getPlayerImage() {
        try{
            this.move1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.move2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));
            this.dead = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(2))));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        if (this.cpdmg>0){
            image=dead;
        }else{
            if (spriteNum == 1) {
                image = move1;
            }
            if (spriteNum == 2) {
                image = move2;
            }
        }

        g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);


    }
}
