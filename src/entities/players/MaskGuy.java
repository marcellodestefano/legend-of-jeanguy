package entities.players;


import main.GamePanel;
import utils.Collisions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/*
    MaskGuy est un personnaeg non-jouable, ennemi de Jean-Guy.

    son constructeur initialise ses attributs en appelant le constructeur de la classe mère NonPlayable.
    il extend la classe NonPlayable qui a son tour extend la classe Players.
    Il implémente une méthode afin d'acceder à sa position de départ.
 */
public class MaskGuy extends NonPlayable{
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public MaskGuy(GamePanel panel) {
        super(panel,"MaskGuy#", 1, new ArrayList<Integer>(Arrays.asList(0,0,0)), 1, 4,1,
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
