package entities.players;


import entities.bullets.Bullets;
import entities.bullets.Octorokatk;
import main.GamePanel;
import utils.AlgorithmMovement;
import utils.AlgorithmMovementRange;
import utils.CollisionDistance;
import utils.Collisions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Octorok extends NonPlayable{
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected int cpAtk=0;
    public Octorok(GamePanel panel) {
        super(panel,"Octorok", 1, new ArrayList<Integer>(Arrays.asList(5,5,0)), 5, 1,2,false,false,1,true, Arrays.asList("",""),
                Arrays.asList("/assets/ennemies/shootingmob/up1.png",
                        "/assets/ennemies/shootingmob/up2.png","/assets/ennemies/shootingmob/down1.png","/assets/ennemies/shootingmob/down2.png",
                        "/assets/ennemies/shootingmob/left1.png", "/assets/ennemies/shootingmob/left2.png","/assets/ennemies/shootingmob/right1.png",
                        "/assets/ennemies/shootingmob/right2.png"));

    }

    @Override
    public void getPlayerImage() {
        try{
            this.up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));
            this.down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(2))));
            this.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(3))));
            this.left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(4))));
            this.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(5))));
            this.right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(6))));
            this.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(7))));
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void update() {
        String dir = AlgorithmMovementRange.algorithmMovementRange(gamePanel,this, cible);
        if (cpAtk==0){
            cpAtk=200;
            Bullets b = new Octorokatk(gamePanel, this, cible);
            gamePanel.bullets.add(b);
        }

        if(this.isDead()){

        }else{


//            if (dir.contains("up")) {
//                direction = "up";
//                spriteCounter++;
//                position.set(1, Math.max(0,position.get(1) - checkSpeed()));
//            }
//            if (dir.contains("down")) {
//                direction = "down";
//                spriteCounter++;
//                position.set(1, Math.min(gamePanel.getHeight() - gamePanel.tileSize,position.get(1) + checkSpeed()));
//            }
//            if (dir.contains("left")) {
//                direction = "left";
//                spriteCounter++;
//                position.set(0, Math.max(0,position.get(0) - checkSpeed()));
//            }
//            if (dir.contains("right")) {
//                direction = "right";
//                spriteCounter++;
//                position.set(0, Math.min(gamePanel.getWidth() - gamePanel.tileSize,position.get(0) + checkSpeed()));
//            }
            cpAtk--;

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

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
    };
}


