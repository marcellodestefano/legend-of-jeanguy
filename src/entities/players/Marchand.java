package entities.players;

import main.GamePanel;
import utils.AlgorithmMovement;
import utils.Collisions;
import utils.CollisionsNpcMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Marchand extends NonPlayable {
    protected BufferedImage move1, move2;
    public Marchand(GamePanel panel) {
        super(panel,"Marchand", 0, new ArrayList<Integer>(Arrays.asList(12,12,0)), 0, 1,0,false,false,0,false, Arrays.asList("",""),Arrays.asList("/assets/world/pnj/marchand/marchantbas1.png","/assets/world/pnj/marchand/marchantbas2.png"));
    }
    @Override
    public void receiveDamage(int damage, String dir) {
    }

    @Override
    public void startPosition(){
        this.position.set(0,500);
        this.position.set(1,200);
    }
    @Override
    public void getPlayerImage() {
        try{
            this.move1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.move2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void update() {
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }spriteCounter = 0;
        }
    }
    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        if (spriteNum == 1) {
            image = move1;
        }
        if (spriteNum == 2) {
            image = move2;
        }

        g2.drawImage(image, position.get(0), position.get(1), gamePanel.getTileSize(), gamePanel.getTileSize(), null);


    }
}
