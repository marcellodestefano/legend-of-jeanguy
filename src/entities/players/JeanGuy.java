package entities.players;


import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import input.KeyHandler;
import main.GamePanel;
import utils.AttackCollisions;
import utils.CollisionEquipement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class JeanGuy extends Playable {

    protected KeyHandler keyHandler;
    protected int hpmax =5;
    protected ArrayList<String> attackSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/attackup.png","/assets/playerattack/attackdown.png",
            "/assets/playerattack/attackleft.png","/assets/playerattack/attackright.png"));
    protected ArrayList<String> weaponsSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/swordup.png","/assets/playerattack/sworddown.png",
            "/assets/playerattack/swordright.png","/assets/playerattack/swordleft.png","/assets/playerattack/slashupleft.png","/assets/playerattack/slashdownleft.png",
            "/assets/playerattack/slashupright.png","/assets/playerattack/slashdownright.png"));
    protected ArrayList<String> damageSprites = new ArrayList<>(Arrays.asList("/assets/playerhit/hithaut1.png","/assets/playerhit/hithaut2.png",
            "/assets/playerhit/hitbas1.png","/assets/playerhit/hitbas2.png","/assets/playerhit/hitgauche1.png","/assets/playerhit/hitgauche2.png",
            "/assets/playerhit/hitdroit1.png","/assets/playerhit/hitdroit2.png"));

    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, dead;
    protected BufferedImage atkup, atkleft, atkdown, atkright;
    protected BufferedImage swordup, sworddown, swordright,swordleft,swordupleft,sworddownleft,swordupright,sworddownright;
    protected BufferedImage degatup1, degatup2, degatdown1, degatdown2, degatright1, degatright2, degatleft1, degatleft2;
    protected int cpdmg = 0;
    protected ArrayList<Integer> positionImage2 = new ArrayList<>(Arrays.asList(0,0,0));
    protected String lastdir = "down";
    protected String dmgdir;
    public JeanGuy(GamePanel panel, KeyHandler keyHandler) {
        super(panel,"Jean-Guy", 1, new ArrayList<Integer>(Arrays.asList(100,100,0)), 2, 5,3,
                false,true,2,true, Arrays.asList("",""), Arrays.asList("/assets/player/Haut1.png",
                        "/assets/player/Haut2.png","/assets/player/Bas1.png","/assets/player/Bas2.png", "/assets/player/Gauche1.png",
                        "/assets/player/Gauche2.png","/assets/player/Droite1.png","/assets/player/Droite2.png"));

        this.keyHandler = keyHandler;
        this.getPlayerImage();


    }

    public int getHpMax() {
        return hpmax;
    }

    public int SetHpMax(CoeurMax coeurMax) {
        return hpmax++;
    }

    public void receiveDamage(int damage, String dir){
        this.hp = Math.max(0,this.hp-=damage);
        this.dmgdir = dir;
        this.cpdmg = 12;
    }
    public void rammasserBouclier(){
        this.spritesPaths.set(0, "/assets/playershield/Haut1.png");
        this.spritesPaths.set(1, "/assets/playershield/Haut2.png");
        this.spritesPaths.set(2, "/assets/playershield/Bas1.png");
        this.spritesPaths.set(3, "/assets/playershield/Bas2.png");
        this.spritesPaths.set(4, "/assets/playershield/Gauche1.png");
        this.spritesPaths.set(5, "/assets/playershield/Gauche2.png");
        this.spritesPaths.set(6, "/assets/playershield/Droite1.png");
        this.spritesPaths.set(7, "/assets/playershield/Droite2.png");
        this.getPlayerImage();
    }

    public void ramasserCoeur(){
        this.hp = Math.min(this.hp+1, this.hpmax);
    }

    public void ramasserCoeurMax(){
        this.hpmax += 1;
        this.hp = hpmax;
    }

    @Override
    public void getPlayerImage() {
        try{
            //movements
            this.up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));
            this.down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(2))));
            this.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(3))));
            this.left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(4))));
            this.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(5))));
            this.right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(6))));
            this.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(7))));

            //attack movements
            this.atkup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(0))));
            this.atkdown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(1))));
            this.atkleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(2))));
            this.atkright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(3))));

            //attack sword
            this.swordup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(0))));
            this.sworddown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(1))));
            this.swordleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(3))));
            this.swordright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(2))));
            this.swordupleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(4))));
            this.sworddownleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(5))));
            this.swordupright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(6))));
            this.sworddownright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(7))));

            //damage endured
            this.degatup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(0))));
            this.degatup2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(1))));
            this.degatdown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(2))));
            this.degatdown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(3))));
            this.degatleft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(4))));
            this.degatleft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(5))));
            this.degatright1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(6))));
            this.degatright2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(7))));
            //dead
            this.dead = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/playerdeath/linkdeath.png")));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update() {

        direction = lastdir;
        if (cpdmg!=0){
            direction = dmgdir;
        }


        if (this.isDead()){
            direction = "dead";
        }else{
            if (this.direction.equals("up-player")) {
                spriteCounter++;
                position.set(1, Math.max(0,position.get(1) - 5));
                cpdmg--;
            }else if (this.direction.equals("down-player")) {
                spriteCounter++;
                position.set(1, Math.min(gamePanel.getHeight()-gamePanel.tileSize, position.get(1) + 5));
                cpdmg--;
            }else if (this.direction.equals("left-player")) {
                spriteCounter++;
                position.set(0, Math.max(0,position.get(0) - 5));
                cpdmg--;
            }else if (this.direction.equals("right-player")) {
                spriteCounter++;
                position.set(0, Math.min(gamePanel.getWidth()- gamePanel.tileSize,position.get(0) + 5));
                cpdmg--;
            }else{

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
                Players receiver = AttackCollisions.attackCollisions(gamePanel.personnages, direction, this, gamePanel.tileSize);

                if (receiver!=null){
                    receiver.receiveDamage(this.inventaire.get(0).getUnite(), direction);
                }
            }else {

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
            }

        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }}}
        System.out.println(this.hp);
        System.out.println(this.hpmax);
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
            case "up-player":
                if (spriteNum == 1) {
                    image = degatup1;
                }
                if (spriteNum == 2) {
                    image = degatup2;
                }
                break;
            case "down-player":
                if (spriteNum == 1) {
                    image = degatdown1;
                }
                if (spriteNum == 2) {
                    image = degatdown2;
                }
                break;
            case "left-player":
                if (spriteNum == 1) {
                    image = degatleft1;
                }
                if (spriteNum == 2) {
                    image = degatleft2;
                }
                break;
            case "right-player":
                if (spriteNum == 1) {
                    image = degatright1;
                }
                if (spriteNum == 2) {
                    image = degatright2;
                }
                break;
            case "dead":
                image = dead;
                break;
        }
        Equipements ramasse= CollisionEquipement.collisionEquipement(gamePanel.equipements, this, gamePanel.tileSize);
        if (ramasse !=null){
            if (ramasse instanceof BouclierBois){
                this.setInventaire(ramasse);
                ramasse.setRamasser();
                this.rammasserBouclier();}
            else if (ramasse instanceof CoeurMax){
                this.ramasserCoeurMax();
                ramasse.setRamasser();

            }else if (ramasse instanceof Coeur){
                this.ramasserCoeur();
                ramasse.setRamasser();
            }


        }
        if (this.cpdmg==0){
            dmgdir=null;

        }
        g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        if (image2 != null) {
            g2.drawImage(image2, positionImage2.get(0), positionImage2.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        }
    };

}
