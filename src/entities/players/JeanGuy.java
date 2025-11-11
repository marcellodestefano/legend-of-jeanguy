package entities.players;


import entities.equipements.soins.CoeurMax;
import input.KeyHandler;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class JeanGuy extends Playable {

    protected KeyHandler keyHandler;
    protected int HpMax =5;
    protected ArrayList<String> attackSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/attackup.png","/assets/playerattack/attackdown.png",
            "/assets/playerattack/attackleft.png","/assets/playerattack/attackright.png"));
    protected ArrayList<String> weaponsSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/swordup.png","/assets/playerattack/sworddown.png",
            "/assets/playerattack/swordright.png","/assets/playerattack/swordleft.png","/assets/playerattack/slashupleft.png","/assets/playerattack/slashdownleft.png",
            "/assets/playerattack/slashupright.png","/assets/playerattack/slashdownright.png"));

    protected BufferedImage atkup, atkleft, atkdown, atkright, swordup, sworddown, swordright,swordleft,swordupleft,sworddownleft,swordupright,sworddownright;
    protected ArrayList<Integer> positionImage2 = new ArrayList<>(Arrays.asList(0,0,0));
    protected String lastdir = "down";
    public JeanGuy(GamePanel panel, KeyHandler keyHandler) {
        super(panel,"Jean-Guy", 0, new ArrayList<Integer>(Arrays.asList(100,100,0)), 2, 5,3,
                false,true,2,true, Arrays.asList("",""), Arrays.asList("/assets/player/Haut1.png",
                        "/assets/player/Haut2.png","/assets/player/Bas1.png","/assets/player/Bas2.png", "/assets/player/Gauche1.png",
                        "/assets/player/Gauche2.png","/assets/player/Droite1.png","/assets/player/Droite2.png"));

        this.keyHandler = keyHandler;
        this.getPlayerImage();


    }

    public int getHpMax() {
        return HpMax;
    }

    public int SetHpMax(CoeurMax coeurMax) {
        return HpMax++;
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
            this.atkup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(0))));
            this.atkdown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(1))));
            this.atkleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(2))));
            this.atkright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(3))));
            this.swordup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(0))));
            this.sworddown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(1))));
            this.swordleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(3))));
            this.swordright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(2))));
            this.swordupleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(4))));
            this.sworddownleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(5))));
            this.swordupright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(6))));
            this.sworddownright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(7))));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update() {

        direction = lastdir;

        if(keyHandler.atkPressed){
            if (keyHandler.upPressed && keyHandler.leftPressed) {
                direction = "atkleftup";
                spriteCounter++;
            }
            else if (keyHandler.upPressed && keyHandler.rightPressed) {
                direction = "atkrightup";
                spriteCounter++;
            }
            else if (keyHandler.upPressed) {
                direction = "atkup";
                spriteCounter++;
            }
            else if (keyHandler.downPressed && keyHandler.leftPressed) {
                direction = "atkleftdown";
                spriteCounter++;
            }
            else if (keyHandler.downPressed && keyHandler.rightPressed) {
                direction = "atkrightdown";
                spriteCounter++;
            }
            else if (keyHandler.rightPressed ) {
                direction = "atkright";
                spriteCounter++;
            }
            else if (keyHandler.leftPressed ) {
                direction = "atkleft";
                spriteCounter++;
            }
            else if (keyHandler.downPressed ) {
                direction = "atkdown";
                spriteCounter++;
            }
            else{
                direction = "atk"+lastdir;
                spriteCounter++;
            }
        }else{

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
        lastdir = direction;

        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }}
    }
    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        BufferedImage image2 = null;
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
            case "atkright":
                if (spriteNum == 1) {
                    image = atkright;
                }
                if (spriteNum == 2) {
                    image = atkright;
                }
                positionImage2.set(0, position.get(0) + gamePanel.tileSize);
                positionImage2.set(1, position.get(1));
                image2 = swordright;
                break;
            case "atkup":
                if (spriteNum == 1) {
                    image = atkup;
                }
                if (spriteNum == 2) {
                    image = atkup;
                }
                positionImage2.set(0, position.get(0));
                positionImage2.set(1, position.get(1)-gamePanel.tileSize);
                image2 = swordup;
                break;
            case "atkleft":
                if (spriteNum == 1) {
                    image = atkleft;
                }
                if (spriteNum == 2) {
                    image = atkleft;
                }
                image2 = swordleft;
                positionImage2.set(0, position.get(0)-gamePanel.tileSize);
                positionImage2.set(1, position.get(1));
                break;
            case "atkdown":
                if (spriteNum == 1) {
                    image = atkdown;
                }
                if (spriteNum == 2) {
                    image = atkdown;
                }
                image2 = sworddown;
                positionImage2.set(0, position.get(0));
                positionImage2.set(1, position.get(1)+gamePanel.tileSize);
                break;
            case "atkleftdown":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = sworddownleft;
                positionImage2.set(0, position.get(0)-gamePanel.tileSize);
                positionImage2.set(1, position.get(1)+gamePanel.tileSize);
                break;
            case "atkrightdown":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = sworddownright;
                positionImage2.set(0, position.get(0)+gamePanel.tileSize);
                positionImage2.set(1, position.get(1)+gamePanel.tileSize);
                break;
            case "atkrightup":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                image2 = swordupright;

                positionImage2.set(0, position.get(0)+gamePanel.tileSize);
                positionImage2.set(1, position.get(1)-gamePanel.tileSize);
                break;
            case "atkleftup":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = swordupleft;
                positionImage2.set(0, position.get(0)-gamePanel.tileSize);
                positionImage2.set(1, position.get(1)-gamePanel.tileSize);
                break;
        }
        g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        if (image2 != null) {
            g2.drawImage(image2, positionImage2.get(0), positionImage2.get(1), gamePanel.tileSize, gamePanel.tileSize, null);

        }
    };


}
